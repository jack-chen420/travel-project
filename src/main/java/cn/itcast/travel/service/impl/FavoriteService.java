package cn.itcast.travel.service.impl;

public interface FavoriteService {

	public boolean isFavorite(String rid,int uid);

	public void add(String rid, int uid);
}
