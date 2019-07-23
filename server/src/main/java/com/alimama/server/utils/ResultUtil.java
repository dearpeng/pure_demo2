package com.alimama.server.utils;

import com.alimama.server.result.Result;
import com.alimama.server.result.ResultSupport;

/**
 *
 * Service层返回结果工具
 *
 * @author matisun
 * @date 2019/3/9 17:51
 * @see ResultSupport
 */
public class ResultUtil {

    /**
     * 请求是否成功
     *
     * @param result 返回结果
     * @return 成功：true 失败：false
     */
    public static boolean isResultSuccess(Result result) {
        return null != result && result.isSuccess();
    }

    /**
     * 请求不成功
     *
     * @param result
     * @return
     */
    public static boolean isResultNotSuccess(Result result) {
        return !isResultSuccess(result);
    }

    /**
     * 返回数据是否为空
     *
     * @param result 返回结果
     * @return 非空：true 空：false
     */
    public static boolean isModelNotNull(Result result) {
        return isResultSuccess(result) && null != result.getModel();
    }

    /**
     * 返回数据为空
     *
     * @param result
     * @return
     */
    public static boolean isModelNull(Result result) {
        return !isModelNotNull(result);
    }

    /**
     * 默认的Result
     *
     * @return Result
     */
    public static Result defaultResult() {
        return new ResultSupport();
    }

    /**
     * 成功的返回结果
     *
     * @param model 返回内容
     * @return Result
     */
    public static Result successResult(Object model) {
        Result result = defaultResult();
        result.setModel(model);
        return result;
    }

    /**
     * 失败的返回结果
     *
     * @param errorCode    错误返回码
     * @param errorMessage 错误信息
     * @return Result
     */
    public static Result failResult(String errorCode, String errorMessage) {
        return failResult(errorCode, errorMessage, false);
    }

    /**
     * 返回失败结果 errorCode会设置成-1
     *
     * @param errorMessage
     * @return
     */
    public static Result failResult(String errorMessage) {
        return failResult("-1", errorMessage);
    }

    /**
     * 失败的返回结果
     *
     * @param errorCode    返回码
     * @param errorMessage 错误信息
     * @param isI18n       返回码是否是国际化编码
     * @return Result
     */
    public static Result failResult(String errorCode, String errorMessage, boolean isI18n) {
        Result result = defaultResult();
        result.setSuccess(false);
        if (isI18n) {
            result.setI18nCode(errorCode);
        } else {
            result.setResultCode(errorCode);
        }
        result.setMessage(errorMessage);
        return result;
    }

    /**
     * 失败的返回结果
     *
     * @param errorCode    错误返回码
     * @param i18nCode     国际化错误码
     * @param errorMessage 错误信息
     * @return Result
     */
    public static Result failResult(String errorCode, String i18nCode, String errorMessage) {
        Result result = defaultResult();
        result.setSuccess(false);
        result.setResultCode(errorCode);
        result.setI18nCode(i18nCode);
        result.setMessage(errorMessage);
        return result;
    }


}
