package mods.mem.world;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

/**
 * Created by dhaAsusAdmin on 2017/01/10.
 */
public class MEMChunkProviderWrapper {

    @Override
    public Chunk loadChunk(int par1, int par2) {
        return super.loadChunk(0, 0);
    }

    public MEMChunkProviderWrapper(World par1World,
                                   IChunkLoader par2iChunkLoader, IChunkProvider par3iChunkProvider) {
        super(par1World, par2iChunkLoader, par3iChunkProvider);
    }
}