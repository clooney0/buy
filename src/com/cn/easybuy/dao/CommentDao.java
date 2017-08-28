package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.Comment;
import com.cn.easybuy.util.Pages;

public interface CommentDao {
	public List<Comment> getCommentLimitPage(Pages page);
	public Comment queryCommentById(int id);
	public boolean updateReplyById(int id, String replyContent);
	public boolean addContentById(String name, String Content);
	public boolean deleteCommentById(int id);
}
