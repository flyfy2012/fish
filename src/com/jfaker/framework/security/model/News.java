package com.jfaker.framework.security.model;

import java.util.Date;
import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * @author liuge
 * 对新闻数据的操作
 */
public class News extends Model<News> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final News dao = new News();
	// 返回评论的体
	public List<News> findCommentsEntity(int id) {
		String sql = "select c.* from sec_newscomments as c join sec_news as s";
		if (id > 0)
			sql += " on s.id=" + id + " and c._id=s.id";
		return find(sql);
	}
	// 根据id找到新闻体
	public News findNewsEntity(int id) {
		return findById(id);
	}
	/**
	 * 在sec_newscomment表中添加评论字段
	 * @param id 评论新闻对应的id
	 * @param comments 评论内容
	 * @param date     评论时间
	 * @param author   平路论作者
	 * @return
	 */
	public boolean addCommmentsCol(int id, String comments,String author) {
		Date insertDate=new Date();
		String sql="insert into sec_newscomments(_id,comments,comment_date,commentor) values(?,?,?,?)";
		Object[] paras={id,comments,insertDate,author};
		int result=Db.update(sql, paras);
		if(result>0)
		     return true;
		else
			 return false;
	}

	/**
	 * 更新新闻评论的条数,顶，踩,
	 * @param id 表示要操作的新闻的id
	 * @param target 要操作的字段 如 news_commentcount news_top  news_down
	 * @param model  表示对条数的加或减 "-"表示减1操作 "+"表示加1操作
	 * @return boolean
	 */
	public boolean updateCommentcounts(int id, String target,String model) {
		 String sql="update sec_news set "+target+"=";
		 sql+="((select t."+target+" from (select "+target+",id from sec_news ) t where t.id=";
		 sql+=id+")"+model+1+")";
		 sql+=" where id="+id;
		 System.out.println( sql);
		 int result=Db.update(sql);
		   if(result>0) return true;
		       else     return false;
	}
	/**
	 * @param type 表示校级新闻或者是院级新闻 1表示校级新闻 2表示院级新闻
	 * @param pageNumber 显示新闻的页数
	 * @param pageSize   每页新闻的条数
	 * @return 
	 */
	public Page<News> findNewsListItem(int type, int pageNumber, int pageSize) {
		String select = "select *  ";
		String where = "from sec_news as s where s.news_type=" + type+ " order by s.news_date desc";
		return paginate(pageNumber, pageSize, select, where);
	}
	/**根据userId来找到其真是的名字 用于显示评论的作者
	 * @param UserID
	 * @return
	 */
	public String getUserNameByUserId(int UserID){
		User user=User.dao.findById(UserID, "realname");
		String userName=user.getStr("realname");
		if(userName!=null)
		   return userName;
		else
			return null;
	}
//	public List getNewsTopOrDown(int newsId,String...args){
//		StringBuffer sb= new StringBuffer();
//		for(String s:args){
//			sb.append(s);
//			sb.append(",");
//		}
//		sb.deleteCharAt(sb.lastIndexOf(","));
//		News result=dao.findById(newsId,sb.toString());
//		if(result==null) return null;
//		List<Long> list= new ArrayList<Long>();
//        for(String s:args){
//        	System.out.println(result.getLong(s));
//        	list.add(result.getLong(s));
//        }
//		return list;
//	}
}
