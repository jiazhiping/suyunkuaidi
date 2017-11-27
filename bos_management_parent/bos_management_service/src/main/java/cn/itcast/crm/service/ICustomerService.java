package cn.itcast.crm.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-08-19T19:20:18.604+08:00
 * Generated source version: 3.1.10
 * 
 */
@WebService(targetNamespace = "http://service.crm.itcast.cn/", name = "ICustomerService")
@XmlSeeAlso({ObjectFactory.class})
public interface ICustomerService {

    @RequestWrapper(localName = "assignCustomers2FixedArea", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.AssignCustomers2FixedArea")
    @WebMethod
    @ResponseWrapper(localName = "assignCustomers2FixedAreaResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.AssignCustomers2FixedAreaResponse")
    public void assignCustomers2FixedArea(
        @WebParam(name = "arg0", targetNamespace = "")
        java.util.List<java.lang.Integer> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @RequestWrapper(localName = "updateCustomerType", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.UpdateCustomerType")
    @WebMethod
    @ResponseWrapper(localName = "updateCustomerTypeResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.UpdateCustomerTypeResponse")
    public void updateCustomerType(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findNoGuanLianCustomers", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindNoGuanLianCustomers")
    @WebMethod
    @ResponseWrapper(localName = "findNoGuanLianCustomersResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindNoGuanLianCustomersResponse")
    public java.util.List<cn.itcast.crm.service.Customer> findNoGuanLianCustomers();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findFixedAreaIdByAddress", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindFixedAreaIdByAddress")
    @WebMethod
    @ResponseWrapper(localName = "findFixedAreaIdByAddressResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindFixedAreaIdByAddressResponse")
    public java.lang.String findFixedAreaIdByAddress(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindAll")
    @WebMethod
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindAllResponse")
    public java.util.List<cn.itcast.crm.service.Customer> findAll();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByTelephone", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindCustomerByTelephone")
    @WebMethod
    @ResponseWrapper(localName = "findCustomerByTelephoneResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindCustomerByTelephoneResponse")
    public cn.itcast.crm.service.Customer findCustomerByTelephone(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByTelephoneAndPassword", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindCustomerByTelephoneAndPassword")
    @WebMethod
    @ResponseWrapper(localName = "findCustomerByTelephoneAndPasswordResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindCustomerByTelephoneAndPasswordResponse")
    public cn.itcast.crm.service.Customer findCustomerByTelephoneAndPassword(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findGuanLianCustomers", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindGuanLianCustomers")
    @WebMethod
    @ResponseWrapper(localName = "findGuanLianCustomersResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindGuanLianCustomersResponse")
    public java.util.List<cn.itcast.crm.service.Customer> findGuanLianCustomers(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @RequestWrapper(localName = "regist", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.Regist")
    @WebMethod
    @ResponseWrapper(localName = "registResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.RegistResponse")
    public void regist(
        @WebParam(name = "arg0", targetNamespace = "")
        cn.itcast.crm.service.Customer arg0
    );
}