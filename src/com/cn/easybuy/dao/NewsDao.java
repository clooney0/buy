package com.cn.easybuy.dao;

import java.util.List;

import com.cn.easybuy.entity.News;


public interface NewsDao {

	List<News> getList(int pageIndex, int pageSize);

	int getCount();

	boolean updatenews(News news);

	boolean delnews(String id);

	boolean addnews(News news);

	List<News> getAllNews();

	News getNewsInfo(String id);

}
