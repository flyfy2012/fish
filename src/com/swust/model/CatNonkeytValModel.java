package com.swust.model;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.swust.utils.EduStringUtil;

/**
 * 商品类别的属性值
 * @Description 
 * @author inging44
 * @date 2015年12月25日 下午7:34:00 
 * @version V0.1
 */
public class CatNonkeytValModel extends Model<CatNonkeytValModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static CatNonkeytValModel dao=new CatNonkeytValModel();
	
	/**
	 * @category  
	 * @author inging44
	 * @date 2015年12月27日 下午2:10:00 
	 * @param paramsId
	 * @return
	 */
	public List<CatNonkeytValModel> findValuesByParamsId(int paramsId){
		String sql = "select * from fish_goods_nonkeyt_params_value where paramsId=? order by displayorder";
		return find(sql,paramsId);
	}
	
	/**
	 * @category 根据 
	 * @author inging44
	 * @date 2015年12月27日 下午2:10:04 
	 * @param paramsId
	 */
	public void deleteValByParamsId(int paramsId){
		String sql = "delete from fish_goods_nonkeyt_params_value where paramsId=?";
		Db.update(sql,paramsId);
	}

	/**
	 * @category  根据商品id 查找其非关键属性
	 * @author inging44
	 * @date 2015年12月29日 下午1:42:52 
	 * @param goodsid
	 * @return
	 */
	public List<CatNonkeytValModel> findByGoodsId(int goodsid){
		String sql = "select * from fish_goods_nonkeyt_params_value where goodsId=?";
		return find(sql,goodsid);
	}
	
	/**
	 * @category  非关键参数
	 * @author inging44
	 * @date 2015年12月29日 下午5:04:27 
	 * @param goodsId
	 * @return
	 */
	public List<CatNonkeytValModel> findAllByGoodsId(int goodsId){
		List<CatNonkeytValModel> list = findByGoodsId(goodsId);
		if(list.size()==0){
			return null;
		}
		for(CatNonkeytValModel nonkeyt : list){
			if(EduStringUtil.isEmpty(nonkeyt.getStr("name"))){
				nonkeyt.set("name", CatNonkeytModel.dao.findById(nonkeyt.getInt("paramsId")).getStr("name"));
			}
		}
		return list;
	}
	
	public void deleteByGid(int gid){
		Db.update("delete from fish_goods_nonkeyt_params_value where goodsId=?",gid);
	}
}
