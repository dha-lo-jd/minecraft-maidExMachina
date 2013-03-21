package net.minecraft.src;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;

public class EntityMEMBlockWrapper extends Entity {

	public double childLastTickPosX;

	public double childLastTickPosY;

	public double childLastTickPosZ;

	public float childLastTickRotationPitch;

	public float childLastTickRotationYaw;

	public float childLastTickRotationZ;
	public final EntityCoreSugar coreEntity;
	public final int innerWorldX, innerWorldY, innerWorldZ;
	public float rotationZ;
	private final TileEntity myTileEntity;
	private EntityCoreSugar.UpdateJob preUpdateJob = new EntityCoreSugar.UpdateJob() {
		@Override
		public void update() {
			// List list = worldObj.getEntitiesWithinAABBExcludingEntity(
			// EntityMEMBlockWrapper.this, boundingBox.expand(0, 0.1, 0));
			//
			// if (list != null && list.size() > 0) {
			// for (int i = 0; i < list.size(); i++) {
			// Entity entity = (Entity) list.get(i);
			// if (!(entity instanceof EntityMEMBlockWrapper)
			// && !(entity instanceof EntityCoreSugar)) {
			// coreEntity.registBlockRidingEntity(
			// EntityMEMBlockWrapper.this, entity);
			// }
			// }
			// }
		}
	};

	private EntityCoreSugar.UpdateJob updateJob = new EntityCoreSugar.UpdateJob() {
		private boolean first = true;

		@Override
		public void update() {
			childLastTickPosX = posX;
			childLastTickPosY = posY;
			childLastTickPosZ = posZ;
			childLastTickRotationYaw = rotationYaw;
			childLastTickRotationPitch = rotationPitch;
			childLastTickRotationZ = rotationZ;
			EntityMEMBlockWrapper.super.onUpdate();
			Block block = Block.blocksList[getBlockID()];
			if (block == null) {
				setDead();
				return;
			}

			if (first || isCoreRotated()) {
				rotatedInnerVec3D.xCoord = innerWorldX
						- EntityCoreSugar.myWorldMyPosX;
				rotatedInnerVec3D.yCoord = innerWorldY
						- EntityCoreSugar.myWorldMyPosY;
				rotatedInnerVec3D.zCoord = innerWorldZ
						- EntityCoreSugar.myWorldMyPosZ;
				EntityRotateSupport.rotateVec3DByEntityRotationWithZ(
						coreEntity, rotationZ, rotatedInnerVec3D);
			}

			EntityPosition ep = new EntityPosition(coreEntity);
			ep.setPosX(coreEntity.posX + rotatedInnerVec3D.xCoord);
			ep.setPosY(coreEntity.posY + rotatedInnerVec3D.yCoord);
			ep.setPosZ(coreEntity.posZ + rotatedInnerVec3D.zCoord);
			ep.setPositonAndRotationToEntity(EntityMEMBlockWrapper.this);
			rotationZ = coreEntity.rotationZ;

			if (myTileEntity != null) {
				ChunkCoordinates cp = new EntityPosition(coreEntity)
						.getChunkCoordinates();

				if (first || isCoreRotated()) {
					cpFixed = EntityCoordsSupport.rotateFixedBoxDirection(
							coreEntity, innerWorldX
									- EntityCoreSugar.myWorldMyPosX,
							innerWorldY - EntityCoreSugar.myWorldMyPosY,
							innerWorldZ - EntityCoreSugar.myWorldMyPosZ);
				}
				myTileEntity.xCoord = cp.posX + cpFixed.posX;
				myTileEntity.yCoord = cp.posY + cpFixed.posY;
				myTileEntity.zCoord = cp.posZ + cpFixed.posZ;
			}

			if (first || isCoreRotated()) {
				rotateBoundingBox();
			}

			for (EntityPlayer sleppingPlayer : sleppingPlayers) {
				if (!sleppingPlayer.isPlayerSleeping()) {
					sleppingPlayer.worldObj = worldObj;
					ChunkCoordinates cp = CoordsSupport
							.toChunkCoordinates(Vec3D.createVector(posX, posY,
									posZ));
					sleppingPlayer.playerLocation = cp;
					ChunkCoordinates chunkcoordinates2 = BlockBed
							.getNearestEmptyChunkCoordinates(worldObj, cp.posX,
									cp.posY, cp.posZ, 0);

					if (chunkcoordinates2 == null) {
						chunkcoordinates2 = new ChunkCoordinates(cp.posX,
								cp.posY + 1, cp.posZ);
					}

					sleppingPlayer.setPosition(
							(float) chunkcoordinates2.posX + 0.5F,
							(float) chunkcoordinates2.posY + yOffset + 0.1F,
							(float) chunkcoordinates2.posZ + 0.5F);
					sleppingPlayer.wakeUpPlayer(true, true, true);
					sleppingPlayers.remove(sleppingPlayer);
				} else {
					setSleepingPlayerPosition(sleppingPlayer);
					ChunkCoordinates cp = new EntityPosition(coreEntity)
							.getChunkCoordinates();
					ChunkCoordinates cpFixed = EntityCoordsSupport
							.rotateFixedBoxDirection(
									coreEntity,
									innerWorldX - EntityCoreSugar.myWorldMyPosX,
									innerWorldY - EntityCoreSugar.myWorldMyPosY,
									innerWorldZ - EntityCoreSugar.myWorldMyPosZ);
					int x = cp.posX + cpFixed.posX;
					int y = cp.posY + cpFixed.posY;
					int z = cp.posZ + cpFixed.posZ;
					sleppingPlayer.playerLocation = new ChunkCoordinates(x, y,
							z);
				}
			}

			checkCollideBlock();

			first = false;
		}
	};

