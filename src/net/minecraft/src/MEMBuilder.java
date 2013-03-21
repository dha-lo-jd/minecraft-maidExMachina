package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;


public class MEMBuilder {
	public interface IMEMCoreChildPartsFactory {
		String getName();

		EntityCoreSugarChildParts createChildParts(EntityCoreSugarParts parent,
				EntityCoreSugarParts core, World par1World, NBTTagCompound tag);
	}

	protected final Map<String, IMEMCoreChildPartsFactory> childPartsMap = new HashMap<String, IMEMCoreChildPartsFactory>();

	public void registFactory(IMEMCoreChildPartsFactory factory) {
		childPartsMap.put(factory.getName(), factory);
	}

	public EntityCoreSugarChildParts createChildParts(String name,
			EntityCoreSugarParts parent, EntityCoreSugarParts core,
			World par1World, NBTTagCompound tag) {
		if (childPartsMap.containsKey(name)) {
			return childPartsMap.get(name).createChildParts(parent, core,
					par1World, tag);
		}
		return null;
	}

}
