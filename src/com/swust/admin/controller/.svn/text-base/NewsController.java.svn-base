package com.swust.admin.controller;

import java.util.Calendar;
import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.bjui.BjuiRender;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.swust.cfg.Preference;
import com.swust.model.NewsCategoryModel;
import com.swust.model.NewsCommentModel;
import com.swust.model.NewsModel;
import com.swust.utils.EduStringUtil;

 /**
  * @category 新闻资讯模块
  * @Description web端新闻Controller
  * @author hmilysean 
  * @date 2015年12月21日 上午10:35:52 
  * @version V0.1
  */
public class NewsController extends CommonController{
	
	public  static final String  menuRel= "newsList";
	
	public void index() {
		
		boolean isAdvance=false;  //当前用户的grouptype=0  为审核用户
		Integer uid = ShiroUtils.getUserId();
		keepPara();		
		int group = ShiroUtils.getGrouptype();
		if(group==0){
			isAdvance=true;
		}
		String title = getPara("title","").trim();
		setAttr("title",title);
		setAttr("page", NewsModel.dao.newsPaginate(getParaToInt("pageCurrent", 1), 
				getParaToInt("pageSize", Preference.PAGE_PER_SIZE), title,isAdvance,uid));
		render("newsList.html");
	}

	/**
	 * 添加新闻
	 */
	public void add() {
		Page<NewsCategoryModel> model =NewsCategoryModel.dao.paginate(1, 200, "");
		setAttr("newsType", model);
		render("newsAdd.html");
	}

	/**
	 *  查看某个新闻
	 */
	@Before (CacheInterceptor.class)  //配置ehcache插件 
	@CacheName("viewNews")
	public void view() {
	
		setAttr("news", NewsModel.dao.get(getParaToInt()));
		CacheKit.put("viewNews", "viewNews", NewsModel.dao.get(getParaToInt()));
		render("newsView.html");
	}

	/**
	 *  编辑新闻
	 *  update by hsongjiang  改为xheditor，去掉kindeditor
	 */
	public void edit() {
		
		NewsModel newsModel = NewsModel.dao.findById(getParaToInt(0,-1));
		if(newsModel.getInt("verify")==1){
			render(BjuiRender.error("审核通过的新闻不允许编辑"));
			return ;
		}
		setAttr("news", newsModel);
		int selectInt = NewsCategoryModel.dao.findById(newsModel.getInt("catid")).getInt("displayorder");
		//display从1开始
		setAttr("selectedInt", selectInt );
		Page<NewsCategoryModel> model = NewsCategoryModel.dao.paginate(1, 200, "");
		setAttr("newsType", model);
		render("newsEdit.html");
	}

	/**
	 * 添加新闻，后保存
	 */
	@Before(NewsValidator.class)
	public void save() { 
		String startime=getPara("startime");
		String endtime=getPara("endtime");
		int catid=getParaToInt("news.newscat");
		if(catid==0){
			render(BjuiRender.error("请选择分类"));
			return ;
		}
		String pic=getPara("news.picture");
		if(EduStringUtil.isEmpty(pic)){
			render(BjuiRender.error("出错了，要上传图片"));
			return;
		}
		String content = getPara("news.content");
		String title = getPara("news.title");
		if(EduStringUtil.isEmpty(title)||EduStringUtil.isEmpty(content)){
			render(BjuiRender.error("标题或内容不能为空"));
			return ;
		}
		NewsModel newsModel = new NewsModel();
		newsModel.set("title", title).
		set("picture", pic).
		set("content", content).
		set("dateline", Calendar.getInstance().getTime()).
		set("catid", catid).
		set("uid",ShiroUtils.getUserId());
		if(startime!=null&&endtime!=null){
			newsModel.set("starttime", startime)
			.set("endtime", endtime);
		}
		
		newsModel.save();
		render(BjuiRender.closeCurrentAndRefresh(menuRel).message("添加成功"));
	}

    /**
     * 
     * @Description 编辑更新操作 
     * @author hmilysean
     * @date 2015年12月21日 上午9:55:56
     */
	@Before(NewsValidator.class)
	public void update() { 
		
		String startime=getPara("startime");
		String endtime=getPara("endtime");
		
		NewsModel news = new  NewsModel();
		
		String pic=getPara("news.picture");
		if(EduStringUtil.isEmpty(pic)){
			render(BjuiRender.error("出错了，要上传图片"));
			return;
		}
		
		news.set("id", getPara("news.id")).
		set("picture", pic).
		set("title", getPara("title")).
		set("content", getPara("news.content")).
		set("dateline", Calendar.getInstance().getTime()).
		set("catid", getPara("newscat"));
		if(startime!=null&&endtime!=null){
			news.set("starttime", startime)
			.set("endtime", endtime);
		}
		
		if (!news.update()){
			render(BjuiRender.error("请稍后再试"));
			return;
		}
		render(BjuiRender.closeCurrentAndRefresh(menuRel).message("更新成功"));
	}

	@Before(Tx.class)
	public void delete() {
		
		Integer newsId = getParaToInt(0,-1);
		if(newsId == -1 ) {
			render(BjuiRender.error("选择新闻"));
			return;			
		}
		//首先删除评论		
		NewsCommentModel.dao.deleteAllComentByNewsId(newsId);
		
		//其次删除新闻
		NewsModel.dao.deleteById(getParaToInt());
		render(BjuiRender.refresh(menuRel));
	}
	
	
}
