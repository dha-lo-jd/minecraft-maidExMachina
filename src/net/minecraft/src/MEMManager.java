package net.minecraft.src;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MEMManager {

	private final Map<String, EntityCoreSugar> MEM_ACTIVATED_MAP = new HashMap<String, EntityCoreSugar>();
	private final Map<EntityCoreSugar, String> MEMID_MAP = new HashMap<EntityCoreSugar, String>();

	private File fileDir;

	public MEMManager(World world) throws IOException {
		super();
		this.fileDir = FileStrageSupport.getTopLevelWorldSaveDirectory(world);
		if (fileDir == null) {
			throw new IOException();
		}
	}
}
