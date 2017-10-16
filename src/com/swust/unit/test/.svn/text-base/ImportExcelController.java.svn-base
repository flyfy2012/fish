package com.swust.unit.test;

import java.io.File;
import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.model.Org;
import com.jfinal.upload.UploadFile;

import com.swust.utils.ReadExcel;

public class ImportExcelController extends CommonController {
	
	public void index(){
		render("excelImport.html");
		
	}
	
	public void read(){
		System.out.println("********导入excel********");
		UploadFile file=getFile("excel");
		System.out.println(file.getFileName());
		System.out.println(file);
		ReadExcel imp=new ReadExcel();
		try {
			System.out.println("**************测试************");
		List<List<Object>> li = imp.readExcel(file);
		orgImport(li);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void orgImport(List<List<Object>> inform){
		for(int i=0;i<inform.size();i++){
			Org org=new Org();
			List<Object> ob=inform.get(i);
			
			org.set("displayorder", ob.get(0)).set("name", ob.get(1)).set("level", 1);
			org.save();
		
			System.out.println(inform.get(i));
		}
		
		
	}

}
