package com.swust.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class HarvestPicturesModel extends Model<HarvestPicturesModel>{

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -7340235287209797561L;
	public static HarvestPicturesModel dao=new HarvestPicturesModel();
	
	
	public List<HarvestPicturesModel> getPictureByHid(int hid){
		String sql="select * from fish_harvest_pictures where hid=?";
		return dao.find(sql,hid);
	}
}
