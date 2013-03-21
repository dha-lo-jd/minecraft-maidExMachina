package net.minecraft.src;

import java.util.HashSet;
import java.util.Set;

public class EntityCoreSugarParts extends EntityCoreSugar {

	protected Set<ChunkCoordinates> framePos = new HashSet<ChunkCoordinates>();
	protected boolean inBuildFrame = false;

	protected int maxX, maxY, maxZ;

	protected int minX, minY, minZ;
	protected Set<EntityCoreSugarChildParts> parts = new HashSet<EntityCoreSugarChildParts>();

	protected Set<UpdateJob> partsUpdateJobs = new HashSet<UpdateJob>();

	boolean blockLoded = false;

	boolean partsLoded = false;

	boolean preLoaded = false;

	public EntityCoreSugarParts(World par1World, ChunkCoordinates size,
			String suffix) {
		super(par1World, suffix);

		int minX = myWorldMyPosX + 1 - (int) ((double) size.posX / 2 + 0.5);
		int maxX = myWorldMyPosX + size.posX / 2;

		int minY = myWorldMyPosY + 1 - (int) ((double) size.posY / 2 + 0.5);
		int maxY = myWorldMyPosY + size.posY / 2;

		int minZ = myWorldMyPosZ + 1 - (int) ((double) size.posZ / 2 + 0.5);
		int maxZ = myWorldMyPosZ + size.posZ / 2;

		minX = minX < 0 ? 0 : minX;
		minY = minY < 0 ? 0 : minY;
		minZ = minZ < 0 ? 0 : minZ;

		maxX = maxX > myWorldMaxX ? myWorldMaxX : maxX;
		maxY = maxY > myWorldMaxY ? myWorldMaxY : maxY;
		maxZ = maxZ > myWorldMaxZ ? myWorldMaxZ : maxZ;

		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;

		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;

		updateRotatedBounds(false);
	}

	public EntityCoreSugarParts(World par1World, String suffix) {
		super(par1World, suffix);
	}

	@Override
	protected String getWorldName() {
		return "TestOC" + getPartsName();
	}

	public boolean addChildParts(EntityCoreSugarChildParts e) {
		return parts.add(e);
	}

	public void buildFrame(IMEMPlanBlockInfo[][][] plan, boolean reverse) {
		inBuildFrame = true;
		for (int i = 0; i < plan.length; i++) {
			IMEMPlanBlockInfo[][] js = plan[i];
			for (int j = 0; j < js.length; j++) {
				IMEMPlanBlockInfo[] is = js[j];
				for (int k = 0; k < is.length; k++) {
					IMEMPlanBlockInfo info = is[k];
					if (reverse) {
						info = is[is.length - k - 1];
					}
					if (info != MEMPlanDefault._VD_) {
						myWorldBlockEntities[k + minX][maxY - i][j + minZ] = null;
						addBlock(Block.obsidian.blockID, k + minX, maxY - i, j
								+ minZ);
						setFrameColor(info, k + minX, maxY - i, j + minZ);
					}
				}
			}
		}
		updateAllFrameRenderFaces();
		inBuildFrame = false;
	}

	public void buildFrame(IMEMPlanBlockInfo[][][] plan) {
		buildFrame(plan, false);
	}

	@Override
	public void destroyAll() {
		super.destroyAll();
		for (EntityCoreSugarChildParts childParts : parts) {
			if (!childParts.isDead) {
				childParts.destroyAll();
			}
		}
	}

	/**
	 * Changes pitch and yaw so that the entity calling the function is facing
	 * the entity provided as an argument.
	 */
	public void faceEntity(Entity par1Entity, float par2, float par3) {
		float yaw = getFaceToYaw(par1Entity);
		getFaceToPitch(par1Entity);
		rotationYaw = updateRotation(rotationYaw, yaw, par2);
	}

	public EntityCoreSugarParts getCore() {
		return this;
	}

	@Override
	public AxisAlignedBB getDefaultBounds() {
		return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	}

	public IMEMDevice getDevice() {
		return null;
	}

	public MEMAI getMemai() {
		return null;
	}

