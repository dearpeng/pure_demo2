package com.alimama.api.model;

import java.io.Serializable;
import java.util.Date;

public class RolePermission implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 是否启用(1:是 0:否)
     */
    private Integer status;

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
     * 获取权限id
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取是否启用(1:是 0:否)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否启用(1:是 0:否)
     */
    public void setStatus(Integer status) {
        this.status = status;
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