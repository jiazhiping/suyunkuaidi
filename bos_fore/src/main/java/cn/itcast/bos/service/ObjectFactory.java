
package cn.itcast.bos.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.itcast.bos.service package. 
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

    private final static QName _EditType_QNAME = new QName("http://service.bos.itcast.cn/", "EditType");
    private final static QName _EditTypeResponse_QNAME = new QName("http://service.bos.itcast.cn/", "EditTypeResponse");
    private final static QName _FindOrderByHand_QNAME = new QName("http://service.bos.itcast.cn/", "FindOrderByHand");
    private final static QName _FindOrderByHandResponse_QNAME = new QName("http://service.bos.itcast.cn/", "FindOrderByHandResponse");
    private final static QName _SaveOrder_QNAME = new QName("http://service.bos.itcast.cn/", "saveOrder");
    private final static QName _SaveOrderResponse_QNAME = new QName("http://service.bos.itcast.cn/", "saveOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.itcast.bos.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EditType }
     * 
     */
    public EditType createEditType() {
        return new EditType();
    }

    /**
     * Create an instance of {@link EditTypeResponse }
     * 
     */
    public EditTypeResponse createEditTypeResponse() {
        return new EditTypeResponse();
    }

    /**
     * Create an instance of {@link FindOrderByHand }
     * 
     */
    public FindOrderByHand createFindOrderByHand() {
        return new FindOrderByHand();
    }

    /**
     * Create an instance of {@link FindOrderByHandResponse }
     * 
     */
    public FindOrderByHandResponse createFindOrderByHandResponse() {
        return new FindOrderByHandResponse();
    }

    /**
     * Create an instance of {@link SaveOrder }
     * 
     */
    public SaveOrder createSaveOrder() {
        return new SaveOrder();
    }

    /**
     * Create an instance of {@link SaveOrderResponse }
     * 
     */
    public SaveOrderResponse createSaveOrderResponse() {
        return new SaveOrderResponse();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link Courier }
     * 
     */
    public Courier createCourier() {
        return new Courier();
    }

    /**
     * Create an instance of {@link FixedArea }
     * 
     */
    public FixedArea createFixedArea() {
        return new FixedArea();
    }

    /**
     * Create an instance of {@link SubArea }
     * 
     */
    public SubArea createSubArea() {
        return new SubArea();
    }

    /**
     * Create an instance of {@link Area }
     * 
     */
    public Area createArea() {
        return new Area();
    }

    /**
     * Create an instance of {@link Standard }
     * 
     */
    public Standard createStandard() {
        return new Standard();
    }

    /**
     * Create an instance of {@link TakeTime }
     * 
     */
    public TakeTime createTakeTime() {
        return new TakeTime();
    }

    /**
     * Create an instance of {@link WayBill }
     * 
     */
    public WayBill createWayBill() {
        return new WayBill();
    }

    /**
     * Create an instance of {@link WorkBill }
     * 
     */
    public WorkBill createWorkBill() {
        return new WorkBill();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bos.itcast.cn/", name = "EditType")
    public JAXBElement<EditType> createEditType(EditType value) {
        return new JAXBElement<EditType>(_EditType_QNAME, EditType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bos.itcast.cn/", name = "EditTypeResponse")
    public JAXBElement<EditTypeResponse> createEditTypeResponse(EditTypeResponse value) {
        return new JAXBElement<EditTypeResponse>(_EditTypeResponse_QNAME, EditTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOrderByHand }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bos.itcast.cn/", name = "FindOrderByHand")
    public JAXBElement<FindOrderByHand> createFindOrderByHand(FindOrderByHand value) {
        return new JAXBElement<FindOrderByHand>(_FindOrderByHand_QNAME, FindOrderByHand.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOrderByHandResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bos.itcast.cn/", name = "FindOrderByHandResponse")
    public JAXBElement<FindOrderByHandResponse> createFindOrderByHandResponse(FindOrderByHandResponse value) {
        return new JAXBElement<FindOrderByHandResponse>(_FindOrderByHandResponse_QNAME, FindOrderByHandResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bos.itcast.cn/", name = "saveOrder")
    public JAXBElement<SaveOrder> createSaveOrder(SaveOrder value) {
        return new JAXBElement<SaveOrder>(_SaveOrder_QNAME, SaveOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bos.itcast.cn/", name = "saveOrderResponse")
    public JAXBElement<SaveOrderResponse> createSaveOrderResponse(SaveOrderResponse value) {
        return new JAXBElement<SaveOrderResponse>(_SaveOrderResponse_QNAME, SaveOrderResponse.class, null, value);
    }

}
