package com.alimama.api.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     * 主ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 说明
     */
    private String note;

    /**
     * 修改时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 使用状态
     */
    private Integer state;

    /**
     * 获取主ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取说明
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置说明
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * 获取修改时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置修改时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置更新时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * 获取使用状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置使用状态
     */
    public void setState(Integer state) {
        this.state = state;
    }
}