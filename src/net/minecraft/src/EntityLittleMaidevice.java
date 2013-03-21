package net.minecraft.src;

public class EntityLittleMaidevice extends EntityLittleMaidEx {

	protected static final MaidExChangeSupport CHANGE_SUPPORT_DEVICE = new MaidExChangeSupport(
			new MaidExChangeSupport.IMaidFactory() {
				@Override
				public EntityLittleMaid createMaid(World world) {
					return new EntityLittleMaidevice(world);
				}

				@Override
				public EntityLittleMaid createMaidByBase(World world,
						EntityLittleMaid maid) {
					return new EntityLittleMaidevice(world, maid);
				}
			});

	EntityLittleMaid baseMaid;

	IMEMDevice device;

	public EntityLittleMaidevice(World world) {
		super(world);
	}

	@Override
	public String getLittleMaidMode() {
		return "BD6-Kr";
	}

	public EntityLittleMaidevice(World world, EntityLittleMaid baseMaid) {
		this(world);
		MaidExChangeSupport.copyProperties(baseMaid, this);
		maidInventory.entitylittlemaid = this;
		if (world != null) {
			maidAvatarEntity = new EntityLittleMaidAvatar(world, this);
			GuiLittleMaidIFF.initIFF(world);
		}
		setPosition(posX, posY, posZ);
		this.baseMaid = baseMaid;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound
				.setString("baseMaidClass", baseMaid.getClass().getName());
		NBTTagCompound tag = new NBTTagCompound();
		baseMaid.writeToNBT(tag);
		nbttagcompound.setTag("baseMaid", tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		String clsName = nbttagcompound.getString("baseMaidClass");
		MaidExChangeSupport exChangeSupport = MaidExChangeSupport
				.getMaidExChangeSupport(clsName);
		NBTTagCompound tag = (NBTTagCompound) nbttagcompound.getTag("baseMaid");
		EntityLittleMaid baseMaid = exChangeSupport.createMaid(worldObj);
		baseMaid.readFromNBT(tag);
		this.baseMaid = baseMaid;
	}

	@Override
	public void onUpdate() {
		if (ridingEntity != null
				&& ridingEntity instanceof EntityMEMDeviceZabuton) {
			device = (IMEMDevice) ridingEntity;
		}
		if (ridingEntity != device) {
			MaidExChangeSupport exChangeSupport = MaidExChangeSupport
					.getMaidExChangeSupport(baseMaid.getClass().getName());
			exChangeSupport.changeMaid(this);
		}
		setMaidMode(1);
		super.onUpdate();
		setMaidMode(1);
	}

}
