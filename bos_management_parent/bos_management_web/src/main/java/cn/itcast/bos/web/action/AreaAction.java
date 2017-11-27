package cn.itcast.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
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

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.IAreaService;
import cn.itcast.bos.utils.FileUtils;
import cn.itcast.bos.utils.PinYin4jUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class AreaAction extends BaseAction<Area> {

	@Resource
	private IAreaService areaService;

	private File areaFile;

	public void setAreaFile(File areaFile) {
		this.areaFile = areaFile;
	}

	@Resource
	private DataSource dataSource;

	/**
	 * 文件上传方法
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "areaAction_importXls")
	public String importXls() throws IOException {
		String flag = "1";// 标志位：1-成功；0-失败
		try {
			// 1.使用workbook读取整个excel
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(areaFile));
			// 2.获取单个sheet对象
			HSSFSheet sheet = wb.getSheetAt(0);
			// 3.循环获取行row对象
			List<Area> list = new ArrayList<Area>();
			for (Row row : sheet) {
				// 3.1跳过第一行标题行
				int rowNum = row.getRowNum();// 获取行号，从0开始
				if (0 == rowNum) {
					// 第一行，跳过
					continue;// 跳过本次循环，直接进入下一次循环
				}
				// 4.获取单元格cell对象
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				// 5.将数据封装到area对象
				Area area = new Area();
				area.setId(id);
				area.setProvince(province);
				area.setCity(city);
				area.setDistrict(district);
				area.setPostcode(postcode);

				province = province.substring(0, province.length() - 1);
				city = city.substring(0, city.length() - 1);
				district = district.substring(0, district.length() - 1);
				String temp = province + city + district;
				String[] headByString = PinYin4jUtils.getHeadByString(temp);// [H,B,S,J,Z,K,F]
				String shortcode = StringUtils.join(headByString, "");
				area.setShortcode(shortcode);
				// 2.citycode：城市码
				String citycode = PinYin4jUtils.hanziToPinyin(city, "");
				area.setCitycode(citycode);

				list.add(area);
			}
			// 6.保存区域数据
			areaService.batchSave(list);
		} catch (IOException e) {
			flag = "0";
			e.printStackTrace();
		}
		// 7.将flag标志位返回到前台
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}

	/**
	 * 分页查询方法
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "areaAction_pageQuery")
	public String pageQuery() throws IOException {
		// 1.封装查询参数
		// 参数1：当前页-1 firstindex = （当前页-1）*每页条数
		// 参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<Area> spec = new Specification<Area>() {
			/**
			 * root：保存当前实体类和表的映射关系 query：将多个查询条件组装成完整的查询条件 cb：组装单个条件，比如id =
			 * ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				// 1.获取查询条件数据:province
				String province = model.getProvince();
				if (StringUtils.isNotBlank(province)) {
					// 录入了条件
					list.add(cb.like(root.get("province").as(String.class), "%" + province + "%"));
				}
				// 2.获取查询条件数据:city
				String city = model.getCity();
				if (StringUtils.isNotBlank(city)) {
					// 录入了条件
					list.add(cb.like(root.get("city").as(String.class), "%" + city + "%"));
				}
				// 1.获取查询条件数据:district
				String district = model.getDistrict();
				if (StringUtils.isNotBlank(district)) {
					// 录入了条件
					list.add(cb.like(root.get("district").as(String.class), "%" + district + "%"));
				}

				Predicate[] pre = new Predicate[list.size()];
				return query.where(list.toArray(pre)).getRestriction();
			}
		};
		// 2.执行分页查询：总记录数和每页要显示的数据集合
		Page<Area> page = areaService.pageQuery(spec, pageable);
		String[] excludes = { "subareas" };
		this.write2JsonObject(page, excludes);
		return NONE;
	}

	/**
	 * 查询所有的区域信息，转换成json数组，返回到界面
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "areaAction_findAll")
	public String findAll() throws IOException {
		List<Area> list = areaService.findAll();
		String[] excludes = { "subareas" };
		this.write2JsonArray(list, excludes);
		return NONE;
	}

	/**
	 * 查询所有的区域数据，放到pdf文件中，返回到界面
	 * 
	 * @return
	 */
	@Action(value = "areaAction_exportPDF")
	public String exportPDF() {
		try {
			// 1.查询所有的区域数据
			List<Area> list = areaService.findAll();
			if (null != list && list.size() > 0) {
				// 有区域数据
				// 2.设置response响应头：一个流，两个头，解决文件名中文乱码问题
				String filename = "区域数据报表.pdf";
				String agent = ServletActionContext.getRequest().getHeader("User-Agent");
				filename = FileUtils.encodeDownloadFilename(filename, agent);
				ServletOutputStream os = ServletActionContext.getResponse().getOutputStream();
				ServletActionContext.getResponse().setContentType("application/pdf");
				ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
				// 3.使用jsperreport生成pdf
				// 3.1获取模版文件绝对路径
				String jaserfile = ServletActionContext.getServletContext()
						.getRealPath("/WEB-INF/jsper/areareport2.jrxml");
				// 3.2读取模板文件
				JasperReport report = JasperCompileManager.compileReport(jaserfile);
				// 3.3设置paramenter变量
				Map<String, Object> paramenters = new HashMap<String, Object>();
				paramenters.put("company", "传智播客");
				// 3.4设置field变量，给pdf文件中的paraments和fild赋值
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, paramenters,
						new JRBeanCollectionDataSource(list));
				// 4.将pdf文件返回到界面
				JRPdfExporter jrpdfExporter = new JRPdfExporter();
				// 4.1设置pdf文件
				jrpdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
				// 4.2设置输出流
				jrpdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, os);
				// 4.3将pdf返回到界面
				jrpdfExporter.exportReport();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 区域添加
	 * 
	 * @return
	 */
	@Action(value = "areaAction_add", results = { @Result(name = "success", location = "/pages/base/area.jsp") })
	public String add() {
		
		areaService.add(model);
		return "success";
	}
	
	
	/**
	 * 区域修改
	 * @return
	 */
	@Action(value = "areaAction_edit", results = { @Result(name = "success", location = "/pages/base/area.jsp") })
	public String edit() {
		areaService.edit(model);
		return "success";
	}
}
