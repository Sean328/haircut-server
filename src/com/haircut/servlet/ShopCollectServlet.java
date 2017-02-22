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
import com.haircut.dao.CartDao;
import com.haircut.dao.CollectDao;
import com.haircut.dao.ShopCollectDao;
import com.haircut.dao.impl.CartDaoImp;
import com.haircut.dao.impl.CollectDaoImp;
import com.haircut.dao.impl.ShopCollectDaoImp;
import com.haircut.enity.Cart;
import com.haircut.enity.Collect;
import com.haircut.enity.ShopCollect;

/**
 * Servlet implementation class ShopCollectServlet
 */
@WebServlet("/ShopCollectServlet")
public class ShopCollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShopCollectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String shop_id = request.getParameter("shop_id");
		String user_tel = request.getParameter("user_tel");
		
		String mUser_tel = request.getParameter("mUser_tel");
		System.out.println("mUser_tel="+mUser_tel);
		
		String flag = request.getParameter("flag");
		
		System.out.println(shop_id);
		System.out.println(user_tel);
		System.out.println(flag);
		
		ShopCollectDao uDao = new ShopCollectDaoImp();
		
		ShopCollectDao dao= new ShopCollectDaoImp();
		
		ShopCollectDao dao1 = new ShopCollectDaoImp();
		
		ResponseObject result = null;
		
		if("submit".equals(flag)){
			ShopCollect shopcollect  = uDao.submit(user_tel, shop_id);
			if(shopcollect != null){
				result = new ResponseObject(1, "收藏成功!",shopcollect);
			}else{
				result = new ResponseObject(0, "该商品已经收藏!");
			}
		}else if("read".equals(flag)){
			System.out.println("程序走到了这一步");
			
			List<ShopCollect> list = dao.getShopCollect();
			
			if(list != null && list.size()>0){
			//正确返回并且带有数据
			result = new ResponseObject(1, list);
			}else {
			result = new ResponseObject(0, "没有购物车数据");
			}
		}else if("send".equals(flag)){
			ShopCollect shopcolelct = dao1.get(mUser_tel);
		}
		
		
		out.print(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
	}

}
