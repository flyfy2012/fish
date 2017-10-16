package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.NewsCommentModel;
import com.swust.model.NewsModel;
import com.swust.model.NewsUpDownModel;

/**
 * @category 资讯审核
 * @Description 后台新闻审核
 * @author hmilysean 
 * @date 2015年12月21日 上午10:58:22 
 * @version V0.1
 */
public class NewsCheckController  extends CommonController {
	/**
	 * @category 经过审核的新闻，包括通过 ，和未通过 
	 * @Description 已经审核过的新闻，包括审核通过，和审核未通过 
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:48:43
	 */
	public void alreadyCheck(){
		setAttr("page",NewsModel.dao.alreadyCheckList(getParaToInt("pageCurrent", 1), 
				getParaToInt("pageSize", Preference.PAGE_PER_SIZE),getPara("title","")));
		setAttr("title", getPara("title",""));
		render("alreadyCheckList.html");
	}
	

	
	 /**
	  * @category   等待审核的新闻 
	  * @Description  获取等待审核的新闻列表
	  * @author hmilysean
	  * @date 2015年12月21日 上午9:47:22
	  */
	public void waitingCheck(){
		setAttr("page",NewsModel.dao.waitingCheckList(getParaToInt("pageCurrent", 1), 
					getParaToInt("pageSize", Preference.PAGE_PER_SIZE),getPara("title","")));
		setAttr("title", getPara("title",""));
		render("waitingCheck.html");
	}
	
	
	/**
	 * @category  新闻审核
	 * @Description 新闻的审核接口 
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:46:51
	 */
	public void doCheck(){
		int newsId = getParaToInt(0,-1);
		int verify = getParaToInt(1,-1);
		if(newsId==-1||verify==-1){
			render(BjuiRender.error("参数有误"));
			return;
		}
		new NewsModel().set("id",newsId).set("verify",verify).update();
		render(BjuiRender.refresh("newsList"));
	}
	
	/** 
	 * @category  推荐新闻
	 * @Description  是否 把新闻设置为推荐
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:42:24
	 */
	public void doRecommend(){
		int newsId = getParaToInt(0,-1);
		int recommend = getParaToInt(1,-1);
		if(newsId==-1||recommend==-1){
			render(BjuiRender.error("参数有误"));
			return;
		}
		new NewsModel().set("id",newsId).set("recommend",recommend).update();
		render(BjuiRender.refresh("newsList"));
	}
	
	/**
	 * 
	 * @Description 查看评论，可对具体新闻的某条评论进行删除
	 * @author hmilysean
	 * @date 2016年1月12日 下午12:47:44
	 */
	public void viewComment(){
		int nid=getParaToInt(0,-1);
		if(nid==-1){
			render(BjuiRender.error("出错啦"));
			return;
		}
		
		Page<NewsCommentModel> page=NewsCommentModel.dao.getCommentsByNews(nid, getParaToInt("pageNumber",1),getParaToInt("pageSize",10));
		setAttr("page", page);
		setAttr("newsid", nid);
		render("commentList.html");
	}
	/**
	 * 
	 * @Description 删除新闻评论内容
	 * @author hmilysean
	 * @date 2016年1月12日 下午1:20:20
	 */
	public void deleteComment(){
		int cid=getParaToInt(0,-1);
		int nid=getParaToInt(1,-1);
		int uid=getParaToInt(2,-1);
		if(cid==-1){
			render(BjuiRender.error("操作出错啦"));
			return ;
		}
		NewsModel newsModel = NewsModel.dao.findById(nid);
		newsModel.set("comments", newsModel.getInt("comments") - 1).update();
		
		String sql="delete from fish_news_comment_updown where uid="+uid+" and newsCommentId="+cid;
		NewsCommentModel.dao.deleteById(cid);
		render(BjuiRender.refresh(""));
	}
	
	
}
