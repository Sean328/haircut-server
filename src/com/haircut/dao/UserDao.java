package com.haircut.dao;

import java.util.List;

import com.haircut.enity.User;

public interface UserDao {
	//��½�ķ���
	User login(String phoneNumber,String pwd);
	//ע��Ľӿ�
	User register(String phoneNumber,String pwd);
	//�����û���Ϣ�Ľӿ�
	User getUerName(String user_tel,String user_name);
	User getUerGender(String user_tel,String user_gender);
	User getUerAddress(String user_tel,String user_address);
	User getUerImg(String user_tel,String user_img);
	User getUerSignature(String user_tel,String user_signature);
	//��ȡ�û�����Ϣ
	public List<User> getUserInfo(String user_tel);
	
}
