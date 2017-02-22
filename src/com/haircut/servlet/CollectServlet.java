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
import com.haircut.dao.CollectDao;
import com.haircut.dao.CommentDao;
import com.haircut.dao.OrderDao;
import com.haircut.dao.UserDao;
import com.haircut.dao.impl.CollectDaoImp;
import com.haircut.dao.impl.CommentDaoImp;
import com.haircut.dao.impl.OrderDaoImp;
import com.haircut.dao.impl.UserDaoImpl;
import com.haircut.enity.Cart;
import com.haircut.enity.Collect;
import com.haircut.enity.Comment;
import com.haircut.enity.Order;
import com.haircut.enity.User;

/**
 * Servlet implementation class CollectServlet
 */
@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static String tel;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String goods_id = request.getParameter("goods_id");
		String user_tel = request.getParameter("user_tel");
		
		String mUser_tel = request.getParameter("mUser_tel");
		System.out.println("mUser_tel="+mUser_tel);
		
		String flag = request.getParameter("flag");
		
		System.out.println(goods_id);
		System.out.println(user_tel);
		System.out.println(flag);
		

		CollectDao uDao = new CollectDaoImp();
		
		CollectDao dao= new CollectDaoImp();
		
		CollectDao dao1= new CollectDaoImp();
		
		ResponseObject result = null;
		
		if("collect".equals(flag)){
			Collect collect  = uDao.register(goods_id, user_tel);
			if(collect != null){
				result = new ResponseObject(1, "收藏成功!",collect);
			}else{
				result = new ResponseObject(0, "该商品已经收藏!");
			}
		
		}else if("read".equals(flag)){
			List<Collect> list = dao.getCollect();
			
			if(list != null && list.size()>0){
			//正确返回并且带有数据
			result = new ResponseObject(1, list);
			}else {
			result = new ResponseObject(0, "没有购物车数据");
			}
		}else if("send".equals(flag)){
			dao1.get(mUser_tel);
		}
		
		out.print(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
	}

}
