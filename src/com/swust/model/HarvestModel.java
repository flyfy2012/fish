package com.swust.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class HarvestModel extends Model<HarvestModel>{
	private static final long serialVersionUID = 1L;
	public static HarvestModel dao= new HarvestModel();
	
	/**
	 * @category  分页获取用户渔获列表
	 * @author inging44
	 * @date 2015年12月19日 下午1:41:37 
	 * @param uid
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<HarvestModel> paginate(int uid,int pageNumber, int pageSize) {
		String sql = " from fish_harvest where uid="+uid;
		sql+=" order by dateline desc";
		return paginate(pageNumber, pageSize, "select * ", sql);
	}
	/**
	 * @category 用户最新一条渔获 
	 * @author inging44
	 * @date 2015年12月19日 下午2:23:07 
	 * @param uid
	 * @return
	 */
	public String findContentByUid(int uid){
		String sql = "select content from fish_harvest where uid="+uid +" order by dateline";
		HarvestModel harvest = findFirst(sql);
		if(harvest==null) return "";
		return harvest.getStr("content");
	}
	/**
	 * @category  主要用户渔获的管理，
	 * @Description 查看所有用户发布的渔获
	 * @author hmilysean
	 * @date 2015年12月21日 下午2:25:04 
	 * @param paraToInt
	 * @param paraToInt2
	 * @param para
	 * @return
	 */
	
	public Page<HarvestModel> newsPaginate(int pageNumber, int pageSize, String para) {
		String sql=" from fish_harvest as h "
				+ "left join sec_user as user on h.uid=user.id";
		if(StringUtils.isNotEmpty(para)){
			sql += " where content like '%" + para + "%' ";
		}
		sql += " order by id desc";
		return paginate(
				pageNumber,
				pageSize,
				"select h.*,user.nickname,user.avatar ",
				sql);
	}
	
	/**
	 * 
	 * @Description   用户查看渔获具体内容
	 * @author hmilysean
	 * @date 2015年12月21日 下午3:07:57 
	 * @param id
	 * @return
	 */
	
	public HarvestModel get(Integer id) {
		
		String sql="select h.*,user.nickname,user.avatar from fish_harvest as h "
				+ "left join sec_user as user on user.id=h.uid "
				+ "where h.id=?";
		
		return HarvestModel.dao.findFirst(sql,id);
	}
		/**
		 * 
		 * @Description 是否该条新闻的发布者和当前用户是否为同一个人
		 * @author hmilysean
		 * @date 2015年12月21日 下午7:36:57 
		 * @param uid
		 * @param id
		 * @return
		 */
	public boolean isExsist(int uid,int id){
		String sql="select * from fish_harvest where uid=? and id=?";
		return null != dao.findFirst(sql,uid,id);
	}
	
	/**
	 * 
	 * @Description 手机端，获取所有有效的渔获
	 * @author hmilysean
	 * @date 2015年12月21日 下午7:38:44 
	 * @param paraToInt
	 * @param paraToInt2
	 * @return
	 */
		public Page<HarvestModel> Paginate(Integer pageNumber, Integer pageSize) {
			String sql=" from fish_harvest as h "
					+ "left join sec_user as u on h.uid=u.id " 
					
					+ "where enable=1 order by dateline desc";
			
			return paginate(pageNumber, pageSize,"select h.*,u.nickname,u.avatar ",sql);
		}
	/**
	 * 
	 * @Description 获取点赞数
	 * @author hmilysean
	 * @date 2015年12月22日 上午10:37:07 
	 * @param hid
	 * @return
	 */
		public long getLikeCount(int hid){
			String sql="select count(id) as likes from fish_harvest_favorites where harvestid=?";
			Record record = Db.findFirst(sql,hid);
			long likes=record.get("likes",0);
			return likes;		
		}
		
		public List<HarvestModel> getPictures(int hid){
			String sql="select * from fish_harvest_pictures where hid=?";
			return dao.find(sql,hid);
		}
		
}
