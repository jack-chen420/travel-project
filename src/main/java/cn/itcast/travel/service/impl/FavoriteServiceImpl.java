package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;

public class FavoriteServiceImpl implements FavoriteService{

	private FavoriteDao favoriteDao=new FavoriteDaoImpl();
	@Override
	public boolean isFavorite(String rid, int uid) {
		// TODO Auto-generated method stub
		
		Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
		
		return favorite!=null;//有值为true 无值为false
	}
	@Override
	public void add(String rid, int uid) {
		// TODO Auto-generated method stub
		favoriteDao.add(Integer.parseInt(rid),uid);
	}

}
