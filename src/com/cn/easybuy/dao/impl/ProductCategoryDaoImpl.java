package com.cn.easybuy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.easybuy.dao.ProductCategoryDao;
import com.cn.easybuy.entity.ProductCategory;
import com.cn.easybuy.util.BaseDao;


public class ProductCategoryDaoImpl extends BaseDao implements ProductCategoryDao {

	@Override
	public List<ProductCategory> getAllCategory() {
		// TODO Auto-generated method stub
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		String sql = "SELECT epc_id,epc_name,epc_parent_id FROM easybuy_product_category";
		try {
			rs = executeQuery(sql);
			while(rs.next()){
				ProductCategory p = new ProductCategory();
				p.setId(rs.getInt("epc_id"));
				p.setName(rs.getString("epc_name"));
				p.setParentID(rs.getInt("epc_parent_id"));
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
	public ProductCategory getProductChildInfo(String id) {
		ProductCategory pcy = null;
		
		try {
			String sql="select epc_id,epc_name,epc_parent_id from easybuy_product_category where epc_id='" + id + "'";
			rs=executeQuery(sql);
			while(rs.next()) {
				pcy = new ProductCategory();
				pcy.setId(rs.getInt("epc_id"));
				pcy.setName(rs.getString("epc_name"));
				pcy.setParentID(rs.getInt("epc_parent_id"));
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		
		return pcy;
	}

	@Override
	public boolean updateProductCategory(ProductCategory productcategory) {
		int result = 0;
		boolean flag = false;
		
		try {
			String sql="update easybuy_product_category set epc_parent_id=?,epc_name=?  where epc_id=? ";
			Object[] params = {productcategory.getParentID(),productcategory.getName(),productcategory.getId()};
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
	public boolean deleteProductCategory(String id) {
		int result = 0;
		boolean flag = false;
		try {
			String sql="delete from easybuy_product_category where epc_id=?";
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

	@Override
	public boolean addProductCategory(ProductCategory productcategory) {
		int result = 0;
		boolean flag = false;
		try {
			String sql="insert into easybuy_product_category(epc_name,epc_parent_id) "
					+ "values(?,?)";
			Object[] params = {productcategory.getName(),
					productcategory.getParentID()};
			result=executeUpdate(sql, params);
			if(result > 0) {
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
