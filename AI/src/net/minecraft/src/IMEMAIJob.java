package net.minecraft.src;

public interface IMEMAIJob {

	abstract public void startExecute();

	abstract public boolean executeTick();

	abstract public void suspendTick();

	abstract public void endExecute();

}
