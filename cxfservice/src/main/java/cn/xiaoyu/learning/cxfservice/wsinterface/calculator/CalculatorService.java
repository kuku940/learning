
package cn.xiaoyu.learning.cxfservice.wsinterface.calculator;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CalculatorService", targetNamespace = "http://impl.service.cxfservice.learning.xiaoyu.cn/", wsdlLocation = "http://localhost:8080/cxfservice/webService/calculator?wsdl")
public class CalculatorService
    extends Service
{

    private final static URL CALCULATORSERVICE_WSDL_LOCATION;
    private final static WebServiceException CALCULATORSERVICE_EXCEPTION;
    private final static QName CALCULATORSERVICE_QNAME = new QName("http://impl.service.cxfservice.learning.xiaoyu.cn/", "CalculatorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/cxfservice/webService/calculator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CALCULATORSERVICE_WSDL_LOCATION = url;
        CALCULATORSERVICE_EXCEPTION = e;
    }

    public CalculatorService() {
        super(__getWsdlLocation(), CALCULATORSERVICE_QNAME);
    }

    public CalculatorService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CALCULATORSERVICE_QNAME, features);
    }

    public CalculatorService(URL wsdlLocation) {
        super(wsdlLocation, CALCULATORSERVICE_QNAME);
    }

    public CalculatorService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CALCULATORSERVICE_QNAME, features);
    }

    public CalculatorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CalculatorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ICalculator
     */
    @WebEndpoint(name = "CalculatorPort")
    public ICalculator getCalculatorPort() {
        return super.getPort(new QName("http://impl.service.cxfservice.learning.xiaoyu.cn/", "CalculatorPort"), ICalculator.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICalculator
     */
    @WebEndpoint(name = "CalculatorPort")
    public ICalculator getCalculatorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.service.cxfservice.learning.xiaoyu.cn/", "CalculatorPort"), ICalculator.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CALCULATORSERVICE_EXCEPTION!= null) {
            throw CALCULATORSERVICE_EXCEPTION;
        }
        return CALCULATORSERVICE_WSDL_LOCATION;
    }

}
