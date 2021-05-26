### 基本概念

**命名空间**：常用于隔离开发、测试、生产环境，使用：`spring.cloud.nacos.config.namespace=命名空间ID`

**配置集**：所有配置的集合，如bootstrap.properties和application.properties

**配置集ID**：Data Id，用于组织划分系统，如admin.properties、member.properties

**配置分组**：常用于区分场景，如default(默认)和promotion(促销)，使用：`spring.cloud.nacos.config.group=Group`

[Nacos 概念](https://nacos.io/zh-cn/docs/concepts.html)

### 使用

#### 配置中心

- 使用Nacos作为配置中心需要给配置中心中添加一个数据集（Data Id），默认规则为`应用名.properties`，在配置集中添加配置
- [使用`@RefreshScope`动态获取并刷新配置](https://github.com/Hitout/gmall/blob/master/gmall-order/src/main/java/com/gxyan/gmall/order/controller/ConfigController.java)
- 优先使用配置中心的配置

##### 多配置集加载

将mysql、redis等的配置信息放到一个配置文件中，再通过Nacos的`extension-configs`加载

```
spring.cloud.nacos.config.extension-configs[0].data-id=root.yml
spring.cloud.nacos.config.extension-configs[0].group=DEFAULT_GROUP
```





[Nacos 架构](https://nacos.io/zh-cn/docs/architecture.html)

