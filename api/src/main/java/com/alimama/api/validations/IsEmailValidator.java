package com.alimama.api.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PengWX on 2020/4/23.
 */
public class IsEmailValidator implements ConstraintValidator<EmailValidation, String> {
    private boolean notNull = false;

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        //初始化这个方法可以取到EmailValidation这个注解以及其中的内容
        notNull = constraintAnnotation.notNull();//将注解器中的内容取出
    }

    // value为用户输入的内容，实现ConstraintValidator的这个方法,通过这个方法进行对value校验是否合法
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //不允许为空
        if (notNull) {
            return isEmail(value);
        } else {//允许为空
            if (value == null) {
                return true;
            } else {
                return isEmail(value);
            }
        }
    }

    // 进行email匹配，使用正则表达式匹配
    public static final Pattern email_pattern = Pattern.compile("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");

    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = email_pattern.matcher(email);
        return matcher.matches();
    }
}
