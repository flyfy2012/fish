package com.swust.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class FubiModel extends Model<FubiModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -7345000770655647629L;
	public static FubiModel dao=new FubiModel();
	
	
	public Page<FubiModel> newpaginate(Integer pageNum, Integer numPerPage) {
		// TODO Auto-generated method stub
		String sql=" from fish_fubi_type";
		return dao.paginate(pageNum, numPerPage, "select *", sql);
	}
	
	public FubiModel EditType(int tid){
		String sql="select * from fish_fubi_type where id=?";
		return dao.findFirst(sql,tid);
		
	}
	
	public int getPoint(int fid){
		String sql="select points as point from fish_fubi_type where id=?";
		Record p = Db.findFirst(sql,fid);
		return p.getInt("point");
	}
	
}
