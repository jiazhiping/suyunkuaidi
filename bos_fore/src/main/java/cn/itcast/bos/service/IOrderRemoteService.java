package cn.itcast.bos.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-08-19T19:17:40.972+08:00
 * Generated source version: 3.1.10
 * 
 */
@WebService(targetNamespace = "http://service.bos.itcast.cn/", name = "IOrderRemoteService")
@XmlSeeAlso({ObjectFactory.class})
public interface IOrderRemoteService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "FindOrderByHand", targetNamespace = "http://service.bos.itcast.cn/", className = "cn.itcast.bos.service.FindOrderByHand")
    @WebMethod(operationName = "FindOrderByHand")
    @ResponseWrapper(localName = "FindOrderByHandResponse", targetNamespace = "http://service.bos.itcast.cn/", className = "cn.itcast.bos.service.FindOrderByHandResponse")
    public java.util.List<cn.itcast.bos.service.Order> findOrderByHand();

    @RequestWrapper(localName = "saveOrder", targetNamespace = "http://service.bos.itcast.cn/", className = "cn.itcast.bos.service.SaveOrder")
    @WebMethod
    @ResponseWrapper(localName = "saveOrderResponse", targetNamespace = "http://service.bos.itcast.cn/", className = "cn.itcast.bos.service.SaveOrderResponse")
    public void saveOrder(
        @WebParam(name = "arg0", targetNamespace = "")
        cn.itcast.bos.service.Order arg0
    );

    @RequestWrapper(localName = "EditType", targetNamespace = "http://service.bos.itcast.cn/", className = "cn.itcast.bos.service.EditType")
    @WebMethod(operationName = "EditType")
    @ResponseWrapper(localName = "EditTypeResponse", targetNamespace = "http://service.bos.itcast.cn/", className = "cn.itcast.bos.service.EditTypeResponse")
    public void editType(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.Integer arg1
    );
}
