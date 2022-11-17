# pluto learn project

JDK 11

默认使用的是`application-local.yml` 需要多版本的可以add or update

入参  `controller` 层 `From` 转换`Dto` 查询数据层
出参  数据层 `pojo` 转换`VO` 返回前端

#### `swagger url` ：http://localhost:8080/swagger-ui.html

#### `redis`  使用`redis`需要修改配置文件里面`redis`的地址,用户名,密码


#### 接口统一`resultful`风格 使用`JsonResult` 有全局捕获异常处理

####  权限认证使用的是`sa-token` 详情可以参考 https://sa-token.dev33.cn/doc.html#/  
也集成了JWT，需要`jwt`拦截时开启`JWTConfiguration.java`, 同时需要注意`sa-token` 和 `jwt` 同时开启的情况，可能会有意想不到的效果呦！所以一般使用一种方式就好，注释掉`config`类就可以

#### 集成 `Mybatis`、`Mybatis-plus`

#### 集成`easyExcel` 参考实例 `TestController`

#### 集成`rabbitmq`  地址：http://localhost:15672/#/queues 用户名/密码：`guest` 参考示例 `TestController`
