package com.yzy.Entity;

/**
 * Checkininfo 数据传输类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */
public class Checkininfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String checkid;
	private String checkname;
	private String checkphone;
	private String checkidcard;
	private String typeid;
	private String arriretime;
	private String leavetime;
	private String checkstate;
	private int checknum;
	private String roomid;
	private String price;
	private String checkprice;
	private int discount;
	private String discountreason;
	private String addbed;
	private String addbedprice;
	private String deposit;
	private String checkmoney;  //预收款
	private String checkdate;
	private String remark;
	private String operatorid;

	/** setter and getter method */
	public void setCheckid(String checkid){
		this.checkid = checkid;
	}
	public String getCheckid(){
		return this.checkid;
	}
	public void setCheckname(String checkname){
		this.checkname = checkname;
	}
	public String getCheckname(){
		return this.checkname;
	}
	public void setCheckphone(String checkphone){
		this.checkphone = checkphone;
	}
	public String getCheckphone(){
		return this.checkphone;
	}
	public void setCheckidcard(String checkidcard){
		this.checkidcard = checkidcard;
	}
	public String getCheckidcard(){
		return this.checkidcard;
	}
	public void setTypeid(String typeid){
		this.typeid = typeid;
	}
	public String getTypeid(){
		return this.typeid;
	}
	public void setArriretime(String arriretime){
		this.arriretime = arriretime;
	}
	public String getArriretime(){
		return this.arriretime;
	}
	public void setLeavetime(String leavetime){
		this.leavetime = leavetime;
	}
	public String getLeavetime(){
		return this.leavetime;
	}
	public void setCheckstate(String checkstate){
		this.checkstate = checkstate;
	}
	public String getCheckstate(){
		return this.checkstate;
	}
	public void setChecknum(int checknum){
		this.checknum = checknum;
	}
	public int getChecknum(){
		return this.checknum;
	}
	public void setRoomid(String roomid){
		this.roomid = roomid;
	}
	public String getRoomid(){
		return this.roomid;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setCheckprice(String checkprice){
		this.checkprice = checkprice;
	}
	public String getCheckprice(){
		return this.checkprice;
	}
	public void setDiscount(int discount){
		this.discount = discount;
	}
	public int getDiscount(){
		return this.discount;
	}
	public void setDiscountreason(String discountreason){
		this.discountreason = discountreason;
	}
	public String getDiscountreason(){
		return this.discountreason;
	}
	public void setAddbed(String addbed){
		this.addbed = addbed;
	}
	public String getAddbed(){
		return this.addbed;
	}
	public void setAddbedprice(String addbedprice){
		this.addbedprice = addbedprice;
	}
	public String getAddbedprice(){
		return this.addbedprice;
	}
	public void setDeposit(String deposit){
		this.deposit = deposit;
	}
	public String getDeposit(){
		return this.deposit;
	}
	public void setCheckmoney(String checkmoney){
		this.checkmoney = checkmoney;
	}
	public String getCheckmoney(){
		return this.checkmoney;
	}
	public void setCheckdate(String checkdate){
		this.checkdate = checkdate;
	}
	public String getCheckdate(){
		return this.checkdate;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setOperatorid(String operatorid){
		this.operatorid = operatorid;
	}
	public String getOperatorid(){
		return this.operatorid;
	}

	public Checkininfo(String checkid, String checkname, String checkphone, String checkidcard, String typeid, String arriretime, String leavetime, String checkstate, int checknum, String roomid, String price, String checkprice, int discount, String discountreason, String addbed, String addbedprice, String deposit, String checkmoney, String checkdate, String remark, String operatorid) {
		this.checkid = checkid;
		this.checkname = checkname;
		this.checkphone = checkphone;
		this.checkidcard = checkidcard;
		this.typeid = typeid;
		this.arriretime = arriretime;
		this.leavetime = leavetime;
		this.checkstate = checkstate;
		this.checknum = checknum;
		this.roomid = roomid;
		this.price = price;
		this.checkprice = checkprice;
		this.discount = discount;
		this.discountreason = discountreason;
		this.addbed = addbed;
		this.addbedprice = addbedprice;
		this.deposit = deposit;
		this.checkmoney = checkmoney;
		this.checkdate = checkdate;
		this.remark = remark;
		this.operatorid = operatorid;
	}

	public Checkininfo() {
	}

	@Override
	public String toString() {
		return "Checkininfo{" +
				"checkid='" + checkid + '\'' +
				", checkname='" + checkname + '\'' +
				", checkphone='" + checkphone + '\'' +
				", checkidcard='" + checkidcard + '\'' +
				", typeid='" + typeid + '\'' +
				", arriretime='" + arriretime + '\'' +
				", leavetime='" + leavetime + '\'' +
				", checkstate='" + checkstate + '\'' +
				", checknum=" + checknum +
				", roomid='" + roomid + '\'' +
				", price='" + price + '\'' +
				", checkprice='" + checkprice + '\'' +
				", discount=" + discount +
				", discountreason='" + discountreason + '\'' +
				", addbed='" + addbed + '\'' +
				", addbedprice='" + addbedprice + '\'' +
				", deposit='" + deposit + '\'' +
				", checkmoney='" + checkmoney + '\'' +
				", checkdate='" + checkdate + '\'' +
				", remark='" + remark + '\'' +
				", operatorid='" + operatorid + '\'' +
				'}';
	}
}