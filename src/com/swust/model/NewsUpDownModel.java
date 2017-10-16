package com.swust.model;


import com.jfinal.plugin.activerecord.Model;

public class NewsUpDownModel extends Model<NewsUpDownModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1L;
	public static NewsUpDownModel dao = new NewsUpDownModel();

	/**
	 * 处理为一旦顶过或踩过后不能再修改
	 * @param newsId 新闻id
	 * @param userId 用户id
	 * @return 0:已经顶过  1:新顶的  2:更改踩为顶
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
		new  NewsUpDownModel().set("newsid", newsId).set("uid", userId).set("ud", 1).save();	 
		return 1;
	}

	/**
	 * @param userId 用户id
	 * @param newsId 新闻id
	 * @return 是否被顶过或踩过
	 */
	public boolean isUpOrDown(int userId,int newsId) {
		return null != dao.findFirst("select ud from fish_news_updown where newsid=? and uid=?",newsId,userId);
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
		new  NewsUpDownModel().set("newsid", newsId).set("uid", userId).set("ud", -1).save();	
		return 1;
	}
	
	

}
