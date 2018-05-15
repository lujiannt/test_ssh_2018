package com.lj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lj.model.User;

/**
 * 注解_基础使用
 * @author lujian
 * @create 2018年5月15日
 * @version 1.0
 */
@Controller
public class UserController_03{
	
	@RequestMapping("userlistByAn")
	public ModelAndView user_list(){
		ModelAndView view = new ModelAndView("/WEB-INF/jsp/user/user_list.jsp");
		
		List<User> userList = new ArrayList<User>();
		userList.add(new User(1, "张三", 11, new Date()));
		userList.add(new User(2, "李四", 21, new Date()));
		userList.add(new User(3, "王五", 31, new Date()));
		
		view.addObject("userList", userList);
		return view;
	}


}
