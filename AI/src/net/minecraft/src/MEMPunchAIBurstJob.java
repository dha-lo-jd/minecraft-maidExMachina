package net.minecraft.src;

public class MEMPunchAIBurstJob extends MEMPunchAIJob {

	public static final float EXPLODE_POWER = 10;

	public MEMPunchAIBurstJob(EntityCoreSugarRightArm arm) {
		super(arm);
	}

	@Override
	public boolean executeTick() {

		// EntityPosition ep = new EntityPosition(arm);
		// ChunkCoordinates cp = ep.getChunkCoordinates();
		if (status.punchTarget != null) {
			MovingObjectPosition p = status.punchTarget;
			arm.getCore().worldObj.createExplosion(arm, p.blockX, p.blockY,
					p.blockZ, EXPLODE_POWER);
		}

		arm.updateState(MEMAIPunchState.RECOVER);

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
