# pluto learn project

默认使用的是application-local.yml 需要多版本的可以add or update

入参  controller 层 From 转换Dto 查询数据层
出参  数据层 pojo 转换Vo 返回前端

#### swagger url ：
```
http://localhost:8080/swagger-ui.html
```

### redis
使用redis需要修改配置文件里面redis的地址,用户名,密码


#### 接口统一resultful风格 使用JsonResult 有全局捕获异常处理

####  权限认证使用的是sa-token 详情可以参考 https://sa-token.dev33.cn/doc.html#/

#### 集成了Mybatis、Mybatis-plus

#### 集成了easyExcel 参考实例 TestExportController
