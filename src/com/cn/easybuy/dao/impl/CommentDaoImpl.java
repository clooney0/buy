package com.cn.easybuy.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cn.easybuy.dao.CommentDao;
import com.cn.easybuy.entity.Comment;
import com.cn.easybuy.util.BaseDao;
import com.cn.easybuy.util.Pages;


public class CommentDaoImpl extends BaseDao implements CommentDao {

	@Override
	public List<Comment> getCommentLimitPage(Pages page) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "SELECT ec_id,ec_content,ec_create_time,"
				+ "ec_reply,ec_reply_time,ec_nick_name FROM easybuy_comment";
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
				Comment comment = new Comment();
				comment.setId(rs.getInt("ec_id"));
				comment.setContent(rs.getString("ec_content"));
				comment.setCreateTime(rs.getDate("ec_create_time"));
				comment.setReply(rs.getString("ec_reply"));
				comment.setReplyTime(rs.getDate("ec_reply_time"));
				comment.setNickName(rs.getString("ec_nick_name"));
				comments.add(comment);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return comments;
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
	public Comment queryCommentById(int id) {
		// TODO Auto-generated method stub
		Comment comment = null;
		String sql = "SELECT ec_id,ec_content,ec_create_time,"
				+ "ec_reply,ec_reply_time,ec_nick_name "
				+ "FROM easybuy_comment WHERE ec_id=?";
		Object[] params = {id};
		try {
			rs = executeQuery(sql, params);
			if(rs.next()){
				comment = new Comment();
				comment.setId(rs.getInt("ec_id"));
				comment.setContent(rs.getString("ec_content"));
				comment.setCreateTime(rs.getDate("ec_create_time"));
				comment.setReply(rs.getString("ec_reply"));
				comment.setReplyTime(rs.getDate("ec_reply_time"));
				comment.setNickName(rs.getString("ec_nick_name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return comment;
	}
	@Override
	public boolean updateReplyById(int id, String replyContent) {
		// TODO Auto-generated method stub
		boolean or = false;
		String sql = "UPDATE easybuy_comment SET ec_reply=?,ec_reply_time=? WHERE ec_id=?";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String replyTime = format.format(new Date());
		Object[] params = {replyContent,replyTime,id};
		try {
			int result = executeUpdate(sql, params);
			if(result!=0){
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
	public boolean deleteCommentById(int id) {
		// TODO Auto-generated method stub
		boolean or = false;
		String sql = "DELETE FROM easybuy_comment WHERE ec_id=?";
		Object[] params = {id};
		try {
			int result = executeUpdate(sql, params);
			if(result!=0){
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
	public boolean addContentById(String name, String Content) {
		// TODO Auto-generated method stub
		boolean or = false;
		String sql = "INSERT INTO easybuy_comment (ec_content,"
				+ "ec_create_time,ec_reply,ec_nick_name)VALUES(?,?,?,?)";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String createTime = format.format(new Date());
		Object[] params = {Content,createTime,"",name};
		try {
			int result = executeUpdate(sql, params);
			if(result!=0){
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
}
