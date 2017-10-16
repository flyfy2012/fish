package com.swust.unit.test;


import java.util.ArrayList;


public class MyMain {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println(StringUtils.startsWith("1020120084", "10"));
//		try {
////			System.out.println("uuid = "+StringUtil.convert(StringUtil.md5Byte(PushConf.DEFAULT_PRE_NAME+ "20128888")));
//			System.out.println("uuid = "+StringUtil.convert(StringUtil.md5Byte(PushConf.DEFAULT_PRE_NAME+"106190120127777")));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	/*测试*/	
//		String  json = "[\"love\",\"you\"]";
//		String [] m = {"love","you"};
//		String jsonStr = JsonUtils.StringArrayToJsonStr(m);
//		System.out.println(jsonStr);
//		String [] out = JsonUtils.JsonStrToStringArray(jsonStr);
//		for(int i=0;i<out.length;i++) {
//			System.out.println(out[i]);
//		}
		
//		byte m = 0x12;
//		int a = 123;
//		ByteBuffer mbuffer = ByteBuffer.allocate(5);
//		mbuffer.put(m);
//		mbuffer.putInt(a);
////		System.out.println( Integer.toHexString(m & 0xFF)+Integer.toHexString(a & 0xffff | 0x0000));
//		byte[] test = mbuffer.array();
//		System.out.println(Integer.toHexString(test[0] & 0xFF) +
//							Integer.toHexString(test[1] & 0xFF) + 
//							Integer.toHexString(test[2] & 0xFF) +
//							Integer.toHexString(test[3] & 0xFF)+
//							Integer.toHexString(test[4] & 0xFF));
//		System.out.println(mbuffer.getInt());
//		System.out.println(mbuffer.get());
		
		/*
		byte[] test = TcpPusher.StringToByteArray((byte)0x1, 12345, "今天开会了哈");
		for(int i=0;i<test.length;i++)
			System.out.println(Integer.toHexString(test[i] & 0xFF));
		
		
		String str = null;
//		Integer typeID,Integer contentID,String title
		ByteBuffer recvData = ByteBuffer.wrap(test, 0, 5);
		byte typeID = recvData.get();//类型
		
		Integer contentID = recvData.getInt(); //内容的ID
		
		try {
			//需要在原来的基础上多跳5个byte
			str = new String(test, 5,  
					test.length-5, "UTF-8");
		} catch (Exception e) {
			System.out.println("运行错误");
			e.printStackTrace();
		}
		
		System.out.println(typeID);
		System.out.println(contentID);
		System.out.println(str);
		*/
		
		
		//字符串的比较
		
//		  String s1="hello";
//	      String s2="hello";
//	      String s3=new String(s1);
//	      
//	      System.out.println(s3);
//	      
//	      if(s1==s2){
//	          System.out.print("true,");
//	      }else{
//	          System.out.print("false,");
//	      }
//	      if(s1.equals(s3)){
//	          System.out.println("true");
//	      }else{
//	          System.out.println("false");
//	      }
//		String username = "20126125";
//		String password = "05271021";
		
		
		String username = "20124438";
		String password = "010157";
		
		
		ArrayList<String> dataList = new ArrayList<String>();
		
		
		 
		 System.out.println(dataList.get(0));
	   }
	
	
	

	}


