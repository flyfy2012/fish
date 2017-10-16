package com.swust.mobile.controller;


import java.util.Date;
import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.model.User;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.swust.model.NewsModel;
import com.swust.model.NewsReportModel;
import com.swust.model.NewsUpDownModel;
import com.swust.model.UserFollowModel;

/**
 * @category 新闻资讯模块
 * @Description 
 * @author hmilysean 
 * @date 2015年12月22日 下午3:35:46 
 * @version V0.1
 */
public class NewsMobile extends CommonController {

	/**
	 * @category 获取通过审核的新闻 
	 * @Description  查看通过审核的新闻，可以通过catid 来获取分组新闻 
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:35:18
	 */
	public void index() {
		
		int myid=ShiroUtils.getUserId();
		//默认给用户推荐资讯列表
		Integer catid = getParaToInt("catid", 0);
		if(catid==0){
			getRecommend();
			return ;
		}
		//获取普通资讯列表
		Page<NewsModel> mNews = NewsModel.dao.newsPaginate(
				getParaToInt("pageNum", 1), getParaToInt("pageSize", 10),
				catid,0);
		for(NewsModel m: mNews.getList()){
			m.put("myfollow",UserFollowModel.dao.isFollow(myid, m.getInt("uid")));
		}
		setAttr("page", mNews);
		setAttr("error", 0);
		setAttr("message", "成功");
		renderJson();
	}

	/**
	 * @category 获取新闻详情，并且获取相关新闻
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月21日 下午7:02:35
	 */
	public void view() {
		int myid=ShiroUtils.getUserId();
		
		Integer newsid = getParaToInt("newsid",-1);
		if(newsid==-1){
			setAttr("error", "-1");
			setAttr("message", "该条资讯不存在！！ ");
			renderJson();
			return;
		}
		
		//获取相关资讯列表   
		List<NewsModel> newsgroup = NewsModel.dao.getNewsGroup(newsid);
		for(NewsModel s: newsgroup){
			s.put("myfollow",UserFollowModel.dao.isFollow(myid, s.getInt("uid")));
			
		}
		
		NewsModel news = NewsModel.dao.get(newsid);
		//统计阅读量 作为热点新闻
		news.set("view", news.getLong("view") + 1).update();
		setAttr("newsgroup", newsgroup);
		setAttr("news", news.put("myfollow",UserFollowModel.dao.isFollow(myid, news.getInt("uid"))));
		setAttr("error", 0);
		setAttr("message", "成功");
		renderJson();
	}
	/**
	 * @category 
	 * @Description 顶和赞是一个意思吧，赞的接口就没写了
	 * @author hmilysean
	 * @date 2015年12月27日 下午2:39:04
	 */
	@Before(Tx.class)
	public void up() {
		int  newsid = getParaToInt("newsid",-1);
		if(newsid == -1) {
			setAttr("error", "-1");
			setAttr("message", "选择新闻并评论");	
			renderJson();
			return;
		}
		//获取赞的富币
		FubiControllerMobile.getFubi.Dolike(newsid);
		int u = NewsUpDownModel.dao.up(ShiroUtils.getUserId(), newsid);
		if (0 == u) {
			// render(DwzRender.error("-_-||,已经顶或踩过了.."));
			setAttr("error", "-1");
			setAttr("message", "已经顶或踩过了..");
		} else {
			NewsModel news = NewsModel.dao.findById(newsid);
			if (1 == u) {
				news.set("up", news.getLong("up") + 1).update();
			} else if (2 == u) {
				news.set("up", news.getLong("up") + 1)
						.set("down", news.getLong("down") - 1).update();
			}
			setAttr("error", "0");
			setAttr("message", "操作成功");
		}

		renderJson();
		// render(DwzRender.success("顶了哦"));
	}

	/**
	 * 踩
	 */
	@Before(Tx.class)
	public void down() {
		
		int  newsid = getParaToInt("newsid",-1);
		if(newsid == -1) {
			setAttr("error", "-1");
			setAttr("message", "选择新闻并评论");	
			renderJson();
			return;
		}
		
		//检查一下是否存在该新闻
		
		
		int d = NewsUpDownModel.dao.down(ShiroUtils.getUserId(),
				newsid);
		if (0 == d) {
			setAttr("error", "-1");
			setAttr("message", "已经顶或踩过了..");
		} else {
			NewsModel news = NewsModel.dao.findById(newsid);
			if (1 == d) {
				news.set("down", news.getLong("down") + 1).update();
			} else if (2 == d) {
				news.set("down", news.getLong("down") + 1)
						.set("up", news.getLong("up") - 1).update();
			}
			setAttr("error", "0");
			setAttr("message", "操作成功");
		}
		renderJson();
	}
	
