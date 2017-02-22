package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.haircut.dao.CommentDao;
import com.haircut.enity.Collect;
import com.haircut.enity.Comment;
import com.haircut.enity.Shop;

public class CommentDaoImp extends BaseDao implements CommentDao{

	@Override
	public List<Comment> getComment() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Comment> comments = null;
		
		try {
			connection =getConn();
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select * from comments order by comments_id");
			comments = new ArrayList<Comment>();
			
			//将结果集里的数据循环放到列表里面
			while (resultSet.next()) {
				Comment comment = new Comment();
				comment.setComments_id(resultSet.getString("comments_id"));
				comment.setGoods_id(resultSet.getString("goods_id"));
				comment.setUser_tel(resultSet.getString("user_tel"));
				comment.setName(resultSet.getString("name"));
				comment.setComments(resultSet.getString("comments"));
				comment.setClevel(resultSet.getString("clevel"));
				comment.setCpermoney(resultSet.getString("cpermoney"));
				comment.setTime(resultSet.getString("time"));
				comment.setOrders_id(resultSet.getString("orders_id"));

				comments.add(comment);
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
		
		return comments;
	}

	@Override
	public Comment submit(String goods_id, String user_tel,String name,String comments, String clevel,String cpermoney, String time,String orders_id) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from comments where goods_id = '"+goods_id+"' AND user_tel = '"+user_tel+"'";
			resultSet = statement.executeQuery(sqlForCheck);
			
			if(resultSet.next()){
				return null;
			}
			
			String sqlForInsert = "insert into comments (goods_id,user_tel,name,comments,clevel,cpermoney,time,orders_id) values"
					+ "( '"+goods_id+"', '"+user_tel+"','"+name+"','"+comments+"','"+clevel+"','"+cpermoney+"','"+time+"','"+orders_id+"')";
			statement.execute(sqlForInsert);
			resultSet = statement.executeQuery(sqlForCheck);
			
			if(resultSet.next()){
				Comment comment1 = new Comment();
				comment1.setGoods_id(resultSet.getString("goods_id"));
				comment1.setUser_tel(resultSet.getString("user_tel"));
				comment1.setName(resultSet.getString("name"));
				comment1.setComments_id(resultSet.getString("comments_id"));
				comment1.setClevel(resultSet.getString("clevel"));
				comment1.setCpermoney(resultSet.getString("cpermoney"));
				comment1.setTime(resultSet.getString("time"));
				comment1.setOrders_id(resultSet.getString("orders_id"));
				comment1.setComments(resultSet.getString("comments"));
				
				return comment1;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				close(resultSet, statement, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
		
	}


}
