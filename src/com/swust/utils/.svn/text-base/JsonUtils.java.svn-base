package com.swust.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * json的操作方法
 * @author hsongjiang
 * 2015-03-01
 *  notes:参考了 http://www.cnblogs.com/hoojo/archive/2011/04/22/2024628.html
 */
public class JsonUtils {

	/**
	 * 字符窜数组转为json字符串
	 * @param str
	 * @return
	 */
	static ObjectMapper objectMapper = new ObjectMapper();
	
	public static String StringArrayToJsonStr(
			String[] str) {
		StringWriter writer = new StringWriter();		
		try {
			objectMapper.writeValue(writer, str);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * 将json字符串 转换为字符串数组
	 * @param jsonStr
	 * @return
	 */
	public static String []  JsonStrToStringArray(String jsonStr){
		
		String [] m =null;
		if(StringUtils.equals(jsonStr, ""))
			return null;
		try {
			 m =  objectMapper.readValue(jsonStr,String[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	/**
	 * Map变为json字符串，用于班会记录里面，某次班会对应几个班级
	 * hsongjiang 2015-3-7
	 * @param orgName
	 * @return json 字符串
	 */
	
	public static String  MapToJsonStr(Map<Integer,String> orgName) {
		StringWriter writer = new StringWriter();		
		try {
			objectMapper.writeValue(writer, orgName);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}	
	/**
	 * @Description 用于二维码信息的封装
	 * @author 
	 * @param orgName
	 * @return
	 */
	public static String  MapToJsonString(Map<String,Object> orgName) {
		StringWriter writer = new StringWriter();		
		try {
			objectMapper.writeValue(writer, orgName);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}	
	
	/**
	 * List转为json测试
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年8月24日 上午11:11:57 
	 * @param str
	 * @return
	 */
	public static String  ListToJsonStr(List<String> str) {
		StringWriter writer = new StringWriter();		
		try {
			objectMapper.writeValue(writer, str);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}	
	
	
	/**
	 * json字符串，变为Map
	 */
	public static Map<Integer,String>  JsonStrToMap ( String jsonStr) {
		Map<Integer,String>  m =null;
		if(StringUtils.equals(jsonStr, ""))
			return null;
		try {
			 m =  objectMapper.readValue(jsonStr,Map.class);
//			m =  objectMapper.readValue(jsonStr,TypeFactory.mapType(HashMap.class, String.class, String.class));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}	
	
	
	/**
	 * 课表信息获取
	 * hsongjiang 2015-03-27 
	 * @param str
	 * @return
	 */
	public static String CourseArrayToJsonStr(
			Map<String,String> [][] str) {
		StringWriter writer = new StringWriter();		
		try {
			objectMapper.writeValue(writer, str);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	
}
