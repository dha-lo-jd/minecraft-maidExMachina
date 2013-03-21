package net.minecraft.src;

public class MEMAILookingState implements IMEMAIState {

	public static final MEMAIStateType TYPE;
	public static final MEMAILookingState DEFAULT;
	static {
		DEFAULT = new MEMAILookingState();
		TYPE = new MEMAIStateType(DEFAULT);
	}

	public static final MEMAILookingState PLAYER = new MEMAILookingState();

	public static final MEMAILookingState CAMERA = new MEMAILookingState();

	public static final MEMAILookingState KEEP = new MEMAILookingState();

	private MEMAILookingState() {
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
