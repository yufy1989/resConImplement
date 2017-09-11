package com.client2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2016-07-21T16:51:45.454+08:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "NetworkAccessSerService", 
                  wsdlLocation = "http://localhost:8080/resConImplement-interface/services/networkAccessSer?wsdl",
                  targetNamespace = "http://service.com/") 
public class NetworkAccessSerService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.com/", "NetworkAccessSerService");
    public final static QName NetworkAccessSerPort = new QName("http://service.com/", "NetworkAccessSerPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/resConImplement-interface/services/networkAccessSer?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(NetworkAccessSerService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/resConImplement-interface/services/networkAccessSer?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public NetworkAccessSerService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NetworkAccessSerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NetworkAccessSerService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public NetworkAccessSerService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public NetworkAccessSerService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public NetworkAccessSerService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns NetworkAccessSer
     */
    @WebEndpoint(name = "NetworkAccessSerPort")
    public NetworkAccessSer getNetworkAccessSerPort() {
        return super.getPort(NetworkAccessSerPort, NetworkAccessSer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NetworkAccessSer
     */
    @WebEndpoint(name = "NetworkAccessSerPort")
    public NetworkAccessSer getNetworkAccessSerPort(WebServiceFeature... features) {
        return super.getPort(NetworkAccessSerPort, NetworkAccessSer.class, features);
    }

}