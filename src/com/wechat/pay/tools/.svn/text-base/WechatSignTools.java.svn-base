package com.wechat.pay.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @category 生成签名工具
 * @Description 
 * @author hmilysean 
 * @date 2016年1月25日 下午1:56:12 
 * @version V0.1
 */
public class WechatSignTools {

	//固定的信息可以在这里配置
	
	
	 public static Map<String, String> sign(HashMap<String,String> ret) {
	       
	       // String timestamp = create_timestamp();
	       // parameters.put("timestamp", timestamp);
	        String nonce_str = create_nonce_str();
	        ret.put("nonce_str", nonce_str);
	        ret.put("appid", WechatConfig.appid);
	        ret.put("mch_id", WechatConfig.mch_id);
	        ret.put("notify_url", WechatConfig.notify_url);
	        ret.put("trade_type", WechatConfig.trade_type);
	      
	        String string1="";
	        String signature = "";

	        //注意这里参数名必须全部小写，且必须有序
	        SortedMap<String, String> parameters=new TreeMap<String,String>(ret);
	        
	        string1=parameters.toString();  
	        string1=string1.replaceAll(", ","&");
	        string1=string1.replace("{","");
	        string1=string1.replace("}","");
	        
	        try
	        {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        catch (UnsupportedEncodingException e)
	        {
	            e.printStackTrace();
	        }

	        ret.put("sign", signature);

	        return ret;
	    }

	    private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash)
	        {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
	    }

	    /**
	     * @category  生成随机字符串
	     * @Description 
	     * @author hmilysean
	     * @date 2016年1月25日 下午1:56:50 
	     * @return
	     */
	    private static String create_nonce_str() {
	        return UUID.randomUUID().toString();
	    }
	    
	    /**
	     * @category 生成时间戳
	     * @Description 
	     * @author hmilysean
	     * @date 2016年1月25日 下午1:57:14 
	     * @return
	     */
//	    private static String create_timestamp() {
//	        return Long.toString(System.currentTimeMillis() / 1000);
//	    }
}