		/**
		 * @category 获取新闻分类列表     除去推广
		 * @Description 获取新闻分类的列表
		 * @author hmilysean
		 * @date 2015年12月20日 上午11:52:03
		 */
		public void catList(){
			setAttr("error", "0");
			setAttr("message", "操作成功");
			setAttr("catList", NewsModel.dao.getCat());
			renderJson();
		}
		
		/**
		 * @category  用户收藏资讯接口
		 * @Description  用户收藏资讯接口
		 * @author hmilysean
		 * @date 2015年12月20日 下午4:44:21
		 */
		public void collection(){
			int uid=ShiroUtils.getUserId();
			if(uid==-1){
				setAttr("error", -1);
				setAttr("message", "请登录后再试");
				renderJson();
				return ;
			}
			
			User currentUser = User.dao.get(uid);
			if (currentUser == null) { // 用户不存在，需要重新登录
				setAttr("error", "-1");
				setAttr("message", "需要登录");
				renderJson();
				return;
			}
			
		int newsid=getParaToInt("newsid",-1);
		
		if(newsid==-1){
			setAttr("error", "-1");
			setAttr("message", "文章不存在");
			renderJson();
			return;
		}
		
		String sql="select * from fish_user_collect where uid=? and tid=?";
		Record iscollect = Db.findFirst(sql, uid,newsid);
		if(iscollect!=null)
		{
			setAttr("error", "-1");
			setAttr("message", "已经收藏了");
			renderJson();
			return;
		}
		
		
		Record record = new Record().set("uid", uid)
				.set("tid", newsid).set("dateline", mCurrentDateTime);
		Db.save("fish_user_collect", record);
		setAttr("error", "0");
		setAttr("message", "收藏成功");
		renderJson();
		}
		
		/**
		 * @category 新闻举报
		 * @Description 新闻举报接口
		 * @author hmilysean
		 * @date 2015年12月21日 上午9:09:59
		 */
		public void NewsReport(){
			Integer userID = ShiroUtils.getUserId();
			User currentUser = User.dao.get(userID);
			if (currentUser == null) { // 用户不存在，需要重新登录
				setAttr("error", "-1");
				setAttr("message", "需要登录");
				renderJson();
				return;
			}
			
		int newsid=getParaToInt("newsid",-1);
		
		if(newsid==-1){
			setAttr("error", "-1");
			setAttr("message", "文章不存在");
			renderJson();
			return;
		}
		
		String content=getPara("content");   //用户举报新闻的内容 
		if(content.equals("")){
			setAttr("error", "-1");
			setAttr("message", "举报内容不能为空 ");
			renderJson();
			return;
		}
		
		NewsReportModel report=new  NewsReportModel()
				.set("newsid",newsid)
				.set("uid", userID)
				.set("content", content)
				.set("dateline", new Date())
				.set("isRead", 1);
		report.save();
		setAttr("error", "0");
		setAttr("message", "举报成功 ");
		renderJson();
		
		}
		
		/**
		 * @category 推荐新闻列表
		 * @Description 获取推荐列表
		 * @author hmilysean
		 * @date 2015年12月21日 下午4:23:15
		 */
		public void getRecommend(){
			int myid=ShiroUtils.getUserId();
			//获取推广广告列表
			List<NewsModel> spread = NewsModel.dao.getSpread();
			for(NewsModel s: spread){
				s.put("myfollow",UserFollowModel.dao.isFollow(myid, s.getInt("uid")));
				
			}
			setAttr("spread", spread);
			Page<NewsModel> recommend = NewsModel.dao.newsPaginate(
					getParaToInt("pageNum", 1), getParaToInt("pageSize", 10),
					getParaToInt("catid", 0),1);
		for(NewsModel m: recommend.getList()){
			m.put("myfollow",UserFollowModel.dao.isFollow(myid, m.getInt("uid")));
			
		}
		    setAttr("error", "0");
			setAttr("message", "操作成功");
			setAttr("recommend", recommend);
			renderJson();
			
		}

		
		/**
		 * @category  获取热点新闻
		 * @Description 获取热点新闻接口
		 * @author hmilysean
		 * @date 2015年12月21日 下午6:38:36
		 */
		public void getHot(){
			int myid=ShiroUtils.getUserId();
			Page<NewsModel> hot=NewsModel.dao.getHot(getParaToInt("pageNum", 1), getParaToInt("pageSize", 10));
			//List<NewsModel> hot = NewsModel.dao.getHot();
			for(NewsModel m: hot.getList()){
				m.put("myfollow",UserFollowModel.dao.isFollow(myid, m.getInt("uid")));
				
			}
			setAttr("error", "0");
			setAttr("message", "操作成功");
			setAttr("hot", hot);
			renderJson();
			
		}

}
