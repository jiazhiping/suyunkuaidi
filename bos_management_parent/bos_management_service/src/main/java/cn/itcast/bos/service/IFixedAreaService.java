package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.FixedArea;

public interface IFixedAreaService{

	void save(FixedArea model);

	Page<FixedArea> pageQuery(Specification<FixedArea> spec, Pageable pageable);

	void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId);


	void batchDelete(String ids);


	FixedArea findOne(String id);

	//void associationSubArea(String id,String SubAreaId);


}
