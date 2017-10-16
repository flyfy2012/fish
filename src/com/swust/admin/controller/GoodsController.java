package com.swust.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.bjui.BjuiRender;
import com.swust.cfg.Preference;
import com.swust.model.CatKeytModel;
import com.swust.model.CatKeytValModel;
import com.swust.model.CatNonkeytModel;
import com.swust.model.CatNonkeytValModel;
import com.swust.model.GoodsCategoryModel;
import com.swust.model.GoodsModel;
import com.swust.model.GoodsPicModel;
import com.swust.model.KeytValGoodsModel;
import com.swust.utils.EduStringUtil;
 /**
  * 后台商家的商品管理
  * @Description 
  * @author inging44
  * @date 2015年12月23日 下午8:49:19 
  * @version V0.1
  */
public class GoodsController extends CommonController{
	
	public  static final String  menuRel= "goodsList";
	/**
	 * @category  商品列表
	 * @author inging44
	 * @date 2015年12月23日 下午8:49:34
	 */
	public void index() {
		int shop_id = ShiroUtils.getUserId();
		if(shop_id==-1){
			render(BjuiRender.error("请商家登录"));
			return;
		}
		String title = getPara("title","").trim();
		setAttr("title",title);
		setAttr("page", GoodsModel.dao.paginateByShopId(getParaToInt("pageCurrent",1),
				getParaToInt("pageSize",Preference.PAGE_PER_SIZE),title,shop_id));
		render("goodsList.html");
	}

	/**
	 * @category  商品添加
	 * @author inging44
	 * @date 2015年12月27日 下午6:42:44
	 */
	public void add() {
		setAttr("f_list", GoodsCategoryModel.dao.getNext(0));
		render("goodsAdd.html");
	}

	/**
	 * @category 商品查看 
	 * @author inging44
	 * @date 2015年12月27日 下午6:42:58
	 */
	public void view() {
		int goodsId = getParaToInt(0,0);
		if(goodsId==0){
			render(BjuiRender.error("请选择商品！"));
			return;
		}
		setAttr("pics",GoodsPicModel.dao.findByGoodsId(goodsId));
		setAttr("goods", GoodsModel.dao.findDetailById(goodsId));
		setAttr("keytList",KeytValGoodsModel.dao.findValuesByGoodsId(goodsId));
		setAttr("nonkeytList",CatNonkeytValModel.dao.findAllByGoodsId(goodsId));
		render("goodsView.html");
	}

	/**
	 * @category 商品查看 
	 * @author inging44
	 * @date 2015年12月27日 下午6:43:10
	 */
	@SuppressWarnings("unchecked")
	public void edit() {
		int goodsId = getParaToInt(0,0);
		GoodsModel goods = GoodsModel.dao.findDetailById(goodsId);
		if(goodsId==0||goods==null){
			render(BjuiRender.error("请选择商品！"));
			return;
		}
		
		List<KeytValGoodsModel> keytList = KeytValGoodsModel.dao.findValuesByGoodsId(goodsId); //商品对应的关键参数
		List<CatNonkeytValModel> nonkeytList = CatNonkeytValModel.dao.findAllByGoodsId(goodsId); //商品对应的非关键参数
		
		// 类别对应的关键参数
		List<Map<Object,Object>> catKeyt = new ArrayList<Map<Object,Object>>();
		List<CatKeytModel> keytParams = CatKeytModel.dao.findByCatId(goods.getInt("catId"));
		if(keytParams!=null){
			for(CatKeytModel param:keytParams){
				Map<Object,Object> keyt = new HashMap<Object,Object>();
				keyt.put("name",param.getStr("name"));
				keyt.put("value",CatKeytValModel.dao.findValuesByParamsId(param.getInt("id")));
				catKeyt.add(keyt);
			}
		}
		//判断关键参数是否已选
		for(Map<Object,Object> k:catKeyt){
			for(KeytValGoodsModel myK:keytList){
				for(CatKeytValModel v:((List<CatKeytValModel>) k.get("value"))){
					if((myK.getStr("paramsVal1")!=null&&myK.getStr("paramsVal1").equals(v.getStr("value"))) 
							|| (myK.getStr("paramsVal2")!=null&&myK.getStr("paramsVal2").equals(v.getStr("value")))){
						v.put("checked", "checked");
					}
				}
			}
		}
		
		setAttr("goods",goods); //商品参数
		setAttr("pics",GoodsPicModel.dao.findByGoodsId(goodsId)); //商品图片
		setAttr("keyt",catKeyt);//全部关键参数
		setAttr("keytList",keytList);//商品详细类别信息
		setAttr("nonkeytList",nonkeytList);//非关键参数信息
		render("goodsEdit.html");
	}

