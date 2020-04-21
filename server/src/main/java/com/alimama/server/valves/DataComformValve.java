package com.alimama.server.valves;

import com.alimama.api.valve.ValveChain;
import com.alimama.server.context.DataConformContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Package: com.cgw360.cls.bss.flow.common.valve
 * @Author: pwx
 * @Date: 2020/3/30 6:16 PM
 */
@Component("dataComformValve")
public class DataComformValve extends AbstractDataConformValve<DataConformContext, Exception> {

    /**
     *
     * @param chain
     * @throws Exception
     */
    @Override
    public void handle(DataConformContext context, ValveChain<DataConformContext, Exception> chain) throws Exception {
//        logger.info(() -> "数据校验入参:{}", JSON.toJSONString(context));
        if (Objects.isNull(context.getBusinessId())) {
            throw new Exception("业务id不能为空");
        }
        chain.handleNext(context);
    }
}
