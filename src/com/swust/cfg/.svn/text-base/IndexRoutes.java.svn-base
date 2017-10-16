package com.swust.cfg;

import com.jfinal.config.Routes;
import com.swust.unit.test.ImportExcelController;
import com.swust.unit.test.i18nController;
import com.swust.web.controller.PortalController;
import com.swust.web.controller.RegistController;


/**
 * 
 * @Description 前台的路由
 * @author Administrator
 * @date 2015年8月12日 上午11:05:18 
 * @version V0.1
 */

public class IndexRoutes extends Routes{

	/**
	 * 
	 * Description  
	 * @see com.jfinal.config.Routes#config()
	 */
	@Override
	public void config() {
		// TODO Auto-generated method stub
		//首页地址:右上角能显示，优惠发布中心，课程管理.
		add("/", PortalController.class,"/portal");
		
		//前台登录:主要针对优化发布者和教师
		add("/portal/login",RegistController.class,"/portal");
		
		add("/portal/i18n", i18nController.class,"/portal");
		add("/portal/importexcel", ImportExcelController.class,"/portal");

		
	}

}
