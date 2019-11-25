package cn.xiaoyu.learning.cxfservice.wsinterface.userservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getUserById complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="getUserById">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserById", propOrder = {
        "arg0"
})
public class GetUserById {

    protected long arg0;

    /**
     * ��ȡarg0���Ե�ֵ��
     */
    public long getArg0() {
        return arg0;
    }

    /**
     * ����arg0���Ե�ֵ��
     */
    public void setArg0(long value) {
        this.arg0 = value;
    }

}
