package com.alimama.server.config;

import com.alimama.api.pipeLine.SimplePipeline;
import com.alimama.server.context.DataConformContext;
import com.alimama.server.valves.DataComformValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springboot配置bean
 * Created by PengWX on 2020/4/21.
 */
@Configuration
public class BeanConfig {

    @Autowired
    private DataComformValve dataComformValve;


    /**
     * 配置数据校验管道
     *
     * @return
     */
    @Bean
    public SimplePipeline<DataConformContext, Exception> submitPipeline() {

        SimplePipeline<DataConformContext, Exception> riskControlPipeline = new SimplePipeline<>();
//        riskControlPipeline.addValve(dataComformValve());
        riskControlPipeline.addValve(dataComformValve);
        return riskControlPipeline;

    }

   /* @Bean
    public Valve<DataConformContext, Exception> dataComformValve() {
        return new DataComformValve();
    }*/
}
