package cn.itcast.bos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.domain.base.TakeTime;


public interface ITakeTimeService {

	void add(TakeTime model);

	Page<TakeTime> pageQuery(Specification<TakeTime> spec, Pageable pageable);

}
