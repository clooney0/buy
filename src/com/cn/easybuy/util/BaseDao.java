package com.cn.easybuy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class BaseDao {
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/easybuy?useUnicode=true&characterEncoding=utf8";
	private String name="root";
	private String pwd="root";
	protected Connection connection = null;		//连接对象
	protected PreparedStatement pstmt = null;	//执行SQL语句
	protected ResultSet rs = null;	//查询
	//创建connection
	public void getConnection(){
		try{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, name, pwd);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//关闭对象
	public void closeAll(){
		try{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(connection != null){
				connection.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//执行查询
	public ResultSet executeQuery(String sql,Object... params){
		try{
			getConnection();
			pstmt = connection.prepareStatement(sql);
			if(params != null){
				for	(int i = 0; i < params.length; i++){
					pstmt.setObject((i+1), params[i]);
				}
			}
			rs = pstmt.executeQuery();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	//执行增删改
	public int executeUpdate(String sql,Object... params){
		int resule = 0;
		try{
			getConnection();
			pstmt = connection.prepareStatement(sql);
			if(params != null){
				for	(int i = 0; i < params.length; i++){
					pstmt.setObject((i+1), params[i]);
				}
			}
			resule = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resule;
	}
}
