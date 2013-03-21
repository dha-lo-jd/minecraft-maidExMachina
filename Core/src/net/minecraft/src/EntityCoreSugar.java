package net.minecraft.src;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.src.obb.IntersectRect;
import net.minecraft.src.obb.OBBCube;

public class EntityCoreSugar extends Entity {

	protected static class MEMCollisionBox {
		AxisAlignedBB aabb;
		Vec3D offset;

		protected MEMCollisionBox(AxisAlignedBB aabb, Vec3D offset) {
			this.aabb = aabb;
			this.offset = offset;
		}

		protected Vec3D rotatedOffset(float yaw) {
			Vec3D v3d = CoordsSupport.copyVectorHelper(offset);
			v3d.rotateAroundY(yaw);
			return v3d;
		}

		protected Vec3D rotatedPosFromEntity(Entity e) {
			Vec3D v3d = rotatedOffset(RotateSupport.toRadF(-e.rotationYaw));
			Vec3D pos = Vec3D.createVectorHelper(e.posX + v3d.xCoord, e.posY
					+ v3d.yCoord, e.posZ + v3d.zCoord);

			return pos;
		}

		protected IntersectRect rotatedIntersectFromEntity(Entity e) {
			Vec3D v3d = rotatedPosFromEntity(e);

			return new IntersectRect(aabb, v3d.xCoord, v3d.zCoord);
		}
	}

	public interface UpdateJob {
		public void update();
	}

	public static final int myWorldMaxX = 15;
	public static final int myWorldMaxY = 256;
	public static final int myWorldMaxZ = 15;

	public static final int myWorldMyPosX = 7;
	public static final int myWorldMyPosY = 128;
	public static final int myWorldMyPosZ = 7;

	protected Set<MEMCollisionBox> collisionBoxSet = new HashSet<MEMCollisionBox>();

	public boolean isRenderTransparent;
	public float prevRotationZ;

	public float rotationZ;

	private boolean disableInteract = false;
	private int rot = 0;

	private AxisAlignedBB rotatedBoundingBox = getDefaultBounds();
	private MEMAIStateMap stateMap = new MEMAIStateMap();

	private String suffix;

	protected Set<UpdateJob> afterUpdateJobs = new HashSet<UpdateJob>();

	protected IReversibleMap<EntityMEMBlockWrapper, Entity> blockRidingEntityMap = new ReversibleMap<EntityMEMBlockWrapper, Entity>();

	protected Set<Entity> innerEntity = new HashSet<Entity>();

	protected Set<Entity> innerEntityInSpace = new HashSet<Entity>();
	protected Vec3D innerViewEntityLastTick = null;

	protected WorldOneChunk myWorld;

	protected final EntityMEMBlockWrapper[][][] myWorldBlockEntities = new EntityMEMBlockWrapper[myWorldMaxX][myWorldMaxY][myWorldMaxZ];

	protected Set<UpdateJob> preUpdateJobs = new HashSet<UpdateJob>();

	protected Set<EntityMEMBlockWrapper> renderChild = new HashSet<EntityMEMBlockWrapper>();

	protected boolean renderModify = true;

	protected Set<UpdateJob> updateJobs = new HashSet<UpdateJob>();

	public EntityCoreSugar(World par1World, String suffix) {
		super(par1World);
		setSize(0, 0);
		ignoreFrustumCheck = true;

		this.suffix = suffix;

		myWorld = new WorldOneChunk(par1World, getWorldName() + suffix,
				new WorldSettings(0, 1, false, false, WorldType.DEFAULT), this);
	}

	public boolean addBlock(int blockId, int x, int y, int z) {
		myWorld.setForceInner(true);
		if (!isAddBlock(x, y, z)) {
			return false;
		}

		myWorld.setBlockWithNotify(x, y, z, blockId);
		myWorld.setForceInner(false);
		return true;
	}

