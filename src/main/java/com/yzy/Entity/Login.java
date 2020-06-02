package com.yzy.Entity;

/**
 * Login 数据传输类
 * @author xlei @qq 251425887 @tel 13352818008
 * @Email dlei0009@163.com
 * @date 2020-05-21 22:51:24
 * @version 1.0
 */
public class Login implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int loginid;
	private String loginname;
	private String loginpwd;
	private String loginnickname;
	private int loginadmin;

	/** setter and getter method */
	public void setLoginid(int loginid){
		this.loginid = loginid;
	}
	public int getLoginid(){
		return this.loginid;
	}
	public void setLoginname(String loginname){
		this.loginname = loginname;
	}
	public String getLoginname(){
		return this.loginname;
	}
	public void setLoginpwd(String loginpwd){
		this.loginpwd = loginpwd;
	}
	public String getLoginpwd(){
		return this.loginpwd;
	}
	public void setLoginnickname(String loginnickname){
		this.loginnickname = loginnickname;
	}
	public String getLoginnickname(){
		return this.loginnickname;
	}
	public void setLoginadmin(int loginadmin){
		this.loginadmin = loginadmin;
	}
	public int getLoginadmin(){
		return this.loginadmin;
	}

	@Override
	public String toString() {
		return "Login{" +
				"loginid=" + loginid +
				", loginname='" + loginname + '\'' +
				", loginpwd='" + loginpwd + '\'' +
				", loginnickname='" + loginnickname + '\'' +
				", loginadmin=" + loginadmin +
				'}';
	}
}