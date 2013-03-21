package net.minecraft.src;

public abstract class MEMPunchAIJob implements IMEMAIJob {

	public static final float YAW_MAX = 75;
	public static final float YAW_MIN = -75;
	public static final float PITCH_MAX = 60;
	public static final float PITCH_MIN = -70;

	protected EntityCoreSugarRightArm arm;
	protected MEMAIPunchStateStatus status;

	public MEMPunchAIJob(EntityCoreSugarRightArm arm) {
		this.arm = arm;
		this.status = MEMAIPunchStateStatus.getStatus(arm.getStateMap());
	}

	protected boolean keyUnpressed = false;

	protected boolean updateKeyState() {
		if (!mod_MaidExMachina.KEY_DRILL.pressed) {
			keyUnpressed = true;
		}
		if (mod_MaidExMachina.KEY_DRILL.pressed) {
			keyUnpressed = false;
		}
		return false;
	}

	protected MovingObjectPosition updateRotation(MovingObjectPosition t) {
		Vec3D v3d = Vec3D.createVector(t.blockX, t.blockY, t.blockZ);
		float yaw = EntityRotateSupport.getFaceToYaw(arm, v3d);
		float pitch = EntityRotateSupport.getFaceToPitch(arm, v3d);

		pitch = pitch - arm.parent.rotationPitch;
		pitch = RotateSupport.fixAngle(pitch, 180);

		yaw = yaw - arm.parent.rotationYaw;
		yaw = RotateSupport.fixAngle(yaw, 180);

		arm.faceXYZ(v3d, yaw, pitch + 90, 3F, 3F);
		arm.rotationZ = RotateSupport.updateRotation(arm.rotationZ, 0, 3F);

		arm.rotationPitchOffset = RotateSupport.fixAngle(
				arm.rotationPitchOffset, PITCH_MIN - 90, PITCH_MAX - 90, 180);
		arm.rotationYawOffset = RotateSupport.fixAngle(arm.rotationYawOffset,
				YAW_MIN, YAW_MAX, 180);

		if (pitch < PITCH_MIN || pitch > PITCH_MAX || yaw < YAW_MIN
				|| yaw > YAW_MAX) {
			t = null;
		}

		return t;
	}

	protected MovingObjectPosition getPunchTargetBlock(EntityPlayer entity) {
		MovingObjectPosition movingObjectPosition = CoordsSupport
				.getMovingObjectPosition(arm.worldObj, entity.getPosition(1),
						entity.getLookVec(), 64);
		return movingObjectPosition;
	}

}
