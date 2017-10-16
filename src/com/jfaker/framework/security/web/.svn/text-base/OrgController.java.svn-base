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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.jfaker.framework.security.model.Org;
import com.jfaker.framework.security.model.User;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfaker.framework.security.web.validate.OrgValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.bjui.BjuiRender;

/**
 * OrgController
 * @author yuqs
 * @since 0.1
 */
public class OrgController extends Controller {
	
	
	public  static final String  menuRel= "orgList";
	
	public void index() {
		keepPara();
		if(getParaToInt("lookup",-1) == 2) {
			setAttr("lookup",2);
		}
		
		int selectOrgID=getParaToInt("selectOrgId",-1);
		String searchOrgName = getPara("searchOrgName","");
		if(getParaToInt("lookup",-1) == 2) {
			setAttr("page", Org.dao.paginateFirst(getParaToInt("pageCurrent", 1), 
					getParaToInt("pageSize", 100)));	
		}else {
			setAttr("page", Org.dao.paginateList(getParaToInt("pageCurrent", 1), 
					getParaToInt("pageSize", 100), selectOrgID,searchOrgName));			
		}
	
		
		boolean permissionEdit=SecurityUtils.getSubject().isPermitted("ROLEEDIT"); //是否显示编辑
		boolean permissionDel=SecurityUtils.getSubject().isPermitted("ROLEDELETE");//是否显示删除

		setAttr("level_1",Org.dao.find("select * from sec_org where level = 1 and id not in( 1 ,2,4 ,13) "));
		setAttr("permissionDel",permissionDel);
		setAttr("permissionEdit", permissionEdit);
		render("orgList.html");
	}
	
	public void add() {
		render("orgAdd.html");
	}
	
	public void view() {
		setAttr("org", Org.dao.get(getParaToInt()));
		render("orgView.html");
	}
	
	public void edit() {
		setAttr("org", Org.dao.get(getParaToInt(0)));
		render("orgEdit.html");
	}
	
	@Before(OrgValidator.class)
	public void save() {
		Org org = new Org();
		org.set("name", getPara("org.name","").trim());
		org.set("parent_org",getParaToInt("parentid",0));
		org.set("description",getPara("org.description","").trim());
		org.set("displayorder", getParaToInt("org.displayorder",0));
		
		if(getParaToInt("parentid",0) == 0)
			org.set("level", 1);
		else
			org.set("level", 2);
		
		
		org.save();
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}

	@Before(OrgValidator.class)
	public void update() {
		Org org = new Org();
		org.set("id",getPara("org.id"));
		org.set("name", getPara("org.name",""));
		org.set("parent_org",getParaToInt("parentid",0));
		org.set("displayorder",getParaToInt("org.displayorder",0));
		org.set("description",getPara("org.description",""));
		
		if(getParaToInt("parentid",0) == 0)
			org.set("level", 1);
		else
			org.set("level", 2);
		org.update();
//		getModel(Org.class).update();
//		render(DwzRender.success());
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}
	
	public void delete() {
		Org.dao.deleteById(getParaToInt());
		render(DwzRender.refresh(menuRel));
	}
	
	
	/**
	 *   教师设置部门
	 *  @author hsongjiang
	 *  date:2015-1-10
	 */
//	@Before(OrgValidator.class)
	public void editOrg(){
		//获取到部门名称
		Integer org = getParaToInt("org.id",-1);
		if(org ==-1){
			render(DwzRender.error());
			return;
		}
		//获取当前用户的Uid
		Integer userID = ShiroUtils.getUserId();
		//更新当前用户的org值
		User.dao.findById(userID).set("org", org).update();
		//返回成功
		redirect("/");
	}
	
	/**
	 * 
	 * @Description 地区获取第二级。
	 * @author hsongjiang
	 * @date 2015年10月1日 下午3:25:56
	 */
	public void getLevel_2(){
		LinkedList<HashMap<String,String>> list = new LinkedList<HashMap<String,String>>(); 
		
		List<Org> district2= Org.dao.getByParent_fid(getParaToInt(0));
		if(district2!=null){
			for(Org district:district2 ){ 
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("value", district.getInt("id")+"");
				map.put("label", district.getStr("name"));
				list.add(map);
			}
		}
		
		renderJson(list);	
	}
	/*
	 * 通过学院获得班级，二级联动
	 */
	public void getGradeByAcademy(){
		LinkedList<HashMap<String,String>> list = new LinkedList<HashMap<String,String>>(); 
		
		List<Org> district2= Org.dao.getByParent_fid(getParaToInt(0));
		if(district2!=null){
			for(Org district:district2 ){ 
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("value", district.getInt("id")+"");
				map.put("label", district.getStr("name"));
				list.add(map);
			}
		}
		
		renderJson(list);	

	}

}


