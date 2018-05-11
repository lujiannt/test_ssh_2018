package com.lj.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lj.mybatis.model.*;

public class MapperTest {
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void buildFactory() throws IOException {
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	}
	
	
	
	/**
	 * 测试简单mapper编程_1
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserById(3);
		System.out.println(user.toString());
		
		sqlSession.close();
	}
	
	/**
	 * 测试简单mapper编程_2
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test2() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.getUsersByName("涛");
		System.out.println(list.toString());
		
		sqlSession.close();
	}
	
	/**
	 * 测试简单mapper编程_包装类
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test3() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setUserName("周涛");
		UserVo userVo = new UserVo();
		userVo.setUser(user);
		List<UserVo> list = userMapper.getUsersByComplex(userVo);
		System.out.println(list.toString());
		
		sqlSession.close();
	}
	
	/**
	 * 测试简单mapper编程_resultMap_简单使用(当查询字段和model字段不一致时--)
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test4() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		List<User> list = userMapper.getUsersByResulstMap("周涛");
		System.out.println(list.size());
		
		sqlSession.close();
	}
	
	/**
	 * 测试简单mapper编程_resultMap_具体使用one2one
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test6() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		
		List<Order> list = orderMapper.getOrdersByResulstMapOne2One();
		System.out.println(list.size());
		System.out.println(list.toString());
		
		sqlSession.close();
	}
	
	/**
	 * 测试简单mapper编程_resultMap_具体使用one2many
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test7() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		
		List<Order> list = orderMapper.getOrdersByResulstMapOne2Many();
		for(Order o : list) {
			System.out.println(o.toString());
			for(OrderProduct op : o.getOrderProducts()) {
				System.out.println(op.toString());
			}
			System.out.println("------------------------------");
		}
		
		sqlSession.close();
	}
	
	/**
	 * 测试简单mapper编程_resultMap_具体使用_多层嵌套(这里只是为了显示嵌套，而user和product正好逻辑上是多对多,所以方法名叫many2many)
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test8() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		
		List<User> list = orderMapper.getOrdersByResulstMapMany2Many();
		for(User u : list) {
			System.out.println(u.toString());
			
			for(Order o : u.getOrders()) {
				System.out.println(o.toString());
				
				for(OrderProduct op : o.getOrderProducts()) {
					System.out.println(op.toString());
				}
			}
			System.out.println("------------------------------");
		}
		
		sqlSession.close();
	}
	
	/**
	 * 测试简单mapper编程_sql代码块
	 * @throws Exception
	 * @author lujian
	 * @create 2018年5月9日
	 */
	@Test
	public void test5() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(3);
		ids.add(8);
		ids.add(13);
		User user = new User();
		user.setUserName("周涛");
		UserVo userVo = new UserVo();
		userVo.setUser(user);
		userVo.setIds(ids);
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<UserVo> list = userMapper.getUsersByComplex(userVo);
		System.out.println(list.toString());
		
		sqlSession.close();
	}
	
}
