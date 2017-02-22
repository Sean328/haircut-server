package com.haircut.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.haircut.dao.UserDao;
import com.haircut.dao.impl.UserDaoImpl;
import com.haircut.enity.Shop;
import com.haircut.enity.User;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.sun.xml.internal.ws.util.StringUtils;

public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		SmartUpload smartUpload = new SmartUpload();
		
		String user_tel = request.getParameter("user_tel");
		String pwd = request.getParameter("password");
		String user_name = request.getParameter("user_name");
		String user_gender = request.getParameter("user_gender");
		String user_address = request.getParameter("user_address");
//		String user_img = request.getParameter("user_img");
		
		String flag = request.getParameter("flag");
		String user_Img;
		
		System.out.println("user_tel ="+user_tel);
		System.out.println("user_password="+pwd);
		System.out.println(flag);
		
		UserDao uDao = new UserDaoImpl();
		ResponseObject result = null;
		
		if("register".equals(flag)){
			User user  = uDao.register(user_tel, pwd);
			System.out.println("user = "+user);
			if(user != null){
				result = new ResponseObject(1, "注册成功!",user);
			}else{
				result = new ResponseObject(0, "注册失败!");
			}
		}else if("login".equals(flag)){
			User user = uDao.login(user_tel, pwd);
			if(user!=null){
				result = new ResponseObject(1, "登陆成功",user);
			}else{
				result = new ResponseObject(0, "登陆失败,用户名或密码不正确！");
			}
		}else if("username".equals(flag)){
			System.out.println("user_name="+user_name);
			
			String user_name1 = URLDecoder.decode(user_name, "utf-8");
			user_name1 = URLDecoder.decode(user_name1, "utf-8");
			System.out.println("user_name1 = "+user_name1);
			
			User user  = uDao.getUerName(user_tel, user_name1);
			if(user != null){
				result = new ResponseObject(1, "更新成功!",user);
			}else{
				result = new ResponseObject(0, "更新失败!");
			}
		}else if("usergender".equals(flag)){
			System.out.println("user_gender="+user_gender);
			User user  = uDao.getUerGender(user_tel, user_gender);
			if(user != null){
				result = new ResponseObject(1, "更新成功!",user);
			}else{
				result = new ResponseObject(0, "更新失败!");
			}
		}else if("useraddress".equals(flag)){
			String user_address1 = URLDecoder.decode(user_address, "utf-8");
			user_address1 = URLDecoder.decode(user_address1, "utf-8");
			System.out.println("user_address="+user_address1);
			User user  = uDao.getUerAddress(user_tel, user_address1);
			if(user != null){
				result = new ResponseObject(1, "更新成功!",user);
			}else{
				result = new ResponseObject(0, "更新失败!");
			}
		}else if("userInfo".equals(flag)){
			List<User> list = uDao.getUserInfo(user_tel);
			if(list != null && list.size()>0){
				//正确返回并且带有数据
				result = new ResponseObject(1, list);
				}else {
				result = new ResponseObject(0, "没有店铺数据");
				}
			
		}else if("userimg".equals(flag)){
			try {  
	            smartUpload.initialize(this.getServletConfig(), request, response);  
	            smartUpload.upload();  
	            String msg = smartUpload.getRequest().getParameter("msg");  
	            if (msg != null)  
	                msg = new String(msg.getBytes("GBK"), "utf-8");  
	            com.jspsmart.upload.Files files = smartUpload.getFiles();  
	            for (int i = 0; i < files.getCount(); i++) {  
	                com.jspsmart.upload.File file = files.getFile(i);  
	                if (!file.isMissing()) {  
	                    SimpleDateFormat sdf = new SimpleDateFormat(  
	                            "yyyyMMddHHmmssSSS");  
	                    String name = sdf.format(new java.util.Date());
	                    System.out.println("name="+name);
	                    name = name + "." + file.getFileExt();// 得到文件的扩展名  
	                    String filename = this.getServletContext().getRealPath("/")  
	                            + "images\\" + name;
	                    System.out.println("filename"+filename);
	                    file.saveAs(filename);
	                    
	                    user_Img = "http://119.29.183.12:8080/haircut-server/images/"+name; 
	                    System.out.println("user_Img=="+user_Img);
	                    
	                    User user  = uDao.getUerImg(user_tel, user_Img);
	        			if(user != null){
	        				result = new ResponseObject(1, "更新成功!",user);
	        			}else{
	        				result = new ResponseObject(0, "更新失败!");
	        			}
	                } 
	            } 
	        } catch (SmartUploadException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            // TODO: handle exception  
	            e.printStackTrace();  
	        }
			
	}
		
		out.print(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
	}

}
