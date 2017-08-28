package com.cn.easybuy.entity;

import java.util.Date;
import java.util.List;


public class Order {
	private int id;			//订单id
	private int userID;		//用户id
	private Date createTime;//创建时间
	private double cost;	//订单金额
	private int status;		//订单状态1 下单 2 审核通过 3 配货 4 送货中 5收获并确认，非空
	private List<OrderDetail> oraderDetail;	//订单详情列表
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<OrderDetail> getOraderDetail() {
		return oraderDetail;
	}
	public void setOraderDetail(List<OrderDetail> oraderDetail) {
		this.oraderDetail = oraderDetail;
	}
}
