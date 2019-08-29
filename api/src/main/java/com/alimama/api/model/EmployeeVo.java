package com.alimama.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PengWX on 2019/8/29.
 */
@Data
public class EmployeeVo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名字
     */
    private String realName;

    /**
     * 性别 0:女 1:男
     */
    private Byte gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门id
     */
    private Long depId;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 盐
     */
    private String salt;
}
