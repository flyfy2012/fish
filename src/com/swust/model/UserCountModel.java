package com.swust.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class UserCountModel extends Model<UserCountModel>{
	private static final long serialVersionUID = 1L;
	public static UserCountModel dao= new UserCountModel();
	
	
	public Page<UserCountModel> findFollowPage(int uid,int pageNumber, int pageSize) {
		String sql = " from fish_user_count where uid="+uid;
		return paginate(pageNumber, pageSize, "select *", sql);
	}
	/**
	 * @category 用户关注  
	 * @author inging44
	 * @date 2015年12月23日 下午1:06:12 
	 * @param uid
	 * @param fuid
	 */
	public void follow(int uid,int fuid){
		Db.update("update fish_user_count set follow=follow+1 where uid="+uid);
		Db.update("update fish_user_count set fans=fans+1 where uid="+fuid);
	}
	
	/**
	 * @category  用户取消关注
	 * @author inging44
	 * @date 2015年12月23日 下午1:06:23 
	 * @param uid
	 * @param fuid
	 */
	public void cancelFollow(int uid,int fuid){
		Db.update("update fish_user_count set follow=follow-1 where uid="+uid);
		Db.update("update fish_user_count set fans=fans-1 where uid="+fuid);
	}
	
	public int getRemainFubi(int uid){
		String sql="select remainfubi as remainfubi from fish_user_count where uid=?";
		Record r=Db.findFirst(sql,uid);
		if(r==null){
			return -1;
		}
		int remain = r.getInt("remainfubi");
		return remain;
	}
	/**
	 * 
	 * @Description 对用户账户的操作，将remainfubi减去price
	 * @author hmilysean
	 * @date 2015年12月27日 下午3:58:30 
	 * @param uid
	 * @param price
	 */
	public void update(Integer uid, int price) {
		// TODO Auto-generated method stub
		String sql="update fish_user_count set remainfubi=remainfubi-"+price+" where uid=?";
		Db.update(sql,uid);
	}
}
