package net.minecraft.src;


public abstract class MEMMoveAICondition implements IMEMAICondition {

	protected EntityCoreSugarBody core;

	public MEMMoveAICondition(EntityCoreSugarBody core) {
		this.core = core;
	}

}
