package com.cn.easybuy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.easybuy.dao.ProductParentDao;
import com.cn.easybuy.entity.ProductParent;
import com.cn.easybuy.util.BaseDao;


public class ProductParentDaoImpl extends BaseDao implements ProductParentDao {

	@Override
	public List<ProductParent> getAllParent() {
		// TODO Auto-generated method stub
		List<ProductParent> list = new ArrayList<ProductParent>();
		String sql = "SELECT epc_parent_id,epc_parent_name FROM easybuy_product_parent";
		try {
			rs = executeQuery(sql);
			while(rs.next()){
				ProductParent p = new ProductParent();
				p.setParentID(rs.getInt("epc_parent_id"));
				p.setParentName(rs.getString("epc_parent_name"));
				list.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return list;
	}
	@Override
	public ProductParent getProductParentInfo(String id) {
		ProductParent pp = null;
		
		try {
			String sql="select epc_parent_id,epc_parent_name from easybuy_product_parent where epc_parent_id='" + id + "'";
			rs=executeQuery(sql);
			while(rs.next()) {
				pp = new ProductParent();
				pp.setParentID(rs.getInt("epc_parent_id"));
				pp.setParentName(rs.getString("epc_parent_name"));
				
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		
		return pp;
		
		
		
	}

	@Override
	public boolean updateProductParent(ProductParent productparent) {
		int result = 0;
		boolean flag = false;
		
		try {
			String sql="update easybuy_product_parent set epc_parent_name=?  where epc_parent_id=? ";
			Object[] params = {productparent.getParentName(),productparent.getParentID()};
			result = executeUpdate(sql, params);
			if(result != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return flag;
	}

	@Override
	public boolean deleteProductParent(String id) {
		int result = 0;
		boolean flag = false;
		try {
			String sql="delete from easybuy_product_parent where epc_parent_id=?";
			Object[] params = {id};
			result = executeUpdate(sql, params);
			if(result !=0 ) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return flag;
	}
}
