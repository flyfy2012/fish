package com.swust.admin.controller;

import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.GoodsModel;
import com.swust.model.GoodsPicModel;
import com.swust.model.SeckillGoodsModel;

/**
 * @category 整点秒杀模块
 * @Description 
 * @author hmilysean 
 * @date 2015年12月23日 下午4:31:26 
 * @version V0.1
 */
public class SeckillGoodsController extends CommonController {
	
	 public void index(){
		 Page<SeckillGoodsModel> goods=SeckillGoodsModel.dao
				 .newpaginate(getParaToInt("pageCurrent", 1), getParaToInt("pageSize", 15),
				getPara("keyword",""));
		 setAttr("keyword", getPara("keyword"));
		 setAttr("page", goods);
		 render("goodslist.html");
	 }
	 
	 /**
	  * @category 添加整点秒杀商品
	  * @Description   用户在后台只能获取自己已发布，并且审核通过的商品
	  * @author hmilysean
	  * @date 2015年12月23日 下午7:59:05
	  */
	 public void add(){
		 Integer uid = ShiroUtils.getUserId();
		//获取整点秒杀时间
		 String sql="select * from fish_mall_seckill";
		 List<Record> times = Db.find(sql);
		 //获取商品列表
		 List<GoodsModel> goods=GoodsModel.dao.getSelfGoods(uid);
		 
		 setAttr("time", times);
		 setAttr("goods", goods);
		 render("goodsadd.html");
		 
	 }
	 
	 /**
	  * @category 保存整点秒杀商品
	  * @Description  代码可能要优化
	  * @author hmilysean
	  * @date 2015年12月23日 下午8:07:42
	  */
	 public void save(){
		    Integer goodsId = getParaToInt("seckillGoods",-1);
			Integer totalcount = getParaToInt("totalcount",-1);
			Integer seckillprice = getParaToInt("seckillprice",-1);
			Integer seckillid = getParaToInt("seckillTime",-1);
			
			if(goodsId==-1||totalcount==-1||seckillprice==-1||seckillid==-1){
				render(BjuiRender.error("出错啦"));
				return ;
			}
			SeckillGoodsModel sgoods=new SeckillGoodsModel()
					.set("seckillId", seckillid)
					.set("goodsId", goodsId)
					.set("totalCount", totalcount)
					.set("seckillPrice", seckillprice)
					.set("remain", totalcount);
				//此处代码需要优化
				try{
					sgoods.save();
				}catch(Exception e){
					e.printStackTrace();
					render(BjuiRender.error("此商品已经参加了！！"));
					return ;
				}
				render(BjuiRender.closeCurrentAndRefresh("seckillgoods"));
		 
	 }
	 /**
	  * @category 查看整点秒杀详情
	  * @Description 
	  * @author hmilysean
	  * @date 2015年12月23日 下午8:20:58
	  */
	 public void view(){
		 
		 	Integer sid = getParaToInt(0,-1);  // 获取时间段
			Integer gid = getParaToInt(1,-1);  // 获取参加活动的商品ID
			SeckillGoodsModel goods=SeckillGoodsModel.dao.ViewSeckillGoods(sid, gid);
			List<GoodsPicModel> pics = GoodsPicModel.dao.findByGoodsId(gid);
			setAttr("pictures", pics);
			setAttr("goods", goods);
			render("goodsview.html");
		 
	 }
	 
	 
	 public void edit(){
		 
		    Integer sid = getParaToInt(0,-1);  // 获取时间段
			Integer gid = getParaToInt(1,-1);  // 获取参加活动的商品ID
			SeckillGoodsModel goods=SeckillGoodsModel.dao.editLimitGoods(sid, gid);
			setAttr("goods", goods);
			render("goodsedit.html");
	 }
		
	 
	 public void update(){
		    Integer goodsId = getParaToInt("seckillGoods",-1);
			Integer totalcount = getParaToInt("totalcount",-1);
			Integer seckillprice = getParaToInt("seckillprice",-1);
			Integer seckillid = getParaToInt("seckilltime",-1);
			
			if(goodsId==-1||totalcount==-1||seckillprice==-1||seckillid==-1){
				render(BjuiRender.error("出错啦！—-"));
				return ;
			}

				//此处代码需要优化
			String sql="update fish_mall_seckill_goods set totalCount=? , seckillPrice=? , remain=? where seckillId=? and goodsId=?";
			try{
			Db.update(sql,totalcount,seckillprice,totalcount,seckillid,goodsId);
			}
			catch(Exception e){
				e.printStackTrace();
				render(BjuiRender.error("出错啦！！！"));
				return ;
			}
			render(BjuiRender.closeCurrentAndRefresh("seckillgoods"));
		 
	 }
	 
	 /**
	  * 
	  * @Description 删除整点秒杀商品  
	  * @author hmilysean
	  * @date 2015年12月23日 下午8:33:01
	  */
	 public void delete(){
		 Integer sid = getParaToInt(0,-1);
		 Integer gid = getParaToInt(1,-1);
			if(sid==-1||gid==-1){
				render(BjuiRender.error("选择商品"));
				return;	
			} 
			
			String sql="delete from fish_mall_seckill_goods where seckillId=? and goodsId=?";
			try{
			Db.update(sql,sid,gid);
			}
			catch(Exception e){
				e.printStackTrace();
				render(BjuiRender.error("出错啦！！"));
				return;
			}
			render(BjuiRender.refresh("limitgoods"));
	 }
	 
}
