package com.cn.easybuy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.easybuy.dao.OrderDao;
import com.cn.easybuy.dao.OrderDetailDao;
import com.cn.easybuy.entity.Order;
import com.cn.easybuy.entity.OrderDetail;
import com.cn.easybuy.util.BaseDao;
import com.cn.easybuy.util.Pages;


public class OrderDaoImpl extends BaseDao implements OrderDao {

	private int getCount(String sql) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			String strSql = "select count(1) as num from ("+sql+") w";
			rs = executeQuery(strSql);
			if(rs.next()){
				result=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean updateStatusByID(int id, int status) {
		// TODO Auto-generated method stub
		boolean or = false;
		String sql = "UPDATE easybuy_order SET eo_status=? WHERE eo_id=?";
		Object[] params = {status,id};
		try {
			int result = executeUpdate(sql, params);
			if(result!=0){
				or = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return or;
	}
	@Override
	public List<Order> getOrderLimit(Pages page, String entityId, String userName) {
		// TODO Auto-generated method stub
				List<Order> orders = new ArrayList<Order>();
				String sql = "SELECT eo_id,eo_user_id,eo_create_time,eo_cost,eo_status"
						+ " FROM easybuy_order,easybuy_user"
						+ " WHERE eo_user_id=eu_user_id";
				if(entityId!=null && !entityId.equals("")){
					sql += " AND eo_id=" + entityId;
				}
				if(userName!=null && !userName.equals("")){
					sql += " AND eu_user_name='" + userName + "'";
				}
				
				OrderDetailDao dDao = new OrderDetailDaoImpl();
				page.setPageCount(this.getCount(sql));
				int start = (page.getPageIndex() - 1)*page.getPageSize();
				int end = page.getPageSize();
				
				if(page.getPageIndex()>page.getPageCount()){
					page.setPageIndex(page.getPageCount());
				}
				if(page.getPageIndex()>0){
					sql += " limit "+start+","+end; 
				}
				
				try {
					rs = executeQuery(sql);
					while(rs.next()){
						Order order = new Order();
						order.setId(rs.getInt("eo_id"));
						order.setUserID(rs.getInt("eo_user_id"));
						order.setCreateTime(rs.getDate("eo_create_time"));
						order.setCost(rs.getInt("eo_cost"));
						order.setStatus(rs.getInt("eo_status"));
						List<OrderDetail> details = dDao.getAllDetailByOrderID(order.getId());
						order.setOraderDetail(details);
						orders.add(order);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally {
					this.closeAll();
				}
				return orders;
	}
}
