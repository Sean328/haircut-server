package com.haircut.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haircut.dao.CItyDao;
import com.haircut.enity.City;


public class CityDaoImpl extends BaseDao implements CItyDao{

	@Override
	public List<City> getCity() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<City>cities = null;
		
		try {
			connection = getConn();//获取数据库的连接对象
			statement =  connection.createStatement();//执行sql语句的对象
			//获取的结果集对象
			resultSet =  statement.executeQuery("select * from city order by city_sortkey");
			cities = new ArrayList<City>();
			//将结果集里的数据循环放到列表里面
			while (resultSet.next()) {
				City city = new City();
				city.setId(resultSet.getString("city_id"));
				city.setName(resultSet.getString("city_name"));
				city.setSortKey(resultSet.getString("city_sortkey"));
				cities.add(city);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
				try {
					close(resultSet, statement, connection);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
			
		return cities;
	}

	
}
