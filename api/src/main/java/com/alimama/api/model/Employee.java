package com.alimama.api.model;




import com.alibaba.fastjson.annotation.JSONField;
import com.alimama.api.validations.EmailValidation;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.MessageInterpolator;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    /**
     * 主键
     */
    @NotNull(message = "用户主键不能为空!")
    private Long id;

    /**
     * 名字
     */
    @NotNull(message = "用户姓名不能为空!")
    private String lastName;

    /**
     * 性别 0:女 1:男
     */
//    @NotNull(message = "用户性别不能为空!")
    private Byte gender;

    /**
     * EmailValidation 这个是自定义注解
     * 邮箱
     */
    @NotNull(message = "邮编不能为空!")
    @EmailValidation(message = "会不会填邮编啊")
    private String email;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 生日
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date birth;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

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
     * 获取名字
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置名字
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * 获取性别 0:女 1:男
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置性别 0:女 1:男
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 获取邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取部门id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置部门名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    /**
     * 获取生日
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置生日
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}