package net.minecraft.src;

public class EntityCoreSugarChildParts extends EntityCoreSugarParts {

	private EntityCoreSugar.UpdateJob updateJob = new EntityCoreSugar.UpdateJob() {
		@Override
		public void update() {
			EntityCoreSugarChildParts.this.onLazyUpdate();
		}
	};

	protected Vec3D jointPos;

	protected Vec3D offset;

	protected EntityCoreSugarParts parent;

	protected float rotationPitchOffset, rotationYawOffset;

	public EntityCoreSugarChildParts(EntityCoreSugarParts parent,
			World par1World, ChunkCoordinates size, Vec3D offset,
			Vec3D jointPos, String suffix) {
		super(par1World, size, suffix);
		this.parent = parent;
		this.offset = offset;
		this.jointPos = jointPos;
		parent.addChildParts(this);
	}

	public EntityCoreSugarChildParts(EntityCoreSugarParts parent,
			World par1World, NBTTagCompound tag) {
		super(par1World, tag.getString("suffix"));
		this.parent = parent;
		parent.addChildParts(this);
		readFromNBT(tag);
	}

	@Override
	public void addCollisionBlockPos(EntityMEMBlockWrapper entity, int j4,
			int k4, int l4) {
		parent.addCollisionBlockPos(entity, j4, k4, l4);
	}

	@Override
	public void destroyAll() {
		super.destroyAll();
		if (!parent.isDead) {
			parent.destroyAll();
		}
	}

	/**
	 * Changes pitch and yaw so that the entity calling the function is facing
	 * the entity provided as an argument.
	 */
	@Override
	public void faceEntity(Entity par1Entity, float par2, float par3) {
		double d = par1Entity.posX - parent.posX;
		double d2 = par1Entity.posZ - parent.posZ;
		double d1;

		if (par1Entity instanceof EntityLiving) {
			EntityLiving entityliving = (EntityLiving) par1Entity;
			d1 = (parent.posY + (double) getEyeHeight())
					- (entityliving.posY + (double) entityliving.getEyeHeight());
		} else {
			d1 = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY)
					/ 2D - (parent.posY + (double) getEyeHeight());
		}

		double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
		float f = (float) ((Math.atan2(d2, d) * 180D) / Math.PI) + 90F;
		float f1 = (float) (-((Math.atan2(d1, d3) * 180D) / Math.PI));
		float pitch = f1 - parent.rotationPitch;
		float yaw = f - parent.rotationYaw;

		while (pitch > 360) {
			pitch -= 360;
		}
		while (pitch < 0) {
			pitch += 360;
		}
		while (yaw > 360) {
			yaw -= 360;
		}
		while (yaw < 0) {
			yaw += 360;
		}
		rotationPitchOffset = -updateRotation(rotationPitch
				- parent.rotationPitch, pitch, par3);
		rotationYawOffset = -updateRotation(rotationYaw - parent.rotationYaw,
				yaw, par2);
	}

	@Override
	public EntityCoreSugarParts getCore() {
		return parent.getCore();
	}

	@Override
	public IMEMDevice getDevice() {
		return getCore().getDevice();
	}

	@Override
	public MEMAI getMemai() {
		return getCore().getMemai();
	}

	public String getPartsNameInTag() {
		return "";
	}

	public void onLazyUpdate() {
		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;
		super.onUpdate();

		updatePosition();
		updateRotatedBounds(true);

		lazyUpdatePartsOnChildParts();
		super.lazyUpdate();
		updateInnerEntityRegistInner();
	}

	protected void updatePosition() {
		Vec3D v3d = Vec3D.createVectorHelper(offset.xCoord, offset.yCoord,
				offset.zCoord);
		EntityRotateSupport.rotateVec3DByEntityRotationWithZ(this, rotationZ,
				v3d);

		Vec3D v3dParent = Vec3D.createVectorHelper(jointPos.xCoord,
				jointPos.yCoord, jointPos.zCoord);
		EntityRotateSupport.rotateVec3DByEntityRotation(parent, v3dParent);

		double pX = parent.posX - v3d.xCoord + v3dParent.xCoord;
		double pY = parent.posY - v3d.yCoord + v3dParent.yCoord;
		double pZ = parent.posZ - v3d.zCoord + v3dParent.zCoord;

		float rY = parent.rotationYaw - rotationYawOffset;
		float rP = parent.rotationPitch - rotationPitchOffset;

		EntityPosition position = new EntityPosition(pX, pY, pZ, rY, rP);
		position.setPositonToEntity(this);
		setRotation(position.getRotationYaw(), position.getRotationPitch());
	}

	@Override
	public final void onUpdate() {
		// registPartsUpdateJob(updateJob);
	}

	@Override
	protected EntityCoreSugar getRegistedInnerEntityFromParent(Entity entity) {
		EntityCoreSugar p = super.getRegistedInnerEntityFromParent(entity);
		if (p == null) {
			p = parent.getRegistedInnerEntityFromParent(entity);
		}
		return p;
	}

	@Override
	protected void lazyUpdateParts() {
	}

	protected void lazyUpdatePartsOnChildParts() {
		doLazyUpdateParts();
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		offset = TagCompoundSupport
				.readTagVec3D((NBTTagCompound) nbttagcompound.getTag("offset"));
		jointPos = TagCompoundSupport
				.readTagVec3D((NBTTagCompound) nbttagcompound
						.getTag("jointPos"));
	}

	@Override
	protected void registAfterUpdateJob(UpdateJob updateJob) {
		parent.registAfterUpdateJob(updateJob);
	}

	@Override
	protected void registInnerEntity(Entity entity) {
		EntityCoreSugar childParts = null;
		for (EntityCoreSugarChildParts p : parts) {
			childParts = p.getRegistedInnerEntity(entity);
			if (childParts != null) {
				break;
			}
		}
		if (childParts == null) {
			innerEntity.add(entity);
		}
	}

	@Override
	protected void registInnerViewEntityLastTick(double x, double y, double z) {
		parent.registInnerViewEntityLastTick(x, y, z);
	}

	@Override
	protected void registPartsUpdateJob(UpdateJob updateJob) {
		parent.registPartsUpdateJob(updateJob);
	}

	@Override
	protected void registPreUpdateJob(UpdateJob updateJob) {
		parent.registPreUpdateJob(updateJob);
	}

	@Override
	protected void registUpdateJob(UpdateJob updateJob) {
		parent.registUpdateJob(updateJob);
	}

	@Override
	protected void resetStateUpdated() {
	}

	@Override
	protected void updateInnerEntityRegist() {
	}

	// @Override
	// protected void registRenderChild(EntityMEMBlockWrapper entity) {
	// parent.registRenderChild(entity);
	// }

	protected void updateInnerEntityRegistInner() {
		super.updateInnerEntityRegist();
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		NBTTagCompound offsetTag = new NBTTagCompound();
		TagCompoundSupport.writeTagVec3D(offsetTag, offset);
		nbttagcompound.setTag("offset", offsetTag);
		NBTTagCompound jointPosTag = new NBTTagCompound();
		TagCompoundSupport.writeTagVec3D(jointPosTag, jointPos);
		nbttagcompound.setTag("jointPos", jointPosTag);
	}

}
