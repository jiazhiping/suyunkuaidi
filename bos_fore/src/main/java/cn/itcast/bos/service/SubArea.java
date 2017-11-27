
package cn.itcast.bos.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>subArea complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取address属性的值。
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
     * 设置address属性的值。
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
     * 获取area属性的值。
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
     * 设置area属性的值。
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
     * 获取assistKeyWords属性的值。
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
     * 设置assistKeyWords属性的值。
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
     * 获取endNum属性的值。
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
     * 设置endNum属性的值。
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
     * 获取fixedArea属性的值。
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
     * 设置fixedArea属性的值。
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
     * 获取id属性的值。
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
     * 设置id属性的值。
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
     * 获取keyWords属性的值。
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
     * 设置keyWords属性的值。
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
     * 获取startNum属性的值。
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
     * 设置startNum属性的值。
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
