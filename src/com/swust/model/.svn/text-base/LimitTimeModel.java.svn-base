package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class LimitTimeModel extends Model<LimitTimeModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -5160832980854928632L;
	public static LimitTimeModel dao=new LimitTimeModel();
	
	
	public Page<LimitTimeModel> newsPaginate(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		String sql="from fish_mall_limited order by startTime";
		return paginate(pageNum, pageSize, "select * ", sql);
	}
	
	
	public List<LimitTimeModel> getLimitTime() {
		// TODO Auto-generated method stub
		String sql="select * from fish_mall_limited ";
		return dao.find(sql);
	}
}
