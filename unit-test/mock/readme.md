## Mock测试类


### EasyMock 

EasyMock是早期比较流行的Mock测试框架，他提供对接口的模拟，能够通过录制、回访、检查三步来完成大体的测试过程，
可以验证方法的调用种类、次数、顺序，可以令Mock对象返回指定的值或抛出指定异常。

使用EasyMock大致可以划分为以下几个步骤：
1. 使用 EasyMock 生成 Mock 对象；
2. 录制 Mock 对象的预期行为和输出；
3. 将 Mock 对象切换到 播放 状态；
4. 调用 Mock 对象方法进行单元测试；
5. 对 Mock 对象的行为进行验证。


    @Test
    public void testEasyMock() {
        // 使用EasyMock生成Mock对象
        Class1Mocked obj = EasyMock.createMock(Class1Mocked.class);

        // 录制Mock对象的预期行为和输出
        EasyMock.expect(obj.say("z3")).andReturn("hello l4");
        // 将Mock对象的切换到播放状态
        EasyMock.replay(obj);

        // 调用Mock对象方法进行单元测试
        String actual = obj.say("z3");
        Assert.assertEquals("hello l4", actual);

        // 对Mock对象的行为进行验证
        EasyMock.verify(obj);
    }
     
### mockito

相比EasyMock学习成本低，而且具有简洁的API，测试代码的可读性很高。

使用mockito大致可以划分为以下几个步骤：
1. 使用 mockito 生成 Mock 对象；
2. 定义(并非录制) Mock 对象的行为和输出(expectations部分)；
3. 调用 Mock 对象方法进行单元测试；
4. 对 Mock 对象的行为进行验证。


    // 使用mockito生成Mock对象
    Class2Mocked obj = Mockito.mock(Class2Mocked.class);
    
    // 定义Mock对象的行为和输出
    Mockito.when(obj.say("Jack")).thenReturn("hello Jack");
    // 调用Mock对象进行单元测试
    Assert.assertEquals("hello Jack", obj.say("Jack"));
    
    // 对Mock对象的行为进行验证
    Mockito.verify(obj).say("Jack");
    
### PowerMockito
PowerMock扩展了EasyMock和Mockito框架，增加了对static和final方法mock支持等功能.
PowerMock有两个重要的注解：
    
    @RunWith(PowerMockRunner.class)
    @prepareForTest({MyObject.class})
@PrepareForTest注解和@RunWith注解是结合使用的，不要单独使用@PrepareForTest，否则不起作用。当使用PowerMock去mock静态，
final或者私有方法时，需要加上这两个注解。

### jmockit示例
JMockit 是一个轻量级的mock框架是用以帮助开发人员编写测试程序的一组工具和API，
该项目完全基于 Java 5 SE 的 java.lang.instrument 包开发，内部使用 ASM 库来修改Java的Bytecode。
  
 
http://ginge.iteye.com/blog/841182
https://www.cnblogs.com/simplestupid/p/5170220.html

#### mock私有方法和私有属性

    // 私有属性mock
    new Expectations(instance) {{  
        Deencapsulation.setField(instance, "size", 2);  
    }};  
    
    // 私有方法mock jmockit 1.36版本后invoke方法被废弃
    new Expectations(instance) {{  
        Deencapsulation.invoke(instance, "getSize");  
        result = 2;  
    }};  
    


[JMockit中文网](http://jmockit.cn/index.htm)