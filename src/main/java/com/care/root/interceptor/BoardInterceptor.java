package com.care.root.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.LoginSession;



public class BoardInterceptor extends HandlerInterceptorAdapter implements LoginSession{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("=== 연동 확인 ===");
		
		HttpSession session = request.getSession();
		if(session.getAttribute(LOGIN)==null) {
			String msg = "<script>alert('로그인 먼저 하세요');";
	         msg += "location.href='/root/member/login';</script>";
	         response.setContentType("text/html; charset=utf-8");
	         PrintWriter out = response.getWriter();
	         out.print( msg );
	         
	         return false;
		}
		return true;
	}
}
