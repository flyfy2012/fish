package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.GoodsModel;
import com.swust.model.TeamBuyModel;
import com.swust.model.TeamBuyUserModel;
import com.swust.utils.EduStringUtil;

/**
 * 团购后台管理
 * @Description 
 * @author inging44
 * @date 2016年1月13日 下午7:17:24 
 * @version V0.1
 */
public class TeambuyController extends CommonController{
	
	public  static final String  rel= "teamBuyList";
	
	/**
	 * @category 团购列表 
	 * @author inging44
	 * @date 2016年1月12日 下午3:39:55
	 */
	public void index() {
		int shopId = ShiroUtils.getUserId();
		String title = getPara("title","").trim();
		setAttr("title",title);
		setAttr("page", TeamBuyModel.dao.paginate(getParaToInt("pageCurrent",1),
				getParaToInt("pageSize",20) ,title,shopId));
		render("teamBuyList.html");
	}
	
	/**
	 * @category 添加 
	 * @author inging44
	 * @date 2016年1月13日 下午2:34:57
	 */
	public void add(){
		setAttr("list",GoodsModel.dao.getSelfGoods(ShiroUtils.getUserId()));
		render("teamBuyAdd.html");
	}
	
	/**
	 * @category  添加保存
	 * @author inging44
	 * @date 2016年1月13日 下午7:15:25
	 */
	public void save(){
		int shopId = ShiroUtils.getUserId();
		int totalCount = getParaToInt("totalCount",0);
		String endTime = getPara("endTime","").trim();
		String price = getPara("price","").trim();
		int goodsId = getParaToInt("goodsId",0);
		if(shopId==-1||goodsId==0||totalCount==0||EduStringUtil.isEmpty(endTime)||EduStringUtil.isEmpty(price)){
			render(BjuiRender.error("参数填写不完整",false));
			return;
		}
		new TeamBuyModel().set("endTime", endTime)
			.set("totalCount", totalCount)
			.set("shopId", shopId)
			.set("goodsId", goodsId)
			.set("remain", totalCount)
			.set("teambuyPrice", price)
			.set("createTime", mCurrentDateTime)
			.save();
		render(BjuiRender.closeCurrentAndRefresh(rel));
	}
	
	/**
	 * @category 编辑
	 * @author inging44
	 * @date 2016年1月13日 下午8:25:40
	 */
	public void edit(){
		int tid = getParaToInt(0,0);
		if(tid==0){
			render(BjuiRender.error("操作错啦！"));
			return;
		}
		TeamBuyModel teamBuy = TeamBuyModel.dao.fingById(tid);
		if(teamBuy==null){
			render(BjuiRender.error("操作错啦！"));
			return;
		}
		int shopId = ShiroUtils.getUserId();
		if(teamBuy.getInt("shopId")!=shopId){
			render(BjuiRender.error("别人的，你别动了"));
			return;
		}
		setAttr("teamBuy",teamBuy);
		render("teambuyEdit.html");
	}
	
	/**
	 * @category 编辑提交修改 
	 * @author inging44
	 * @date 2016年1月14日 上午9:59:02
	 */
	public void update(){
		int shopId = ShiroUtils.getUserId();
		int totalCount = getParaToInt("totalCount",0);
		String endTime = getPara("endTime","").trim();
		String price = getPara("price","").trim();
		int tid = getParaToInt("tid",0);
		if(shopId==-1||tid==0||totalCount==0||EduStringUtil.isEmpty(endTime)||EduStringUtil.isEmpty(price)){
			render(BjuiRender.error("参数填写不完整",false));
			return;
		}
		new TeamBuyModel().set("id", tid)
			.set("endTime", endTime)
			.set("totalCount", totalCount)
			.set("shopId", shopId)
			.set("remain", totalCount)
			.set("teambuyPrice", price)
			.set("createTime", mCurrentDateTime)
			.update();
		render(BjuiRender.closeCurrentAndRefresh(rel));
	}
	
	/**
	 * @category  删除
	 * @author inging44
	 * @date 2016年1月13日 下午7:15:28
	 */
	public void delete(){
		int tid = getParaToInt(0,0);
		if(tid!=0){
			TeamBuyModel.dao.deleteByTid(tid);
		}
		render(BjuiRender.refresh(rel));
	}
	
	/**
	 * @category  查看参团用户
	 * @author inging44
	 * @date 2016年1月12日 下午5:19:58
	 */
	public void viewUser(){
		int tid = getParaToInt(0,0);
		setAttr("goods",TeamBuyModel.dao.fingById(tid));
		setAttr("list",TeamBuyUserModel.dao.getByTid(tid));
		render("viewUser.html");
	}
}