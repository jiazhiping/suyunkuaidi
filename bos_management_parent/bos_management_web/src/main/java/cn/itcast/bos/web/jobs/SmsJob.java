package cn.itcast.bos.web.jobs;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.domain.base.WorkBill;
import cn.itcast.bos.service.IWorkBillService;
import cn.itcast.bos.utils.MailUtils;
import cn.itcast.bos.utils.SmsUtils;

public class SmsJob {

	@Resource
	private IWorkBillService workBillService;
	
	public void sendMsg(){
		//1.查询所有未取件的工单信息
		List<WorkBill> list = workBillService.findAllNoPick();
		//2.循环工单信息，给工单关联的快递员发短信
		if(null != list && list.size() > 0){
			//有工单需要处理
			for(WorkBill workbill : list){
				String msg = "尊敬的用户您好，本次获取的验证码为："+workbill.toString()+"，服务电话：4006184000【传智播客】";
				//给工单关联的快递员发短信
				MailUtils.sendMail("新单通知", msg, "itcast_demo@itheima.com");
			}
		}
	}
}
