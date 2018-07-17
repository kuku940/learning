# Java自定义注解

## 元注解
Java提供了4种元注解用于注解其他注解，所有的注解都是基于这四种注解来定义的

### @Retention 
定义注解的保留策略，描述注解的生命周期，即注解的生效范围

    @Retention(RetentionPolicy.SOURCE)      注解仅存在于源码中，在class字节码文件中不包含  
    @Retention(RetentionPolicy.CLASS)       默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得  
    @Retention(RetentionPolicy.RUNTIME)     注解会在class字节码文件中存在，在运行时可以通过反射获取到  

### @Target 
注解类型声明，用于描述注解的使用范围，超出范围时编译失败

    @Target({ElementType.TYPE})
    
    ElementType类型如下：
    
    CONSTRUCTOR         构造方法声明
    FIELD               成员变量声明（包括枚举常量）
    LOCAL_VARIABLE      局部变量声明
    METHOD              方法声明
    PACKAGE             包声明
    PARAMETER           参数声明
    TYPE                类、接口（包括注解类型）或枚举声明
    
### @Documented
用于指定javac生成API时显示该注解信息

### @Inherited
允许子类继承父类中的注解，默认情况下，子类是不继承父类注解的

