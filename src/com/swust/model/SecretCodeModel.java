package com.swust.model;

import com.jfinal.plugin.activerecord.Model;

public class SecretCodeModel extends Model<SecretCodeModel>{
	private static final long serialVersionUID = 1L;
	public static SecretCodeModel dao= new SecretCodeModel();
	
	public SecretCodeModel findByEmailAndCode(String email,String code){
		String sql = "select * from fish_user_emailcode where email=? and code=? and now()>DATEADD(hour,0.5,createTime)";
		return findFirst(sql,email,code);
	}

}
