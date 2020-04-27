package com.alimama.api.model;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 权限code
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 获取主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取权限code
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * 设置权限code
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    /**
     * 获取权限名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置权限名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
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
     * 获取修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}