/**
 * 版权所有，侵权必究！
 */

package io.sihuan.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
