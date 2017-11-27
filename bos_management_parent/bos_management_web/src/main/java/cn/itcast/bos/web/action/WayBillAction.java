package cn.itcast.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.dao.OrderRepository;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.Order;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.domain.base.WayBill;
import cn.itcast.bos.service.IOrderRemoteService;
import cn.itcast.bos.service.IWayBillService;
import cn.itcast.bos.utils.FileUtils;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class WayBillAction extends BaseAction<WayBill> {
	/*@Resource
	private IOrderRemoteService oderRemoteServiceimpl;*/
	@Resource
	private OrderRepository orderRepository;
	@Resource
	private IWayBillService wayBillService;
	
	@Action(value="waybillAction_add")
	public String add() throws IOException{
		String flag = "1";
		try {
			wayBillService.add(model);
		} catch (Exception e) {
			flag = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	/**
	 * 创建一个空的Excel表格
	 */
	@Action(value="waybillAction_exportxls")
	public String wayBill() throws IOException {
		// 创建一个空的excel
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建空的sheet页
		HSSFSheet sheet = wb.createSheet();
		// 创建标题行
		HSSFRow row = sheet.createRow(0);
		// 创建单元格,给单元格赋值
		row.createCell(0).setCellValue("运单编号");
		row.createCell(1).setCellValue("订单信息");
		row.createCell(2).setCellValue("寄件人姓名");
		row.createCell(3).setCellValue("寄件人电话");
		row.createCell(4).setCellValue("寄件人公司");
		row.createCell(5).setCellValue("寄件人省市区信息");
		row.createCell(6).setCellValue("寄件人详细地址信息");
		row.createCell(7).setCellValue("收件人姓名");
		row.createCell(8).setCellValue("收件人电话");
		row.createCell(9).setCellValue("收件人公司");
		row.createCell(10).setCellValue("收件人省市区信息");
		row.createCell(11).setCellValue("收件人详细地址信息");
		row.createCell(12).setCellValue("快递产品类型编号");
		row.createCell(13).setCellValue("托寄物类型");
		row.createCell(14).setCellValue("支付类型编号");
		row.createCell(15).setCellValue("托寄物重量");
		row.createCell(16).setCellValue("备注");
		row.createCell(17).setCellValue("原件数");
		row.createCell(18).setCellValue("到达地");
		row.createCell(19).setCellValue("实际件数");
		row.createCell(20).setCellValue("实际重量");
		row.createCell(21).setCellValue("体积");
		row.createCell(22).setCellValue("配载要求");
		row.createCell(23).setCellValue("运单类型");
		row.createCell(24).setCellValue("签收状态");
		row.createCell(25).setCellValue("作废标志");

		// 设置响应参数
		String filename = "工单数据.xls";
		// 从request中获取浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		// 对文件名进行编码
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		// 6.1一个流：response的输出流
		ServletOutputStream s = ServletActionContext.getResponse().getOutputStream();
		// 6.2两个头之一：content-type，告诉浏览器返回数据的格式
		// 通过application域根据文件类型获取该文件的mimeType
		ServletActionContext.getResponse().setContentType(mimeType);
		// 6.3两个头之二：content-disposition，告诉浏览器打开文件的方式，下载文件值：attachment;filename=文件名
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
		// 7.通过response将文件返回到前台
		wb.write(s);
		return null;
	}
	
	
	//接受页面传来的文件
	private File wayBillFile;
	
public void setWayBillFile(File wayBillFile) {
		this.wayBillFile = wayBillFile;
	}
/**
 * 工单导入
 * @throws Exception 
 */
	@Action(value="wayBillAction_batchImport")
	public String inport() throws Exception{
		String s="success";//响应信息
		try {
			// 1.使用workbook读取整个excel
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(wayBillFile));
			// 2.获取单个sheet对象
			HSSFSheet sheet = wb.getSheetAt(0);
			
			// 3.循环获取行row对象
			
			for (Row row : sheet) {
				Order order = null;
				// 3.1跳过第一行标题行
				int rowNum = row.getRowNum();// 获取行号，从0开始
				if (0 == rowNum) {
					// 第一行，跳过
					continue;// 跳过本次循环，直接进入下一次循环
				}
				
				// 4.获取单元格cell对象,将对象封装到way中
				WayBill way=new WayBill();
				if(row.getCell(0)!=null){
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);										
			     }
				String wayBillNum = row.getCell(0).getStringCellValue();	
				if(row.getCell(1)!=null){
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);		
					String orderid = row.getCell(1).getStringCellValue();//获取订单信息的id	
				    order=orderRepository.findOne(Integer.valueOf(orderid));//根据订单id查询订单信息,得到一个订单对象
					way.setOrder(order);//将订单对象封装到运单中
			     }
						
				String sendName = row.getCell(2).getStringCellValue();
				String sendMobile = row.getCell(3).getStringCellValue();
				String  sendCompany= row.getCell(4).getStringCellValue();
				String sendArea = row.getCell(5).getStringCellValue();
				String sendAddress = row.getCell(6).getStringCellValue();
				String recName = row.getCell(7).getStringCellValue();
				//先判断是否为空,buweinull将其数字类型转换成"String类型,在取值
				if(row.getCell(8)!=null){
					row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);										
			     }
				String recMobile = row.getCell(8).getStringCellValue();
				String recCompany = row.getCell(9).getStringCellValue();
				String recArea = row.getCell(10).getStringCellValue();
				String recAddress = row.getCell(11).getStringCellValue();
				String sendProNum = row.getCell(12).getStringCellValue();
				String goodsType = row.getCell(13).getStringCellValue();
				if(row.getCell(14)!=null){
					row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);		
			     }
				String payTypeNum = row.getCell(14).getStringCellValue();
				if(row.getCell(15)!=null){
					row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);										
			     }
				String weight = row.getCell(15).getStringCellValue();
				String remark = row.getCell(16).getStringCellValue();
				if(row.getCell(17)!=null){
					row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);										
			     }
				String num = row.getCell(17).getStringCellValue();
				String arriveCity = row.getCell(18).getStringCellValue();
				if(row.getCell(19)!=null){
					row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);										
			     }
				String feeitemnum = row.getCell(19).getStringCellValue();
				if(row.getCell(20)!=null){
					row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);										
			     }
				String actlweit = row.getCell(20).getStringCellValue();
				String vol = row.getCell(21).getStringCellValue();
				String floadreqr = row.getCell(22).getStringCellValue();
				String wayBillType = row.getCell(23).getStringCellValue();
				if(row.getCell(24)!=null){
					row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);										
			     }
				String signStatus = row.getCell(24).getStringCellValue();
				String delTag = row.getCell(25).getStringCellValue();
		
				// 5.将数据封装到waybill对象				
		       way.setWayBillNum(wayBillNum);				
				way.setSendName(sendName);
				way.setSendMobile(sendMobile);
				way.setSendCompany(sendCompany);
				way.setSendArea(order.getSendArea());
				way.setSendAddress(sendAddress);
				way.setRecName(recName);
				way.setRecMobile(recMobile);
				way.setRecCompany(recCompany);
				way.setRecArea(order.getRecArea());
				way.setRecAddress(recAddress);
				way.setGoodsType(goodsType);
				way.setSendProNum(sendProNum);
				way.setWeight(Double.valueOf(weight));
				way.setRemark(remark);
				way.setNum(Integer.valueOf(num));				
				way.setArriveCity(arriveCity);
				way.setFeeitemnum(Integer.valueOf(feeitemnum));		//将String类型转换成Integer类型数据		
				way.setActlweit(Double.valueOf(actlweit));
				way.setVol(vol);
				way.setFloadreqr(floadreqr);
				way.setWayBillType(wayBillType);
				way.setSignStatus(Integer.valueOf(signStatus));
				way.setDelTag(delTag);
				//将封装好的对象放入集合中
				
				//保存数据
				wayBillService.save(way);				
			
		
			}
			} catch (Exception e) {
				s="failure";
			e.printStackTrace();
			
		}
		// 7.将flag标志位返回到前台
				ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
				ServletActionContext.getResponse().getWriter().print(s);
				return NONE;



	}
	
		
	/**
	 * 工单分页查询
	 * @throws Exception 
	 */
			@Action(value="wayBillAction_pageQuery")
			public String pageQuery() throws Exception{
				//1.封装查询参数
				//参数1：当前页-1 
				//参数2：每页条数
				Pageable pageable = new PageRequest(page-1, rows);
				Specification<WayBill> spec = new Specification<WayBill>() {
					/**
					 * root：保存当前实体类和表的映射关系 query：将多个查询条件组装成完整的查询条件 cb：组装单个条件，比如id =
					 * ?或name like ?
					 */
					@Override
					public Predicate toPredicate(Root<WayBill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
						return null;
					}
				};
				//2.执行分页查询：总记录数和每页要显示的数据集合
				Page<WayBill> page = wayBillService.pageQuery(spec, pageable);
				String[] excludes = {"recArea","order","sendArea"};
				this.write2JsonObject(page, excludes);
				return NONE;
	
			}
	

}
