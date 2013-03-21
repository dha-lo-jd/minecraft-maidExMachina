package net.minecraft.src;

public class EntityMEMDeviceZabuton extends EntityZabuton implements IMEMDevice {

	public EntityCoreSugarBody coreSugarBody;

	public int ridingLimitTick = 0;

	public EntityMEMDeviceZabuton(World world, ItemStack itemstack,
			EntityCoreSugarBody coreSugarBody) {
		super(world, itemstack);
		this.coreSugarBody = coreSugarBody;
	}

	@Override
	public void applyEntityCollision(Entity entity) {
		if (ridingLimitTick > 0) {
			return;
		}
		super.applyEntityCollision(entity);
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		if (damagesource.getSourceOfDamage() != null
				&& damagesource.getSourceOfDamage() instanceof EntityPlayer) {
			if (riddenByEntity != null) {
				riddenByEntity.mountEntity(null);
			}
		}
		return false;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see net.minecraft.src.IMEMDevice#getRidingDevice()
	 */
	@Override
	public EntityLittleMaidevice getRidingDevice() {
		if (!isRidingDevice()) {
			return null;
		}
		return (EntityLittleMaidevice) riddenByEntity;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see net.minecraft.src.IMEMDevice#getRidingLittleMaid()
	 */
	@Override
	public EntityLittleMaid getRidingLittleMaid() {
		if (!isRidingLittleMaid()) {
			return null;
		}
		return (EntityLittleMaid) riddenByEntity;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see net.minecraft.src.IMEMDevice#getRidingPlayer()
	 */
	@Override
	public EntityPlayer getRidingPlayer() {
		if (!isRidingPlayer()) {
			return null;
		}
		return (EntityPlayer) riddenByEntity;
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		{
			Entity entity = entityplayer.riddenByEntity;
			if (entity != null && entity instanceof EntityLittleMaid) {
				entity.mountEntity(this);
				return true;
			}
		}
		{
			Entity entity = this.riddenByEntity;
			if (entity != null && entity instanceof EntityLittleMaid) {
				return false;
			}
		}
		boolean interact = super.interact(entityplayer);
		coreSugarBody.renderModify = true;
		EntityPlayer rider = null;
		if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer) {
			rider = (EntityPlayer) riddenByEntity;
		}
		return interact;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see net.minecraft.src.IMEMDevice#isRidingDevice()
	 */
	@Override
	public boolean isRidingDevice() {
		return riddenByEntity != null
				&& riddenByEntity instanceof EntityLittleMaidevice;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see net.minecraft.src.IMEMDevice#isRidingLittleMaid()
	 */
	@Override
	public boolean isRidingLittleMaid() {
		return riddenByEntity != null
				&& riddenByEntity instanceof EntityLittleMaid;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see net.minecraft.src.IMEMDevice#isRidingPlayer()
	 */
	@Override
	public boolean isRidingPlayer() {
		return riddenByEntity != null && riddenByEntity instanceof EntityPlayer;
	}

	@Override
	public boolean isInRangeToRenderDist(double par1) {
		if (coreSugarBody.isRenderTransparent) {
			return false;
		}
		return super.isInRangeToRenderDist(par1);
	}

	@Override
	public void onUpdate() {
		if (coreSugarBody == null || coreSugarBody.isDead) {
			setDead();
		}
		if (ridingLimitTick > 0) {
			ridingLimitTick--;
		}
		if (riddenByEntity != null) {
			ridingLimitTick = 100;
			if (riddenByEntity instanceof EntityLittleMaid
					&& !(riddenByEntity instanceof EntityLittleMaidevice)) {
				EntityLittleMaid maid = (EntityLittleMaid) riddenByEntity;
				if (maid.isMaidContractEX()) {
					maid.mountEntity(null);
					EntityLittleMaid newMaid = EntityLittleMaidevice.CHANGE_SUPPORT_DEVICE
							.changeMaid(maid);
					newMaid.mountEntity(this);
				}
			}
		}
	}
}
