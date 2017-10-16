package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 商品图片表
 * @Description 
 * @author inging44
 * @date 2015年12月30日 下午2:42:40 
 * @version V0.1
 */
public class GoodsPicModel extends Model<GoodsPicModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static GoodsPicModel dao=new GoodsPicModel();
	
	/**
	 * @category 商品图片list 
	 * @author inging44
	 * @date 2015年12月30日 下午2:42:51 
	 * @param gid
	 * @return
	 */
	public List<GoodsPicModel> findByGoodsId(int gid){
		String sql = "select * from fish_goods_pic where goodsId=?";
		return find(sql,gid);
	}
	
	public void deleteByGid(int gid){
		Db.update("delete from fish_goods_pic where goodsId=?",gid);
	}
}
