package com.swust.admin.controller;

import com.jfaker.app.web.CommonController;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.NewsCategoryModel;
/**
 * @category 新闻的分类
 * @Description 新闻分类，数据库表为 fish_news_categary
 * @author hmilysean 
 * @date 2015年12月17日 下午5:02:30 
 * @version V0.1
 */
public class NewsCategoryController extends CommonController{
	
	public  static final String  menuRef= "newsCategoryList";
	
	public void index() {
		keepPara();
		setAttr("page", NewsCategoryModel.dao.paginate(getParaToInt("pageNum", 1), 
				getParaToInt("numPerPage", 20), getPara("keyword")));	
		render("newsCatList.html");
	}
	
	/**
	 *  添加的页面展示
	 */
	public void add() {
		render("newsCatAdd.html");
	}
	
	/**
	 * 编辑展示页面
	 */
	public void edit() {
		
		setAttr("newsCategory",NewsCategoryModel.dao.findById(getParaToInt(0)));
		render("newsCatEdit.html");
	}
	
	/**
	 * 保存具体执行
	 */
	//@Before(NewsCategoryValidator.class)
	public void save() {
		NewsCategoryModel newscat=new NewsCategoryModel()
				.set("displayorder", getPara("NewsCategory.displayorder"))
				.set("catname", getPara("NewsCategory.catname"));
		newscat.save();
	render(BjuiRender.closeCurrentAndRefresh(menuRef));
	}

	/**
	 * 更新具体执行
	 */
//	@Before(NewsCategoryValidator.class)
	public void update() {
		//getModel(NewsCategoryModel.class).update();
			NewsCategoryModel newscat=new NewsCategoryModel()
					.set("id", getPara("NewsCategory.id"))
					.set("displayorder", getPara("NewsCategory.displayorder"))
					.set("catname", getPara("NewsCategory.name"));
			newscat.update();
			render(BjuiRender.closeCurrentAndRefresh(menuRef));

	}
	
	/**
	 * 删除执行
	 */
	public void delete() {
		NewsCategoryModel.dao.deleteById(getParaToInt());
		render(DwzRender.refresh(menuRef));
	}
	
	
}
