package cn.xiaoyu.learning.cxfservice.wsinterface.userservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>user complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="user">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="habby" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
        "age",
        "habby",
        "id",
        "username"
})
public class User {

    protected int age;
    @XmlElement(nillable = true)
    protected List<String> habby;
    protected long id;
    protected String username;

    /**
     * ��ȡage���Ե�ֵ��
     */
    public int getAge() {
        return age;
    }

    /**
     * ����age���Ե�ֵ��
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * Gets the value of the habby property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the habby property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHabby().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getHabby() {
        if (habby == null) {
            habby = new ArrayList<String>();
        }
        return this.habby;
    }

    /**
     * ��ȡid���Ե�ֵ��
     */
    public long getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * ��ȡusername���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getUsername() {
        return username;
    }

    /**
     * ����username���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUsername(String value) {
        this.username = value;
    }

}
