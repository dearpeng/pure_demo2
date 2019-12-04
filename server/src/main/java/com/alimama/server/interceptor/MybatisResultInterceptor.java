package com.alimama.server.interceptor;

/**
 * Created by PengWX on 2019/12/3.
 */

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义mybatis拦截器,格式化SQL输出,
 *
 * @author zengsong
 * @version 1.0
 * @description 只对查询和更新语句做了格式化，其它语句需要手动添加
 * @date 2019/5/30 10:17
 **/
@Intercepts({@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "query", args = {MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class, org.apache.ibatis.session.ResultHandler.class})})
@Component
public class MybatisResultInterceptor implements Interceptor {

    private final static Logger logger = LoggerFactory.getLogger(MybatisResultInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            //com.alimama.server.mapper.EmployeeMapper.selectByExample  sql语句的sql全限定名
            String sqlId = mappedStatement.getId();
            //查询的sql,参数依然是?代替
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            Configuration configuration = mappedStatement.getConfiguration();
            String sql = getSql(configuration, boundSql, sqlId);
            logger.info("查询使用是sql语句:" + sql);
        } catch (Exception localException) {
        }
        return invocation.proceed();
    }

    /**
     * 拼接了xml中sql的id(com.alimama.server.mapper.EmployeeMapper.selectByExample)和sql语句和参数合并一起的sql
     *
     * @param configuration
     * @param boundSql
     * @param sqlId
     * @return
     */
    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
        String sql = showSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append(sqlId);
        str.append(":");
        str.append(sql);
        return str.toString();
    }

    private static String getParameterValue(Object obj) {//转换请求参数类型成string
        String value = null;
        if ((obj instanceof String)) {
            value = "'" + obj.toString() + "'";
        } else if ((obj instanceof Date)) {
            DateFormat formatter = DateFormat.getDateTimeInstance(2, 2, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else if (obj != null) {
            value = obj.toString();
        } else {
            value = "";
        }
        return value;
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();//获取参数对象
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");//去除sql中的\s符号
        MetaObject metaObject;
        if ((CollectionUtils.isNotEmpty(parameterMappings)) && (parameterObject != null)) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        //查询参数
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        //将sql和参数拼接起来
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        sql = sql.replaceFirst("\\?", "缺失");
                    }
                }
            }
        }
        return sql;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
