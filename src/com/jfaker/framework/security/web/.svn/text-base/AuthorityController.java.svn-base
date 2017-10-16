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

import java.util.List;

import com.jfaker.framework.security.model.Authority;
import com.jfaker.framework.security.model.Resource;
import com.jfaker.framework.security.web.validate.AuthorityValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.bjui.BjuiRender;

/**
 * AuthorityController
 * @author yuqs
 * @since 0.1
 * 存在的问题：但添加或者修的权限时，点击下一页资源，权限的名称和描述清零了，现在采取的办法是，一页展示所有
 */
public class AuthorityController extends Controller {
	
	public  static final String  menuRel= "authorityList";
	
	public void index() {
		keepPara();
		setAttr("page", Authority.dao.paginate(getParaToInt("pageCurrent", 1), getParaToInt("pageSize", 20), getPara("name")));
		render("authorityList.html");
	}
	
	public void add() {
		setAttr("page", Resource.dao.paginate(getParaToInt("pageNum", 1), getParaToInt("numPerPage", 200), getPara("name")));
		render("authorityAdd.html");
	}
	
	public void edit() {
		setAttr("authority", Authority.dao.findById(getParaToInt()));//得到当前权限的相关信息，如名字描述
		Page<Resource> resources = Resource.dao.paginate(getParaToInt("pageNum", 1), 
				getParaToInt("numPerPage", 200), getPara("name"));//读出所有的权限列表		
		
		List<Resource> resss = Authority.dao.getResources(getParaToInt());
		for(Resource res : resources.getList()) {
			for(Resource selRes : resss) {
				if(res.getInt("id").intValue() == selRes.getInt("id").intValue())
				{
					res.put("selected", 1);
				}
				if(res.get("selected") == null)
				{
					res.put("selected", 0);
				}
			}
		}
		setAttr("page", resources);
		render("authorityEdit.html");
	}
	

	
	@Before({AuthorityValidator.class, Tx.class})
	public void save() {
		Integer[] orderIndexs = getParaValuesToInt("orderIndexs");
		Authority model = getModel(Authority.class);
		model.save();
		if(orderIndexs != null) {
			for(Integer orderIndex : orderIndexs) {
				Authority.dao.insertCascade(model.getInt("id"), orderIndex);
			}
		}
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}
	
	@Before({AuthorityValidator.class, Tx.class})
	public void update() {
		Integer[] orderIndexs = getParaValuesToInt("orderIndexs");
		Authority model = getModel(Authority.class);//表单中，有authority的所有字段，可以直接获取
		model.update();//根据id更新数据库。
		Authority.dao.deleteCascade(model.getInt("id"));//全部删除
		if(orderIndexs != null) {//重新写入
			for(Integer orderIndex : orderIndexs) {
				Authority.dao.insertCascade(model.getInt("id"), orderIndex);
			}
		}
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}
	
	/**
	 * 删除权限，需要首先删除权限对应资源，然后删除角色对应的权限，最后才能删除角色
	 */
	
	@Before(Tx.class)
	public void delete() {
		Authority.dao.deleteCascade(getParaToInt());//删除权限对应的资源
		Authority.dao.deleteRoleAuthority(getParaToInt());//删除角色对应的权限
		Authority.dao.deleteById(getParaToInt());//删除权限
		render(DwzRender.refresh(menuRel));
	}
}


