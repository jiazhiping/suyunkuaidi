package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.WayBill;

public interface IWayBillService{

	void add(WayBill model);

	

	void save(WayBill way);

/**
 * 工单的分页查询
 * @param spec
 * @param pageable
 * @return
 */

	Page<WayBill> pageQuery(Specification<WayBill> spec, Pageable pageable);

}
