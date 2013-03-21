package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMLookingAILookPlayerJob extends MEMLookingAIJob {

	public MEMLookingAILookPlayerJob(EntityCoreSugarHead head) {
		super(head);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		head.faceEntity(mc.thePlayer, 10F, 10F);
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
