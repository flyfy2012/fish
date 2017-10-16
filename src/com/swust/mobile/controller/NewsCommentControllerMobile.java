package com.swust.mobile.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.swust.model.NewsCommentModel;
import com.swust.model.NewsModel;

/**
 * @category 资讯评论模块
 * @Description 
 * @author hmilysean 
 * @date 2015年12月22日 下午3:35:04 
 * @version V0.1
 */
public class NewsCommentControllerMobile extends Controller{

	/**
	 * @category 获取某个新闻的评论 
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:32:09
	 */
	public void index(){
		
		int newsId =  getParaToInt("newsid");
		setAttr("error", 0);
		setAttr("message", "成功");
		setAttr("page", NewsCommentModel.dao.getCommentsByNews(newsId,getParaToInt("pageNum", 1), getParaToInt("pageSize", 10)));
		renderJson();
	}
	
	/**
	 * @category  保存评论
	 * @Description 保存提交的评论
	 * @author hmilysean
	 * @date 2015年12月21日 上午10:04:47 
	 * @throws UnsupportedEncodingException
	 */
	@Before(Tx.class)
	public void save() throws UnsupportedEncodingException{
		int uid=ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", -1);
			setAttr("message", "请登录后再试");
			renderJson();
			return ;
		}
		int touid=getParaToInt("touid",-1);


		int newsId =  getParaToInt("newsid",-1);
		if(newsId==-1){
			setAttr("error", "-1");
			setAttr("message", "id不能为空");
			renderJson();
			return;
		}

		NewsCommentModel newsCommentModel = new NewsCommentModel();
		newsCommentModel.set("newsid",newsId);
		newsCommentModel.set("uid", ShiroUtils.getUserId());
		newsCommentModel.set("dateline", Calendar.getInstance().getTime());
		newsCommentModel.set("content",getPara("content") );
		if(touid!=-1){
			boolean isHaveUser = NewsCommentModel.dao.iscommented(touid, newsId);
			if(isHaveUser)
			newsCommentModel.set("touid", touid);
			else{
				setAttr("error", "-1");
				setAttr("message", "你所要的评论的用户不存在");
				renderJson();
				return;
			}
		}
		//评论获取富币哟！！
		FubiControllerMobile.getFubi.Docomment(newsId);
		if(!newsCommentModel.save()){
			setAttr("error", "-1");
			setAttr("message", "评论失败");
			renderJson();
			return;
		}
		NewsModel newsModel = NewsModel.dao.findById(newsId);
		newsModel.set("comments", newsModel.getInt("comments") + 1).update();
		
		setAttr("error", "0");
		setAttr("message", "评论成功");
		renderJson();
	}
	
	/**
	 * @category 评论的赞
	 * @Description 评论的赞
	 * @author hmilysean
	 * @date 2015年12月20日 下午4:04:26
	 */
		public void Like(){
			if(ShiroUtils.getUserId() == -1){
			setAttr("error","-1");
			setAttr("message","登录才能操作");
			renderJson();
			return;
		}
		
		int commentId =  getParaToInt("commentid",-1);
		
		if(commentId ==-1){
			setAttr("error","-1");
			setAttr("message","未指定评论的id");
			renderJson();
			return;
		}
		Db.update("update fish_news_comment set favorite=favorite + 1 where id = ?",commentId);
		setAttr("error", "0");
		setAttr("message", "赞成功");
		renderJson();
		}
	
		
		/**
		 * 
		 * @Description 评论的顶和踩
		 * @author hmilysean
		 * @date 2015年12月20日 下午3:17:22
		 */
		@Before(Tx.class)
		public void up() {
			int  commentid = getParaToInt("commentid",-1);
			if(commentid == -1) {
				setAttr("error", "-1");
				setAttr("message", "选择评论");	
				renderJson();
				return;
			}
			int u = NewsCommentModel.dao.up(ShiroUtils.getUserId(), commentid);
			if (0 == u) {
				// render(DwzRender.error("-_-||,已经顶或踩过了.."));
				setAttr("error", "-1");
				setAttr("message", "已经顶或踩过了..");
			} else {
				NewsCommentModel comment = NewsCommentModel.dao.findById(commentid);
				Db.update("insert into fish_news_comment_updown Values("+commentid+","+ShiroUtils.getUserId()+",1)");
				if (1 == u) {
					comment.set("up", comment.getLong("up") + 1).update();
				}
				else{
					comment.set("up", comment.getLong("up") + 1)
							.set("down", comment.getLong("down") - 1).update();
				}
				setAttr("error", "0");
				setAttr("message", "操作成功");
			}

			renderJson();
			// render(DwzRender.success("顶了哦"));
		}

		/**
		 * 踩
		 */
		@Before(Tx.class)
		public void down() {
			
			int  commentid = getParaToInt("commentid",-1);
			if(commentid == -1) {
				setAttr("error", "-1");
				setAttr("message", "选择新闻并评论");	
				renderJson();
				return;
			}
			
			//检查一下是否存在该新闻
			
			
			int d = NewsCommentModel.dao.down(ShiroUtils.getUserId(), commentid);
							
			if (0 == d) {
				setAttr("error", "-1");
				setAttr("message", "已经顶或踩过了..");
			} else {
				NewsCommentModel comment = NewsCommentModel.dao.findById(commentid);
				Db.update("insert into fish_news_comment_updown Values("+commentid+","+ShiroUtils.getUserId()+",2)");
				if (1 == d) {
					comment.set("down", comment.getLong("down") + 1).update();
				}
				else{
					comment.set("down", comment.getLong("down") + 1)
							.set("up", comment.getLong("up") - 1).update();
				}
				setAttr("error", "0");
				setAttr("message", "踩成功");
			}
			renderJson();
		}
	
}
