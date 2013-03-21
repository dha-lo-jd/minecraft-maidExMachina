package net.minecraft.src;

public abstract class MEMMoveAIJob implements IMEMAIJob {

	protected EntityCoreSugarBody core;

	public MEMMoveAIJob(EntityCoreSugarBody core) {
		this.core = core;
	}

}
