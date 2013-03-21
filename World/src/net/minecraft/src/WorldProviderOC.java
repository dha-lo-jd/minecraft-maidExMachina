package net.minecraft.src;

public class WorldProviderOC extends WorldProvider {

	@Override
	protected void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerOC(worldObj);
	}

	@Override
	public IChunkProvider getChunkProvider() {
		return new ChunkProviderOC(worldObj, worldObj.getSeed(), worldObj
				.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public int getMoonPhase(long par1, float par3) {
		return 4;
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public int getAverageGroundLevel() {
		return 64;
	}

}
