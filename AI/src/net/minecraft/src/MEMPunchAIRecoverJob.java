package net.minecraft.src;

public class MEMPunchAIRecoverJob extends MEMPunchAIJob {

	public static final float RECOVER_SPEED = 1F;

	public MEMPunchAIRecoverJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {
		Vec3D v3d = MEMAISupport.getCoreHomePosition(arm);
		arm.faceXYZ(Vec3D.createVector(v3d.xCoord, v3d.yCoord, v3d.zCoord), 3F,
				3F);
		arm.rotationZ = RotateSupport.updateRotation(arm.rotationZ, 0, 3F);

		v3d = Vec3D.createVectorHelper(v3d.xCoord - arm.posX, v3d.yCoord
				- arm.posY, v3d.zCoord - arm.posZ);
		double dist = v3d.lengthVector();

		if (dist < 2) {
			arm.updateState(MEMAIPunchState.DEFAULT);
		}

		v3d = v3d.normalize();
		v3d.xCoord = v3d.xCoord * RECOVER_SPEED;
		v3d.yCoord = v3d.yCoord * RECOVER_SPEED;
		v3d.zCoord = v3d.zCoord * RECOVER_SPEED;

		arm.posX += v3d.xCoord;
		arm.posY += v3d.yCoord;
		arm.posZ += v3d.zCoord;

		return true;
	}

	@Override
	public void startExecute() {
		status.punchTarget = null;
	}

	@Override
	public void endExecute() {
	}

	@Override
	public void suspendTick() {
	}

}
