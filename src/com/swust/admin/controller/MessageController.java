package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.model.User;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.MessageModel;

/**
 * 
 * @Description 给用户发送消息 
 * @author hmilysean 
 * @date 2016年1月25日 下午4:37:10 
 * @version V0.1
 */
public class MessageController extends CommonController{
	
	public void index(){
		Page<MessageModel> page=MessageModel.dao.newpaginate(getParaToInt("pageSize",15), getParaToInt("pageNum",1),getPara("name",""));
		setAttr("page", page);
		render("messageList.html");
	}
	
	public void add(){
		String username = getPara("username","").trim();
		Integer grouptype = getParaToInt("grouptype",-1);
		setAttr("user", User.dao.paginateUserList(username,grouptype,
				getParaToInt("pageCurrent", 1),getParaToInt("pageSize", 20)));
		
		render("messageAdd.html");
	}
	
	public void delete(){
		int mid=getParaToInt(0,-1);
		MessageModel.dao.deleteById(mid);
		render(BjuiRender.refresh(""));
	}
	
	
	public void view(){
		int mid=getParaToInt(0,-1);
		MessageModel message = MessageModel.dao.getDetail(mid);
		setAttr("message", message);
		render("messageView.html");
	}
	
	public void save(){
		int uid=ShiroUtils.getUserId();
		int touid=getParaToInt("touid",-1);
		String title=getPara("title","");
		String message=getPara("message","");
		String pic = getPara("pic","");
		MessageModel m=new MessageModel()
				.set("title", title)
				.set("uid", uid)
				.set("message", message)
				.set("touid", touid)
				.set("pic", pic)
				.set("dateline", mCurrentDateTime);
		m.save();
		render(BjuiRender.success("发送成功"));
	}
	

}
