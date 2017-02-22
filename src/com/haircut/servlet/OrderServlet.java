package com.haircut.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.haircut.dao.CartDao;
import com.haircut.dao.OrderDao;
import com.haircut.dao.impl.CartDaoImp;
import com.haircut.dao.impl.OrderDaoImp;
import com.haircut.enity.Cart;
import com.haircut.enity.Order;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String goods_id = request.getParameter("goods_id");
		String user_tel = request.getParameter("user_tel");
		String orders_time1 = request.getParameter("orders_time");
		String orders_price = request.getParameter("orders_price");
		
		String mUser_tel = request.getParameter("mUser_tel");
		
		String flag = request.getParameter("flag");

		
	
		
		System.out.println(goods_id);
		System.out.println(user_tel);
		System.out.println(orders_price);
		System.out.println("mUser_tel="+mUser_tel);
		System.out.println(flag);
		
		OrderDao uDao = new OrderDaoImp();
		ResponseObject result = null;
		
		OrderDao dao= new OrderDaoImp();
		OrderDao dao1 = new OrderDaoImp();
		
		if("order".equals(flag)){
			
			System.out.println("解码前 orders_time="+orders_time1);
			
			String orders_time = URLDecoder.decode(orders_time1, "utf-8");
			orders_time = URLDecoder.decode(orders_time, "utf-8");
			System.out.println(orders_time);
			
			
			Order order  = uDao.submit(goods_id, user_tel,orders_time, orders_price);
			if(order != null){
				result = new ResponseObject(1, "购买成功!",order);
			}else{
				result = new ResponseObject(0, "该商品已经在购买列表!");
			}
		
		}else if("read".equals(flag)){
			System.out.println("程序走到了这一步");
			
			List<Order> list = dao.getOrder();
			
			if(list != null && list.size()>0){
			//正确返回并且带有数据
			result = new ResponseObject(1, list);
			}else {
			result = new ResponseObject(0, "没有购物车数据");
			}
		}else if("send".equals(flag)){
			
			Order order = dao1.get(mUser_tel);
		}
		
		out.print(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
		
	}

}
