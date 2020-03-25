package cn.itcast.travel.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;

public class RouteImgDaoImpl implements RouteImgDao{

	private JdbcTemplate tempalte=new JdbcTemplate(JDBCUtils.getDataSource());
	
	@Override
	public List<RouteImg> findByRid(int rid) {
		// TODO Auto-generated method stub
		String sql="select * from tab_route_img  where rid = ? ";
		
		return tempalte.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
		
		
	}

	
}
