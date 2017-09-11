
package com.client2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>networkAccessConfigDto complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="networkAccessConfigDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://service.com/}baseDto"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="iccId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nacIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "networkAccessConfigDto", propOrder = {
    "iccId",
    "nacIds"
})
public class NetworkAccessConfigDto
    extends BaseDto
{

    protected String iccId;
    @XmlElement(nillable = true)
    protected List<String> nacIds;

    /**
     * 获取iccId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIccId() {
        return iccId;
    }

    /**
     * 设置iccId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIccId(String value) {
        this.iccId = value;
    }

    /**
     * Gets the value of the nacIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nacIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNacIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNacIds() {
        if (nacIds == null) {
            nacIds = new ArrayList<String>();
        }
        return this.nacIds;
    }

}
