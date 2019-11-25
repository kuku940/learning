package cn.xiaoyu.learning.cxfservice.wsinterface.calculator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sub complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="sub">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sub", propOrder = {
        "arg0",
        "arg1"
})
public class Sub {

    protected double arg0;
    protected double arg1;

    /**
     * ��ȡarg0���Ե�ֵ��
     */
    public double getArg0() {
        return arg0;
    }

    /**
     * ����arg0���Ե�ֵ��
     */
    public void setArg0(double value) {
        this.arg0 = value;
    }

    /**
     * ��ȡarg1���Ե�ֵ��
     */
    public double getArg1() {
        return arg1;
    }

    /**
     * ����arg1���Ե�ֵ��
     */
    public void setArg1(double value) {
        this.arg1 = value;
    }

}
