package cn.itcast.travel.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao{
	
	private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from tab_category";
		return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
	}

}
