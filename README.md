# 用户身份验证-实现示例

架构：SpringBoot（COLA），MySQL，Redis。

功能点：
- 验证码生成、校验。
- token生成、认证。
- 密码保密存储（加盐、加密算法）
- Filter（识别token将user信息传输到controller）

## 提供过帮助的网站
- [alibaba/COLA](https://github.com/alibaba/COLA/tree/master/cola-components)
- [jwt-官网](https://jwt.io/)
- [Springboot项目通过filter修改接口的入参](https://blog.csdn.net/liangcha007/article/details/126013945)
- [Spring中同一个Filter被连续调用两次](https://blog.csdn.net/u010154520/article/details/105860535)
- [解决:Required request body is missing](https://blog.csdn.net/zhangbeizhen18/article/details/88738241)
- [BASE64转图片](https://tool.jisuapi.com/base642pic.html)
- [在springboot中如何使用filter设置要排除的URL](https://www.eolink.com/news/post/20022.html)
- [解决ibatis.executor.ExecutorException: No setter found for the keyProperty 'id'](https://www.jianshu.com/p/b506fce4a3ae)
- [@MapperScan注解批量扫描](https://blog.csdn.net/u013059432/article/details/80239075)
- [解决ibatis.binding.BindingException: Invalid bound statement (not found)问题](https://blog.csdn.net/sundacheng1989/article/details/81630370)
- [【密码学】轻松理解“加盐”的原理与java实现](https://blog.csdn.net/DavidHuang2017/article/details/80283469)
- [java 密码MD5加密 加盐加密工具类](https://developer.aliyun.com/article/1052647)
- [java_note/安全/密码加盐Salt](https://github.com/hmfight/java_note/blob/master/%E5%AE%89%E5%85%A8/%E5%AF%86%E7%A0%81%E5%8A%A0%E7%9B%90Salt.md)
- [Java实现密码加密](https://juejin.cn/post/7125789975148232717)
- [MySQL--连接超时提示](https://blog.csdn.net/JustinQin/article/details/78630100)
- [spring boot 添加自定义拦截器过滤器 Content-Type=application/json 格式的参数请求方式处理](https://blog.csdn.net/weikzhao0521/article/details/82377951)
- [SpringBoot集成Redis - 基于RedisTemplate+Jedis的数据操作](https://pdai.tech/md/spring/springboot/springboot-x-redis-jedis.html)
- [Idea配置，设置当import的类数量大于多少时，采用*代替](https://blog.csdn.net/weixin_38106322/article/details/107724676)
- [最全的Java操作Redis的工具类](https://juejin.cn/post/7106842788288790565)
- [Swagger3.0介绍及springboot整合Swagger3.0](https://blog.csdn.net/qq_43521797/article/details/115835771)
- [SpringBoot教程(十四) | SpringBoot集成Redis(全网最全)](https://juejin.cn/post/7076244567569203208)
- [MySQL字符集和排序规则](https://segmentfault.com/a/1190000020339810)
- [chiner：干掉 PowerDesigner，国人开源的数据库设计工具](https://tobebetterjavaer.com/gongju/chiner.html)
- [PDMaas-元数建模](https://www.pdmaas.cn/home/)
- [spring过滤器Filter 、 拦截器Interceptor 、 切片Aspect 详解](https://blog.csdn.net/ztx114/article/details/112979202)
- [servlet、jsp依赖](https://blog.csdn.net/zixiao217/article/details/52654965)
