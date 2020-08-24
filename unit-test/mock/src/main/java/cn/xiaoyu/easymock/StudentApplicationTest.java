package cn.xiaoyu.easymock;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author 01399268
 * @date 2020/8/24
 */
@RunWith(EasyMockRunner.class)
public class StudentApplicationTest {
    @Mock
    IStudent student;

    @TestSubject
    StudentApplication application = new StudentApplication(student);

    @Test
    public void testdoMethod() {
        //设定 Mock 对象的预期行为和输出
        EasyMock.expect(student.play()).andReturn("play").times(1);
        EasyMock.expect(student.run()).andReturn("run").times(1);
        EasyMock.expect(student.sing()).andReturn("sing").times(1);

        //将 Mock 对象切换到 Replay 状态
        EasyMock.replay(student);
        //调用 Mock 对象方法进行单元测试
        String getStr = application.doSomething();
        System.out.println(getStr);
        //对 Mock 对象的行为进行验证
        String cstr = "playrunsing";//正确的字符串
        Assert.assertEquals(getStr, cstr);
        EasyMock.verify(student);
    }
}
