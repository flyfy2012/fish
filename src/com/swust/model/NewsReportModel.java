package com.swust.model;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class NewsReportModel extends Model<NewsReportModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -3499646469573032747L;
    public static NewsReportModel dao=new NewsReportModel();
    
    public Page<NewsReportModel> newsPaginate(int pageNumber, int pageSize,
			String name) {

		String sql = "from fish_news_report as report left join fish_news as news on report.newsid = news.id "
				+ "left join sec_user as user on report.uid=user.id";

		if (StringUtils.isNotEmpty(name)) {
			sql += " and title like '%" + name + "%' ";
		}
		sql += " order by id desc";
		return paginate(
				pageNumber,
				pageSize,
				"select report.*,user.username ",
				sql);
	}
    
    public NewsReportModel viewReport(int rid){
		String sql="select report.*,u.nickname from fish_news_report as report "
				+ " left join sec_user as u on report.uid= u.id "
				+ "where report.id=?";
    	
    	return dao.findFirst(sql,rid);
    	
    }
}
