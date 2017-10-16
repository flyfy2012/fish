package com.wechat.pay.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dom4j.Document;  
import org.dom4j.DocumentException;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  
import org.dom4j.Namespace;  
import org.dom4j.QName;

public class XmlTools {
	
	/**
	 * @category 将map转为xml
	 * @Description 
	 * @author hmilysean
	 * @date 2016年1月25日 下午2:01:12 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static StringBuffer callMapToXML(Map map) {  
        
        StringBuffer sb = new StringBuffer();  
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml>");  
        mapToXMLTest2(map, sb);  
        sb.append("</xml>");  
        return sb;
//        try {  
//            return sb.toString().getBytes("UTF-8");  
//        } catch (Exception e) {  
//          
//        }  
//        return null;  
    }  
  
	/**
	 * @category 拼接xml格式
	 * @Description 
	 * @author hmilysean
	 * @date 2016年1月25日 下午2:00:46 
	 * @param map
	 * @param sb
	 */
    @SuppressWarnings("rawtypes")
	private static void mapToXMLTest2(Map map, StringBuffer sb) {  
        Set set = map.keySet();  
        for (Iterator it = set.iterator(); it.hasNext();) {  
            String key = (String) it.next();  
            Object value = map.get(key);  
            if (null == value)  
                value = "";  
            if (value.getClass().getName().equals("java.util.ArrayList")) {  
                ArrayList list = (ArrayList) map.get(key);  
                sb.append("<" + key + ">");  
                for (int i = 0; i < list.size(); i++) {  
                    HashMap hm = (HashMap) list.get(i);  
                    mapToXMLTest2(hm, sb);  
                }  
                sb.append("</" + key + ">");  
  
            } else {  
                if (value instanceof HashMap) {  
                    sb.append("<" + key + ">");  
                    mapToXMLTest2((HashMap) value, sb);  
                    sb.append("</" + key + ">");  
                } else {  
                    sb.append("<" + key + ">" + value + "</" + key + ">");  
                }  
  
            }  
  
        }  
    }  
    
    /**
     * @category xml2map
     * @Description 
     * @author hmilysean
     * @date 2016年1月25日 下午2:17:05 
     * @param xmlString
     * @return
     * @throws DocumentException
     */
    @SuppressWarnings("rawtypes")
	static Map xml2map(String xmlString) throws DocumentException {  
    	  Document doc = DocumentHelper.parseText(xmlString);  
    	  Element rootElement = doc.getRootElement();  
    	  Map<String, Object> map = new HashMap<String, Object>();  
    	  ele2map(map, rootElement);  
    	  return map;  
    	 }  
    
    /*** 
     * 核心方法，里面有递归调用 
     *  
     * @param map 
     * @param ele 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	static void ele2map(Map map, Element ele) {  
     
     // 获得当前节点的子节点  
     List<Element> elements = ele.elements();  
     if (elements.size() == 0) {  
      // 没有子节点说明当前节点是叶子节点，直接取值即可  
      map.put(ele.getName(), ele.getText());  
     } else if (elements.size() == 1) {  
      // 只有一个子节点说明不用考虑list的情况，直接继续递归即可  
      Map<String, Object> tempMap = new HashMap<String, Object>();  
      ele2map(tempMap, elements.get(0));  
      map.put(ele.getName(), tempMap);  
     } else {  
      // 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的  
      // 构造一个map用来去重  
      Map<String, Object> tempMap = new HashMap<String, Object>();  
      for (Element element : elements) {  
       tempMap.put(element.getName(), null);  
      }  
      Set<String> keySet = tempMap.keySet();  
      for (String string : keySet) {  
       Namespace namespace = elements.get(0).getNamespace();  
       List<Element> elements2 = ele.elements(new QName(string,namespace));  
       // 如果同名的数目大于1则表示要构建list  
       if (elements2.size() > 1) {  
        List<Map> list = new ArrayList<Map>();  
        for (Element element : elements2) {  
         Map<String, Object> tempMap1 = new HashMap<String, Object>();  
         ele2map(tempMap1, element);  
         list.add(tempMap1);  
        }  
        map.put(string, list);  
       } else {  
        // 同名的数量不大于1则直接递归去  
        Map<String, Object> tempMap1 = new HashMap<String, Object>();  
        ele2map(tempMap1, elements2.get(0));  
        map.put(string, tempMap1);  
       }  
      }  
     }  
    }  
}
