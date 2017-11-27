package cn.itcast.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.FixedAreaRepository;
import cn.itcast.bos.dao.SubAreaRepository;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.IFixedAreaService;
import cn.itcast.bos.service.ISubAreaService;

@Service
@Transactional
public class SubAreaServiceImpl implements ISubAreaService {

	@Resource
	private SubAreaRepository subAreaRepository;

	
	public void add(SubArea model) {
		subAreaRepository.save(model);
	}

	
	public Page<SubArea> pageQuery(Specification<SubArea> spec, Pageable pageable) {
		return subAreaRepository.findAll(spec, pageable);
	}

	public List<SubArea> findXuanZhongSubarea(String ids) {
		//1.分割字符串
		String[] idsArr = ids.split(",");
		//2.循环查询放入集合并返回
		List<SubArea> list = new ArrayList<SubArea>();
		for(String id : idsArr){
			list.add(subAreaRepository.findOne(id));
		}
		return list;
	}

	
	public List<SubArea> findAll() {
		return subAreaRepository.findAll();
	}


	public List<Object> findGroupedSubareas() {
		return subAreaRepository.findGroupedSubareas();
	}


	
	
	public List<SubArea> findNoGuanLianFixedArea() {
		
		return subAreaRepository.findByFixedAreaIdIsNull();
	}

	
	public List<SubArea> findGuanLianFixedArea(String id) {
		//FixedArea fixedArea=fixedAreaRepository.findOne(id);
		
	
		return subAreaRepository.findGuanLianFixedArea(id);
	}

	@Resource
	private FixedAreaRepository fixedAreaRepository;
	public void assignSubArea(List<String> subAreaIds, String id) {
		//根据id查找fixedArea对象
		FixedArea fixedArea=fixedAreaRepository.findOne(id);
	//1.解除选中定区的原有的绑定关系
			subAreaRepository.updateFixedAreaIdIsNull(fixedArea);
		//2.循环绑定新的分区和定区
			if (null!=subAreaIds&&subAreaIds.size()>0) {
				for (String sid : subAreaIds) {
					subAreaRepository.updateFixedAreaId(fixedArea, sid);
				}
			}
	
	}

	//定区查看分区
	public List<SubArea> watchSubArea(String id) {
		
		return subAreaRepository.watchSubArea(id);
	}


	
	

	
}

