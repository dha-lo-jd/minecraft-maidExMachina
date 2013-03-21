package net.minecraft.src;

public class ChunkProviderOCWrapper extends ChunkProvider {

	@Override
	public Chunk loadChunk(int par1, int par2) {
		return super.loadChunk(0, 0);
	}

	public ChunkProviderOCWrapper(World par1World,
			IChunkLoader par2iChunkLoader, IChunkProvider par3iChunkProvider) {
		super(par1World, par2iChunkLoader, par3iChunkProvider);
	}
}
