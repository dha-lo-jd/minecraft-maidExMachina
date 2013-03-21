package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMMoveAIRidingPlayerJob extends MEMMoveAIJob {

	private boolean isCameraDistChanged = false;

	private float prevThirdPersonDistance;

	MEMAICameraStateUpdater cameraStateUpdater = new MEMAICameraStateUpdater(
			core);

	public MEMMoveAIRidingPlayerJob(EntityCoreSugarBody core) {
		super(core);
	}

	@Override
	public void endExecute() {
		setThirdPersonDistanceToViewCamera(prevThirdPersonDistance);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		EntityPlayer player = core.device.getRidingPlayer();

		core.updateState(cameraStateUpdater);

		if (mc.gameSettings.keyBindForward.pressed) {
			if (core.speedTick < 100) {
				core.speedTick++;
			}
		} else if (mc.gameSettings.keyBindBack.pressed) {
			if (core.speedTick > -50) {
				core.speedTick--;
			}
		} else {
			core.speedTick *= 0.9;
		}
		if (mc.gameSettings.keyBindSneak.pressed) {
			if (core.speedTick > 50) {
				core.speedTick--;
			}
			if (core.speedTick > 50) {
				core.speedTick--;
			}
			if (core.speedTick < -25) {
				core.speedTick++;
			}
			if (core.speedTick < -25) {
				core.speedTick++;
			}
		}
		double speed = core.speedTick * 0.01 * 0.2;

		Vec3D v3d = Vec3D.createVectorHelper(0, 0, speed);
		v3d.rotateAroundY((-core.rotationYaw * (float) Math.PI) / 180F);
		core.motionX = -v3d.xCoord;
		core.motionZ = -v3d.zCoord;

		if (mc.gameSettings.keyBindRight.pressed) {
			core.rotationYaw += 0.75F;
			if (mc.gameSettings.thirdPersonView != 2) {
				player.rotationYaw += 0.75F;
				player.renderYawOffset += 0.75F;
			}
		} else if (mc.gameSettings.keyBindLeft.pressed) {
			core.rotationYaw -= 0.75F;
			if (mc.gameSettings.thirdPersonView != 2) {
				player.rotationYaw -= 0.75F;
				player.renderYawOffset -= 0.75F;
			}
		} else if (mc.gameSettings.thirdPersonView == 1) {
			core.rotationYaw = core.updateRotation(core.rotationYaw,
					player.rotationYawHead - 180F, 0.75F);
		}
		return true;

	}

	@Override
	public void startExecute() {
		prevThirdPersonDistance = setThirdPersonDistanceToViewCamera(30);
	}

	@Override
	public void suspendTick() {
	}

	private float setThirdPersonDistanceToViewCamera(float dist) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		float prevDist = 0;
		try {
			int i = 13;
			prevDist = (Float) ModLoader.getPrivateValue(EntityRenderer.class,
					mc.entityRenderer, i);
			ModLoader.setPrivateValue(EntityRenderer.class, mc.entityRenderer,
					i, dist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prevDist;
	}

}
