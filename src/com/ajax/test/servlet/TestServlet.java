package com.ajax.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet(urlPatterns = "/test/*")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson g = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String str = ""; 
		if("true".equals(request.getParameter("list"))) {
			List<String> strList = new ArrayList<>();
			strList.add("안녕");
			strList.add("하세");
			strList.add("요오오옹!");
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			pw.print(num1+num2);
		}else {
			Map<String,String> map = new HashMap<>();
			map.put("name", "하하");
			map.put("age", "44");
			map.put("addr", "중랑구");
			str = g.toJson(map);
		}
		
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
