package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 购物车
 * @Description 
 * @author inging44
 * @date 2015年12月31日 下午5:17:14 
 * @version V0.1
 */
public class ShoppingCartModel extends Model<ShoppingCartModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static ShoppingCartModel dao=new ShoppingCartModel();
	
	/**
	 * @category 查询当前商品是否已加入购物车 
	 * @author inging44
	 * @date 2015年12月31日 下午5:17:34 
	 * @param uid
	 * @param gdid
	 * @return
	 */
	public ShoppingCartModel findByUidAndGdid(int uid,int gdid){
		String sql = "select * from fish_shopping_cart where uid=? and goodsDetailId=?";
		return findFirst(sql,uid,gdid);
	}
	
	/**
	 * @category  根据商品id查找购物车商品信息
	 * @author inging44
	 * @date 2015年12月31日 下午5:17:37 
	 * @param uid
	 * @param goodsId
	 * @return
	 */
	public List<ShoppingCartModel> findByUidAndGid(int uid,int goodsId){
		String sql = "select c.*,p1.name as paramsName1,v1.value as paramsVal1,p2.name as paramsName2,v2.value as paramsVal2,fg.expressfee,vg.remain "
				+ "from fish_shopping_cart c "
				+ "left join fish_goods_keyt_params_value_goods vg on vg.id=c.goodsDetailId "
				+ "left join fish_goods fg on vg.goodsId=fg.id "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "where c.uid=? and vg.goodsId=?";
		return find(sql,uid,goodsId);
	}
	
	public void delByUidAndGdid(int uid,int goodsDetailId){
		Db.update("delete from fish_shopping_cart where uid=? and goodsDetailId=?",uid,goodsDetailId);
	}
	
	public Object getNumByUid(int uid){
		ShoppingCartModel car = findFirst("select count(*) as count from fish_shopping_cart where uid=?",uid);
		return car.get("count");
	}
	
	public List<ShoppingCartModel> getByUid(int uid){
		String sql = "select c.*,vg.goodsId,p1.name as paramsName1,v1.value as paramsVal1,p2.name as paramsName2,v2.value as paramsVal2 "
				+ "from fish_shopping_cart c "
				+ "left join fish_goods_keyt_params_value_goods vg on vg.id=c.goodsDetailId "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "where c.uid=?";
		return find(sql,uid);
	}
}





