	/**
	 * @category 商品保存 
	 * @author inging44
	 * @date 2015年12月27日 下午6:44:42
	 */
	@Before(Tx.class)
	public void save() { 
		String cover = getPara("goods.cover","").trim();
		if(EduStringUtil.isEmpty(cover)){
			render(BjuiRender.error("封面不能为空"));
			return;
		}
		//保存商品
		GoodsModel newGoods = new GoodsModel();
		newGoods.set("title", getPara("goods.title","").trim())
			.set("shopId", ShiroUtils.getUserId())
			.set("catIdParent", getPara("goods.pid_0","").trim())
			.set("catId", getPara("goods.pid_1","").trim())
			.set("priceRange", getPara("goods.priceRange","").trim())
			.set("qualityassurance", getParaToInt("goods.qualityassurance",0))
			.set("replacement", getParaToInt("goods.replacement",0))
			.set("status", getParaToInt("goods.status",0))
			.set("give", getParaToInt("goods.give",0))
			.set("expressfee", getParaToInt("goods.expressfee",0))
			.set("cover", cover)
			.set("dateline", mCurrentDateTime)
			.save();
		int goodsId = newGoods.getInt("id");
		int remain = 0;
		
		//保存商品图片
		String[] pictures = getParaValues("goods.pictures");
		if(pictures!=null){
			for(String picture:pictures){
				new GoodsPicModel().set("goodsId",goodsId).set("pic",picture).save();
			}
		}
		
		//保存商品具体属性
		Integer[] pvId_1s = getParaValuesToInt("pvId_1");
		Integer[] pvId_2s = getParaValuesToInt("pvId_2");
		Integer[] totalCounts = getParaValuesToInt("totalCount");
		String[] prices = getParaValues("price");
		if(totalCounts!=null && prices!=null){
			if(pvId_1s==null){
				pvId_1s = new Integer[1];
				pvId_1s[0] = 0;
			}
			int length = pvId_1s.length;
			if(pvId_2s==null){
				pvId_2s = new Integer[length];
				for(int a=0;a<length;a++){
					pvId_2s[a] = 0;
				}
			}
			if(length == prices.length && length== totalCounts.length){
				for(int i=0;i<length;i++){
					new KeytValGoodsModel().set("goodsId",goodsId)
						.set("pvId_1",pvId_1s[i]).set("pvId_2",pvId_2s[i])
						.set("totalCount",totalCounts[i]).set("price",prices[i])
						.set("remain",totalCounts[i]).save();
					remain += totalCounts[i];
				}
			}
		}
		newGoods.set("remain", remain).update();
		//保存商品的非关键属性
		Integer[] nonkeytParamsIds = getParaValuesToInt("nonkeyt.ids");
		Integer[] displayorders = getParaValuesToInt("nonkeyt.displayorder");
		String[] names = getParaValues("nonkeyt.names");
		String[] values = getParaValues("nonkeyt.values");
		if(nonkeytParamsIds!=null && displayorders!=null && names!=null && values!=null){
			int nkpLength = nonkeytParamsIds.length;
			if(nkpLength==displayorders.length && nkpLength==names.length && nkpLength==values.length ){
				for(int j=0;j<nkpLength;j++){
					if(nonkeytParamsIds[j]==0){
						new CatNonkeytValModel().set("goodsId", goodsId)
							.set("name", names[j]).set("value", values[j])
							.set("displayorder", displayorders[j]).save();
					}else{
						new CatNonkeytValModel().set("goodsId", goodsId)
						.set("paramsId", nonkeytParamsIds[j]).set("value", values[j])
						.set("displayorder", displayorders[j]).save();
					}
				}
			}
		}
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}

