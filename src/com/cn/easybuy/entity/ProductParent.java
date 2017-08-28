package com.cn.easybuy.entity;


public class ProductParent {
	private int parentID;		//父类商品类id
	private String parentName;	//父类商品类名称
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
