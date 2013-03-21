package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerOC extends WorldChunkManager {

	private WorldChunkManagerOC() {
	}

	private WorldChunkManagerOC(long par1) {
		this();
	}

	public WorldChunkManagerOC(World par1World) {
		this(par1World.getSeed());
	}

	/**
	 * checks given Chunk's Biomes against List of allowed ones
	 */
	@Override
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
		return par4List.contains(BiomeGenVoid.VOID);
	}

	/**
	 * Calls the WorldChunkManager's biomeCache.cleanupCache()
	 */
	@Override
	public void cleanupCache() {
	}

	/**
	 * Finds a valid position within a range, that is once of the listed biomes.
	 */
	@Override
	public ChunkPosition findBiomePosition(int par1, int par2, int par3,
			List par4List, Random par5Random) {
		if (!par4List.contains(BiomeGenVoid.VOID)) {
			return null;
		}
		return new ChunkPosition(par1, 0, par2);
	}

	/**
	 * Return a list of biomes for the specified blocks. Args: listToReuse, x,
	 * y, width, length, cacheFlag (if false, don't check biomeCache to avoid
	 * infinite loop in BiomeCacheBlock)
	 */
	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase par1ArrayOfBiomeGenBase[],
			int par2, int par3, int par4, int par5, boolean par6) {
		IntCache.resetIntCache();

		if (par1ArrayOfBiomeGenBase == null
				|| par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		for (int i = 0; i < par4 * par5; i++) {
			par1ArrayOfBiomeGenBase[i] = BiomeGenVoid.VOID;
		}

		return par1ArrayOfBiomeGenBase;
	}

	/**
	 * Returns the BiomeGenBase related to the x, z position on the world.
	 */
	@Override
	public BiomeGenBase getBiomeGenAt(int par1, int par2) {
		return BiomeGenVoid.VOID;
	}

	/**
	 * Returns an array of biomes for the location input.
	 */
	@Override
	public BiomeGenBase[] getBiomesForGeneration(
			BiomeGenBase par1ArrayOfBiomeGenBase[], int par2, int par3,
			int par4, int par5) {
		IntCache.resetIntCache();

		if (par1ArrayOfBiomeGenBase == null
				|| par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		for (int i = 0; i < par4 * par5; i++) {
			par1ArrayOfBiomeGenBase[i] = BiomeGenVoid.VOID;
		}

		return par1ArrayOfBiomeGenBase;
	}

	/**
	 * Gets the list of valid biomes for the player to spawn in.
	 */
	@Override
	public List getBiomesToSpawnIn() {
		return new ArrayList();
	}

	/**
	 * Returns a list of rainfall values for the specified blocks. Args:
	 * listToReuse, x, z, width, length.
	 */
	@Override
	public float[] getRainfall(float par1ArrayOfFloat[], int par2, int par3,
			int par4, int par5) {
		IntCache.resetIntCache();

		if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
			par1ArrayOfFloat = new float[par4 * par5];
		}

		for (int i = 0; i < par4 * par5; i++) {
			par1ArrayOfFloat[i] = 0;
		}

		return par1ArrayOfFloat;
	}

	/**
	 * Return an adjusted version of a given temperature based on the y height
	 */
	@Override
	public float getTemperatureAtHeight(float par1, int par2) {
		return par1;
	}

	/**
	 * Returns a list of temperatures to use for the specified blocks. Args:
	 * listToReuse, x, y, width, length
	 */
	@Override
	public float[] getTemperatures(float par1ArrayOfFloat[], int par2,
			int par3, int par4, int par5) {
		IntCache.resetIntCache();

		if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
			par1ArrayOfFloat = new float[par4 * par5];
		}

		for (int i = 0; i < par4 * par5; i++) {
			par1ArrayOfFloat[i] = 0;
		}

		return par1ArrayOfFloat;
	}

	/**
	 * Returns biomes to use for the blocks and loads the other data like
	 * temperature and humidity onto the WorldChunkManager Args: oldBiomeList,
	 * x, z, width, depth
	 */
	@Override
	public BiomeGenBase[] loadBlockGeneratorData(
			BiomeGenBase par1ArrayOfBiomeGenBase[], int par2, int par3,
			int par4, int par5) {
		return getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5,
				true);
	}
}
