package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityCoreSugarSkirt extends EntityCoreSugarChildParts {

	public static final MEMBuilder.IMEMCoreChildPartsFactory FACTORY = new MEMBuilder.IMEMCoreChildPartsFactory() {
		public static final String NAME = "SKIRT";

		@Override
		public EntityCoreSugarChildParts createChildParts(
				EntityCoreSugarParts parent, EntityCoreSugarParts core,
				World par1World, NBTTagCompound tag) {
			return new EntityCoreSugarSkirt(parent, par1World, tag);
		}

		@Override
		public String getName() {
			return NAME;
		}
	};

	private EntityCoreSugarSkirt(EntityCoreSugarParts parent, World par1World,
			NBTTagCompound tag) {
		super(parent, par1World, tag);
	}

	public EntityCoreSugarSkirt(EntityCoreSugarParts parent, World par1World,
			ChunkCoordinates size, Vec3D offset, Vec3D jointPos) {
		super(parent, par1World, size, offset, jointPos, "");
	}

	@Override
	public void onLazyUpdate() {
		super.onLazyUpdate();

		Minecraft mc = ModLoader.getMinecraftInstance();

	}

	@Override
	public AxisAlignedBB getRenderFrameBox() {
		return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY - 1,
				maxZ);
	}

	@Override
	protected String getPartsName() {
		return "_SKIRT";
	}

	@Override
	public String getPartsNameInTag() {
		return FACTORY.getName();
	}

}
