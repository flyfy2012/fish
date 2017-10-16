package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

public class FubiBannerModel extends Model<FubiBannerModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 6844015085675621596L;
	public static FubiBannerModel dao=new FubiBannerModel();
	
	
	public Page<FubiBannerModel> newpaginate(Integer pagenum, Integer pagesize,String keyword) {
		String sql=" from fish_fubi_mall_banner as b "
				+ "left join sec_user as u on b.uid=u.id "
				+ "left join fish_goods as g on b.gid=g.id";
		if(EduStringUtil.isNotEmpty(keyword)){
			sql += " where g.title like '%"+keyword+"%'";
		}
		
		sql+=" order by b.createTime desc";
		return paginate(pagenum, pagesize, "select b.*,u.username,u.avatar,g.title", sql);
	}
	public FubiBannerModel BannerDetail(int  bid){
		String sql="select b.*,g.title from fish_fubi_mall_banner as b "
				+ "left join fish_goods as g on b.gid=g.id "
				+ "where b.id=?";
		
		return dao.findFirst(sql,bid);	
	}
	
	public FubiBannerModel viewBanner(int  bid){
		String sql="select b.*,g.id as goodsid,g.title,u.username,u.avatar from fish_fubi_mall_banner as b"
				+ " left join fish_goods as g on b.gid=g.id "
				+ "left join sec_user as u on b.uid=u.id "
				+ " where b.id=?";
		
		return dao.findFirst(sql,bid);	
	}
	
	/**
	 * 
	 * @Description  手机端 获取富币Banner列表
	 * @author hmilysean
	 * @date 2016年1月13日 上午10:02:34 
	 * @return
	 */
	public List<FubiBannerModel> getBannerList(){
		String sql="select b.*,u.username,u.avatar,g.title from fish_fubi_mall_banner as b "
				+ "left join sec_user as u on b.uid=u.id "
				+ "left join fish_goods as g on b.gid=g.id";
		return dao.find(sql);

	}

}
