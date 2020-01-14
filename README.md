# 信鸽 
## 一、特征
> - 自定义html邮件模板
> - xml文件配置值，发邮件
> - 多样化调用，可以通过接口，json的方式发邮件
## 二、结构
```text
carrierpigeon
├─config
├─mailTemplate
└─src
   └─main
       ├─java
       │  └─com
       │      └─alway
       │          └─carrierpigeon
       │              └─util
       └─resources  

```
## 三、说明
1.mailConfig.xml详解
<br>
<font color="red">这是基础配置文件，文件名及文件结构不能变</font>

> - myEmailAccount：发送方的邮箱账号
> - myEmailPassword：发送方的邮箱密码
> - myEmailSMTPHost：邮箱服务器地址
> - mailTemplatePath：邮件html模板文件地址
> - mailAttributePath：邮件变量以及接收方地址
> - matchRule：匹配规则
>   - prefix：匹配前缀
>   - suffix：匹配后缀

2.mailAttribute.xml详解
> - receiveMailAccounts：收件邮箱地址列表
>   - receiveAccount：单个收件邮箱地址
> - attributes：变量列表
>   - attribute：变量
>       - key：变量名
>       - value：变量值
## 四、运行方式
```text
方式一：调用接口
json
方式二：配置文件
xml
```