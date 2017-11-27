package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
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
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.ISubAreaService;
import cn.itcast.bos.utils.FileUtils;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class SubAreaAction extends BaseAction<SubArea> {

	@Resource
	private ISubAreaService subAreaService;

	@Action(value = "subareaAction_save", results = {
			@Result(name = "success", location = "/pages/base/sub_area.jsp") })
	public String add() {
		subAreaService.add(model);
		return "success";
	}

	/**
	 * 分页查询方法
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "subareaAction_pageQuery")
	public String pageQuery() throws IOException {
		// 1.封装查询参数
		// 参数1：当前页-1 firstindex = （当前页-1）*每页条数
		// 参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<SubArea> spec = new Specification<SubArea>() {
			/**
			 * root：保存当前实体类和表的映射关系 query：将多个查询条件组装成完整的查询条件 cb：组装单个条件，比如id =
			 * ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<SubArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		};
		// 2.执行分页查询：总记录数和每页要显示的数据集合
		Page<SubArea> page = subAreaService.pageQuery(spec, pageable);
		String[] excludes = { "subareas", "fixedArea" };
		this.write2JsonObject(page, excludes);
		return NONE;
	}

	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * 文件下载方法
	 * 
	 * @return
	 */
	@Action(value = "subareaAction_exportXls")
	public String exportXls() {
		try {
			// 1.判断是否选中了要导出的数据
			List<SubArea> list = null;
			if (StringUtils.isNotBlank(ids)) {
				// 2.选中了，根据选中的id查询分区集合
				list = subAreaService.findXuanZhongSubarea(ids);
			} else {
				// 3.没有选中，查询所有分区集合
				list = subAreaService.findAll();
			}
			// 4.创建一个excel
			// 4.1创建空excel
			HSSFWorkbook wb = new HSSFWorkbook();
			// 4.2创建空sheet页
			HSSFSheet sheet = wb.createSheet();
			// 4.3创建标题行row
			HSSFRow row = sheet.createRow(0);// 第一行从0开始
			// 4.4创建单元格，给单元格赋值
			row.createCell(0).setCellValue("分区编号");// 第一列从0开始
			row.createCell(1).setCellValue("分区地址");// 第一列从0开始
			row.createCell(2).setCellValue("分区辅助关键字");// 第一列从0开始
			row.createCell(3).setCellValue("分区关键字");// 第一列从0开始
			row.createCell(4).setCellValue("区域编号");// 第一列从0开始
			// 5.循环将数据放入excel
			if (null != list && list.size() > 0) {
				// 查询到分区
				int index = 1;
				for (SubArea subarea : list) {
					// 5.1创建数据行
					row = sheet.createRow(index++);
					// 5.2创建数据列,将数据保存到列上
					row.createCell(0).setCellValue(subarea.getId());// 第一列从0开始
					row.createCell(1).setCellValue(subarea.getAddress());// 第一列从0开始
					row.createCell(2).setCellValue(subarea.getAssistKeyWords());// 第一列从0开始
					row.createCell(3).setCellValue(subarea.getKeyWords());// 第一列从0开始
					Area area = subarea.getArea();
					if (null != area) {
						// 当前分区关联了区域
						row.createCell(4).setCellValue(area.getId());// 第一列从0开始
					} else {
						// 当前分区未关联区域
						row.createCell(4).setCellValue("未关联区域");
					}
				}
			}
			// 6.设置响应（response）参数：一个流两个头
			String filename = "分区数据.xls";
			//从request中获取浏览器类型
			String agent = ServletActionContext.getRequest().getHeader("User-Agent");
			String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
			//对文件名进行编码，
			filename = FileUtils.encodeDownloadFilename(filename, agent);
			// 6.1一个流：response的输出流
			ServletOutputStream os = ServletActionContext
					.getResponse().getOutputStream();
			// 6.2两个头之一：content-type，告诉浏览器返回数据的格式
			//通过application域根据文件类型获取该文件的mimeType
			ServletActionContext.getResponse().setContentType(mimeType);
			// 6.3两个头之二：content-disposition，告诉浏览器打开文件的方式，下载文件值：attachment;filename=文件名
			ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
			// 7.通过response将文件返回到前台
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 查询省的分区分布数据，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="subareaAction_findGroupedSubareas")
	public String findGroupedSubareas() throws IOException{
		//1.查询省的分区分布数据：[['河北省', 1],['河南省',3]]
		List<Object> list = subAreaService.findGroupedSubareas();
		String[] excludes = {};
		//2.转换成json数组，返回到界面
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	
}
