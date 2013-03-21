package net.minecraft.src;

public class MEMPunchAIDrillRecoverJob extends MEMPunchAIJob {

	public MEMPunchAIDrillRecoverJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {
		if (status.drillReachLength > 0) {
			status.drillReachLength -= MEMAIPunchStateStatus.DRILL_MOVE_SPEED;
		}
		if (status.drillReachLength < 0) {
			status.drillReachLength = 0;
		}
		if (status.rollingRotationSpeed > 0) {
			status.rollingRotationSpeed *= 0.98;
			status.rollingRotationSpeed -= MEMAIPunchStateStatus.ROLL_SPEED_ACCEL;
		}
		if (status.rollingRotationSpeed < 0) {
			status.rollingRotationSpeed = 0;
		}
		if (status.rollingRotationSpeed < 5F) {
			status.rollingRotationSpeed = 0;
			if (status.rollingRotationAngle < 1) {
				status.rollingRotationAngle = 0;
			}
			if (status.rollingRotationAngle != 0) {
				status.rollingRotationAngle = RotateSupport
						.updateDirectionalRotation(status.rollingRotationAngle,
								0, 5F, false);
				status.rollingRotationAngle = RotateSupport.fixAngle(
						status.rollingRotationAngle, 0);
			}
		}
		status.rolling(status.rollingRotationSpeed);
		if (status.drillReachLength == 0 && status.rollingRotationSpeed == 0
				&& status.rollingRotationAngle == 0) {
			arm.updateState(MEMAIPunchState.DEFAULT);
		}

		return true;
	}

	@Override
	public void startExecute() {
		status.drillTarget = null;
	}

	@Override
	public void endExecute() {
	}

	@Override
	public void suspendTick() {
	}

}
