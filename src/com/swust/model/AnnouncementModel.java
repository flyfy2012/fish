package com.swust.model;

import java.util.ArrayList;
import java.util.List;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;
/**
 * 公告表
 * @Description 
 * @author inging44
 * @date 2015年11月13日 下午8:28:20 
 * @version V0.1
 */
public class AnnouncementModel extends Model<AnnouncementModel>{
	private static final long serialVersionUID = 1L;
	public static AnnouncementModel dao= new AnnouncementModel();
	
	public Page<AnnouncementModel> paginate (int pageNumber, int pageSize, String name) {
		String sql = "from tech_announcement";
		if(!EduStringUtil.isEmpty(name)){
			sql+=" where message like '%"+name+"%'";
		}
		sql += " order by createTime";
		return paginate(pageNumber, pageSize, "select *", sql);
	}
	/**
	 * 获取公告列表
	 * @Description 
	 * @author inging44
	 * @return
	 */
	public ArrayList<Integer> getAll(){
		String sql = "select * from tech_announcement";
		List<AnnouncementModel> list = AnnouncementModel.dao.find(sql);
		ArrayList<Integer> ids = new ArrayList<Integer>();
		if(list!=null){
			for(AnnouncementModel announcement:list){
				ids.add(announcement.getInt("id"));
			}
		}
		return ids;
	}
	/**
	 * 获取最新有效公告
	 * @Description 
	 * @author inging44
	 * @return
	 */
	public AnnouncementModel get(){
		String sql = "select * from tech_announcement where enable=1 order by createTime";
		return findFirst(sql);
	}
	
}
