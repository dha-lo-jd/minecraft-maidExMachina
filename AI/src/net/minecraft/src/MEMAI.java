package net.minecraft.src;

import java.util.HashSet;
import java.util.Set;

public class MEMAI {

	protected final Set<MEMAIEntrySet<? extends IMEMAIJob>> entries;
	protected final Set<MEMAIStateUpdater> stateUpdaterSet;

	public final EntityCoreSugarBody core;

	private static Set<MEMAIStateUpdater> initStateUpdaterSet(
			EntityCoreSugarBody core) {
		Set<MEMAIStateUpdater> s = new HashSet<MEMAIStateUpdater>();
		s.add(new MEMAIRidingStateUpdater(core));
		s.add(new MEMAILookingStateUpdater(core));
		s.add(new MEMAIViewerPositionStateUpdater(core));
		return s;
	}

	protected Set<IMEMAIJob> prevTickAIJobs = new HashSet<IMEMAIJob>();
	protected Set<IMEMAIJob> currentTickAIJobs = new HashSet<IMEMAIJob>();

	public MEMAI(EntityCoreSugarBody core) {
		this.core = core;
		stateUpdaterSet = initStateUpdaterSet(core);
		entries = new HashSet<MEMAIEntrySet<?>>();
	}

	public void addEntries(MEMAIEntrySet<? extends IMEMAIJob> jobSet) {
		entries.add(jobSet);
	}

	public void updateStates() {
		for (MEMAIStateUpdater updater : stateUpdaterSet) {
			core.updateState(updater);
		}
	}

	public void updateAIEntry() {
		prevTickAIJobs = currentTickAIJobs;
		currentTickAIJobs = new HashSet<IMEMAIJob>();
		for (MEMAIEntrySet<? extends IMEMAIJob> entriesSet : entries) {
			updateAIEntry(entriesSet);
		}
		for (IMEMAIJob job : prevTickAIJobs) {
			if (!currentTickAIJobs.contains(job)) {
				job.endExecute();
			}
		}
	}

	public void updateAIEntry(MEMAIEntrySet<? extends IMEMAIJob> aiSet) {
		for (MEMAIEntry<? extends IMEMAIJob> entry : aiSet) {
			IMEMAIJob job = entry.job;
			if (entry.shouldExecute()) {
				if (!prevTickAIJobs.contains(job)) {
					job.startExecute();
				}
				if (job.executeTick()) {
					currentTickAIJobs.add(job);
					break;
				}
			} else {
				if (!prevTickAIJobs.contains(job)) {
					job.suspendTick();
				}
			}
		}
	}
}
