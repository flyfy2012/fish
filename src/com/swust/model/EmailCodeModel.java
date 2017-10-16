package com.swust.model;

import com.jfinal.plugin.activerecord.Model;

public class EmailCodeModel extends Model<EmailCodeModel>{
	private static final long serialVersionUID = 1L;
	public static EmailCodeModel dao= new EmailCodeModel();
	
	public EmailCodeModel findByEmailAndCode(String email,String code){
		String sql = "select * from fish_user_emailcode where email=? and code=? and now() < date_add(createTime,interval 0.5 hour)";
		return findFirst(sql,email,code);
	}

}
