package com.swust.web.controller;

import com.jfaker.framework.security.model.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.swust.model.NewsModel;
import com.swust.utils.EduStringUtil;
/**
 * 
 * @Description 
 * @author Administrator
 * @date 2015年8月12日 下午10:05:13 
 * @version V1.3.1
 */
public class PortalController extends Controller{
	
	/**
	 * 
	 * @Description 
	 * @author Administrator
	 */
	
	public void index(){
		redirect("/admin/");
//		render("portal.html");
	}
	
	/**
	 * @category  用户邮箱点击确认更换邮箱
	 * @author inging44
	 * @date 2015年12月23日 下午12:47:13
	 */
	public void queRenEmail(){
		String verify = getPara("verify","");
		Record rc =Db.findFirst("select * from fish_user_change_email where verify=?",verify);
		if(rc==null){
			setAttr("error", -1);
			setAttr("message", "无效链接");
			render("queRenEmail.html");			
			return;
		}
		
		if(rc.get("changed")){
			setAttr("error", -1);
			setAttr("message", "重复操作~");
			render("queRenEmail.html");			
			return;
		}
		User user = User.dao.findById(rc.getInt("uid"));
		user.set("email", rc.getStr("newEmail")).update();
		rc.set("changed", 1);
		Db.update("fish_user_change_email", "uid",rc);
		setAttr("error", 0);
		setAttr("message", "操作成功");
		render("queRenEmail.html");		
	}
	
	/**
	 * @author hmilysean
	 * @category 无权限阅读新闻
	 */
	 
	 public void viewNews(){
		 int newsId =  getParaToInt("newsId");
			NewsModel m=NewsModel.dao.findById(newsId);
//			String s=m.get("picture");
//			String c=EduStringUtil.changeUrl(s);
//			m.set("picture", c);
			setAttr("news", m);
			render("viewnews.html");
	 }
	
}
