package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityCoreSugarArm extends EntityCoreSugarChildParts {

	public static final MEMBuilder.IMEMCoreChildPartsFactory FACTORY = new MEMBuilder.IMEMCoreChildPartsFactory() {
		public static final String NAME = "ARM";

		@Override
		public EntityCoreSugarChildParts createChildParts(
				EntityCoreSugarParts parent, EntityCoreSugarParts core,
				World par1World, NBTTagCompound tag) {
			return new EntityCoreSugarArm(parent, par1World, tag);
		}

		@Override
		public String getName() {
			return NAME;
		}
	};

	private final int direction;

	private int tickMotion;

	private int tickMotionDirection = 1;

	private int tickMotionSpeed;

	public EntityCoreSugarArm(EntityCoreSugarParts parent, World par1World,
			ChunkCoordinates size, Vec3D offset, Vec3D jointPos, int direction,
			String suffix) {
		super(parent, par1World, size, offset, jointPos, suffix);
		this.direction = direction;
	}

	public EntityCoreSugarArm(EntityCoreSugarParts parent, World par1World,
			NBTTagCompound tag) {
		super(parent, par1World, tag);
		this.direction = tag.getInteger("direction");
	}

	@Override
	public String getPartsNameInTag() {
		return FACTORY.getName();
	}

	@Override
	public void onLazyUpdate() {
		super.onLazyUpdate();

		Minecraft mc = ModLoader.getMinecraftInstance();

		updateMotion();

	}

	protected void updateMotion() {
		if (parent.motionX - parent.motionX % 0.01 != 0
				|| parent.motionZ - parent.motionZ % 0.01 != 0) {
			if (tickMotionSpeed < 100) {
				tickMotionSpeed++;
			}

			tickMotion += tickMotionSpeed * tickMotionDirection;

			if (tickMotion > 2000 && tickMotionDirection > 0) {
				tickMotionDirection = -tickMotionDirection;
			}
			if (tickMotion < -2000 && tickMotionDirection < 0) {
				tickMotionDirection = -tickMotionDirection;
			}

			if (Math.abs(rotationZ + 30 * direction) < 5) {
				float pitch = -30 * (tickMotion / 2000F)
						* (tickMotionSpeed / 100F) * direction;
				rotationPitchOffset = updateRotation(rotationPitchOffset,
						pitch, 5F);
			}
			rotationYawOffset = updateRotation(rotationYawOffset, 0, 5F);
			rotationZ = updateRotation(rotationZ, -30 * direction, 1F);
		} else {
			if (tickMotionSpeed > 0) {
				tickMotionSpeed--;
			}

			rotationPitchOffset = updateRotation(rotationPitchOffset, -50, 5F);
			rotationYawOffset = updateRotation(rotationYawOffset, 0, 1F);
			if (rotationPitchOffset < -35) {
				rotationZ = updateRotation(rotationZ, 25 * direction, 5F);
			} else {
				rotationZ = updateRotation(rotationZ, -45 * direction, 5F);
			}

		}
	}

	@Override
	protected EntityMEMFrame createMEMFrameInstance(int blockId, int x, int y,
			int z, TileEntity tileEntity) {
		EntityMEMFrame blockWrapper = new EntityMEMFrameDummy(worldObj,
				blockId, x, y, z, this, tileEntity);
		return blockWrapper;
	}

	@Override
	protected String getPartsName() {
		return "_ARM";
	}

	@Override
	protected void updateInnerEntityRegistInner() {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("direction", direction);
	}

}
