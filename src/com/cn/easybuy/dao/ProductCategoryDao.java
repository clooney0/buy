package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.ProductCategory;


public interface ProductCategoryDao {
	public List<ProductCategory> getAllCategory();
	public ProductCategory getProductChildInfo(String id);
	public boolean updateProductCategory(ProductCategory productcategory);
	public boolean deleteProductCategory(String id);
	public boolean addProductCategory(ProductCategory productcategory);
}
