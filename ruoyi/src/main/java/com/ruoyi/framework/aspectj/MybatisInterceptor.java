package com.ruoyi.framework.aspectj;

/**
 * @author :   ch
 * @version :   1.0
 * @ClassName :   MybatisInterceptor
 * @Description :   TODO(用一句话描述该类做什么)
 * @DATE :   Created in 16:43 2024/3/26
 * <pre>       Copyright: Copyright(c)2023     </pre>
 * <pre>       Company :   			           </pre>
 * Modification  History:
 * Date         Author        Version        Discription
 * --------------------------------------------------------------------------
 * 2024/3/26       ch        1.0       Why & What is modified: <修改原因描述> *
 */

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.reflect.ReflectUtils;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        if (args != null && args[0] instanceof MappedStatement) {
            MappedStatement ms = (MappedStatement) args[0];

            BoundSql boundSql = ms.getBoundSql(args[1]);
            SqlCommandType sqlCmdType = ms.getSqlCommandType();

            if (sqlCmdType == SqlCommandType.INSERT) {
                MetaObject metaObj = SystemMetaObject.forObject(boundSql);

                // 获取参数对象
                Object parameter = metaObj.getValue("parameterObject");
                // 设置公共字段值
                // ReflectUtils.getAccessibleField()
                ReflectUtils.setFieldValue(parameter,"createTime",DateUtils.getNowDate());
                ReflectUtils.setFieldValue(parameter,"createBy",SecurityUtils.getUsername());

                // 更新参数对象
                metaObj.setValue("parameterObject", parameter);
            } else if (sqlCmdType == SqlCommandType.UPDATE) {
                MetaObject metaObj = SystemMetaObject.forObject(boundSql);

                // 获取参数对象
                Object parameter = metaObj.getValue("parameterObject");
                // 设置公共字段值
                ReflectUtils.setFieldValue(parameter,"updateTime",DateUtils.getNowDate());
                ReflectUtils.setFieldValue(parameter,"updateBy",SecurityUtils.getUsername());



                // 更新参数对象
                metaObj.setValue("parameterObject", parameter);

            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以配置一些属性，如时间格式等
    }
}

