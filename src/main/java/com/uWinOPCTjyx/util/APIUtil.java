package com.uWinOPCTjyx.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafish.clients.opc.component.OpcItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIUtil {

	public static final String SERVICE_URL="http://localhost:8080/UWinOPCTjyx/opc/";

	public static JSONObject doHttp(String method, List<OpcItem> params) {
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		JSONObject resultJO = null;
		String result = "";
		try {
			URL url = new URL(SERVICE_URL+method);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");//请求post方式
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//header内的的参数在这里set
			//connection.setRequestProperty("key", "value");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
			//获取URLConnection对象对应的输出流
			writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			//OutputStream writer = connection.getOutputStream();
			//发送请求参数
			writer.write(params.toString());
			// flush输出流的缓冲
			writer.flush();
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				result += strRead;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try{
				if(writer!=null){
					writer.close();
				}
				if(reader!=null){
					reader.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return resultJO;
	}

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

	public static JSONObject addTrigger(List triggerList) {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
			resultJO = doHttp("addPiCiU",triggerList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
}