	/**
	 * @category 商品更新 
	 * @author inging44
	 * @date 2015年12月27日 下午6:44:56
	 */
	public void update() { 
		int goodsId = getParaToInt("goods.id",0);
		GoodsModel goods = GoodsModel.dao.findById(goodsId);
		if(goods==null){
			render(BjuiRender.error("something wrong ~~~~~~~~~~~~~~~~~~"));
			return;
		}
		String cover = getPara("goods.cover","").trim();
		if(EduStringUtil.isEmpty(cover)){
			render(BjuiRender.error("封面不能为空"));
			return;
		}
		
		//保存商品
		goods.set("title", getPara("goods.title","").trim())
			.set("shopId", ShiroUtils.getUserId())
			.set("priceRange", getPara("goods.priceRange","").trim())
			.set("qualityassurance", getParaToInt("goods.qualityassurance",0))
			.set("replacement", getParaToInt("goods.replacement",0))
			.set("status", getParaToInt("goods.status",0))
			.set("give", getParaToInt("goods.give",0))
			.set("expressfee", getParaToInt("goods.expressfee",0))
			.set("cover", cover)
			.set("dateline", mCurrentDateTime)
			.update();
		int remain = 0;
		
		//保存商品图片
		GoodsPicModel.dao.deleteByGid(goodsId);
		String[] pictures = getParaValues("goods.pictures");
		if(pictures!=null){
			for(String picture:pictures){
				new GoodsPicModel().set("goodsId",goodsId).set("pic",picture).save();
			}
		}
		
		//保存商品具体属性
		KeytValGoodsModel.dao.deleteByGid(goodsId);
		Integer[] pvId_1s = getParaValuesToInt("pvId_1");
		Integer[] pvId_2s = getParaValuesToInt("pvId_2");
		Integer[] totalCounts = getParaValuesToInt("totalCountEdit");
		String[] prices = getParaValues("priceEdit");
		if(totalCounts!=null && prices!=null){
			if(pvId_1s==null){
				pvId_1s = new Integer[1];
				pvId_1s[0] = 0;
			}
			int length = pvId_1s.length;
			if(pvId_2s==null){
				pvId_2s = new Integer[length];
				for(int a=0;a<length;a++){
					pvId_2s[a] = 0;
				}
			}
			if(length == prices.length && length== totalCounts.length){
				for(int i=0;i<length;i++){
					new KeytValGoodsModel().set("goodsId",goodsId)
						.set("pvId_1",pvId_1s[i]).set("pvId_2",pvId_2s[i])
						.set("totalCount",totalCounts[i]).set("price",prices[i])
						.set("remain",totalCounts[i]).save();
					remain += totalCounts[i];
				}
			}
		}
		goods.set("remain", remain).update();
		
		//保存商品的非关键属性
		CatNonkeytValModel.dao.deleteByGid(goodsId);
		Integer[] nonkeytParamsIds = getParaValuesToInt("nonkeyt.ids");
		Integer[] displayorders = getParaValuesToInt("nonkeyt.displayorder");
		String[] names = getParaValues("nonkeyt.names");
		String[] values = getParaValues("nonkeyt.values");
		if(nonkeytParamsIds!=null && displayorders!=null && names!=null && values!=null){
			int nkpLength = nonkeytParamsIds.length;
			if(nkpLength==displayorders.length && nkpLength==names.length && nkpLength==values.length ){
				for(int j=0;j<nkpLength;j++){
					if(nonkeytParamsIds[j]==0){
						new CatNonkeytValModel().set("goodsId", goodsId)
							.set("name", names[j]).set("value", values[j])
							.set("displayorder", displayorders[j]).save();
					}else{
						new CatNonkeytValModel().set("goodsId", goodsId)
						.set("paramsId", nonkeytParamsIds[j]).set("value", values[j])
						.set("displayorder", displayorders[j]).save();
					}
				}
			}
		}
		render(BjuiRender.closeCurrentAndRefresh("goodsView",menuRel));
	}

	/**
	 * @category 商品删除 
	 * @author inging44
	 * @date 2015年12月27日 下午6:45:18
	 */
	public void delete() {
		int goodsId = getParaToInt(0,0);
		GoodsModel goods = GoodsModel.dao.findById(goodsId);
		if(goods==null){
			render(BjuiRender.error("请选择商品！"));
			return;
		}
		goods.set("isClosed", 1).update();
		render(BjuiRender.refresh(menuRel));
	}
	
	/**
	 * @category 根据商品分类获取商品属性 
	 * @author inging44
	 * @date 2015年12月27日 下午6:47:32
	 */
	public void getAttr(){
		int catId = getParaToInt("catId",0);
		if(catId==0){
			setAttr("flag",false);
			renderJson();
			return;
		}
		Map<Object,Object> keyt = new HashMap<Object,Object>();
		List<CatKeytModel> keytParams = CatKeytModel.dao.findByCatId(catId);
		if(keytParams!=null){
			for(CatKeytModel param:keytParams){
				keyt.put(param.getStr("name"), CatKeytValModel.dao.findValuesByParamsId(param.getInt("id")));
			}
		}
		
		List<CatNonkeytModel> nonkeytParams = CatNonkeytModel.dao.findByCatId(catId);
		setAttr("keyt",keyt);
		setAttr("nonkeyt",nonkeytParams);
		renderJson();
	}
}
