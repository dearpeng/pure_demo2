package com.alimama.api.valve;

/**
 * 管道链
 * Date: 2015-11-6
 * JDK: 1.8
 */
public interface ValveChain<Context, E extends Exception> {

    public void handleNext(Context context) throws E;

}
