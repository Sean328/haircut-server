package com.haircut.dao;

import java.util.List;

import com.haircut.enity.User;

public interface UserDao {
	//登陆的方法
	User login(String phoneNumber,String pwd);
	//注册的接口
	User register(String phoneNumber,String pwd);
	//完善用户信息的接口
	User getUerName(String user_tel,String user_name);
	User getUerGender(String user_tel,String user_gender);
	User getUerAddress(String user_tel,String user_address);
	User getUerImg(String user_tel,String user_img);
	User getUerSignature(String user_tel,String user_signature);
	//获取用户的信息
	public List<User> getUserInfo(String user_tel);
	
}
