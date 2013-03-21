package net.minecraft.src;

public class MEMAISupport {

	public static Vec3D getCoreHomePosition(EntityCoreSugarChildParts parts) {
		Vec3D v3d = Vec3D.createVectorHelper(parts.offset.xCoord,
				parts.offset.yCoord, parts.offset.zCoord);
		EntityRotateSupport.rotateVec3DByEntityRotationWithZ(parts,
				parts.rotationZ, v3d);

		Vec3D v3dParent = Vec3D.createVectorHelper(parts.jointPos.xCoord,
				parts.jointPos.yCoord, parts.jointPos.zCoord);
		EntityRotateSupport
				.rotateVec3DByEntityRotation(parts.parent, v3dParent);

		double pX = parts.parent.posX - v3d.xCoord + v3dParent.xCoord;
		double pY = parts.parent.posY - v3d.yCoord + v3dParent.yCoord;
		double pZ = parts.parent.posZ - v3d.zCoord + v3dParent.zCoord;

		return Vec3D.createVectorHelper(pX, pY, pZ);
	}

	public static MEMAIEntrySet<MEMMoveAIJob> getMoveJobSet(
			EntityCoreSugarBody core) {
		MEMAIEntrySet<MEMMoveAIJob> s = new MEMAIEntrySet();
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					core.getStateMap());
			condition.addAllow(MEMAIRidingState.PLAYER);
			MEMAIEntry<MEMMoveAIJob> entry = new MEMAIEntry<MEMMoveAIJob>(
					condition, new MEMMoveAIRidingPlayerJob(core));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					core.getStateMap());
			condition.addAllow(MEMAIRidingState.DEVICE);
			MEMAIEntry<MEMMoveAIJob> entry = new MEMAIEntry<MEMMoveAIJob>(
					condition, new MEMMoveAIRidingDeviceJob(core));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					core.getStateMap());
			condition.addAllow(MEMAIRidingState.MAID);
			MEMAIEntry<MEMMoveAIJob> entry = new MEMAIEntry<MEMMoveAIJob>(
					condition, new MEMMoveAIRidingMaidFreedomJob(core));
			s.add(entry);
		}
		return s;
	}

	public static MEMAIEntrySet<MEMLookingAIJob> getLookingJobSet(
			EntityCoreSugarHead head) {
		MEMAIEntrySet<MEMLookingAIJob> s = new MEMAIEntrySet<MEMLookingAIJob>();
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					head.getStateMap());
			condition.addAllow(MEMAILookingState.PLAYER);
			MEMAIEntry<MEMLookingAIJob> entry = new MEMAIEntry<MEMLookingAIJob>(
					condition, new MEMLookingAILookPlayerJob(head));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					head.getStateMap());
			condition.addAllow(MEMAILookingState.CAMERA);
			MEMAIEntry<MEMLookingAIJob> entry = new MEMAIEntry<MEMLookingAIJob>(
					condition, new MEMLookingAILookCameraJob(head));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					head.getStateMap());
			condition.addAllow(MEMAILookingState.KEEP);
			MEMAIEntry<MEMLookingAIJob> entry = new MEMAIEntry<MEMLookingAIJob>(
					condition, new MEMLookingAILookKeepJob(head));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					head.getStateMap());
			condition.addAllow(MEMAILookingState.DEFAULT);
			MEMAIEntry<MEMLookingAIJob> entry = new MEMAIEntry<MEMLookingAIJob>(
					condition, new MEMLookingAILookFrontJob(head));
			s.add(entry);
		}
		return s;
	}

	public static MEMAIEntrySet<MEMPunchAIJob> getPunchJobSet(
			EntityCoreSugarRightArm arm) {
		MEMAIEntrySet<MEMPunchAIJob> s = new MEMAIEntrySet<MEMPunchAIJob>();
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIRidingState.PLAYER);
			condition.addAllow(MEMAIPunchState.DEFAULT);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIWaitJob(arm));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.CHARGE);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIChargingJob(arm));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.FIRE);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIFireJob(arm));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.BURST);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIBurstJob(arm));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.RECOVER);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIRecoverJob(arm));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.DRILL_STANDBY);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIDrillStandbyJob(arm));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.DRILL_WORKING);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIDrillWorkingJob(arm));
			s.add(entry);
		}
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.DRILL_RECOVER);
			MEMAIEntry<MEMPunchAIJob> entry = new MEMAIEntry<MEMPunchAIJob>(
					condition, new MEMPunchAIDrillRecoverJob(arm));
			s.add(entry);
		}
		return s;
	}

	public static MEMAIEntrySet<MEMPunchChargeAIJob> getPunchChargeJobSet(
			EntityCoreSugarRightArm arm) {
		MEMAIEntrySet<MEMPunchChargeAIJob> s = new MEMAIEntrySet<MEMPunchChargeAIJob>();
		{
			MEMAIStateWatcher condition = new MEMAIStateWatcher(
					arm.getStateMap());
			condition.addAllow(MEMAIPunchState.CHARGE);
			MEMAIEntry<MEMPunchChargeAIJob> entry = new MEMAIEntry<MEMPunchChargeAIJob>(
					condition, new MEMPunchChargeAIChargeJob(arm));
			s.add(entry);
		}
		return s;
	}

}
