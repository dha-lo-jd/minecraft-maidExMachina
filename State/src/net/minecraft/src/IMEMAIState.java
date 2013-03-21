package net.minecraft.src;

import net.minecraft.src.IMEMAIStateStatus.IMEMAIStateStatusFactory;

public interface IMEMAIState {

	public static class MEMAIStateType<T extends IMEMAIState> {
		public final IMEMAIState defaultState;
		public final IMEMAIStateStatusFactory<T> statusFactory;

		public MEMAIStateType(IMEMAIState defaultState) {
			this(defaultState, null);
		}

		public MEMAIStateType(IMEMAIState defaultState,
				IMEMAIStateStatusFactory<T> statusFactory) {
			this.defaultState = defaultState;
			this.statusFactory = statusFactory;
		}

		public IMEMAIStateStatus<T> createStateStatus() {
			if (statusFactory == null) {
				return null;
			}
			return statusFactory.createInstance();
		}
	}

	public MEMAIStateType getType();

	public IMEMAIState getUpdateState();

	public IMEMAIState getEndState();

}
