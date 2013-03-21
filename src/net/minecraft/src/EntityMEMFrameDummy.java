package net.minecraft.src;

public class EntityMEMFrameDummy extends EntityMEMFrame {

	public EntityMEMFrameDummy(World par1World, int blockID, int innerWorldX,
			int innerWorldY, int innerWorldZ, EntityCoreSugar coreEntity,
			TileEntity myTileEntity) {
		super(par1World, blockID, innerWorldX, innerWorldY, innerWorldZ,
				coreEntity, myTileEntity);
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return null;
	}

	@Override
	protected void setInnerEntityPosition(double prevX, double prevY,
			double prevZ, double newPosX, double newPosY, double newPosZ) {
	}

}
