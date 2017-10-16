package com.swust.cfg;

import com.jfinal.config.Routes;
import com.swust.mobile.controller.*;

/**
 * 手机端的路由信息表,所有手机的访问走这条路
 * 
 * @author Administrator
 *
 */
public class MobileRoutes extends Routes{
	public void config() {
		//手机端用户登录
		add("/api/user",UserControllerMobile.class);
			
		//新闻,和新闻评论,hsongjiang 2015-1-31
		add("/api/news",NewsMobile.class);
		add("/api/newscomment",NewsCommentControllerMobile.class);
		//渔获
		add("/api/harvest", HarvestControllerMobile.class);

		//富币商城
		add("/api/fubi", FubiMallControllerMobile.class);//富币商城
		add("/api/fubiget", FubiControllerMobile.class);// 富币消费明细
		
		//订单
		add("/api/order", OrderControllerMobile.class);
		
		//用户个人中心
		add("/api/usercenter",UserControllerMobile.class);
		
		add("/api/mall",MallControllerMobile.class);//普通商城
		
		add("/api/teambuy",TeambuyMobile.class);//团购
	}
}
