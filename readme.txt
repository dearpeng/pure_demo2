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
10.加入springboot的validation校验参数   https://blog.csdn.net/csdn13257081409/article/details/105716313
11.增加验证码生成
    1. 导包  kaptcha
    2. CaptchaConfig 验证码配置
    3. KaptchaTextCreator验证码文本生成器
    4. SysCaptchaController 调用接口测试   http://localhost:8086/captcha/captchaImage?type=math&s=0.5114908571706647