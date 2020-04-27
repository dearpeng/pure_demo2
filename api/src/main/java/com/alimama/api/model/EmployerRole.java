package com.alimama.api.model;

import java.io.Serializable;
import java.util.Date;

public class EmployerRole implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 用户employer表ID
     */
    private Long employerId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 说明
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 获取
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户employer表ID
     */
    public Long getEmployerId() {
        return employerId;
    }

    /**
     * 设置用户employer表ID
     */
    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    /**
     * 获取角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
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
}