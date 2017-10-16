package com.swust.utils;

import java.util.*;
import java.io.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class createQRImg 
{
	private static String fileName;
	//con为二维码的内容，id来自于课程id
	public static String QRimg(String con,String p)
	{
		try 
	  	{  
			 //二维码类内容
		     String content = con;
		     
		     //String path = "C:/Users/Administrator/Desktop/testImage";
		     //存储路径
		     String path = p;//"F:/erweima";
		     
		     //二维码名称
		     StringBuffer sb = new StringBuffer();
		     sb.append(System.currentTimeMillis()).append(".jpg");
		      fileName = sb.toString();
		     
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     
		     Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
		     hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200,hints);
		     File file1 = new File(path,fileName);
		     CreateQRCode.writeToFile(bitMatrix, "jpg", file1);
		     
		     System.out.println("成功");
		    
		 }
	  	 catch (Exception e) 
	  	 {
		     e.printStackTrace();
		 }
		 return fileName;
	}
}

	/*public static void encode() {  
	    // 二维码内容  
	    StringBuffer params = new StringBuffer();  
	      
	    params.append("资产名称:").append("你好").append("\r/n");  
	    params.append("资产编号:").append("adfadf").append("\r\n");  
	    params.append("资产类型:").append("网络设备").append("\r/n");  
	    params.append("所属部门:").append("综合部").append("\r/n");  
	    params.append("制码时间:").append(DateUtils.getCurrentTime()).append("\r/n");  
	    params.append("审核人:").append("管理员");  
	            // 二维码图片生成路径  
	 // String path = "D:\\Project\\foshan_tobacco\\code\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\fsycsms\\images\\qrcodeImg\\";  
	    String path = "D:\\360Downloads\\Users\\591983829\\FileRecv\\swust-fee\\WebRoot\\EWM";
	//    String path = "D:\\EEWWMM";
	    try {  
	        String imgPath = GenerateQRCode.getInstance().generate(  
	                "你好",params.toString(), path, 200, 200);  
	          
	        System.out.println(imgPath);  
	
	        System.out.println("生成二维码成功");  
	
	    } catch (WriterException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  */

