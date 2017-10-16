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
import org.apache.shiro.SecurityUtils;
import com.jfaker.framework.security.model.Authority;
import com.jfaker.framework.security.model.Role;
import com.jfaker.framework.security.web.validate.RoleValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.bjui.BjuiRender;

/**
 * RoleController
 * @author yuqs
 * @since 0.1
 * 
 * 存在的问题：但添加或者修的角色时，点击下一页权限，角色的闽菜和描述清零了，现在采取的办法是，一页展示所有
 * hsongjiang 2015-3-14
 * 添加menuRel,
 */
public class RoleController extends Controller {
	
	public  static final String  menuRel= "roleList";
	
	public void index() {
		keepPara();
		
		//setRoles
		Page<Role> roles = Role.dao.paginate(getParaToInt("pageNo", 1), getParaToInt("pageSize",20),getPara("name"));
		setAttr("page",roles);
		
		//指定（增改）删权限
		boolean permissionEdit=SecurityUtils.getSubject().isPermitted("ROLEEDIT");
		boolean permissionDel=SecurityUtils.getSubject().isPermitted("ROLEDELETE");

		setAttr("permissionDel",permissionDel);
		setAttr("permissionEdit", permissionEdit);
		render("roleList.html");
	}

	public void add() {
		setAttr("page",Authority.dao.paginate(getParaToInt("pageNum", 1), 
				getParaToInt("numPerPage", 200), getPara("name")));
		render("roleAdd.html");
	}

	public void edit() {
		setAttr("role", Role.dao.findById(getParaToInt()));//读出角色相关信息
		Page<Authority> authorities = Authority.dao.paginate(getParaToInt("pageNum", 1), 
				getParaToInt("numPerPage", 200), getPara("name"));//读出所有的权限列表
		List<Authority> auths = Role.dao.getAuthorities(getParaToInt());//某个角色的所有资源
		if (auths!=null)
		{
			for(Authority auth : authorities.getList()) {//权限
				for(Authority sels : auths) {//角色
					if(auth.getInt("id").intValue() == sels.getInt("id").intValue())
					{
						auth.put("selected", 1);
					}
					if(auth.get("selected") == null)
					{
						auth.put("selected", 0);
					}
				}
			}
		}
		setAttr("page", authorities);
		render("roleEdit.html");
	}

	public void view() {
		setAttr("role", Role.dao.findById(getParaToInt()));
		setAttr("authorities", Role.dao.getAuthorities(getParaToInt()));
		render("roleView.html");
	}

	@Before({RoleValidator.class, Tx.class})
	public void save() {
		Integer[] orderIndexs = getParaValuesToInt("ids");
		Role model = getModel(Role.class);
		model.save();
		if(orderIndexs != null) {
			for(Integer orderIndex : orderIndexs) {
				Role.dao.insertCascade(model.getInt("id"), orderIndex);
			}
		}
		render(BjuiRender.success("操作成功"));
//		render(DwzRender.closeCurrentAndRefresh(menuRel).message("操作成功"));
	}

	@Before({RoleValidator.class, Tx.class})
	public void update() {
		Integer[] orderIndexs = getParaValuesToInt("ids");
		Role model = getModel(Role.class);
		model.update();
		Role.dao.deleteCascade(model.getInt("id"));
		if(orderIndexs != null) {
			for(Integer orderIndex : orderIndexs) {
				Role.dao.insertCascade(model.getInt("id"), orderIndex);
			}
		}
		render(BjuiRender.success("操作成功", true));
	}

	@Before(Tx.class)
	public void delete() {
		Role.dao.deleteCascade(getParaToInt());
		Role.dao.deleteById(getParaToInt());
		render(BjuiRender.success("操作成功", true));
	}
}


