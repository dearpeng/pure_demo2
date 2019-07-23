package com.alimama.server.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2019/3/9 17:48
 */
public class ResultSupport<T> implements Result<T> {

    private static final long serialVersionUID = 4661096805690919752L;

    private boolean success = true;

    private String resultCode;

    private String i18nCode;

    private String message;

    private String tip;

    private T model;

    private int totalRecord;

    /**
     * 国际化占位符以及对应的值
     */
    private Map<String, Map<String, String>> replaces = new HashMap<>();

    private List<T> models = new ArrayList<T>();

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public T getModel() {
        return model;
    }

    @Override
    public int getTotalRecord() {
        return totalRecord;
    }

    @Override
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * 创建一个result。
     */
    public ResultSupport() {
    }

    /**
     * 创建一个result。
     *
     * @param success 是否成功
     */
    public ResultSupport(boolean success) {
        this.success = success;
    }

    public ResultSupport(boolean success, String tip) {
        this.success = success;
        this.tip = tip;
    }

    public ResultSupport(boolean success, String resultCode, String message) {
        this.success = success;
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultSupport(String resultCode, String message) {
        this.success = Boolean.FALSE;
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultSupport(ResultCode resultCode) {
        this.success = false;
        this.resultCode = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    @Override
    public boolean isSuccess() {
        return success;
    }


    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the resultCode
     */
    @Override
    public String getResultCode() {
        if (!isSuccess() && isBlank(resultCode)) {
            return BaseResultCode.SYSTEM_DEFAULT.getCode();
        }
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    @Override
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String getI18nCode() {
        return this.i18nCode;
    }

    @Override
    public void setI18nCode(String code) {
        this.i18nCode = code;
    }

    @Override
    public String getTip() {
        return this.tip;
    }

    @Override
    public Map<String, Map<String, String>> getReplaces() {
        return this.replaces;
    }

    @Override
    public Map<String, String> getReplace(String multiCode) {
        return this.replaces.get(multiCode);
    }

    @Override
    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public List<T> getModels() {
        return models;
    }

    @Override
    public void setModels(List<T> models) {
        this.models = models;
    }

    @Override
    public void setModel(T model) {
        this.model = model;
    }

    @Override
    public void setReplaces(Map<String, Map<String, String>> replaces) {
        if (replaces != null && !replaces.isEmpty()) {
            this.replaces.putAll(replaces);
        }
    }

    @Override
    public void setReplace(String multiCode, Map<String, String> replace) {
        this.replaces.put(multiCode, replace);
    }

}
