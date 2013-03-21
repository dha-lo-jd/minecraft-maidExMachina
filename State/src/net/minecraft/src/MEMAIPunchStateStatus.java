package net.minecraft.src;

public class MEMAIPunchStateStatus implements
		IMEMAIStateStatus<MEMAIPunchState> {

	public static MEMAIPunchStateStatus getStatus(MEMAIStateMap stateMap) {
		return stateMap.getStateStatus(MEMAIPunchState.TYPE);
	}

	public static final int CHARGE_MAX_OFFSET = 4;
	public static final int CHARGE_MAX = 10000;
	public static final int CHARGE_SPEED = 300;

	protected int charge = 0;

	protected int chargeSpeed = CHARGE_SPEED;
	protected int chargingPos = 0;

	public MovingObjectPosition punchTarget;

	public static final float ROLL_SPEED_MAX = 20;
	public static final float ROLL_SPEED_ACCEL = 0.3F;
	public static final int DRILL_REACH_LENGTH = 10;
	public static final int DRILL_REACH_MAX = 10000;
	public static final int DRILL_MOVE_SPEED = 200;

	protected float rollingRotationAngle;
	protected float rollingRotationSpeed;
	protected float drillReachLength;
	public MovingObjectPosition drillTarget;

	public void rolling(float angle) {
		rollingRotationAngle += angle;
		rollingRotationAngle = RotateSupport.fixAngle(rollingRotationAngle, 0);
	}

	public void addCharge(int c) {
		charge += c;
		if (charge > CHARGE_MAX) {
			charge = CHARGE_MAX;
		}
		if (charge > chargingPos) {
			chargingPos = charge;
		}
	}
}