package com.haircut.dao;

import java.util.List;

import com.haircut.enity.Collect;

public interface CollectDao {

	public List<Collect> getCollect();
	Collect register(String goods_id,String user_tel);
	
	Collect get(String user_tel);

}
