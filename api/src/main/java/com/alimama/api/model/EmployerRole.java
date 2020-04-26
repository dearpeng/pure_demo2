package com.alimama.api.model;

import java.io.Serializable;
import java.util.Date;

public class EmployerRole implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 分公司ID
     */
    private Integer branchCompanyId;

    /**
     * 业务组ID
     */
    private Integer businessGroupId;

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
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置姓名
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 获取角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取分公司ID
     */
    public Integer getBranchCompanyId() {
        return branchCompanyId;
    }

    /**
     * 设置分公司ID
     */
    public void setBranchCompanyId(Integer branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }

    /**
     * 获取业务组ID
     */
    public Integer getBusinessGroupId() {
        return businessGroupId;
    }

    /**
     * 设置业务组ID
     */
    public void setBusinessGroupId(Integer businessGroupId) {
        this.businessGroupId = businessGroupId;
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