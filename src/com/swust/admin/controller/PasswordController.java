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
public class PasswordController extends CommonController {
	/**
	 * 
	 */
	
	public static final String menuRel = "powerwordList";
	
	
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
		render("password.html");
	}

	public void update() {
		User user = new User().set("id", getPara("user.id")).set(
				"plainPassword", getPara("user.plainPassword"));
		user.entryptPasswordWithSalt(getPara("user.plainPassword"));
		user.update();
		render(DwzRender.refresh(menuRel));

	}

}
