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
import com.haircut.dao.ShopDao;
import com.haircut.dao.impl.ShopDaoImpl;
import com.haircut.enity.Shop;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();//�����ݷ��س�json��
		
		ShopDao dao= new ShopDaoImpl();
		List<Shop> list = dao.getShop();
		ResponseObject result = null;
		//��ȡ�˳�����Ϣ֮��
		if(list != null && list.size()>0){
			//��ȷ���ز��Ҵ�������
			result = new ResponseObject(1, list);
			}else {
			result = new ResponseObject(0, "û�е�������");
			}
		out.println(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
