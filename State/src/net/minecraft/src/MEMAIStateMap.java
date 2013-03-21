package net.minecraft.src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.src.IMEMAIState.MEMAIStateType;

public class MEMAIStateMap {

	private final Map<MEMAIStateType, IMEMAIState> map = new HashMap<MEMAIStateType, IMEMAIState>();
	private final Map<MEMAIStateType, IMEMAIStateStatus> statusMap = new HashMap<MEMAIStateType, IMEMAIStateStatus>();
	private Set<MEMAIStateType> updated = new HashSet<MEMAIStateType>();

	public IMEMAIState getState(MEMAIStateType type) {
		if (!map.containsKey(type)) {
			map.put(type, type.defaultState);
		}
		return map.get(type);
	}

	public <T extends IMEMAIStateStatus> T getStateStatus(MEMAIStateType type) {
		IMEMAIStateStatus s = do_getStateStatus(type);
		if (s == null) {
			return null;
		}
		T status = null;
		try {
			status = (T) s;
		} catch (ClassCastException e) {
		}
		return status;
	}

	private IMEMAIStateStatus do_getStateStatus(MEMAIStateType type) {
		if (!statusMap.containsKey(type)) {
			statusMap.put(type, type.createStateStatus());
		}
		return statusMap.get(type);
	}

	public boolean isStateAs(IMEMAIState state) {
		return state == getState(state.getType());
	}

	public boolean hasState(MEMAIStateType type, Set<IMEMAIState> states) {
		return states.contains(getState(type));
	}

	public boolean isUpdated(MEMAIStateType type) {
		return updated.contains(type);
	}

	public void resetUpdated() {
		updated = new HashSet<MEMAIStateType>();
	}

	public boolean setState(IMEMAIState state) {
		if (isStateAs(state)) {
			return false;
		}
		MEMAIStateType type = state.getType();
		map.put(type, state);
		updated.add(type);
		return true;
	}
}
