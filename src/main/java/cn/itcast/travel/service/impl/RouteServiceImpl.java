package cn.itcast.travel.service.impl;

import java.util.List;

import cn.itcast.travel.dao.impl.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDao;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDao;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

public class RouteServiceImpl implements RouteService{
	
	private RouteDao routeDao=new RouteDaoImpl();
	private RouteImgDao routeImgDao=new RouteImgDaoImpl();
	private SellerDao sellerDao=new SellerDaoImpl();
	private FavoriteDao favoriteDao=new FavoriteDaoImpl();

	@Override
	public PageBean<Route> pageQuery(int cid, int cuurentPage, int pageSize,String rname) {
		// TODO Auto-generated method stub
		//封装pagebean
		PageBean<Route> pb = new PageBean<Route>();
		//设置当前页码
		pb.setCurrentPage(cuurentPage);
		//设置每页条数
		pb.setPageSize(pageSize);
		
		//总记录数
		int totalCount=routeDao.findTotalCount(cid,rname);
		pb.setTotalCount(totalCount);
		
		//当前页的数据集合
		int start=(cuurentPage-1)*pageSize;
		List<Route> list = routeDao.findByPage(cid, start, pageSize,rname);
		pb.setList(list);
		
		//总页数
		
		int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
		pb.setTotalPage(totalPage); 
		
		
		
		return pb;
	}

	//根据rid查询
	@Override
	public Route findOne(String rid) {
		// TODO Auto-generated method stub
		//根据id去route表查询route对象
		Route route = routeDao.findOne(Integer.parseInt(rid));
		
		//根据route的id 查询图片集合信息
		List<RouteImg> list = routeImgDao.findByRid(Integer.parseInt(rid));
		//将集合设置到route对象
		route.setRouteImgList(list);
		//根据route的sid(s商家id)查询卖家对象
		Seller seller = sellerDao.findById(route.getSid());
		route.setSeller(seller);
		
		//查询收藏次数
		int count = favoriteDao.findCountByRid(route.getRid());
		route.setCount(count);
		
		return route;
	}
	

}
