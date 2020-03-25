package cn.itcast.travel.dao.impl;

import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;

public class FavoriteDaoImpl implements FavoriteDao{
	
	private JdbcTemplate tempalte=new JdbcTemplate(JDBCUtils.getDataSource());

	//根据uid 和rid查询用户
	@Override
	public Favorite findByRidAndUid(int rid, int uid) {
		// TODO Auto-generated method stub
		String sql="select * from tab_favorite where rid = ? and uid = ? ";
		 Favorite favorite = null;
		
		try {
			favorite = tempalte.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return favorite;
	}

	@Override
	public int findCountByRid(int rid) {
		// TODO Auto-generated method stub
		String sql="select count(*) from tab_favorite where rid = ? ";
		return tempalte.queryForObject(sql,Integer.class,rid);
	}

	@Override
	public void add(int rid, int uid) {
		// TODO Auto-generated method stub
		String sql="insert into tab_favorite values(?,?,?)";
		tempalte.update(sql,rid,new Date(),uid);
	}

}
