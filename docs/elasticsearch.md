### 简介

Elasticsearch是一个分布式，高性能、高可用、可伸缩的搜索和分析系统

> Elastic Stack前身缩写是ELK，即Elasticsearch + LogStash + Kibana，常用于日志分析、搜索等场景

#### 基本概念

##### 文档

Elasticsearch是面向文档的，文档是所有可搜索数据的最小单元。可以把文档理解为关系型数据库中的一条记录。文档会被序列化成json格式，保存在Elasticsearch中。

##### 索引（index）

索引是具有某种相似特性的文档集合

##### 类型（type）

7.0开始一个索引只能创建一个type

### 使用

[样本测试数据](https://www.elastic.co/guide/en/elasticsearch/reference/current/getting-started-index.html#getting-started-batch-processing)

kibana dev tools快捷键

- CTRL+ENTER提交请求
- ENTER 或 TAB 选中项自动补全
- CTRL+i 自动缩进

#### RESTful API

...

#### IK分词

下载与ES相对应版本的 [IK分词](https://github.com/medcl/elasticsearch-analysis-ik) 插件，解压到plugins目录下

自定义分词：ik/config/IKAnalyzer.cfg.xml中配置分词文件地址

