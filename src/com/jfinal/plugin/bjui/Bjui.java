package com.jfinal.plugin.bjui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;






import org.apache.commons.lang.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class Bjui {
	public static DbPro use(String configName) {
		return Db.use(configName);
	}
	/**
	 * BJUI默认表格显示分页传入表名
	 * @param tableName 表名
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param sqlOrder 	SQL的排序部分 例：t.c desc,t.d asc
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPageByTableName(String tableName,String sqlQuery,String sqlOrder,Controller c,Object... paras){
		BjuiPage("select * ", "from "+tableName, sqlQuery, sqlOrder, c, paras);
	}
	/**
	 * BJUI默认表格显示分页传入表名
	 * @param tableName 表名
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPageByTableName(String tableName,String sqlQuery,Controller c,Object... paras){
		BjuiPage("select * ", "from "+tableName, sqlQuery, "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页传入表名
	 * @param tableName 表名
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPageByTableName(String tableName,Controller c,Object... paras){
		BjuiPage("select * ", "from "+tableName, "", "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页
	 * @param sql 	查询SQL
	 * @param c 	调用的controller
	 * @param paras SQL的WHERE的若干参数
	 */
	public static void BjuiPage(String sql, Controller c, Object... paras){
		int index = sql.toLowerCase().indexOf("from");
		String select = sql;
		String from = "";
		if (index > 0) {
			select = sql.substring(0, index);
			from = sql.substring(index);
		}
		BjuiPage(select, from, "", "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页和查询
	 * @param sql 		查询SQL
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPage(String sql,String sqlQuery, Controller c, Object... paras){
		int index = sql.toLowerCase().indexOf("from");
		String select = sql;
		String from = "";
		if (index > 0) {
			select = sql.substring(0, index);
			from = sql.substring(index);
		}
		BjuiPage(select, from, sqlQuery, "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页和查询
	 * @param sql 		查询SQL
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param sqlOrder 	SQL的排序部分 例：t.c desc,t.d asc
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPage(String sql, String sqlQuery,String sqlOrder, Controller c, Object... paras){
		int index = sql.toLowerCase().indexOf("from");
		String select = sql;
		String from = "";
		if (index > 0) {
			select = sql.substring(0, index);
			from = sql.substring(index);
		}
		BjuiPage(select, from, sqlQuery, sqlOrder, c, paras);
	}
	/**
	 * BJUI默认表格显示分页和查询
	 * @param select 		SQL的SELECT部分 例：select t.a
	 * @param sqlFromWhere 	SQL的FROM部分 例：from t where t.b = ? and t.c = ? order by t.a asc
	 * @param sqlQuery 		页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param sqlOrder 		SQL的排序部分 例：t.c desc,t.d asc
	 * @param c 			调用的controller
	 * @param paras 		SQL的WHERE的若干参数
	 */
	public static void BjuiPage(String select, String sqlFromWhere, String sqlQuery,String sqlOrder, Controller c, Object... paras){
		BjuiPage(DbPro.use(), select, sqlFromWhere, sqlQuery, sqlOrder, c, paras);
	}
	/**
	 * BJUI默认表格显示分页传入表名
	 * @param tableName 表名
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param sqlOrder 	SQL的排序部分 例：t.c desc,t.d asc
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPageByTableName(DbPro db,String tableName,String sqlQuery,String sqlOrder,Controller c,Object... paras){
		BjuiPage(db,"select * ", "from "+tableName, sqlQuery, sqlOrder, c, paras);
	}
	/**
	 * BJUI默认表格显示分页传入表名
	 * @param tableName 表名
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPageByTableName(DbPro db,String tableName,String sqlQuery,Controller c,Object... paras){
		BjuiPage(db,"select * ", "from "+tableName, sqlQuery, "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页传入表名
	 * @param tableName 表名
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPageByTableName(DbPro db,String tableName,Controller c,Object... paras){
		BjuiPage(db,"select * ", "from "+tableName, "", "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页
	 * @param sql 	查询SQL
	 * @param c 	调用的controller
	 * @param paras SQL的WHERE的若干参数
	 */
	public static void BjuiPage(DbPro db,String sql, Controller c, Object... paras){
		int index = sql.toLowerCase().indexOf("from");
		String select = sql;
		String from = "";
		if (index > 0) {
			select = sql.substring(0, index);
			from = sql.substring(index);
		}
		BjuiPage(db,select, from, "", "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页和查询
	 * @param sql 		查询SQL
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPage(DbPro db,String sql,String sqlQuery, Controller c, Object... paras){
		int index = sql.toLowerCase().indexOf("from");
		String select = sql;
		String from = "";
		if (index > 0) {
			select = sql.substring(0, index);
			from = sql.substring(index);
		}
		BjuiPage(db,select, from, sqlQuery, "", c, paras);
	}
	/**
	 * BJUI默认表格显示分页和查询
	 * @param sql 		查询SQL
	 * @param sqlQuery 	页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param sqlOrder 	SQL的排序部分 例：t.c desc,t.d asc
	 * @param c 		调用的controller
	 * @param paras 	SQL的WHERE的若干参数
	 */
	public static void BjuiPage(DbPro db,String sql, String sqlQuery,String sqlOrder, Controller c, Object... paras){
		int index = sql.toLowerCase().indexOf("from");
		String select = sql;
		String from = "";
		if (index > 0) {
			select = sql.substring(0, index);
			from = sql.substring(index);
		}
		BjuiPage(db,select, from, sqlQuery, sqlOrder, c, paras);
	}
	/**
	 * BJUI默认表格显示分页和查询
	 * @param select 		SQL的SELECT部分 例：select t.a
	 * @param sqlFromWhere 	SQL的FROM部分 例：from t where t.b = ? and t.c = ? order by t.a asc
	 * @param sqlQuery 		页面传递的查询列名，以逗号分隔。 例：t.a,t.b
	 * @param sqlOrder 		SQL的排序部分 例：t.c desc,t.d asc
	 * @param c 			调用的controller
	 * @param paras 		SQL的WHERE的若干参数
	 */
	public static void BjuiPage(DbPro db,String select, String sqlFromWhere, String sqlQuery,String sqlOrder, Controller c, Object... paras){
		Map<String,Object> pagemap = new HashMap<String, Object>();
		Integer pageNumber = c.getParaToInt("pageCurrent", 1);
		Integer pageSize = c.getParaToInt("pageSize", 15);
		String orderField = c.getPara("orderField", "");
		String orderDirection = c.getPara("orderDirection", "asc");
		//处理空格及分号
		String sqlExceptSelect = sqlFromWhere.replaceAll("(\\s)+", " ").replaceAll(";", "");
		//处理查询列
		if(sqlQuery!=null&&!"".equals(sqlQuery)){
			String[] queryColumns = sqlQuery.split(",");
			int index = sqlExceptSelect.toLowerCase().lastIndexOf("where");
			//不支持最外层WHERE中含括号的SQL
			if (index > sqlExceptSelect.toLowerCase().lastIndexOf(")")) {
				String sql1 = sqlExceptSelect.substring(0, index+5);
				String sql2 = sqlExceptSelect.substring(index+5);
				for (int i = 0; i < queryColumns.length; i++) {
					String queryColumnData = c.getPara(queryColumns[i],"");
					if(!"".equals(queryColumnData)){
						// 默认都是like处理
						if(queryColumnData.toLowerCase().startsWith("in(")||
								queryColumnData.toLowerCase().startsWith("!=")||
								queryColumnData.toLowerCase().startsWith("=")||
								queryColumnData.toLowerCase().startsWith(">")||
								queryColumnData.toLowerCase().startsWith("<")){
							sql1 = sql1 + " " + queryColumns[i] + " "+queryColumnData+" and ";
						}else{
							sql1 = sql1 + " " + queryColumns[i] + " like '%"+queryColumnData+"%' and ";
						}
						if(StringUtils.contains(queryColumns[i], ".")){
							Map<String,String> querycolmap = new HashMap<String, String>();
							querycolmap.put(StringUtils.substringAfterLast(queryColumns[i], "."), queryColumnData);
							c.setAttr(StringUtils.substringBefore(queryColumns[i], "."), querycolmap);
						}else{
							c.setAttr(queryColumns[i], queryColumnData);
						}
					}
				}
				sqlExceptSelect = sql1 + sql2;
			}else{
				sqlExceptSelect = sqlExceptSelect + " where 1=1";
				for (int i = 0; i < queryColumns.length; i++) {
					String queryColumnData = c.getPara(queryColumns[i],"");
					if(!"".equals(queryColumnData)){
						//默认都是like处理
						if(queryColumnData.toLowerCase().startsWith("in(")||
								queryColumnData.toLowerCase().startsWith("!=")||
								queryColumnData.toLowerCase().startsWith("=")||
								queryColumnData.toLowerCase().startsWith(">")||
								queryColumnData.toLowerCase().startsWith("<")){
							sqlExceptSelect = sqlExceptSelect + " and " + queryColumns[i] + " "+queryColumnData;
						}else{
							sqlExceptSelect = sqlExceptSelect + " and " + queryColumns[i] + " like '%"+queryColumnData+"%'";
						}
						if(StringUtils.contains(queryColumns[i], ".")){
							Map<String,String> querycolmap = new HashMap<String, String>();
							querycolmap.put(StringUtils.substringAfterLast(queryColumns[i], "."), queryColumnData);
							c.setAttr(StringUtils.substringBefore(queryColumns[i], "."), querycolmap);
						}else{
							c.setAttr(queryColumns[i], queryColumnData);
						}
					}
				}
			}
		}
		//处理排序列
		if(sqlOrder!=null&&!"".equals(sqlOrder)){
			int index = sqlExceptSelect.toLowerCase().lastIndexOf("order by");
			if (index > sqlExceptSelect.toLowerCase().lastIndexOf(")")) {
				String sql1 = sqlExceptSelect.substring(0, index+8);
				String sql2 = sqlExceptSelect.substring(index+8);
				sqlExceptSelect = sql1 + " "+sqlOrder+", "+sql2;
			}else{
				sqlExceptSelect = sqlExceptSelect + " order by "+sqlOrder;
			}
		}
		
		if(!"".equals(orderField)){
			int index = sqlExceptSelect.toLowerCase().lastIndexOf("order by");
			if (index > sqlExceptSelect.toLowerCase().lastIndexOf(")")) {
				String sql1 = sqlExceptSelect.substring(0, index+8);
				String sql2 = sqlExceptSelect.substring(index+8);
				sqlExceptSelect = sql1 + " "+orderField+" "+orderDirection + ", " + sql2;
			}else{
				sqlExceptSelect = sqlExceptSelect + " order by "+orderField+" "+orderDirection;
			}
		}
		
		Page<Record> result = db.paginate(pageNumber, pageSize, select, sqlExceptSelect, paras);
		
		pagemap.put("pageSize", result.getPageSize());//页面大小
		pagemap.put("totalRow", result.getTotalRow());//总行数
		pagemap.put("pageCurrent", result.getPageNumber());//当前页号
		pagemap.put("orderField", orderField);//排序列名
		pagemap.put("orderDirection", orderDirection);//排序方式
		pagemap.put("pageData", result.getList());//页面数据
		c.setAttr("page",pagemap);
		
		
	}
	/**
	 * 返回自动刷新的元素
	 * @param c
	 * @param newurl
	 * @param newtitle
	 */
	public static void BjuiReloadUrl(Controller c,String newurl,String newtitle){
		c.setAttr("newurl", newurl);
		c.setAttr("newtitle", newtitle);
		c.render("/WEB-INF/pm/common/autoReloadUrl.html");
	}
	/**
	 * BJUI表格单表更新
	 * 表格规则说明：
	 * table中需要更新的列设置name属性#index#*colName,其中colName为需要更新的列名
	 * @param tablename 表名
	 * @param c
	 * @return
	 */
	public static boolean updateTable(String tablename,Controller c){
		return updateTable(tablename, "", c);
	}
	/**
	 * BJUI表格单表更新
	 * 表格规则说明：
	 * table中需要更新的列设置name属性#index#*colName,其中colName为需要更新的列名
	 * @param tablename 表名
	 * @param primaryKey 主键
	 * @param c
	 * @return
	 */
	public static boolean updateTable(String tablename,String primaryKey,Controller c){
		/*if(c.getRequest().getContentType().startsWith("multipart")){
			c.getFile();
		}else{
			//c.render(BjuiRender.code(802));
			return false;
		}*/
		String primaryKeyName = "id";//默认主键为id
		if(!isBlank(primaryKey)) primaryKeyName = primaryKey;
		Map<String, String[]> tabledata = c.getParaMap();
		SortedMap<String,String> sortmap = mapSortByKey(tabledata);
		List<Map<String, String>> datalist = mapGroupByRow(sortmap);
		for(Map<String, String> rowdata : datalist ){
			if("Normal".equals(rowdata.get("_rowstatus"))) {
				if(isBlank(rowdata.get(primaryKeyName))){
					//new
					Record row = new Record().set(primaryKeyName, getUUID());
					for(String key : rowdata.keySet()) {
						if(key.equals(primaryKeyName)||key.equals("_rowstatus")) continue;
						//if(!key.startsWith("_u_")) continue;
						//key = key.substring(3);
						if(isBlank(rowdata.get(key))){
							row.set(key, null);
						}else{
							row.set(key, rowdata.get(key));
						}
					}
					if(!Db.save(tablename,primaryKeyName, row)) return false;
				}else{
					//update
					Record row = Db.findById(tablename,primaryKeyName, rowdata.get(primaryKeyName));
					for(String key : rowdata.keySet()) {
						if(key.equals(primaryKeyName)||key.equals("_rowstatus")) continue;
						//if(!key.startsWith("_u_")) continue;
						//key = key.substring(3);
						if(isBlank(rowdata.get(key))){
							row.set(key, null);
						}else{
							row.set(key, rowdata.get(key));
						}
					}
					if(!Db.update(tablename,primaryKeyName, row)) return false;
				}
			}else if("Delete".equals(rowdata.get("_rowstatus"))) {
				if(!isBlank(rowdata.get(primaryKeyName))){
					//delete
					if(!Db.deleteById(tablename,primaryKeyName, rowdata.get(primaryKeyName))) return false;
				}
			}
		}
		return true;
	}
	/**
	 * BJUI表格单表更新
	 * 表格规则说明：
	 * table中需要更新的列设置name属性#index#*colName,其中colName为需要更新的列名
	 * @param tablename 表名
	 * @param c
	 * @return
	 */
	public static boolean updateTable(DbPro db,String tablename,Controller c){
		return updateTable(db,tablename, "", c);
	}
	/**
	 * BJUI表格单表更新
	 * 表格规则说明：
	 * table中需要更新的列设置name属性#index#*colName,其中colName为需要更新的列名
	 * @param tablename 表名
	 * @param primaryKey 主键
	 * @param c
	 * @return
	 */
	public static boolean updateTable(DbPro db,String tablename,String primaryKey,Controller c){
		/*if(c.getRequest().getContentType().startsWith("multipart")){
			c.getFile();
		}else{
			//c.render(BjuiRender.code(802));
			return false;
		}*/
		String primaryKeyName = "id";//默认主键为id
		if(!isBlank(primaryKey)) primaryKeyName = primaryKey;
		Map<String, String[]> tabledata = c.getParaMap();
		SortedMap<String,String> sortmap = mapSortByKey(tabledata);
		List<Map<String, String>> datalist = mapGroupByRow(sortmap);
		for(Map<String, String> rowdata : datalist ){
			if("Normal".equals(rowdata.get("_rowstatus"))) {
				if(isBlank(rowdata.get(primaryKeyName))){
					//new
					Record row = new Record().set(primaryKeyName, getUUID());
					for(String key : rowdata.keySet()) {
						if(key.equals(primaryKeyName)||key.equals("_rowstatus")) continue;
						//if(!key.startsWith("_u_")) continue;
						//key = key.substring(3);
						if(isBlank(rowdata.get(key))){
							row.set(key, null);
						}else{
							row.set(key, rowdata.get(key));
						}
					}
					if(!db.save(tablename,primaryKeyName, row)) return false;
				}else{
					//update
					Record row = Db.findById(tablename,primaryKeyName, rowdata.get(primaryKeyName));
					for(String key : rowdata.keySet()) {
						if(key.equals(primaryKeyName)||key.equals("_rowstatus")) continue;
						//if(!key.startsWith("_u_")) continue;
						//key = key.substring(3);
						if(isBlank(rowdata.get(key))){
							row.set(key, null);
						}else{
							row.set(key, rowdata.get(key));
						}
					}
					if(!db.update(tablename,primaryKeyName, row)) return false;
				}
			}else if("Delete".equals(rowdata.get("_rowstatus"))) {
				if(!isBlank(rowdata.get(primaryKeyName))){
					//delete
					if(!db.deleteById(tablename,primaryKeyName, rowdata.get(primaryKeyName))) return false;
				}
			}
		}
		return true;
	}
	/**
	 * 用于updateTable整理数据-1.排序MAP
	 * @param unsort_map
	 * @return
	 */
	private static SortedMap<String, String> mapSortByKey(Map<String, String[]> unsort_map) {
		TreeMap<String, String> result = new TreeMap<String, String>();
		Object[] unsort_key = unsort_map.keySet().toArray();
		Arrays.sort(unsort_key);
		for (int i = 0; i < unsort_key.length; i++) {
			result.put(unsort_key[i].toString(), unsort_map.get(unsort_key[i])[0]);
		}
		return result.tailMap(result.firstKey());
	}
	/**
	 * 用于updateTable整理数据-2.分组数据
	 * @param sort_map
	 * @return
	 */
	private static List<Map<String,String>> mapGroupByRow(SortedMap<String,String> sort_map){
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		String rowsnum = "";
		for(String key : sort_map.keySet()) {
			String[] keys = key.split("\\*");
			if(keys.length != 2) continue;
			try {
				if(Integer.parseInt(keys[0]) < 0) continue;
			} catch (NumberFormatException e) {
				continue;
			}
			if(!rowsnum.equals(keys[0])){
				rowsnum = keys[0];
				result.add(new HashMap<String, String>());
			}
			result.get(result.size() - 1).put(keys[1], sort_map.get(key));
		}
		return result;
		
	}
	/**
	 * 获取UUID
	 * @return UUID
	 */
	private static String  getUUID(){
		return  java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * 判断String为空
	 * @param str
	 * @return
	 */
	private static boolean isBlank(String str) {
	        int strLen;
	        if (str == null || (strLen = str.length()) == 0) {
	            return true;
	        }
	        for (int i = 0; i < strLen; i++) {
	            if ((Character.isWhitespace(str.charAt(i)) == false)) {
	                return false;
	            }
	        }
	        return true;
	    }
}
