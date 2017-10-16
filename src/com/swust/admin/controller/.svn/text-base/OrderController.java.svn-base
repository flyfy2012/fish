package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.OrderModel;
import com.swust.model.OrderPackageModel;

/**
 * 订单管理
 * @Description 
 * @author inging44
 * @date 2016年1月6日 下午1:36:21 
 * @version V0.1
 */
public class OrderController  extends CommonController {
	
	private static String orderRel = "orderList";
	/**
	 * @category  订单列表
	 * @author inging44
	 * @date 2016年1月6日 下午1:36:31
	 */
	public void index(){
		int uid = ShiroUtils.getUserId();
		String keyword = getPara("keyword","").trim();
		setAttr("keyword",keyword);
		Page<OrderPackageModel> packages = OrderPackageModel.dao.paginate(getParaToInt("pageCurrent", 1), getParaToInt("pageSize", Preference.PAGE_PER_SIZE),uid,keyword);
		for(OrderPackageModel orderPackage:packages.getList()){
			orderPackage.put("list", OrderModel.dao.getByPackageId(orderPackage.getInt("id")));
		}
		setAttr("page",packages);
		render("orderList.html");
	}
	
	/***
	 * @category  发货
	 * @author inging44
	 * @date 2016年1月7日 上午11:12:16
	 */
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
		render(BjuiRender.refresh(orderRel));
	}
	
	/**
	 * @category 确认完成退款 
	 * @author inging44
	 * @date 2016年1月8日 下午2:03:00
	 */
	public void endRefund(){
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
		if(order.getInt("status")!=1&&order.getInt("status")!=2&&order.getInt("tuiStatus")!=1){
			render(BjuiRender.error("当前订单状态不能执行该操作"));
			return;
		}
		order.set("tuiStatus", 2).update();
		render(BjuiRender.refresh(orderRel));
	}
	
}
