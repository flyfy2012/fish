/*
 *  Copyright 2014-2015 edulab.cn
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.jfaker.framework.security.web;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.model.Menu;
import com.jfaker.framework.security.web.validate.MenuValidator;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.bjui.BjuiRender;

/**
 * MenuController
 * @author hsongjiang
 * @since 0.1 
 * @date 2014-12-14
 * tabID ="menuList"
 */
public class MenuController extends CommonController {
	
	public  static final String  menuRel= "menuList";
	
	public void index() {
		keepPara(); ///security/menu?lookup=1，将自动保存了变量lookup，在freemarker中可供使用
		setAttr("page", Menu.dao.paginate(getParaToInt("pageCurrent", 1), 
				getParaToInt("pageSize",80), getPara("menuname")));
		render("menuList.html");
	}

	public void add() {
		render("menuAdd.html");
	}

	public void view() {
		setAttr("menu", Menu.dao.get(getParaToInt()));
		render("menuView.jsp");
	}

	public void edit() {
		setAttr("menu",Menu.dao.get(getParaToInt(0)) );
		render("menuEdit.html");
	}

	@Before(MenuValidator.class)
	public void save() {
		//getModel(Menu.class).save();
		new Menu().set("name", getPara("menu.name"))
		.set("description",getPara("menu.description"))
		.set("parent_menu",getParaToInt("parentid"))
		.set("ref", getPara("menu.ref",""))
		.set("orderby",getParaToInt("menu.orderby")).save();
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}


	@Before(MenuValidator.class)
	public void update() { //通过这种方式实现更新
		Menu menu=new  Menu();
		menu.set("id",getParaToInt("menu.id"))
		.set("orderby",getParaToInt("menu.orderby"))
		.set("name", getPara("menu.name").trim())
		.set("parent_menu", getParaToInt("parentid"))
		.set("ref",getPara("menu.ref","").trim())
		.set("description", getPara("menu.description").trim());
		menu.update();
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}

	/**
	 * 删除某个菜单
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年9月20日 下午4:17:35
	 */
	public void delete() {
		int menuId  = getParaToInt(0,-1);
		if(menuId == -1){
			render(BjuiRender.error("菜单ID不对"));
			return;
		}
		Record res = Db.findFirst("select * from sec_resource where menu = ?",menuId);
		if( res != null) {
			render(BjuiRender.error("请至少先删除对应资源:"+res.getStr("name")));
			return;
		}
			
		Menu.dao.deleteById(getParaToInt());
		render(BjuiRender.refresh(menuRel));
	}
}


