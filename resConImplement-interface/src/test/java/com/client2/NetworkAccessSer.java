package com.client2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2016-07-21T16:51:45.406+08:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://service.com/", name = "NetworkAccessSer")
@XmlSeeAlso({ObjectFactory.class})
public interface NetworkAccessSer {

    @WebResult(name = "networkAccessConfig", targetNamespace = "")
    @RequestWrapper(localName = "getNetworkAccessConfig", targetNamespace = "http://service.com/", className = "com.client2.GetNetworkAccessConfig")
    @WebMethod
    @ResponseWrapper(localName = "getNetworkAccessConfigResponse", targetNamespace = "http://service.com/", className = "com.client2.GetNetworkAccessConfigResponse")
    public com.client2.NetworkAccessConfigDto getNetworkAccessConfig(
        @WebParam(name = "msgHeader", targetNamespace = "")
        com.client2.MsgHeader msgHeader,
        @WebParam(name = "iccId", targetNamespace = "")
        java.lang.String iccId,
        @WebParam(name = "messageId", targetNamespace = "")
        java.lang.String messageId
    );
}
