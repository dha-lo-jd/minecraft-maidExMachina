package mods.mem.world;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import scala.collection.immutable.List;
import scala.util.Random;

/**
 * Created by dhaAsusAdmin on 2017/01/06.
 */
public class BiomeGenVoid extends BiomeGenBase {

    public static final BiomeGenBase VOID = new BiomeGenVoid(
            mod_MaidExMachina.BIOME_VOID_ID).setBiomeName("VOID");

    protected BiomeGenVoid(int par1) {
        super(par1);
    }

    @Override
    public boolean canSpawnLightningBolt() {
        return false;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4) {
    }

    @Override
    public int getBiomeFoliageColor() {
        return 0;
    }

    @Override
    public int getBiomeGrassColor() {
        return 0;
    }

    @Override
    public boolean getEnableSnow() {
        return false;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
        return null;
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
        return null;
    }

    @Override
    public List getSpawnableList(EnumCreatureType par1EnumCreatureType) {
        return null;
    }

    @Override
    public float getSpawningChance() {
        return 0;
    }

    @Override
    public boolean isHighHumidity() {
        return false;
    }

    @Override
    protected BiomeDecorator createBiomeDecorator() {
        return null;
    }

    @Override
    protected BiomeGenBase func_4124_a(int par1) {
        return this;
    }

    @Override
    protected BiomeGenBase setColor(int par1) {
        return this;
    }

    @Override
    protected BiomeGenBase setEnableSnow() {
        return this;
    }
}