package com.cn.easybuy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.easybuy.dao.OrderDetailDao;
import com.cn.easybuy.entity.OrderDetail;
import com.cn.easybuy.util.BaseDao;


public class OrderDetailDaoImpl extends BaseDao implements OrderDetailDao {

	@Override
	public List<OrderDetail> getAllDetailByOrderID(int id) {
		// TODO Auto-generated method stub
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		String sql = "SELECT eod_id,eo_id,ep_id,eod_quantity,eod_cost"
				+ " FROM easybuy_order_detail WHERE eo_id=?";
		Object[] params = {id};
		try {
			rs = executeQuery(sql, params);
			while(rs.next()){
				OrderDetail detail = new OrderDetail();
				detail.setId(rs.getInt("eod_id"));
				detail.setOrderID(rs.getInt("eo_id"));
				detail.setProductID(rs.getInt("ep_id"));
				detail.setCost(rs.getDouble("eod_cost"));
				detail.setQuantity(rs.getInt("eod_quantity"));
				details.add(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return details;
	}

}
