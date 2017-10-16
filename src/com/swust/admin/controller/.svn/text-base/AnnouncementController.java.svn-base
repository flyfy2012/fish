package com.swust.admin.controller;

import java.util.ArrayList;
import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.AnnouncementModel;
/**
 * 公告管理
 * @Description 
 * @author inging44
 * @date 2015年11月13日 下午8:29:11 
 * @version V0.1
 */
public class AnnouncementController extends CommonController{
	public  static final String  gameRel= "announcementList";

	public void index() {
		setAttr("page", AnnouncementModel.dao.paginate(getParaToInt("pageCurrent",1),
				getParaToInt("pageSize",20) ,getPara("message","")));
		render("announcementList.html");
	}
	
	//保存修改（增加删除修改）
	public void save(){
		Integer[] ids = getParaValuesToInt("announcement.id");
		String[] messages = getParaValues("announcement.message");
		boolean flag = false;
		ArrayList<Integer> list = AnnouncementModel.dao.getAll();
		if(ids==null){
			for(Integer announcement:list){
				AnnouncementModel.dao.deleteById(announcement);
			}
		}else{
			if(list!=null){
				for(Integer announcement:list){
					for(Integer id:ids){
						if(announcement==id){
							flag=true;
							break;
						}
					}
					if(!flag){
						AnnouncementModel.dao.deleteById(announcement);
					}
				}
			}
		}
		if(ids!=null){
			for(int i=0;i<ids.length;i++){
				if(ids[i]==0){
					
					new AnnouncementModel().set("createTime",mCurrentDateTime)
						.set("message",messages[i]).save();
				}else{
					new AnnouncementModel().set("id",ids[i])
						.set("message",messages[i])
						.set("createTime",mCurrentDateTime).update();
				}
			}
		}
		render(BjuiRender.success("操作成功",false));
	}
	
   
   
   public void enable(){
	   new AnnouncementModel().set("id",getParaToInt(0)).set("enable",1).update();
	   render(BjuiRender.refresh(gameRel)); 
   }
   public void disable(){
	   new AnnouncementModel().set("id",getParaToInt(0)).set("enable",0).update();
	   render(BjuiRender.refresh(gameRel)); 
   }
 
   //查看
   public void view(){
	   setAttr("announcement",AnnouncementModel.dao.get());
	   render("announcementView.html");
   }
}