	public void addCollisionBlockPos(EntityMEMBlockWrapper entity, int j4,
			int k4, int l4) {
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		return false;
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	@Override
	public boolean canBePushed() {
		return false;
	}

	public void destroyAll() {
		setDead();
		for (int x = 0; x < myWorldMaxX; x++) {
			for (int z = 0; z < myWorldMaxZ; z++) {
				for (int y = 0; y < myWorldMaxY; y++) {
					EntityMEMBlockWrapper memBlockWrapper = myWorldBlockEntities[x][y][z];
					if (memBlockWrapper != null && !memBlockWrapper.isDead) {
						memBlockWrapper.setDead();
					}
				}
			}
		}
		Minecraft mc = ModLoader.getMinecraftInstance();
		ISaveFormat isaveformat = mc.getSaveLoader();
		isaveformat.flushCache();
		isaveformat.deleteWorldDirectory(worldObj.getSaveHandler()
				.getSaveDirectoryName()
				+ "/"
				+ myWorld.getSaveHandler().getSaveDirectoryName());
		;
	}

	public AxisAlignedBB getDefaultBounds() {
		return AxisAlignedBB.getBoundingBox(0, 0, 0, myWorldMaxX, myWorldMaxY,
				myWorldMaxZ);
	}

	public Vec3D getInnerEntityInnerPositon(Entity entity) {
		double x = entity.posX - posX;
		double y = entity.posY - posY;
		double z = entity.posZ - posZ;

		Vec3D v3d = Vec3D.createVectorHelper(x, y, z);

		float r = ((entity.rotationYaw) * (float) Math.PI) / 180F;

		v3d.rotateAroundY(r);

		v3d.xCoord = v3d.xCoord + myWorldMyPosX + 0.5 - 0.5;
		v3d.yCoord = v3d.yCoord + myWorldMyPosY - 0.5;
		v3d.zCoord = v3d.zCoord + myWorldMyPosZ + 0.5 - 0.5;

		return v3d;
	}

	public WorldOneChunk getMyWorld() {
		return myWorld;
	}

	public EntityMEMBlockWrapper getMyWorldBlockWrapper(int x, int y, int z) {
		if (x < 0 || x > myWorldMaxX - 1 || y < 0 || y > myWorldMaxY - 1
				|| z < 0 || z > myWorldMaxZ - 1) {
			return null;
		}
		return myWorldBlockEntities[x][y][z];
	}

	public AxisAlignedBB getRenderFrameBox() {
		return AxisAlignedBB.getBoundingBox(0, 0, 0, myWorldMaxX, myWorldMaxY,
				myWorldMaxZ);
	}

	public Set<Entity> getRidingEntity(EntityMEMBlockWrapper block) {
		Set<Entity> result = new HashSet<Entity>();
		Set<Entity> set = blockRidingEntityMap.getA(block);
		if (set == null) {
			return result;
		}
		for (Entity e : set) {
			if (getRegistedInnerEntity(e) == this) {
				result.add(e);
			}
		}
		return result;
	}

	public AxisAlignedBB getRotatedPositionBounds() {
		return updateRotatedBounds(true).getOffsetBoundingBox(posX, posY, posZ);
	}

	public MEMAIStateMap getStateMap() {
		return stateMap;
	}

	public World getWorld() {
		return worldObj;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (isDisableInteract()) {
			return false;
		}
		ItemStack itemstack = par1EntityPlayer.getCurrentEquippedItem();
		if (itemstack == null) {
			rot++;
			if (rot > 2) {
				rot = 0;
			}
			return false;
		}

		if (itemstack.itemID > 255) {
			return false;
		}

		ChunkCoordinates cp = EntityCoordsSupport.getEntityBlockSide(this,
				par1EntityPlayer);

		int wX = myWorldMyPosX + cp.posX;
		int wY = myWorldMyPosY + cp.posY;
		int wZ = myWorldMyPosZ + cp.posZ;

		return addBlock(itemstack.itemID, wX, wY, wZ);
	}

	public boolean isAddBlock(int x, int y, int z) {
		return 0 <= x && x < myWorldMaxX && 0 <= y && y < myWorldMaxY && 0 <= z
				&& z < myWorldMaxZ && myWorldBlockEntities[x][y][z] == null;
	}

	public boolean isDisableInteract() {
		return disableInteract;
	}

	public boolean isEntityInCore(Entity entity) {
		return false;
	}

	@Override
	public boolean isInRangeToRenderDist(double par1) {
		return par1 < 6400;
	}

	public void notifyBlockChanged(int x, int y, int z) {
		Entity blockWrapper = myWorldBlockEntities[x][y][z];
		int blockId = myWorld.getBlockIdInner(x, y, z);
		if (blockWrapper == null || blockWrapper.isDead) {
			if (blockId > 0) {
				spwanMEMBlockWrapper(x, y, z, blockId);
			}

		} else {
			blockWrapper.setDead();
			myWorldBlockEntities[x][y][z] = null;
			if (blockId > 0) {
				spwanMEMBlockWrapper(x, y, z, blockId);
			}
		}
		renderModify = true;
	}

	@Override
	public void onUpdate() {
		prevRotationZ = rotationZ;
		super.onUpdate();

		renderModify = checkRenderModified();
		isRenderTransparent = checkRenderTransparent();

		resetStateUpdated();
		updateInnerEntityRegist();

		setCollisionEntityPosition();

		lazyUpdate();
	}

	protected void setCollisionEntityPosition() {
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this,
				getRotatedPositionBounds().expand(8, 8, 8));

		Minecraft mc = ModLoader.getMinecraftInstance();
		for (Object o : list) {
			Entity entity = (Entity) o;
			EntityMEMBlockWrapper nearE = null;
			boolean slided = false;
			for (MEMCollisionBox cBox : collisionBoxSet) {
				if (!(entity instanceof EntityMEMBlockWrapper)
						&& !(entity instanceof EntityCoreSugar)
						&& !(entity instanceof EntityMEMDeviceZabuton)) {

					float radYaw = RotateSupport.toRadF(-rotationYaw);
					float radPitch = RotateSupport.toRadF(rotationPitch);
					OBBCube obbA = OBBCube.createOBBCube(cBox.aabb,
							cBox.rotatedPosFromEntity(this), radYaw, radPitch);
					Vec3D pos = OBBCube.getPosFromAABB(entity.boundingBox,
							OBBCube.getRadiusFromAABB(entity.boundingBox));
					if (obbA != null && obbA.isCollisionCircle(1, pos)) {
						IntersectRect rect = cBox
								.rotatedIntersectFromEntity(this);
						IntersectRect bounceRect = cBox
								.rotatedIntersectFromEntity(this);
						rect.slideToEntity(entity, 0.125);
						if (!slided) {
							Vec3D p = rect.getSlidePoint(entity, radYaw);
							if (p != null) {
								slided = true;
								entity.setPosition(entity.prevPosX + p.xCoord,
										entity.posY, entity.prevPosZ + p.zCoord);
								entity.motionX = p.xCoord;
								entity.motionZ = p.zCoord;
							}
						} else {
							Vec3D p = rect.getCollisionPoint(entity, radYaw);
							if (p != null) {
								entity.setPosition(entity.prevPosX + p.xCoord,
										entity.posY, entity.prevPosZ + p.zCoord);
								entity.motionX = 0;
								entity.motionZ = 0;
							}
						}
						Vec3D bounce = bounceRect.bounceInField(
								EntityCoordsSupport.getPosition(entity), 0.25,
								0.25, radYaw);
						entity.setPosition(entity.posX + bounce.xCoord,
								entity.posY, entity.posZ + bounce.zCoord);
						entity.motionX = entity.motionX + bounce.xCoord;
						entity.motionZ = entity.motionZ + bounce.zCoord;
					}
				}
			}
			if (slided) {
				// entity.lastTickPosX = entity.prevPosX = entity.posX;
				// entity.lastTickPosY = entity.prevPosY = entity.posY;
				// entity.lastTickPosZ = entity.prevPosZ = entity.posZ;
				entity.lastTickPosX = entity.posX;
				entity.lastTickPosY = entity.posY;
				entity.lastTickPosZ = entity.posZ;
			}
			// if (nearE != null) {
			// IntersectRect rect = new IntersectRect(nearE.boundingBox);
			// // rect.slideToEntity(entity, 0.125);
			// Vec3D p = rect.getSlidePoint(entity,
			// RotateSupport.toRadF(nearE.rotationYaw));
			// if (p != null) {
			// entity.setPosition(entity.prevPosX + p.xCoord, entity.posY,
			// entity.prevPosZ + p.zCoord);
			// }
			// }
		}
	}

