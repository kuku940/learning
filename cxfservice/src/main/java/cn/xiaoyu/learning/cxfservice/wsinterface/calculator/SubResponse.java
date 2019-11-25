package cn.xiaoyu.learning.cxfservice.wsinterface.calculator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>subResponse complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="subResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subResponse", propOrder = {
        "_return"
})
public class SubResponse {

    @XmlElement(name = "return")
    protected double _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     */
    public double getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     */
    public void setReturn(double value) {
        this._return = value;
    }

}
