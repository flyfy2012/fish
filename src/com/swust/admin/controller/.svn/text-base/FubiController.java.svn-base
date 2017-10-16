package com.swust.admin.controller;


import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.FubiModel;
import com.swust.utils.EduStringUtil;

/**
 * @category 富币管理
 * @Description 设置富币获取的类型，已经商城顶部的图片设置
 * @author hmilysean 
 * @date 2015年12月25日 下午4:06:36 
 * @version V0.1
 */
	public class FubiController extends CommonController{
		
		/**
		 * @category 富币获取的类型
		 * @Description 签到，商品购买等获取富币方式
		 * @author hmilysean
		 * @date 2015年12月25日 下午4:09:58
		 */
	     public void index(){
	    	 Page<FubiModel> page=FubiModel.dao.newpaginate(getParaToInt("pageNum", 1), getParaToInt("numPerPage", 10));
//	    	 List<FubiModel> list = page.getList();
//	    	 System.out.println(list);
	    	 setAttr("page", page);
	    	 render("fubitype.html");
	     }
	     
	     public void add(){
	    	 render("typeadd.html");
	     }
	     
	     public void save(){
	    	 String name=getPara("name","");
	    	 int point=getParaToInt("point",-1);
	    	 if(EduStringUtil.isEmpty(name)||point==-1){
	    		 render(BjuiRender.error("出错了，不能为空哟"));
	    		 return ;
	    	 }
	    	 FubiModel fubi=new FubiModel()
	    			 .set("name", name)
	    			 .set("points", point);
	    	 fubi.save();
	    	 render(BjuiRender.closeCurrentAndRefresh("fubitype"));
    		 return ;
	     }
	     
	     public void edit(){
	    	 Integer tid = getParaToInt(0,-1);
	    	 if(tid==-1){
	    		 render(BjuiRender.error("出错啦！！"));
	    		 return;
	    	 }
	    	 FubiModel type = FubiModel.dao.EditType(tid);
	    	 setAttr("type", type);
	    	 render("typeEdit.html");
	     }
	     
	     public void update(){
	    	 int tid=getParaToInt("id",-1);
	    	 String name=getPara("name","");
	    	 int point=getParaToInt("point",-1);
	    	 if(tid==-1||point==-1||EduStringUtil.isEmpty(name)){
	    		 render(BjuiRender.error("出错了，不能为空哟！！"));
	    		 return ;
	    	 }
	    	 FubiModel fu=new FubiModel()
	    			 .set("id", tid)
	    			 .set("name", name)
	    			 .set("points", point);
	    	 fu.update();
	    	 render(BjuiRender.closeCurrentAndRefresh("fubitype"));
	    	 
	     }
	     

	     
	     public void delete(){
	    	 Integer tid = getParaToInt(0,-1);
	    	 if(tid==-1){
	    		 render(BjuiRender.error("出错啦"));
	    		 return ;
	    	 }
	    	 FubiModel.dao.deleteById(tid);
	    	 render(BjuiRender.refresh("fubitype"));
	    	 return;
	     }
	}
