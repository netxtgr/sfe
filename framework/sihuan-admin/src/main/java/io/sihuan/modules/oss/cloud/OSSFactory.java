/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.oss.cloud;


import io.sihuan.common.utils.ConfigConstant;
import io.sihuan.common.utils.Constant;
import io.sihuan.common.utils.SpringContextUtils;
import io.sihuan.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
 * @author ljt liujiangtao@sihuanpharm.com
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
