package com.config.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 180465
 * @date 2020/7/5 19:44
 */

/**
 * nacos中的dataid组成的格式以及SpingBoot配置文件中的匹配规则为，完整的格式如下
 * ${prefix}-${spring-profile-active}.${file-extension}
 * prefix默认为spring.application.name的值，也可以通过spring.cloud.nacos.config.prefix来做配置
 * 当spring-profile-active为空时，则dataid的格式为${prefix}.${file-extension}
 * file-extension为配置内容的数据格式，可以通过spring.cloud.nacos.config.file-extension来配置，目前只支持properties和yaml两种类型
 * 最终通过@RefreshScope实现刷新
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
