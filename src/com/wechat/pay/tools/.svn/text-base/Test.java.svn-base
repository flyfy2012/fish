package com.wechat.pay.tools;


import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

/**
 * @category 微信支付签名工具测试
 * @Description 
 * @author hmilysean 
 * @date 2016年1月25日 下午1:58:13 
 * @version V0.1
 */
public class Test {

	private static String URL="https://api.mch.weixin.qq.com/pay/unifiedorder?";
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		//这里配置自定义信息
		HashMap<String,String> map=new HashMap<String, String>();  
		 map.put("body", "测试数据");
		 map.put("out_trade_no", "000000000");
		 map.put("total_fee", "0000");
		 map.put("spbill_create_ip", "127.0.0.1");
		
		 //获取带有签名的字符串
		Map<String, String> sign = WechatSignTools.sign(map);		
		String string1=sign.toString();
		   string1=string1.replaceAll(", ","&");
	       string1=string1.replace("{","");
	       string1=string1.replace("}","");
	   //将字符串生成xml格式
	    StringBuffer xml = XmlTools.callMapToXML(sign);
		//用POST方法提交数据
		StringBuffer rstr = HttpTool.submitPost(URL, xml.toString());
		System.out.println(rstr);
		try {
			//将接收到的xml数据转换为map
			Map rmap = XmlTools.xml2map(rstr.toString());
			System.out.println("===========post method result=========");
			System.out.println(rmap);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
