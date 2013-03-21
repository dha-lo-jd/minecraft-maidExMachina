package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMAICameraStateUpdater extends
		MEMAIStateUpdater<MEMAICameraState> {

	public MEMAICameraStateUpdater(EntityCoreSugarBody core) {
		super(core, MEMAICameraState.DEFAULT);
	}

	@Override
	public MEMAICameraState updateState() {

		Minecraft mc = ModLoader.getMinecraftInstance();
		switch (mc.gameSettings.thirdPersonView) {
		case 0:
			return MEMAICameraState.PLAYER;
		case 1:
			return MEMAICameraState.THIRD_BACK;
		case 2:
			return MEMAICameraState.THIRD_FRONT;
		default:
			return MEMAICameraState.DEFAULT;
		}
	}

}
