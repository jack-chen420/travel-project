package cn.itcast.travel.dao.impl;

import java.util.List;

import cn.itcast.travel.domain.Route;

public interface RouteDao {
	
	//根据cid查询总记录数
	
	public int findTotalCount(int cid,String rname) ;
	
	//根据cid start pageSize查询当页的数据集合
	
	public List<Route> findByPage(int cid,int start,int pageSize,String rname) ;
	
	//根据rid查询一个route
	public Route findOne(int rid);

}
