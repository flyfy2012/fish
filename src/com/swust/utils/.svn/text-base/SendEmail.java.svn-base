package com.swust.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
/**
 * 邮件发送工具
 * @Description 
 * @author inging44
 * @date 2015年12月23日 下午8:04:40 
 * @version V0.1
 */
public class SendEmail {
	
	/**
	 * @category  邮件发送
	 * @author inging44
	 * @date 2015年12月23日 下午7:10:57 
	 * @return
	 * @throws EmailException
	 */
	public static String sendEmail(String email,String subject,String msg) throws EmailException{
		
		HtmlEmail emailToSend = new HtmlEmail();
		emailToSend.setHostName("smtp.qq.com");  
		emailToSend.setAuthentication("738631563@qq.com","uebxgkddvspbbdid");//zeviqsllkxefbddd //uebxgkddvspbbdid
	 	emailToSend.setCharset("UTF-8");  
	 	emailToSend.addTo(email);  
	 	emailToSend.setFrom("738631563@qq.com"); 
	 	emailToSend.setSSL(true);
	 	emailToSend.setSubject(subject); 
	 	emailToSend.setContent(msg,"text/html");
	// 	emailToSend.setMsg(msg);  
	 	emailToSend.send(); 
	 	System.out.println("--------------邮件发送成功---to："+email+"------------------");
		return "";
	}
}
