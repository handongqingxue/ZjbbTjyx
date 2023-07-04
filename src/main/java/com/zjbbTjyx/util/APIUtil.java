package com.zjbbTjyx.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import javafish.clients.opc.component.OpcItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;

public class APIUtil {

	public static final String SERVICE_URL="http://localhost:8080/ZjbbTjyx/opc/";

	public static JSONObject doHttp(String method, Map<String, Object> params) {
		JSONObject resultJO = null;
		try {
			// 构建请求参数  
			StringBuffer paramsSB = new StringBuffer();
			if (params != null) {  
			    for (Entry<String, Object> e : params.entrySet()) {
			    	paramsSB.append(e.getKey());  
			    	paramsSB.append("=");  
			    	paramsSB.append(e.getValue());  
			    	paramsSB.append("&");  
			    }  
			    paramsSB.substring(0, paramsSB.length() - 1); 
			}  
			
			StringBuffer sbf = new StringBuffer(); 
			String strRead = null; 
			URL url = new URL(SERVICE_URL+method);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");//请求post方式
			connection.setDoInput(true); 
			connection.setDoOutput(true); 
			//header内的的参数在这里set    
			//connection.setRequestProperty("key", "value");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect(); 

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			//OutputStream writer = connection.getOutputStream();
			writer.write(paramsSB.toString());
			writer.flush();
			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();

			connection.disconnect();
			String result = sbf.toString();
			resultJO = new JSONObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject doHttp(String method, JSONArray bodyParamJA) {
		JSONObject resultJO = null;
		try {
			StringBuffer sbf = new StringBuffer(); 
			String strRead = null; 
			URL url = new URL(SERVICE_URL+method);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");//请求post方式
			connection.setDoInput(true); 
			connection.setDoOutput(true); 
			//header内的的参数在这里set    
			//connection.setRequestProperty("key", "value");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect(); 
			
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); 
			//body参数放这里
			String bodyParamStr = bodyParamJA.toString();
			//System.out.println("bodyParamStr==="+bodyParamStr);
			writer.write(bodyParamStr);
			writer.flush();
			InputStream is = connection.getInputStream(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead); 
				sbf.append("\r\n"); 
			}
			reader.close();
			
			connection.disconnect();
			String result = sbf.toString();
			System.out.println("result==="+result);
			resultJO = new JSONObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject addPiCiU(String scrq) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("scrq", scrq);
	        resultJO = doHttp("addPiCiU",params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static void addVar(String method,List<OpcItem> opcItemList) {

		try {
			JSONArray ja=new JSONArray();
			JSONObject jo=null;
			for (OpcItem opcItem : opcItemList) {
				String itemName = opcItem.getItemName();
				String value = opcItem.getValue().toString();
				if(itemName.startsWith(Constant.FAN_YING_JIE_SHU)||
				   itemName.startsWith("红色报警消音")) {
					boolean boolVal = Boolean.parseBoolean(value);
					value = boolVal?"1":"0";
				}
				
				jo=new JSONObject();
				jo.put("varName", itemName);
				jo.put("varValue", value);
				
				ja.put(jo);
			}
			doHttp(method,ja);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
