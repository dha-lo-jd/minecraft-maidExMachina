package net.minecraft.src;

public interface IMEMDevice {

	public abstract EntityLittleMaidevice getRidingDevice();

	public abstract EntityLittleMaid getRidingLittleMaid();

	public abstract EntityPlayer getRidingPlayer();

	public abstract boolean isRidingDevice();

	public abstract boolean isRidingLittleMaid();

	public abstract boolean isRidingPlayer();

}