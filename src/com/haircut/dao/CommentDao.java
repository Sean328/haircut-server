package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Comment;

public interface CommentDao {
	//��ȡ�����б�
	public List<Comment> getComment();

	//��������
	Comment submit(String goods_id,String user_tel,String name,String comments,String clevel,String cpermoney,String time,String orders_id);
}
