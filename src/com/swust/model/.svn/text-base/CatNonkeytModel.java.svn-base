package com.swust.model;

import java.util.List;
import com.jfinal.plugin.activerecord.Model;

/**
 * 商品类别的属性管理
 * @Description 
 * @author inging44
 * @date 2015年12月25日 下午7:34:00 
 * @version V0.1
 */
public class CatNonkeytModel extends Model<CatNonkeytModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static CatNonkeytModel dao=new CatNonkeytModel();
	
	/**
	 * @category  获取类别下的属性列表
	 * @author inging44
	 * @date 2015年12月25日 下午8:02:17 
	 * @param catId
	 * @return
	 */
	public List<CatNonkeytModel> findByCatId(int catId){
		String sql = "select * from fish_goods_nonkeyt_params where catId=? order by displayorder";
		return find(sql,catId);
	}
	
}
