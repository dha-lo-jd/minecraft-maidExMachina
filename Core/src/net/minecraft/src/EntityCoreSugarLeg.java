package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityCoreSugarLeg extends EntityCoreSugarChildParts {

	public static final MEMBuilder.IMEMCoreChildPartsFactory FACTORY = new MEMBuilder.IMEMCoreChildPartsFactory() {
		public static final String NAME = "LEG";

		@Override
		public EntityCoreSugarChildParts createChildParts(
				EntityCoreSugarParts parent, EntityCoreSugarParts core,
				World par1World, NBTTagCompound tag) {
			return new EntityCoreSugarLeg(parent, core, par1World, tag);
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

	protected EntityCoreSugarParts core;

	public EntityCoreSugarLeg(EntityCoreSugarParts parent,
			EntityCoreSugarParts core, World par1World, ChunkCoordinates size,
			Vec3D offset, Vec3D jointPos, int direction, String suffix) {
		super(parent, par1World, size, offset, jointPos, suffix);
		this.direction = direction;
		this.core = core;
	}

	public EntityCoreSugarLeg(EntityCoreSugarParts parent,
			EntityCoreSugarParts core, World par1World, NBTTagCompound tag) {
		super(parent, par1World, tag);
		this.core = core;
		this.direction = tag.getInteger("direction");
	}

	@Override
	public void addCollisionBlockPos(EntityMEMBlockWrapper entity, int j4,
			int k4, int l4) {
	}

	@Override
	public String getPartsNameInTag() {
		return FACTORY.getName();
	}

	@Override
	public AxisAlignedBB getRenderFrameBox() {
		return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY - 6,
				maxZ);
	}

	@Override
	public void onLazyUpdate() {
		super.onLazyUpdate();

		if (core.motionX - core.motionX % 0.01 != 0
				|| core.motionZ - core.motionZ % 0.01 != 0) {
			if (tickMotionSpeed < 100) {
				tickMotionSpeed++;
			}

			tickMotion += tickMotionSpeed * tickMotionDirection;

			if (tickMotion > 2000 && tickMotionDirection > 0) {
				tickMotionDirection = -tickMotionDirection;
				worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 30.0F,
						0.01F);
				worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 30.0F,
						0.05F);
			}
			if (tickMotion < -2000 && tickMotionDirection < 0) {
				tickMotionDirection = -tickMotionDirection;
				worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 30.0F,
						0.01F);
				worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 30.0F,
						0.05F);
			}
		} else {
			if (tickMotionSpeed > 0) {
				tickMotionSpeed--;
			}
		}

		rotationPitchOffset = 15 * (tickMotion / 2000F)
				* (tickMotionSpeed / 100F) * direction;

		Minecraft mc = ModLoader.getMinecraftInstance();

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
		return "_LEG";
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
