package cn.xiaoyu.learning.cxfservice.service.test;

import cn.xiaoyu.learning.cxfservice.wsinterface.userservice.IUserService;
import cn.xiaoyu.learning.cxfservice.wsinterface.userservice.User;
import cn.xiaoyu.learning.cxfservice.wsinterface.userservice.UserServiceImplService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

public class UserServiceTest {
    public static void main(String[] args) throws MalformedURLException {
        test01();
        test02();
    }

    public static void test01() throws MalformedURLException {
        // wsdl地址
        URL wsdlDocumentLocation = new URL("http://localhost:8080/cxfservice/webService/userservice?wsdl");

        IUserService userService = new UserServiceImplService(wsdlDocumentLocation).getUserServiceImplPort();

        // 执行方法
        User user = userService.getUserById(1);
        System.out.println(user.getUsername());

        System.out.println(userService.getName("Hello world"));
    }

    public static void test02() {
        // 通过JaxWsProxyFactoryBean实现客户端调用
        JaxWsProxyFactoryBean wsProxyFactoryBean = new JaxWsProxyFactoryBean();

        // 设置调用的webService的地址
        wsProxyFactoryBean.setAddress("http://localhost:8080/cxfservice/webService/userservice?wsdl");
        // 设置SEI(接口类型portType)
        wsProxyFactoryBean.setServiceClass(IUserService.class);
        // 创建客户端调用对象(也是portType，与上面的接口是一致的)
        IUserService userService = wsProxyFactoryBean.create(IUserService.class);
        // 调用webservice发布的方法
        User user = userService.getUserById(2);
        System.out.println(user.getUsername());

        System.out.println(userService.getName("Hello world"));
    }
}
