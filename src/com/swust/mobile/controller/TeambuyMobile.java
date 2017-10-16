package com.swust.mobile.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Page;
import com.swust.model.AddressModel;
import com.swust.model.GoodsCategoryModel;
import com.swust.model.GoodsModel;
import com.swust.model.GoodsPicModel;
import com.swust.model.KeytValGoodsModel;
import com.swust.model.OrderPackageModel;
import com.swust.model.TeamBuyModel;
import com.swust.model.TeamBuyUserModel;

public class TeambuyMobile extends CommonController{
	public void index(){
		renderJson();
	}
	
	/**
	 * @category 获取所有一级分类 
	 * @author inging44
	 * @date 2016年1月14日 上午10:51:10
	 */
	public void getAllCat(){
		setAttr("list",GoodsCategoryModel.dao.getFirst());
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	/**
	 * @category 团购列表 
	 * @author inging44
	 * @date 2016年1月14日 下午2:48:47
	 */
	public void getByCat(){
		int catId = getParaToInt("catId",1);
		setAttr("page",TeamBuyModel.dao.getByCat(getParaToInt("pageSize",20),getParaToInt("pageNumber",1),catId));
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	/**
	 * @category 团购详情 
	 * @author inging44
	 * @date 2016年1月14日 下午5:00:34
	 */
	public void view(){
		int tid = getParaToInt("tid",0);
		if(tid==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		TeamBuyModel teambuy = TeamBuyModel.dao.fingById(tid);
		if(teambuy==null){
			setAttr("error","-1");
			setAttr("message","团购不存在或已失效");
			renderJson();
			return;
		}
		int goodsId = teambuy.getInt("goodsId");
		setAttr("teambuy",teambuy);
		setAttr("pictures",GoodsPicModel.dao.findByGoodsId(goodsId));
		setAttr("goods",GoodsModel.dao.findDetailById(goodsId));
		setAttr("list",KeytValGoodsModel.dao.findValuesByGoodsId(goodsId));
		setAttr("nonkeyt",GoodsModel.dao.getGoodsNonkeytParams(goodsId));
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	public void select(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","0");
			setAttr("message","未登录");
			renderJson();
			return;
		}
		int tid = getParaToInt("tid",0);
		if(tid==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		TeamBuyModel teambuy = TeamBuyModel.dao.fingById(tid);
		if(teambuy==null){
			setAttr("error","-1");
			setAttr("message","团购不存在或已失效");
			renderJson();
			return;
		}
		int goodsId = teambuy.getInt("goodsId");
		setAttr("teambuy",teambuy);
		setAttr("list",KeytValGoodsModel.dao.findValuesByGoodsId(goodsId));
		setAttr("defaultAddr",AddressModel.dao.getDefaultByUid(uid));
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	
	/**
	 * @category 参团
	 */
	public void join(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("message","未登录");
			renderJson();
			return;
		}
		int tid = getParaToInt("tid",0);
		int goodsDetailId = getParaToInt("goodsDetailId",0);
		int aid = getParaToInt("addrId",0);
		if(tid==0||goodsDetailId==0||aid==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		KeytValGoodsModel goodsDetail = KeytValGoodsModel.dao.findById(goodsDetailId);
		TeamBuyModel teambuy = TeamBuyModel.dao.fingById(tid);
		if(teambuy==null||goodsDetail==null){
			setAttr("error","-1");
			setAttr("message","团购不存在或已失效");
			renderJson();
			return;
		}
		if(teambuy.getLong("remain")<=0){
			setAttr("error","-1");
			setAttr("message","团购人数已满");
			renderJson();
			return;
		}
		if(TeamBuyUserModel.dao.findByUidAndTid(uid,tid)!=null){
			setAttr("error","-1");
			setAttr("message","重复操作");
			renderJson();
			return;
		}
		TeamBuyUserModel order = new TeamBuyUserModel()
				.set("uid", uid).set("tid", tid)
				.set("nickname", ShiroUtils.getNickName())
				.set("address", aid)
				.set("price", teambuy.get("teambuyPrice"))
				.set("goodsDetailId", goodsDetailId)
				.set("createTime", mCurrentDateTime);
		order.save();
		setAttr("order",order);
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	public void edit(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("message","未登录");
			renderJson();
			return;
		}
		int orderId = getParaToInt("orderId",0);
		int pay = getParaToInt("pay",0);
		if(orderId==0||pay==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		TeamBuyUserModel order = TeamBuyUserModel.dao.findById(orderId);
		if(order==null){
			setAttr("error","-1");
			setAttr("message","团购订单不存在");
			renderJson();
			return;
		}
		
		TeamBuyModel teambuy = TeamBuyModel.dao.fingById(order.getInt("tid"));
		if(teambuy==null){
			setAttr("error","-1");
			setAttr("message","团购不存在或已失效");
			renderJson();
			return;
		}
		if(teambuy.getInt("remain")<=0){
			setAttr("error","-1");
			setAttr("message","团购人数已满");
			renderJson();
			return;
		}
		if(order.getInt("uid")!=uid){
			setAttr("error","-1");
			setAttr("message","他人订单");
			renderJson();
			return;
		}
		order.set("pay", pay).update();
		setAttr("order",order);
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	/**
	 * @category 支付宝异步请求操作 
	 * @author inging44
	 * @date 2016年1月14日 下午6:37:48 
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
					TeamBuyUserModel order = TeamBuyUserModel.dao.findById(Integer.parseInt(out_trade_no));
					if(order!=null){
						order.set("status", 1).set("payTime", mCurrentDateTime).update();
						TeamBuyModel.dao.add(order.getInt("tid"));
						System.out.println("success");	//请不要修改或删除
						returnStr = "success";
					}
				}
		}else{//验证失败
			returnStr = "fail";
		}
		renderText(returnStr);
	}
	
	/**
	 * @category  我的团购 
	 * @author inging44
	 * @date 2016年1月14日 下午6:41:14
	 */
	public void mine(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("message","未登录");
			renderJson();
			return;
		}
		Page<TeamBuyUserModel> page = TeamBuyUserModel.dao.getByUid(getParaToInt("pageSize",20),getParaToInt("pageNumber",1),uid);
		for(TeamBuyUserModel model:page.getList()){
			if(model!=null){
				model.put("attr", KeytValGoodsModel.dao.findValuesByGdId(model.getInt("goodsDetailId")));
			}
		}
		setAttr("page",page);
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
}
