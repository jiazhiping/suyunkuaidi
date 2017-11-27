package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.CourierRepository;
import cn.itcast.bos.dao.FixedAreaRepository;
import cn.itcast.bos.dao.SubAreaRepository;
import cn.itcast.bos.dao.TakeTimeRepository;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.IFixedAreaService;

@Service
@Transactional
public class FixedAreaServiceImpl implements IFixedAreaService {

	@Resource
	private FixedAreaRepository fixedAreaRepository;
	@Resource
	private CourierRepository courierRepository;
	@Resource
	private TakeTimeRepository takeTimeRepository;
	@Resource
	private SubAreaRepository subAreaRepository;
	

	@Override
	public void save(FixedArea model) {
		fixedAreaRepository.save(model);
	}

	@Override
	public Page<FixedArea> pageQuery(Specification<FixedArea> spec, Pageable pageable) {
		return fixedAreaRepository.findAll(spec, pageable);
	}

	@Override
	public void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId) {
		//1.关联定区和快递员：将快递员对象添加到定区的快递员集合中
		/**
		 * hibernate持久化对象的三种状态：
		 * 瞬时态：没有id，没有session，比如刚通过new创建的对象都是瞬时态
		 * 持久态：有id，有session，比如通过find、update、insert操作获取的对象都是持久态，持久态对象可以自动更新数据库
		 * 游离态：有id，但没有session，比如delete操作获取的对象，或者是new的对象设置了id，
		 */
		//1.1查询定区对象
		FixedArea fixedArea = fixedAreaRepository.findOne(id);
		//1.2查询快递员对象
		Courier courier = courierRepository.findOne(courierId);
		//1.3将快递员对象添加到定区的快递员集合中
		fixedArea.getCouriers().add(courier);//持久态对象
		//2.关联快递员和收派时间：将收派时间的对象设置到快递员的收派时间属性上
		//2.1查询收派时间对象
		TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
		//2.2将收派时间的对象设置到快递员的收派时间属性上
		courier.setTakeTime(takeTime);//持久态对象
	}

	@Override
	public void batchDelete(String ids) {
		//1.切割字符串
		String[] idsArr = ids.split(",");
		//2.循环获取id，根据id将其删除
		for(String id : idsArr){
			fixedAreaRepository.delete(id);
		}
		
	}

	@Override
	public FixedArea findOne(String id) {
		
		return fixedAreaRepository.findOne(id);
	}

}
