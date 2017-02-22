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
import com.haircut.dao.CommentDao;
import com.haircut.dao.OrderDao;
import com.haircut.dao.ShopDao;
import com.haircut.dao.impl.CommentDaoImp;
import com.haircut.dao.impl.OrderDaoImp;
import com.haircut.dao.impl.ShopDaoImpl;
import com.haircut.enity.Comment;
import com.haircut.enity.Order;
import com.haircut.enity.Shop;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommentServlet() {
        super();
        
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();//将数据返回成json串
		
		
//		CommentDao dao = new CommentDaoImp();
//		List<Comment> list = dao.getComment();
//		ResponseObject result = null;
//		if(list != null && list.size()>0){
//			//正确返回并且带有数据
//			result = new ResponseObject(1, list);
//			}else {
//			result = new ResponseObject(0, "没有评论数据");
//			}
		
		
//		out.println(new GsonBuilder().create().toJson(result));
//		out.flush();
//		out.close();
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String goods_id = request.getParameter("goods_id");
		String user_tel = request.getParameter("user_tel");
		String name = request.getParameter("name");
		String comments = request.getParameter("comments");
		String clevel = request.getParameter("clevel");
		String cpermoney = request.getParameter("cpermoney");
		String time = request.getParameter("time");
		String orders_id = request.getParameter("orders_id");
		String flag = request.getParameter("flag");
		
		
		System.out.println(goods_id+user_tel+orders_id+name+comments+clevel+cpermoney+time);
		
		
		CommentDao uDao = new CommentDaoImp();
		CommentDao dao = new CommentDaoImp();
		ResponseObject result = null;
		if("submit".equals(flag)){
			
			System.out.println("解码前 time="+time);
			System.out.println("解码前 comments="+comments);
			
			String comment_time = URLDecoder.decode(time, "utf-8");
			comment_time = URLDecoder.decode(comment_time, "utf-8");
			
			String comments1 =  URLDecoder.decode(comments, "utf-8");
			comments1 = URLDecoder.decode(comments1, "utf-8");
			
			System.out.println("解码后 time="+comment_time);
			System.out.println("解码后 comments="+comments);
			
			Comment comment  = uDao.submit(goods_id, user_tel,name,comments1,clevel,cpermoney,comment_time,orders_id);
			if(comment != null){
				result = new ResponseObject(1, "评论成功!",comment);
			}else{
				result = new ResponseObject(0, "您已经发表过评论!");
			}
		
		}else if("read".equals(flag)){
			List<Comment> list = dao.getComment();
			if(list != null && list.size()>0){
				//正确返回并且带有数据
				result = new ResponseObject(1, list);
				}else {
				result = new ResponseObject(0, "没有评论数据");
				}
		}
		
		
		
		out.print(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
	}

}