	double getLength(Entity e1, Entity e2) {
		double rX = e1.prevPosX - e2.posX;
		double rY = e1.prevPosZ - e2.posZ;
		return rX * rX + rY * rY;
	}

	public boolean removeBlock(int x, int y, int z) {
		int b = myWorld.getBlockIdInner(x, y, z);
		if (b <= 0) {
			return false;
		}
		myWorld.setForceInner(true);
		int m = myWorld.getBlockMetadata(x, y, z);

		Block.blocksList[b].onBlockDestroyedByPlayer(myWorld, x, y, z, m);
		Minecraft mc = ModLoader.getMinecraftInstance();
		Block.blocksList[b].harvestBlock(myWorld, mc.thePlayer, x, y, z, m);
		myWorld.setBlockWithNotify(x, y, z, 0);
		myWorld.setForceInner(false);
		return true;
	}

	public void setDisableInteract(boolean disableInteract) {
		this.disableInteract = disableInteract;
	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		setInnerEntityPosition(posX, posY, posZ, par1, par3, par5);
		super.setPosition(par1, par3, par5);
	}

	public boolean shouldRenderMEMBlock(EntityMEMBlockWrapper memBlockWrapper) {
		return true;
	}

	public AxisAlignedBB updateRotatedBounds(boolean useCache) {
		if (useCache && !isCoreRotated()) {
			return rotatedBoundingBox;
		}
		AxisAlignedBB bounds = getDefaultBounds();
		Vec3D boundVec3dMin;
		Vec3D boundVec3dMax;
		boundVec3dMin = EntityCoordsSupport.rotateFixedBoxDirection(this,
				bounds.minX - myWorldMyPosX - 0.5, bounds.minY - myWorldMyPosY
						- 0.5, bounds.minZ - myWorldMyPosZ - 0.5);
		boundVec3dMax = EntityCoordsSupport.rotateFixedBoxDirection(this,
				bounds.maxX - myWorldMyPosX - 0.5, bounds.maxY - myWorldMyPosY
						- 0.5, bounds.maxZ - myWorldMyPosZ - 0.5);
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
		return rotatedBoundingBox = AxisAlignedBB.getBoundingBox(minX, minY,
				minZ, maxX, maxY, maxZ);
	}

