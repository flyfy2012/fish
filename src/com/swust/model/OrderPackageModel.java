package com.swust.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

public class OrderPackageModel extends Model<OrderPackageModel>{
	private static final long serialVersionUID = 1L;
	public static OrderPackageModel dao= new OrderPackageModel();
	
	
	public Page<OrderPackageModel> paginate(int pageNumber,int pageSize,int shopId,String keyword){
		String select = "select p.* ,u.avatar,u.nickname,a.province,a.city,a.district,a.street,a.tel,a.zipCode";
		String sql = "from fish_package p left join sec_user u on u.id=p.uid "
				+ "left join fish_user_address a on a.id=p.address where p.shopId=?";
		if(EduStringUtil.isNotEmpty(keyword)){
			sql += " and a.tel like '%"+keyword+"%'";
		}
		return paginate(pageNumber, pageSize,select,sql,shopId);
	}
}
