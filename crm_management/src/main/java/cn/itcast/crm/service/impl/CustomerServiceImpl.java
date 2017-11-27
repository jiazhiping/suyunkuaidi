package cn.itcast.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.crm.dao.CustomerRepository;
import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.ICustomerService;

@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Resource
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findNoGuanLianCustomers() {
		return customerRepository.findByFixedAreaIdIsNull();
	}

	@Override
	public List<Customer> findGuanLianCustomers(String fixedAreaId) {
		return customerRepository.findGuanLianCustomers(fixedAreaId);
	}

	@Override
	public void assignCustomers2FixedArea(Integer[] customerIds, String fixedAreaId) {
		//1.解除选中定区的原有的绑定关系（update customer set fixedareaid = null where fixedareaid = dq001）
		customerRepository.updateFixedAreaId2Null(fixedAreaId);
		//2.循环绑定新的客户和定区（update customer set fixedareaid = dq001 where id = 3;）
		if(null != customerIds && customerIds.length > 0){
			//有新客户需要绑定
			for(Integer id : customerIds){
				customerRepository.updateFixedAreaId(id, fixedAreaId);
			}
		}
	}

	@Override
	public void regist(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public Customer findCustomerByTelephone(String telephone) {
		return customerRepository.findByTelephone(telephone);
	}

	@Override
	public void updateCustomerType(Integer id) {
		customerRepository.updateCustomerType(id);
	}

	@Override
	public Customer findCustomerByTelephoneAndPassword(String telephone, String password) {
		return customerRepository.findByTelephoneAndPassword(telephone, password);
	}

	@Override
	public String findFixedAreaIdByAddress(String address) {
		return customerRepository.findFixedAreaIdByAddress(address);
	}

	@Override
	public List<Customer> findxianshiGuanLianCustomers(String fixedAreaId) {
		return customerRepository.findxianshiGuanLianCustomers(fixedAreaId);
	}

}
