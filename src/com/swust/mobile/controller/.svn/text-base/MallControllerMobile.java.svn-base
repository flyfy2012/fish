package com.swust.mobile.controller;

import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.swust.model.GoodsCategoryModel;
import com.swust.model.GoodsCountMonthModel;
import com.swust.model.GoodsModel;
import com.swust.model.GoodsPicModel;
import com.swust.model.KeytValGoodsModel;
import com.swust.model.ShoppingCartModel;

public class MallControllerMobile extends CommonController {
	/**
	 * @category  商城-商品列表页
	 * @author inging44
	 * @date 2015年12月23日 下午8:25:40
	 */
	public void index() {
		List<GoodsCategoryModel> firstC = GoodsCategoryModel.dao.getFirst();
		
		for(GoodsCategoryModel n: firstC){
			n.put("child",GoodsCategoryModel.dao.getNext(n.getInt("id")));
		}
		setAttr("catList", firstC);
		setAttr("error", 0);
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category  商品详情
	 * @author inging44
	 * @date 2016年1月4日 上午11:12:07
	 */
	public void view() {
		Integer goodsId = getParaToInt("goodsId",-1);
		if(goodsId==-1){
			setAttr("error", "-1");
			setAttr("message", "该商品不存在！！ ");
			renderJson();
			return;
		}
		GoodsModel goods = GoodsModel.dao.findDetailById(goodsId);
		goods.put("monthSelled", GoodsCountMonthModel.dao.getMonthSelled(goodsId));
		setAttr("error", "0");
		setAttr("message", "操作成功");
		setAttr("pictures",GoodsPicModel.dao.findByGoodsId(goodsId));
		setAttr("goods",goods);
		setAttr("list",KeytValGoodsModel.dao.findValuesByGoodsId(goodsId));
		setAttr("nonkeyt",GoodsModel.dao.getGoodsNonkeytParams(goodsId));
		renderJson();
	}
	
	/**
	 * @category  获取分类商品列表
	 * @author inging44
	 * @date 2016年1月4日 上午11:11:49
	 */
	public void getByCat(){
		int catId = getParaToInt("catId",-1);
		int selled = getParaToInt("selled",-1);
		int priceOrder = getParaToInt("priceOrder",-1);
		String title = getPara("title","").trim();
		if(catId==-1){
			setAttr("error","-1");
			setAttr("message","请提交类别id");
			renderJson();
			return;
		}
		Page<GoodsModel> page = GoodsModel.dao.paginateByCatId(getParaToInt("pageNumber",1), getParaToInt("pageSize",20), catId,selled,priceOrder,title);
		for(GoodsModel goods:page.getList()){
			goods.put("monthSelled", GoodsCountMonthModel.dao.getMonthSelled( goods.getInt("id")));
		}
		setAttr("page",page);
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	/**
	 * @category  添加到购物车
	 * @author inging44
	 * @date 2016年1月4日 上午11:11:18
	 */
	@Before(Tx.class)
	public void addToCart(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("error","未登录");
			renderJson();
			return;
		}
		int goodsDetailId = getParaToInt("goodsDetailId",0);
		if(goodsDetailId==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		KeytValGoodsModel goodsDetail = KeytValGoodsModel.dao.findById(goodsDetailId);
		if(goodsDetail==null){
			setAttr("error","-1");
			setAttr("message","商品不存在");
			renderJson();
			return;
		}
		int count = getParaToInt("count",1);
		ShoppingCartModel cast = ShoppingCartModel.dao.findByUidAndGdid(uid,goodsDetailId);
		if(cast!=null){
			Db.update("update fish_shopping_cart set count=? where uid=? and goodsDetailId=?",count,uid,goodsDetailId);
			setAttr("error","0");
			setAttr("message","操作成功");
			renderJson();
			return;
		}
		
		GoodsModel theGoods = GoodsModel.dao.findDetailById(goodsDetail.getInt("goodsId"));
		new ShoppingCartModel().set("uid", uid).set("count", count)
			.set("goodsDetailId", goodsDetailId)
			.set("goodsTitle", theGoods.get("title"))
			.set("goodsCover", theGoods.get("cover"))
			.set("goodsPrice", goodsDetail.get("price"))
			.set("goodsPostage", theGoods.get("postagePrice"))
			.set("shopId", theGoods.get("shopId"))
			.set("shopAvatar", theGoods.get("shopAvatar"))
			.set("shopName", theGoods.get("shopName"))
			.set("datetime", mCurrentDateTime).save();
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	/**
	 * @category 用户购物车商品列表 
	 * @author inging44
	 * @date 2016年1月4日 上午11:12:24
	 */
	public void goodsCartList(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("message","未登录");
			renderJson();
			return;
		}
		int goodsId = getParaToInt("goodsId",0);
		if(goodsId==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		if(GoodsModel.dao.findById(goodsId)==null){
			setAttr("error","-1");
			setAttr("message","商品不存在");
			renderJson();
			return;
		}
		setAttr("list",ShoppingCartModel.dao.findByUidAndGid(uid, goodsId));
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	/**
	 * @category  从购物车删除
	 * @author inging44
	 * @date 2016年1月4日 上午11:11:35
	 */
	public void delCartGoods(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("message","未登录");
			renderJson();
			return;
		}
		int goodsDetailId = getParaToInt("goodsDetailId",0);
		if(goodsDetailId==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		ShoppingCartModel.dao.delByUidAndGdid(uid,goodsDetailId);
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
	/**
	 * @category  我的购物车
	 * @author inging44
	 * @date 2016年1月19日 上午11:11:35
	 */
	public void myShoppingCart(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error","-1");
			setAttr("message","未登录");
			renderJson();
			return;
		}
		setAttr("list",ShoppingCartModel.dao.getByUid(uid));
		setAttr("error","0");
		setAttr("message","操作成功");
		renderJson();
	}
	
}
