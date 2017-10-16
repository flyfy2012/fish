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
public class GoodsCountMonthModel extends Model<GoodsCountMonthModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static GoodsCountMonthModel dao=new GoodsCountMonthModel();
	
	
	public long getMonthSelled(int gid){
		String sql = "select * from fish_goods_count_month where gid=? order by dateline desc";
		List<GoodsCountMonthModel> count = find(sql,gid);
		if(count.size()>1&&count.get(1)!=null){
			return count.get(1).get("selled");
		}
		return 0;
	}
	
	
	
	
}
