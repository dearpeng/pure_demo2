package com.alimama.server.commands;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PengWX on 2020/4/21.
 */
public class Commands2 implements Command {

    private static final Logger logger = LoggerFactory.getLogger(Commands2.class);


    @Override
    public boolean execute(Context context) throws Exception {
        Object v1 = context.get("k1");
        logger.info("do something by Commands2... params is {}", JSONObject.toJSONString(context));
        if(v1 != null){
            int IntV1 = (Integer) v1;
            context.put("k1", IntV1+1);
        }else{
            throw new Exception("数据为空2!");
        }
        return false;
    }
}
