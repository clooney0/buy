package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.OrderDetail;


public interface OrderDetailDao {
	public List<OrderDetail> getAllDetailByOrderID(int id);
}
