package cn.itcast.travel.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		UserService service = new UserServiceImpl();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
