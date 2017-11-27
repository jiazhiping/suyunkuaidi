package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.CourierRepository;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.service.ICourierService;

@Service
@Transactional
public class CourierServiceImpl implements ICourierService {

	@Resource
	private CourierRepository courierRepository;

	@RequiresPermissions("courier")
	@Override
	public void add(Courier model) {
		courierRepository.save(model);
	}

	@Override
	public Page<Courier> pageQuery(Specification<Courier> spec, Pageable pageable) {
		return courierRepository.findAll(spec, pageable);
	}

	@Override
	public void batchDelete(String ids) {
		//1.获取subject对象
		Subject subject = SecurityUtils.getSubject();//已认证
		//2.根据subject调用权限校验方法
		subject.checkPermission("courier");
		//1.切割字符串
		String[] idsArr = ids.split(",");
		//2.循环获取id，根据id将deltag更新为1-已作废
		for(String id : idsArr){
			courierRepository.updateDeltag(Integer.parseInt(id));
		}
	}

	@Override
	public List<Courier> findNoDel() {
		return courierRepository.findNoDel();
	}

	@Override
	public void resove(String ids) {
		//1.切割字符串
				String[] idsArr = ids.split(",");
				//2.循环获取id，根据id将deltag更新为0还原
				for(String id : idsArr){
					courierRepository.resove(Integer.parseInt(id));
				}
		
	}



	
}
