package com.alimama.api.valve;

import java.util.List;

/**
 * Date: 2015-11-6
 * JDK: 1.8
 */
public class SimpleValveChain<Context, E extends Exception> extends BaseValveChain<Valve<Context, E>> implements ValveChain<Context, E> {

    public SimpleValveChain(List<Valve<Context, E>> valves) {
        super(valves);
    }

    @Override
    public void handleNext(Context context) throws E {
        if (index < size) {
            valves.get(index++).handle(context, this);
        }
    }


}
