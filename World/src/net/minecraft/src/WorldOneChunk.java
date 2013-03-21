package net.minecraft.src;

import java.util.Collection;
import java.util.List;


public class WorldOneChunk extends World {

	private static ISaveHandler getSaveHandler(World world, String par2Str) {
		return new ChildWorldSaveHandler((SaveHandler) world.getSaveHandler(),
				par2Str) {
			@Override
			protected WorldInfo createInstance(NBTTagCompound nbttagcompound) {
				return new WorldInfo(nbttagcompound);
			}
		};
	}

	private static WorldProvider getWorldProvider() {
		return new WorldProviderOC();
	}

	public final EntityCoreSugar coreEntity;

	private boolean forceInner = false;

	public ChunkCoordinates renderChunkCoordinates = null;

	public ChunkCoordinates relativeChunkCoordinates = null;

	public WorldOneChunk(World world, String par2Str,
			WorldSettings par4WorldSettings, EntityCoreSugar coreEntity) {
		super(getSaveHandler(world, par2Str), par2Str, par4WorldSettings,
				getWorldProvider());
		this.coreEntity = coreEntity;
	}

	@Override
	public void addTileEntity(Collection par1Collection) {
		super.addTileEntity(par1Collection);
	}

	@Override
	public boolean addWeatherEffect(Entity par1Entity) {
		return false;
	}

	@Override
	public void addWorldAccess(IWorldAccess par1iWorldAccess) {
		super.addWorldAccess(par1iWorldAccess);
	}

	@Override
	public int calculateSkylightSubtracted(float par1) {
		return 0;
	}

