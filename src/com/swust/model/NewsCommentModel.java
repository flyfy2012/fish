package com.swust.model;



import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


public class NewsCommentModel extends Model<NewsCommentModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1490736817415095490L;
	public static NewsCommentModel dao = new NewsCommentModel();

	/**
	 * 根据新闻Id来获得评论
	 */
	public Page<NewsCommentModel> getCommentsByNews(int newsId,int pageNumber,int pageSize) {
		System.out.println(newsId+"---"+pageNumber+"---"+pageSize);
		String sqlExceptSelect = " from fish_news_comment n "
				+ "left join sec_user u2 on n.touid=u2.id "
				+ " left join sec_user u on n.uid = u.id  where n.newsid="+newsId
				+" order by n.dateline desc";
		System.out.println(sqlExceptSelect);
		return paginate(pageNumber, pageSize, "select n.*,u.avatar,u.nickname,u2.nickname as tousername ", sqlExceptSelect);
	}
	
	
	public void deleteAllComentByNewsId(Integer Id) {
		 Db.update("delete from fish_news_updown where newsid = ? ",Id);
		 Db.update("delete from fish_news_comment where newsid = ? ",Id);
	}
	
	/**
	 * 
	 * @Description 处理对评论的顶
	 * @author hmilysean
	 * @date 2015年12月20日 下午2:59:50 
	 * @param userId
	 * @param newsId
	 * @return
	 */
	
	
	public int up(int userId,int newsId) {
		//		if (isUpOrDown(userId,newsId)) {
		//			//为1仅代表找到了符合where条件的行数并在这些行上成功执行了excuteUpdate,与实际的更新数无关
		//			if  (1 == Db.update("update oa_news_updown set ud=1  where newsid=? and uid=? and ud!=1", newsId,userId)) {
		//				return 2;
		//			} 
		//			return 0;
		//		}
		if (isUpOrDown(userId, newsId))
			return 0;
		else 
		return 1;
	}

	/**
	 * @param userId 用户id
	 * @param newsId 新闻id
	 * @return 是否被顶过或踩过
	 */
	public boolean isUpOrDown(int userId,int newsId) {
		return null != dao.findFirst("select ud from fish_news_comment_updown where newsCommentId=? and uid=?",newsId,userId);
	}


	/**
	 * 处理为一旦顶过或踩过后不能再修改
	 * @param newsId 新闻id
	 * @param userId 用户id
	 * @return 0:已经踩过  1:新踩的  2:更改顶为踩
	 */
	public int down(int userId,int newsId) {
		//		if (isUpOrDown(userId,newsId)) {
		//			//为1仅代表找到了符合where条件的行数并在这些行上成功执行了excuteUpdate,与实际的更新数无关
		//			if  (1 == Db.update("update oa_news_updown set ud=-1  where newsid=? and uid=? and ud!=-1", newsId,userId)) {
		//				return 2;
		//			}
		//			return 0;
		//		}
		if (isUpOrDown(userId, newsId))
			return 0;
		else
			return 1;
	}
	/**
	 * 
	 * @Description 用户是否评论过此新闻
	 * @author hmilysean
	 * @date 2015年12月27日 上午11:21:04 
	 * @param userId
	 * @param newsId
	 * @return
	 */
	public boolean iscommented(int userId,int newsId) {
		return null != dao.findFirst("select content from fish_news_comment where newsid=? and uid=?",newsId,userId);
	}
	

}
