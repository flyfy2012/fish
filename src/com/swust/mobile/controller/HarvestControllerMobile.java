package com.swust.mobile.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.swust.model.HarvestCommentModel;
import com.swust.model.HarvestModel;
import com.swust.model.HarvestPicturesModel;
import com.swust.model.UserFollowModel;

/**
 * @category 渔获手机端
 * @Description 
 * @author hmilysean 
 * @date 2015年12月22日 下午3:33:59 
 * @version V0.1
 */
public class HarvestControllerMobile extends CommonController{
		
	/*  鱼获首页
	 * */
	public void index(){
		int myid=ShiroUtils.getUserId();
		
		Page<HarvestModel> harvest = HarvestModel.dao.Paginate(
				getParaToInt("pageNum", 1), getParaToInt("pageSize", 10));	
		for(HarvestModel a:harvest.getList()){
			
			a.put("pictures", HarvestPicturesModel.dao.getPictureByHid(a.getInt("id")));
			a.put("myfollow",UserFollowModel.dao.isFollow(myid, a.getInt("uid")));
		}
		setAttr("page", harvest);
		setAttr("error","0");
		setAttr("message","成功");
		renderJson();
	}
		
	/**
	 * @category 保存发布的渔获
	 * @Description 保存用户提交的渔获
	 * @author hmilysean
	 * @date 2015年12月21日 上午11:12:16
	 */
	public void save(){
		Integer uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "用户未登录");
			renderJson();
			return ;
		}
		
		//String pictures=getPara("pictures","");
		String content=getPara("content","");
		String address=getPara("address","");
		if(content.equals("")){
			setAttr("error", "-1");
			setAttr("message", "无法保存空白信息");
			renderJson();
			return ;
		}
