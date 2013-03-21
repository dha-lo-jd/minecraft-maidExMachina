package net.minecraft.src;

import java.util.HashSet;
import java.util.Set;

public class EntityCoreSugarRightArm extends EntityCoreSugarArm {

	public static final MEMBuilder.IMEMCoreChildPartsFactory FACTORY = new MEMBuilder.IMEMCoreChildPartsFactory() {
		public static final String NAME = "R_ARM";

		@Override
		public EntityCoreSugarChildParts createChildParts(
				EntityCoreSugarParts parent, EntityCoreSugarParts core,
				World par1World, NBTTagCompound tag) {
			return new EntityCoreSugarRightArm(parent, par1World, tag);
		}

		@Override
		public String getName() {
			return NAME;
		}
	};
	private static final Set<IMEMAIState> states;
	static {
		states = new HashSet<IMEMAIState>();
		states.add(MEMAIPunchState.FIRE);
		states.add(MEMAIPunchState.BURST);
		states.add(MEMAIPunchState.RECOVER);
	}

	{
		getMemai().addEntries(MEMAISupport.getPunchJobSet(this));
		getMemai().addEntries(MEMAISupport.getPunchChargeJobSet(this));
	}

	public EntityCoreSugarRightArm(EntityCoreSugarParts parent,
			World par1World, ChunkCoordinates size, Vec3D offset,
			Vec3D jointPos, int direction, String suffix) {
		super(parent, par1World, size, offset, jointPos, 1, suffix);
	}

	public EntityCoreSugarRightArm(EntityCoreSugarParts parent,
			World par1World, NBTTagCompound tag) {
		super(parent, par1World, tag);
	}

	@Override
	public String getPartsNameInTag() {
		return FACTORY.getName();
	}

	@Override
	protected void updatePosition() {
		if (getStateMap().hasState(MEMAIPunchState.TYPE, states)) {
			return;
		}
		super.updatePosition();
	}

	@Override
	protected void updateMotion() {
		if (getStateMap().isStateAs(MEMAIPunchState.DEFAULT)) {
			super.updateMotion();
		}
	}

	protected void processCollision(MEMCollisionObject collision) {
		collisionSet.remove(collision);

		int blockId = worldObj.getBlockId(collision.pos.posX,
				collision.pos.posY, collision.pos.posZ);
		Material m;
		if (blockId == 0) {
			m = Material.air;
		} else {
			m = Block.blocksList[blockId].blockMaterial;
		}
		if (EntityCoreSugarBody.ignoreMaterials.contains(m)) {
			return;
		}
		if (EntityCoreSugarBody.unbreakableAlwaysMaterials.contains(blockId)) {
			return;
		} else {
			breakCollideBlock(collision);
		}
	}

	protected void breakCollideBlock(MEMCollisionObject collision) {
		worldObj.setBlockWithNotify(collision.pos.posX, collision.pos.posY,
				collision.pos.posZ, 0);
	}

	@Override
	public void onLazyUpdate() {
		super.onLazyUpdate();

		if (getStateMap().isStateAs(MEMAIPunchState.DRILL_WORKING)) {
			Set<MEMCollisionObject> cSet = new HashSet<MEMCollisionObject>(
					collisionSet);
			for (MEMCollisionObject collisionObject : cSet) {
				processCollision(collisionObject);
			}
		} else {
			collisionSet = new HashSet<MEMCollisionObject>();
		}

		MEMAIPunchStateStatus status = MEMAIPunchStateStatus
				.getStatus(getStateMap());

		if (status.chargingPos > 0) {
			float cRate = (float) status.chargingPos
					/ MEMAIPunchStateStatus.CHARGE_MAX;
			double offsetLength = MEMAIPunchStateStatus.CHARGE_MAX_OFFSET
					* cRate;

			Vec3D offsetPos = Vec3D.createVectorHelper(0, offsetLength, 0);

			EntityRotateSupport.rotateVec3DByEntityRotationWithZ(this,
					rotationZ, offsetPos);

			posX += offsetPos.xCoord;
			posY += offsetPos.yCoord;
			posZ += offsetPos.zCoord;
		}
		if (status.drillReachLength > 0) {
			float cRate = (float) status.drillReachLength
					/ MEMAIPunchStateStatus.CHARGE_MAX;
			double offsetLength = MEMAIPunchStateStatus.DRILL_REACH_LENGTH
					* cRate;

			Vec3D offsetPos = Vec3D.createVectorHelper(0, -offsetLength, 0);

			EntityRotateSupport.rotateVec3DByEntityRotationWithZ(this,
					rotationZ, offsetPos);

			posX += offsetPos.xCoord;
			posY += offsetPos.yCoord;
			posZ += offsetPos.zCoord;
		}

	}

	Set<MEMCollisionObject> collisionSet = new HashSet<MEMCollisionObject>();

	@Override
	public void addCollisionBlockPos(EntityMEMBlockWrapper entity, int j4,
			int k4, int l4) {
		collisionSet.add(new MEMCollisionObject(entity, new ChunkCoordinates(
				j4, k4, l4)));
	}

	public void faceXYZ(Vec3D v3d, float par2, float par3) {
		float yaw = EntityRotateSupport.getFaceToYaw(this, v3d);
		float pitch = EntityRotateSupport.getFaceToPitch(this, v3d);

		pitch = pitch - parent.rotationPitch + 90;
		yaw = yaw - parent.rotationYaw;

		faceXYZ(v3d, yaw, pitch, par2, par3);
	}

	public void faceXYZ(Vec3D v3d, float yaw, float pitch, float par2,
			float par3) {
		this.rotationPitchOffset = -RotateSupport.updateRotation(rotationPitch
				- parent.rotationPitch, pitch, par3);
		this.rotationYawOffset = -RotateSupport.updateRotation(rotationYaw
				- parent.rotationYaw, yaw, par2);
	}
}
