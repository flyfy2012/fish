/*
 *  Copyright 2014-2015 snakerflow.com
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

import com.jfaker.framework.security.model.Resource;
import com.jfaker.framework.security.web.validate.ResourceValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.bjui.BjuiRender;

/**
 * ResourceController
 * @author yuqs
 * @since 0.1
 */
public class ResourceController extends Controller {
	
	
	public  static final String  menuRef= "resourceList";
	/**
	 * 显示列表
	 */
	public void index() {
		keepPara();
		setAttr("page", Resource.dao.paginate(getParaToInt("pageCurrent",1),getParaToInt("pageSize",20),getPara("name")));
		render("resourceList.html");
	}
	/**
	 * 渲染增加页面
	 */
	public void add() {
		render("resourceAdd.html");
	}
	/**
	 * 渲染编辑页面
	 */
	public void edit() {
		setAttr("resource", Resource.dao.getById(getParaToInt()));
		render("resourceEdit.html");
	}
	/**
	 * 添加后的保存
	 */
	@Before(ResourceValidator.class)
	public void save() {
		new Resource().set("name",getPara("resource.name"))
		.set("source",getPara("resource.source"))
		.set("menu",getParaToInt("pid")).save();
		render(BjuiRender.closeCurrentAndRefresh(menuRef));
	}
	
	/**
	 * 编辑后的保存,
	 * hsongjiang  修复bug，修改后的菜单ID不对
	 */
	@Before(ResourceValidator.class)
	public void update() {
		int resourseId = getParaToInt("resource.id",-1);
		
		
		new Resource().set("id", resourseId)
					 .set("name", getPara("resource.name"))
					 .set("source", getPara("resource.source"))
					 .set("menu", getParaToInt("parentid")).update();
		render(BjuiRender.closeCurrentAndRefresh(menuRef));
	}
	
	/**
	 * 直接删除
	 */
	public void delete() {
		Resource.dao.deleteById(getParaToInt());
		render(BjuiRender.refresh(menuRef));
	}
}