	@Override
	public AxisAlignedBB getRenderFrameBox() {
		return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public MEMAIStateMap getStateMap() {
		if (isCore()) {
			return super.getStateMap();
		}
		return getCore().getStateMap();
	}

	@Override
	public boolean isAddBlock(int x, int y, int z) {
		if (x < minX || maxX < x || y < minY || maxY < y || z < minZ
				|| maxZ < z) {
			return false;
		}
		return super.isAddBlock(x, y, z);
	}

	public boolean isCore() {
		return getCore() == this;
	}

	@Override
	public boolean isEntityInCore(Entity entity) {
		return !(entity.posX < posX - myWorldMyPosX + minX
				|| posX - myWorldMyPosX + maxX < entity.posX
				|| entity.posY < posY - myWorldMyPosY + minY
				|| posY - myWorldMyPosY + maxY < entity.posY
				|| entity.posZ < posZ - myWorldMyPosZ - 1 + minZ || posZ
				- myWorldMyPosZ + 1 + maxZ < entity.posZ);
	}

	@Override
	public void onUpdate() {
		if (!blockLoded) {
			blockLoded = true;
			for (EntityMEMBlockWrapper block : getBlocks()) {
				if (!block.addedToChunk) {
					blockLoded = false;
					worldObj.spawnEntityInWorld(block);
				}
			}
		}
		if (!partsLoded) {
			partsLoded = true;
			for (EntityCoreSugarChildParts childParts : parts) {
				if (!childParts.preLoaded) {
					partsLoded = false;
					// worldObj.spawnEntityInWorld(childParts);
				}
			}
		}
		if (!preLoaded && blockLoded && partsLoded) {
			preLoaded();
			preLoaded = true;
			renderModify = true;
		}
		lazyUpdateParts();
		super.onUpdate();

	}

	public void setFrameColor(IMEMPlanBlockInfo info, int x, int y, int z) {
		Entity entity = myWorldBlockEntities[x][y][z];
		if (entity != null && entity instanceof EntityMEMFrame) {
			EntityMEMFrame frame = (EntityMEMFrame) entity;
			info.setInfo(frame);
		}
	}

	public EntityMEMFrame spawnMEMFrame(int blockId, int x, int y, int z) {
		TileEntity tileEntity = myWorld.getBlockTileEntityInner(x, y, z);
		EntityMEMFrame blockWrapper = createMEMFrameInstance(blockId, x, y, z,
				tileEntity);

		Vec3D v3d = Vec3D.createVectorHelper(x - myWorldMyPosX, y
				- myWorldMyPosY, z - myWorldMyPosZ);
		v3d.rotateAroundX((rotationPitch * (float) Math.PI) / 180F);
		v3d.rotateAroundY((rotationYaw * (float) Math.PI) / 180F);

		blockWrapper.setPosition(posX + v3d.xCoord, posY + v3d.yCoord, posZ
				+ v3d.zCoord);

		worldObj.spawnEntityInWorld(blockWrapper);

		myWorldBlockEntities[x][y][z] = blockWrapper;

		return blockWrapper;
	}

	protected EntityMEMFrame createMEMFrameInstance(int blockId, int x, int y,
			int z, TileEntity tileEntity) {
		EntityMEMFrame blockWrapper = new EntityMEMFrame(worldObj, blockId, x,
				y, z, this, tileEntity);
		return blockWrapper;
	}

	protected void doLazyUpdateParts() {
		for (EntityCoreSugarChildParts p : parts) {
			p.onLazyUpdate();
		}
	}

	protected Set<EntityMEMBlockWrapper> getBlocks() {
		Set<EntityMEMBlockWrapper> set = new HashSet<EntityMEMBlockWrapper>();
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				for (int k = minZ; k <= maxZ; k++) {
					EntityMEMBlockWrapper b = myWorldBlockEntities[i][j][k];
					if (b != null) {
						set.add(b);
					}
				}
			}
		}
		return set;
	}

	protected void getFaceToPitch(Entity par1Entity) {
		double d = par1Entity.posX - posX;
		double d2 = par1Entity.posZ - posZ;
		double d1;

		if (par1Entity instanceof EntityLiving) {
			EntityLiving entityliving = (EntityLiving) par1Entity;
			d1 = (posY + (double) getEyeHeight())
					- (entityliving.posY + (double) entityliving.getEyeHeight());
		} else {
			d1 = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY)
					/ 2D - (posY + (double) getEyeHeight());
		}

		double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
		float f1 = (float) (-((Math.atan2(d1, d3) * 180D) / Math.PI));
		float pitch = f1;

		while (pitch > 360) {
			pitch -= 360;
		}
		while (pitch < 0) {
			pitch += 360;
		}
	}

	protected float getFaceToYaw(Entity par1Entity) {
		double d = par1Entity.posX - posX;
		double d2 = par1Entity.posZ - posZ;
		float f = (float) ((Math.atan2(d2, d) * 180D) / Math.PI) + 90F;
		float yaw = f;
		while (yaw > 180) {
			yaw -= 360;
		}
		while (yaw < -180) {
			yaw += 360;
		}
		return yaw;
	}

	protected Set<EntityMEMBlockWrapper> getFrames() {
		Set<EntityMEMBlockWrapper> set = new HashSet<EntityMEMBlockWrapper>();
		for (ChunkCoordinates pos : framePos) {
			EntityMEMBlockWrapper e = myWorldBlockEntities[pos.posX][pos.posY][pos.posZ];
			set.add(e);
		}

		return set;
	}

	@Override
	protected EntityCoreSugar getRegistedInnerEntity(Entity entity) {
		for (EntityCoreSugarChildParts p : parts) {
			EntityCoreSugar childParts = p.getRegistedInnerEntity(entity);
			if (childParts != null) {
				return childParts;
			}
		}
		return super.getRegistedInnerEntity(entity);
	}

	@Override
	protected boolean isOnGround() {
		return EntityCoordsSupport.getHeightFromGround(this) <= myWorldMyPosY
				- minY;
	}

	protected void lazyUpdateParts() {
		doLazyUpdateParts();
	}

	protected void preLoaded() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		minX = nbttagcompound.getInteger("minX");
		minY = nbttagcompound.getInteger("minY");
		minZ = nbttagcompound.getInteger("minZ");
		maxX = nbttagcompound.getInteger("maxX");
		maxY = nbttagcompound.getInteger("maxY");
		maxZ = nbttagcompound.getInteger("maxZ");
		framePos = readTagFramePos((NBTTagCompound) nbttagcompound
				.getTag("framePos"));

		readTagFrames((NBTTagCompound) nbttagcompound.getTag("frames"));

		readTagParts((NBTTagCompound) nbttagcompound.getTag("parts"));

		super.readEntityFromNBT(nbttagcompound);

		updateRotatedBounds(false);

		updateAllFrameRenderFaces();
	}

	protected Set<ChunkCoordinates> readTagFramePos(
			NBTTagCompound nbttagcompound) {
		return TagCompoundSupport.readSet(nbttagcompound,
				new TagCompoundSupport.TagReader<ChunkCoordinates>() {
					@Override
					public ChunkCoordinates read(String name,
							NBTTagCompound nbttagcompound) {
						return TagCompoundSupport
								.readChunkCoordinates((NBTTagCompound) nbttagcompound
										.getTag(name));
					}
				});
	}

	protected void readTagFrames(NBTTagCompound nbttagcompound) {
		TagCompoundSupport.readSet(nbttagcompound,
				new TagCompoundSupport.TagReader<Void>() {
					@Override
					public Void read(String name, NBTTagCompound nbttagcompound) {
						NBTTagCompound tag = (NBTTagCompound) nbttagcompound
								.getTag(name);
						ChunkCoordinates pos = TagCompoundSupport
								.readChunkCoordinates((NBTTagCompound) tag
										.getTag("pos"));
						restoreFrame(pos, (NBTTagCompound) tag.getTag("entity"));
						return null;
					}
				});
	}

	protected Set<EntityCoreSugarChildParts> readTagParts(
			NBTTagCompound nbttagcompound) {
		return TagCompoundSupport.readSet(nbttagcompound,
				new TagCompoundSupport.TagReader<EntityCoreSugarChildParts>() {
					@Override
					public EntityCoreSugarChildParts read(String name,
							NBTTagCompound nbttagcompound) {
						NBTTagCompound tag = (NBTTagCompound) nbttagcompound
								.getTag(name);
						String partsName = tag.getString("name");
						System.out.print(name);
						System.out.print(": ");
						System.out.println(partsName);
						EntityCoreSugarChildParts childParts = mod_MaidExMachina.MEM_BUILDER
								.createChildParts(partsName,
										EntityCoreSugarParts.this, getCore(),
										worldObj,
										(NBTTagCompound) tag.getTag("entity"));
						return childParts;
					}
				});
	}

	@Override
	protected void registInnerEntity(Entity entity) {
		EntityCoreSugar childParts = null;
		for (EntityCoreSugarChildParts p : parts) {
			childParts = p.getRegistedInnerEntity(entity);
			if (childParts != null) {
				break;
			}
		}
		if (childParts == null) {
			super.registInnerEntity(entity);
		}
	}

	protected void registPartsUpdateJob(UpdateJob updateJob) {
		partsUpdateJobs.add(updateJob);
	}

	protected void restoreFrame(ChunkCoordinates pos, NBTTagCompound tag) {
		EntityMEMFrame frame = spawnMEMFrame(Block.obsidian.blockID, pos.posX,
				pos.posY, pos.posZ);
		frame.readFromNBT(tag);
	}

	@Override
	protected void restoreWorldBlocks(int x, int y, int z, int blockId) {
		if (framePos.contains(new ChunkCoordinates(x, y, z))) {
			return;
		}
		super.restoreWorldBlocks(x, y, z, blockId);
	}

	@Override
	protected void spwanMEMBlockWrapper(int x, int y, int z, int blockId) {
		if (inBuildFrame) {
			spawnMEMFrame(blockId, x, y, z);
			framePos.add(new ChunkCoordinates(x, y, z));
		} else {
			super.spwanMEMBlockWrapper(x, y, z, blockId);
		}
	}

	protected void updateAllFrameRenderFaces() {
		for (ChunkCoordinates cp : framePos) {
			EntityMEMBlockWrapper b = getMyWorldBlockWrapper(cp);
			EntityMEMFrame frame = (EntityMEMFrame) b;
			frame.updateRenderFace();
		}
	}

	/**
	 * Arguments: current rotation, intended rotation, max increment.
	 */
	protected float updateRotation(float par1, float par2, float par3) {
		float f;

		for (f = par2 - par1; f < -180F; f += 360F) {
		}

		for (; f >= 180F; f -= 360F) {
		}

		if (f > par3) {
			f = par3;
		}

		if (f < -par3) {
			f = -par3;
		}

		return par1 + f;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("minX", minX);
		nbttagcompound.setInteger("minY", minY);
		nbttagcompound.setInteger("minZ", minZ);
		nbttagcompound.setInteger("maxX", maxX);
		nbttagcompound.setInteger("maxY", maxY);
		nbttagcompound.setInteger("maxZ", maxZ);
		nbttagcompound.setTag("framePos", writeTagFramePos());
		nbttagcompound.setTag("frames", writeTagFrames());
		nbttagcompound.setTag("parts", writeTagParts());
	}

	protected NBTTagCompound writeTagFrame(EntityMEMBlockWrapper entity) {
		NBTTagCompound tag = new NBTTagCompound();

		NBTTagCompound posTag = TagCompoundSupport
				.getChunkCoordinatesTag(new ChunkCoordinates(
						entity.innerWorldX, entity.innerWorldY,
						entity.innerWorldZ));
		NBTTagCompound entityTag = new NBTTagCompound();
		entity.writeToNBT(entityTag);

		tag.setTag("pos", posTag);
		tag.setTag("entity", entityTag);

		return tag;
	}

	protected NBTTagCompound writeTagFramePos() {
		NBTTagCompound tag = new NBTTagCompound();
		TagCompoundSupport.writeSet(framePos, tag,
				new TagCompoundSupport.TagWriter<ChunkCoordinates>() {
					@Override
					public void write(String name, ChunkCoordinates value,
							NBTTagCompound nbttagcompound) {
						NBTTagCompound tag = TagCompoundSupport
								.getChunkCoordinatesTag(value);
						nbttagcompound.setTag(name, tag);
					}
				});
		return tag;
	}

	protected NBTTagCompound writeTagFrames() {
		NBTTagCompound tag = new NBTTagCompound();
		TagCompoundSupport.writeSet(getFrames(), tag,
				new TagCompoundSupport.TagWriter<EntityMEMBlockWrapper>() {
					@Override
					public void write(String name, EntityMEMBlockWrapper value,
							NBTTagCompound nbttagcompound) {
						nbttagcompound.setTag(name, writeTagFrame(value));
					}
				});
		return tag;
	}

	// @Override
	// public void moveEntity(double par1, double par3, double par5) {
	// double prevX = posX;
	// double prevY = posY;
	// double prevZ = posZ;
	// super.moveEntity(par1, par3, par5);
	// setInnerEntityPosition(prevX, prevY, prevZ, posX, posY, posZ);
	// }

	protected NBTTagCompound writeTagParts() {
		NBTTagCompound tag = new NBTTagCompound();
		TagCompoundSupport.writeSet(parts, tag,
				new TagCompoundSupport.TagWriter<EntityCoreSugarChildParts>() {
					@Override
					public void write(String name,
							EntityCoreSugarChildParts value,
							NBTTagCompound nbttagcompound) {
						System.out.print(value);
						System.out.print(": ");
						System.out.println(value.getMyWorld().getSaveHandler()
								.getSaveDirectoryName());
						NBTTagCompound tag = new NBTTagCompound();
						NBTTagCompound entityTag = new NBTTagCompound();
						value.writeToNBT(entityTag);

						tag.setString("name", value.getPartsNameInTag());
						tag.setTag("entity", entityTag);
						nbttagcompound.setTag(name, tag);
					}
				});
		return tag;
	}

}
