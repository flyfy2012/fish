package com.swust.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.GoodsCategoryModel;
import com.swust.utils.EduStringUtil;
 /**
  * 后台商品类别管理
  * @Description 
  * @author inging44
  * @date 2015年12月23日 下午8:49:19 
  * @version V0.1
  */
public class GoodsCategoryController extends CommonController{
	
	public  static final String  menuRel= "categoryList";
	/**
	 * @category  商品类别列表
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
			if(pid.get(pid.size()-1)!=-1){
				setAttr("list",GoodsCategoryModel.dao.getNext(pid.get(pid.size()-1)));
				setAttr("parentId",pid.get(pid.size()-1));
			}
		}
		setAttr("pidList",pid);
		setAttr("level",GoodsCategoryModel.dao.getLevel());
		setAttr("f_list", GoodsCategoryModel.dao.getNext(0));
		render("categoryList.html");
	}

	/**
	 * @category  商品下级分类列表
	 * @author inging44
	 * @date 2015年12月24日 下午12:40:44
	 */
	public void getNext(){
		LinkedList<HashMap<String,String>> list = new LinkedList<HashMap<String,String>>(); 
		int pid = getParaToInt(0,-1);
		if(pid==-1){
			List<Object> a = new ArrayList<Object>();
			renderJson(a);
			return;
		}
		if(pid!=0){
			HashMap<String,String> f = new HashMap<String,String>();
			f.put("value", " ");
			f.put("label", "请选择");
			list.add(f);
		}
		List<GoodsCategoryModel> GoodsCategories= GoodsCategoryModel.dao.getNext(pid);
		if(GoodsCategories!=null){
			for(GoodsCategoryModel GoodsCategory:GoodsCategories ){ 
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("value", GoodsCategory.getInt("id")+"");
				map.put("label", GoodsCategory.getStr("name"));
				list.add(map);
			}
		}
		renderJson(list);	
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
		int pid = getParaToInt("parentId",0);
		String[] pics = getParaValues("pics");
		ArrayList<Integer> list = GoodsCategoryModel.dao.getNextIds(pid);
		int level = 1;
		GoodsCategoryModel parentCat = GoodsCategoryModel.dao.findById(pid);
		if(parentCat!=null){
			level = parentCat.getInt("level")+1;
		}
		if(ids==null){
			if(list!=null){
				for(Integer category:list){
					GoodsCategoryModel.dao.deleteCasecade(category);
					GoodsCategoryModel.dao.deleteById(category);
					parentCat.set("hasChild", 0).update();
				}
			}
		}else{
			if(list!=null){
				for(Integer category:list){
					boolean flag = false;
					for(Integer id:ids){
						if(category==id){
							flag=true;
							break;
						}
					}
					if(!flag){
						GoodsCategoryModel.dao.deleteCasecade(category);
						GoodsCategoryModel.dao.deleteById(category);
					}
				}
			}
		}
		if(ids!=null){
			for(int i=0;i<ids.length;i++){
				if(ids[i]==0){
					new GoodsCategoryModel().set("parentId",pid).set("name",names[i]).set("level", level).set("pic", pics[i]).save();
					if(parentCat!=null){
						parentCat.set("hasChild", 1).update();
					}
				}else{
					new GoodsCategoryModel().set("id",ids[i]).set("name",names[i]).set("level", level).set("pic", pics[i]).update();
				}
			}
		}
		render(BjuiRender.success("操作成功",false));
	}
	
	/**
	 * 删除商品类别
	 */
	public void delete(){
		int cid=getParaToInt(0,-1);
	    new GoodsCategoryModel().deleteById(cid);
		render(BjuiRender.success("删除成功",false));
	}
}
