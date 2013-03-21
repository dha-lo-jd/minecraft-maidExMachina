package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMAILookingStateUpdater extends
		MEMAIStateUpdater<MEMAILookingState> {

	public MEMAILookingStateUpdater(EntityCoreSugarBody core) {
		super(core, MEMAILookingState.DEFAULT);
	}

	@Override
	public MEMAILookingState updateState() {
		if (shouldLookCamera()) {
			return MEMAILookingState.CAMERA;
		}

		Minecraft mc = ModLoader.getMinecraftInstance();
		double dist = Vec3D.createVector(core.posX, core.posY, core.posZ)
				.squareDistanceTo(mc.thePlayer.posX, mc.thePlayer.posY,
						mc.thePlayer.posZ);
		if (128 < dist && dist < 1024) {
			return MEMAILookingState.PLAYER;
		} else if (dist < 128 || 1060 < dist) {
			return MEMAILookingState.DEFAULT;
		}
		return MEMAILookingState.KEEP;
	}

	public boolean shouldLookCamera() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		return mc.gameSettings.thirdPersonView == 2
				&& core.device.isRidingPlayer();
	}

}
