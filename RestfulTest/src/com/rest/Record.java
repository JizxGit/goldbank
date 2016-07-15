package com.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Record {
	private int ID;
	private int year;
	private int month;
	private int day;
	private String time;
	private double moneyin;
	private double moneyout;
	private double balance;
	private String kind;
	private String detail;
	private int userid;
	
	public Record(){
		
	}
	
	public Record(int ID, int year, int month, int day, String time, double moneyin, double moneyout, double balance,
			String kind, String detail, int userid) {
		super();
		this.ID = ID;
		this.year = year;
		this.month = month;
		this.day = day;
		this.time = time;
		this.moneyin = moneyin;
		this.moneyout = moneyout;
		this.balance = balance;
		this.kind = kind;
		this.detail = detail;
		this.userid = userid;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getMoneyin() {
		return moneyin;
	}
	public void setMoneyin(double moneyin) {
		this.moneyin = moneyin;
	}
	public double getMoneyout() {
		return moneyout;
	}
	public void setMoneyout(double moneyout) {
		this.moneyout = moneyout;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
