package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import cn.itcast.bos.domain.base.Order;

public interface OrderRepository 
	extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
/**
 * 查询order
 * @param orderid
 * @return
 */
	@Query("from Order where id = ?1")
	Order findOne(String orderid);

	
	@Query("from Order where orderType = '手工分单'")
	List<Order> FindOrderByHand();
	
	
	@Query("update Order set orderType='调度分单' where id = ?1")
	@Modifying
	void updateOrderType(Integer id);


	@Query("from Order where id = ?1")
	Order findById(Integer id);

}
