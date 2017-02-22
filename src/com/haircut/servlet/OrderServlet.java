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
			
			System.out.println("����ǰ orders_time="+orders_time1);
			
			String orders_time = URLDecoder.decode(orders_time1, "utf-8");
			orders_time = URLDecoder.decode(orders_time, "utf-8");
			System.out.println(orders_time);
			
			
			Order order  = uDao.submit(goods_id, user_tel,orders_time, orders_price);
			if(order != null){
				result = new ResponseObject(1, "����ɹ�!",order);
			}else{
				result = new ResponseObject(0, "����Ʒ�Ѿ��ڹ����б�!");
			}
		
		}else if("read".equals(flag)){
			System.out.println("�����ߵ�����һ��");
			
			List<Order> list = dao.getOrder();
			
			if(list != null && list.size()>0){
			//��ȷ���ز��Ҵ�������
			result = new ResponseObject(1, list);
			}else {
			result = new ResponseObject(0, "û�й��ﳵ����");
			}
		}else if("send".equals(flag)){
			
			Order order = dao1.get(mUser_tel);
		}
		
		out.print(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
		
	}

}
