package com.swust.admin.controller;

import java.util.ArrayList;
import java.util.List;
import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.model.CatNonkeytModel;
import com.swust.model.GoodsCategoryModel;
import com.swust.utils.EduStringUtil;
 /**
  * 后台商品类别属性管理
  * @Description 
  * @author inging44
  * @date 2015年12月23日 下午8:49:19 
  * @version V0.1
  */
public class CatNonkeytController extends CommonController{
	
	public  static final String  menuRel= "nonkeytAttrList";
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
				setAttr("list",CatNonkeytModel.dao.findByCatId(pid.get(pid.size()-1)));
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
		
		List<CatNonkeytModel> list = CatNonkeytModel.dao.findByCatId(catId);
		if(ids==null){
			if(list!=null){
				for(CatNonkeytModel attr:list){
					CatNonkeytModel.dao.deleteById(attr.getInt("id"));
				}
			}
		}else{
			if(list!=null){
				for(CatNonkeytModel attr:list){
					boolean flag = false;
					for(Integer id:ids){
						if(attr.getInt("id")==id){
							flag=true;
							break;
						}
					}
					if(!flag){
						CatNonkeytModel.dao.deleteById(attr.getInt("id"));
					}
				}
			}
		}
		if(ids!=null){
			for(int i=0;i<ids.length;i++){
				if(ids[i]==0){
					new CatNonkeytModel().set("catId",catId).set("name",names[i])
					.set("displayorder", displayorder[i]).save();
				}else{
					new CatNonkeytModel().set("id",ids[i]).set("catId",catId)
					.set("name",names[i]).set("displayorder", displayorder[i]).update();
				}
			}
		}
		render(BjuiRender.success("操作成功",false));
	}
}
