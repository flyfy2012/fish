package com.swust.utils;

/**
 * @author LiuGe
 *date:
 *desc:
 */
/**
 * @author LiuGe
 *date:
 *desc:
 */
public class DefineJsonUtils {
	public final static String myString = "[com.jfaker.framework.security.model.Org@38268b {id:3, name:学办}, com.jfaker.framework.security.model.Org@4bc2aa80 {id:4, name:通信1004}, com.jfaker.framework.security.model.Org@70ef5e68 {id:8, name:生医1201}, com.jfaker.framework.security.model.Org@f4bfc481 {id:12, name:电气1201}]";
	public static String defineStringGrade(String[] cast) {
		String name = null;
		String id = null;
		String castString = "";
		String tempString = null;
		char myChar = '"';
		if (cast == null)
			return null;
		for (int i = 0; i < cast.length; i++) {
			if ((i + 1) % 2 != 0) {
				id = cast[i].substring(cast[i].indexOf(":") + 1);
			} else {
				name = cast[i].substring(cast[i].indexOf(":") + 1,
						cast[i].indexOf("}"));
				tempString = "[" + id + "," + myChar + name + myChar + "]";
				if(i==cast.length-1)
				castString=castString.concat(tempString);
				else
					castString=castString.concat(tempString)+",";
			}
			
	}
//		System.out.println("[" + castString + "]");
		return "[" +"[\"0\", \"所有年级\"],"+ castString + "]";
}
	
	public static String defineStringClass(String[] cast) {
		String name = null;
		String id = null;
		String castString = "";
		String tempString = null;
		char myChar = '"';
		if (cast == null)
			return null;
		for (int i = 0; i < cast.length; i++) {
			if ((i + 1) % 2 != 0) {
				id = cast[i].substring(cast[i].indexOf(":") + 1);
			} else {
				name = cast[i].substring(cast[i].indexOf(":") + 1,
						cast[i].indexOf("}"));
				tempString = "[" + id + "," + myChar + name + myChar + "]";
				if(i==cast.length-1)
				castString=castString.concat(tempString);
				else
					castString=castString.concat(tempString)+",";
			}
			
	}
//		System.out.println("[" + castString + "]");
		return "[" +"[\"1\", \"所有班级\"],"+ castString + "]";
}
	
	
//将规定格式的串截取之后，调用defineString（转化成需要的格式
	public static String myJsonFormatGrade(String originString) {
		String splitedString[]=null;
		splitedString = originString.split(",");
		return defineStringGrade(splitedString);
	}
	public static String myJsonFormatClass(String originString) {
		String splitedString[]=null;
		splitedString = originString.split(",");
		return defineStringClass(splitedString);
	}
	
	
	public static void main(String[] args) {
		//System.out.println(myJsonFormat(myString));
	}
}
