package com.swust.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;

public class FubidetailModel extends Model<FubidetailModel> {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -2487894616691640306L;
	public static FubidetailModel dao=new FubidetailModel();
	
	
	public boolean isSign(Integer uid) {
		// TODO Auto-generated method stub
		String sql="select max(dateline) as dateline from fish_fubi_detail where uid=? ";
		FubidetailModel r = dao.findFirst(sql,uid);
		Date record = r.getDate("dateline");
		if(record==null){
			return false;
		}
		
		 @SuppressWarnings("deprecation")
		String day = record.toLocaleString().split(" ")[0];
		 @SuppressWarnings("deprecation")
		String today=new Date().toLocaleString().split(" ")[0];
		if(day.equals(today)){
			return true;
		}
		return false;
		}
}
