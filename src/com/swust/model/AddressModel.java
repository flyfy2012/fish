package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class AddressModel extends Model<AddressModel>{
	private static final long serialVersionUID = 1L;
	public static AddressModel dao= new AddressModel();
	
	/**
	 * @category  用户收货地址列表
	 * @author inging44
	 * @date 2015年12月21日 下午3:31:41 
	 * @param uid
	 * @return
	 */
	public List<AddressModel> getByUid(int uid){
		String sql = "select * from fish_user_address where uid="+uid;
		return find(sql);
	}
	
	public AddressModel getDefaultByUid(int uid){
		String sql = "select * from fish_user_address where isDefault=1 and uid="+uid;
		return findFirst(sql);
	}
	
	/**
	 * @category 取消地址默认 
	 * @author inging44
	 * @date 2015年12月21日 下午4:11:42 
	 * @param uid
	 */
	public void setAllNotDefault(int uid){
		String sql = "update fish_user_address set isDefault=0 where uid="+uid;
		Db.update(sql);
	}
}
