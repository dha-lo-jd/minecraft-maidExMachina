package net.minecraft.src;

import java.util.Set;

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
