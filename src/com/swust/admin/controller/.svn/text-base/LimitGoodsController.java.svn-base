package com.swust.admin.controller;

import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.GoodsModel;
import com.swust.model.GoodsPicModel;
import com.swust.model.LimitGoodsModel;
import com.swust.model.LimitTimeModel;


/**
 * @category 限时特惠商品
 * @Description 
 * @author hmilysean 
 * @date 2015年12月23日 上午11:15:55 
 * @version V0.1
 */
public class LimitGoodsController extends CommonController{
	
	public void index(){
		
		Page<LimitGoodsModel> page=LimitGoodsModel.dao.
				newpaginate(getParaToInt("pageCurrent", 1), getParaToInt("pageSize", Preference.PAGE_PER_SIZE), getPara("keyword",""));
		setAttr("keyword", getPara("keyword"));
		setAttr("page", page);
		render("goodslist.html");
	}
	/**
	 * @category 添加限时特惠商品
	 * @Description  用户在后台只显示自己发布的商品
	 * @author hmilysean
	 * @date 2015年12月23日 下午7:54:38
	 */
	public void add(){
		Integer uid = ShiroUtils.getUserId();
		//获取设定的限时时间段
		Page<LimitTimeModel> time=LimitTimeModel.dao.newsPaginate(1, 100);
		//获取该用户的所有有效商品
		List<GoodsModel> goods=GoodsModel.dao.getSelfGoods(uid);
		setAttr("time", time);
		setAttr("goods", goods);
		render("goodsadd.html");
	}
	
	/**
	 * @category 查看特惠商品详情
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月23日 下午3:27:25
	 */
	public void view(){
		Integer lid = getParaToInt(0,-1);  // 获取时间段
		Integer gid = getParaToInt(1,-1);  // 获取参加活动的商品ID
		LimitGoodsModel goods=LimitGoodsModel.dao.ViewlimitGoods(lid, gid);
		//商品图片
		List<GoodsPicModel> pics = GoodsPicModel.dao.findByGoodsId(gid);
		setAttr("pictures", pics);
		setAttr("goods", goods);
		render("goodsview.html");
	}
	
	
	 /**
	  * @category 限时特惠商品 修改
	  * @Description  目前个人觉得只能修改参加数量，和价格
	  * @author hmilysean
	  * @date 2015年12月23日 下午3:51:58
	  */
	public void edit(){
		
		Integer lid = getParaToInt(0,-1);  // 获取时间段
		Integer gid = getParaToInt(1,-1);  // 获取参加活动的商品ID
		LimitGoodsModel goods=LimitGoodsModel.dao.editLimitGoods(lid, gid);
		setAttr("goods", goods);
		render("goodsedit.html");
		
	}
	
	/**
	 * 
	 * @Description 删除特惠商品
	 * @author hmilysean
	 * @date 2015年12月23日 下午1:57:43
	 */
	public void delete(){
		Integer gid = getParaToInt(1,-1);
		Integer lid = getParaToInt(0,-1);  // 获取时间段
		if(gid==-1||lid==-1){
			render(BjuiRender.error("选择商品"));
			return;	
		} 
		String sql="delete from fish_mall_limited_goods where limitedId=? and goodsId=?";
		try{
			Db.update(sql,lid,gid);
			}
			catch(Exception e){
				e.printStackTrace();
				render(BjuiRender.error("出错啦！！"));
				return;
			}
//		Db.deleteById("fish_mall_limited_goods", "limitedId", lgid);
		render(BjuiRender.refresh("limitgoods"));
	}
	
	/**
	 * @category 保存参加活动的产品，此处代码需优化
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月23日 下午3:07:56
	 */
	public void save(){
		Integer goodsId = getParaToInt("limitGoods",-1);
		Integer totalcount = getParaToInt("totalcount",-1);
		Integer limitprice = getParaToInt("limitedprice",-1);
		Integer limitid = getParaToInt("limitTime",-1);
		
		if(goodsId==-1||totalcount==-1||limitprice==-1||limitid==-1){
			render(BjuiRender.error("出错啦"));
			return ;
		}
		LimitGoodsModel lgoods=new LimitGoodsModel()
				.set("limitedId", limitid)
				.set("goodsId", goodsId)
				.set("totalCount", totalcount)
				.set("limitedPrice", limitprice)
				.set("remain", totalcount);
			//此处代码需要优化
			try{
				lgoods.save();
			}catch(Exception e){
				e.printStackTrace();
				render(BjuiRender.error("此商品已经参加了！！"));
				return ;
			}
			render(BjuiRender.closeCurrentAndRefresh("limitgoods"));
	}
	
	public void update(){
		
		Integer goodsId = getParaToInt("limitGoods",-1);
		Integer totalcount = getParaToInt("totalcount",-1);
		Integer limitprice = getParaToInt("limitedprice",-1);
		Integer limitid = getParaToInt("limitTime",-1);
		
		if(goodsId==-1||totalcount==-1||limitprice==-1||limitid==-1){
			render(BjuiRender.error("出错啦"));
			return ;
		}
//		LimitGoodsModel lgoods=new LimitGoodsModel()
//				.set("limitedId", limitid)
//				.set("goodsId", goodsId)
//				.set("totalCount", totalcount)
//				.set("limitedPrice", limitprice)
//				.set("remain", totalcount)
//				;
			//此处代码需要优化
		String sql="update fish_mall_limited_goods set totalCount=? , limitedPrice=? , remain=? where limitedId=? and goodsId=?";
		try{
		Db.update(sql,totalcount,limitprice,totalcount,limitid,goodsId);
		}
		catch(Exception e){
			e.printStackTrace();
			render(BjuiRender.error("出错啦"));
			return ;
		}
		render(BjuiRender.closeCurrentAndRefresh("limitgoods"));
	}
	
}
