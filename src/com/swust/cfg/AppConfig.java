/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.swust.cfg;

import com.jfaker.framework.security.model.Authority;
import com.jfaker.framework.security.model.Menu;
import com.jfaker.framework.security.model.Org;
import com.jfaker.framework.security.model.Resource;
import com.jfaker.framework.security.model.Role;
import com.jfaker.framework.security.model.User;
import com.jfaker.framework.security.shiro.ShiroPlugin;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.scheduler.SchedulerPlugin;
import com.jfinal.render.ViewType;
import com.swust.model.AddressModel;
import com.swust.model.FubiBannerModel;
import com.swust.model.FubiModel;
import com.swust.model.FubidetailModel;
import com.swust.model.GoodsModel;
import com.swust.model.HarvestCommentModel;
import com.swust.model.HarvestModel;
import com.swust.model.LimitGoodsModel;
import com.swust.model.LimitTimeModel;
import com.swust.model.NewsCategoryModel;
import com.swust.model.NewsCommentModel;
import com.swust.model.NewsModel;
import com.swust.model.NewsReportModel;
import com.swust.model.NewsUpDownModel;
import com.swust.model.OrderModel;
import com.swust.model.SeckillGoodsModel;
import com.swust.model.UserCountModel;
import com.swust.model.UserFollowModel;
import com.swust.model.*;


/**
 *应用总配置
 * @Description 
 * @author hsongjiang
 * @version V0.1
 */
