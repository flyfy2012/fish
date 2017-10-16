package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.OrderModel;
import com.swust.model.OrderPackageModel;

/**
 * @category 富币商城订单管理
 * @Description 
 * @author hmilysean 
 * @date 2016年1月7日 下午2:00:39 
 * @version V0.1
 */

public class FubiOrderController extends CommonController{
	
	public void index(){
		int uid = ShiroUtils.getUserId();
		Page<OrderModel> page=OrderModel.dao.paginate(getParaToInt("pageCurrent", 1), getParaToInt("pageSize", Preference.PAGE_PER_SIZE),uid,1,getPara("keyword"));
		setAttr("keyword", getPara("keyword"));
		setAttr("page", page);
		render("orderList.html");
		
	}
	
	
	public void sendOut(){
		int shopId = ShiroUtils.getUserId();
		int orderId = getParaToInt(0,0);
		OrderModel order = OrderModel.dao.findById(orderId);
		if(order==null){
			render(BjuiRender.error("操作有误"));
			return;
		}
		if(order.getInt("shopId")!=shopId){
			render(BjuiRender.error("请勿操作他人订单"));
			return;
		}
		if(order.getInt("status")!=1){
			render(BjuiRender.error("当前订单状态不能执行该操作"));
			return;
		}
		order.set("status", 2).update();
		render(BjuiRender.refresh("fubiorder"));
	}
}
