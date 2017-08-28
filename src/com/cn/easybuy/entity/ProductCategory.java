package com.cn.easybuy.entity;


public class ProductCategory {
	private int id;			//子类商品类id
	private String name;	//子类商品类名称
	private int parentID;	//子类商品类对应的父类归属id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
}
