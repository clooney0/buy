package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.Cart;
import com.cn.easybuy.entity.User;


public interface CartDao {
	public List<Cart> getAllCartByUser(User user);
	public boolean queryCart(User user, int epid);
	public int addCart(User user,int epid);
	public int updateCart(User user,int epid);
	public boolean updateCaerQuantity(String id,String quantity);
}
