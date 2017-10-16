package com.swust.model;



import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.swust.utils.EduStringUtil;

public class MessageModel extends Model<MessageModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 8837400560386383467L;
	public static MessageModel dao=new MessageModel();
	
	/**
	 * 
	 * @Description web端获取消息列表
	 * @author hmilysean
	 * @date 2016年1月25日 下午5:14:17 
	 * @param pageSize
	 * @param pageNum
	 * @param name
	 * @return
	 */
	public Page<MessageModel> newpaginate(int pageSize,int pageNum,String name)
	{
		String sqlExceptSelect=" from fish_message as m "
				+ "left join sec_user as su on m.uid=su.id "
				+ "left join sec_user as uu on m.touid=uu.id ";
				
		if(EduStringUtil.isNotEmpty(name)){
			sqlExceptSelect+=" where uu.nickname like '%"+name+"%' ";
		}
		
		sqlExceptSelect+=" order by dateline desc";
		String select="select m.*,su.nickname as fname,su.avatar as favatar,uu.nickname as toname,uu.avatar as toavatar ";
		return paginate(pageNum, pageSize, select, sqlExceptSelect);
	}
	/**
	 * 
	 * @Description   获取具体某条消息的内容
	 * @author hmilysean
	 * @date 2016年1月25日 下午5:13:57 
	 * @param mid
	 * @return
	 */
	public MessageModel getDetail(int mid){
		String sql="select m.*,su.nickname as fname,su.avatar as favatar,uu.nickname as toname,uu.avatar as toavatar "
				+" from fish_message as m "
				+ "left join sec_user as su on m.uid=su.id "
				+ "left join sec_user as uu on m.touid=uu.id "
				+ "where m.id=?";
		return dao.findFirst(sql,mid);
				
	}
	
	/**
	 * 
	 * @Description 获取用户自己的消息列表
	 * @author hmilysean
	 * @date 2016年1月25日 下午5:26:54 
	 * @param uid
	 * @returngetParaToInt("pageNumber",1),getParaToInt("pageSize",20)
	 */
	public Page<MessageModel> getMymessage(int uid,int pageNumber,int pageSize){
		
		String select="select m.*,fu.nickname as fname,fu.avatar as favatar " ;
		String sqlExceptSelect= " from fish_message as m "
				+ "left join sec_user as u on m.touid=u.id "
				+ "left join sec_user as fu on m.uid=fu.id "
				+ "where m.touid="+uid
				+ " order by dateline desc";
		return paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}
	
}
