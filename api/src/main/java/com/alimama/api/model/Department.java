package com.alimama.api.model;

import java.io.Serializable;

public class Department implements Serializable {
    /**
     * 部门id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 获取部门id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置部门id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}