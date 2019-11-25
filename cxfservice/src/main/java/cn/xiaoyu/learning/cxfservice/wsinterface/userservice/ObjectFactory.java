package cn.xiaoyu.learning.cxfservice.wsinterface.userservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the cn.xiaoyu.learning.cxfservice.wsinterface.userservice package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetNameResponse_QNAME = new QName("http://service.cxfservice.learning.xiaoyu.cn/", "getNameResponse");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://service.cxfservice.learning.xiaoyu.cn/", "getUserByIdResponse");
    private final static QName _GetName_QNAME = new QName("http://service.cxfservice.learning.xiaoyu.cn/", "getName");
    private final static QName _GetUserById_QNAME = new QName("http://service.cxfservice.learning.xiaoyu.cn/", "getUserById");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.xiaoyu.learning.cxfservice.wsinterface.userservice
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetName }
     */
    public GetName createGetName() {
        return new GetName();
    }

    /**
     * Create an instance of {@link GetUserById }
     */
    public GetUserById createGetUserById() {
        return new GetUserById();
    }

    /**
     * Create an instance of {@link GetUserByIdResponse }
     */
    public GetUserByIdResponse createGetUserByIdResponse() {
        return new GetUserByIdResponse();
    }

    /**
     * Create an instance of {@link GetNameResponse }
     */
    public GetNameResponse createGetNameResponse() {
        return new GetNameResponse();
    }

    /**
     * Create an instance of {@link User }
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.cxfservice.learning.xiaoyu.cn/", name = "getNameResponse")
    public JAXBElement<GetNameResponse> createGetNameResponse(GetNameResponse value) {
        return new JAXBElement<GetNameResponse>(_GetNameResponse_QNAME, GetNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.cxfservice.learning.xiaoyu.cn/", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.cxfservice.learning.xiaoyu.cn/", name = "getName")
    public JAXBElement<GetName> createGetName(GetName value) {
        return new JAXBElement<GetName>(_GetName_QNAME, GetName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}}
     */
    @XmlElementDecl(namespace = "http://service.cxfservice.learning.xiaoyu.cn/", name = "getUserById")
    public JAXBElement<GetUserById> createGetUserById(GetUserById value) {
        return new JAXBElement<GetUserById>(_GetUserById_QNAME, GetUserById.class, null, value);
    }

}
