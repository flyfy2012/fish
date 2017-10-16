package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class UserFollowModel extends Model<UserFollowModel>{
	private static final long serialVersionUID = 1L;
	public static UserFollowModel dao= new UserFollowModel();
	
	/**
	 * @category  分页获取关注列表
	 * @author inging44
	 * @date 2015年12月19日 下午1:41:37 
	 * @param uid
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<UserFollowModel> findFollowPage(int uid,int pageNumber, int pageSize) {
		String sql = " from fish_user_follow uf left join sec_user u on u.id=uf.fuid where uf.uid="+uid;
		return paginate(pageNumber, pageSize, "select distinct u.nickname,u.avatar,u.verify,uf.fuid as uid,u.id", sql);
	}
	
	/**
	 * @category  分页获取粉丝列表
	 * @author inging44
	 * @date 2015年12月19日 下午1:41:59 
	 * @param uid
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<UserFollowModel> findFansPage(int uid,int pageNumber, int pageSize){
		String sql = " from fish_user_follow uf left join sec_user u on u.id=uf.uid where uf.fuid="+uid;
		return paginate(pageNumber, pageSize, "select distinct u.nickname,u.avatar,u.verify,uf.uid", sql);
	}
	
	/**
	 * @category  获取关注list
	 * @author inging44
	 * @date 2015年12月19日 下午1:40:45 
	 * @param uid
	 * @return
	 */
	public List<UserFollowModel> findFollows(int uid){
		String sql = "select distinct * from fish_user_follow where uid="+uid;
		return find(sql);
	}
	
	/**
	 * @category  获取粉丝list
	 * @author inging44
	 * @date 2015年12月19日 下午1:41:08 
	 * @param uid
	 * @return
	 */
	public List<UserFollowModel> findFans(int uid){
		String sql = "select distinct * from fish_user_follow where fuid="+uid;
		return find(sql);
	}
	
	/**
	 * @category 取消关注（删除当前记录）  
	 * @author inging44
	 * @date 2015年12月21日 下午12:51:47 
	 * @param userid
	 * @param uid
	 */
	public void deleteByIds(int userid,int uid){
		String sql = "delete from fish_user_follow where uid=? and fuid=?";
		Db.update(sql,uid,userid);
	}
	
	/**
	 * @category  判断uid是否关注了fuid
	 * @author inging44
	 * @date 2015年12月21日 下午12:53:15 
	 * @param userid
	 * @param uid
	 * @return
	 */
	public UserFollowModel findByIds(int userid,int uid){
		String sql = "select * from fish_user_follow where uid=? and fuid=?";
		return findFirst(sql,uid,userid);
	}
	
	public boolean isFollow(int myid,int uid){
		String sql = "select * from fish_user_follow where uid=? and fuid=?";
		UserFollowModel have = dao.findFirst(sql,myid,uid);
		if(have!=null)
			return true;
		return false;
	}
}
