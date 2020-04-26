package com.alimama.web.selfExceptions;

/**
 * Created by jx on 2019/2/17.
 */
public class UserNotExistExcception extends RuntimeException {
    public UserNotExistExcception(){
        super("用户不存在");
    }
}
