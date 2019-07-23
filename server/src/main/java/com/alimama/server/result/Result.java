package com.alimama.server.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 车盛通用返回结果
 *
 * @author
 * @date 2019/3/9 17:42
 */
public interface Result<T> extends Serializable {

    /**
     * 设置请求成功标志。
     *
     * @param success 成功标志
     */
    void setSuccess(boolean success);

    /**
     * 请求是否成功。
     *
     * @return 如果成功，则返回<code>true</code>
     */
    boolean isSuccess();

    /**
     * 当出现业务异常或系统异常时，返回相应的错误码
     *
     * @return 返回码
     */
    String getResultCode();


    /**
     * 设置返回码
     *
     * @param code
     */
    void setResultCode(String code);


    /**
     * 当出现业务异常或系统异常时，返回相应的国际化错误码
     *
     * @return 返回码
     */
    String getI18nCode();


    /**
     * 设置国际化返回码
     *
     * @param i18nCode
     */
    void setI18nCode(String i18nCode);


    /**
     *
     * @return 返回提示文案
     */
    String getTip();


    /**
     * 设置提示文案
     *
     * @param tip
     */
    void setTip(String tip);

    /**
     * 取得model对象
     *
     * @return model对象
     */
    T getModel();

    /**
     * 设置model对象。
     *
     * @param model model对象
     */
    void setModel(T model);

    /**
     * 设置model对象。
     *已废弃，所有对象及列表都置入Model中
     * @param models model对象
     */
    @Deprecated
    void setModels(List<T> models);

    /**
     * 取得所有model对象。
     *
     * @return model对象表
     */
    @Deprecated
    List<T> getModels();

    /**
     * 返回信息
     * @return
     */
    String getMessage();

    /**
     * 返回错误信息
     * @param message
     */
    void setMessage(String message);

    /**
     * 如需要总记录数，单独调用接口获取
     * @param totalRecord
     */
    @Deprecated
    void setTotalRecord(int totalRecord);

    /**
     * * 如需要总记录数，单独调用接口获取
     * 返回记录总数
     * @return
     */
    @Deprecated
    int getTotalRecord();


    void setReplaces(Map<String, Map<String, String>> replaces);

    void setReplace(String multiCode, Map<String, String> replace);

    Map<String, Map<String, String>> getReplaces();

    Map<String, String> getReplace(String multiCode);

}

