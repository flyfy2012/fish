package com.swust.model;

import java.util.List;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/**
 * 团购用户
 * @Description 
 * @author inging44
 * @date 2016年1月14日 下午5:08:00 
 * @version V0.1
 */
public class TeamBuyUserModel extends Model<TeamBuyUserModel>{
	private static final long serialVersionUID = 1L;
	public static TeamBuyUserModel dao= new TeamBuyUserModel();
	
	/**
	 * @category  用户团购
	 * @author inging44
	 * @date 2016年1月14日 下午6:56:36 
	 * @param pageSize
	 * @param pageNumber
	 * @param uid
	 * @return
	 */
	public Page<TeamBuyUserModel> getByUid(int pageSize,int pageNumber,int uid){
		String sql = "from fish_mall_teambuy_user tu "
				+ "left join fish_mall_teambuy t on t.id=tu.tid "
				+ "left join fish_goods g on g.id=t.goodsId "
				+ "left join fish_shop_count sc on sc.uid=t.shopId "
				+ "where tu.uid=?";
		return paginate(pageNumber, pageSize, "select sc.shopName,sc.picture as shopAvatar,g.title,g.cover,tu.*,t.percent,t.id as teambuyId ", sql,uid);
	}
	/**
	 * @category 参加某团购的用户列表 
	 * @author inging44
	 * @date 2016年1月14日 下午5:08:12 
	 * @param tid
	 * @return
	 */
	public List<TeamBuyUserModel> getByTid(int tid){
		String sql = "select * from fish_mall_teambuy_user where tid=? and payTime is not null";
		return find(sql,tid);
	}
	
	/**
	 * @category 用户的某团购 
	 * @author inging44
	 * @date 2016年1月14日 下午5:08:31 
	 * @param uid
	 * @param tid
	 * @return
	 */
	public TeamBuyUserModel findByUidAndTid(int uid,int tid){
		String sql = "select * from fish_mall_teambuy_user where uid=? and tid=?";
		return findFirst(sql,uid,tid);
	}

}
