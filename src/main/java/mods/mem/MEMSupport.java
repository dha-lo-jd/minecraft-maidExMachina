package mods.mem;

import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import scala.Double;

public class MEMSupport {
    public static Double getAreaHeightFromGround(Set<ChunkCoordinates> area,
                                                 World world, Set<Material> ignoreMaterials) {
        for (int i = 0; i < 256; i++) {
            for (ChunkCoordinates pos : area) {
                if (!CoordsSupport.isNonMaterial(world, pos.posX, pos.posY - i,
                        pos.posZ, ignoreMaterials)) {
                    return (double) i;
                }
            }
        }
        return null;
    }
}