	@Override
	public boolean canBlockSeeTheSky(int par1, int par2, int par3) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return false;
		}
		return super.canBlockSeeTheSky(par1, par2, par3);
	}

	private boolean chackIsAllZero(int... i) {
		for (int j : i) {
			if (j != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkChunksExist(int par1, int par2, int par3, int par4,
			int par5, int par6) {
		return true;
	}

	@Override
	protected IChunkProvider createChunkProvider() {
		IChunkLoader ichunkloader = saveHandler.getChunkLoader(worldProvider);
		return new ChunkProviderOCWrapper(this, ichunkloader,
				worldProvider.getChunkProvider());
	}

	@Override
	public boolean doChunksNearChunkExist(int par1, int par2, int par3, int par4) {
		return true;
	}

	@Override
	public Vec3D drawClouds(float par1) {
		return Vec3D.createVector(0, 0, 0);
	}

	@Override
	public float func_35464_b(float par1) {
		return 0;
	}

	@Override
	public void func_48464_p(int par1, int par2, int par3) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.func_48464_p(par1, par2, par3);
	}

	@Override
	protected void generateSpawnPoint() {
	}

	@Override
	public BiomeGenBase getBiomeGenForCoords(int par1, int par2) {
		return BiomeGenVoid.VOID;
	}

	@Override
	public int getBlockId(int par1, int par2, int par3) {
		if (renderChunkCoordinates != null) {
			return getBlockIdInner(renderChunkCoordinates.posX + par1,
					renderChunkCoordinates.posY + par2,
					renderChunkCoordinates.posZ + par3);
		}
		if (forceInner) {
			return getBlockIdInner(par1, par2, par3);
		}
		ChunkCoordinates cp = getRelativePositionInnerWorld(par1, par2, par3);
		ChunkCoordinates cpFixed = EntityCoordsSupport.rotateFixedBoxDirection(
				coreEntity, cp.posX, cp.posY, cp.posZ, true);
		return super.getBlockId(cpFixed.posX + EntityCoreSugar.myWorldMyPosX,
				cpFixed.posY + EntityCoreSugar.myWorldMyPosY, cpFixed.posZ
						+ EntityCoreSugar.myWorldMyPosZ);
	}

	public int getBlockIdInner(int par1, int par2, int par3) {
		return super.getBlockId(par1, par2, par3);
	}

	@Override
	public Material getBlockMaterial(int par1, int par2, int par3) {
		if (forceInner) {
			return getBlockMaterialInner(par1, par2, par3);
		}
		ChunkCoordinates cp = getRelativePositionInnerWorld(par1, par2, par3);
		ChunkCoordinates cpFixed = EntityCoordsSupport.rotateFixedBoxDirection(
				coreEntity, cp.posX, cp.posY, cp.posZ, true);
		return super.getBlockMaterial(cpFixed.posX
				+ EntityCoreSugar.myWorldMyPosX, cpFixed.posY
				+ EntityCoreSugar.myWorldMyPosY, cpFixed.posZ
				+ EntityCoreSugar.myWorldMyPosZ);
	}

	public Material getBlockMaterialInner(int par1, int par2, int par3) {
		return super.getBlockMaterial(par1, par2, par3);
	}

	@Override
	public int getBlockMetadata(int par1, int par2, int par3) {
		if (renderChunkCoordinates != null) {
			return getBlockMetadataInner(renderChunkCoordinates.posX + par1,
					renderChunkCoordinates.posY + par2,
					renderChunkCoordinates.posZ + par3);
		}
		if (forceInner) {
			return getBlockMetadataInner(par1, par2, par3);
		}
		ChunkCoordinates cp = getRelativePositionInnerWorld(par1, par2, par3);
		ChunkCoordinates cpFixed = EntityCoordsSupport.rotateFixedBoxDirection(
				coreEntity, cp.posX, cp.posY, cp.posZ, true);
		return super.getBlockMetadata(cpFixed.posX
				+ EntityCoreSugar.myWorldMyPosX, cpFixed.posY
				+ EntityCoreSugar.myWorldMyPosY, cpFixed.posZ
				+ EntityCoreSugar.myWorldMyPosZ);
	}

	public int getBlockMetadataInner(int par1, int par2, int par3) {
		return super.getBlockMetadata(par1, par2, par3);
	}

	@Override
	public TileEntity getBlockTileEntity(int par1, int par2, int par3) {
		if (forceInner) {
			return getBlockTileEntityInner(par1, par2, par3);
		}
		ChunkCoordinates cp = getRelativePositionInnerWorld(par1, par2, par3);
		ChunkCoordinates cpFixed = EntityCoordsSupport.rotateFixedBoxDirection(
				coreEntity, cp.posX, cp.posY, cp.posZ, true);
		return super.getBlockTileEntity(cpFixed.posX
				+ EntityCoreSugar.myWorldMyPosX, cpFixed.posY
				+ EntityCoreSugar.myWorldMyPosY, cpFixed.posZ
				+ EntityCoreSugar.myWorldMyPosZ);
	}

	public TileEntity getBlockTileEntityInner(int par1, int par2, int par3) {
		return super.getBlockTileEntity(par1, par2, par3);
	}

	@Override
	public float getCelestialAngle(float par1) {
		return 0;
	}

	@Override
	public float getCelestialAngleRadians(float par1) {
		return 0;
	}

	@Override
	public List getCollidingBoundingBoxes(Entity par1Entity,
			AxisAlignedBB par2AxisAlignedBB) {
		return super.getCollidingBoundingBoxes(par1Entity, par2AxisAlignedBB);
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation() {
		return null;
	}

	@Override
	public int getFirstUncoveredBlock(int par1, int par2) {
		return super.getFirstUncoveredBlock(par1, par2);
	}

	@Override
	public Vec3D getFogColor(float par1) {
		return Vec3D.createVector(0, 0, 0);
	}

	@Override
	public int getLightBrightnessForSkyBlocks(int par1, int par2, int par3,
			int par4) {
		return 15728640;
		// return super.getLightBrightnessForSkyBlocks(par1, par2, par3, par4);
	}

	@Override
	public int getMoonPhase(float par1) {
		return 0;
	}

	private ChunkCoordinates getRelativePositionInnerWorld(int par1, int par2,
			int par3) {
		ChunkCoordinates cp = new EntityPosition(coreEntity)
				.getChunkCoordinates();
		int x = par1 - cp.posX;
		int y = par2 - cp.posY;
		int z = par3 - cp.posZ;

		ChunkCoordinates result = new EntityPosition(x, y, z)
				.getChunkCoordinates();

		return result;
	}

	@Override
	public Vec3D getSkyColor(Entity par1Entity, float par2) {
		return Vec3D.createVector(0, 0, 0);
	}

	public boolean isForceInner() {
		return forceInner;
	}

	@Override
	public void markBlockAsNeedsUpdate(int par1, int par2, int par3) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.markBlockAsNeedsUpdate(par1, par2, par3);
	}

	@Override
	public void markBlockNeedsUpdate(int par1, int par2, int par3) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.markBlockNeedsUpdate(par1, par2, par3);
	}

	@Override
	public void markBlocksDirty(int par1, int par2, int par3, int par4,
			int par5, int par6) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.markBlocksDirty(par1, par2, par3, par4, par5, par6);
	}

	@Override
	public void markBlocksDirtyVertical(int par1, int par2, int par3, int par4) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.markBlocksDirtyVertical(par1, par2, par3, par4);
	}

	@Override
	public void notifyBlockChange(int par1, int par2, int par3, int par4) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.notifyBlockChange(par1, par2, par3, par4);
	}

	@Override
	public void notifyBlocksOfNeighborChange(int par1, int par2, int par3,
			int par4) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.notifyBlocksOfNeighborChange(par1, par2, par3, par4);
	}

	@Override
	public void playRecord(String par1Str, int par2, int par3, int par4) {
		super.playRecord(par1Str, par2, par3, par4);
	}

	@Override
	public void playSoundAtEntity(Entity par1Entity, String par2Str,
			float par3, float par4) {
		super.playSoundAtEntity(par1Entity, par2Str, par3, par4);
	}

	@Override
	public void playSoundEffect(double par1, double par3, double par5,
			String par7Str, float par8, float par9) {
		super.playSoundEffect(par1, par3, par5, par7Str, par8, par9);
	}

	@Override
	public MovingObjectPosition rayTraceBlocks(Vec3D par1Vec3D, Vec3D par2Vec3D) {
		return super.rayTraceBlocks(par1Vec3D, par2Vec3D);
	}

	@Override
	public MovingObjectPosition rayTraceBlocks_do(Vec3D par1Vec3D,
			Vec3D par2Vec3D, boolean par3) {
		return super.rayTraceBlocks_do(par1Vec3D, par2Vec3D, par3);
	}

	@Override
	public MovingObjectPosition rayTraceBlocks_do_do(Vec3D par1Vec3D,
			Vec3D par2Vec3D, boolean par3, boolean par4) {
		return super.rayTraceBlocks_do_do(par1Vec3D, par2Vec3D, par3, par4);
	}

	@Override
	public void removeWorldAccess(IWorldAccess par1iWorldAccess) {
		super.removeWorldAccess(par1iWorldAccess);
	}

	@Override
	public boolean setBlock(int par1, int par2, int par3, int par4) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return false;
		}
		boolean result = super.setBlock(par1, par2, par3, par4);
		coreEntity.notifyBlockChanged(par1, par2, par3);
		return result;
	}

	@Override
	public boolean setBlockAndMetadata(int par1, int par2, int par3, int par4,
			int par5) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return false;
		}
		boolean result = super
				.setBlockAndMetadata(par1, par2, par3, par4, par5);
		coreEntity.notifyBlockChanged(par1, par2, par3);
		return result;
	}

	@Override
	public boolean setBlockAndMetadataWithNotify(int par1, int par2, int par3,
			int par4, int par5) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return false;
		}
		return super
				.setBlockAndMetadataWithNotify(par1, par2, par3, par4, par5);
	}

	@Override
	public boolean setBlockMetadata(int par1, int par2, int par3, int par4) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return false;
		}
		return super.setBlockMetadata(par1, par2, par3, par4);
	}

	@Override
	public void setBlockMetadataWithNotify(int par1, int par2, int par3,
			int par4) {
		if (relativeChunkCoordinates != null) {
			par1 = par1 - relativeChunkCoordinates.posX;
			par2 = par2 - relativeChunkCoordinates.posY;
			par3 = par3 - relativeChunkCoordinates.posZ;
			ChunkCoordinates cpFixed = EntityCoordsSupport
					.rotateFixedBoxDirection(coreEntity, par1, par2, par3, true);
			super.setBlockMetadataWithNotify(cpFixed.posX
					+ EntityCoreSugar.myWorldMyPosX, cpFixed.posY
					+ EntityCoreSugar.myWorldMyPosY, cpFixed.posZ
					+ EntityCoreSugar.myWorldMyPosZ, par4);
			return;
		}
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return;
		}
		super.setBlockMetadataWithNotify(par1, par2, par3, par4);
	}

	@Override
	public void setBlockTileEntity(int par1, int par2, int par3,
			TileEntity par4TileEntity) {
		super.setBlockTileEntity(par1, par2, par3, par4TileEntity);
	}

	@Override
	public boolean setBlockWithNotify(int par1, int par2, int par3, int par4) {
		if (!chackIsAllZero(par1 >> 4, par3 >> 4)) {
			return false;
		}
		return super.setBlockWithNotify(par1, par2, par3, par4);
	}

	@Override
	public void setEntityDead(Entity par1Entity) {
	}

	public void setForceInner(boolean forceInner) {
		this.forceInner = forceInner;
	}

	@Override
	public void setSpawnLocation() {
	}

	@Override
	public boolean spawnEntityInWorld(Entity par1Entity) {

		Vec3D v3d = Vec3D.createVectorHelper(par1Entity.posX
				- EntityCoreSugar.myWorldMyPosX, par1Entity.posY
				- EntityCoreSugar.myWorldMyPosY, par1Entity.posZ
				- EntityCoreSugar.myWorldMyPosZ);
		v3d.rotateAroundX((-coreEntity.rotationPitch * (float) Math.PI) / 180F);
		v3d.rotateAroundY((-coreEntity.rotationYaw * (float) Math.PI) / 180F);
		par1Entity.setPosition(v3d.xCoord + coreEntity.posX, v3d.yCoord
				+ coreEntity.posY, v3d.zCoord + coreEntity.posZ);
		par1Entity.worldObj = coreEntity.worldObj;
		return coreEntity.worldObj.spawnEntityInWorld(par1Entity);
	}

	@Override
	public void spawnParticle(String par1Str, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super.spawnParticle(par1Str, par2, par4, par6, par8, par10, par12);
	}

	@Override
	public void spawnPlayerWithLoadedChunks(EntityPlayer par1EntityPlayer) {
	}

	@Override
	public void updateEntities() {
		super.updateEntities();
	}

	@Override
	public void updateEntity(Entity par1Entity) {
		super.updateEntity(par1Entity);
	}

	@Override
	public boolean isDaytime() {
		return coreEntity.worldObj.isDaytime();
	}

	@Override
	public void updateAllPlayersSleepingFlag() {
		coreEntity.worldObj.updateAllPlayersSleepingFlag();
	}

}
