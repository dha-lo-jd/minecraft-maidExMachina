package net.minecraft.src;

import net.minecraft.src.IMEMAIStateStatus.IMEMAIStateStatusFactory;

public class MEMAIPunchState implements IMEMAIState {
	public static class StateFactory implements
			IMEMAIStateStatusFactory<MEMAIPunchState> {
		@Override
		public IMEMAIStateStatus<MEMAIPunchState> createInstance() {
			return new MEMAIPunchStateStatus();
		}
	}

	public static final StateFactory FACTORY = new StateFactory();

	public static final MEMAIStateType TYPE;
	public static final MEMAIPunchState DEFAULT;
	static {
		DEFAULT = new MEMAIPunchState();
		TYPE = new MEMAIStateType(DEFAULT, FACTORY);
	}

	public static final MEMAIPunchState CHARGE = new MEMAIPunchState();

	public static final MEMAIPunchState FIRE = new MEMAIPunchState();

	public static final MEMAIPunchState BURST = new MEMAIPunchState();

	public static final MEMAIPunchState RECOVER = new MEMAIPunchState();

	public static final MEMAIPunchState DRILL_STANDBY = new MEMAIPunchState();

	public static final MEMAIPunchState DRILL_WORKING = new MEMAIPunchState();

	public static final MEMAIPunchState DRILL_RECOVER = new MEMAIPunchState();

	private MEMAIPunchState() {
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
