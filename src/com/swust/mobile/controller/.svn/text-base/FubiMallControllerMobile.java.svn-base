package com.swust.mobile.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.swust.model.FubiBannerModel;
import com.swust.model.GoodsModel;
import com.swust.model.GoodsPicModel;
import com.swust.model.KeytValGoodsModel;
import com.swust.model.LimitGoodsModel;
import com.swust.model.LimitTimeModel;
import com.swust.model.SeckillGoodsModel;


/**
 * @category 富币商城手机接口
 * @Description 
 * @author hmilysean 
 * @date 2015年12月30日 下午3:15:22 
 * @version V0.1
 */
public class FubiMallControllerMobile extends CommonController{

	/**
	 * 
	 * @Description 主要获取秒杀，特惠的时间，商品的获取在其它接口
	 * @author hmilysean
	 * @date 2016年1月13日 上午10:04:59
	 */
		public void index(){
			
			//限时特惠时间列表
			List<LimitTimeModel> limitedtime=LimitTimeModel.dao.getLimitTime();
			setAttr("limitedtime", limitedtime);
			
			//整点秒杀时间列表
			String sql="select * from fish_mall_seckill";
			List<Record> seckilltime = Db.find(sql);
			setAttr("seckilltime", seckilltime);
			setAttr("error", 0);
			setAttr("message", "成功");
			renderJson();
		
		}
		/**
		 * @category 获取限时特惠商品
		 * @Description 
		 * @author hmilysean
		 * @date 2016年1月13日 上午10:06:26
		 */
		public void getLimitedGoods(){
			Page<LimitGoodsModel> limitgoods=LimitGoodsModel.dao.Mobilepaginate(getParaToInt("pageNum", 1), getParaToInt("pageSize", 2), getParaToInt("limitedId", 1));		
			setAttr("limitgoods", limitgoods);
			setAttr("error", 0);
			setAttr("message", "成功");
			renderJson();
		}
		
		/**
		 * @category 获取秒杀商品
		 * @Description 
		 * @author hmilysean
		 * @date 2016年1月13日 上午10:09:07
		 */
		public void getSeckillGoods(){
			
			Page<SeckillGoodsModel> seckillgoods = SeckillGoodsModel.dao.Mobilepaginate(getParaToInt("pageNum", 1), getParaToInt("pageSize", 2), getParaToInt("seckillId", 1));
			setAttr("seckillgoods", seckillgoods);
			setAttr("error", 0);
			setAttr("message", "成功");
			renderJson();
		}
		
		
		/**  看起来还没写完的样子
		 * @category 购买限时特惠商品
		 * @Description 这里只是购买动作，并不是生成订单
		 * @author hmilysean
		 * @throws ParseException 
		 * @date 2015年12月24日 下午2:43:27
		 */
		public void buyLimited() throws ParseException{
			Integer limitedId = getParaToInt("limitedId",-1);
			Integer goodsId = getParaToInt("goodsId",-1);
			int uid = ShiroUtils.getUserId();
			//判断当前时间是否在特惠事假
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请登录后再抢购");
				renderJson();
				return ;
			}
			if(limitedId==-1||goodsId==-1){
				setAttr("error", -1);
				setAttr("message", "请选择商品");
				renderJson();
				return ;
			}
			//判断用户提交的时间是否在限时特惠时间内
			//时间处理
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(new Date());
			long now = sdf.parse(time).getTime();
			LimitTimeModel limitedTime = LimitTimeModel.dao.findById(limitedId);
			long startTime = limitedTime.getTime("startTime").getTime();
			long endTime=limitedTime.getTime("endTime").getTime();
			
