package mods.mem.ai;

/**
 * Created by dhaAsusAdmin on 2017/02/21.
 */
public interface IMEMAIJob {
    abstract public void startExecute();

    abstract public boolean executeTick();

    abstract public void suspendTick();

    abstract public void endExecute();
}
