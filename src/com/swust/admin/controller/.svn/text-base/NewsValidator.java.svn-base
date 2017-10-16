package com.swust.admin.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.validate.Validator;
import com.swust.model.NewsModel;

public class NewsValidator extends Validator{
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/oa/news/save")) {
			validateRequiredString("title", "titleMsg", "请输入标题!");
			validateRequiredString("content", "contentMsg", "请输入内容名称!");
			validateRequiredString("type", "typetMsg", "请输入分类名称!");
		}else if (actionKey.equals("/oa/news/update")) {
			validateRequiredString("newsId", "newsIdMsg", "非法操作!");
			validateRequiredString("title", "titleMsg", "请输入标题!");
			validateRequiredString("content", "contentMsg", "请输入内容名称!");
			validateRequiredString("type", "typetMsg", "请输入分类名称!");
		}
	}


	protected void handleError(Controller controller) {
		controller.keepModel(NewsModel.class);

		String actionKey = getActionKey();
		if (actionKey.equals("/oa/news/save"))
//			controller.render("newsCatAdd.html");
			controller.render(DwzRender.error("添加类型"));
		else if (actionKey.equals("/oa/news/update"))
			controller.render("orgEdit.html");
		else if (actionKey.equals("/oa/news/editOrg"))
			controller.render("orgChoose.html");
	}
}
