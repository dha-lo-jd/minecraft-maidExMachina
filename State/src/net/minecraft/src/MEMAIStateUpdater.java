package net.minecraft.src;

public abstract class MEMAIStateUpdater<T extends IMEMAIState> {

	public MEMAIStateUpdater(EntityCoreSugarBody core, T defaultState) {
		this.defaultState = defaultState;
		this.core = core;
	}

	public final T defaultState;
	public final EntityCoreSugarBody core;

	public abstract T updateState();
}
