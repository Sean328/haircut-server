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
import com.haircut.dao.ShopDao;
import com.haircut.dao.impl.CartDaoImp;
import com.haircut.dao.impl.ShopDaoImpl;
import com.haircut.enity.Cart;
import com.haircut.enity.Shop;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();//�����ݷ��س�json��
//		String flag = request.getParameter("flag");
//		
//		System.out.println("flag="+flag);
		
		
//		if("read".equals(flag)){
//			CartDao dao= new CartDaoImp();
//			List<Cart> list = dao.getCart();
//			ResponseObject result = null;
//
//			if(list != null && list.size()>0){
//				//��ȷ���ز��Ҵ�������
//				result = new ResponseObject(1, list);
//				}else {
//				result = new ResponseObject(0, "û���ղ�����");
//				}
//			
//			
//		}
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
		
		String mUser_tel = request.getParameter("mUser_tel");
		System.out.println("mUser_tel="+mUser_tel);
		
		String flag = request.getParameter("flag");
		
		System.out.println("goods_id="+goods_id);
		System.out.println("user_tel="+user_tel);
		System.out.println(flag);
		
		CartDao uDao = new CartDaoImp();
		ResponseObject result = null;
		
		CartDao dao= new CartDaoImp();
		
		CartDao dao1 = new CartDaoImp();
		
		
		
		if("cart".equals(flag)){
			Cart cart = uDao.put(goods_id, user_tel);
			if(cart!=null){
				result = new ResponseObject(1, "��ӹ��ﳵ�ɹ�!",cart);
			}else{
				result = new ResponseObject(0, "����Ʒ�Ѿ����빺�ﳵ!");
			}
		}else if("read".equals(flag)){
			System.out.println("�����ߵ�����һ��");
			
			List<Cart> list = dao.getCart();
			
			if(list != null && list.size()>0){
			//��ȷ���ز��Ҵ�������
			result = new ResponseObject(1, list);
			}else {
			result = new ResponseObject(0, "û�й��ﳵ����");
			}
		}else if("send".equals(flag)){
			
			Cart cart = dao1.get(mUser_tel);
		}
		
		out.print(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
		
	}

}
