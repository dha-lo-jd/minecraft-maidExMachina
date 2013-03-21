package net.minecraft.src;

public class MEMAIEntry<T extends IMEMAIJob> {

	protected IMEMAICondition condition;
	protected T job;

	public MEMAIEntry(IMEMAICondition condition, T job) {
		super();
		this.condition = condition;
		this.job = job;
	}

	public boolean shouldExecute() {
		return condition.shouldExecute();
	}

}
