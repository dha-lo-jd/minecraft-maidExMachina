package net.minecraft.src;

public abstract class MEMPunchChargeAIJob implements IMEMAIJob {

	protected EntityCoreSugarRightArm arm;
	protected MEMAIPunchStateStatus status;

	public MEMPunchChargeAIJob(EntityCoreSugarRightArm arm) {
		this.arm = arm;
		this.status = MEMAIPunchStateStatus.getStatus(arm.getStateMap());
	}

}
