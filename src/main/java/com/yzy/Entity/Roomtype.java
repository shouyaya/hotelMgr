package com.yzy.Entity;

/**
 * Roomtype 数据传输类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */
public class Roomtype implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String typeid;
	private String typename;
	private String price;

	/** setter and getter method */
	public void setTypeid(String typeid){
		this.typeid = typeid;
	}
	public String getTypeid(){
		return this.typeid;
	}
	public void setTypename(String typename){
		this.typename = typename;
	}
	public String getTypename(){
		return this.typename;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}

	@Override
	public String toString() {
		return "Roomtype{" +
				"typeid='" + typeid + '\'' +
				", typename='" + typename + '\'' +
				", price='" + price + '\'' +
				'}';
	}
}