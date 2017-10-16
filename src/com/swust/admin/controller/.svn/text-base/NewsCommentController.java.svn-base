package com.swust.admin.controller;


import java.util.Calendar;




import org.apache.shiro.SecurityUtils;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.swust.model.NewsCommentModel;
import com.swust.model.NewsModel;

/**
 * 新闻评论:用户添加评论，管理员删除评论。
 * @author hsongjiang
 *
 */
public class NewsCommentController  extends CommonController{


	public  static final String  menuRel= "newsCommentList";

	/**
	 * 显示整个评论
	 */
	public void index(){
      viewAll();
	}

	/**
	 *  添加评论
	 */
	public void add() {
		getSession().setAttribute("newsId",getParaToInt());
		render("newsCommentAdd.html");
	}

	/**
	 * 保存评论,评论人由username变为realname
	 * 2015-3-12,但是字段名暂时不变
	 */
	@Before(Tx.class)
	public void save() {

		NewsCommentModel newsCommentModel = new NewsCommentModel();
		newsCommentModel.set("newsid",getSession().getAttribute("newsId"));
		newsCommentModel.set("uid", ShiroUtils.getUserId());
		newsCommentModel.set("username", ShiroUtils.getRealName());
		newsCommentModel.set("postip", " ");
		newsCommentModel.set("dateline", Calendar.getInstance().getTime());
		newsCommentModel.set("message", getPara("content"));
		if(!newsCommentModel.save()){
			render(DwzRender.error("保存失败 "));
			return;
		}
		NewsModel newsModel = NewsModel.dao.findById(getSession().getAttribute("newsId"));
		newsModel.set("commentcount", newsModel.getInt("commentcount") + 1).update();
//		render(DwzRender.closeCurrentAndRefresh(menuName).message("保存成功 "));
		render(DwzRender.closeCurrentAndRefresh("newsList").message("更新成功"));

	}

	/**
	 *   编辑评论
	 */

	public void edit(){
		int commentId = getParaToInt(0);
		NewsCommentModel nCM = NewsCommentModel.dao.findById(commentId);
		setAttr("comment", nCM);
		NewsModel newsModel = NewsModel.dao.findById(nCM.getInt("newsid"));
		setAttr("newsTitle", newsModel.getStr("title"));
		render("newsCommentEdit.html");
	}

	/**
	 *  编辑过来的 更新
	 */

	public void update(){
		NewsCommentModel newsCommentModel = new NewsCommentModel();
        newsCommentModel.set("id", getParaToInt("commentId"));
		newsCommentModel.set("message", getPara("content"));
		if(!newsCommentModel.update()){
			render(DwzRender.error("更新失败 "));
			return;
		}
		render(DwzRender.closeCurrentAndRefresh(menuRel).message("更新成功"));
	}

	/**
	 * 删除某条评论
	 */
	@Before(Tx.class)
	public void delete(){
		
		
		NewsCommentModel nCM = NewsCommentModel.dao.findById(getParaToInt(0));
		if (!NewsCommentModel.dao.deleteById(getParaToInt(0))) {
			render(DwzRender.error("删除失败,或已删除.请刷新试试看"));
			return;
		} 
	    NewsModel nModel = NewsModel.dao.findById(nCM.getInt("newsid"));
	    nModel.set("commentcount", nModel.getInt("commentcount")-1);
	    nModel.update();
	    render(DwzRender.refresh(menuRel));
	}

	/**
	 * 查看所有评论
	 */
	public void viewAll() {
		boolean permissionNewsCommentManage=SecurityUtils.getSubject().isPermitted("NewsCommentManage"); //是否显示编辑
		
		int newsId =  getParaToInt("newsId");
//		String keyWord = getPara("keyword");
		NewsModel newsModel = NewsModel.dao.findById(newsId);
		setAttr("news", newsModel);
		setAttr("page", NewsCommentModel.dao.getCommentsByNews(newsId,getParaToInt("pageNum",1),getParaToInt("numPerPage",20)));
		setAttr("permissionNewsCommentManage",permissionNewsCommentManage);
		render("newsCommentsList.html");	
	}
}
