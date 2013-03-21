package net.minecraft.src;


public class EntityCoreSugarHair extends EntityCoreSugarChildParts {

	public static final MEMBuilder.IMEMCoreChildPartsFactory FACTORY = new MEMBuilder.IMEMCoreChildPartsFactory() {
		public static final String NAME = "HAIR_0";

		@Override
		public EntityCoreSugarChildParts createChildParts(
				EntityCoreSugarParts parent, EntityCoreSugarParts core,
				World par1World, NBTTagCompound tag) {
			return new EntityCoreSugarHair(parent, core, par1World, tag);
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

	public EntityCoreSugarHair(EntityCoreSugarParts parent,
			EntityCoreSugarParts core, World par1World, ChunkCoordinates size,
			Vec3D offset, Vec3D jointPos, int direction) {
		super(parent, par1World, size, offset, jointPos, String
				.valueOf(direction));
		this.direction = direction;
		this.core = core;
	}

	public EntityCoreSugarHair(EntityCoreSugarParts parent,
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
		return null;
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
		return "_HAIR";
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
