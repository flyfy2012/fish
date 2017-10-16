package com.swust.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.swust.cfg.Preference;

public class EduStringUtil extends StringUtils{
	
	
	public static boolean isValidLength(String string, int minLength, int maxLength) {
		if (null == string) {
			return false;
		}

		int length = string.length();
		if (minLength > length || maxLength < length) {
			return false;
		}

		return true;
	}

	public static boolean check_pwd(String pwd) {
		if (pwd == null)
			return false;
		if (pwd.length() > Preference.ULTRA_PASSWORD_MAX_LEN || pwd.length() < Preference.ULTRA_PASSWORD_MIN_LEN) {
			return false;
		}
		Pattern pattern = Pattern.compile("([\\x21-\\x7E])*");
		return pattern.matcher(pwd).matches();
	}

	/*
	 * 检查用户名是否是纯数字
	 * 
	 */
	public static boolean check_username(String username) {
		if (username == null)
			return false;
		
		return StringUtils.isNumeric(username);
	}

	public static boolean check_phone(String phone) {
		if (phone == null || phone.isEmpty()) {

			return false;
		}

		if (phone.length() > 30 || phone.length() < 11) {

			return false;
		}
		Pattern pattern = Pattern.compile("([0-9])*");
		return pattern.matcher(phone).matches();
	}

	public static boolean check_email(String mail) {
		if (mail == null || mail.isEmpty()) {
			return false;
		}
		if (mail.length() > Preference.ULTRA_EMAIL_LEN) {
			return false;
		}

		Pattern pattern = Pattern
				.compile(".*@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

		return pattern.matcher(mail).matches();
	}

	
	/**
	 * 提取字符串内所有的img标签下的src
	 * @param content
	 * @return
	 */
	public static List<String> getImg(String content){
	     String regex;     
	       List<String> list = new ArrayList<String>();
	       //提取字符串中的img标签
	       regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";     
	       Pattern pa = Pattern.compile(regex, Pattern.DOTALL);     
	       Matcher ma = pa.matcher(content);     
	       while (ma.find())     
	       {  
	        //提取字符串中的src路径
	        Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(ma.group());
	        while(m.find())
	        {
	                list.add(m.group(1));

	        }
	       }
	       return list;
	    }	
	
	/**
	 * 从新闻内容中得到第一张图片的地址
	 * @Description 
	 * @author hmilysean
	 * @date 2015-10-14 下午03:42:11 
	 * @param content
	 * @return
	 */
	public static String getOnlyOneImg(String content){
		String regex;  
	       //提取字符串中的img标签
	       regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
	       Pattern pa = Pattern.compile(regex, Pattern.DOTALL);     
	       Matcher ma = pa.matcher(content);   
	       while (ma.find())     
	       {  
	        //提取字符串中的src路径
	        Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(ma.group());
	        while(m.find())
	        {
	                return m.group(1);
	        }
	        
	        
	       }
	       
	       return null;
	}
	
	public static String changeUrl(String content){
 	    
	       //提取字符串中的img标签  <table[^>]*?>.*?</table>
	     	
	      //reg=(href=[\"\']*)([^\"\']*[\"\'])
	     	Pattern pattern = Pattern.compile("(href=[\"\']*)([^\"\']*[\"\'])", Pattern.DOTALL);
	     	Matcher matcher = pattern.matcher(content);
	     	String string = matcher.replaceAll("href=\"http://www.baidu.com\"");
	     	//System.out.println(string);
			return string;
	    }	
}
