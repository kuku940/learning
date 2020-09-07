## Apache Common工具包

### commons-lang 基础工具包

    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.11</version>
    </dependency>

为Java核心类提供额外方法，如StringUtils.isEmpty(CharSequence cs)

### commons-io IO工具包

    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.7</version>
    </dependency>
    
提供了一些常见的IO操作，比如FileUtils.copyDirectory(File srcDir, File destDir)

### commons-beanutils Bean相关用法

    <dependency>
        <groupId>commons-beanutils</groupId>
        <artifactId>commons-beanutils</artifactId>
        <version>1.9.4</version>
    </dependency>
    
对JavaBean对象操作的工具类，如BeanUtils.copyProperties(final Object dest, final Object orig)可以方便对象属性的拷贝 

### commons-collections 集合扩展类

    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-collections4</artifactId>
        <version>4.4</version>
    </dependency>

Java集合扩展类，如[BidiMap](./src/test/java/cn/xiaoyu/learing/common/BidiMapTest.java)不仅可以从key找value，还可以value找key。

### commons-fileupload

    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>
文件上传工具类，处理form标签的上传文件

### commons-dbutils 

    <dependency>
        <groupId>commons-dbutils</groupId>
        <artifactId>commons-dbutils</artifactId>
        <version>1.7</version>
    </dependency>

### commons-httpclient Http请求

### commons-dbcp 数据库连接池

### commons-codec 核心算法，如MD5
