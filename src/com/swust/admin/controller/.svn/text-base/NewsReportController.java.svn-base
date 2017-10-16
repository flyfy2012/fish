package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.NewsReportModel;

/**
 * @category 查看用户举报内容
 * @Description 查看举报的信息
 * @author hmilysean 
 * @date 2015年12月17日 下午5:37:19 
 * @version V0.1
 */

public class NewsReportController extends CommonController{
	
	public void index(){
		setAttr("page", NewsReportModel.dao.newsPaginate(getParaToInt("pageNum", 1), 
				getParaToInt("numPerPage", Preference.PAGE_PER_SIZE), getPara("keyword","")));		
		render("reportList.html");
	}
	
	/**
	 * 
	 * @Description  查看具体举报内容
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:43:25
	 */
	public void view(){
		int rid=getParaToInt();  //获取当前举报信息的id
		System.out.println(rid);
		if(rid==-1){
			setAttr("error", "举报内容不存在");
			render(BjuiRender.refresh("reportList.html"));
		}
		else
		{
			System.out.println("查看新闻");
			NewsReportModel r=new NewsReportModel()
					.set("id", rid)
					.set("isRead",2);
			r.update();
			setAttr("report", NewsReportModel.dao.viewReport(rid));  
			render("reportView.html");
			
		}
		
	}
	
	/**
	 * 
	 * @Description 删除举报内容
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:43:48
	 */
		public void delete() {
			NewsReportModel.dao.deleteById(getParaToInt());
			render(BjuiRender.refresh("reportList.html"));
		}
 	}
