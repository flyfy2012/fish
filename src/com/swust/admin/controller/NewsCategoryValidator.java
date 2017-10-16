package com.swust.admin.controller;

import com.jfaker.framework.security.model.Org;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 *  新闻的类别校验
 * @author hsongjiang  
 * @date 2015-1-30
 */
public class NewsCategoryValidator extends Validator{
	
	protected void validate(Controller controller) {
		validateRequiredString("newsCategoryModel.catname", "nameMsg", "请输入分类名称!");
	}
	
	
	protected void handleError(Controller controller) {
		controller.keepModel(Org.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/oa/newsCategory/save"))
			controller.render("NewsCategoryAdd.html");
		else if (actionKey.equals("/security/org/update"))
			controller.render("orgEdit.html");
		else if (actionKey.equals("/security/org/editOrg"))
			controller.render("orgChoose.html");
	}
}
