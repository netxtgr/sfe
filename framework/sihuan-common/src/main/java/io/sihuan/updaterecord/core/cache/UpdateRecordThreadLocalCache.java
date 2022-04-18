/*

 */

package io.sihuan.updaterecord.core.cache;

import io.sihuan.updaterecord.core.vo.UpdateRecordItemVo;
import io.sihuan.updaterecord.core.vo.UpdateRecordVo;

/**
 * 修改记录线程池缓存工具
 *
 * @author liujiangtao
 * @date 2022-03-17
 **/
public class UpdateRecordThreadLocalCache {

    private static ThreadLocal<UpdateRecordVo> threadLocal = new ThreadLocal<>();

    /**
     * 获取线程池中缓存的修改记录对象
     *
     * @return
     */
    public static UpdateRecordVo get() {
        return threadLocal.get();
    }

    /**
     * 缓存修改记录对象到线程池中
     *
     * @param updateRecordVo
     */
    public static void set(UpdateRecordVo updateRecordVo) {
        threadLocal.set(updateRecordVo);
    }

    /**
     * 移除当前线程池变量
     */
    public static void remove() {
        threadLocal.remove();
    }

    /**
     * 添加修改记录项
     *
     * @param updateRecordItemVo
     */
    public static void add(UpdateRecordItemVo updateRecordItemVo) {
        get().getUpdateRecordItemVos().add(updateRecordItemVo);
    }

    /**
     * 获取请求提交ID
     * 同一个请求，commitId一致
     *
     * @return
     */
    public static String getCommitId() {
        return get().getCommitId();
    }

    /**
     * 线程池中的变量是否为空
     *
     * @return
     */
    public static boolean empty() {
        return get() == null;
    }

}