//		try {
//			content=java.net.URLEncoder.encode(content,"UTF-8");
//			//content=new String(content.getBytes("GB2312"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			setAttr("error", -1);
//			setAttr("message", "不支持系统表情");
//			renderJson();
//			return;
//		}
		HarvestModel harvest=new HarvestModel()
				.set("uid", uid)
				.set("content", content)
				.set("address", address)
				.set("dateline", new Date());
		harvest.save();
		int hid = harvest.get("id");
		//获取图片数量  
		int picSize=getParaToInt("picSize",1);
		//保存图片
		for(int i=0;i<picSize;i++){
			String p=getPara("picture"+i,"");
			HarvestPicturesModel pictures=new HarvestPicturesModel()
					.set("hid", hid)
					.set("picture", p);
			pictures.save();
		}
		
		setAttr("error", "0");
		setAttr("message", "发布渔获成功");
		renderJson();
	}
	
	/**
	 * @category 查看某条渔获
	 * @Description 查看渔获内容
	 * @author hmilysean
	 * @date 2015年12月21日 上午11:13:24
	 */
	public void view(){
		Integer hid = getParaToInt("harvestid",-1);
		if(hid==-1){
			setAttr("error", "-1");
			setAttr("message", "该条渔获不存在");
			renderJson();
			return ;
		}
			//获取渔获内容
		HarvestModel harvest = HarvestModel.dao.get(hid);
		if(harvest==null){
			setAttr("error", "-1");
			setAttr("message", "该条渔获不存在");
			renderJson();
			return ;
		}
		//是否被我关注
		int myid=ShiroUtils.getUserId();
		setAttr("myfollow",UserFollowModel.dao.isFollow(myid, harvest.getInt("uid")));
		
		//获取渔获图片
		List<HarvestModel> pictures = HarvestModel.dao.getPictures(hid);
		setAttr("pictures", pictures);
		setAttr("error", "0");
		setAttr("message", "成功");
		setAttr("harvest", harvest);
		
		renderJson();
	}
	
	/**
	 * @category 获取具体渔获的评论列表
	 * @Description
	 * @author hmilysean
	 * @date 2016年1月12日 下午3:25:18
	 */
	public void getComments(){
		int harvestid=getParaToInt("harvestid",-1);
		if(harvestid==-1){
			setAttr("error", -1);
			setAttr("message", "渔获不存在");
			renderJson();
			return ;
		}
		//获取评论列表
		Page<HarvestCommentModel> page=HarvestCommentModel.dao.get(harvestid,getParaToInt("pageSize",10),getParaToInt("pageNum",1));
		setAttr("page",page);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	
	/**
	 * @category 删除发布的渔获   等下修改
	 * @Description 删除用户发布的渔获
	 * @author hmilysean
	 * @date 2015年12月21日 上午11:14:26
	 */
	public void delete(){
		Integer uid = ShiroUtils.getUserId();
		Integer hid = getParaToInt("harvestid");
		if(uid==-1){
			setAttr("error", -1);
			setAttr("message", "请重新登录后再试");
			renderJson();
			return;
		}
		boolean isexsist = HarvestModel.dao.isExsist(uid, hid);
		if(isexsist){
			
			//这里还需要删除评论,和赞
			HarvestCommentModel.dao.deleteComment(hid);
			String sql="delete from fish_harvest_favorites where harvestid=?";
			Db.update(sql,hid);
			
			HarvestModel.dao.deleteById(hid); 
			setAttr("error", "0");
			setAttr("message", "删除成功");
			renderJson();	
		}
		else{
			setAttr("error", "-1");
			setAttr("message", "删除失败！！！");
			renderJson();
		}
	}
	
	/**
	 * @category 渔获的赞
	 * @Description 赞
	 * @author hmilysean
	 * @date 2015年12月21日 上午11:17:11
	 */
	
	public void like(){
		
		Integer uid = ShiroUtils.getUserId();
		Integer hid = getParaToInt("harvestid",-1);
		if(uid==-1||hid==-1){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return ;
		}
		String sql ="select * from fish_harvest_favorites where uid=? and harvestid=?";
		Record recor = Db.findFirst(sql, uid,hid);

		if(recor!=null){
			Db.deleteById("fish_harvest_favorites", recor.getInt("id"));
			//Db.delete("fish_harvest_favorites", )
			Db.update("update fish_harvest set favorites=favorites-1 where id="+hid);
			setAttr("error", "0");
			setAttr("message", "取消了赞");
			renderJson();
			return ;
		}
			
		Record record=new Record()
				.set("uid", uid)
				.set("harvestid", hid);
		Db.save("fish_harvest_favorites", record);
		
		Db.update("update fish_harvest set favorites=favorites+1 where id="+hid);
		
		setAttr("error", "0");
		setAttr("message", "点赞成功");
		renderJson();
	}
	
	/**
	 * @category 渔获评论
	 * @Description  不单独写一个模块
	 * @author hmilysean
	 * @date 2015年12月22日 上午9:34:53
	 */
	public void comment(){
		
		Integer uid = ShiroUtils.getUserId(); 
		String content=getPara("content","");
		Integer hid = getParaToInt("harvestid",-1);
		Integer touid = getParaToInt("touid",-1);
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请登录后再试");
			renderJson();
			return ;
		}
		
		if(content.equals("")){
			setAttr("error", "-1");
			setAttr("message", "请填写内容后再提交");
			renderJson();
			return ;
		}
		
		if(hid==-1){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return ;
		}
		
		HarvestCommentModel hcomment=new HarvestCommentModel()
				.set("uid", uid)
				.set("harvestid", hid)
				.set("content", content)
				.set("dateline", new Date());
		if(touid!=-1){
			hcomment.set("touid", touid);
		}
		hcomment.save();
		Db.update("update fish_harvest set comments=comments+1 where id="+hid);

		setAttr("error", "0");
		setAttr("message", "评论成功");
		renderJson();
		
	}
	
	/**
	 * @category 删除评论
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月22日 上午10:59:22
	 */
	public void deleteComment(){
		Integer uid = ShiroUtils.getUserId();
		int cid=getParaToInt("commentid",-1);
		int hid=getParaToInt("harvestid",-1);
		if(uid==-1||cid==-1||hid==-1){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return ;
		}
		String sql="delete from fish_harvest_comments where id="+cid+" and uid="+uid;
		if(Db.update(sql)==0){
			setAttr("error", "-1");
			setAttr("message", "已经删除了");
			renderJson();
			return ;
		}
		Db.update("update fish_harvest set comments=comments-1 where id="+hid);
		setAttr("error", "0");
		setAttr("message", "删除成功");
		renderJson();
	}
	
}
