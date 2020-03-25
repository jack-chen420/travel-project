package cn.itcast.travel.dao.impl;

import java.util.List;

import cn.itcast.travel.domain.Category;

public interface CategoryDao {
	
	//查询所有
	public List<Category> findAll();

}
