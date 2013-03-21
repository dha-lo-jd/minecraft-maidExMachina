package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ChunkProviderOC implements IChunkProvider {
	/** RNG. */
	private Random rand;

	/** Reference to the World object. */
	private World worldObj;

	public final Chunk CHUNK;
	public final Chunk EMPTY;

	public ChunkProviderOC(World par1World, long par2, boolean par4) {
		worldObj = par1World;
		rand = new Random(par2);
		byte abyte0[] = new byte[32768];
		generateTerrain(0, 0, abyte0);
		Chunk chunk = new Chunk(par1World, abyte0, 0, 0);
		byte abyte1[] = chunk.getBiomeArray();
		for (int i = 0; i < abyte1.length; i++) {
			abyte1[i] = (byte) BiomeGenVoid.VOID.biomeID;
		}
		chunk.generateSkylightMap();
		CHUNK = chunk;
		EMPTY = new Chunk(par1World, 0, 0);
	}

	/**
	 * Generates the shape of the terrain for the chunk though its all stone
	 * though the water is frozen if the temperature is low enough
	 */
	public void generateTerrain(int par1, int par2, byte par3ArrayOfByte[]) {
		for (int i = 0; i < par3ArrayOfByte.length; i++) {
			par3ArrayOfByte[i] = 0;
		}
	}

	/**
	 * Replaces the stone that was placed in with blocks that match the biome
	 */
	public void replaceBlocksForBiome(int par1, int par2,
			byte par3ArrayOfByte[], BiomeGenBase par4ArrayOfBiomeGenBase[]) {
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	@Override
	public Chunk loadChunk(int par1, int par2) {
		return provideChunk(par1, par2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it
	 * will generates all the blocks for the specified chunk from the map seed
	 * and chunk seed
	 */
	@Override
	public Chunk provideChunk(int par1, int par2) {
		if (par1 != 0 || par2 != 0) {
			return EMPTY;
		}
		return CHUNK;
	}

	/**
	 * generates a subset of the level's terrain data. Takes 7 arguments: the
	 * [empty] noise array, the position, and the size.
	 */
	private double[] initializeNoiseField(double par1ArrayOfDouble[], int par2,
			int par3, int par4, int par5, int par6, int par7) {
		if (par1ArrayOfDouble == null) {
			par1ArrayOfDouble = new double[par5 * par6 * par7];
		}
		for (int i = 0; i < par1ArrayOfDouble.length; i++) {
			par1ArrayOfDouble[i] = 0;
		}
		return par1ArrayOfDouble;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	@Override
	public boolean chunkExists(int par1, int par2) {
		return par1 == 0 && par2 == 0;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	@Override
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If
	 * passed false, save up to two chunks. Return true if all chunks have been
	 * saved.
	 */
	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
		return true;
	}

	/**
	 * Unloads the 100 oldest chunks from memory, due to a bug with
	 * chunkSet.add() never being called it thinks the list is always empty and
	 * will not remove any chunks.
	 */
	@Override
	public boolean unload100OldestChunks() {
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	@Override
	public boolean canSave() {
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the
	 * given location.
	 */
	@Override
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType,
			int par2, int par3, int par4) {
		return null;
	}

	/**
	 * Returns the location of the closest structure of the specified type. If
	 * not found returns null.
	 */
	@Override
	public ChunkPosition findClosestStructure(World par1World, String par2Str,
			int par3, int par4, int par5) {
		return null;
	}
}
