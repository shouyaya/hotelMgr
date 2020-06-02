package com.yzy.Entity;

/**
 * Roominfo 数据传输类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */
public class Roominfo implements java.io.Serializable{

	private String roomid;
	private String typeid;
	private int floorid;
	private int ratednum;
	private int bednum;
	private String roomdescription;
	private String remark;
	private String status;
	private Roomtype roomtype;
	/** setter and getter method */
	public void setRoomid(String roomid){
		this.roomid = roomid;
	}
	public String getRoomid(){
		return this.roomid;
	}
	public void setTypeid(String typeid){
		this.typeid = typeid;
	}
	public String getTypeid(){
		return this.typeid;
	}
	public void setFloorid(int floorid){
		this.floorid = floorid;
	}
	public int getFloorid(){
		return this.floorid;
	}
	public void setRatednum(int ratednum){
		this.ratednum = ratednum;
	}
	public int getRatednum(){
		return this.ratednum;
	}
	public void setBednum(int bednum){
		this.bednum = bednum;
	}
	public int getBednum(){
		return this.bednum;
	}
	public void setRoomdescription(String roomdescription){
		this.roomdescription = roomdescription;
	}
	public String getRoomdescription(){
		return this.roomdescription;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}

	public Roomtype getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(Roomtype roomtype) {
		this.roomtype = roomtype;
	}

	@Override
	public String toString() {
		return "Roominfo{" +
				"roomid='" + roomid + '\'' +
				", typeid='" + typeid + '\'' +
				", floorid=" + floorid +
				", ratednum=" + ratednum +
				", bednum=" + bednum +
				", roomdescription='" + roomdescription + '\'' +
				", remark='" + remark + '\'' +
				", status='" + status + '\'' +
				", roomtype=" + roomtype +
				'}';
	}
}