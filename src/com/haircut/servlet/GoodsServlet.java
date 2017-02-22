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
import com.haircut.dao.GoodsDao;
import com.haircut.dao.impl.GoodsDaoImp;
import com.haircut.enity.Cart;
import com.haircut.enity.Goods;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();//将数据返回成json串
		
		String mGoods_id = request.getParameter("mGoods_id");
		System.out.println("mGoods_id="+mGoods_id);
		
		String flag = request.getParameter("flag");
		
		GoodsDao dao = new GoodsDaoImp();
		
		ResponseObject result = null;
				
		if("read".equals(flag)){
			
			List<Goods> list = dao.getGoods();
			
			if(list != null && list.size()>0){
				//正确返回并且带有数据
				result = new ResponseObject(1, list);
				}else {
				result = new ResponseObject(0, "没有商品数据");
				}
			
		}else if("send".equals(flag)){
			System.out.println("程序走到了这一步");
			
			List<Goods> list1 = dao.getGoodsItem(mGoods_id);
			
			if(list1 != null && list1.size()>0){
			//正确返回并且带有数据
			result = new ResponseObject(1, list1);
			}else {
			result = new ResponseObject(0, "没有购物车数据");
			}
		}
			
		out.println(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
