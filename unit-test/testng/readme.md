

### @Test注解常见参数

#### 1. 测试方法是否执行enable
默认是true,如果设置为false，则在运行时不会执行这个测试方法
> @Test(enabled = false)

#### 2. 预期异常expectedExeption

> @Test(expectedExceptions = ClassName.class)
>@Test(expectedExceptions = {ClassName.class, ClassName2.class,... })

如果ClassName类抛出了异常，测算测试通过，没有异常算测试不通过。
expectedExceptions的值也可以是一个数组。

#### 3. 超时timeOut
单位为毫秒，如果测试方法运行时间超这个值算测试不通过；



### testng.xml
直接右键运行[testng](./src/test/resources/testng.xml)