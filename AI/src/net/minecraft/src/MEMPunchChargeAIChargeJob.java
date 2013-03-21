package net.minecraft.src;

public class MEMPunchChargeAIChargeJob extends MEMPunchChargeAIJob {

	public MEMPunchChargeAIChargeJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {
		if (status.charge < MEMAIPunchStateStatus.CHARGE_MAX) {
			status.addCharge(status.chargeSpeed);
		}
		if (arm.getStateMap().isStateAs(MEMAIRidingState.DEFAULT)) {
			arm.updateState(MEMAIPunchState.DEFAULT);
		}
		return true;
	}

	@Override
	public void startExecute() {
	}

	@Override
	public void endExecute() {
		status.charge = 0;
	}

	@Override
	public void suspendTick() {
		if (status.chargingPos > 0) {
			status.chargingPos -= MEMAIPunchStateStatus.CHARGE_SPEED;
		}
		if (status.chargingPos < 0) {
			status.chargingPos = 0;
		}
	}

}
