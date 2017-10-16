package com.swust.model;

import java.util.ArrayList;
import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 商品类别管理
 * @Description 
 * @author inging44
 * @date 2015年12月25日 下午7:34:00 
 * @version V0.1
 */
public class GoodsCategoryModel extends Model<GoodsCategoryModel>{
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8805398235529058150L;
	public static GoodsCategoryModel dao=new GoodsCategoryModel();
	
	/**
	 * @category 获取第一级类别 
	 * @author inging44
	 * @date 2015年12月24日 下午12:47:14 
	 * @return
	 */
	public List<GoodsCategoryModel> getFirst(){
		String sql = "select * from fish_goods_category where parentId=0";
		return find(sql);
	}
	
	/**
	 * @category 根据pid获取下级列表 
	 * @author inging44
	 * @date 2015年12月24日 下午12:47:33 
	 * @param pid
	 * @return
	 */
	public List<GoodsCategoryModel> getNext(int pid){
		String sql = "select * from fish_goods_category where parentId=?";
		return find(sql,pid);
	}
	
	/**
	 * @category  获取当前父类别的所有子类id
	 * @author inging44
	 * @date 2015年12月24日 下午12:57:41 
	 * @param pid
	 * @return
	 */
	public ArrayList<Integer> getNextIds(int pid){
		String sql = "select * from fish_goods_category where parentId="+pid;
		List<GoodsCategoryModel> list = GoodsCategoryModel.dao.find(sql);
		ArrayList<Integer> ids = new ArrayList<Integer>();
		if(list!=null){
			for(GoodsCategoryModel category:list){
				ids.add(category.getInt("id"));
			}
		}
		return ids;
	}
	
	/**
	 * @category 获取最大level
	 * @author inging44
	 * @date 2015年12月25日 下午7:33:30 
	 * @return
	 */
	public int getLevel(){
		String sql = "select max(level) as level from fish_goods_category";
		return findFirst(sql).getInt("level");
	}
	
	/**
	 * @category 级联删除 
	 * @author inging44
	 * @date 2015年12月25日 下午7:33:18 
	 * @param parentId
	 */
	public void deleteCasecade(int parentId){
		String sql = "delete from fish_goods_category where parentId=?";
		Db.update(sql,parentId);
	}
}
