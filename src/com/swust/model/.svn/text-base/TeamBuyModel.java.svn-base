package com.swust.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

/**
 * 团购
 * @Description 
 * @author inging44
 * @date 2016年1月14日 上午10:02:42 
 * @version V0.1
 */
public class TeamBuyModel extends Model<TeamBuyModel>{
	private static final long serialVersionUID = 1L;
	public static TeamBuyModel dao= new TeamBuyModel();
	
	/**
	 * @category 首页列表 
	 * @author inging44
	 * @date 2016年1月14日 上午10:02:55 
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @param uid
	 * @return
	 */
	public Page<TeamBuyModel> paginate (int pageNumber, int pageSize, String title,int uid) {
		String sql = "from fish_mall_teambuy t "
				+ "left join fish_goods g on g.id=t.goodsId where t.shopId=? and t.isClosed=0";
		if(!EduStringUtil.isEmpty(title)){
			sql+=" and title like '%"+title+"%'";
		}
		sql += " order by t.createTime desc";
		System.out.println(sql);
		return paginate(pageNumber, pageSize, "select t.*,g.title ", sql,uid);
	}

	public Page<TeamBuyModel> getByCat(int pageSize, int pageNumber,int catId) {
		String sql = "from fish_mall_teambuy t "
				+ "left join fish_goods g on g.id=t.goodsId "
				+ "left join fish_shop_count c on c.uid=g.shopId "
				+ "where (g.catId="+catId+" or g.catIdParent="+catId+") and t.isClosed=0 order by t.createTime desc";
		return paginate(pageNumber, pageSize, "select t.*,g.title,g.cover,c.shopName ", sql);
	}
	
	/**
	 * @category 根据id查找团购详情 
	 * @author inging44
	 * @date 2016年1月14日 上午10:03:08 
	 * @param tid
	 * @return
	 */
	public TeamBuyModel fingById(int tid){
		String sql = "select t.*,g.title,g.cover from fish_mall_teambuy t "
				+ "left join fish_goods g on g.id=t.goodsId "
				+ "where t.id=? and t.isClosed=0 and t.endTime>now()";
		return findFirst(sql,tid);
	}
	
	/**
	 * @category 删除团购商品 
	 * @author inging44
	 * @date 2016年1月13日 下午7:17:02 
	 * @param tid
	 */
	public void deleteByTid(int tid){
		Db.update("update fish_mall_teambuy set isClosed=1 where id=?",tid);
	}
	
	public void add(int tid){
		Db.update("update fish_mall_teambuy set remain=remain-1,percent=((totalCount-remain)/totalCount) where id=?",tid);
	}
}
