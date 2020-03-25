package cn.itcast.travel.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	private CategoryService	service=new CategoryServiceImpl();
   
	//查询所有
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用service查询所有
		List<Category> cs = service.findAll();
		//序列化json返回
		writeValue(cs, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
