package com.cn.easybuy.entity;


public class Product {
	private int id;				//商品id
	private String name;		//商品名称
	private String description;	//商品描述
	private double price;		//商品价格
	private int stock;			//商品库存
	private int epcID;			//商品分类id
	private String fileName;	//商品文件名
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getEpcID() {
		return epcID;
	}
	public void setEpcID(int epcID) {
		this.epcID = epcID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
