package com.swust.model;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 商品类别的属性值
 * @Description 
 * @author inging44
 * @date 2015年12月25日 下午7:34:00 
 * @version V0.1
 */
public class CatKeytValModel extends Model<CatKeytValModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static CatKeytValModel dao=new CatKeytValModel();
	
	/**
	 * @category  
	 * @author inging44
	 * @date 2015年12月27日 下午2:10:00 
	 * @param paramsId
	 * @return
	 */
	public List<CatKeytValModel> findValuesByParamsId(int paramsId){
		String sql = "select * from fish_goods_keyt_params_value where paramsId=? order by displayorder";
		return find(sql,paramsId);
	}
	
	/**
	 * @category 根据 
	 * @author inging44
	 * @date 2015年12月27日 下午2:10:04 
	 * @param paramsId
	 */
	public void deleteValByParamsId(int paramsId){
		String sql = "delete from fish_goods_keyt_params_value where paramsId=?";
		Db.update(sql,paramsId);
	}
}
