package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMPunchAIDrillWorkingJob extends MEMPunchAIJob {

	public MEMPunchAIDrillWorkingJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {
		if (status.rollingRotationSpeed < MEMAIPunchStateStatus.ROLL_SPEED_MAX) {
			status.rollingRotationSpeed += MEMAIPunchStateStatus.ROLL_SPEED_ACCEL;
		}
		if (status.rollingRotationSpeed > MEMAIPunchStateStatus.ROLL_SPEED_MAX) {
			status.rollingRotationSpeed = MEMAIPunchStateStatus.ROLL_SPEED_MAX;

		}
		status.rolling(status.rollingRotationSpeed);
		EntityPlayer player = arm.getDevice().getRidingPlayer();
		if (player != null) {
			MovingObjectPosition t = getPunchTargetBlock(player);
			status.drillTarget = t;
			if (t != null) {
				status.drillTarget = updateRotation(t);
			}
		}
		Minecraft mc = ModLoader.getMinecraftInstance();
		if (mc.gameSettings.keyBindSneak.pressed) {
			if (status.drillReachLength > 0) {
				status.drillReachLength -= MEMAIPunchStateStatus.DRILL_MOVE_SPEED;
			}
			if (status.drillReachLength < 0) {
				status.drillReachLength = 0;
			}
		}
		if (mc.gameSettings.keyBindJump.pressed) {
			if (status.drillReachLength < MEMAIPunchStateStatus.DRILL_REACH_MAX) {
				status.drillReachLength += MEMAIPunchStateStatus.DRILL_MOVE_SPEED;
			}
			if (status.drillReachLength > MEMAIPunchStateStatus.DRILL_REACH_MAX) {
				status.drillReachLength = MEMAIPunchStateStatus.DRILL_REACH_MAX;
			}
		}
		if (mod_MaidExMachina.KEY_DRILL.pressed && keyUnpressed) {
			arm.updateState(MEMAIPunchState.DRILL_STANDBY);
		}

		updateKeyState();
		return true;
	}

	@Override
	public void startExecute() {
	}

	@Override
	public void endExecute() {
	}

	@Override
	public void suspendTick() {
	}

}
