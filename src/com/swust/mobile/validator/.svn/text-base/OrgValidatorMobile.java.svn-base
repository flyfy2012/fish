package com.swust.mobile.validator;

import com.jfaker.framework.security.model.Org;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * 判断用户是否
 * @author hsongjiang
 *
 */
public class OrgValidatorMobile extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("org.name", "nameMsg", "请输入部门名称!");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Org.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/api/org/editOrg")) {		
			controller.setAttr("code", "0001");
			controller.setAttr("message", "请输入部门名称!");
			controller.renderJson();
		}
	}

}