	protected ChunkCoordinates cpFixed = new ChunkCoordinates(0, 0, 0);

	protected boolean hasBoundingBox = true;
	protected Vec3D rotatedInnerVec3D = Vec3D.createVectorHelper(0, 0, 0);

	protected Set<EntityPlayer> sleppingPlayers = new HashSet<EntityPlayer>();

	public EntityMEMBlockWrapper(World par1World, int blockID, int innerWorldX,
			int innerWorldY, int innerWorldZ, EntityCoreSugar coreEntity,
			TileEntity myTileEntity) {
		super(par1World);
		this.innerWorldX = innerWorldX;
		this.innerWorldY = innerWorldY;
		this.innerWorldZ = innerWorldZ;
		this.coreEntity = coreEntity;
		this.myTileEntity = myTileEntity;
		Block block = Block.blocksList[getBlockID()];
		if (block != null) {
			AxisAlignedBB alignedBB = block.getCollisionBoundingBoxFromPool(
					getInnerWorld(), innerWorldX, innerWorldY, innerWorldZ);
			if (alignedBB != null) {
				setSize((float) (alignedBB.maxX - alignedBB.minX),
						(float) (alignedBB.maxY - alignedBB.minY));
			} else {
				setSize(1F, 1F);
			}
		} else {
			setSize(1F, 1F);
		}
		yOffset = 0.5F;
		preventEntitySpawning = true;
		if (coreEntity != null) {
			coreEntity.registPreUpdateJob(preUpdateJob);
			coreEntity.registUpdateJob(updateJob);
			coreEntity.registRenderChild(this);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (isUnBreakable()) {
			return false;
		}
		setDead();
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	@Override
	public boolean canBePushed() {
		return false;
	}

	public int getBlockID() {
		return getInnerWorld().getBlockIdInner(innerWorldX, innerWorldY,
				innerWorldZ);
	}

	public TileEntity getBlockTileEntity() {
		return getInnerWorld().getBlockTileEntityInner(innerWorldX,
				innerWorldY, innerWorldZ);
	}

	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox() {
		if (!hasBoundingBox) {
			return null;
		}
		return null;
	}

	public WorldOneChunk getInnerWorld() {
		return coreEntity.getMyWorld();
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		Block block = Block.blocksList[getBlockID()];
		if (block == null) {
			return boundingBox;
		}
		getInnerWorld().setForceInner(true);
		AxisAlignedBB alignedBB = block.getSelectedBoundingBoxFromPool(
				getInnerWorld(), innerWorldX, innerWorldY, innerWorldZ);
		getInnerWorld().setForceInner(false);
		return AxisAlignedBB.getBoundingBoxFromPool(posX + alignedBB.minX
				- innerWorldX - 0.5, posY + alignedBB.minY - innerWorldY - 0.5,
				posZ + alignedBB.minZ - innerWorldZ - 0.5, posX
						+ alignedBB.maxX - innerWorldX - 0.5, posY
						+ alignedBB.maxY - innerWorldY - 0.5, posZ
						+ alignedBB.maxZ - innerWorldZ - 0.5);
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and
	 * blocks. This enables the entity to be pushable on contact, like boats or
	 * minecarts.
	 */
	// public AxisAlignedBB getCollisionBox(Entity par1Entity) {
	// return par1Entity.boundingBox;
	// }

	public World getWorld() {
		return worldObj;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		ItemStack equippedItem = par1EntityPlayer.getCurrentEquippedItem();
		if (equippedItem != null) {
			if (equippedItem.itemID == mod_MaidExMachina.itemSugarCore.shiftedIndex) {
				EntityMEMBlockWrapper memBlockWrapper = (EntityMEMBlockWrapper) mc.objectMouseOver.entityHit;
				memBlockWrapper.coreEntity.destroyAll();
				return true;
			}
			if (equippedItem.itemID == mod_zabuton.zabuton.shiftedIndex) {
				EntityMEMBlockWrapper b = coreEntity.getMyWorldBlockWrapper(
						innerWorldX, innerWorldY + 1, innerWorldZ);
				if (b != null) {
					return false;
				}
				EntityZabuton ez = new EntityZabuton(worldObj, (float) posX,
						(float) posY + 0.5F, (float) posZ, new ItemStack(
								equippedItem.getItem(), 1,
								equippedItem.getItemDamage()));
				ez.rotationYaw = rotationYaw;
				worldObj.spawnEntityInWorld(ez);
				equippedItem.stackSize--;
				if (equippedItem.stackSize == 0) {
					par1EntityPlayer.inventory.mainInventory[par1EntityPlayer.inventory.currentItem] = null;
				}
				par1EntityPlayer.swingItem();
				return true;
			}
		}

		int i = getInnerWorld().getBlockIdInner(innerWorldX, innerWorldY,
				innerWorldZ);

		boolean flag = false;
		World prevWorld = par1EntityPlayer.worldObj;
		par1EntityPlayer.worldObj = getInnerWorld();
		ChunkCoordinates cp = new EntityPosition(coreEntity)
				.getChunkCoordinates();
		ChunkCoordinates cpFixed = EntityCoordsSupport.rotateFixedBoxDirection(
				coreEntity, innerWorldX - EntityCoreSugar.myWorldMyPosX,
				innerWorldY - EntityCoreSugar.myWorldMyPosY, innerWorldZ
						- EntityCoreSugar.myWorldMyPosZ);
		int x = cp.posX + cpFixed.posX;
		int y = cp.posY + cpFixed.posY;
		int z = cp.posZ + cpFixed.posZ;

		getInnerWorld().relativeChunkCoordinates = cp;
		if (i > 0 && Block.blocksList[i].blockID == Block.chest.blockID) {
			getInnerWorld().relativeChunkCoordinates = null;
		}
		if (i > 0
				&& Block.blocksList[i].blockActivated(getInnerWorld(), x, y, z,
						par1EntityPlayer)) {
			flag = true;
		}

		getInnerWorld().relativeChunkCoordinates = null;
		if (par1EntityPlayer.isPlayerSleeping() && prevWorld == worldObj) {
			sleppingPlayers.add(par1EntityPlayer);
		} else {
			par1EntityPlayer.worldObj = prevWorld;
		}
		return flag || onItemBlockUse(par1EntityPlayer);
	}

	@Override
	public boolean isInRangeToRenderDist(double par1) {
		return false;
	}

	public boolean isInRangeToRenderDistByCore(double par1) {
		return super.isInRangeToRenderDist(par1);
	}

	@Override
	public boolean isInRangeToRenderVec3D(Vec3D par1Vec3D) {
		return false;
	}

	public boolean isInRangeToRenderVec3DByCore(Vec3D par1Vec3D) {
		double d = posX - par1Vec3D.xCoord;
		double d1 = posY - par1Vec3D.yCoord;
		double d2 = posZ - par1Vec3D.zCoord;
		double d3 = d * d + d1 * d1 + d2 * d2;
		return isInRangeToRenderDistByCore(d3);
	}

	public boolean onItemBlockUse(EntityPlayer par1EntityPlayer) {
		if (coreEntity.isDisableInteract()) {
			return false;
		}
		ItemStack itemstack = par1EntityPlayer.getCurrentEquippedItem();
		if (itemstack == null) {
			return false;
		}

		if (itemstack.itemID > 255 && itemstack.itemID != Item.bed.shiftedIndex
				&& itemstack.itemID != Item.cake.shiftedIndex
				&& itemstack.itemID != Item.doorSteel.shiftedIndex
				&& itemstack.itemID != Item.doorWood.shiftedIndex) {
			return false;
		}

		int blockId = itemstack.itemID;
		boolean result;
		int entityBlockSideHit = EntityCoordsSupport.getEntityBlockSideHit(
				this, par1EntityPlayer);
		EntityPosition prevEntityPosition = new EntityPosition(par1EntityPlayer);
		Vec3D innerPosition = coreEntity
				.getInnerEntityInnerPositon(par1EntityPlayer);
		par1EntityPlayer.posX = innerPosition.xCoord;
		par1EntityPlayer.posY = innerPosition.yCoord;
		par1EntityPlayer.posZ = innerPosition.zCoord;
		par1EntityPlayer.rotationYaw = par1EntityPlayer.rotationYaw
				- coreEntity.rotationYaw;
		if (itemstack.itemID > 255) {
			getInnerWorld().setForceInner(true);
			result = itemstack.useItem(par1EntityPlayer, getInnerWorld(),
					innerWorldX, innerWorldY, innerWorldZ, entityBlockSideHit);
			getInnerWorld().setForceInner(false);
		} else {
			getInnerWorld().setForceInner(true);
			result = itemstack.useItem(par1EntityPlayer, getInnerWorld(),
					innerWorldX, innerWorldY, innerWorldZ, entityBlockSideHit);
			getInnerWorld().setForceInner(false);
			// ChunkCoordinates cp =
			// EntityCoordsSupport.getEntityBlockSide(this,
			// par1EntityPlayer);
			//
			// int wX = cp.posX + innerWorldX;
			// int wY = cp.posY + innerWorldY;
			// int wZ = cp.posZ + innerWorldZ;
			// result = coreEntity.addBlock(blockId, wX, wY, wZ);
		}
		if (itemstack != null) {
			if (itemstack.stackSize == 0) {
				par1EntityPlayer.inventory.mainInventory[par1EntityPlayer.inventory.currentItem] = null;
			}
		}

		prevEntityPosition.setPositonAndRotationToEntity(par1EntityPlayer);
		return result;
	}

	@Override
	public final void onUpdate() {
	}

	@Override
	public void setDead() {
		setDeadMEM();
		super.setDead();
	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		setInnerEntityPosition(posX, posY, posZ, par1, par3, par5);
		super.setPosition(par1, par3, par5);
	}

	public boolean shouldRenderCheckByCore() {
		return coreEntity.shouldRenderMEMBlock(this);
	}

	private boolean isCoreRotated() {
		return rotationPitch != coreEntity.rotationPitch
				|| rotationZ != coreEntity.rotationZ
				|| rotationYaw != coreEntity.rotationYaw;
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	protected void checkCollideBlock() {
		int i1 = MathHelper.floor_double(boundingBox.minX + 0.001D);
		int k1 = MathHelper.floor_double(boundingBox.minY + 0.001D);
		int i2 = MathHelper.floor_double(boundingBox.minZ + 0.001D);
		int k3 = MathHelper.floor_double(boundingBox.maxX - 0.001D);
		int l3 = MathHelper.floor_double(boundingBox.maxY - 0.001D);
		int i4 = MathHelper.floor_double(boundingBox.maxZ - 0.001D);

		if (worldObj.checkChunksExist(i1, k1, i2, k3, l3, i4)) {
			for (int j4 = i1; j4 <= k3; j4++) {
				for (int k4 = k1; k4 <= l3; k4++) {
					for (int l4 = i2; l4 <= i4; l4++) {
						int i5 = worldObj.getBlockId(j4, k4, l4);

						if (i5 > 0) {
							coreEntity.addCollisionBlockPos(this, j4, k4, l4);
						}
					}
				}
			}
		}
	}

	@Override
	protected void entityInit() {
	}

	protected boolean isUnBreakable() {
		return false;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
	}

	protected void rotateBoundingBox() {
		hasBoundingBox = false;
		Block block = Block.blocksList[getBlockID()];
		if (block != null && boundingBox != null) {
			hasBoundingBox = true;
			getInnerWorld().setForceInner(true);
			AxisAlignedBB alignedBB = block.getCollisionBoundingBoxFromPool(
					getInnerWorld(), innerWorldX, innerWorldY, innerWorldZ);
			getInnerWorld().setForceInner(false);
			Vec3D boundVec3dMin;
			Vec3D boundVec3dMax;
			if (alignedBB != null) {
				boundVec3dMin = EntityCoordsSupport.rotateFixedBoxDirection(
						coreEntity, alignedBB.minX - innerWorldX - 0.5,
						alignedBB.minY - innerWorldY - 0.5, alignedBB.minZ
								- innerWorldZ - 0.5);
				boundVec3dMax = EntityCoordsSupport.rotateFixedBoxDirection(
						coreEntity, alignedBB.maxX - innerWorldX - 0.5,
						alignedBB.maxY - innerWorldY - 0.5, alignedBB.maxZ
								- innerWorldZ - 0.5);
			} else {
				boundVec3dMin = EntityCoordsSupport.rotateFixedBoxDirection(
						coreEntity, boundingBox.minX - posX - 0.5,
						boundingBox.minY - posY - 0.5, boundingBox.minZ - posZ
								- 0.5);
				boundVec3dMax = EntityCoordsSupport.rotateFixedBoxDirection(
						coreEntity, boundingBox.maxX - posX - 0.5,
						boundingBox.maxY - posY - 0.5, boundingBox.maxZ - posZ
								- 0.5);
			}
			double minX = boundVec3dMin.xCoord < boundVec3dMax.xCoord ? boundVec3dMin.xCoord
					: boundVec3dMax.xCoord;
			double maxX = boundVec3dMin.xCoord < boundVec3dMax.xCoord ? boundVec3dMax.xCoord
					: boundVec3dMin.xCoord;

			double minY = boundVec3dMin.yCoord < boundVec3dMax.yCoord ? boundVec3dMin.yCoord
					: boundVec3dMax.yCoord;
			double maxY = boundVec3dMin.yCoord < boundVec3dMax.yCoord ? boundVec3dMax.yCoord
					: boundVec3dMin.yCoord;

			double minZ = boundVec3dMin.zCoord < boundVec3dMax.zCoord ? boundVec3dMin.zCoord
					: boundVec3dMax.zCoord;
			double maxZ = boundVec3dMin.zCoord < boundVec3dMax.zCoord ? boundVec3dMax.zCoord
					: boundVec3dMin.zCoord;

			boundingBox.setBounds(posX + minX, posY + minY, posZ + minZ, posX
					+ maxX, posY + maxY, posZ + maxZ);
		}
	}

	protected void setDeadMEM() {
		coreEntity.removeBlock(innerWorldX, innerWorldY, innerWorldZ);
	}

	protected void setInnerEntityPosition(double prevX, double prevY,
			double prevZ, double newPosX, double newPosY, double newPosZ) {
		if (coreEntity == null) {
			return;
		}
		for (Entity entity : coreEntity
				.getRidingEntity(EntityMEMBlockWrapper.this)) {
			if (!(entity instanceof EntityMEMBlockWrapper)
					&& !(entity instanceof EntityCoreSugar)) {
				double x = newPosX - prevX;
				double y = newPosY - prevY;
				double z = newPosZ - prevZ;
				Minecraft mc = ModLoader.getMinecraftInstance();
				if (mc.renderViewEntity == entity) {
					coreEntity.registInnerViewEntityLastTick(x, y, z);
				}

				entity.setPosition(entity.posX + x, entity.posY + y,
						entity.posZ + z);

				// if (entity.riddenByEntity != null) {
				// if (!(entity.riddenByEntity instanceof EntityPlayer)) {
				// entity.riddenByEntity.lastTickPosX += x;
				// entity.riddenByEntity.lastTickPosY += y;
				// entity.riddenByEntity.lastTickPosZ += z;
				// entity.updateRiderPosition();
				// }
				// }
				entity.prevPosX += x;
				entity.prevPosY += y;
				entity.prevPosZ += z;
				entity.lastTickPosX += x;
				entity.lastTickPosY += y;
				entity.lastTickPosZ += z;
			}
		}
	}

	protected void setSleepingPlayerPosition(EntityPlayer player) {
		if (getInnerWorld().blockExists(innerWorldX, innerWorldY, innerWorldZ)) {
			int i = worldObj.getBlockMetadata(innerWorldX, innerWorldY,
					innerWorldZ);
			int j = BlockBed.getDirection(i);
			float f = 0.5F;
			float f1 = 0.5F;

			switch (j) {
			case 0:
				f1 = 0.9F;
				break;

			case 2:
				f1 = 0.1F;
				break;

			case 1:
				f = 0.1F;
				break;

			case 3:
				f = 0.9F;
				break;
			}
			Vec3D v3d = Vec3D.createVectorHelper(f, 0, f1);
			v3d.rotateAroundY((-rotationYaw * (float) Math.PI) / 180F);
			f1 = (float) v3d.xCoord;
			f = (float) v3d.zCoord;

			player.setPosition((float) posX + f, (float) posY + 0.9375F,
					(float) posZ + f1);
		} else {
			player.setPosition((float) posX + 0.5F, (float) posY + 0.9375F,
					(float) posZ + 0.5F);
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
	}

	/**
	 * Get if the Entity is sprinting.
	 */
	public boolean isSprinting() {
		return false;
	}

	@Override
	public boolean isInWater() {
		return false;
	}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

}
