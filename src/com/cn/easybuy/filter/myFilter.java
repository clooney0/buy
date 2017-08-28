package com.cn.easybuy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class myFilter implements Filter {

	protected String encoding = null; // 接收字符编码
	protected FilterConfig filterConfig = null; // 初始化配置
	protected boolean ignore = true; // 是否忽略大小写

	@Override
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request); // 如果为空先从web.xml中得到
			if (encoding != null) {
				request.setCharacterEncoding(encoding); // 设置字符集编码
			}
		}
		chain.doFilter(request, response);
	}

	protected String selectEncoding(ServletRequest request) {
		return this.encoding;
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		this.filterConfig = fc;
		this.encoding = fc.getInitParameter("encoding");
		String value = fc.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			this.ignore = true;
		} else {
			this.ignore = false;
		}
	}

}
