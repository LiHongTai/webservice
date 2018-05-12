package com.roger.external.httpclient;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtilTest{

	private static final String APPLICATION_NAME = "MobileCodeWS.asmx";
	private static final String MEHTOD_NAME = "getMobileCodeInfo";
	
	@Test
	public void testPhoneGet() {
		JSONObject reqParam = new JSONObject();
		reqParam.put("mobileCode", "13855381409");
		reqParam.put("userID", "");
		JSONObject respResult = HttpRequestUtil.get(APPLICATION_NAME, MEHTOD_NAME, reqParam);
		String respString  = respResult.getString("respResult");
		
		assertTrue(respString.contains("芜湖"));
	}

	@Test
	public void testPhonePost() {
		JSONObject reqParam = new JSONObject();
		reqParam.put("mobileCode", "13855381409");
		reqParam.put("userID", "");
		
		JSONObject respResult = HttpRequestUtil.post(APPLICATION_NAME, MEHTOD_NAME, reqParam);
		String respString  = respResult.getString("respResult");
		
		assertTrue(respString.contains("安徽"));
	}

}
