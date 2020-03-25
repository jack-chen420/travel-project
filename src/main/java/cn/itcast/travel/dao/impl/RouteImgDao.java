package cn.itcast.travel.dao.impl;

import java.util.List;

import cn.itcast.travel.domain.RouteImg;

public interface RouteImgDao {
	
	//根据route id 查询图片
	public List<RouteImg> findByRid(int rid);

}
