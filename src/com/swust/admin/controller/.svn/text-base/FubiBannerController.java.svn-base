package com.swust.admin.controller;

import java.util.Date;
import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.FubiBannerModel;
import com.swust.model.GoodsModel;
import com.swust.utils.EduStringUtil;

/**
 * @category Banner管理
 * @Description 对Banner进行增删改查
 * @author hmilysean 
 * @date 2015年12月25日 下午8:16:40 
 * @version V0.1
 */
public class FubiBannerController extends CommonController{

		public void index(){
			String keyword = getPara("keyword","").trim();
			setAttr("keyword",keyword);
			Page<FubiBannerModel> page=FubiBannerModel.dao.newpaginate(getParaToInt("pageNum", 1), getParaToInt("pageCurrent", 10),keyword);
			setAttr("page", page);
			render("banner.html");
		}
		
		public void add(){
			Integer uid = ShiroUtils.getUserId();
			//用户只能对自家商品操作
			List<GoodsModel> goods=GoodsModel.dao.getSelfGoods(uid);
			setAttr("goods", goods);
			render("banneradd.html");
		}
		
		public void save(){
			Integer uid = ShiroUtils.getUserId();
			Integer goodsId = getParaToInt("goodsid",0);
			//创建时间有系统默认为当前
			Date createTime = new Date();
			Date passTime=getParaToDate("passTime");
			String pic=getPara("goods.cover","");
			System.out.println(goodsId+"________"+pic);
			if(uid==-1||goodsId==0||EduStringUtil.isEmpty(pic)){
				render(BjuiRender.error("图片和链接商品不能为空哟！！"));
				return ;
			}
			FubiBannerModel banner=new FubiBannerModel()
					.set("pic", pic)
					.set("uid", uid)
					.set("gid", goodsId)
					.set("enabled", 0)
					.set("createTime", createTime)
					.set("passTime", passTime);
			banner.save();
			render(BjuiRender.closeCurrentAndRefresh("fubibanner"));
		}
		
		public void edit(){
			Integer uid = ShiroUtils.getUserId();
			Integer bid = getParaToInt(0,-1);
			if(bid==-1){
				render(BjuiRender.error("请选择Banner"));
				return ;
			}
			//获取商品列表
			List<GoodsModel> goods=GoodsModel.dao.getSelfGoods(uid);
			setAttr("goods", goods);
			FubiBannerModel banner=FubiBannerModel.dao.BannerDetail(bid);
			setAttr("banner", banner);
			render("bannerEdit.html");
		}
		
		
		public void update(){
			Integer uid = ShiroUtils.getUserId();
			Integer bid = getParaToInt("bannerid");
			Integer goodsId = getParaToInt("goodsid",0);
			Date createTime = new Date();
			Date passTime=getParaToDate("passTime");
			String pic=getPara("goods.cover","");
			if(uid==-1||goodsId==0||EduStringUtil.isEmpty(pic)){
				render(BjuiRender.error("图片和链接商品不能为空哟！！"));
				return ;
			}
			FubiBannerModel banner=new FubiBannerModel()
					.set("id", bid)
					.set("pic", pic)
					.set("uid", uid)
					.set("gid", goodsId)
					.set("enabled", 0)
					.set("createTime", createTime)
					.set("passTime", passTime);
			banner.update();
			render(BjuiRender.closeCurrentAndRefresh("fubibanner"));
			
		}
		
		
		public void delete(){
			int bid=getParaToInt(0,-1);
			if(bid==-1){
				render(BjuiRender.error("参数出错了"));
				return;
			}
			FubiBannerModel.dao.deleteById(bid);
			render(BjuiRender.refresh("fubibanner"));
		}
		
		
		public void view(){
			
			
			Integer bid = getParaToInt(0,-1);
			if(bid==-1){
				render(BjuiRender.error("请选择Banner"));
				return ;
			}
			
			FubiBannerModel banner=FubiBannerModel.dao.viewBanner(bid);
			setAttr("banner", banner);
			render("bannerView.html");
		}
		
		
		
		public void enable(){
			int enable=getParaToInt(0,-1);
			int bid=getParaToInt(1,-1);
			if(bid==-1||enable==-1){
				render(BjuiRender.error("参数错误"));
				return ;
			}
			FubiBannerModel banner=new FubiBannerModel()
					.set("id", bid)
					.set("enabled", enable);
			banner.update();
			render(BjuiRender.refresh("fubibanner"));
			
		}
}
