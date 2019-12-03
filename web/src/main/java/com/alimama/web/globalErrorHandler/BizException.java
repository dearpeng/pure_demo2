package com.alimama.web.globalErrorHandler;

/**
 * 业务系统相关的异常
 *
 * @author pwx
 * @date 2019/3/9 17:36
 */
public class BizException extends RuntimeException {

    /**
     * 异常业务编码
     */
    private String code;

    /**
     * 国际化异常编码
     */
    private String i18nCode;

    /**
     * <code>[serialVersionUID]</code>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 默认异常构造器.
     */
    public BizException() {
        super();
    }

    /**
     * 根据异常信息和原生异常构造对象.
     *
     * @param code    错误码
     * @param message 异常信息.
     * @param cause   原生异常.
     */
    public BizException(final String code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }


    public BizException(final String code, final String i18nCode, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.i18nCode = i18nCode;
    }

    /**
     * 根据异常信息和原生异常构造对象.
     *
     * @param message 异常信息.
     * @param cause   原生异常.
     */
    public BizException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * 根据异常信息构造对象.
     *
     * @param message 异常信息.
     */
    public BizException(final String message) {
        super(message);
    }


    /**
     * 根据异常构造业务对象，设置 编码及 消息
     *
     * @param code
     * @param message
     * @author zxh 槟榔
     */
    public BizException(final String message, final String code) {
        super(message);
        this.code = code;
    }

    public BizException(final String message, final String code, final String i18nCode) {
        super(message);
        this.code = code;
        this.i18nCode = i18nCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    /**
     * 根据原生异常构造对象.
     *
     * @param cause 原生异常.
     */
    public BizException(final Throwable cause) {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
