package cn.itcast.travel.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.travel.service.impl.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ActiveUserServlet
 */
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取激活码
		String code = request.getParameter("code");
		if (code!=null) {
			//调用service激活
			UserService service = new UserServiceImpl();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
