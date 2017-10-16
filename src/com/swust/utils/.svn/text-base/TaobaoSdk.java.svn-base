package com.swust.utils;

import java.util.Iterator;
import java.util.Map;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.CheckVerCodeRequest;
import com.taobao.api.domain.SendVerCodeRequest;
import com.taobao.api.request.OpenSmsCheckvercodeRequest;
import com.taobao.api.request.OpenSmsSendvercodeRequest;
import com.taobao.api.response.OpenSmsCheckvercodeResponse;
import com.taobao.api.response.OpenSmsSendvercodeResponse;

public class TaobaoSdk {
	private static String url = "http://gw.api.taobao.com/router/rest";
	private static String key = "23264881";
	private static String secret = "91b07919041d251b09a806fd6c387c6d";
	private static long regTpl = 545L;
	private static long changePswTpl = 545L;
	private static long changeTel = 545L;
	private static long changeEmail = 545L;
	
	public static  Boolean sendCode(String mobile,int type) throws ApiException{
		long tpl;
		switch(type){
			case 1:tpl = regTpl;break;
			case 2:tpl = changePswTpl;break;
			case 3:tpl = changeTel;break;
			case 4:tpl = changeEmail;break;
			default:tpl = 545L;break;
		}
		
		TaobaoClient client = new DefaultTaobaoClient(url,key,secret);
		OpenSmsSendvercodeRequest req = new OpenSmsSendvercodeRequest();
		SendVerCodeRequest obj117574 = new SendVerCodeRequest();
		obj117574.setExpireTime(120L);//验证码失效时间，单位为秒
		obj117574.setSessionLimit(123L);//session级别的发送次数限制
		obj117574.setDeviceLimit(123L);//设备级别的发送次数限制
		obj117574.setDeviceLimitInTime(123L);//发送次数限制的时间，单位为秒
		obj117574.setMobileLimit(123L);//手机号的次数限制
		obj117574.setSessionLimitInTime(123L);//发送次数限制的时间，单位为秒
		//obj117574.setExternalId("12345");//外部的id
		obj117574.setMobileLimitInTime(123L);//手机号的次数限制的时间
		obj117574.setTemplateId(tpl);//模板id
		obj117574.setSignatureId(608L);//签名id
		//obj117574.setSessionId("demo");//session id
		//obj117574.setDomain("demo");//场景域，比如登录的验证码不能用于注册
		//obj117574.setDeviceId("demo");//设备id
		obj117574.setMobile(mobile);
		//obj117574.setContextString("{\"appName\":\"xxx\"}");//短信内容替换上下文
		req.setSendVerCodeRequest(obj117574);
		OpenSmsSendvercodeResponse rsp = client.execute(req);
		String body = rsp.getBody();
		System.out.println(body);
		Map<Integer, String> map = JsonUtils.JsonStrToMap(body);
		
		for (Iterator<?>  iterator = map.entrySet().iterator(); iterator.hasNext();) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry=(Map.Entry)iterator.next();
			//先遍历一维map
			if(entry.getValue() instanceof Map){
				@SuppressWarnings("unchecked")
				Map<String, String> map1=(Map<String, String>)entry.getValue();
				for(Iterator<?> it=map1.entrySet().iterator();it.hasNext();){
					@SuppressWarnings("rawtypes")
					Map.Entry entry2=(Map.Entry)it.next();
			     //再遍历二维map
					if(entry2.getValue() instanceof Map){
						
						@SuppressWarnings("unchecked")
						Map<String, String> map2=(Map<String, String>)entry2.getValue();
						for(Iterator<?> tt=map2.entrySet().iterator();tt.hasNext();){
							@SuppressWarnings("rawtypes")
							Map.Entry entry3=(Map.Entry)tt.next();
					     //再遍历三维map
							//System.out.println("two map key="+entry3.getKey()+"  value="+entry3.getValue());
							if(EduStringUtil.equals((String) entry3.getKey(), "successful")){
								return (Boolean)entry3.getValue();
							}
						}
					}
			    }
			}
		}
		return false;
	}
	
	public static Boolean checkCode(String mobile,String code) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url,key,secret );
		OpenSmsCheckvercodeRequest req = new OpenSmsCheckvercodeRequest();
		CheckVerCodeRequest obj116871 = new CheckVerCodeRequest();
		//obj116871.setDomain("demo");
		obj116871.setCheckFailLimit(123L);
		obj116871.setCheckSuccessLimit(123L);
		obj116871.setVerCode(code);
		obj116871.setMobile(mobile);
		req.setCheckVerCodeRequest(obj116871);
		OpenSmsCheckvercodeResponse rsp = client.execute(req);
		String body = rsp.getBody();
		System.out.println(body);
		Map<Integer, String> map = JsonUtils.JsonStrToMap(body);
		
		for (Iterator<?>  iterator = map.entrySet().iterator(); iterator.hasNext();) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry=(Map.Entry)iterator.next();
			//先遍历一维map
			if(entry.getValue() instanceof Map){
				@SuppressWarnings("unchecked")
				Map<String, String> map1=(Map<String, String>)entry.getValue();
				for(Iterator<?> it=map1.entrySet().iterator();it.hasNext();){
					@SuppressWarnings("rawtypes")
					Map.Entry entry2=(Map.Entry)it.next();
			     //再遍历二维map
					if(entry2.getValue() instanceof Map){
						
						@SuppressWarnings("unchecked")
						Map<String, String> map2=(Map<String, String>)entry2.getValue();
						for(Iterator<?> tt=map2.entrySet().iterator();tt.hasNext();){
							@SuppressWarnings("rawtypes")
							Map.Entry entry3=(Map.Entry)tt.next();
					     //再遍历三维map
							//System.out.println("two map key="+entry3.getKey()+"  value="+entry3.getValue());
							if(EduStringUtil.equals((String) entry3.getKey(), "successful")){
								return (Boolean)entry3.getValue();
							}
						}
					}
			    }
			}
		}
		return false;
	}
}