	public boolean updateState(IMEMAIState state) {
		return getStateMap().setState(state);
	}

	public boolean updateState(MEMAIStateUpdater stateUpdater) {
		return updateState(stateUpdater.updateState());
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	protected boolean checkRenderModified() {

		if (getStateMap().isUpdated(MEMAICameraState.TYPE)) {
			return true;
		}
		if (getStateMap().isUpdated(MEMAIRidingState.TYPE)) {
			return true;
		}
		if (getStateMap().isUpdated(MEMAIViewerPositionState.TYPE)) {
			return true;
		}

		return renderModify;
	}

	protected boolean checkRenderTransparent() {

		if (getStateMap().isStateAs(MEMAIRidingState.PLAYER)
				&& getStateMap().isStateAs(MEMAICameraState.PLAYER)) {
			return true;
		}

		return false;
	}

	@Override
	protected void entityInit() {
	}

	protected ChunkCoordinates getInnerEntityRidingChunkCoords(Entity entity) {
		double x = entity.posX - posX;
		double y = entity.boundingBox.minY - posY;
		double z = entity.posZ - posZ;

		Vec3D v3d = Vec3D.createVectorHelper(x, y - 0.5, z);

		float r = ((entity.rotationYaw) * (float) Math.PI) / 180F;

		v3d.rotateAroundY(r);

		v3d.xCoord = v3d.xCoord + myWorldMyPosX + 0.5 - 0.5;
		v3d.yCoord = v3d.yCoord + myWorldMyPosY;
		v3d.zCoord = v3d.zCoord + myWorldMyPosZ + 0.5 - 0.5;

		return CoordsSupport.toChunkCoordinates(v3d);
	}

	protected EntityMEMBlockWrapper getMyWorldBlockWrapper(ChunkCoordinates cp) {
		return getMyWorldBlockWrapper(cp.posX, cp.posY, cp.posZ);
	}

	protected String getPartsName() {
		return "Body";
	}

	protected EntityCoreSugar getRegistedInnerEntity(Entity entity) {
		if (innerEntity.contains(entity)) {
			return this;
		}
		return null;
	}

	protected EntityCoreSugar getRegistedInnerEntityFromParent(Entity entity) {
		if (innerEntity.contains(entity)) {
			return this;
		}
		return null;
	}

	protected String getWorldName() {
		return "TestOC" + getPartsName();
	}

	protected boolean isCoreRotated() {
		return rotationPitch != prevRotationPitch || rotationZ != prevRotationZ
				|| rotationYaw != prevRotationYaw;
	}

	protected boolean isOnGround() {
		return EntityCoordsSupport.getHeightFromGround(this) <= 8;
	}

	protected void lazyUpdate() {
		innerViewEntityLastTick = null;
		for (UpdateJob job : preUpdateJobs) {
			job.update();
		}
		for (UpdateJob job : updateJobs) {
			job.update();
		}
		for (UpdateJob job : afterUpdateJobs) {
			job.update();
		}
		blockRidingEntityMap = new ReversibleMap<EntityMEMBlockWrapper, Entity>();
		innerEntity = new HashSet<Entity>();
		innerEntityInSpace = new HashSet<Entity>();
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		restoreWorldBlocks();
	}

	protected void registAfterUpdateJob(UpdateJob updateJob) {
		afterUpdateJobs.add(updateJob);
	}

	protected void registBlockRidingEntity(EntityMEMBlockWrapper block,
			Entity entity) {
		if (getRegistedInnerEntity(entity) == null) {
			if (!blockRidingEntityMap.containB(entity)) {
				registInnerEntity(entity);
				blockRidingEntityMap.put(block, entity);
				if (entity.riddenByEntity != null) {
					registInnerEntity(entity.riddenByEntity);
					blockRidingEntityMap.put(block, entity.riddenByEntity);
				}
			}
		}
	}

	protected void registInnerEntity(Entity entity) {
		EntityCoreSugar p = getRegistedInnerEntityFromParent(entity);
		if (p == this) {
			return;
		}
		if (p != null) {
			p.innerEntity.remove(entity);
		}
		innerEntity.add(entity);
	}

	protected void registInnerEntityInSpace(Entity entity) {
		if (getRegistedInnerEntity(entity) == null) {
			if (!innerEntityInSpace.contains(entity)) {
				registInnerEntity(entity);
				innerEntityInSpace.add(entity);
				if (entity.riddenByEntity != null) {
					registInnerEntity(entity.riddenByEntity);
					innerEntityInSpace.add(entity.riddenByEntity);
				}
			}
		}
	}

	protected void registInnerViewEntityLastTick(double x, double y, double z) {
		innerViewEntityLastTick = Vec3D.createVectorHelper(x, y, z);
	}

	protected void registPreUpdateJob(UpdateJob updateJob) {
		preUpdateJobs.add(updateJob);
	}

	protected void registRenderChild(EntityMEMBlockWrapper entity) {
		renderChild.add(entity);
	}

	protected void registUpdateJob(UpdateJob updateJob) {
		updateJobs.add(updateJob);
	}

	protected void resetStateUpdated() {
		getStateMap().resetUpdated();
	}

	protected void restoreWorldBlocks() {
		for (int x = 0; x < myWorldMaxX; x++) {
			for (int z = 0; z < myWorldMaxZ; z++) {
				for (int y = 0; y < myWorldMaxY; y++) {
					int blockId = myWorld.getBlockIdInner(x, y, z);
					restoreWorldBlocks(x, y, z, blockId);
				}
			}
		}
	}

	protected void restoreWorldBlocks(int x, int y, int z, int blockId) {
		if (blockId > 0) {
			spwanMEMBlockWrapper(x, y, z, blockId);
		}
	}

	protected void setInnerEntityPosition(double prevX, double prevY,
			double prevZ, double newPosX, double newPosY, double newPosZ) {
		if (innerEntityInSpace == null) {
			return;
		}
		for (Entity entity : innerEntityInSpace) {
			if (!(entity instanceof EntityMEMBlockWrapper)
					&& !(entity instanceof EntityCoreSugar)) {
				double x = newPosX - prevX;
				double y = newPosY - prevY;
				double z = newPosZ - prevZ;

				Minecraft mc = ModLoader.getMinecraftInstance();
				if (mc.renderViewEntity == entity) {
					registInnerViewEntityLastTick(x, y, z);
				}
				entity.setPosition(entity.posX + x, entity.posY + y,
						entity.posZ + z);

				// entity.prevPosX = entity.posX;
				// entity.prevPosY = entity.posY;
				// entity.prevPosZ = entity.posZ;
				// entity.lastTickPosX =entity. posX;
				// entity.lastTickPosY =entity. posY;
				// entity.lastTickPosZ = entity.posZ;

				// entity.prevPosX += x;
				// entity.prevPosY += y;
				// entity.prevPosZ += z;
				// entity.lastTickPosX += x;
				// entity.lastTickPosY += y;
				// entity.lastTickPosZ += z;
			}
		}
	}

	protected void spwanMEMBlockWrapper(int x, int y, int z, int blockId) {
		EntityMEMBlockWrapper blockWrapper;
		TileEntity tileEntity = myWorld.getBlockTileEntityInner(x, y, z);
		blockWrapper = new EntityMEMBlockWrapper(worldObj, blockId, x, y, z,
				this, tileEntity);

		Vec3D v3d = Vec3D.createVectorHelper(x - myWorldMyPosX, y
				- myWorldMyPosY, z - myWorldMyPosZ);
		v3d.rotateAroundX((rotationPitch * (float) Math.PI) / 180F);
		v3d.rotateAroundY((rotationYaw * (float) Math.PI) / 180F);

		blockWrapper.setPosition(posX + v3d.xCoord, posY + v3d.yCoord, posZ
				+ v3d.zCoord);

		worldObj.spawnEntityInWorld(blockWrapper);

		myWorldBlockEntities[x][y][z] = blockWrapper;
	}

	protected void updateInnerEntityRegist() {
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this,
				getRotatedPositionBounds().expand(0, 0.5, 0));

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Entity entity = (Entity) list.get(i);
				if (!(entity instanceof EntityMEMBlockWrapper)
						&& !(entity instanceof EntityCoreSugar)
						&& entity.ridingEntity == null) {
					ChunkCoordinates innerEntityChunkCoords = getInnerEntityRidingChunkCoords(entity);
					EntityMEMBlockWrapper memBlockWrapper = getMyWorldBlockWrapper(innerEntityChunkCoords);
					EntityMEMBlockWrapper lapped = getMyWorldBlockWrapper(
							innerEntityChunkCoords.posX,
							innerEntityChunkCoords.posY + 1,
							innerEntityChunkCoords.posZ);
					if (memBlockWrapper != null && lapped == null) {
						registBlockRidingEntity(memBlockWrapper, entity);
					} else {
						registInnerEntityInSpace(entity);
					}
				}
			}
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		myWorld.saveWorldIndirectly(null);
		nbttagcompound.setString("suffix", suffix);
	}

}
