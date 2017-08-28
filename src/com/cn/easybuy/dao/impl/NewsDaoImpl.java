package com.cn.easybuy.dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cn.easybuy.dao.NewsDao;
import com.cn.easybuy.entity.News;
import com.cn.easybuy.util.BaseDao;


public class NewsDaoImpl  extends BaseDao implements NewsDao{

	//public Object params;


	@Override
	public List<News> getAllNews()
	{
		List<News> list= new ArrayList<News>();
		try {
			String sql="select  en_id,en_title,en_content,en_create_time from easybuy_news ORDER BY en_create_time DESC limit 0,10";
					
			
			rs=executeQuery(sql);
		 while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt("en_id"));
				news.setTitle(rs.getString("en_title"));
				//news.setEnContent(rs.getString("en_content"));
				//news.setEnDate(rs.getDate("en_create_time"));
				list.add(news);
			} 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return list;
	
	}
	@Override
	 public News getNewsInfo(String id)
	 {
		 News news=null;
			try {
				String sql="select * from easybuy_news where en_id='" + id + "'";
				rs=executeQuery(sql);
			 while (rs.next()) {
					news = new News();
					news.setId(rs.getInt("en_id"));
					news.setTitle(rs.getString("en_title"));
					news.setContent(rs.getString("en_content"));
					news.setCreateTime(rs.getDate("en_create_time"));
				} 

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll();
			}
			return news;
			
		  }  
		
	@Override
	public boolean addnews(News news) {
		int result=0;
		boolean flag=false;
		try {
			String sql="insert into easybuy_news(en_title,en_content,en_create_time)"
							+ " values (?,?,?)";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(new Date());
			Object[] params ={news.getTitle(),news.getContent(),date};
			result=executeUpdate(sql,params);	
			if(result>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return flag;
	}

	@Override
	public boolean delnews(String id) {
		// TODO Auto-generated method stub
		int result=0;
		boolean flag=false;
		try {
			String sql="DELETE FROM easybuy_news where en_id=?";
			Object[] params ={id};
			result=executeUpdate(sql,params);	
			if(result!=0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return flag;
	}

	@Override
	public boolean updatenews(News news) {
		// TODO Auto-generated method stub
		int result=0;
		boolean flag=false;
		try {
			String sql="update easybuy_news set en_title=?,en_content=?,en_create_time=? where en_id=?";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(new Date());
			Object[] params ={news.getTitle(),news.getContent(),date,news.getId()};
			result=executeUpdate(sql,params);	
			if(result>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return flag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int result=0;
		try {
			String sql="SELECT COUNT(*) as num FROM easybuy_news";
			rs=executeQuery(sql);
			if(rs.next())
			{
				result=rs.getInt("num");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return result;
	}

	@Override
	public List<News> getList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<News> list=new ArrayList<News>();
		try {
			String sql="SELECT en_id,en_title,en_content, en_create_time FROM easybuy_news LIMIT ?,?";
								//+(pageIndex-1)*"pageSize+","+pageSize;
			Object[] params={(pageIndex-1)*pageSize,pageSize};
			rs=executeQuery(sql,params);
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt("en_id"));
				news.setTitle(rs.getString("en_title"));
				news.setContent(rs.getString("en_content"));
				news.setCreateTime(rs.getDate("en_create_time"));
				list.add(news);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return list;
	}
}
