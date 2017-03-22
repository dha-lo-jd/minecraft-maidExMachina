package mods.mem.build;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

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
