package com.cn.easybuy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.easybuy.dao.UserDao;
import com.cn.easybuy.entity.User;
import com.cn.easybuy.util.BaseDao;
import com.cn.easybuy.util.Pages;


public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT eu_user_id,eu_user_name,eu_name,eu_password,"
				+ "eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,"
				+ "eu_address,eu_login,eu_status FROM easybuy_user";
		try {
			rs = executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("eu_user_id"));
				user.setUserName(rs.getString("eu_user_name"));
				user.setName(rs.getString("eu_name"));
				user.setPassword(rs.getString("eu_password"));
				user.setSex(rs.getString("eu_sex"));
				user.setBirthday(rs.getDate("eu_birthday"));
				user.setIdentityCode(rs.getString("eu_identity_code"));
				user.setEmail(rs.getString("eu_email"));
				user.setMobile(rs.getString("eu_mobile"));
				user.setAddress(rs.getString("eu_address"));
				user.setLogin(rs.getInt("eu_login"));
				user.setStatus(rs.getInt("eu_status"));
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return users;
	}

	@Override
	public User queryUserByNameAndPwd(String name, String pwd) {
		// TODO Auto-generated method stub
		User user = null;
		String sql = "SELECT eu_user_id,eu_user_name,eu_name,eu_password,"
				+ "eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,"
				+ "eu_address,eu_login,eu_status FROM easybuy_user "
				+ "WHERE eu_user_name=? AND eu_password=?";
		Object[] params = {name,pwd};
		try {
			rs = executeQuery(sql, params);
			if(rs.next()){
				user = new User();
				user.setUserID(rs.getInt("eu_user_id"));
				user.setUserName(rs.getString("eu_user_name"));
				user.setName(rs.getString("eu_name"));
				user.setPassword(rs.getString("eu_password"));
				user.setSex(rs.getString("eu_sex"));
				user.setBirthday(rs.getDate("eu_birthday"));
				user.setIdentityCode(rs.getString("eu_identity_code"));
				user.setEmail(rs.getString("eu_email"));
				user.setMobile(rs.getString("eu_mobile"));
				user.setAddress(rs.getString("eu_address"));
				user.setLogin(rs.getInt("eu_login"));
				user.setStatus(rs.getInt("eu_status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return user;
	}

	@Override
	public List<User> getAllUserLimitPage(Pages page) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = "SELECT eu_user_id,eu_user_name,eu_name,eu_password,"
				+ "eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,"
				+ "eu_address,eu_login,eu_status FROM easybuy_user ";
		
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
				User user = new User();
				user.setUserID(rs.getInt("eu_user_id"));
				user.setUserName(rs.getString("eu_user_name"));
				user.setName(rs.getString("eu_name"));
				user.setPassword(rs.getString("eu_password"));
				user.setSex(rs.getString("eu_sex"));
				user.setBirthday(rs.getDate("eu_birthday"));
				user.setIdentityCode(rs.getString("eu_identity_code"));
				user.setEmail(rs.getString("eu_email"));
				user.setMobile(rs.getString("eu_mobile"));
				user.setAddress(rs.getString("eu_address"));
				user.setLogin(rs.getInt("eu_login"));
				user.setStatus(rs.getInt("eu_status"));
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return users;
	}

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
	public User queryUserByID(int id) {
		// TODO Auto-generated method stub
		User user = null;
		String sql = "SELECT eu_user_id,eu_user_name,eu_name,eu_password,"
				+ "eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,"
				+ "eu_address,eu_login,eu_status FROM easybuy_user WHERE eu_user_id=?";
		Object[] params = {id};
		try {
			rs = executeQuery(sql, params);
			if(rs.next()){
				user = new User();
				user.setUserID(rs.getInt("eu_user_id"));
				user.setUserName(rs.getString("eu_user_name"));
				user.setName(rs.getString("eu_name"));
				user.setPassword(rs.getString("eu_password"));
				user.setSex(rs.getString("eu_sex"));
				user.setBirthday(rs.getDate("eu_birthday"));
				user.setIdentityCode(rs.getString("eu_identity_code"));
				user.setEmail(rs.getString("eu_email"));
				user.setMobile(rs.getString("eu_mobile"));
				user.setAddress(rs.getString("eu_address"));
				user.setLogin(rs.getInt("eu_login"));
				user.setStatus(rs.getInt("eu_status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		boolean or = false;
		String sql = "UPDATE easybuy_user SET eu_user_name=?,eu_name=?,"
				+ "eu_password=?,eu_sex=?,eu_birthday=?,eu_email=?,"
				+ "eu_mobile=?,eu_address=? WHERE eu_user_id=?";
		Object[] params = {user.getUserName(),user.getName(),user.getPassword(),user.getSex(),
				user.getBirthday(),user.getEmail(),user.getMobile(),user.getAddress(),user.getUserID()};
		try {
			int result = executeUpdate(sql, params);
			if(result != 0){
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
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		boolean or = false;
		String sql = "DELETE FROM easybuy_user WHERE eu_user_id=?";
		Object[] params = {id};
		try {
			int result = executeUpdate(sql, params);
			if(result != 0){
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
	public boolean queryUserByName(String name) {
        boolean flag=false; //可以注册
        try{
       	String sql="SELECT * from easybuy_user WHERE eu_user_name =?";
           Object[] params={name};
       	rs=executeQuery(sql, params);
       	if(rs.next()){
       		flag=true; //查找到相同用户不能注册
       	}
        }catch(Exception e){
       	 e.printStackTrace();
        }finally{
       	 this.closeAll();
        }
        return flag;
	}
	@Override
	public int saveUser(User user) {
		int result=0;
		try{
			StringBuffer sql=new StringBuffer();
			sql.append("INSERT into easybuy_user (eu_user_name,eu_name,eu_password,");
			sql.append("eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address)");
		    sql.append("VALUES(?,?,?,?,?,?,?,?,?)");
		    Object[] params={user.getUserName(),user.getName(),user.getPassword(),user.getSex(),user.getBirthday(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getAddress()};
		    result=executeUpdate(sql.toString(), params);
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			this.closeAll();
		}
		
		return result;
	}

}
