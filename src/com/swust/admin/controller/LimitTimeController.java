package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.LimitTimeModel;


/**
 * @category 限时抢购时间设置
 * @Description 
 * @author hmilysean 
 * @date 2015年12月23日 上午9:54:26 
 * @version V0.1
 */
public class LimitTimeController extends CommonController{
		
	/**
	 * @category 所有限时特惠时间
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月23日 上午11:02:23
	 */
		public void index(){
			Page<LimitTimeModel> time = LimitTimeModel.dao.newsPaginate(
					getParaToInt("pageNum", 1), getParaToInt("numPerPage", 10));	
			
			setAttr("page", time);
			render("limitTime.html");
		}
		
	/**
	 * @category 添加特惠时间
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月23日 上午11:02:59
	 */
		public void add(){
			render("limitTimeadd.html");
		}
	
		/**
		 * @category 删除时间
		 * @Description 
		 * @author hmilysean
		 * @date 2015年12月23日 上午11:03:20
		 */
		public void delete(){
			Integer timeId = getParaToInt(0,-1);
		    if(timeId==-1){
		    	render(BjuiRender.error("选择时间"));
				return;			
		    }
		    Db.deleteById("fish_mall_limited", timeId);
		    render(BjuiRender.refresh("limitTime"));
		}
		
//		public void edit(){
//			
//		}
		
		public void save(){
			
			String startime=getPara("startime","");
			String endtime=getPara("endtime","");
			if(startime.equals("")||endtime.equals("")){
				 render(BjuiRender.error("出错啦"));
			}
			LimitTimeModel time=new LimitTimeModel()
					.set("startTime", startime)
					.set("endTime", endtime);
			time.save();
			 render(BjuiRender.closeCurrentAndRefresh("limitTime"));
		}
		
//		public void update(){
//			
//			
//		}
}
