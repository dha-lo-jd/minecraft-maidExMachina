package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMMoveAIRidingMaidFreedomJob extends MEMMoveAIJob {

	public MEMMoveAIRidingMaidFreedomJob(EntityCoreSugarBody core) {
		super(core);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		Entity ridingEntity = core.device.riddenByEntity;

		if (core.speedTick < 100) {
			core.speedTick++;
		}
		double speed = core.speedTick * 0.01 * 0.12;
		Vec3D v3d = Vec3D.createVectorHelper(0, 0, speed);
		v3d.rotateAroundY((-core.rotationYaw * (float) Math.PI) / 180F);
		core.motionX = -v3d.xCoord;
		core.motionZ = -v3d.zCoord;

		core.rotationYaw = core.updateRotation(core.rotationYaw,
				ridingEntity.rotationYaw - 180, 0.75F);
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
