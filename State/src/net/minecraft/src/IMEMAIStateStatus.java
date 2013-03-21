package net.minecraft.src;


public interface IMEMAIStateStatus<T extends IMEMAIState> {
	public static interface IMEMAIStateStatusFactory<T extends IMEMAIState> {
		public IMEMAIStateStatus<T> createInstance();
	}

}
