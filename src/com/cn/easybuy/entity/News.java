package com.cn.easybuy.entity;

import java.util.Date;


public class News {
	private int id;			//最新动态id
	private String title;	//最新动态标题
	private String content;	//最新动态内容
	private Date createTime;//最新动态时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
