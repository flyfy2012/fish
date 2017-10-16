package com.swust.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

public class SeckillGoodsModel extends Model<SeckillGoodsModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 6281260515212549360L;
	public static SeckillGoodsModel dao =new SeckillGoodsModel();
	
	
	public Page<SeckillGoodsModel> newpaginate(Integer pageNum, Integer numPerPage, String title) {
		// TODO Auto-generated method stub
		String sql=" from fish_mall_seckill_goods as sg "
				+ "left join fish_goods as fg on sg.goodsId=fg.id "
				+ "left join sec_user as u on fg.shopId=u.id "
				+ "left join fish_mall_seckill as s on s.id=sg.seckillId where fg.isClosed=0";
		
		if(EduStringUtil.isNotEmpty(title)){
			sql+=" and fg.title like '%"+title+"%'";
		}
		
		sql+=" order by s.time";
		return dao.paginate(pageNum, numPerPage, "select sg.*,sg.remain as sremain,fg.*,u.username,u.avatar,u.address,s.*", sql);
	}

	/**
	 * 
	 * @Description  主要获取的是一些图文信息，商品的详细参数还有另外获取
	 * @author hmilysean
	 * @date 2015年12月25日 下午2:23:01 
	 * @param sid
	 * @param gid
	 * @return
	 */
	public SeckillGoodsModel ViewSeckillGoods(Integer sid, Integer gid) {
		String sql="select lg.*,lg.remain as sremain,g.*,u.username,u.avatar,u.address from fish_mall_seckill_goods as lg "
				+ "left join fish_goods as g on g.id=lg.goodsId "
				+ "left join sec_user as u on u.id=g.shopId "
				+ "where lg.seckillId=? and lg.goodsId=? and g.isClosed=0";
		
		return dao.findFirst(sql,sid,gid);
	}


	public SeckillGoodsModel editLimitGoods(Integer sid, Integer gid) {
		// TODO Auto-generated method stub
		String sql="select lg.*,g.title,lt.* from fish_mall_seckill_goods as lg "
				+ "left join fish_goods as g on lg.goodsId=g.id "
				+ "left join fish_mall_seckill as lt on lt.id=lg.seckillId "
				+ "where seckillId=? and goodsId=? and g.isClosed=0";
		
		return dao.findFirst(sql,sid,gid);
	}
		
	
	/**
	 * 
	 * @Description 手机端获取秒杀商品列表
	 * @author hmilysean
	 * @date 2015年12月24日 下午1:22:49 
	 * @param pageNum
	 * @param numPerPage
	 * @param seckillId
	 * @return
	 */
	public Page<SeckillGoodsModel> Mobilepaginate(Integer pageNum, Integer numPerPage, int seckillId) {
		// TODO Auto-generated method stub
		String sql=" from fish_mall_seckill_goods as sg "
			    + "left join fish_mall_seckill as s on s.id=sg.seckillId "
				+ "left join fish_goods as fg on sg.goodsId=fg.id "
				+ "left join fish_goods_pic as gp on gp.goodsId=fg.id "
				+ "left join sec_user as u on fg.shopId=u.id "
				+ " where seckillId="+seckillId
				+" and fg.isClosed=0";
		
		return dao.paginate(pageNum, numPerPage, "select sg.*,sg.remain as sremain,fg.*,u.nickname,u.avatar,u.address,s.time,gp.pic", sql);
	}
}
