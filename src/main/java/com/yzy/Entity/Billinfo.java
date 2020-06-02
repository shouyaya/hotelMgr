package com.yzy.Entity;

/**
 * Billinfo 数据传输类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:23
 * @version 1.0
 */
public class Billinfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int billid;
	private String checkid;
	private String costmoney;
	private String costdate;
	private String returndeposit;
	private String remark;

	/** setter and getter method */
	public void setBillid(int billid){
		this.billid = billid;
	}
	public int getBillid(){
		return this.billid;
	}
	public void setCheckid(String checkid){
		this.checkid = checkid;
	}
	public String getCheckid(){
		return this.checkid;
	}
	public void setCostmoney(String costmoney){
		this.costmoney = costmoney;
	}
	public String getCostmoney(){
		return this.costmoney;
	}
	public void setCostdate(String costdate){
		this.costdate = costdate;
	}
	public String getCostdate(){
		return this.costdate;
	}
	public void setReturndeposit(String returndeposit){
		this.returndeposit = returndeposit;
	}
	public String getReturndeposit(){
		return this.returndeposit;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}

}