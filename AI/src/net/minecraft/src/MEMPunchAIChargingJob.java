package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMPunchAIChargingJob extends MEMPunchAIJob {

	public MEMPunchAIChargingJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		if (mc.gameSettings.keyBindJump.pressed) {
			if (status.charge == MEMAIPunchStateStatus.CHARGE_MAX
					&& status.punchTarget != null) {
				arm.updateState(MEMAIPunchState.FIRE);
			}
		}
		if (mc.gameSettings.keyBindSneak.pressed) {
			arm.updateState(MEMAIPunchState.DEFAULT);
		}
		EntityPlayer player = arm.getDevice().getRidingPlayer();
		if (player != null) {
			MovingObjectPosition t = getPunchTargetBlock(player);
			status.punchTarget = t;
			if (t != null) {
				status.punchTarget = updateRotation(t);
			}
		}
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
