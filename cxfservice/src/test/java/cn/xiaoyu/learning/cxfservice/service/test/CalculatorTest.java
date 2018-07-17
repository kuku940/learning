package cn.xiaoyu.learning.cxfservice.service.test;

import cn.xiaoyu.learning.cxfservice.wsinterface.calculator.CalculatorService;
import cn.xiaoyu.learning.cxfservice.wsinterface.calculator.ICalculator;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {
    public static void main(String[] args) throws MalformedURLException {
        test01();
        test02();
    }

    public static void test01() throws MalformedURLException {
        // wsdl地址
        URL wsdlDocumentLocation = new URL("http://localhost:8080/cxfservice/webService/calculator?wsdl");

        ICalculator calculator = new CalculatorService(wsdlDocumentLocation).getCalculatorPort();

        // 执行方法
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.sub(3, 2));
    }

    public static void test02() {
        // 通过JaxWsProxyFactoryBean实现客户端调用
        JaxWsProxyFactoryBean wsProxyFactoryBean = new JaxWsProxyFactoryBean();

        // 设置调用的webService的地址
        wsProxyFactoryBean.setAddress("http://localhost:8080/cxfservice/webService/calculator?wsdl");
        // 设置SEI(接口类型portType)
        wsProxyFactoryBean.setServiceClass(ICalculator.class);
        // 创建客户端调用对象(也是portType，与上面的接口是一致的)
        ICalculator calculator = wsProxyFactoryBean.create(ICalculator.class);
        // 调用webservice发布的方法
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.sub(3, 2));
    }
}
