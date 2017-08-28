package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.Product;


public interface ProductDao {
	public List<Product> getAllProduct();
	public Product getProductByID(int id);
	public int getCount();
	public List<Product> getList(int pageIndex, int pageSize);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(String id);
	public boolean addProduct(Product product);
}
