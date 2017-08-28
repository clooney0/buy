package com.cn.easybuy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.easybuy.dao.ProductDao;
import com.cn.easybuy.entity.Product;
import com.cn.easybuy.util.BaseDao;


public class ProductDaoImpl extends BaseDao implements ProductDao {

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();
		String sql = "SELECT ep_id,ep_name,ep_description,ep_price,ep_stock,epc_id,ep_file_name "
				+ "FROM easybuy_product";
		try {
			rs = executeQuery(sql);
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt("ep_id"));
				p.setName(rs.getString("ep_name"));
				p.setDescription(rs.getString("ep_description"));
				p.setPrice(rs.getDouble("ep_price"));
				p.setStock(rs.getInt("ep_stock"));
				p.setEpcID(rs.getInt("epc_id"));
				p.setFileName(rs.getString("ep_file_name"));
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
	public Product getProductByID(int id) {
		// TODO Auto-generated method stub
		Product product = null;
		String sql = "SELECT ep_id,ep_name,ep_description,ep_price,ep_stock,epc_id,ep_file_name "
				+ "FROM easybuy_product WHERE ep_id=?";
		Object[] params = {id};
		try {
			rs = executeQuery(sql, params);
			if (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("ep_id"));
				product.setName(rs.getString("ep_name"));
				product.setDescription(rs.getString("ep_description"));
				product.setPrice(rs.getDouble("ep_price"));
				product.setStock(rs.getInt("ep_stock"));
				product.setEpcID(rs.getInt("epc_id"));
				product.setFileName(rs.getString("ep_file_name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return product;
	}
	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			int result=0;
			try{
				String sql="SELECT COUNT(1) as num FROM easybuy_product";	
			    rs=executeQuery(sql);
			    if(rs.next()){
			    	result=rs.getInt("num");
			    }
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.closeAll();
			}
			return result;
		}
		@Override
		public List<Product> getList(int pageIndex, int pageSize) {
			// TODO Auto-generated method stub
	       List<Product> list=new ArrayList<Product>();
			try{
				String sql="SELECT ep_id,ep_name,ep_price,ep_file_name  FROM easybuy_product LIMIT ?,?";	
			    Object[] params={(pageIndex-1)*pageSize,pageSize};
			    rs=executeQuery(sql, params);
			    while(rs.next()){
			    	Product product =new Product();
			    	product.setId(rs.getInt("ep_id"));
			    	product.setName(rs.getString("ep_name"));
			    	product.setPrice(rs.getFloat("ep_price"));
			    	product.setFileName(rs.getString("ep_file_name"));
			    	list.add(product);
			    }
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				this.closeAll();
			}
			return list;
		}
		
		@Override
		public boolean updateProduct(Product product) {
			// TODO Auto-generated method stub
			int result = 0;
			boolean flag = false;
			
			try {
				String sql="update easybuy_product set ep_name=?,ep_description=?,epc_id=?,ep_price=?,ep_stock=?,ep_file_name=?  where ep_id=? ";
				Object[] params = {product.getName(),product.getDescription(),
						product.getEpcID(),product.getPrice(),
						product.getStock(),product.getFileName(),product.getId()};
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
		public boolean deleteProduct(String id) {
			int result = 0;
			boolean flag = false;
			try {
				String sql="DELETE FROM easybuy_product where ep_id=?";
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
		public boolean addProduct(Product product) {
			int result = 0;
			boolean flag = false;
			try {
				String sql="insert into easybuy_product(ep_name,ep_description,epc_id,ep_price,ep_stock,ep_file_name) "
						+ "values(?,?,?,?,?,?)";
				Object[] params = {product.getName(),product.getDescription(),product.getEpcID(),
						product.getPrice(),product.getStock(),product.getFileName()};
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
