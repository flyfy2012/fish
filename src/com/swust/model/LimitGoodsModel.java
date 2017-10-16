package com.swust.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class LimitGoodsModel extends Model<LimitGoodsModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -2875643485227382921L;
	public static LimitGoodsModel dao=new LimitGoodsModel();
	
	/**
	 * 
	 * @Description Web展示所有参加限时特惠的商品
	 * @author hmilysean
	 * @date 2015年12月23日 下午3:09:49 
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	public Page<LimitGoodsModel> newpaginate(Integer pageNum, Integer pageSize, String keyword) {
		// TODO Auto-generated method stub
		String sql=" from fish_mall_limited_goods as g "
				+ "left join fish_mall_limited as t on g.limitedId=t.id "
				+ "left join fish_goods as fg on g.goodsId=fg.id where fg.isClosed=0";
		if(StringUtils.isNotEmpty(keyword)){
			sql+=" and title like '%" + keyword + "%' ";
		}
		
		sql+=" order by t.startTime";
		return dao.paginate(pageNum, pageSize, "select g.*,fg.title,t.startTime,t.endTime", sql);
	}
	/**
	 * 主要获取的是一些图文信息，商品的详细参数还有另外获取
	 * @Description 获取具体参与商品的信息  此处的LREMAIN表示限购商品中的剩余量，因为fish_goods中还存在remain
	 * @author hmilysean
	 * @date 2015年12月23日 下午3:11:00
	 */
		public LimitGoodsModel ViewlimitGoods(int limitId,int goodsId){
			String sql="select lg.*,lg.remain as lremain,g.*,u.username,u.avatar,u.address from fish_mall_limited_goods as lg "
					+ "left join fish_goods as g on g.id=lg.goodsId "
					+ "left join sec_user as u on u.id=g.shopId "
					+ "where lg.limitedId=? and lg.goodsId=? and g.isClosed=0";
			
			return dao.findFirst(sql,limitId,goodsId);
			
		}
		
		public LimitGoodsModel editLimitGoods(int limitId,int goodsId){
			String sql="select lg.*,g.title,lt.* from fish_mall_limited_goods as lg "
					+ "left join fish_goods as g on lg.goodsId=g.id "
					+ "left join fish_mall_limited as lt on lt.id=lg.limitedId "
					+ "where limitedId=? and goodsId=? and g.isClosed=0";
			
			return dao.findFirst(sql,limitId,goodsId);	
		}
		
		/**
		 * 
		 * @Description  手机端接口获取商品列表
		 * @author hmilysean
		 * @date 2015年12月24日 下午12:54:00 
		 * @param pageNum
		 * @param pageSize
		 * @param keyword
		 * @return
		 */
		public Page<LimitGoodsModel> Mobilepaginate(Integer pageNum, Integer pageSize, int limitId) {
			// TODO Auto-generated method stub
			
			String sql=" from fish_mall_limited_goods as g "
					+ "left join fish_mall_limited as t on g.limitedId=t.id "
					+ "left join fish_goods as fg on g.goodsId=fg.id "
					+" left join fish_goods_pic as gp on fg.id=gp.goodsId "
					+ "left join sec_user as u on fg.shopId=u.id "
					+ " where g.limitedId="+limitId+ " and fg.isClosed=0";
		
			
			return dao.paginate(pageNum, pageSize, "select g.*,g.remain as lremain,fg.*,t.startTime,t.endTime,gp.pic,u.nickname,u.avatar,u.address", sql);
		}
		/**
		 * 
		 * @Description 对库存的操作  type=0 减，type=1加
		 * @author hmilysean 
		 * @date 2015年12月25日 下午1:37:23 
		 * @param limitId
		 * @param goodsId
		 */
		public void updateRemain(int limitId,int goodsId,int type){
			
			String sql="update fish_mall_limited_goods set remain=";
			if(type==0)
				sql+="remain-1";
			else
				sql+="remain+1";
			String sql2=" where limitedId=? and goodsId=?";
			Db.update(sql+sql2,limitId,goodsId);
		}
}
