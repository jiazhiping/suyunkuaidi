
package cn.itcast.crm.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.itcast.crm.service package. 
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

    private final static QName _AssignCustomers2FixedArea_QNAME = new QName("http://service.crm.itcast.cn/", "assignCustomers2FixedArea");
    private final static QName _AssignCustomers2FixedAreaResponse_QNAME = new QName("http://service.crm.itcast.cn/", "assignCustomers2FixedAreaResponse");
    private final static QName _FindAll_QNAME = new QName("http://service.crm.itcast.cn/", "findAll");
    private final static QName _FindAllResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findAllResponse");
    private final static QName _FindCustomerByTelephone_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephone");
    private final static QName _FindCustomerByTelephoneAndPassword_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephoneAndPassword");
    private final static QName _FindCustomerByTelephoneAndPasswordResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephoneAndPasswordResponse");
    private final static QName _FindCustomerByTelephoneResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephoneResponse");
    private final static QName _FindFixedAreaIdByAddress_QNAME = new QName("http://service.crm.itcast.cn/", "findFixedAreaIdByAddress");
    private final static QName _FindFixedAreaIdByAddressResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findFixedAreaIdByAddressResponse");
    private final static QName _FindGuanLianCustomers_QNAME = new QName("http://service.crm.itcast.cn/", "findGuanLianCustomers");
    private final static QName _FindGuanLianCustomersResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findGuanLianCustomersResponse");
    private final static QName _FindNoGuanLianCustomers_QNAME = new QName("http://service.crm.itcast.cn/", "findNoGuanLianCustomers");
    private final static QName _FindNoGuanLianCustomersResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findNoGuanLianCustomersResponse");
    private final static QName _Regist_QNAME = new QName("http://service.crm.itcast.cn/", "regist");
    private final static QName _RegistResponse_QNAME = new QName("http://service.crm.itcast.cn/", "registResponse");
    private final static QName _UpdateCustomerType_QNAME = new QName("http://service.crm.itcast.cn/", "updateCustomerType");
    private final static QName _UpdateCustomerTypeResponse_QNAME = new QName("http://service.crm.itcast.cn/", "updateCustomerTypeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.itcast.crm.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AssignCustomers2FixedArea }
     * 
     */
    public AssignCustomers2FixedArea createAssignCustomers2FixedArea() {
        return new AssignCustomers2FixedArea();
    }

    /**
     * Create an instance of {@link AssignCustomers2FixedAreaResponse }
     * 
     */
    public AssignCustomers2FixedAreaResponse createAssignCustomers2FixedAreaResponse() {
        return new AssignCustomers2FixedAreaResponse();
    }

    /**
     * Create an instance of {@link FindAll }
     * 
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     * 
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephone }
     * 
     */
    public FindCustomerByTelephone createFindCustomerByTelephone() {
        return new FindCustomerByTelephone();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephoneAndPassword }
     * 
     */
    public FindCustomerByTelephoneAndPassword createFindCustomerByTelephoneAndPassword() {
        return new FindCustomerByTelephoneAndPassword();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephoneAndPasswordResponse }
     * 
     */
    public FindCustomerByTelephoneAndPasswordResponse createFindCustomerByTelephoneAndPasswordResponse() {
        return new FindCustomerByTelephoneAndPasswordResponse();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephoneResponse }
     * 
     */
    public FindCustomerByTelephoneResponse createFindCustomerByTelephoneResponse() {
        return new FindCustomerByTelephoneResponse();
    }

    /**
     * Create an instance of {@link FindFixedAreaIdByAddress }
     * 
     */
    public FindFixedAreaIdByAddress createFindFixedAreaIdByAddress() {
        return new FindFixedAreaIdByAddress();
    }

    /**
     * Create an instance of {@link FindFixedAreaIdByAddressResponse }
     * 
     */
    public FindFixedAreaIdByAddressResponse createFindFixedAreaIdByAddressResponse() {
        return new FindFixedAreaIdByAddressResponse();
    }

    /**
     * Create an instance of {@link FindGuanLianCustomers }
     * 
     */
    public FindGuanLianCustomers createFindGuanLianCustomers() {
        return new FindGuanLianCustomers();
    }

    /**
     * Create an instance of {@link FindGuanLianCustomersResponse }
     * 
     */
    public FindGuanLianCustomersResponse createFindGuanLianCustomersResponse() {
        return new FindGuanLianCustomersResponse();
    }

    /**
     * Create an instance of {@link FindNoGuanLianCustomers }
     * 
     */
    public FindNoGuanLianCustomers createFindNoGuanLianCustomers() {
        return new FindNoGuanLianCustomers();
    }

    /**
     * Create an instance of {@link FindNoGuanLianCustomersResponse }
     * 
     */
    public FindNoGuanLianCustomersResponse createFindNoGuanLianCustomersResponse() {
        return new FindNoGuanLianCustomersResponse();
    }

    /**
     * Create an instance of {@link Regist }
     * 
     */
    public Regist createRegist() {
        return new Regist();
    }

    /**
     * Create an instance of {@link RegistResponse }
     * 
     */
    public RegistResponse createRegistResponse() {
        return new RegistResponse();
    }

    /**
     * Create an instance of {@link UpdateCustomerType }
     * 
     */
    public UpdateCustomerType createUpdateCustomerType() {
        return new UpdateCustomerType();
    }

    /**
     * Create an instance of {@link UpdateCustomerTypeResponse }
     * 
     */
    public UpdateCustomerTypeResponse createUpdateCustomerTypeResponse() {
        return new UpdateCustomerTypeResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignCustomers2FixedArea }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "assignCustomers2FixedArea")
    public JAXBElement<AssignCustomers2FixedArea> createAssignCustomers2FixedArea(AssignCustomers2FixedArea value) {
        return new JAXBElement<AssignCustomers2FixedArea>(_AssignCustomers2FixedArea_QNAME, AssignCustomers2FixedArea.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignCustomers2FixedAreaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "assignCustomers2FixedAreaResponse")
    public JAXBElement<AssignCustomers2FixedAreaResponse> createAssignCustomers2FixedAreaResponse(AssignCustomers2FixedAreaResponse value) {
        return new JAXBElement<AssignCustomers2FixedAreaResponse>(_AssignCustomers2FixedAreaResponse_QNAME, AssignCustomers2FixedAreaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findAllResponse")
    public JAXBElement<FindAllResponse> createFindAllResponse(FindAllResponse value) {
        return new JAXBElement<FindAllResponse>(_FindAllResponse_QNAME, FindAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephone")
    public JAXBElement<FindCustomerByTelephone> createFindCustomerByTelephone(FindCustomerByTelephone value) {
        return new JAXBElement<FindCustomerByTelephone>(_FindCustomerByTelephone_QNAME, FindCustomerByTelephone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephoneAndPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephoneAndPassword")
    public JAXBElement<FindCustomerByTelephoneAndPassword> createFindCustomerByTelephoneAndPassword(FindCustomerByTelephoneAndPassword value) {
        return new JAXBElement<FindCustomerByTelephoneAndPassword>(_FindCustomerByTelephoneAndPassword_QNAME, FindCustomerByTelephoneAndPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephoneAndPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephoneAndPasswordResponse")
    public JAXBElement<FindCustomerByTelephoneAndPasswordResponse> createFindCustomerByTelephoneAndPasswordResponse(FindCustomerByTelephoneAndPasswordResponse value) {
        return new JAXBElement<FindCustomerByTelephoneAndPasswordResponse>(_FindCustomerByTelephoneAndPasswordResponse_QNAME, FindCustomerByTelephoneAndPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephoneResponse")
    public JAXBElement<FindCustomerByTelephoneResponse> createFindCustomerByTelephoneResponse(FindCustomerByTelephoneResponse value) {
        return new JAXBElement<FindCustomerByTelephoneResponse>(_FindCustomerByTelephoneResponse_QNAME, FindCustomerByTelephoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindFixedAreaIdByAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findFixedAreaIdByAddress")
    public JAXBElement<FindFixedAreaIdByAddress> createFindFixedAreaIdByAddress(FindFixedAreaIdByAddress value) {
        return new JAXBElement<FindFixedAreaIdByAddress>(_FindFixedAreaIdByAddress_QNAME, FindFixedAreaIdByAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindFixedAreaIdByAddressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findFixedAreaIdByAddressResponse")
    public JAXBElement<FindFixedAreaIdByAddressResponse> createFindFixedAreaIdByAddressResponse(FindFixedAreaIdByAddressResponse value) {
        return new JAXBElement<FindFixedAreaIdByAddressResponse>(_FindFixedAreaIdByAddressResponse_QNAME, FindFixedAreaIdByAddressResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindGuanLianCustomers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findGuanLianCustomers")
    public JAXBElement<FindGuanLianCustomers> createFindGuanLianCustomers(FindGuanLianCustomers value) {
        return new JAXBElement<FindGuanLianCustomers>(_FindGuanLianCustomers_QNAME, FindGuanLianCustomers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindGuanLianCustomersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findGuanLianCustomersResponse")
    public JAXBElement<FindGuanLianCustomersResponse> createFindGuanLianCustomersResponse(FindGuanLianCustomersResponse value) {
        return new JAXBElement<FindGuanLianCustomersResponse>(_FindGuanLianCustomersResponse_QNAME, FindGuanLianCustomersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNoGuanLianCustomers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findNoGuanLianCustomers")
    public JAXBElement<FindNoGuanLianCustomers> createFindNoGuanLianCustomers(FindNoGuanLianCustomers value) {
        return new JAXBElement<FindNoGuanLianCustomers>(_FindNoGuanLianCustomers_QNAME, FindNoGuanLianCustomers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNoGuanLianCustomersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findNoGuanLianCustomersResponse")
    public JAXBElement<FindNoGuanLianCustomersResponse> createFindNoGuanLianCustomersResponse(FindNoGuanLianCustomersResponse value) {
        return new JAXBElement<FindNoGuanLianCustomersResponse>(_FindNoGuanLianCustomersResponse_QNAME, FindNoGuanLianCustomersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Regist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "regist")
    public JAXBElement<Regist> createRegist(Regist value) {
        return new JAXBElement<Regist>(_Regist_QNAME, Regist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "registResponse")
    public JAXBElement<RegistResponse> createRegistResponse(RegistResponse value) {
        return new JAXBElement<RegistResponse>(_RegistResponse_QNAME, RegistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCustomerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "updateCustomerType")
    public JAXBElement<UpdateCustomerType> createUpdateCustomerType(UpdateCustomerType value) {
        return new JAXBElement<UpdateCustomerType>(_UpdateCustomerType_QNAME, UpdateCustomerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCustomerTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "updateCustomerTypeResponse")
    public JAXBElement<UpdateCustomerTypeResponse> createUpdateCustomerTypeResponse(UpdateCustomerTypeResponse value) {
        return new JAXBElement<UpdateCustomerTypeResponse>(_UpdateCustomerTypeResponse_QNAME, UpdateCustomerTypeResponse.class, null, value);
    }

}
