package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Favorite;


	//根据uid 和rid查询用户
public interface FavoriteDao {
	
	public Favorite findByRidAndUid(int rid,int uid);

	public int findCountByRid(int rid);

	public void add(int rid, int uid);
}
