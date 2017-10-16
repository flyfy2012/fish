package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class HarvestCommentModel extends Model<HarvestCommentModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1228513763362351202L;
	public static HarvestCommentModel dao=new HarvestCommentModel();
	
	/**
	 * 
	 * @Description 获取某条渔获所有评论
	 * @author hmilysean
	 * @date 2015年12月22日 上午9:51:23 
	 * @param hid
	 * @return
	 */
	public List<HarvestCommentModel> get(Integer hid) {
		// TODO Auto-generated method stub
		String sql="select c.content as ccontent,u.nickname as cname,u.avatar as cavatar from fish_harvest_comments as c "
				+ "left join sec_user as u on u.id=c.uid "
				+ "where harvestid=?";
		
		return dao.find(sql, hid);
	}
	
	/**
	 * 
	 * @Description 以page形式获取渔获评论
	 * @author hmilysean
	 * @date 2016年1月12日 下午3:28:26 
	 * @param hid
	 * @return
	 */
	
	public Page<HarvestCommentModel> get(Integer hid,int pageSize,int pageNum) {
		// TODO Auto-generated method stub
		String sqlExceptSelect=" from fish_harvest_comments as c "
				+ "left join sec_user as user on c.touid=user.id "
				+ "left join sec_user as u on u.id=c.uid "
				+ "where harvestid="+hid;
		String select="select c.*,u.nickname,u.avatar,user.nickname as tousername";
		
		return paginate(pageNum, pageSize, select, sqlExceptSelect);
	}
	/**
	 * 
	 * @Description 根据渔获的id 来删除评论
	 * @author hmilysean
	 * @date 2015年12月22日 下午1:47:22 
	 * @param hid
	 * @return 
	 */
	public void deleteComment(Integer hid){
		String sql="delete from fish_harvest_comments where harvestid=?";
		Db.update(sql,hid);
	}
	
	
}
