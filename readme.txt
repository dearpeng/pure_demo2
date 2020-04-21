1.ssm多模块
2.整合dubbo
3.整合kafaka(未完成)
4.整合redis,加入bloom filter 解决redis缓存穿透
5.整合mapstruct
6.配置全局异常处理--结束
    6.1:MyGlobalExceptionHandler中具体处理那种类型的exception
7.logback 格式化sql(拼接sql和参数)
8.sentinel限流,降权,熔断(@SentinelResource)
9.引入管道(BeanConfig-->submitPipeline),Apache commons-chain(CommandChain)两种方式来处理链式逻辑问题.