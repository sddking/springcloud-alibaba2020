hystrix 存在的缺点
1需要程序员手动搭建监控平台
2没有一套web界面可以给我们进行更加细粒度的配置流量控制、速率控制、服务熔断、服务降级

sentinel优点
1单独一个组件，可以独立出来
2直接界面化细粒度统一配置
约定>配置>编码
建议使用配置+注解的形式