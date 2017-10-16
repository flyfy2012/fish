package com.swust.admin.controller;

import java.util.ArrayList;
import java.util.List;
import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.CatKeytModel;
import com.swust.model.CatKeytValModel;
import com.swust.model.GoodsCategoryModel;
import com.swust.utils.EduStringUtil;

/**
 * 商品类别属性管理
 * @Description 
 * @author inging44
 * @date 2015年12月25日 下午7:36:44 
 * @version V0.1
 */
public class CatKeytController extends CommonController{
	
	public  static final String  menuRel= "keytAttrList";
	/**
	 * @category  属性列表
	 * @author inging44
	 * @date 2015年12月23日 下午8:49:34
	 */
	public void index() {
		int pid_0 = getParaToInt("pid_0",-1);
		setAttr("pid_0",pid_0);
		String[] pValues = getParaValues("pid[]")!=null?getParaValues("pid[]"):getParaValues("pid");
		List<Integer> pid = new ArrayList<Integer>();
		pid.add(pid_0);
		if(pValues!=null){
			int length = pValues.length;
			for(int i=1;i<=length;i++){
				if (EduStringUtil.isNumeric(pValues[i-1])) {
					pid.add(Integer.parseInt(pValues[i-1]));
				}
			}
			
			if(pid.get(pid.size()-1)!=-1 && GoodsCategoryModel.dao.getNext(pid.get(pid.size()-1)).isEmpty()){
				setAttr("list",CatKeytModel.dao.findByCatId(pid.get(pid.size()-1)));
				setAttr("catId",pid.get(pid.size()-1));
			}
		}
		setAttr("pidList",pid);
		setAttr("level",GoodsCategoryModel.dao.getLevel());
		setAttr("f_list", GoodsCategoryModel.dao.getNext(0));
		render("attrList.html");
	}

	/**
	 * @category  统一提交处理
	 * @Descprition 添加编辑删除
	 * @author inging44
	 * @date 2015年12月24日 下午12:52:25
	 */
	public void save(){
		keepPara();
		Integer[] ids = getParaValuesToInt("ids");
		String[] names = getParaValues("names");
		Integer[] displayorder = getParaValuesToInt("displayorder");
		int catId = getParaToInt("catId",0);
		
		List<CatKeytModel> list = CatKeytModel.dao.findByCatId(catId);
		if(ids==null){
			if(list!=null){
				for(CatKeytModel attr:list){
					CatKeytModel.dao.deleteById(attr.getInt("id"));
				}
			}
		}else{
			if(list!=null){
				for(CatKeytModel attr:list){
					boolean flag = false;
					for(Integer id:ids){
						if(attr.getInt("id")==id){
							flag=true;
							break;
						}
					}
					if(!flag){
						CatKeytModel.dao.deleteById(attr.getInt("id"));
					}
				}
			}
		}
		if(ids!=null){
			for(int i=0;i<ids.length;i++){
				if(ids[i]==0){
					new CatKeytModel().set("catId",catId).set("name",names[i])
					.set("displayorder", displayorder[i]).save();
				}else{
					new CatKeytModel().set("id",ids[i]).set("catId",catId)
					.set("name",names[i]).set("displayorder", displayorder[i]).update();
				}
			}
		}
		render(BjuiRender.success("操作成功",false));
	}
	
	/**
	 * @category  编辑属性值
	 * @author inging44
	 * @date 2015年12月25日 下午9:16:04
	 */
	public void editAttrValue(){
		int paramsId = getParaToInt(0,0);
		if(paramsId==0){
			render(BjuiRender.errorWithoutNavtab("请选择参数！"));
			return;
		}
		CatKeytModel params = CatKeytModel.dao.findById(paramsId);
		setAttr("params",params);
		setAttr("category",GoodsCategoryModel.dao.findById(params.getInt("catId")));
		setAttr("list",CatKeytValModel.dao.findValuesByParamsId(paramsId));
		render("attrValue.html");
	}
	
	/**
	 * @category  统一处理属性值数据
	 * @author inging44
	 * @date 2015年12月25日 下午9:16:13
	 */
	public void saveAttrValue(){
		keepPara();
		Integer[] ids = getParaValuesToInt("ids");
		String[] values = getParaValues("values");
		Integer[] displayorder = getParaValuesToInt("displayorder");
		int paramsId = getParaToInt("paramsId",0);
		
		List<CatKeytValModel> list = CatKeytValModel.dao.findValuesByParamsId(paramsId);
		if(ids==null){
			if(list!=null){
				for(CatKeytValModel attr:list){
					CatKeytValModel.dao.deleteValByParamsId(attr.getInt("id"));
				}
			}
		}else{
			if(list!=null){
				for(CatKeytValModel attr:list){
					boolean flag = false;
					for(Integer id:ids){
						if(attr.getInt("id")==id){
							flag=true;
							break;
						}
					}
					if(!flag){
						CatKeytValModel.dao.deleteById(attr.getInt("id"));
					}
				}
			}
		}
		if(ids!=null){
			for(int i=0;i<ids.length;i++){
				if(ids[i]==0){
					new CatKeytValModel().set("paramsId",paramsId).set("value",values[i])
					.set("displayorder", displayorder[i]).save();
				}else{
					new CatKeytValModel().set("id",ids[i]).set("paramsId",paramsId)
					.set("value",values[i]).set("displayorder", displayorder[i]).update();
				}
			}
		}
		render(BjuiRender.success("操作成功",false));
	}
	
}
