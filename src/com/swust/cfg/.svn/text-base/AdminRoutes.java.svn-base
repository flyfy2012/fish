package com.swust.cfg;

import com.jfaker.app.web.AdminController;
import com.jfaker.app.web.PictureController;
import com.jfaker.framework.security.web.AuthorityController;
import com.jfaker.framework.security.web.FirstOrgController;
import com.jfaker.framework.security.web.MenuController;
import com.jfaker.framework.security.web.OrgController;
import com.jfaker.framework.security.web.ResourceController;
import com.jfaker.framework.security.web.RoleController;
import com.jfaker.framework.security.web.SecondOrgController;
import com.jfaker.framework.security.web.SecurityTreeController;
import com.jfaker.framework.security.web.UserController;
import com.jfinal.config.Routes;
import com.swust.admin.controller.*;

/**
 *  后台使用的所有路由
 * @author Administrator
 * @date 2015-08-12
 */
public class AdminRoutes extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		
		add("/admin", AdminController.class);
		//用户、资源、角色等权限管理
		add("/security/user", UserController.class);
		add("/security/org", OrgController.class); //部门管理
		add("/security/firstorg", FirstOrgController.class); //部门管理
		add("/security/secondorg", SecondOrgController.class); //部门管理
		add("/security/authority", AuthorityController.class);
		add("/security/role", RoleController.class);
		add("/security/resource", ResourceController.class);
		add("/security/menu", MenuController.class);
		add("/security/tree", SecurityTreeController.class);
		
		/**新闻功能**/
		add("/fish/news",NewsController.class); //新闻
		add("/fish/newsCategory", NewsCategoryController.class);//新闻分类
		add("/fish/newsReport", NewsReportController.class);  
		add("/fish/newsCheck",NewsCheckController.class,"/fish/check/news");  //新闻审核
		
		/**渔获板块**/
		add("/fish/harvest", HarvestController.class);
		
		
		/**富币商城**/
		add("/fish/fubi/limittime", LimitTimeController.class,"/fish/fubi/limit");//限时特惠时间段
		add("/fish/fubi/limitgoods", LimitGoodsController.class,"/fish/fubi/limit");
		
		add("/fish/fubi/seckilltime", SeckillTimeController.class,"/fish/fubi/seckill");//整点秒杀时间设置
		add("/fish/fubi/seckillgoods", SeckillGoodsController.class,"/fish/fubi/seckill"); //整点秒杀商品
		
		add("/fish/fubi/type", FubiController.class,"/fish/fubi/type");//获取富币的类型
		add("/fish/fubi/banner", FubiBannerController.class); //富币商城中的Banner
		add("/fish/fubi/order", FubiOrderController.class);//富币商城订单
		

		add("/fish/teamBuy", TeambuyController.class);//团购后台管理
		
		
		/**商城管理板块**/
		add("/fish/goodsCategory", GoodsCategoryController.class);
		add("/fish/keytAttr", CatKeytController.class);
		add("/fish/nonkeytAttr", CatNonkeytController.class);
		add("/fish/goods", GoodsController.class);
		add("/fish/goodsCheck", GoodsCheckController.class,"/fish/check/goods");
		add("/fish/order",OrderController.class);
		
		/**上传图片路径**/
		add("/file/upload",PictureController.class);
		
		/**通告新闻**/
		add("/fish/announcement",AnnouncementController.class);
		/**消息中心**/
		add("/fish/message", MessageController.class);
	}
	
	

}
