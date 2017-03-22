package mods.mem.ai;

public interface IMEMAIJob {
    abstract public void startExecute();

    abstract public boolean executeTick();

    abstract public void suspendTick();

    abstract public void endExecute();
    //と見せかけて何もしていない！
}
