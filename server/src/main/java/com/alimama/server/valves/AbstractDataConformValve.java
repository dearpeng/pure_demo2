package com.alimama.server.valves;


import com.alimama.api.valve.Valve;
/**
 * 抽象数据校验管道
 */
public abstract class AbstractDataConformValve<Context, E extends Exception> implements Valve<Context, E> {

}
