package com.alimama.api.pipeLine;

import com.alimama.api.valve.SimpleValveChain;
import com.alimama.api.valve.Valve;
import com.alimama.api.valve.ValveChain;

/**
 * Created by PengWX on 2020/4/21.
 */
public class SimplePipeline<Context, E extends Exception> extends BasePipeline<Valve<Context, E>> implements PipeLine.Pipeline<Context, E> {
    /**
     * 管道处理。
     *
     * @param context 阀门上下文.
     */
    @Override
    public void handle(Context context)
            throws E {
        ValveChain<Context, E> chain = new SimpleValveChain<Context, E>(getValves());
        chain.handleNext(context);
    }
    @Override
    public void handle(Context context, ValveChain<Context, E> chain) throws E {
        handle(context);
        chain.handleNext(context);
    }
}
