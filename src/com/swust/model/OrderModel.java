package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

public class OrderModel extends Model<OrderModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 652740070493799893L;
	public static OrderModel dao=new OrderModel();
	
	
	public Page<OrderModel> paginate(int pageNumber,int pageSize,int shopId){
		String select = "select o.*,u.nickname,u.telephone,p.PackageId,p.totalPrice,p.size,p.hasPostage, p1.name as paramsName1,v1.value as paramsVal1,"
				+ "p2.name as paramsName2,v2.value as paramsVal2,g.title,g.cover ";
		String sql = "from fish_order o left join fish_order_package op on op.orderId=o.id "
				+ "left join sec_user u on o.uid=u.id "
				+ "left join fish_goods_keyt_params_value_goods vg on vg.id=o.goodsDetailId "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "left join fish_goods g on g.id=vg.goodsId "
				+ "left join fish_package p on p.id=op.orderPackageId where p.shopId=?";
		return paginate(pageNumber, pageSize,select,sql,shopId);
	}
	/**
	 * @category 用户待付款订单列表 
	 * @author inging44
	 * @date 2016年1月4日 下午3:42:01 
	 * @param uid
	 * @return
	 */
	public List<OrderModel> findOrderListByUidAndStatus(Integer status,int uid){
		String sql = "select o.*,p1.name as paramsName1,v1.value as paramsVal1,p2.name as paramsName2,"
				+ "v2.value as paramsVal2,g.title,g.cover,sc.shopName,sc.picture as shopAvatar, "
				+ "sc.nonPostage,sc.postagePrice from fish_order o "
				+ "left join fish_goods_keyt_params_value_goods vg on vg.id=o.goodsDetailId "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "left join fish_goods g on g.id=vg.goodsId "
				+ "left join fish_shop_count sc on sc.uid=g.shopId "
				+ "where o.uid=? and o.isClosed=0 and o.status<>-1 ";
		if(!status.equals(8)){
			if(status==3){
				sql += " and o.tuiStatus<>0 ";
			}else{
				sql += " and o.tuiStatus=0 and o.status="+status;
			}
		}
		sql += " order by o.dateline desc";
		return find(sql,uid);
	}
	
	public List<OrderModel> getByPackageId(int packageId){
		String sql = "select o.*,g.title "
				+ "from fish_order o "
				+ "left join fish_order_package op on op.orderId=o.id "
				+ "left join fish_goods_keyt_params_value_goods vg on vg.id= o.goodsDetailId "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "left join fish_goods g on g.id=vg.goodsId "
				+ "where op.orderPackageId=? and o.status<>-1";
		return find(sql,packageId);
	}
	
	/**
	 * 
	 * @Description 富币订单
	 * @author hmilysean
	 * @date 2016年1月7日 下午3:07:30 
	 * @param pageNumber
	 * @param pageSize
	 * @param shopId
	 * @return
	 */
	public Page<OrderModel> paginate(int pageNumber,int pageSize,int shopId,int type,String keyword){
		String select = "select o.*,u.nickname,u.telephone,p1.name as paramsName1,v1.value as paramsVal1,"
				+ "p2.name as paramsName2,v2.value as paramsVal2,g.title,g.cover ";
		String sql = "from fish_order o left join fish_order_package op on op.orderId=o.id "
				+ "left join sec_user u on o.uid=u.id "
				+ "left join fish_goods_keyt_params_value_goods vg on vg.id=o.goodsDetailId "
				+ "left join fish_goods_keyt_params_value v1 on v1.id=vg.pvId_1 "
				+ "left join fish_goods_keyt_params_value v2 on v2.id=vg.pvId_2 "
				+ "left join fish_goods_keyt_params p1 on v1.paramsId=p1.id "
				+ "left join fish_goods_keyt_params p2 on v2.paramsId=p2.id "
				+ "left join fish_goods g on g.id=vg.goodsId "
				+ " where o.shopId=? and isFubi=1" 
				;
		
		if(EduStringUtil.isNotEmpty(keyword)){
			sql+=" and g.title like '%" + keyword + "%'";
		}
		return paginate(pageNumber, pageSize,select,sql,shopId);
	}
	
}
