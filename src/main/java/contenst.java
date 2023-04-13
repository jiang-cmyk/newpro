package main.java;

import java.util.Date;

public class contenst {

	public static Date stdate=new Date();
	public static Date endate=new Date();
	public static int  sumall=0;//所有企业数量

	public static int  cancel=0;//注销
	public static int  revocation=0;//吊销
	public static int  Emigration=0;//迁出
	public static int  new_this_mon=0;//本月成立
	

	public static Date getStdate() {
		return stdate;
	}
	public static void setStdate(Date stdate) {
		contenst.stdate = stdate;
	}
	public static Date getEndate() {
		return endate;
	}
	public static void setEndate(Date endate) {
		contenst.endate = endate;
	}
	public static int getSumall() {
		return sumall;
	}
	public static void setSumall(int sumall) {
		contenst.sumall = sumall;
	}
	public static int getCancel() {
		return cancel;
	}
	public static void setCancel(int cancel) {
		contenst.cancel = cancel;
	}
	public static int getRevocation() {
		return revocation;
	}
	public static void setRevocation(int revocation) {
		contenst.revocation = revocation;
	}
	public static int getEmigration() {
		return Emigration;
	}
	public static void setEmigration(int emigration) {
		Emigration = emigration;
	}
	public static int getNew_this_mon() {
		return new_this_mon;
	}
	public static void setNew_this_mon(int new_this_mon) {
		contenst.new_this_mon = new_this_mon;
	}
	

}
