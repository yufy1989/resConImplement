
package com.client2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getNetworkAccessConfigResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡnetworkAccessConfig���Ե�ֵ��
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
     * ����networkAccessConfig���Ե�ֵ��
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
