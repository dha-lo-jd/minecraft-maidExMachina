package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMMoveAIDefaultJob extends MEMMoveAIJob {

	public MEMMoveAIDefaultJob(EntityCoreSugarBody core) {
		super(core);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		core.speedTick *= 0.9;
		return true;
	}

	@Override
	public void startExecute() {
	}

	@Override
	public void endExecute() {
	}

	@Override
	public void suspendTick() {
	}

}
