package cn.itcast.travel.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//userservice的业务对象
	private UserService service = new UserServiceImpl();
    

	//注册功能
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//验证码
				String check = request.getParameter("check");
				HttpSession session = request.getSession();
				String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");
				session.removeAttribute("CHECKCODE_SERVER");//保证验证码用一次
				
				//比较
				if (checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)) {
					//验证码错误
					ResultInfo info = new ResultInfo();
					//注册失败
					  info.setFlag(false);
			          info.setErrorMsg("验证码错误!");
			        //将info对象序列化为json
			          ObjectMapper mapper = new ObjectMapper();
			          String json = mapper.writeValueAsString(info);
			          response.setContentType("application/json;charset=utf-8");
			          response.getWriter().write(json);
					
					return;
				}

		        

		        //1.获取数据
		        Map<String, String[]> map = request.getParameterMap();

		        //2.封装对象
		        User user = new User();
		        try {
		            BeanUtils.populate(user,map);
		        } catch (IllegalAccessException e) {
		            e.printStackTrace();
		        } catch (InvocationTargetException e) {
		            e.printStackTrace();
		        }

		        //3.调用service完成注册
//		        UserService service = new UserServiceImpl();
		        boolean flag = service.regist(user);
		        ResultInfo info = new ResultInfo();
		        //4.响应结果
		        if(flag){
		            //注册成功
		            info.setFlag(true);
		        }else{
		            //注册失败
		            info.setFlag(false);
		            info.setErrorMsg("注册失败!");
		        }

		        //将info对象序列化为json
		        ObjectMapper mapper = new ObjectMapper();
		        String json = mapper.writeValueAsString(info);

		        //将json数据写回客户端
		        //设置content-type
		        response.setContentType("application/json;charset=utf-8");
		        response.getWriter().write(json);

	}

	//登录功能
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户名和密码
		Map<String, String[]> map = request.getParameterMap();
		//封装user对象
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//调用service查询
		
//		UserService service = new UserServiceImpl();
		User u = service.login(user);
		
		ResultInfo info = new ResultInfo();
		
		//判断对象是否为null
		if (u==null) {
			//用户名密码错误
			info.setFlag(false);
			info.setErrorMsg("用户名或密码错误！");
		}
		//有对象判断用户是否激活
		if (u!=null && !"Y".equals(u.getStatus())) {
			//用户尚未激活
			info.setFlag(false);
			info.setErrorMsg("用户尚未激活！！");
		}
		//成功判断
		if (u!=null && "Y".equals(u.getStatus())) {
			//登录成功的标记
			request.getSession().setAttribute("user", u);
			
			info.setFlag(true);
		}
		
		//响应数据
		ObjectMapper mapper = new ObjectMapper();
		
		response.setContentType("application/json;charset=utf-8");
		
		mapper.writeValue(response.getOutputStream(), info);
		
	}
	
	//查询单个功能
	public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object user = request.getSession().getAttribute("user");
		
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json;charset=utf-8");
		mapper.writeValue(response.getOutputStream(), user);
	}
	
	
	//退出功能
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//销毁session
		request.getSession().invalidate();
				
		//跳转登录页面
		response.sendRedirect(request.getContextPath()+"/login.html");
	
	}
	
	
	//激活功能
	public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//销毁session
	 	//获取激活码
		 String code = request.getParameter("code");
		 if (code!=null) {
		 	//调用service激活
//		 	UserService service = new UserServiceImpl();
		 	boolean flag=service.active(code);
		 	
		 	//判断标记
		 	String msg=null;
		 	if (flag) {
		 		//激活成功
		 		msg="激活成功，请<a href='login.html'>登录</a>";
		 	}else {
		 		//激活失败
		 		msg="激活成功，请联系管理员";
		 	}
		 	//写回页面
		 	response.setContentType("text/html;charset=utf-8");
		 	response.getWriter().write(msg);
	
	}
	
}
}
