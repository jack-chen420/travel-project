package cn.itcast.travel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.itcast.travel.dao.impl.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class CategoryServiceImpl implements CategoryService{
	
	private CategoryDao categoryDao=new CategoryDaoImpl();
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		
		//从redis查询
		Jedis jedis = JedisUtil.getJedis();
		//用sortedset查询
//		Set<String> categorys = jedis.zrange("category", 0, -1);
		//查询分数（cid）和值(cname)de 方法
		Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
		
		List<Category> cs=null;
		//判断是否为空
		if (categorys == null || categorys.size()==0) {
			//为空要从数据库查询。存入redis
			System.out.println("从数据库查");
			cs = categoryDao.findAll();
			//存入key为category的健的集合中
			for (int i = 0; i < cs.size(); i++) {
				
				jedis.zadd("category", cs.get(i).getCid(),cs.get(i).getCname());
			}
			
		}else {
			//如果不为空，将set存入list 
			System.out.println("从redis查");
			cs=new ArrayList<Category>();
			for (Tuple tuple : categorys) {
				Category category=new Category();
				category.setCname(tuple.getElement());
				category.setCid((int)tuple.getScore());
				cs.add(category);
			}
		}
		
	return cs;
	
	}

}
