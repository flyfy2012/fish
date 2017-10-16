package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 商品具体属性值管理
 * @Description 
 * @author inging44
 * @date 2015年12月25日 下午7:34:00 
 * @version V0.1
 */
public class KeytValGoodsModel extends Model<KeytValGoodsModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static KeytValGoodsModel dao=new KeytValGoodsModel();
	
	/**
	 * @category  关键参数--商品列表
	 * @author inging44
	 * @date 2015年12月27日 下午2:10:00 
	 * @param paramsId
	 * @return
	 */
	public List<KeytValGoodsModel> findValuesByGoodsId(int goodsId){
		String sql = "select vg.*,p1.name as paramsName1,v1.value as paramsVal1,p2.name as paramsName2,v2.value as paramsVal2 "
				+ "from fish_goods_keyt_params_value_goods vg "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "where vg.goodsId=? order by vg.id;";
		return find(sql,goodsId);
	}
	
	/**
	 * @category 根据商品详情id 查找其基本属性 
	 * @author inging44
	 * @date 2016年1月4日 下午3:21:25 
	 * @param goodsDetailId
	 * @return
	 */
	public KeytValGoodsModel findValuesByGdId(int goodsDetailId){
		String sql = "select vg.*,p1.name as paramsName1,v1.value as paramsVal1,p2.name as paramsName2,v2.value as paramsVal2,g.title "
				+ "from fish_goods_keyt_params_value_goods vg "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "left join fish_goods g on g.id=vg.goodsId "
				+ "where vg.id=?";
		return findFirst(sql,goodsDetailId);
	}
	
	public void deleteByGid(int gid){
		Db.update("delete from fish_goods_keyt_params_value_goods where goodsId=?",gid);
	}
	
}
