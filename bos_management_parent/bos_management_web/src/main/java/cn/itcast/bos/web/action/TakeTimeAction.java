package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.ITakeTimeService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class TakeTimeAction extends BaseAction<TakeTime> {

	@Resource
	private ITakeTimeService takeTimeService;

	@Action(value = "takeTimeAction_add", results = { @Result(name = "success", location = "/pages/base/take_time.jsp"),
			@Result(name = "failure", location = "/unauthorizedUrl.jsp") })
	/**
	 * 工作时间保存
	 * 
	 * @return
	 */
	public String add() {
		try {
			takeTimeService.add(model);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Action(value = "takeTimeAction_pageQuery")
	public String pageQuery() throws IOException {
		
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<TakeTime> spec = new Specification<TakeTime>() {

			@Override
			public Predicate toPredicate(Root<TakeTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		};
		// 2.执行分页查询：总记录数和每页要显示的数据集合
		Page<TakeTime> page = takeTimeService.pageQuery(spec, pageable);
		String[] excludes = {};
		this.write2JsonObject(page, excludes);
		return NONE;

	}

}
