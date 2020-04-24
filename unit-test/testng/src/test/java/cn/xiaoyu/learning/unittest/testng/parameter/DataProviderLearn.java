package cn.xiaoyu.learning.unittest.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author roin.zhang
 * @date 2020/4/24
 */
public class DataProviderLearn {
    @DataProvider(name = "user")
    public Object[][] Users() {
        return new Object[][]{
                {"root", "passowrd"},
                {"testng", "123456"}
        };
    }

    /**
     * 通过DataProvider传递参数
     */
    @Test(dataProvider = "user")
    public void verifyUser(String username, String password) {
        System.out.println("Username: " + username + " Password: " + password);
    }

    /**
     * 通过Parameters传递参数
     */
    @Test
    @Parameters({"username", "password"})
    public void verifyUserByXml(String username, String password) {
        System.out.println("Username: " + username + " Password: " + password);
    }
}
