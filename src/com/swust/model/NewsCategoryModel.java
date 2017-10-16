package com.swust.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class NewsCategoryModel extends Model<NewsCategoryModel>{

	private static final long serialVersionUID = 1L;
	public static NewsCategoryModel dao = new NewsCategoryModel();
	
	/**
	 *  获取新闻分类的信息
	 */
	public Page<NewsCategoryModel> paginate(int pageNumber,int pageSize,String name) {
		String sql = "from fish_news_category  ";
		if(StringUtils.isNotEmpty(name)) {
			sql += " where catname like '%" + name + "%' ";
		}
		sql += " order by displayorder asc";
		return paginate(pageNumber, pageSize, "select * ", sql);		
	}

}
