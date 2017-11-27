package cn.itcast.bos.domain.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 待办事项
 * @author lenovo
 *
 */
@Entity
@Table(name = "T_DAIBAN")
public class DaiBan {

	
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id; // 主键
	@Column(name = "C_PRIORITY")
	private String priority; // 优先级  0重要
	@Column(name = "C_STATE")
	private String state; // 状态 0 正常
	@Column(name = "C_TYPE")
	private String type; // 单据类型
	@Column(name = "C_SENDER")
	private String sender; // 发送人
	@Column(name = "C_SENDDATE")
	private Date sendDate; // 发送日期
	
	public String getSendDateString(){
		if(sendDate!=null){
			return new SimpleDateFormat("yyyy-MM-dd").format(sendDate);
		}
		return "暂无数据";
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	@Override
	public String toString() {
		return "DaiBan [id=" + id + ", priority=" + priority + ", state=" + state + ", type=" + type + ", sender="
				+ sender + ", sendDate=" + sendDate + "]";
	}
	
	
}
