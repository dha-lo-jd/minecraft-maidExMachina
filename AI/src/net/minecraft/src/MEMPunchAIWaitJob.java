package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMPunchAIWaitJob extends MEMPunchAIJob {

	public MEMPunchAIWaitJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		if (mc.gameSettings.keyBindJump.pressed) {
			arm.updateState(MEMAIPunchState.CHARGE);
		}
		if (mod_MaidExMachina.KEY_DRILL.pressed && keyUnpressed) {
			arm.updateState(MEMAIPunchState.DRILL_STANDBY);
		}
		updateKeyState();
		return true;
	}

	@Override
	public void startExecute() {
		status.punchTarget = null;
		status.drillTarget = null;
	}

	@Override
	public void endExecute() {
	}

	@Override
	public void suspendTick() {
	}

}
