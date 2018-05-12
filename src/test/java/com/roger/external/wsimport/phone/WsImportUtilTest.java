package com.roger.external.wsimport.phone;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WsImportUtilTest {
	
	@Test
	public void testMobileCodeWS() {
		//创建一个ws服务
		MobileCodeWS ws = new MobileCodeWS();
		//获取具体服务类型 get, post, soap1.1, soap1.2
		MobileCodeWSSoap wsSoap = ws.getMobileCodeWSSoap();
		
		String mobileCodeInfo = wsSoap.getMobileCodeInfo("13855381409", null);
		assertTrue(mobileCodeInfo.contains("芜湖"));
	}
	

}
