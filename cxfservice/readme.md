## webServive服务端搭建与客户端程序编写

### 搭建webService服务
1. 在需要提供webService服务的类上添加@WebService注解

2. 添加三处配置文件，分别是applicationContext.xml,cxf-servlet.xml,web.xml分别按照项目的例子添加配置文件

3. 启动tomcat工程项目，然后访问如下地址[http://localhost:8080/cxfservice/webService/userservice?wsdl](http://localhost:8080/cxfservice/webService/userservice?wsdl),
[http://localhost:8080/cxfservice/webService/calculator?wsdl](http://localhost:8080/cxfservice/webService/calculator?wsdl)可以查看到具体的wsdl文件

### 编写客户端
1. 通过点击生成cxfservice生成Java Code From WSDL
2. 编写测试类，有两种方式

第一种方式


    URL wsdlDocumentLocation = new URL("http://localhost:8080/cxfservice/webService/calculator?wsdl");
    ICalculator calculator = new CalculatorService(wsdlDocumentLocation).getCalculatorPort();

第二种方式
    
    // 通过JaxWsProxyFactoryBean实现客户端调用
    JaxWsProxyFactoryBean wsProxyFactoryBean = new JaxWsProxyFactoryBean();

    // 设置调用的webService的地址
    wsProxyFactoryBean.setAddress("http://localhost:8080/cxfservice/webService/calculator?wsdl");
    // 设置SEI(接口类型portType)
    wsProxyFactoryBean.setServiceClass(ICalculator.class);
    // 创建客户端调用对象(也是portType，与上面的接口是一致的)
    ICalculator calculator = wsProxyFactoryBean.create(ICalculator.class);

## 参考
[CXF开发WebService](https://www.cnblogs.com/qianna-daisy/p/6681970.html)  
[wdsl生成客户端代码](https://blog.csdn.net/yishichangan1/article/details/51861970)
