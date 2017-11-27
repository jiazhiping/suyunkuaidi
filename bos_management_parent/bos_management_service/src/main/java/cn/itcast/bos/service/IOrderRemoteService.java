package cn.itcast.bos.service;

import java.util.List;

import javax.jws.WebService;

import cn.itcast.bos.domain.base.Order;

@WebService
public interface IOrderRemoteService {

	public void saveOrder(Order order);

	public List<Order> FindOrderByHand();
	
	public void EditType(String id, Integer courierId);

}
