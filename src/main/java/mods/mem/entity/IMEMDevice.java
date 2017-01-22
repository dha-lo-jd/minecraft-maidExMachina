package mods.mem.entity;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by dhaAsusAdmin on 2017/01/22.
 */
public interface IMEMDevice {
    public abstract EntityLittleMaidevice getRidingDevice();

    public abstract EntityLittleMaid getRidingLittleMaid();

    public abstract EntityPlayer getRidingPlayer();

    public abstract boolean isRidingDevice();

    public abstract boolean isRidingLittleMaid();

    public abstract boolean isRidingPlayer();
}
