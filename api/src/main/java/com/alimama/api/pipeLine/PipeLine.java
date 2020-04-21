package com.alimama.api.pipeLine;

import com.alimama.api.valve.Valve;

/**
 * Created by PengWX on 2020/4/21.
 */
public class PipeLine {

    /**
     * Date: 2015-11-6
     * JDK: 1.8
     * 管道接口
     */
    public interface Pipeline<Context, E extends Exception> extends Valve<Context, E> {
        public void handle(Context context)
                throws E;
    }

}
