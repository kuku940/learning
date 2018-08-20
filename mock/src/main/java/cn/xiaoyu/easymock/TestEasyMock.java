package cn.xiaoyu.easymock;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import static org.easymock.EasyMock.anyString;
import static org.junit.Assert.fail;

/**
 * @author Roin zhang
 * @date 2018/7/25
 */
public class TestEasyMock {

    @Test
    public void testEasyMock() {
        // 使用EasyMock生成Mock对象
        Class1Mocked obj = EasyMock.createMock(Class1Mocked.class);

        // 录制Mock对象的预期行为和输出
        EasyMock.expect(obj.say(anyString()))
                .andReturn("hello Jack").once()
                .andThrow(new RuntimeException("error occur")).once()
                .andReturn("hello world!").anyTimes();

        // 将Mock对象的切换到播放状态
        EasyMock.replay(obj);

        // 调用Mock对象方法进行单元测试
        Assert.assertEquals("hello Jack", obj.say("Ma"));
        try {
            obj.say("Mary");
            fail("error occur");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
        }
        Assert.assertEquals("hello world!", obj.say("Ma"));

        // 对Mock对象的行为进行验证
        EasyMock.verify(obj);
    }
}

interface Class1Mocked {
    String say(String name);
}