public class AppConfig extends JFinalConfig {
	/**
	 * 配置常量
	 * Description 
	 * @param me 
	 * @see com.jfinal.config.JFinalConfig#configConstant(com.jfinal.config.Constants)
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("jfinal.properties");
		me.setDevMode(getPropertyToBoolean("devMode", false));

		me.setViewType(ViewType.FREE_MARKER);

		me.setBaseViewPath("WEB-INF/html/");
		
		/**配置國際化**/
//		me.setI18nDefaultBaseName("message");  //设置in18配置文件
		

	}

	/**
	 * 配置路由
	 * Description 分为前台、后台以及手机端JSON接口
	 * @param me 
	 * @see com.jfinal.config.JFinalConfig#configRoute(com.jfinal.config.Routes)
	 */
	public void configRoute(Routes me) {

		// 手机端的API，里面全部render为JSON数据
		me.add(new MobileRoutes());
		me.add(new AdminRoutes());
		me.add(new IndexRoutes());
	}

	/**
	 * 配置插件
	 * Description 配置插件
	 * @param me 
	 * @see com.jfinal.config.JFinalConfig#configPlugin(com.jfinal.config.Plugins)
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("sec_user", User.class);
		arp.addMapping("sec_org", Org.class);
		arp.addMapping("sec_role", Role.class);
		arp.addMapping("sec_authority", Authority.class);
		arp.addMapping("sec_resource", Resource.class);
		arp.addMapping("sec_menu", Menu.class);


		arp.addMapping("fish_news", NewsModel.class);// 新闻
		arp.addMapping("fish_news_category", NewsCategoryModel.class);// 新闻类别
		arp.addMapping("fish_news_comment", NewsCommentModel.class);// 新闻评论
		arp.addMapping("fish_news_upDown", NewsUpDownModel.class);// 新闻 顶踩
		arp.addMapping("fish_news_report", NewsReportModel.class);  //新闻的举报
		

		//渔获
		arp.addMapping("fish_harvest", HarvestModel.class);//渔获 by inging44
		arp.addMapping("fish_harvest_comments", HarvestCommentModel.class);
		arp.addMapping("fish_harvest_pictures", HarvestPicturesModel.class);//渔获的图片
		
		

		arp.addMapping("fish_user_follow", UserFollowModel.class);// 用户关注 by inging44
		arp.addMapping("fish_user_address", AddressModel.class);// 用户收货地址 by inging44
		arp.addMapping("fish_user_count", "uid", UserCountModel.class);// 用户统计 by inging44
		arp.addMapping("fish_user_emailcode", "email", EmailCodeModel.class);// 邮箱验证码 by inging44
		arp.addMapping("fish_secret_code", "tel", SecretCodeModel.class);// 邮箱验证码 by inging44
		
		//订单
		arp.addMapping("fish_order", OrderModel.class);//
		
		arp.addMapping("fish_goods", GoodsModel.class);//商品总表
		arp.addMapping("fish_goods_category", GoodsCategoryModel.class);// 商品分类 by inging44
		arp.addMapping("fish_goods_keyt_params", CatKeytModel.class);// 商品属性 by inging44
		arp.addMapping("fish_goods_keyt_params_value", CatKeytValModel.class);// 商品属性值 by inging44
		arp.addMapping("fish_goods_nonkeyt_params", CatNonkeytModel.class);// 非关键属性 by inging44
		arp.addMapping("fish_goods_nonkeyt_params_value", CatNonkeytValModel.class);// 属性值 by inging44
		arp.addMapping("fish_goods_keyt_params_value_goods", KeytValGoodsModel.class);// 商品对应具体属性值 by inging44
		arp.addMapping("fish_goods_count_month", GoodsCountMonthModel.class);// 商家统计 by inging44
		arp.addMapping("fish_goods_pic", GoodsPicModel.class);// 商品图片表 by inging44
		arp.addMapping("fish_shopping_cart", ShoppingCartModel.class);// 购物车by inging44
		arp.addMapping("fish_package", OrderPackageModel.class);// 封装订单 by inging44
		
		arp.addMapping("fish_mall_teambuy", TeamBuyModel.class);//
		arp.addMapping("fish_mall_teambuy_user", TeamBuyUserModel.class);//
		
		//富币商城
		arp.addMapping("fish_mall_limited_goods", LimitGoodsModel.class);//限时特惠商品
		arp.addMapping("fish_mall_limited", LimitTimeModel.class); //限时特惠时间

		arp.addMapping("fish_mall_seckill_goods", SeckillGoodsModel.class);//整点秒杀商品
		arp.addMapping("fish_fubi_mall_banner", FubiBannerModel.class);//富币商城Banner
		
		arp.addMapping("fish_fubi_type", FubiModel.class);  //获取富币的方式
		arp.addMapping("fish_fubi_detail",FubidetailModel.class);//富币消费细节
		
		//消息板块
		arp.addMapping("fish_message", MessageModel.class);//消息模块

		/***配置shiro插件***/ 

		ShiroPlugin shiroPlugin = new ShiroPlugin();
		me.add(shiroPlugin);
		
		/*****配置周期任务*******/
		SchedulerPlugin sp = new SchedulerPlugin("schedule.properties");
		me.add(sp);	
		
		/********配置Ehcache插件********/
		me.add(new EhCachePlugin());

	}

	/**
	 *  配置全局拦截器
	 * Description 
	 * @param me 
	 * @see com.jfinal.config.JFinalConfig#configInterceptor(com.jfinal.config.Interceptors)
	 */
	public void configInterceptor(Interceptors me) {
        //配置国际化全局拦截器
		//me.add(new I18nInterceptor());
	}
	
	/**
	 * 配置处理器
	 * Description 
	 * @param me 
	 * @see com.jfinal.config.JFinalConfig#configHandler(com.jfinal.config.Handlers)
	 */
	public void configHandler(Handlers me) {
		/*
		 * In JFinalFilter: handlers.add(new
		 * ContextPathHandler("CONTEXT_PATH"));<br> in freemarker: <img
		 * src="${BASE_PATH}/images/logo.png" />
		 */
		// 此处用于解决View层中，路径问题
		me.add(new ContextPathHandler("BASE_PATH"));
		
	}
	
	// public static void main(String[] args) {
	//
	// JFinal.start("WebRoot", 8088, "/", 6);
	//
	// }
}
