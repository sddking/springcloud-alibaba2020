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
 *
 * nacos中的dataid组成的格式以及SpingBoot配置文件中的匹配规则为，完整的格式如下
 * ${prefix}-${spring-profile-active}.${file-extension}
 * prefix默认为spring.application.name的值，也可以通过spring.cloud.nacos.config.prefix来做配置
 * 当spring-profile-active为空时，则dataid的格式为${prefix}.${file-extension}
 * file-extension为配置内容的数据格式，可以通过spring.cloud.nacos.config.file-extension来配置，目前只支持properties和yaml两种类型
 * 最终通过@RefreshScope实现刷新
 *
 */

/**
 *
 * NameSpace>Group>Service>Cluster>Instance
 * 默认的为NameSpace = public,Group = DEFAULT_GROUP，默认Cluster为DEFAULT
 * NameSpace用于隔离，如三个环境生产、开发和测试，创建三个NameSpace，不同的NameSpace之间为隔离的
 * Group将微服务划分到同一个分组中去,类似Stream中的Group
 * Service即为微服务，一个Service可以包含多个Cluster集群，Nacos默认Cluster为Default，Cluster为对指定微服务的一个虚拟的划分
 * 例如：为了容灾有杭州和广州两个集群，杭州集群的微服务为HZ，而广州的为GZ
 * Instance为微服务的实例
 *
 */

/**
 * DataId方案利用spring.profiles.active切换不同 开发环境 的配置文件
 */

/**
 * Group方案默认为DEFAULT_GROUP，可以通过spring.cloud.nacos.config.group进行切换 分组 配置文件
 */

/**
 * NameSpace方案，新建NameSpace会产生NameSpaceId，可以通过spring.cloud.nacos.config.namespace进行切换 命名空间，值为NameSpaceId
 */

/**
 * Linux环境下，nacos集群需要配合Nginx集群暴露地址到外部
 * 默认的nacos的数据存储在内嵌的数据库derby中，在nacos集群中存在单点数据一致性的问题
 * 采用集中式存储支持集群化部署，目前只支持Mysql
 */

/**
 * derby切换到mysql的步骤
 * windows版本
 * 1nacos解压后的conf目录下的nacos-mysql.sql，执行脚本
 * 2找到同级目录的application.properties,然后添加下面以下的内容
 *   spring.datasource.platform = mysql
 *   db.num = 1
 *   db.url.0 = jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf-8&connectionTimeout=3000&socketTimeout=3000&autoReconnect=true
 *   db.user = root
 *   db.password = asp_10010
 *  3重启nacos则自动将Derby切换到mysql
 *Linux版本
 * Nginx双机集群+3个Nacos(3个以上的)+Mysql高可用集群
 * 1找到conf目录下的nacos-mysql.sql文件，粘贴到Linux的mysql数据库，执行脚本
 * 2找到同级目录的application.properties,然后添加数据库配置内容（同上）
 * 3梳理nacos集群的端口号，找到cluster.conf文件,通过hostname -i查找本机ens3，然后添加本机ip+端口号1，ip+端口号2，ip+端口号3
 * 4编辑startup.sh脚本，使其能够传递不同的端口号启动，
 *  修改getopts ":m:f:s:p:"添加了p:
 *  修改case $opt 为 p) PORT=$OPTARG;;
 *  修改nohup $JAVA ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2 > &1 &为
 *     nohup $JAVA -Dserver.port=${PORT} ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2 > &1 &
 * 5分别使用./startup.sh -p 3333,./startup.sh -p 4444,./startup.sh -p 5555启动三个nacos
 * 6Nginx作为负载均衡,打开/user/local/nginx/conf目录，编辑nginx.conf文件
 *  (1)定义一个upstream组，每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉自动剔除
 *    upstream cluster{ server 127.0.0.1:3333; server 127.0.0.1:4444; server 127.0.0.1:5555; }
 *  (2)设置listen的监听端口
 *     server{
 *          listen            1111;
 *          server_name       localhost;
 *      }
 *  (3)设置代理
 *      location /{
 *           proxy_pass http://cluster
 *      }
 *  (4)按照配置启动切到nginx的sbin目录, ./nginx -c /usr/local/nginx/conf/nginx.conf
 *  7正常的启动顺序为先开Nginx，再启动nacos集群，最后访问Nginx的监听端口和地址
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
