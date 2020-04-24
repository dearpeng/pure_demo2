package com.alimama.api.validations;

/**
 * Created by PengWX on 2020/4/23.
 */
//参照javax.validation.constraints包下的NotNull注解进行自定义自己的注解，添加以下四个注解

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsEmailValidator.class})// 通过Constraint注解器中的validatedBy参数，调用相应的类进行规则判定
public @interface EmailValidation {
    String message() default "email 格式错误"; //对email进行验证，验证不通过时返回的消息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //参照javax.validation.constraints;包下的NotNull注解进行自定义自己的注解，以上三个函数是必须的

    // 自定义一个函数，设置是否允许参数为空
    boolean notNull() default true; //默认设置为不允许为空
}
