package com.cn.easybuy.entity;


public class Cart {
	private int id;
	private String euUserid;
	private int epID;
	private int epQuantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEuUserid() {
		return euUserid;
	}
	public void setEuUserid(String euUserid) {
		this.euUserid = euUserid;
	}
	public int getEpID() {
		return epID;
	}
	public void setEpID(int epID) {
		this.epID = epID;
	}
	public int getEpQuantity() {
		return epQuantity;
	}
	public void setEpQuantity(int epQuantity) {
		this.epQuantity = epQuantity;
	}
}
