package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.ProductParent;


public interface ProductParentDao {
	public List<ProductParent> getAllParent();
	public ProductParent getProductParentInfo(String id);
	public boolean updateProductParent(ProductParent productparent);
	public boolean deleteProductParent(String id);
}
