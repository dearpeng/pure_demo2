package com.alimama.api.valve;

import java.util.List;

/**
 * Date: 2019-11-6
 * JDK: 1.8
 */
public abstract class BaseValveChain<V> {
    protected List<V> valves = null;

    protected int index = 0;

    protected int size;

    public BaseValveChain(List<V> valves) {
        this.valves = valves;
        this.size = valves.size();
    }

    public BaseValveChain(List<V> valves, int index) {
        this(valves);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
