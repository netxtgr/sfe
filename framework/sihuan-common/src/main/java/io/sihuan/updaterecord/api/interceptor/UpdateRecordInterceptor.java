/*

 */

package io.sihuan.updaterecord.api.interceptor;

import io.sihuan.updaterecord.api.vo.TableVo;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * 修改记录 mybatis 拦截器支持接口
 *
 * @author liujiangtao
 * @date 2019-11-22
 **/
public interface UpdateRecordInterceptor extends Interceptor {

    /**
     * 获取修改方法的参数
     *
     * @param parameterObject
     * @return
     */
    Object getParameterObject(Object parameterObject);

    /**
     * 获取表信息
     *
     * @param cls
     * @return
     */
    TableVo getTableVo(Class<?> cls);

    /**
     * 获取修改之后的Map集合
     *
     * @param cls
     * @param object
     * @param parameterMappings
     * @param tableVo
     * @return
     */
    Map<String, Object> getUpdateAfterMap(Class<?> cls, Object object, List<ParameterMapping> parameterMappings, TableVo tableVo);

    /**
     * 获取列名称
     *
     * @param cls
     * @param object
     * @param propertyName
     * @return
     */
    String getColumnName(Class<?> cls, Object object, String propertyName);

    /**
     * 获取版本列名
     *
     * @return
     */
    String getVersionColumn();

    /**
     * 获取版本值
     *
     * @param map
     * @return
     */
    Object getVersionValue(Map<String, Object> map);

    /**
     * 获取修改之后的版本值
     *
     * @param beforeVersionValueObject
     * @param afterMap
     * @return
     */
    Object getAfterVersionValue(Object beforeVersionValueObject, Map<String, Object> afterMap);

}