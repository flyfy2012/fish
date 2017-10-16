package com.swust.admin.controller;

import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.HarvestCommentModel;
import com.swust.model.HarvestModel;

/**
 * @category  渔获模块
 * @Description 后台管理用户发布的渔获
 * @author hmilysean 
 * @date 2015年12月21日 上午11:27:21 
 * @version V0.1
 */
public class HarvestController extends CommonController{
  
	/**
	 * @category  获取所有渔获
	 * @Description  获取所有渔获列表
	 * @author hmilysean
	 * @date 2015年12月21日 上午11:32:57
	 */
	public void index(){
		
		Page<HarvestModel> harvest = HarvestModel.dao.newsPaginate(
				getParaToInt("pageCurrent", 1), getParaToInt("pageSize", Preference.PAGE_PER_SIZE),
				getPara("keyword","").trim());	
		setAttr("keyword", getPara("keyword",""));
		setAttr("page", harvest);
		render("harvestList.html");
				
	}
	
	/**
	 * @category 查看渔获
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月22日 下午3:31:27
	 */
	public void view() {
		List<HarvestCommentModel> comment=HarvestCommentModel.dao.get(getParaToInt(0,-1));
		setAttr("comments", comment);
		List<HarvestModel> pictures = HarvestModel.dao.getPictures(getParaToInt(0,-1));
		setAttr("pictures", pictures);
		setAttr("harvest", HarvestModel.dao.get(getParaToInt()));
		render("harvestView.html");
	}
	
	/**
	 * @category 渔获状态
	 * @Description 对渔获的管理 
	 * @author hmilysean
	 * @date 2015年12月21日 下午3:25:28
	 */
	public void enable(){
		int hid=getParaToInt(0,-1);
		int type=getParaToInt(1,-1);
		if(hid==-1||type==-1){
			render(BjuiRender.error("参数有误"));
			return;
		}
		
		new HarvestModel().set("id",hid).set("enable",type).update();
		render(BjuiRender.refresh("harvestList"));
	}
	
}
