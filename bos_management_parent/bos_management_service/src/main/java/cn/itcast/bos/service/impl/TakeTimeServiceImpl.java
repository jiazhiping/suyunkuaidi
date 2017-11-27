package cn.itcast.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.TakeTimeRepository;
import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.ITakeTimeService;

@Service
@Transactional
public class TakeTimeServiceImpl implements ITakeTimeService{

	@Resource
	private TakeTimeRepository takeTimeRepository;
	
	@Override
	public void add(TakeTime model) {
		takeTimeRepository.save(model);
		
	}

	@Override
	public Page<TakeTime> pageQuery(Specification<TakeTime> spec, Pageable pageable) {
		
		return takeTimeRepository.findAll(spec, pageable);
	}

}
