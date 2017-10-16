package com.swust.utils;

import java.awt.Image;
import java.io.*;    
import java.awt.image.*;  

import javax.imageio.ImageIO;  


  
/**
 * 类说明：
 * @author 蒋和松 E-mail:17904127@qq.com
 * @version 创建时间：2015年5月15日 上午8:36:31
 * 
 */
public class EduImgCompress {
 	private Image img;  
    private int width;  
    private int height; 
    private  File srcFile;
    private  File destFile;
    /** 
     * 构造函数 
     */  
    public EduImgCompress(String srcfileName,String destFileName) throws IOException {  
    	srcFile = new File(srcfileName);// 读入文件  
        img = ImageIO.read(srcFile);      // 构造Image对象  
        destFile = new File(destFileName); 
        
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
    }  
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public boolean compressPic(int w, int h)  { 
    	int needCompress = 0;
    	
        if (width / height > w / h) {  
        	needCompress = w;
        } else {  
        	needCompress =h;
        }    
        
        
        try {
			resizeByHeight(needCompress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}   
        return true;
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public void resizeByWidth(int w) throws IOException {  
        int h = (int) (height * w / width);  
        resize(w, h);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public void resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    private void resize(int w, int h) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
         
        
        
        
        // 可以正常实现bmp、png、gif转jpg  
//        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
//        encoder.encode(image); // JPEG编码  
        
//        String formatName = destFile.substring(destFile.lastIndexOf(".") + 1);  
        ImageIO.write(image, "jpeg", destFile);  
        
//        out.close();  
    }  
}
