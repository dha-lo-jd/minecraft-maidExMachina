package net.minecraft.src;

public class MEMAIRidingState implements IMEMAIState {

	public static final MEMAIStateType TYPE;
	public static final MEMAIRidingState DEFAULT;
	static {
		DEFAULT = new MEMAIRidingState();
		TYPE = new MEMAIStateType(DEFAULT);
	}

	public static final MEMAIRidingState PLAYER = new MEMAIRidingState();

	public static final MEMAIRidingState MAID = new MEMAIRidingState();

	public static final MEMAIRidingState DEVICE = new MEMAIRidingState();

	private MEMAIRidingState() {
	}

	@Override
	public MEMAIStateType getType() {
		return TYPE;
	}

	@Override
	public IMEMAIState getUpdateState() {
		return this;
	}

	@Override
	public IMEMAIState getEndState() {
		return this;
	}

}
