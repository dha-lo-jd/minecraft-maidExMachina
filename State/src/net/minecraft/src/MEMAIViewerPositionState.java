package net.minecraft.src;

public class MEMAIViewerPositionState implements IMEMAIState {

	public static final MEMAIStateType TYPE;
	public static final MEMAIViewerPositionState OUT;
	static {
		OUT = new MEMAIViewerPositionState();
		TYPE = new MEMAIStateType(OUT);
	}

	public static final MEMAIViewerPositionState IN = new MEMAIViewerPositionState();

	private MEMAIViewerPositionState() {
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
