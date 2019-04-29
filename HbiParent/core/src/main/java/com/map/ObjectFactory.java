
package com.map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.map package. 
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

    private final static QName _GetCallBackResponse_QNAME = new QName("http://service.webservice.activity.hand.com/", "getCallBackResponse");
    private final static QName _GetCallBack_QNAME = new QName("http://service.webservice.activity.hand.com/", "getCallBack");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.map
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCallBack }
     * 
     */
    public GetCallBack createGetCallBack() {
        return new GetCallBack();
    }

    /**
     * Create an instance of {@link GetCallBackResponse }
     * 
     */
    public GetCallBackResponse createGetCallBackResponse() {
        return new GetCallBackResponse();
    }

    /**
     * Create an instance of {@link RequestVo }
     * 
     */
    public RequestVo createRequestVo() {
        return new RequestVo();
    }

    /**
     * Create an instance of {@link CallProductVo }
     * 
     */
    public CallProductVo createCallProductVo() {
        return new CallProductVo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCallBackResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.activity.hand.com/", name = "getCallBackResponse")
    public JAXBElement<GetCallBackResponse> createGetCallBackResponse(GetCallBackResponse value) {
        return new JAXBElement<GetCallBackResponse>(_GetCallBackResponse_QNAME, GetCallBackResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCallBack }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.activity.hand.com/", name = "getCallBack")
    public JAXBElement<GetCallBack> createGetCallBack(GetCallBack value) {
        return new JAXBElement<GetCallBack>(_GetCallBack_QNAME, GetCallBack.class, null, value);
    }

}
