package com.yzy.Entity;

/**
 * Floorinfo 数据传输类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */
public class Floorinfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int floorid;
	private String floorname;

	/** setter and getter method */
	public void setFloorid(int floorid){
		this.floorid = floorid;
	}
	public int getFloorid(){
		return this.floorid;
	}
	public void setFloorname(String floorname){
		this.floorname = floorname;
	}
	public String getFloorname(){
		return this.floorname;
	}

}