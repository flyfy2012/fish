package com.swust.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

public class NewsModel extends Model<NewsModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static NewsModel dao = new NewsModel();

	/**
	 * 
	 * @Description  后台获取新闻，可以查看自发布的，高级人员，grouptype=0可以查看所有的
	 * @author hmilysean
	 * @date 2015年12月21日 上午11:00:05 
	 * @param pageNumber
	 * @param pageSize
	 * @param name
	 * @param isAdvance
	 * @param uid
	 * @return
	 */
	public Page<NewsModel> newsPaginate(int pageNumber, int pageSize,
			String title,boolean isAdvance, Integer uid) {

		String sql = "from fish_news as news left join fish_news_category as category  on news.catid = category.id "
				+ "left join sec_user as u on u.id=news.uid where 1=1 "
				;
		if (isAdvance == true) {
//			sql += " or news.verify = 0 ";
		}
		else{
			sql += " and uid = " + uid;
		}

		if (StringUtils.isNotEmpty(title)) {
			sql += " and news.title like '%" + title + "%' ";
		}
		sql += " order by id desc";
		
		return paginate(
				pageNumber,
				pageSize,
				"select news.id,news.verify,news.catid,title,dateline,username,avatar,comments,category.catname ",
				sql);
	}

	/**
	 * 
	 * @Description   手机端    获取 通过审核的新闻，可以通过catid来查找 
	 * @author hmilysean
	 * @date 2015年12月21日 上午10:07:02 
	 * @param pageNumber
	 * @param pageSize
	 * @param catid
	 * @return
	 */
	public Page<NewsModel> newsPaginate(int pageNumber, int pageSize,
			int catid,int type) {
		
		String sql = "from fish_news as news left join fish_news_category as category  on news.catid = category.id "
				+ "left join sec_user as user on user.id=news.uid "
				+ "where news.verify = 1 ";
			
		if (catid!=0) {
			sql += " and catid=" + catid;
		}
		
		if(type==1){
			sql+=" and news.recommend=1"; //获取推荐资讯
		}

		
		sql += " order by dateline desc";
		return paginate(
				pageNumber,
				pageSize,
				"select news.*,user.nickname as username,user.avatar,category.catname ",
				sql);
	}

	public NewsModel get(Integer id) {
		return NewsModel.dao
				.findFirst(
						"select news.*,u.nickname,u.avatar,category.catname  from fish_news as news left join fish_news_category as category on news.catid = category.id "
						+ "left join sec_user as u on news.uid=u.id "
						+ "where news.id=?",
						id);
	}

	/**
	 * 获取今日新闻 hsongjiang 2015-04-04
	 */
	public List<NewsModel> getTodayNews(Integer uid, boolean type) {
		if (type) { // 管理员
			return NewsModel.dao
					.find("select news.*,category.catname  from fish_news as news left join fish_news_category as category on news.catid = category.id  order by id desc limit 5 ");
		} else {
			return NewsModel.dao
					.find("select news.*,category.catname  from fish_news as news left join fish_news_category as category on news.catid = category.id where news.type = 1  or  uid = ? order by id desc limit 5",
							uid);
		}
	}
	
	
	/**
	 * 
	 * @Description 获取等待审核的新闻  verify=0
	 * @author hmilysean
	 * @date 2015年12月21日 上午9:48:09 
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public Page<NewsModel> waitingCheckList(int pageNumber,int pageSize,String title) {
		String sql = "from fish_news as n  "
				+ "left join sec_user as user on n.uid=user.id"
				+ " where n.verify=0"
				;
		if(EduStringUtil.isNotEmpty(title)){
			sql += " and n.title like '%"+title+"%'";
		}
		sql += " order by n.id desc";
		return paginate(pageNumber,pageSize,"select n.*,user.username,user.avatar ",sql);
	}
	
	/**
	 * 
	 * @Description 获取已经审核过的新闻，包括审核通过和未通过
	 * @author hmilysean
	 * @date 2015年12月21日 上午10:08:55 
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @return
	 */
	public Page<NewsModel> alreadyCheckList(int pageNumber,int pageSize,String title) {
		String sql = "from fish_news as news "
				+ "left join sec_user as user on news.uid=user.id"
				+ " left join fish_news_category as cat on news.catid=cat.id"
				+ " where (news.verify=1 or news.verify=2)";
		if(EduStringUtil.isNotEmpty(title)){
			sql += " and news.title like '%"+title+"%'";
		}
		sql += " order by news.id desc";
		System.out.println(sql);
		return paginate(pageNumber,pageSize,"select news.*,user.username,user.avatar,cat.catname ",sql);
	}
	
	
	/**
	 * 
	 * @Description 获取新闻分类的列表 
	 * @author hmilysean
	 * @date 2015年12月20日 上午11:53:47 
	 * @param id
	 * @return 
	 * @return
	 */
	public  List<NewsModel> getCat() {
		String  sql="select * from fish_news_category where id !=1";
		return NewsModel.dao.find(sql);
				
	}
	
	
	
	public Page<NewsModel> getUserCollect(int uid,int pageNumber,int pageSize,String title){
		String select = "select n.id,n.title,n.dateline as createTime,c.dateline as collectTime,u.avatar,u.nickname,u.id as uid,u.verify ";
		String sql = "from fish_user_collect c "
				+ "left join fish_news n on c.tid=n.id "
				+ "left join sec_user u on u.id=n.uid where c.uid="+uid+" and n.id is not null";
		if(EduStringUtil.isNotEmpty(title)){
			sql += " and n.title like '%"+title+"%'";
		}
		System.out.println(select+sql);
		return paginate(pageNumber, pageSize, select, sql);
	}

	/**
	 * @category 获取推广列表
	 * @Description 获取推广列表
	 * @author hmilysean
	 * @date 2015年12月21日 下午4:32:30 
	 * @return
	 */
	public List<NewsModel> getSpread(){
		String sql="select n.*,u.nickname as username,u.avatar from fish_news as n "
				+ "left join sec_user as u on u.id=n.uid "
				+ "where catid=1 and n.verify=1 order by id desc limit 2";
		
		return dao.find(sql);
	}

	/**
	 * @category 获取热点新闻
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月21日 下午6:48:15 
	 * @return
	 */
	
	public Page<NewsModel> getHot(int pageNum,int pageSize){
		String sql=" from fish_news as n "
				+ "left join sec_user as u on u.id=n.uid "
				+ "where n.verify=1 order by n.view desc ";
		
		return paginate(pageNum, pageSize, "select n.*,u.nickname as username", sql);
		
	}
	
	/**
	 * @category  获取相关新闻列表
	 * @Description 
	 * @author hmilysean
	 * @date 2015年12月21日 下午7:00:31 
	 * @return
	 */
	public List<NewsModel> getNewsGroup(int newsid){
		String sql1="select catid as catid from fish_news  where id=?";
		NewsModel cat = dao.findFirst(sql1,newsid);
		int catid = cat.getInt("catid");
		
		String sql2="select n.*,u.nickname,u.avatar from fish_news as n "
				+ "left join sec_user as u on u.id=n.uid "
				+ "where n.verify=1 and catid=? order by n.view desc limit 2";
		
		return dao.find(sql2,catid);
		
	}
	
}
