package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class MEMAIViewerPositionStateUpdater extends
		MEMAIStateUpdater<MEMAIViewerPositionState> {

	public MEMAIViewerPositionStateUpdater(EntityCoreSugarBody core) {
		super(core, MEMAIViewerPositionState.OUT);
	}

	@Override
	public MEMAIViewerPositionState updateState() {

		Minecraft mc = ModLoader.getMinecraftInstance();
		if (core.isEntityInCore(mc.renderViewEntity)) {
			return MEMAIViewerPositionState.IN;
		}
		return MEMAIViewerPositionState.OUT;
	}

}
