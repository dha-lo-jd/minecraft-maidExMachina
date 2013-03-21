package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMPunchAIFireJob extends MEMPunchAIJob {

	public static final double PUNCH_SPEED = 2.3;
	public static final double BURST_RANGE = 2;

	public MEMPunchAIFireJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		if (status.punchTarget == null) {
			arm.updateState(MEMAIPunchState.RECOVER);
		}

		MovingObjectPosition target = status.punchTarget;

		Vec3D v3d = Vec3D.createVectorHelper(target.blockX - arm.posX,
				target.blockY - arm.posY, target.blockZ - arm.posZ);
		double dist = v3d.lengthVector();

		double speed = PUNCH_SPEED;
		if (speed > dist) {
			speed = dist * 0.9;
		}
		if (dist < BURST_RANGE) {
			arm.updateState(MEMAIPunchState.BURST);
		}

		v3d = v3d.normalize();
		v3d.xCoord = v3d.xCoord * PUNCH_SPEED;
		v3d.yCoord = v3d.yCoord * PUNCH_SPEED;
		v3d.zCoord = v3d.zCoord * PUNCH_SPEED;

		arm.posX += v3d.xCoord;
		arm.posY += v3d.yCoord;
		arm.posZ += v3d.zCoord;

		return true;
	}

	@Override
	public void startExecute() {
		status.chargingPos = 0;
	}

	@Override
	public void endExecute() {
	}

	@Override
	public void suspendTick() {
	}

}
