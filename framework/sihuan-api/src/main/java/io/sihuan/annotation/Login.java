/**

 *
 * 版权所有，侵权必究！
 */

package io.sihuan.annotation;

import java.lang.annotation.*;

/**
 * 登录效验
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
