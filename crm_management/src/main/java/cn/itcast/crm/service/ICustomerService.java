package cn.itcast.crm.service;

import java.util.List;

import javax.jws.WebService;

import cn.itcast.crm.domain.Customer;

@WebService
public interface ICustomerService {

	public List<Customer> findAll();
	//查询所有未关联定区（fixedAreaId is null）的客户信息，返回
	public List<Customer> findNoGuanLianCustomers();
	//查询所有关联选中定区（fixedAreaId = ?1）的客户信息，返回
	public List<Customer> findGuanLianCustomers(String fixedAreaId);
	//将客户绑定到选中的定区上
	public void assignCustomers2FixedArea(Integer[] customerIds, String fixedAreaId);
	//客户注册方法
	public void regist(Customer customer);
	//根据客户电话查询客户激活状态
	public Customer findCustomerByTelephone(String telephone);
	//根据客户id将客户激活状态（type）更新成1
	public void updateCustomerType(Integer id);
	//根据客户电话和密码查询客户信息
	public Customer findCustomerByTelephoneAndPassword(String telephone, String password);
	//根据客户详细地址，查询客户所属定区id
	public String findFixedAreaIdByAddress(String address);
	
	//查询所有关联选中定区（fixedAreaId = ?1）的客户信息，返回
	public List<Customer> findxianshiGuanLianCustomers(String fixedAreaId);
}
