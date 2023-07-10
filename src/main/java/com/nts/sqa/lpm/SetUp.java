package com.nts.sqa.lpm;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import com.nts.gt.auto.appium.AndroidUtil;
import com.nts.gt.auto.common.Automation;
import com.nts.gt.auto.common.Formatter;
import com.nts.gt.auto.getset.DeviceInfo;
import com.nts.gt.auto.selenium.ChromeUtil;
import com.tool.testrail.*;


public class SetUp extends Formatter {
	public StoreInfoPageElements spe = new StoreInfoPageElements();
	public Payment pay = new Payment();
	public ChromeUtil chrome;
	public APIClient client = new APIClient("https://sqa.navercorp.com/testrail");
	public String testRunNum = "48583";			// Testrun 
	public Map pass = new HashMap();
	public int CaseNum = 13030479;
	
	@BeforeClass
	public void setUp() throws Exception {
		chrome = (ChromeUtil) new Automation()
				.pc()
				.chrome()
				.selectChromeBrowser("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
						"C:\\Users\\USER\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe")
				.mainUrl(spe.targetUrl)
				.useZaProxy(false)
				.logLevel("error")
				.start();
		
		
		
		
		// Testrail 설정
		client.setUser("linepaymerchant@nts-corp.com");    // testrail id 입력(메일주소)
        client.setPassword("Linepay123!");             	 // testrail pw 입력

        
        pass.put("status_id", new Integer(1));
        pass.put("comment", "Passed by automation");
        
	}

}
