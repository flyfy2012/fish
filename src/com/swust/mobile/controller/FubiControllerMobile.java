package com.swust.mobile.controller;

import java.util.Date;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.swust.model.FubiModel;
import com.swust.model.FubidetailModel;
import com.swust.model.NewsCommentModel;
import com.swust.model.NewsUpDownModel;
import com.swust.model.UserCountModel;

/**
 * @category 用户获取富币管理+ -
 * @Description 
 * @author hmilysean 
 * @date 2015年12月26日 上午11:16:12 
 * @version V0.1
 */
public class FubiControllerMobile extends CommonController{
	
		/**
		 * @category 签到获取富币
		 * @Description 
		 * @author hmilysean
		 * @date 2015年12月26日 上午11:15:55
		 * 
		 */
	public static FubiControllerMobile getFubi=new FubiControllerMobile();
	
		public void DosignIn(){
		
			Integer uid = ShiroUtils.getUserId();
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请登录后再试");
				renderJson();
				return ;
			}
			int type=1;
			boolean isSign = FubidetailModel.dao.isSign(uid);
			if(isSign){
				setAttr("error", -1);
				setAttr("message", "今天已经签到了");
				renderJson();
				return ;
			}
			int fubi=FubiModel.dao.getPoint(type);
			FubidetailModel detail=new FubidetailModel()
					.set("uid", uid)
					.set("type", type)
					.set("fubi", fubi)
					.set("dateline", new Date());
			detail.save();
			UserCountModel count = UserCountModel.dao.findById(uid);
			count.set("totalfubi", count.getInt("totalfubi")+fubi)
			.set("remainfubi", count.getInt("remainfubi")+fubi);
			count.update();
			setAttr("error", 0);
			setAttr("message", "签到成功");
			renderJson();
		}
		
		/**
		 * @category 购买商品获取富币
		 * @Description 
		 * @author hmilysean
		 * @date 2015年12月26日 上午11:17:24
		 */
		public void Dobuy(int fubi){
			Integer uid = ShiroUtils.getUserId();
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请登录后再试");
				renderJson();
				return ;
			}
			int type=2;
			//int fubi=FubiModel.dao.getPoint(type);
			FubidetailModel detail=new FubidetailModel()
					.set("uid", uid)
					.set("type", type)
					.set("fubi", fubi)
					.set("dateline", new Date());
			UserCountModel count = UserCountModel.dao.findById(uid);
			count.set("totalfubi", count.getInt("totalfubi")+fubi)
			.set("remainfubi", count.getInt("remainfubi")+fubi);
			count.update();
			detail.save();
			
		}
		
//		/**
//		 * @category 发表资讯文章获取富币
//		 * @Description 
//		 * @author hmilysean
//		 * @date 2015年12月26日 上午11:18:48
//		 */
//		public void Dopublish(){
//			Integer uid = ShiroUtils.getUserId();
//			int type=3;
//			int fubi=FubiModel.dao.getPoint(type);
//			FubidetailModel detail=new FubidetailModel()
//					.set("uid", uid)
//					.set("type", type)
//					.set("fubi", fubi)
//					.set("dateline", new Date());
//			detail.save();
//			setAttr("error", 0);
//			setAttr("message", "发布成功获得富币");
//			renderJson();
//		}
//		
		
		/**
		 * @category 资讯评论获取富币   本段代码嵌入至NewsCommentController.save
		 * @Description 
		 * @author hmilysean
		 * @date 2015年12月26日 上午11:19:56
		 */
		public void Docomment(int newsid){
			Integer uid = ShiroUtils.getUserId();
			
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请登录后再试");
				renderJson();
				return ;
			}
			boolean commented = NewsCommentModel.dao.iscommented(uid, newsid);
			
			if(commented){
				return ;
			}
			int type=3;
			int fubi=FubiModel.dao.getPoint(type);
			FubidetailModel detail=new FubidetailModel()
					.set("uid", uid)
					.set("type", type)
					.set("fubi", fubi)
					.set("dateline", new Date());
			detail.save();
			UserCountModel count = UserCountModel.dao.findById(uid);
			count.set("totalfubi", count.getInt("totalfubi")+fubi)
			.set("remainfubi", count.getInt("remainfubi")+fubi);
			count.update();
		}
		
		/**
		 * @category 点赞获取富币
		 * @Description 这个代码已写入NewsMobile.up
		 * @author hmilysean
		 * @date 2015年12月26日 上午11:20:26
		 */
		public void Dolike(int newsid){
			
			Integer uid = ShiroUtils.getUserId();
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请登录后再试");
				renderJson();
				return ;
			}
			int type=4;
			int fubi=FubiModel.dao.getPoint(type);
			//是否之前已经赞过了
			boolean isliked = NewsUpDownModel.dao.isUpOrDown(uid, newsid);
			if(isliked){
				return ;
			}
			
			FubidetailModel detail=new FubidetailModel()
					.set("uid", uid)
					.set("type", type)
					.set("fubi", fubi)
					.set("dateline", new Date());
			detail.save();
			UserCountModel count = UserCountModel.dao.findById(uid);
			count.set("totalfubi", count.getInt("totalfubi")+fubi)
			.set("remainfubi", count.getInt("remainfubi")+fubi);
			count.update();
			
		}
		
		
//		public void getFubi(){
//			Integer uid = ShiroUtils.getUserId();
//			int type=getParaToInt("type",-1);
//			int fubi=FubiModel.dao.getPoint(type);
//			FubidetailModel detail=new FubidetailModel()
//					.set("uid", uid)
//					.set("type", type)
//					.set("fubi", fubi)
//					.set("dateline", new Date());
//			detail.save();
//		}

}
