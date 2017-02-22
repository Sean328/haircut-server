package com.haircut.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.haircut.dao.CItyDao;
import com.haircut.dao.impl.CityDaoImpl;
import com.haircut.enity.City;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();//将数据返回成json串
		CItyDao dao = new CityDaoImpl();
		//获取服务器中的关联的数据库中的城市
		List<City>list = dao.getCity();
		ResponseObject result = null;
		//获取了城市信息之后
		if(list != null && list.size()>0){
			//正确返回并且带有数据
			result = new ResponseObject(1, list);
		}else {
			result = new ResponseObject(0, "没有城市数据");
		}
		out.println(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
