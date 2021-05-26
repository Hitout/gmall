

### 引言

Nginx是一款高性能的**Web**和**反向代理**服务器

#### 正向代理和反向代理

|      | 正向代理                                                     | 反向代理                                                     |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 概念 | 客户端与目标服务器之间增加一个代理服务器，客户端直接访问代理服务器，在由代理服务器访问目标服务器并返回客户端并返回 | 客户端访问目标服务器，在目标服务内部有一个统一接入网关将请求转发至后端真正处理的服务器并返回结果 |
| 场景 | 屏蔽客户端IP、翻墙                                           | 屏蔽服务端内部实现、负载均衡、缓存                           |

### 使用

#### 配置文件 nginx.conf

文件结构：

```
...              #全局块

events {         #events块
   ...
}

http      #http块
{
    ...   #http全局块
    server        #server块
    { 
        ...       #server全局块
        location [PATTERN]   #location块
        {
            ...
        }
        location [PATTERN] 
        {
            ...
        }
    }
    server
    {
      ...
    }
    ...     #http全局块
}
```

- **全局块**：配置影响nginx全局的指令。一般有运行nginx服务器的用户组，nginx进程pid存放路径，日志存放路径，配置文件引入，允许生成worker process数等

- **events块**：配置影响nginx服务器或与用户的网络连接。有每个进程的最大连接数，选取哪种事件驱动模型处理连接请求，是否允许同时接受多个网路连接，开启多个网络连接序列化等

- **http块**：可以嵌套多个server，配置代理，缓存，日志定义等绝大多数功能和第三方模块的配置。如文件引入，mime-type定义，日志自定义，是否使用sendfile传输文件，连接超时时间，单连接请求数等

- **server块**：配置虚拟主机的相关参数，一个http中可以有多个server

  ```
  server {
      keepalive_requests 120; #单连接请求上限次数。
      listen       4545;   #监听端口
      server_name  127.0.0.1;   #监听地址       
      location  ~*^.+$ {       #请求的url过滤，正则匹配，~为区分大小写，~*为不区分大小写。
          #root path;  #根目录
          #index vv.txt;  #设置默认页
          proxy_pass  http://mysvr;  #请求转向mysvr 定义的服务器列表
          deny 127.0.0.1;  #拒绝的ip
          allow 172.18.5.54; #允许的ip           
      } 
  }
  ```

- **location块**：配置请求的路由，以及各种页面的处理情况

需要注意，配置文件的**每个指令必须有分号结束**

#### 负载均衡

在upstream中配置服务器组，使用`proxy_pass`将请求传递到服务器组中

```

http {
    upstream backend {
    	# 默认使用轮询负载负载均衡策略
        server backend1.example.com;
        server backend2.example.com;
        server 192.0.0.1 backup;	# 热备
    }
    
    server {
        location / {
        	proxy_set_header Host $host;	# 设置请求的host信息（nginx代理给网关时会丢失请求的host信息）
            proxy_pass http://backend;
        }
    }
}
```

#### nginx与网关

在分布式系统中，由于服务可能部署在多台机器上，如果直接由nginx代理到服务集群，就无法做到动态伸缩。nginx应该配置网关的地址，由网关通过注册中心的发现注册负载均衡到真正的服务器上。



参考文章：[Nginx 配置详解](https://www.runoob.com/w3cnote/nginx-setup-intro.html)