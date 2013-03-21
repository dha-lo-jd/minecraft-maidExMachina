package net.minecraft.src;

public class MEMAICameraState implements IMEMAIState {

	public static final MEMAIStateType TYPE;
	public static final MEMAICameraState DEFAULT;
	static {
		DEFAULT = new MEMAICameraState();
		TYPE = new MEMAIStateType(DEFAULT);
	}

	public static final MEMAICameraState PLAYER = new MEMAICameraState();

	public static final MEMAICameraState THIRD_BACK = new MEMAICameraState();

	public static final MEMAICameraState THIRD_FRONT = new MEMAICameraState();

	private MEMAICameraState() {
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
