package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.User;
import com.cn.easybuy.util.Pages;


public interface UserDao {
	public List<User> getAllUser();
	public User queryUserByNameAndPwd(String name,String pwd);
	public List<User> getAllUserLimitPage(Pages page);
	public User queryUserByID(int id);
	public boolean updateUser(User user);
	public boolean deleteUser(int id);
	public boolean queryUserByName(String name);
	public int saveUser(User user);
}
