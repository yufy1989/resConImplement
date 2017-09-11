
package com.client2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getNetworkAccessConfigResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getNetworkAccessConfigResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="networkAccessConfig" type="{http://service.com/}networkAccessConfigDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNetworkAccessConfigResponse", propOrder = {
    "networkAccessConfig"
})
public class GetNetworkAccessConfigResponse {

    protected NetworkAccessConfigDto networkAccessConfig;

    /**
     * 获取networkAccessConfig属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NetworkAccessConfigDto }
     *     
     */
    public NetworkAccessConfigDto getNetworkAccessConfig() {
        return networkAccessConfig;
    }

    /**
     * 设置networkAccessConfig属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkAccessConfigDto }
     *     
     */
    public void setNetworkAccessConfig(NetworkAccessConfigDto value) {
        this.networkAccessConfig = value;
    }

}
