package com.cn.easybuy.util;


public class Pages {
	  private int pageIndex; //当前页面
      private int pageSize; //页面记录
      private int pageCount; //总记录数
      private int pageTotal; //页面总数
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		if(pageCount>0)
		this.pageCount = pageCount;
	    this.pageTotal=this.pageCount%this.pageSize==0?this.pageCount/this.pageSize:this.pageCount/this.pageSize+1;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
}
