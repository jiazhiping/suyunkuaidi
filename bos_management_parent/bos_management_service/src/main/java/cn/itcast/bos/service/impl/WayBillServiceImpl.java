package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.WayBillRepository;
import cn.itcast.bos.domain.base.WayBill;
import cn.itcast.bos.service.IWayBillService;

@Service
@Transactional
public class WayBillServiceImpl implements IWayBillService {

	@Resource
	private WayBillRepository wayBillRepository;

	@Override
	public void add(WayBill model) {
		wayBillRepository.save(model);
	}

	/**
	 * 保存工单
	 */
	@Override
	public void save(WayBill way) {
		wayBillRepository.save(way);

	}
/**
 * 工单的分页查询
 */
	@Override
	public Page<WayBill> pageQuery(Specification<WayBill> spec, Pageable pageable) {
		
		return  wayBillRepository.findAll(spec,pageable);
	}

}
