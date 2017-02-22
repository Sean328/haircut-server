package com.haircut.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class ReceiveImgServlet
 */
@WebServlet("/ReceiveImgServlet")
public class ReceiveImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReceiveImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);  
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html,charset=UTF-8");  
		  
        SmartUpload smartUpload = new SmartUpload();  
  
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

}
