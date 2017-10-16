package com.jfaker.framework.security.web;

import java.util.ArrayList;
import java.util.List;
import com.jfaker.framework.security.model.Org;
import com.jfinal.core.Controller;
import com.jfinal.plugin.bjui.BjuiRender;
import com.jfinal.upload.UploadFile;
import com.swust.utils.EduStringUtil;
import com.swust.utils.ReadExcel;
import java.io.IOException;
/**
 * 第一级部门操作
 * @Description 
 * @author inging44
 * @date 2015年11月25日 上午9:21:02 
 * @version V0.1
 */
public class FirstOrgController extends Controller {
	public static  List<Object> list = new ArrayList<Object>(); 
	public  static final String  menuRel= "firstorg";
	
	public void index() {
		setAttr("orglist",Org.dao.getAllFirst());
		render("orgList.html");
	}
	
	public void add() {
		render("orgAdd.html");
	}
	
	public void view() {
		setAttr("org", Org.dao.get(getParaToInt(0)));
		render("orgView.html");
	}
	
	public void edit() {
		setAttr("org", Org.dao.get(getParaToInt(0)));
		render("orgEdit.html");
	}
	
	public void save() {
		Org org = new Org();
		org.set("name", getPara("org.name","").trim())
			.set("description",getPara("org.description","").trim())
			.set("displayorder", getParaToInt("org.displayorder",0))
			.set("level", 1)
			.save();
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}

	public void update() {
		Org org = new Org();
		org.set("id",getPara("org.id"))
			.set("name", getPara("org.name",""))
			.set("displayorder",getParaToInt("org.displayorder",0))
			.set("description",getPara("org.description",""))
			.set("level", 1)
			.update();
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}
	
	public void delete() {
		Org.dao.deleteById(getParaToInt());
		render(BjuiRender.refresh(menuRel));
	}
	
	public void importExcel(){
		render("importExcel.html");
	}
	
	public void saveExcel() throws IOException{
		String message = "操作成功！";
		UploadFile file = getFile("excel");
		if(file==null){
			render(BjuiRender.error("未发现Excel文件"));
			return;
		}
		try {
			List<List<Object>> list = ReadExcel.readExcel(file);
			for(List<Object> obj:list){
				
				Integer displayorder = 0;
				String name = obj.get(1).toString();
				String desc = obj.get(2).toString();
				if(EduStringUtil.isEmpty(name)){
					message = "excel名称存在空，错误部分已跳过";
					continue;
				}
				if(Org.dao.getOrgIdWithName(name,0)!=null){
					message = "数据库已存在相同名称，相同部分已跳过";
					continue;
				}
				
				try{
					displayorder = Integer.parseInt(new java.text.DecimalFormat("0").format(obj.get(0)));
					
				}catch(Exception ex) {
					message = "excel存在排序不为正整数！错误部分已跳过";
					continue;
				}
				new Org().set("name", name).set("parent_org", "0")
					.set("level", "1")
					.set("description", desc)
					.set("displayorder", displayorder)
					.save();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		render(BjuiRender.closeCurrentAndRefresh(menuRel).message(message));
	}
	
	
}


