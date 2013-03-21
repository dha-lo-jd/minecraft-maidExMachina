package net.minecraft.src;

public class MEMAIRidingStateUpdater extends
		MEMAIStateUpdater<MEMAIRidingState> {

	public MEMAIRidingStateUpdater(EntityCoreSugarBody core) {
		super(core, MEMAIRidingState.DEFAULT);
	}

	@Override
	public MEMAIRidingState updateState() {
		if (isRidingPlayer()) {
			return MEMAIRidingState.PLAYER;
		}
		if (isRidingDevice()) {
			return MEMAIRidingState.DEVICE;
		}
		if (isRidingLittleMaid()) {
			return MEMAIRidingState.MAID;
		}
		return MEMAIRidingState.DEFAULT;
	}

	public boolean isRidingLittleMaid() {
		return core.device.isRidingLittleMaid();
	}

	public boolean isRidingDevice() {
		return core.device.isRidingDevice();
	}

	public boolean isRidingPlayer() {
		return core.device.isRidingPlayer();
	}

}