			if(now>=startTime&&now<=endTime){
				LimitGoodsModel goods = LimitGoodsModel.dao.ViewlimitGoods(limitedId, goodsId);
				Long limitedRemain = goods.getLong("remain");
				//如果限时抢购商品剩余量为0
				if(limitedRemain<=0){
					setAttr("error", -1);
					setAttr("message", "哎呀就差一点啦");
					renderJson();
					return ;
				}
				LimitGoodsModel.dao.updateRemain(limitedId, goodsId, 0);//减
				List<KeytValGoodsModel> para = KeytValGoodsModel.dao.findValuesByGoodsId(goodsId);

				setAttr("para", para);
				setAttr("money", goods.get("limitedPrice"));
				setAttr("error", 0);
				setAttr("message", "恭喜您抢购成功");
				renderJson();
				return ;
			}
			setAttr("error", -1);
			setAttr("message", "现在不是限时特惠时间哟");
			renderJson();
		}
		
		/**
		 * @category 购买秒杀商品
		 * @Description 这里只是购买动作，并不是生成订单
		 * @author hmilysean
		 * @throws ParseException 
		 * @date 2015年12月24日 下午2:43:06
		 */
		public void buySeckill() throws ParseException{
			Integer uid = ShiroUtils.getUserId();
			Integer seckillId = getParaToInt("seckillId",-1);
			Integer goodsId = getParaToInt("goodsId",-1);
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请登录后再抢购");
				renderJson();
				return ;
			}
			if(seckillId==-1||goodsId==-1){
				setAttr("error", -1);
				setAttr("message", "请选择商品再抢购");
				renderJson();
				return ;
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(new Date());
			long now = sdf.parse(time).getTime();
			String sql="select * from fish_mall_seckill where id="+seckillId;
			long seckillTime = Db.findFirst(sql).getDate("time").getTime();
			if(now>seckillTime){
				SeckillGoodsModel goods = SeckillGoodsModel.dao.ViewSeckillGoods(seckillId, goodsId);
				long remain=goods.getLong("sremain");
				if(remain<=0){
					setAttr("error", -1);
					setAttr("message", "哎呀就差一点啦");
					renderJson();
					return ;
				}
				//符合秒杀条件
				String sql2="update fish_mall_seckill_goods set remain=remain-1 where seckillId=? and goodsId=?";
				Db.update(sql2,seckillId, goodsId);
				
				List<KeytValGoodsModel> para = KeytValGoodsModel.dao.findValuesByGoodsId(goodsId);

				setAttr("para", para);
				setAttr("money", goods.get("seckillPrice"));
				setAttr("error", 0);
				setAttr("message", "恭喜您秒杀成功");
				renderJson();
				return ;
				
			}
			else{
				setAttr("error", -1);
				setAttr("message", "还没有到秒杀时间哟");
				renderJson();
			}
			
			
		}
		
		/**
		 * @category 查看限时特惠商品详情
		 * @Description  限时特惠商品详情
		 * @author hmilysean
		 * @date 2015年12月24日 下午2:42:41
		 */
		public void viewLimited(){
			Integer limitedId = getParaToInt("limitedId",-1);
			Integer goodsId = getParaToInt("goodsId",-1);
			
			if(limitedId==-1||goodsId==-1){
				setAttr("error", -1);
				setAttr("message", "请选择商品");
				renderJson();
				return ;
			}
			//主要是获取图文信息
			LimitGoodsModel goods = LimitGoodsModel.dao.ViewlimitGoods(limitedId, goodsId);
			if(goods==null){
				setAttr("error", -1);
				setAttr("message", "商品参数错误");
				renderJson();
				return ;
			}
			//商品关键参数
			setAttr("list", KeytValGoodsModel.dao.findValuesByGoodsId(goodsId));
			//商品非关键参数
			Map<String, String> unkey = GoodsModel.dao.getGoodsNonkeytParams(goodsId);
			
			//商品图片
			List<GoodsPicModel> pic = GoodsPicModel.dao.findByGoodsId(goodsId);
			setAttr("pictures", pic);
			setAttr("nonkeyt", unkey);
			setAttr("goods", goods);
			setAttr("error", 0);
			setAttr("message", "操作成功");
			renderJson();
		}
		
		/**
		 * @category 查看秒杀商品详情
		 * @Description 查看秒杀商品详情 
		 * @author hmilysean
		 * @date 2015年12月24日 下午2:42:04
		 */
		public void viewSeckill(){
			Integer seckillId = getParaToInt("seckillId",-1);
			Integer goodsId = getParaToInt("goodsId",-1);
			if(seckillId==-1||goodsId==-1){
				setAttr("error", -1);
				setAttr("message", "请选择商品再抢购");
				renderJson();
				return ;
			}
			SeckillGoodsModel goods = SeckillGoodsModel.dao.ViewSeckillGoods(seckillId, goodsId);
			if(goods==null){
				setAttr("error", -1);
				setAttr("message", "商品参数错误");
				renderJson();
				return ;
			}
			
			//商品关键参数
			setAttr("list", KeytValGoodsModel.dao.findValuesByGoodsId(goodsId));
			//商品非关键参数
			Map<String, String> unkey = GoodsModel.dao.getGoodsNonkeytParams(goodsId);
			
			
			//商品图片
			List<GoodsPicModel> pic = GoodsPicModel.dao.findByGoodsId(goodsId);
			System.out.println(pic);
			setAttr("pictures", pic);
			setAttr("nonkeyt", unkey);
			setAttr("goods", goods);
			setAttr("error", 0);
			setAttr("message", "操作成功");
			renderJson();
		}
		
		/**
		 * @category 获取banner广告，和团购公用
		 * @Description 
		 * @author hmilysean
		 * @date 2016年1月15日 上午11:14:32
		 */
		
		public void getBanner(){
			//富币商城Banner
			List<FubiBannerModel> banner=FubiBannerModel.dao.getBannerList();
			setAttr("banner", banner);
			setAttr("error", 0);
			setAttr("message", "成功");
			renderJson();
		}
}
