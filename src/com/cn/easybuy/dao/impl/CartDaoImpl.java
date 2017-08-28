package com.cn.easybuy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.easybuy.dao.CartDao;
import com.cn.easybuy.entity.Cart;
import com.cn.easybuy.entity.User;
import com.cn.easybuy.util.BaseDao;


public class CartDaoImpl extends BaseDao implements CartDao {

	@Override
	public boolean queryCart(User user, int epid) {
		// TODO Auto-generated method stub
		boolean falg = false;
		String sql = "SELECT * FROM easybuy_cart WHERE eu_userid=? AND ep_id=?";
		Object[] params = {user.getUserID(),epid};
		try {
			rs = executeQuery(sql, params);
			if(rs.next()){
				falg = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return falg;
	}

	@Override
	public int addCart(User user, int epid) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "INSERT INTO easybuy_cart(eu_userid,ep_id,ep_quantity) VALUES(?,?,1)";
		Object[] params = {user.getUserID(),epid};
		try {
			result = executeUpdate(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return result;
	}

	@Override
	public int updateCart(User user, int epid) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "UPDATE easybuy_cart SET ep_quantity=ep_quantity+1 WHERE eu_userid=? AND ep_id=? ";
		Object[] params = {user.getUserID(),epid};
		try {
			result = executeUpdate(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return result;
	}

	@Override
	public List<Cart> getAllCartByUser(User user) {
		// TODO Auto-generated method stub
		List<Cart> carts = new ArrayList<Cart>();
		String sql = "SELECT id,eu_userid,ep_id,ep_quantity FROM easybuy_cart WHERE eu_userid=?";
		Object[] params = {user.getUserID()};
		try {
			rs = executeQuery(sql, params);
			while(rs.next()){
				Cart cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setEuUserid(rs.getString("eu_userid"));
				cart.setEpID(rs.getInt("ep_id"));
				cart.setEpQuantity(rs.getInt("ep_quantity"));
				carts.add(cart);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return carts;
	}

	@Override
	public boolean updateCaerQuantity(String id, String quantity) {
		// TODO Auto-generated method stub
		boolean falg = false;
		String sql = "";
		if(quantity.equals("1")){
			sql = "UPDATE easybuy_cart SET ep_quantity=ep_quantity+1 WHERE id=?";
		}else{
			sql = "UPDATE easybuy_cart SET ep_quantity=ep_quantity-1 WHERE id=?";
		}
		Object[] params = {id};
		try {
			int result = executeUpdate(sql, params);
			if(result!=0){
				falg = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return falg;
	}

}
