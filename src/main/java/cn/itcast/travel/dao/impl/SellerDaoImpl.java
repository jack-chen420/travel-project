package cn.itcast.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;

public class SellerDaoImpl implements SellerDao{

	private JdbcTemplate tempalte=new JdbcTemplate(JDBCUtils.getDataSource());
	
	
	@Override
	
	public Seller findById(int id) {
		// TODO Auto-generated method stub
		
		String sql="select * from tab_seller  where sid = ? ";
				
		return tempalte.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),id);
				
	}

}
