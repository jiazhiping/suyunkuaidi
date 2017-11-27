package cn.itcast.bos.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.AreaRepository;
import cn.itcast.bos.dao.CourierRepository;
import cn.itcast.bos.dao.FixedAreaRepository;
import cn.itcast.bos.dao.OrderRepository;
import cn.itcast.bos.dao.WorkBillRepository;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.Order;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.domain.base.WorkBill;
import cn.itcast.bos.service.IOrderRemoteService;
import cn.itcast.bos.utils.SmsUtils;
import cn.itcast.crm.service.ICustomerService;

@Transactional
public class OrderRemoteServiceImpl implements IOrderRemoteService {

	@Resource
	private OrderRepository orderRepository;
	@Resource
	private ICustomerService customerService;
	@Resource
	private FixedAreaRepository fixedAreaRepository;
	@Resource
	private WorkBillRepository workBillRepository;
	@Resource
	private AreaRepository areaRepository;
	@Resource
	private CourierRepository courierRepository;

	@Override
	public void saveOrder(Order order) {
		// 1.设置订单号
		order.setOrderNum(UUID.randomUUID().toString());
		// 2.设置订单时间
		order.setOrderTime(new Date());// 当前系统时间
		Area sendArea = order.getSendArea();// 发件人的区域
		Area recArea = order.getRecArea();// 收件人的区域
		order.setSendArea(null);
		order.setRecArea(null);
		// 3.保存订单
		orderRepository.save(order);
		// 4.自动分单：客户详细地址完全匹配自动分单
		// 4.1获取客户详细地址
		String sendAddress = order.getSendAddress();// 发件人详细地址
		String fixedAreaId = customerService.findFixedAreaIdByAddress(sendAddress);
		if (StringUtils.isNotBlank(fixedAreaId)) {
			// 查询到定区
			// 4.2根据定区id查询定区对象
			FixedArea fixedArea = fixedAreaRepository.findOne(fixedAreaId);
			// 4.3从定区对象中获取快递员集合
			Set<Courier> couriers = fixedArea.getCouriers();
			for (Courier courier : couriers) {
				// 4.4从集合中获取快递员
				if (null != courier) {
					// 4.5设置订单的分单类型：自动分单
					order.setOrderType("自动分单");
					// 4.6订单关联快递员
					order.setCourier(courier);
					// 4.7给快递员生成工单
					WorkBill workbill = new WorkBill();
					workbill.setAttachbilltimes(0);// 追单次数，新单是0
					workbill.setBuildtime(new Date());// 工单生成时间，当前系统时间
					workbill.setCourier(courier);// 关联工单和快递员
					workbill.setOrder(order);// 关联工单和订单
					workbill.setPickstate("未取件");// 取件状态：未取件、已取件
					workbill.setRemark(order.getRemark());// 备注，订单中的备注信息
					workbill.setSmsNumber(RandomStringUtils.randomNumeric(4));// 取件短信验证码
					workbill.setType("新单");// 工单类型：新单、追单、销单

					workBillRepository.save(workbill);
					// 4.8给快递员发送短信通知
					/*
					 * String msg = "工单信息：请到"+sendAddress+"取件，客户电话：" +
					 * order.getSendMobile();
					 */
					String msg = "尊敬的用户您好，本次获取的验证码为：" + workbill.getSmsNumber() + "，服务电话：4006184000【传智播客】";
					// SmsUtils.sendSmsByWebService(courier.getTelephone(),
					// msg);
					System.out.println(msg);
					return;
				}
			}
		}
		// 5.自动分单：分区关键字匹配自动分单
		// 5.1根据客户提供的省市区查询区域
		Area area = areaRepository.findByProvinceAndCityAndDistrict(sendArea.getProvince(), sendArea.getCity(),
				sendArea.getDistrict());
		// 5.2根据区域获取该区域的分区集合
		Set<SubArea> subareas = area.getSubareas();
		if (null != subareas && subareas.size() > 0) {
			// 5.3判断如果分区集合不为null，循环比对分区的关键字辅助关键字和客户的详细地址，
			for (SubArea subarea : subareas) {
				if (sendAddress.contains(subarea.getKeyWords()) && sendAddress.contains(subarea.getAssistKeyWords())) {
					// 如果匹配说明客户住在该小区
					// 5.4根据该分区获取定区
					FixedArea fixedArea = subarea.getFixedArea();
					// 5.5从定区对象中获取快递员集合
					Set<Courier> couriers = fixedArea.getCouriers();
					for (Courier courier : couriers) {
						// 5.6从集合中获取快递员
						if (null != courier) {
							// 5.7设置订单的分单类型：自动分单
							order.setOrderType("自动分单");
							// 5.8订单关联快递员
							order.setCourier(courier);
							// 5.9给快递员生成工单
							WorkBill workbill = new WorkBill();
							workbill.setAttachbilltimes(0);// 追单次数，新单是0
							workbill.setBuildtime(new Date());// 工单生成时间，当前系统时间
							workbill.setCourier(courier);// 关联工单和快递员
							workbill.setOrder(order);// 关联工单和订单
							workbill.setPickstate("未取件");// 取件状态：未取件、已取件
							workbill.setRemark(order.getRemark());// 备注，订单中的备注信息
							workbill.setSmsNumber(RandomStringUtils.randomNumeric(4));// 取件短信验证码
							workbill.setType("新单");// 工单类型：新单、追单、销单

							workBillRepository.save(workbill);
							// 5.10给快递员发送短信通知
							String msg = "工单信息：请到" + sendAddress + "取件，客户电话：" + order.getSendMobile();
							// SmsUtils.sendSmsByWebService(courier.getTelephone(),
							// msg);
							return;
						}
					}
				}
			}
		}
		// 6.手工分单：
		// 6.1设置订单的分单类型：手工分单
		order.setOrderType("手工分单");
	}

	/**
	 * 查询分单方式为人工调度的订单
	 * 
	 * @return
	 */
	@Override
	public List<Order> FindOrderByHand() {

		return orderRepository.FindOrderByHand();
	}

	/**
	 * 修改调度后的订单状态 调度分单
	 */
	@Override
	public void EditType(String id, Integer courierId) {
		// 更新type
		orderRepository.updateOrderType(Integer.valueOf(id));

		// 查询更新后的订单
		Order order = orderRepository.findById(Integer.valueOf(id));
		Courier courier = courierRepository.findById(courierId);

		// 订单的关联快递员
		order.setCourier(courier);

		// 给快递员生成工单
		WorkBill workbill = new WorkBill();
		// 新单(追单0)
		workbill.setAttachbilltimes(0);
		// 工单生成时间
		workbill.setBuildtime(new Date());
		// 关联工单和快递员
		// 取件的状态 取 没取
		workbill.setPickstate("未取件");
		// 备注 订单中的备注信息
		workbill.setRemark(order.getRemark());
		// 取件验证码
		workbill.setSmsNumber(RandomStringUtils.randomNumeric(4));
		// 工单类型：新单、追单、销单
		workbill.setType("新单");

		workBillRepository.save(workbill);
		workbill.setCourier(courier);
		String msg = "请到" + order.getSendAddress() + "取件，客户电话：" + order.getSendMobile() + "取件验证码："
				+ workbill.getSmsNumber();
		System.out.println(msg);
		return;

	}

}
