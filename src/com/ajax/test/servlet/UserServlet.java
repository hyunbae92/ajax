package com.ajax.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ajax.test.service.UserService;
import com.ajax.test.service.impl.UserServiceImpl;
import com.google.gson.Gson;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson g = new Gson();
	private UserService userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/");
		String cmd = request.getRequestURI().substring(idx+1);
		PrintWriter pw = response.getWriter();
		if("checkid".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			Map<String,String>rMap = userService.doCheckId(uiId);
			pw.print(g.toJson(rMap));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/");
		String cmd = request.getRequestURI().substring(idx+1); 
		PrintWriter pw = response.getWriter();
		
		if("login".equals(cmd)) {
			BufferedReader br = request.getReader();
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			Map<String, String> pMap = g.fromJson(sb.toString(), Map.class);
			Map<String, String> rMap = userService.doLogin(pMap);
			if ("ok".equals(rMap.get("result"))) {
				HttpSession session = request.getSession();
				session.setAttribute("id",pMap.get("id"));
			}
			pw.append((g.toJson(rMap)));
		}else if("logout".equals(cmd)) {
			HttpSession session = request.getSession();
			session.invalidate();
			Map<String, String> rMap = new HashMap<>();
			rMap.put("msg", "로그아웃완료");
			pw.append((g.toJson(rMap)));
		}else if("join".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			if(uiId==null || uiId.trim().length()<4 || uiId.trim().length()>15) {
				throw new ServletException("올바르지 않은 아이디값!");
			}
			String uiPwd = request.getParameter("ui_password");
			String uiName = request.getParameter("ui_name");
			String uiAge = request.getParameter("ui_age");
			String uiBirth = request.getParameter("ui_birth");
			uiBirth = uiBirth.replace("-", "");
			String uiPhone = request.getParameter("ui_phone");
			String uiEmail = request.getParameter("ui_email");
			String uiNickname = request.getParameter("ui_nickname");
			Map<String,Object> user = new HashMap<>();
			user.put("ui_id", uiId);
			user.put("ui_password", uiPwd);
			user.put("ui_name", uiName);
			user.put("ui_age", uiAge);
			user.put("ui_birth", uiBirth);
			user.put("ui_phone", uiPhone);
			user.put("ui_email", uiEmail);
			user.put("ui_nickname", uiNickname);
			Map<String,Object> rMap = userService.joinUserInfo(user);
			request.setAttribute("rMap", rMap);
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg");
			rd.forward(request, response);
		}
		
	}
//	public static void main(String[] args) {
//		InitServlet is = new InitServlet();
//		is.init();
//		UserService userService = new UserServiceImpl();
//		Map<String,Object> user = new HashMap<>();
//		user.put("ui_id", "1234");
//		user.put("ui_password", "1234");
//		user.put("ui_name", "1234");
//		user.put("ui_age", "12");
//		user.put("ui_birth", "19920616");
//		user.put("ui_phone", "01027269242");
//		user.put("ui_email", "");
//		user.put("ui_nickname", "gusqoekt");
//		Map<String,Object> rMap = userService.joinUserInfo(user);
//		System.out.println(rMap.get("result"));
//		System.out.println(rMap.get("msg"));
//		System.out.println(rMap.get("url"));
//	}

}
