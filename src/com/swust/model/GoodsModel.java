package com.swust.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

/**
 * 商品管理
 * @Description 
 * @author inging44
 * @date 2015年12月27日 下午3:59:04 
 * @version V0.1
 */
public class GoodsModel extends Model<GoodsModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static GoodsModel dao=new GoodsModel();
	
	
	public Page<GoodsModel> paginateByCatId(int pageNumber,int pageSize,int catId,int selled,int priceOrder,String title) {
		System.out.println(title);
		String select = "select g.id,g.title,g.cover,g.priceRange,u.realname as shopName";
		String sql = "from fish_goods g left join sec_user u on u.id=g.shopId where (g.catId="+catId+" or g.catIdParent="+catId+" ) and g.enable=1 and g.isClosed=0 ";
		if(EduStringUtil.isNotEmpty(title)){
			System.out.println(title);
			sql += " and g.title like '%"+title+"%'";
		}
		if (selled!=-1) {
			sql += " order by g.selled desc";
		}else if(priceOrder==1){
			sql += " order by g.priceRange asc";
		}else if(priceOrder==2){
			sql += " order by g.priceRange desc";
		}else{
			sql += " order by g.displayorder";
		}
		System.out.println(sql);
		return paginate(pageNumber,pageSize,select,sql);
	}
	
	
	public List<GoodsModel> getSelfGoods(int uid){
		String sql="select title,id from fish_goods where shopId=? and enable=1 and isClosed=0";
		return dao.find(sql,uid);	
	}
	/**
	 * @category  商家商品
	 * @author inging44
	 * @date 2015年12月27日 下午3:38:27 
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @param uid
	 * @return
	 */
	public Page<GoodsModel> paginateByShopId(int pageNumber,int pageSize,String title,Integer uid) {
		String select = "select g.*,u.realname as shopName,cat1.name as pcatName,cat2.name as catName";
		String sql = "from fish_goods g left join sec_user u on u.id=g.shopId "
				+ "left join fish_goods_category cat1 on cat1.id=g.catIdParent "
				+ "left join fish_goods_category cat2 on cat2.id=g.catId "
				+ "where g.shopId=? and g.isClosed=0";
		if (StringUtils.isNotEmpty(title)) {
			sql += " and title like '%"+title+"%' ";
		}
		sql += " order by dateline desc";
		return paginate(pageNumber,pageSize,select,sql,uid);
	}
	
	/**
	 * @category 待审核  
	 * @author inging44
	 * @date 2015年12月27日 下午3:58:23 
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public Page<GoodsModel> paginateUnchecked(int pageNumber,int pageSize,String title) {
		String select = "select g.*,u.realname as shopName,cat1.name as pcatName,cat2.name as catName";
		String sql = "from fish_goods g left join sec_user u on u.id=g.shopId "
				+ "left join fish_goods_category cat1 on cat1.id=g.catIdParent "
				+ "left join fish_goods_category cat2 on cat2.id=g.catId "
				+ "where g.enable=0 and g.isClosed=0";
		if (StringUtils.isNotEmpty(title)) {
			sql += " and g.title like '%"+title+"%' ";
		}
		sql += " order by g.dateline desc";
		return paginate(pageNumber,pageSize,select,sql);
	}
	
	/**
	 * @category  已审核
	 * @author inging44
	 * @date 2015年12月27日 下午3:58:39 
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public Page<GoodsModel> paginateChecked(int pageNumber,int pageSize,String title) {
		String select = "select g.*,u.realname as shopName,cat1.name as pcatName,cat2.name as catName";
		String sql = "from fish_goods g left join sec_user u on u.id=g.shopId "
				+ "left join fish_goods_category cat1 on cat1.id=g.catIdParent "
				+ "left join fish_goods_category cat2 on cat2.id=g.catId "
				+ "where g.enable<>0 and g.isClosed=0";
		if (StringUtils.isNotEmpty(title)) {
			sql += " and g.title like '%"+title+"%' ";
		}
		sql += " order by g.dateline desc";
		return paginate(pageNumber,pageSize,select,sql);
	}
	
	/**
	 * @category 查看商品非关键参数
	 * @Description 主要用于点击商品时，查看商品的详细参数
	 * @return 
	 * @date 2015年12月25日 下午2:16:13
	 */
	public Map<String, String> getGoodsNonkeytParams(int goodsid){
		List<CatNonkeytValModel> paras = CatNonkeytValModel.dao.findByGoodsId(goodsid);
		Map<String,String> attribute=new HashMap<String,String>();
		for(CatNonkeytValModel para : paras){
			String value = para.getStr("value");
			Integer paraId = para.getInt("paramsId");
			String name = "";
			if(paraId!=0){
				if(CatNonkeytModel.dao.findById(paraId)!=null){
					name = CatNonkeytModel.dao.findById(paraId).getStr("name");
				}
			}else{
				name = para.getStr("name");
			}
			attribute.put(name,value);
		}
		return attribute;
	}
	
	/**
	 * @category 商品详情 
	 * @author inging44
	 * @date 2016年1月7日 下午7:13:40 
	 * @param gid
	 * @return
	 */
	public GoodsModel findDetailById(int gid){
		String sql = "select g.*,c1.name as catName,c2.name as catParentName,"
				+ "sc.shopName,sc.picture as shopAvatar,sc.goodsNum as shopTotalGoodsNum,"
				+ "sc.postagePrice,sc.uid as shopId,sc.nonPostage,sc.postagePrice "
				+ "from fish_goods g "
				+ "left join fish_goods_category c1 on c1.id=g.catId "
				+ "left join fish_goods_category c2 on c2.id=g.catIdParent "
				+ "left join fish_shop_count sc on sc.uid=g.shopId "
				+ "where g.id=?";
		return findFirst(sql,gid);
	}
}
