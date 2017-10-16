package com.swust.mobile.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.swust.model.OrderModel;
import com.swust.model.OrderPackageModel;
import com.swust.model.UserCountModel;
import com.swust.utils.EduStringUtil;

public class OrderControllerMobile extends CommonController{
	public void index() {
		renderJson();
	}
	
	/**
	 * @category 用户订单列表 
	 * @author inging44
	 * @date 2016年1月4日 下午3:14:48
	 */
	@Before(Tx.class)
	public void orderList(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("error","未登录");
			renderJson();
			return;
		}
		Integer status = getParaToInt("status",8);
		setAttr("list",OrderModel.dao.findOrderListByUidAndStatus(status,uid));
		renderJson();
	}
	
	/**
	 * @category 生成订单
	 * @author inging44
	 * @date 2016年1月4日 下午8:31:06
	 */
	@Before(Tx.class)
	public void create(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请登录");
			renderJson();
			return;
		}
		int size = getParaToInt("size",1);
		String totalPrice = getPara("totalPrice","");
		int shopId = getParaToInt("shopId",0);
		int hasPostage = getParaToInt("hasPostage",-1);
		if(hasPostage==-1||shopId==0||EduStringUtil.isEmpty(totalPrice)){
			setAttr("error", "-1");
			setAttr("message", "封装订单参数错误");
			renderJson();
			return;
		}
		OrderPackageModel orderPackage = new OrderPackageModel().set("size", size)
				.set("totalPrice", totalPrice)
				.set("shopId", shopId)
				.set("hasPostage", hasPostage)
				.set("dateline", mCurrentDateTime)
				.set("uid", uid);
		orderPackage.save();
		int orderPackageId = orderPackage.getInt("id");
		for(int i=0;i<size;i++){
			int goodsDetailId = getParaToInt("goodsDetailId_"+i,0);
			String p = getPara("price_"+i," ");
			if(goodsDetailId==0||EduStringUtil.isEmpty(p)){
				setAttr("error", "-1");
				setAttr("message", "单个订单参数错误");
				renderJson();
				return;
			}
			int count = getParaToInt("count_"+i,1);
			BigDecimal price = BigDecimal.valueOf(Double.valueOf(p));
			OrderModel order = new OrderModel().set("uid", uid)
					.set("goodsDetailId", goodsDetailId)
					.set("count", count)
					.set("price", price)
					.set("shopId", shopId)
					.set("dateline", mCurrentDateTime);
			order.save();	
			Record record = new Record().set("orderPackageId", orderPackageId).set("orderId", order.get("id"));
			Db.save("fish_order_package", record);
		}
		setAttr("orderPackage",orderPackage);
		setAttr("error", "0");
		setAttr("message", "成功");
		renderJson();
	}	
	
	/**
	 * @category 订单参数（地址、付款方式） 
	 * @author inging44
	 * @date 2016年1月5日 下午3:54:12
	 */
	public void edit(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请登录");
			renderJson();
			return;
		}
		int packageId = getParaToInt("packageId",-1);
		int address = getParaToInt("address",-1);
		int pay = getParaToInt("pay",-1);
		int invoice = getParaToInt("invoice",0);
		String invoiceTitle = getPara("invoiceTitle","").trim();
		if(packageId==-1||address==-1||pay==-1){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return;
		}
		OrderPackageModel orderPackage = OrderPackageModel.dao.findById(packageId);
		if(orderPackage==null){
			setAttr("error", "-1");
			setAttr("message", "封装订单不存在");
			renderJson();
			return;
		}
		if(orderPackage.getInt("uid")!=uid){
			setAttr("error", "-1");
			setAttr("message", "他人订单请勿操作");
			renderJson();
			return;
		}
		orderPackage.set("address", address).update();
		List<OrderModel> list = OrderModel.dao.getByPackageId(packageId);
		for(OrderModel order:list){
			order.set("invoiceTitle", invoiceTitle)
				.set("invoice", invoice)
				.set("address", address)
				.set("pay", pay)
				.update();
		}
		setAttr("orderPackage",orderPackage);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	/**
	 * @category 提醒发货 
	 * @author inging44
	 * @date 2016年1月5日 下午2:27:41
	 */
	public void reminder(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请登录");
			renderJson();
			return;
		}
		int orderId = getParaToInt("orderId",0);
		if(orderId==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return;
		}
		OrderModel order = OrderModel.dao.findById(orderId);
		if(order==null){
			setAttr("error", "-1");
			setAttr("message", "当前订单不存在");
			renderJson();
			return;
		}
		order.set("isReminder", 1).update();
		setAttr("error","0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category 申請退款 
	 * @author inging44
	 * @date 2016年1月8日 上午9:46:41
	 */
	public void refund(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请登录");
			renderJson();
			return;
		}
		int orderId = getParaToInt("orderId",0);
		if(orderId==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return;
		}
		OrderModel order = OrderModel.dao.findById(orderId);
		if(order==null){
			setAttr("error", "-1");
			setAttr("message", "当前订单不存在");
			renderJson();
			return;
		}
		if(order.getInt("status")!=1&&order.getInt("status")!=2){
			setAttr("error", "-1");
			setAttr("message", "当前订单不能进行此操作");
			renderJson();
			return;
		}
		order.set("tuiStatus", 1).update();
		setAttr("error","0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category 取消订单 
	 * @author inging44
	 * @date 2016年1月5日 下午2:47:54
	 */
	public void dropOrder(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请登录");
			renderJson();
			return;
		}
		int orderId = getParaToInt("orderId",0);
		if(orderId==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return;
		}
		OrderModel order = OrderModel.dao.findById(orderId);
		if(order==null){
			setAttr("error", "-1");
			setAttr("message", "当前订单不存在");
			renderJson();
			return;
		}
		if(order.getInt("status")!=0){
			setAttr("error", "-1");
			setAttr("message", "订单不可取消");
			renderJson();
			return;
		}
		order.set("status", -1).update();
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category  删除订单
	 * @author inging44
	 * @date 2016年1月5日 下午2:48:02
	 */
	public void delOrder(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请登录");
			renderJson();
			return;
		}
		int orderId = getParaToInt("orderId",0);
		if(orderId==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return;
		}
		OrderModel order = OrderModel.dao.findById(orderId);
		if(order==null){
			setAttr("error", "-1");
			setAttr("message", "当前订单不存在");
			renderJson();
			return;
		}
		if(order.getInt("tuiStatus")!=0||order.getInt("status")!=3){
			setAttr("error", "-1");
			setAttr("message", "订单不可删除");
			renderJson();
			return;
		}
		order.set("isClosed", 1).update();
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
		/**
		 * @category 富币商城生成订单
		 * @Description   
		 * @author hmilysean
		 * @date 2015年12月27日 下午1:39:44
		 */
		public void FubiOrder(){
			
			int uid = ShiroUtils.getUserId();
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请重新登录");
				renderJson();
				return;
			}
			String p =getPara("price");
			int price = Integer.valueOf(p);
			Integer address = getParaToInt("address",-1);
			Integer goodsDetailId=getParaToInt("goodsDetailId",-1);
			
			
			if(address==-1){
				setAttr("error", -1);
				setAttr("message", "参数错误");
				renderJson();
				return;
			}
			
			int remainfubi = UserCountModel.dao.getRemainFubi(uid);
			
			if(remainfubi==-1||remainfubi<price){
				setAttr("error", -1);
				setAttr("message", "你的富币金额不够哟");
				renderJson();
				return;
			}
			OrderModel order=new OrderModel()
					.set("price", price)
					.set("goodsDetailId", goodsDetailId)
					.set("dateline", new Date())
					.set("uid", uid)
					.set("address", address)
					.set("status", 1)// 待发货
					.set("pay", 2)  //富币支付方式
					.set("isFubi", 1)
					.set("count", 1)
					.set("payTime", new Date());
			order.save();
			
			
			//扣除用户 富币
			UserCountModel.dao.update(uid,price);
			setAttr("error", 0);
			setAttr("message", "生成订单成功");
			renderJson();
		}
		
		/**
		 * @category  支付宝异步访问服务器地址
		 * @author inging44
		 * @date 2016年1月5日 下午4:53:29 
		 * @throws UnsupportedEncodingException
		 */
		public void notify_url() throws UnsupportedEncodingException{
			String returnStr="fail";
			
			Map<String,String> params = new HashMap<String,String>();
			Map<String, String[]> requestParams = getParaMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			
			//交易状态
			String trade_status = new String(getPara("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			//收款账号
			String alipay = new String(getPara("seller_id").getBytes("ISO-8859-1"),"UTF-8");
			//订单号
			String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//订单总额
			String total_fee = new String(getPara("total_fee").getBytes("ISO-8859-1"),"UTF-8");
			//当前订单
			OrderPackageModel orderPackage = OrderPackageModel.dao.findById(out_trade_no);
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if(AlipayNotify.verify(params)&&orderPackage!=null&&orderPackage.getFloat("totalPrice").equals(total_fee)
					&&alipay.equals(AlipayConfig.seller_alipay)){//验证成功
					
					if (trade_status.equals("TRADE_SUCCESS")){//付款完成后，支付宝系统发送该交易状态通知
						List<OrderModel> list = OrderModel.dao.getByPackageId(Integer.parseInt(out_trade_no));
						for(OrderModel order:list){
							order.set("status", 1).set("payTime", mCurrentDateTime).update();
						}
						System.out.println("success");	//请不要修改或删除
						returnStr = "success";
					}
		
			}else{//验证失败
				returnStr = "fail";
			}
			renderText(returnStr);
		}
}
