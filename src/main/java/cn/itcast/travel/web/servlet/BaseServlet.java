package cn.itcast.travel.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BaseServlet
 */

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		System.out.println("baseservlet执行了");
    		
    		//获取请求路径
    		String uri = req.getRequestURI();	//   /travel/user/add	
    		System.out.println(uri);
    		//获取方法名字
    		String methodName = uri.substring(uri.lastIndexOf('/')+1);
    		System.out.println(methodName);
    		//获取方法对象method
    		System.out.println(this);
    		
    		try {
    			//获取方法
				Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			////执行方法
				method.invoke(this, req,resp);
			
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
    	}
	
    
    
    //将传入对象序列化
    public void writeValue(Object obj,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		
		response.setContentType("application/json;charset=utf-8");
		mapper.writeValue(response.getOutputStream(), obj);
		
	}
    
    
    
    public String writeValueAsString(Object obj) throws JsonProcessingException  {
    	ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(obj);
		
	}
    
    
    
  
    
    
    
}
