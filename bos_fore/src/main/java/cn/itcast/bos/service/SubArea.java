
package cn.itcast.bos.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>subArea complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="subArea"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="area" type="{http://service.bos.itcast.cn/}area" minOccurs="0"/&gt;
 *         &lt;element name="assistKeyWords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fixedArea" type="{http://service.bos.itcast.cn/}fixedArea" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="keyWords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subArea", propOrder = {
    "address",
    "area",
    "assistKeyWords",
    "endNum",
    "fixedArea",
    "id",
    "keyWords",
    "startNum"
})
public class SubArea {

    protected String address;
    protected Area area;
    protected String assistKeyWords;
    protected String endNum;
    protected FixedArea fixedArea;
    protected String id;
    protected String keyWords;
    protected String startNum;

    /**
     * ��ȡaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * ����address���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * ��ȡarea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Area }
     *     
     */
    public Area getArea() {
        return area;
    }

    /**
     * ����area���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Area }
     *     
     */
    public void setArea(Area value) {
        this.area = value;
    }

    /**
     * ��ȡassistKeyWords���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssistKeyWords() {
        return assistKeyWords;
    }

    /**
     * ����assistKeyWords���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssistKeyWords(String value) {
        this.assistKeyWords = value;
    }

    /**
     * ��ȡendNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndNum() {
        return endNum;
    }

    /**
     * ����endNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndNum(String value) {
        this.endNum = value;
    }

    /**
     * ��ȡfixedArea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link FixedArea }
     *     
     */
    public FixedArea getFixedArea() {
        return fixedArea;
    }

    /**
     * ����fixedArea���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link FixedArea }
     *     
     */
    public void setFixedArea(FixedArea value) {
        this.fixedArea = value;
    }

    /**
     * ��ȡid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * ��ȡkeyWords���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyWords() {
        return keyWords;
    }

    /**
     * ����keyWords���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyWords(String value) {
        this.keyWords = value;
    }

    /**
     * ��ȡstartNum���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartNum() {
        return startNum;
    }

    /**
     * ����startNum���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartNum(String value) {
        this.startNum = value;
    }

}
