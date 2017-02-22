package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.UserDao;
import com.haircut.enity.Shop;
import com.haircut.enity.User;
import com.jspsmart.upload.SmartUpload;

public class UserDaoImpl extends BaseDao implements UserDao{
	
	private String userNumber;
	
	@Override
	public User register(String phoneNumber, String pwd) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from users where user_tel = '"+phoneNumber+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				return null;
			}
			String sqlForInsert = "insert into users (user_tel,user_pwd) values"
					+ "('"+phoneNumber+"' , '"+pwd+"')";
			statement.execute(sqlForInsert);
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				User user = new User();
				user.setUser_id(resultSet.getString("user_id"));
				user.setUser_name(resultSet.getString("user_name"));
				user.setUser_pwd(resultSet.getString("user_pwd"));
				user.setUser_payword(resultSet.getString("user_payword"));
				user.setUser_tel(resultSet.getString("user_tel"));
				user.setUser_address(resultSet.getString("user_address"));
				user.setUser_gender(resultSet.getString("user_gender"));
				user.setUser_img(resultSet.getString("user_img"));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet,statement,connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;

	}
	
	
	@Override
	public User login(String name, String pwd) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from users where user_tel = '"+name+"' and user_pwd = '"+pwd+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				User user = new User();
				user.setUser_id(resultSet.getString("user_id"));
				user.setUser_name(resultSet.getString("user_name"));
				user.setUser_pwd(resultSet.getString("user_pwd"));
				user.setUser_payword(resultSet.getString("user_payword"));
				user.setUser_tel(resultSet.getString("user_tel"));
				user.setUser_address(resultSet.getString("user_address"));
				user.setUser_gender(resultSet.getString("user_gender"));
				user.setUser_img(resultSet.getString("user_img"));
				return user;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet, statement, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	//填写用户昵称
	@Override
	public User getUerName(String user_tel,String user_name) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from users where user_name = '"+user_name+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				return null;
			}
			String sqlForUpdata = "update users set user_name ='"+user_name+"' where user_tel ="+user_tel;
			statement.execute(sqlForUpdata);
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				User user = new User();
				user.setUser_id(resultSet.getString("user_id"));
				user.setUser_name(resultSet.getString("user_name"));
				user.setUser_pwd(resultSet.getString("user_pwd"));
				user.setUser_payword(resultSet.getString("user_payword"));
				user.setUser_tel(resultSet.getString("user_tel"));
				user.setUser_address(resultSet.getString("user_address"));
				user.setUser_gender(resultSet.getString("user_gender"));
				user.setUser_img(resultSet.getString("user_img"));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet,statement,connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	@Override
	public User getUerGender(String user_tel,String user_gender) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select user_gender from users where user_tel = '"+user_tel+"'";

			String sqlForUpdata = "update users set user_gender ='"+user_gender+"' where user_tel ="+user_tel;
			statement.execute(sqlForUpdata);
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				User user = new User();
				user.setUser_gender(resultSet.getString("user_gender"));
				return user;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet,statement,connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	//获取用户地址
	@Override
	public User getUerAddress(String user_tel,String user_address) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select user_address from users where user_tel = '"+user_tel+"'";

			String sqlForUpdata = "update users set user_address ='"+user_address+"' where user_tel ="+user_tel;
			statement.execute(sqlForUpdata);
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				User user = new User();
				user.setUser_address(resultSet.getString("user_address"));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet,statement,connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	@Override
	public User getUerImg(String user_tel,String user_img) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from users where user_img = '"+user_img+"'";
	
			String sqlForUpdata = "update users set user_img ='"+user_img+"' where user_tel ="+user_tel;
			statement.execute(sqlForUpdata);
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()){
				User user = new User();
				user.setUser_id(resultSet.getString("user_id"));
				user.setUser_name(resultSet.getString("user_name"));
				user.setUser_pwd(resultSet.getString("user_pwd"));
				user.setUser_payword(resultSet.getString("user_payword"));
				user.setUser_tel(resultSet.getString("user_tel"));
				user.setUser_img(resultSet.getString("user_img"));
				user.setUser_address(resultSet.getString("user_address"));
				user.setUser_gender(resultSet.getString("user_gender"));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet,statement,connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	@Override
	public User getUerSignature(String user_tel,String user_signature) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> getUserInfo(String user_tel) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<User>users= null;
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select * from users where user_tel = '"+user_tel+"'");
			users = new ArrayList<User>();
			
			//将结果集里的数据循环放到列表里面
			while (resultSet.next()) {
				User user = new User();
				user.setUser_id(resultSet.getString("user_id"));
				user.setUser_name(resultSet.getString("user_name"));
				user.setUser_pwd(resultSet.getString("user_pwd"));
				user.setUser_payword(resultSet.getString("user_payword"));
				user.setUser_tel(resultSet.getString("user_tel"));
				user.setUser_img(resultSet.getString("user_img"));
				user.setUser_address(resultSet.getString("user_address"));
				user.setUser_gender(resultSet.getString("user_gender"));
				users.add(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				close(resultSet, statement, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return users;
	}


}
