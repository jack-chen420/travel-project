package cn.itcast.travel.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

/**
 * Servlet implementation class RouteServlet
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private RouteService routeService=new RouteServiceImpl();
	private FavoriteService favoriteService=new FavoriteServiceImpl();
   
	//分页查询
	
	public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受参数
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");

		String cidStr = request.getParameter("cid");
		//接受线路名称
		String rname = request.getParameter("rname");
		
		

		//处理参数
		int cid=0;
		if (cidStr!=null && cidStr.length()>0 && !"null".equals(cidStr)) {
			cid=Integer.parseInt(cidStr);
		}
		
		int currentPage=0;
		if (currentPageStr!=null && currentPageStr.length()>0) {
			currentPage=Integer.parseInt(currentPageStr);
		}else {
			currentPage=1;//不传递默认为第一页
		}
		
		int pageSize=0;//每页显示条数
		if (pageSizeStr!=null && pageSizeStr.length()>0) {
			pageSize=Integer.parseInt(pageSizeStr);
		}else {
			pageSize=5;
		}
		
		//调用service查询pagebean对象
		PageBean<Route>pb=routeService.pageQuery(cid,currentPage,pageSize,rname);
		
		//将pagebean序列化为json
		writeValue(pb, response);
		
	}
	
	
	
	//根据rid查询一个旅游线路的详细信息
	public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//接受id
		String rid = request.getParameter("rid");
		
		//调用service查询route对象
		Route route=routeService.findOne(rid);
		
		//转为json写回客户端
		writeValue(route, response);
	
}
	
	//判断当前用户是否收藏过该线路
	public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取线路id
		String rid = request.getParameter("rid");
	
		//获取当前登录的用户
		User user = (User) request.getSession().getAttribute("user");
		int uid;
		if (user==null) {
			//用户尚未登录
			uid=0;
		}else {
			//已经登录
			uid=user.getUid();
		}
	
		//调用FavoriteService查询是否收藏
		boolean flag = favoriteService.isFavorite(rid, uid);
		
		//写回客户端
		writeValue(flag, response);
	}
	
	
	//添加收藏
	public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取线路id
		String rid = request.getParameter("rid");
		

		//获取当前登录的用户
		User user = (User) request.getSession().getAttribute("user");
		int uid;
		if (user==null) {
			//用户尚未登录
			return;
		}else {
			//已经登录
			uid=user.getUid();
		}
		
		//调用service添加
		favoriteService.add(rid,uid);
	
	
	
		
		
	}
	
}
