package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.Order;
import com.cn.easybuy.util.Pages;


public interface OrderDao {
	public List<Order> getOrderLimit(Pages page, String entityId,String userName);
	public boolean updateStatusByID(int id,int status);
}
