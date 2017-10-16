package com.swust.admin.controller;


import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.model.User;

import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.ext.render.DwzRender;

/**
 * 个人中心，显示用户的个人信息
 * 
 * @author Administrator
 * 
 */
public class ProfileController extends CommonController {
	/**
	 * hsongjiang 2015-2-7 添加其他用户的个人信息
	 */
	
	
	public  static final String  menuRel= "profileList";
	
	
	public void index() { //
		Integer userID = ShiroUtils.getUserId();
		User currentUser = User.dao.get(userID);
		if (currentUser == null) { // 用户不存在，需要重新登录
			setAttr("code", "0001");
			setAttr("message", "账号或密码不能为空");
			render("login.html");
			return;
		}

		setAttr("user", currentUser);
		

		render("profileListother.html");
		

	}

	/**
	 * @author 修改个人信息
	 */
	public void update() {
		getModel(User.class).update();//更新个人基本信息表
		render(DwzRender.success("更新成功"));
	}
}
