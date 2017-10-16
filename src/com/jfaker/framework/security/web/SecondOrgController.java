package com.jfaker.framework.security.web;

import java.io.IOException;
import java.util.List;

import com.jfaker.framework.security.model.Org;
import com.jfinal.core.Controller;
import com.jfinal.plugin.bjui.BjuiRender;
import com.jfinal.upload.UploadFile;
import com.swust.utils.EduStringUtil;
import com.swust.utils.ReadExcel;


public class SecondOrgController extends Controller {
	
	public  static final String  menuRel= "secondorg";
	
	public void index() {
		setAttr("orglist",Org.dao.getAllFirst());
		render("orgList.html");
	}
	
	public void add() {
		int id = getParaToInt(0,0);
		if(id==0){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		setAttr("pid",id);
		render("orgAdd.html");
	}
	
	public void view() {
		setAttr("org", Org.dao.get(getParaToInt()));
		render("orgView.html");
	}
	
	public void edit() {
		int id = getParaToInt(1,0);
		if(id==0){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		setAttr("pid",id);
		setAttr("org", Org.dao.get(getParaToInt(0)));
		render("orgEdit.html");
	}
	
	public void save() {
		int pid = getParaToInt("org.pid",0);
		if(pid==0){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		if(Org.dao.findById(pid)==null){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		Org org = new Org();
		org.set("name", getPara("org.name",""))
			.set("parent_org",pid)
			.set("displayorder",getParaToInt("org.displayorder",0))
			.set("description",getPara("org.description",""))
			.set("level", 2)
			.save();
		render(BjuiRender.closeCurrentAndRefreshDiv("layout"));
	}

	public void update() {
		int pid = getParaToInt("org.pid",0);
		if(pid==0){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		if(Org.dao.findById(pid)==null){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		Org org = new Org();
		org.set("id",getPara("org.id"))
			.set("name", getPara("org.name",""))
			.set("parent_org",pid)
			.set("displayorder",getParaToInt("org.displayorder",0))
			.set("description",getPara("org.description",""))
			.set("level", 2)
			.update();
		render(BjuiRender.closeCurrentAndRefreshDiv("layout"));
	}
	
	public void delete() {
		Org.dao.deleteById(getParaToInt());
		render(new BjuiRender().divid("layout"));
	}
	
	public void getNext(){
		int id = getParaToInt(0,0);
		if(id==0){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		setAttr("porgid",id);
		setAttr("orglist",Org.dao.getByParent(id));
		render("secondList.html");
	}
	
	public void importExcel(){
		int id = getParaToInt(0,0);
		if(id==0){
			render(BjuiRender.error("操作错误！"));
			return;
		}
		setAttr("porgid",id);
		render("importExcel.html");
	}
	
	public void saveExcel() throws IOException{
		String message = "操作成功！";
		int pid = getParaToInt(0,-1);
		if(pid==-1){
			render(BjuiRender.error("出现异常"));
			return;
		}
		UploadFile file = getFile("excel");
		if(file==null){
			render(BjuiRender.error("未发现Excel文件"));
			return;
		}
		try {
			List<List<Object>> list = ReadExcel.readExcel(file);
			for(List<Object> obj:list){
				int displayorder = 0;
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
				new Org().set("name", name)
					.set("parent_org",pid)
					.set("level", "2")
					.set("description", desc)
					.set("displayorder", displayorder)
					.save();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		render(BjuiRender.closeCurrentAndRefreshDiv("layout").message(message));
	}
}


