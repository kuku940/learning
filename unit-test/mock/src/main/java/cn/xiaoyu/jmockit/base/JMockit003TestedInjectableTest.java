package cn.xiaoyu.jmockit.base;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

interface MailService {
    /**
     * 发送邮件
     *
     * @param userId  邮件接收人Id
     * @param content 邮件内容
     * @return 邮件是否发送成功
     */
    boolean sendMail(long userId, String content);
}

interface UserCheckService {

    /**
     * 校验某个用户是否是合法用户
     *
     * @param userId 用户ID
     * @return 合法的就返回true, 否则返回false
     */
    boolean check(long userId);
}

/**
 * 注释@Tested和@Injectable的使用
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class JMockit003TestedInjectableTest {
    @Tested
    OrderService orderService;
    long testUserId = 123456L;
    long testItemId = 456789L;

    @Test
    public void submitOrderTest(@Injectable MailService mailService, @Injectable UserCheckService userCheckService) {
        // 还可以通过如下方式注入
//        new Expectations(orderService) {{
//            Deencapsulation.setField(orderService, "userCheckService", userCheckService);
//        }};

        new Expectations() {{
            // 模拟邮件全部发送成功
            mailService.sendMail(testUserId, anyString);
            result = true;

            // 模拟用户验证都合法
            userCheckService.check(testUserId);
            result = true;
        }};

        // JMockit帮我们实例化了mailService了，并通过OrderService的构造函数，注入到orderService对象中。
        // JMockit帮我们实例化了userCheckService了，并通过OrderService的属性，注入到orderService对象中。
        Assert.assertTrue(orderService.submitOrder(testUserId, testItemId));
    }
}

class OrderService {
    @Resource
    private UserCheckService userCheckService;
    private MailService mailService;

    // 构造函数
    public OrderService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 下订单
     *
     * @param buyerId 买家ID
     * @param itemId  商品id
     * @return 返回 下订单是否成功
     */
    public boolean submitOrder(long buyerId, long itemId) {
        // 先校验用户身份
        if (!userCheckService.check(buyerId)) {
            // 用户身份不合法
            return false;
        }
        // 下单逻辑代码，
        // 省略...
        // 下单完成，给买家发邮件
        if (!this.mailService.sendMail(buyerId, "下单成功")) {
            // 邮件发送成功
            return false;
        }
        return true;
    }
}