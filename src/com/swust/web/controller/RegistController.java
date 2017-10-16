package com.swust.web.controller;

import java.io.File;
import com.jfaker.framework.security.model.User;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.swust.cfg.Preference;

/**
 * 
 * @Description 修理工的注册
 * @author hsongjiang
 * @date 2015年8月14日 上午7:28:44 
 * @version V1.3.1
 */

public class RegistController extends Controller {
	/**
	 * 
	 * @Description 注册界面
	 * @author hsongjiang 
	 */
	public void index(){
		render("fixerReg.html");
	}
	
	
	/** 
	 * @Description 页面ajax检测昵称是否已存在
	 * @author inging44
	 * @param username
	 * @return hasUser（true 为不存在，可继续注册；false为存在，请换用户名）
	 */
	public void checkNickname(){
		String nickname = getPara(0);
		User hasUser = User.dao.checkNickname(nickname); 
		if(hasUser==null){
			setAttr("user", false);
		}else{
			setAttr("user", true);
		}
		renderJson();
		
	}
	
	/**
	 * @Description 页面ajax检测手机号是否已经注册过
	 * @author inging44
	 * @param tel
	 * @return 已注册返回false,未注册过返回true
	 */
	public void checkTel(){
		String tel = getPara(0);
		User isReg = User.dao.getUser(tel) ;
		if(isReg==null){
			setAttr("isNew", false);
		}else{
			setAttr("isNew", true);
		}
		renderJson();
	}
	
	/**
	 * 实际完成   注册保存 
	 * @Description 
	 * @author inging44
	 */
	public void toReg(){
		//获取图片
		
		 User user = new User();
		 user.set("username", getPara("user.username").trim());
		 user.set("nickname", getPara("user.nickname").trim());
		 user.set("realname", getPara("user.realname").trim());
		 user.set("plainPassword",getPara("user.plainPassword").trim());
		 user.entryptPasswordWithSalt(getPara("user.plainPassword")); // 加密密码
		 user.set("grouptype","worker");
		 user.set("avatar", getPara("user.avatar").trim());
		 user.save();
		 render("regSuccess.html");
	}
	
	
	
	
}
