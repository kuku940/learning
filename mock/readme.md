## Mock测试类

使用EasyMock大致可以划分为以下几个步骤：
1. 使用 EasyMock 生成 Mock 对象；
2. 录制 Mock 对象的预期行为和输出；
3. 将 Mock 对象切换到 播放 状态；
4. 调用 Mock 对象方法进行单元测试；
5. 对 Mock 对象的行为进行验证。