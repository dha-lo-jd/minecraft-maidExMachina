package net.minecraft.src;

public class MEMLookingAILookFrontJob extends MEMLookingAIJob {

	public MEMLookingAILookFrontJob(EntityCoreSugarHead head) {
		super(head);
	}

	@Override
	public boolean executeTick() {
		head.rotationPitchOffset = head.updateRotation(
				head.rotationPitchOffset, 0, 10F);
		head.rotationYawOffset = head.updateRotation(head.rotationYawOffset, 0,
				10F);
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
