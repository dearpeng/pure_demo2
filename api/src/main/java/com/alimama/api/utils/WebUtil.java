package com.alimama.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alimama.api.myDataPage.DataPage;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;


/**
 * web工具类
 *
 * @author shawn.du
 */
public class WebUtil {
    /**
     * 成功标识
     */
    public static final int OK = 10000;
    /**
     * 成功标识,需要给前端提示
     */
    public static final int REMIND = 10001;
    //错误标识
    public static final int ERROR = 20000;
    //错误标识,需要前端用户确认
    public static final int CONFIRM = 20001;
    public static final int TIMEOUT = 30000;

    public static final String CONTEXT_TYPE = "application/json; charset=utf-8";

    public static final String JSON_RESULT_MESSAGE = "message";
    public static final String JSON_RESULT_STATUS_CODE = "code";
    public static final String JSON_RESULT_STATUS_CODE_TWO = "status";
    public static final String JSON_RESULT_DATA = "data";
    public static final String JSON_RESULT_LIST = "list";
    public static final String JSON_RESULT_TOTAL = "totalItem";

    public static final String DATE_FMT = "yyyy-MM-dd";

    /**
     * 生成一个失败的JSON对象
     *
     * @param message 失败消息
     * @return String
     */
    public static String getFailureJson(String message) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, ERROR);
        return resultJson.toJSONString();
    }

    public static String getTimeOutJson() {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, "提交超时，请稍候再试");
        resultJson.put(JSON_RESULT_STATUS_CODE, ERROR);
        return resultJson.toJSONString();
    }

    public static <T> String getSuccessJson(Object data) {
        return getSuccessJson(null, data, null);
    }

    public static String getFailureJson(String message, int code) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, code);
        return resultJson.toJSONString();
    }

    public static String getSuccessJson(String message, int code, String status) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, code);
        resultJson.put(JSON_RESULT_STATUS_CODE_TWO, status);
        return resultJson.toJSONString();
    }
    public static String getSuccessJson( Integer code, Object data) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_STATUS_CODE, code);
        resultJson.put(JSON_RESULT_DATA, data);
        return resultJson.toJSONString();
    }

    /**
     * 生成一个成功的json
     *
     * @return
     */
    public static String getSuccessJson() {
        return getSuccessJson(null, null, null);
    }

    public static <T> String getSuccessJson(DataPage<T> data) {
        return getSuccessJson(null, data.getData(), data.getTotalItem());
    }

    public static <T> String getSuccessJson(DataPage<T> data, String dateFmt) {
        return getSuccessJson(null, data.getData(), data.getTotalItem(), dateFmt);
    }


    public static String getFailureJson(String message, int code, Object data) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, code);
        resultJson.put(JSON_RESULT_DATA, data);
        return JSON.toJSONString(resultJson, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static <T> String getSuccessJson(Object data, String dateFmt) {
        return getSuccessJson(null, data, null, dateFmt);
    }

    public static String getSuccessJson(String message, Object data, Integer totalItem) {
        return getSuccessJson(message, data, totalItem, null);
    }

    public static String getSuccessJsonByMap(Map<String, Object> map) {
        return getSuccessJsonByMap(map, null);
    }

    public static String getSuccessJsonByMap(Map<String, Object> map, String dateFmt) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_STATUS_CODE, OK);
        if (null == map.get("data")) {
            return resultJson.toJSONString();
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            resultJson.put(entry.getKey(), entry.getValue());
        }
        if (dateFmt == null) {
            return JSON.toJSONString(resultJson, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        }
        SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFmt));
        return JSON.toJSONString(resultJson, mapping, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String getSuccessJson(String message, Object data, Integer totalItem, String dateFmt) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, OK);
        resultJson.put(JSON_RESULT_DATA, data);
        resultJson.put(JSON_RESULT_TOTAL, totalItem);

        if (data == null){
            return resultJson.toJSONString();
        }

        if (dateFmt == null) {
            return JSON.toJSONString(resultJson, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        }

        SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFmt)); //yyyy-MM-dd
        return JSON.toJSONString(resultJson, mapping, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static Integer getInteger(ServletRequest request, String paraName) {
        String value = request.getParameter(paraName);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public static Date getDate(ServletRequest request, String paraName) {
        String value = request.getParameter(paraName);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return DateUtils.strToDate(value);
        } catch (ParseException e) {
            return null;
        }
    }


    public static Long getLong(ServletRequest request, String paraName) {
        String value = request.getParameter(paraName);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String generalBusiNo(String prefix_, int randomBit) {
        return prefix_ + DateUtils.format(DateUtils.getCurDate(), "yyMMddHHmmssSS")
                + String.format("%0" + randomBit + "d", (int) Math.floor(Math.random() * 1000));
    }

    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static Builder newBuilder() {
        return Builder.newInstance();
    }

    public static class Builder implements Serializable {

        private static final long serialVersionUID = -6617068277182687260L;

        private String message;
        private Object data;
        private Integer totalItem;
        private String dateFmt;
        private int code;

        private Builder() {
        }

        private static Builder newInstance() {
            return new Builder();
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder totalItem(Integer totalItem) {
            this.totalItem = totalItem;
            return this;
        }

        public Builder dataFmt(String dataFmt) {
            this.dateFmt = dataFmt;
            return this;
        }

        public <T> Builder DataPage(DataPage<T> dataPage) {
            this.data = dataPage.getData();
            this.totalItem = dataPage.getTotalItem();
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public String getSuccessJson() {
            return WebUtil.getSuccessJson(message, data, totalItem, dateFmt);
        }

        public String getFailureJson() {
            if (code == 0) {
                code = ERROR;
            }
            return WebUtil.getFailureJson(message, code);
        }
    }
}
