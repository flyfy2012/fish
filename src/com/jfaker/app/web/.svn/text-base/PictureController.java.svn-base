package com.jfaker.app.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.bjui.BjuiRender;
import com.jfinal.upload.UploadFile;
import com.swust.cfg.Preference;
import com.swust.utils.EduImgCompress;

/**
 * 
 * @Description 所有上传图片放到这个地方:新闻图片、证件图片。
 * @author Administrator
 * @date 2015年8月15日 下午4:41:14 
 * @version V1.3.1
 */
public class PictureController extends Controller{
	/**
	 * 编辑器上传新闻
	 */
	public void newsUpload(){
		//用于保存上传错误，暂时用不到
		String err = "";
		//存储路径
		String path = getSession().getServletContext().getRealPath(Preference.UPLOAD_NEWS_DIR);
		UploadFile file = getFile("filedata", path);
		String fileName = "";
		if(file.getFile().length() > 5*1024*1024) {
			err = "文件不能大于5M";
			render(BjuiRender.error(err)); //大于多少时，返回错误码
			return;
		}
		else {
			//上传文件
			String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀
			fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀
			String dest = path + "/" + fileName;
			file.getFile().renameTo(new File(dest));
			
			try {
				new EduImgCompress(dest,path + "/" +"thumb_"+ fileName).compressPic(128, 128);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String realPic = Preference.UPLOAD_NEWS_DIR +  fileName;
		
		render(BjuiRender.pic(realPic));
		//直接传回完整路径
		//renderJson("{\"err\":\"" + err + "\",\"msg\":\"" + basePath + "/" + Preference.UPLOAD_XHEDITOR_NEWS_DIR +  fileName + "\"}");
	}
	
	public void kindEditorUpload(){
	//	HttpServletRequest request = getRequest();

		//用于保存上传错误，暂时用不到
		String err = "";
		//存储路径
		String path = getSession().getServletContext().getRealPath(Preference.UPLOAD_KINDEDTOR_DIR);
		UploadFile file = getFile("imgFile", path);
		String fileName = "";
		if(file.getFile().length() > 5*1024*1024) {
			err = "文件不能大于5M";
			render(BjuiRender.error(err)); //大于多少时，返回错误码
			return;
		}
		else {
			//上传文件
			String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀
			fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀
			String dest = path + "/" + fileName;
			file.getFile().renameTo(new File(dest));
			
			if(type.equals(".jpg") || type.equals(".bmp") || type.equals(".jpeg") 
					|| type.equals(".gif") || type.equals(".png")){ //如果是图片就压缩
				
				try {
					new EduImgCompress(dest,path + "/" +"thumb_"+ fileName).compressPic(128, 128);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}
		String realPic =Preference.UPLOAD_KINDEDTOR_DIR +  fileName;
//		URL
		//直接传回完整路径
		renderJson("{\"error\":0,\"url\":\"" + realPic + "\"}");
	}
	
	
	
	/**
	 * @Description 用于头像上传
	 * @author inging44
	 */
	public void avatarUpload(){
		//用于保存上传错误，暂时用不到
		String err = "0";
		//存储路径
		String path = getSession().getServletContext().getRealPath(Preference.UPLOAD_AVATAR_DIR);
		UploadFile file = getFile("avatar", path);
		String fileName = "";
		if(file.getFile().length() > 120*1024) {
			err = "-1";
		}
		else {
			//上传文件
			String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀
			fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀
			String dest = path + "/" + fileName;
			file.getFile().renameTo(new File(dest));
			
			try {
				new EduImgCompress(dest,path + "/" +"thumb_"+ fileName).compressPic(128, 128);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(StringUtils.endsWithIgnoreCase(err, "-1")){
			renderJson("{\"error\":\"" + err + "\",\"message\":\""  + "文件大于了120K" + "\"}");
		}else{
			renderJson("{\"error\":\"" + err + "\",\"message\":\""  + Preference.UPLOAD_AVATAR_DIR +  fileName + "\"}");
		}
		//直接传回完整路径
	}
	
	
	
	
	
	/**
	 * 
	 * @Description闪屏内容的上传 
	 * @author hmilysean
	 * @date 2015-10-7 下午08:07:12
	 */
	public void splashUpload(){
		HttpServletRequest request = getRequest();
		String basePath = request.getContextPath();
		//用于保存上传错误，暂时用不到
		String err = "";
		System.out.println("传送图片");
		//存储路径
		String path = getSession().getServletContext().getRealPath(Preference.UPLOAD_SPLASH_DIR);
		UploadFile file = getFile("filedata", path);
		String fileName = "";
		if(file.getFile().length() >  5*1024*1024) {
			err = "文件不能大于5M";
			render(BjuiRender.error(err)); //大于多少时，返回错误码
			return;
		}
		else {
			//上传文件
			String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀
			fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀
			String dest = path + "/" + fileName;
			file.getFile().renameTo(new File(dest));
			
			try {
				new EduImgCompress(dest,path + "/" +"thumb_"+ fileName).compressPic(128, 128);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String realPic = basePath + "/" + Preference.UPLOAD_SPLASH_DIR +  fileName;
		System.out.println(realPic);
		render(BjuiRender.pic(realPic));
		//直接传回完整路径
		//renderJson("{\"err\":\"" + err + "\",\"msg\":\"" + basePath + "/" + Preference.UPLOAD_NEWS_DIR +  fileName + "\"}");
	}
	
	
	
}
