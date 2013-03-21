package net.minecraft.src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.src.IMEMAIState.MEMAIStateType;

public class MEMAIStateWatcher implements IMEMAICondition {

	Map<MEMAIStateType, Set<IMEMAIState>> allow = new HashMap<MEMAIStateType, Set<IMEMAIState>>();
	Map<MEMAIStateType, Set<IMEMAIState>> deny = new HashMap<MEMAIStateType, Set<IMEMAIState>>();

	protected MEMAIStateMap stateMap;

	public MEMAIStateWatcher(MEMAIStateMap stateMap) {
		this.stateMap = stateMap;
	}

	public void addAllow(IMEMAIState state) {
		MEMAIStateType type = state.getType();
		if (!allow.containsKey(type)) {
			allow.put(type, new HashSet<IMEMAIState>());
		}
		allow.get(type).add(state);
	}

	public void addDeny(IMEMAIState state) {
		MEMAIStateType type = state.getType();
		if (!deny.containsKey(type)) {
			deny.put(type, new HashSet<IMEMAIState>());
		}
		deny.get(type).add(state);
	}

	@Override
	public boolean shouldExecute() {
		for (Entry<MEMAIStateType, Set<IMEMAIState>> entry : allow.entrySet()) {
			if (!stateMap.hasState(entry.getKey(), entry.getValue())) {
				return false;
			}
		}
		for (Entry<MEMAIStateType, Set<IMEMAIState>> entry : deny.entrySet()) {
			if (stateMap.hasState(entry.getKey(), entry.getValue())) {
				return false;
			}
		}
		return true;
	}

}
