
package com.client2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.client2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetNetworkAccessConfig_QNAME = new QName("http://service.com/", "getNetworkAccessConfig");
    private final static QName _GetNetworkAccessConfigResponse_QNAME = new QName("http://service.com/", "getNetworkAccessConfigResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.client2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetNetworkAccessConfig }
     * 
     */
    public GetNetworkAccessConfig createGetNetworkAccessConfig() {
        return new GetNetworkAccessConfig();
    }

    /**
     * Create an instance of {@link GetNetworkAccessConfigResponse }
     * 
     */
    public GetNetworkAccessConfigResponse createGetNetworkAccessConfigResponse() {
        return new GetNetworkAccessConfigResponse();
    }

    /**
     * Create an instance of {@link MsgHeader }
     * 
     */
    public MsgHeader createMsgHeader() {
        return new MsgHeader();
    }

    /**
     * Create an instance of {@link NetworkAccessConfigDto }
     * 
     */
    public NetworkAccessConfigDto createNetworkAccessConfigDto() {
        return new NetworkAccessConfigDto();
    }

    /**
     * Create an instance of {@link BaseDto }
     * 
     */
    public BaseDto createBaseDto() {
        return new BaseDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNetworkAccessConfig }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.com/", name = "getNetworkAccessConfig")
    public JAXBElement<GetNetworkAccessConfig> createGetNetworkAccessConfig(GetNetworkAccessConfig value) {
        return new JAXBElement<GetNetworkAccessConfig>(_GetNetworkAccessConfig_QNAME, GetNetworkAccessConfig.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNetworkAccessConfigResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.com/", name = "getNetworkAccessConfigResponse")
    public JAXBElement<GetNetworkAccessConfigResponse> createGetNetworkAccessConfigResponse(GetNetworkAccessConfigResponse value) {
        return new JAXBElement<GetNetworkAccessConfigResponse>(_GetNetworkAccessConfigResponse_QNAME, GetNetworkAccessConfigResponse.class, null, value);
    }

}
