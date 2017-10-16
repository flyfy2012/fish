package com.swust.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfinal.kit.PathKit;
import com.swust.cfg.Preference;


public class ExcelTool {

	/**
	 * 工作表文件
	 */
	//private static  HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
	/**
	 * 工作表
	 */
//	private static  HSSFSheet hssfSheet = hssfWorkbook.createSheet("write");

	
	File file;
	/**
	 * 避免可能引发的文件错误对其他文件处理的影响
	 * @return 一个ExcelTool实例
	 */
	public static ExcelTool getInstance() {
		ExcelTool eTool =  new ExcelTool();
		eTool.file = new File(PathKit.getWebRootPath()  + Preference.EXCEL_TEMP_PATH);
		//eTool.file = new File("G:\\" + generateTimeString() +".xls");
		
        if(!eTool.file.exists()){  
        	eTool.file.mkdirs();  
        }  
        
        
		return eTool;
	}


	/**
	 * 
	 * @param s 表头
	 * @param sql 得到数据的sql语句
	 * @param file 工作表文件引用
	 */
	public  File writeExcel(List<String> title,List<List<String>>  elements,String userName){
		int  count = 0;
		// 创建工作表
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("write");
	    
		// 创建行
		HSSFRow row = hssfSheet.createRow(0);
		// 创建单元格，设置表头 创建列
		HSSFCell cell = null;
		//遍历表头
		int sSize = title.size();
		for (int i = 0; i < sSize; i++) {
			//创建传入进来的表头的个数
			cell = row.createCell((short) i);
			//表头的值就是传入进来的值
			//	cell.setCellValue(s[i]);
			cell.setCellValue(new HSSFRichTextString(title.get(i)));

		}
		//新增一个行就累加
		//row = hssfSheet.createRow(++count);




		if (elements != null) {
			//获取所有的记录 有多少条记录就创建多少行
			for (int i = 0; i < elements.size(); i++) {
				row = hssfSheet.createRow(++count);
				// 得到所有的行 一个record就代表 一行
				List<String> r = elements.get(i);
				//在有所有的记录基础之上，便利传入进来的表头,再创建N行
				for (int j = 0; j < sSize; j++) {
					cell = row.createCell((short) j);
					//把每一行的记录再次添加到表头下面 如果为空就为 "" 否则就为值
					cell.setCellValue(new HSSFRichTextString((r.get(j)==null?"":r.get(j))));
				}
			}
		}
		//generateTimeString()+ generateWordString()
		try {
			FileOutputStream fileOutputStreane = new FileOutputStream(file.getPath()+"/" +userName);
			hssfWorkbook.write(fileOutputStreane);
			fileOutputStreane.flush();
			fileOutputStreane.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
		
		
		
	}
	
	 private static  String generateTimeString() {
		 Date t = Calendar.getInstance().getTime();
		 SimpleDateFormat df=new SimpleDateFormat("yyMMddHHmmss"); 
		return df.format(t);
	 }

	  private  static String generateWordString() {
		String[] beforeShuffle = new String[] {"1", "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(5, 9);
		return result;
	}

}