package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
	
	public PageBean<Route> pageQuery(int cid,int cuurentPage,int pageSize,String rname) ;

	public Route findOne(String rid);

}
