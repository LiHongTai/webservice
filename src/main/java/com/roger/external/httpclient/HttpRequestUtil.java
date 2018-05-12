package com.roger.external.httpclient;

import java.io.File;
import java.io.FileInputStream;

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
		getMethod.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
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
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
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
	
	/**
	 * SOAP 也是 http post请求
	 * @param applicationName
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static JSONObject soap(String applicationName,String reqParamPath) {
		JSONObject respResult = new JSONObject();
		//Step1 : 创建浏览器
		HttpClient httpClient = new HttpClient();
		//Step2 : 添加数据和构造uri地址
		String uri = HOST_IP + applicationName;
		
		PostMethod postMethod = new PostMethod(uri);
		postMethod.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
		//Step3 : 发送请求
		try {
			FileInputStream in = new FileInputStream(new File(reqParamPath));
			postMethod.setRequestBody(in);
			httpClient.executeMethod(postMethod);
			respResult.put("respResult", postMethod.getResponseBodyAsString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return respResult;
	}
}
