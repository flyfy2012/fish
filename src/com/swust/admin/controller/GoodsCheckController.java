package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.GoodsModel;

/**
 * 商品审核
 * @Description 
 * @author inging44
 * @date 2015年12月27日 下午3:30:30 
 * @version V0.1
 */
public class GoodsCheckController  extends CommonController {
	
	/**
	 * @category 已审核 
	 * @author inging44
	 * @date 2015年12月27日 下午3:30:27
	 */
	public void alreadyCheckedList(){
		String title = getPara("title","").trim();
		setAttr("page",GoodsModel.dao.paginateChecked(getParaToInt("pageCurrent", 1), 
				getParaToInt("pageSize", Preference.PAGE_PER_SIZE),title));
		setAttr("title",title);
		render("alreadyCheckedList.html");
	}
	
	/**
	 * @category  待审核
	 * @author inging44
	 * @date 2015年12月27日 下午3:30:49
	 */
	public void waitingCheckList(){
		String title = getPara("title","").trim();
		setAttr("page",GoodsModel.dao.paginateUnchecked(getParaToInt("pageCurrent", 1), 
					getParaToInt("pageSize", Preference.PAGE_PER_SIZE),title));
		setAttr("title",title);
		render("waitingCheckList.html");
	}
	/**
	 * @category 执行审核 
	 * @author inging44
	 * @date 2015年12月27日 下午4:02:17
	 */
	public void doCheck(){
		int goodsId = getParaToInt(0,-1);
		int enable = getParaToInt(1,-1);
		if(goodsId==-1||enable==-1){
			render(BjuiRender.error("参数有误"));
			return;
		}
		new GoodsModel().set("id",goodsId).set("enable",enable).update();
		render(BjuiRender.refresh("waitingCheckList"));
	}
}
