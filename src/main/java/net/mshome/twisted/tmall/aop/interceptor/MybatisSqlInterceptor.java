package net.mshome.twisted.tmall.aop.interceptor;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.extern.slf4j.Slf4j;
import net.mshome.twisted.tmall.service.ISqlLogService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 自定义mybatis插件，实现输出实际执行sql语句
 *
 * @author tangjizhou
 * @date 2019-08-18
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
@Slf4j
public class MybatisSqlInterceptor extends AbstractSqlParserHandler implements Interceptor {

    /**
     * 获取配置中需要拦截的表
     */
    @Value("#{'${tmall.sync.tables:}'.split(',')}")
    private List<String> tableNames;

    @Lazy
    @Autowired
    private ISqlLogService sqlLogService;

    /**
     * 忽略插入sql_log表的语句
     */
    private static final String IGNORE_SQL_PREFIX = "insert into sql_log";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (CollectionUtils.isEmpty(tableNames)) {
            return invocation.proceed();
        }
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String sql = boundSql.getSql().replaceAll("\\s+", " ").toLowerCase();
        if (sql.toLowerCase().startsWith(IGNORE_SQL_PREFIX)) {
            return invocation.proceed();
        }

        List<ParameterMapping> parameterMappings = new ArrayList<>(boundSql.getParameterMappings());
        Object parameterObject = boundSql.getParameterObject();
        if (parameterMappings.isEmpty() && parameterObject == null) {
            log.warn("parameterMappings is empty or parameterObject is null");
            return invocation.proceed();
        }

        Configuration configuration = mappedStatement.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        try {
            this.sqlParser(metaObject);

            String parameter = "null";
            MetaObject newMetaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() == ParameterMode.OUT) {
                    continue;
                }
                String propertyName = parameterMapping.getProperty();
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    parameter = getParameterValue(parameterObject);
                } else if (newMetaObject.hasGetter(propertyName)) {
                    parameter = getParameterValue(newMetaObject.getValue(propertyName));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    parameter = getParameterValue(boundSql.getAdditionalParameter(propertyName));
                }
                // fixme 此处不严谨，若sql语句中有❓，则替换错位，encode一下就好。🤔️
                sql = sql.replaceFirst("\\?", parameter);
            }
            // 将拦截到的sql语句插入日志表中
            // sqlLogService.save(SqlLog.builder().executedSql(sql).build());
        } catch (Exception e) {
            log.error(String.format("intercept sql error: [%s]", sql), e);
        }
        return invocation.proceed();
    }

    /**
     * 获取参数
     *
     * @param param Object类型参数
     * @return 转换之后的参数
     */
    private static String getParameterValue(Object param) {
        if (param == null) {
            return "null";
        }
        if (param instanceof Number) {
            return param.toString();
        }
        String value = null;
        if (param instanceof String) {
            value = param.toString();
        } else if (param instanceof Date) {
            DateFormatUtils.format((Date) param, "yyyy-MM-dd HH:mm:ss");
        } else if (param instanceof IEnum) {
            value = String.valueOf(((IEnum) param).getValue());
        } else {
            value = param.toString();
        }
        return StringUtils.quotaMark(value);
    }


    @Override
    public Object plugin(Object o) {
        if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        }
        return o;
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
