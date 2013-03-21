package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMMoveAIRidingDeviceJob extends MEMMoveAIJob {

	public MEMMoveAIRidingDeviceJob(EntityCoreSugarBody core) {
		super(core);
	}

	@Override
	public boolean executeTick() {
		Minecraft mc = ModLoader.getMinecraftInstance();
		Entity ridingEntity = core.device.riddenByEntity;

		double dist = Vec3D.createVector(core.posX, 0, core.posZ)
				.squareDistanceTo(mc.thePlayer.posX, 0, mc.thePlayer.posZ);
		if (128 < dist && dist < 640) {
			core.faceEntity(mc.thePlayer, 0.5F, 0);
			float faceTo = core.getFaceToYaw(mc.thePlayer);
			float yaw = core.rotationYaw;
			while (yaw > 180) {
				yaw -= 360;
			}
			while (yaw < -180) {
				yaw += 360;
			}
			float f = Math.abs(yaw - faceTo);
			if (f < 20) {
				if (core.speedTick < 100) {
					core.speedTick++;
				}
				double speed = core.speedTick * 0.01 * 0.05;
				Vec3D v3d = Vec3D.createVectorHelper(0, 0, speed);
				v3d.rotateAroundY((-core.rotationYaw * (float) Math.PI) / 180F);
				core.motionX = -v3d.xCoord;
				core.motionZ = -v3d.zCoord;
			} else if (f > 30) {
				if (core.speedTick > -50) {
					core.speedTick--;
				}
				int base = 120;
				float br = (f + 120) / (60 + base);
				br = br > 1 ? 1 : br;
				br = (1 - br);
				core.motionX *= br;
				core.motionZ *= br;
			}
		}
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
