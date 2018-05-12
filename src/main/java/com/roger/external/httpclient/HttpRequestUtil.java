package com.roger.external.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtil {

	private static final String HOST_IP = "http://www.webxml.com.cn/WebServices/";
	
	public static JSONObject get(String applicationName,String methodName,JSONObject reqParam) {
		JSONObject respResult = new JSONObject();
		//Step1 : 创建浏览器
		HttpClient httpClient = new HttpClient();
		//Step2 : 添加数据和构造uri地址
		String url = HOST_IP + applicationName + "/" + methodName;
		StringBuilder reqString = new StringBuilder();
		
		reqParam.keySet().forEach((key)->{
			if(!"".equals(reqString.toString())) {
				reqString.append("&");
			}
			reqString.append(key).append("=").append(reqParam.getString(key));
		});
		
		String uri =  url + "?" + reqString.toString();
		GetMethod getMethod = new GetMethod(uri);
		//Step3 : 发送请求
		try {
			httpClient.executeMethod(getMethod);
			respResult.put("respResult", getMethod.getResponseBodyAsString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return respResult;
	}
	
	public static JSONObject post(String applicationName,String methodName,JSONObject reqParam) {
		JSONObject respResult = new JSONObject();
		//Step1 : 创建浏览器
		HttpClient httpClient = new HttpClient();
		//Step2 : 添加数据和构造uri地址
		String uri = HOST_IP + applicationName + "/" + methodName;
		
		PostMethod postMethod = new PostMethod(uri);
		
		reqParam.keySet().forEach((key)->{
			postMethod.setParameter(key, reqParam.getString(key));
		});
		
		//Step3 : 发送请求
		try {
			httpClient.executeMethod(postMethod);
			respResult.put("respResult", postMethod.getResponseBodyAsString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return respResult;
	}
}
