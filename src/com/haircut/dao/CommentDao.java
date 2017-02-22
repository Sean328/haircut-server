package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Comment;

public interface CommentDao {
	//获取店铺列表
	public List<Comment> getComment();

	//发表评论
	Comment submit(String goods_id,String user_tel,String name,String comments,String clevel,String cpermoney,String time,String orders_id);
}
