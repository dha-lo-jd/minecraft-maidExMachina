package net.minecraft.src;

public class MEMLookingAILookCameraJob extends MEMLookingAIJob {

	public MEMLookingAILookCameraJob(EntityCoreSugarHead head) {
		super(head);
	}

	@Override
	public boolean executeTick() {
		EntityPlayer player = head.getDevice().getRidingPlayer();
		if (player != null) {
			head.rotationPitchOffset = head.updateRotation(
					head.rotationPitchOffset, player.rotationPitch + 20, 10F);
			head.rotationYawOffset = head.updateRotation(
					head.rotationYawOffset, 180 - player.rotationYawHead
							+ head.parent.rotationYaw, 10F);
			if (75 < head.rotationYawOffset) {
				head.rotationYawOffset = 75;
			}
			if (-75 > head.rotationYawOffset) {
				head.rotationYawOffset = -75;
			}
			if (50 < head.rotationPitchOffset) {
				head.rotationPitchOffset = 50;
			}
			if (-60 > head.rotationPitchOffset) {
				head.rotationPitchOffset = -60;
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
