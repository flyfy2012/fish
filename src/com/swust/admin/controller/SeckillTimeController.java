package com.swust.admin.controller;

import java.util.List;

import com.jfaker.app.web.CommonController;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.bjui.BjuiRender;

/**
 * @category 整点秒杀时间设置
 * @Description 
 * @author hmilysean 
 * @date 2015年12月23日 下午4:38:31 
 * @version V0.1
 */
public class SeckillTimeController extends CommonController{
	public void index(){
		String sql="select * from fish_mall_seckill order by time";
		List<Record> seckill = Db.find(sql);
		
		setAttr("page", seckill);
		render("seckillTime.html");
	}
	
	public void add(){
		render("seckillTimeadd.html");
	}
	
	public void save(){
		String time = getPara("time");
		Record record=new Record()
				.set("time", time);
		Db.save("fish_mall_seckill", record);
		render(BjuiRender.closeCurrentAndRefresh("seckill").message("添加成功"));
		
	}
	
	public void delete(){
		Integer tid = getParaToInt(0,-1);
		String sql="delete from fish_mall_seckill where id=?";
		Db.update(sql, tid);
		render(BjuiRender.refresh("seckill"));
	}
	
	
	
}
