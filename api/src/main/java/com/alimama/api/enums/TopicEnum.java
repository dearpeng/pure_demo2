package com.alimama.api.enums;

import java.io.Serializable;

/**
 * Created by PengWX on 2019/7/22.
 */
public enum TopicEnum implements Serializable {
    /*定义topic*/
    LOAN("cheguo.queues.loan.test");


    private String key;

    TopicEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
