package com.nts.sqa.lpmtest;
import org.testng.annotations.Test;
import com.nts.sqa.lpm.Payment;

import com.nts.sqa.lpm.SetUp;
import com.ntscorp.auto_client.verity.Verify;
import com.tool.testrail.APIException;
import Utils.MktpUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasItems;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.FileDetector;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

import static junit.framework.TestCase.assertTrue;


public class Application extends SetUp{	
	
	@Test(description="Center Home에서 로그인 페이지로 이동")
	public void s01() throws MalformedURLException, IOException, APIException {
		chrome.click(By.xpath("html/body/div[1]/header/div/div/div[2]/a[1]"));
//		chrome.sleep(1);
		chrome.click(By.xpath("//*[@id=\"twTabLine\"]/a"));
		chrome.sleep(1);
		
//		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + testRunNum + "/4896795", pass);
	}

	@Test(description="login")
	public void s02() throws MalformedURLException, IOException, APIException {
		Verify.verifyTrue(chrome.isElementPresent(By.id("id")));
		chrome.type(By.id("id"), spe.sId);
//		chrome.sleep(1);
		Verify.verifyTrue(chrome.isElementPresent(By.id("password")));
		chrome.type(By.id("password"), spe.sPassword);
//		chrome.sleep(1);
		Verify.verifyTrue(chrome.isElementPresent(By.id("loginActionButton")));
		chrome.click(By.id("loginActionButton"));
//		chrome.sleep(1);
		
//		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + testRunNum + "/4896802", pass);
		
	}
	
	@Test(description="change Language Chinese to English")
	public void s03() throws MalformedURLException, IOException, APIException {
		if(chrome.isElementPresent(By.id("urgentPromotionCloseButton"))) {
			chrome.click(By.id("urgentPromotionCloseButton"));
		} 
		Verify.verifyTrue(chrome.isElementPresent(By.id("selLang")));
		chrome.click(By.id("selLang"));
		chrome.sleep(1);
		Assert.assertEquals(chrome.getText(By.xpath("//*[@id=\"gnb\"]/li[1]/a")), "HOME");

		if(chrome.isElementPresent(By.id("urgentPromotionCloseButton"))) {
			chrome.click(By.id("urgentPromotionCloseButton"));
		} 
//		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + testRunNum + "/4896803", pass);
		chrome.sleep(2);
	}
	
	@Test(description="Enter MKTP")
	public void s04() throws MalformedURLException, IOException, APIException {
		chrome.get("https://pay.line-beta.me/marketing/tw/application/agreementItem");
		chrome.sleep(1);
	}
	
	@Test(description="Apply SPC_default")
	public void s05() throws MalformedURLException, IOException, APIException {
		// 상품 타입 선택
		chrome.click(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div[1]/ul/li[1]/a"));
//		chrome.sleep(1);
		chrome.click(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div[2]/button"));
		chrome.sleep(1);
//		
	}
	@Test(description="select period")
	public void s06 () {
		chrome.click(By.xpath("//*[@id=\"daterangepicker1-container\"]/div/div[2]/div[1]/table/tbody/tr[3]/td[5]/span"));
//		chrome.sleep(1);
		chrome.click(By.xpath("//*[@id=\"daterangepicker1-container\"]/div/div[2]/div[1]/table/tbody/tr[3]/td[5]/span"));
//		chrome.sleep(1);
		chrome.click(By.xpath("//*[@id=\"_next\"]"));
		chrome.sleep(1);
	}

	
	@Test(description="select product type")
	public void s07() {
		
		// 프로모션 타입 선택
		chrome.click(By.xpath("//*[@id=\"wrap\"]/div/div[3]/div[1]/ul/li[1]"));
//		chrome.sleep(1);
		chrome.click(By.xpath("//*[@id=\"saveButton\"]"));
		chrome.sleep(1);
		
	}
		// 리뷰 페이지 결제 진행
//		LogEntries logs = chrome.manage().logs().get(LogType.BROWSER);
//		for (LogEntry log : logs) {
//			System.out.print("Test: ");
//			System.out.println(log.getMessage());
//		}
	
	@Test(description="payment")
	public void s08() {
		
//		chrome.click(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div[1]/div[6]/div[3]/label"));
//		chrome.sleep(1);
		
		chrome.click(By.xpath("//*[@id=\"payBtn\"]"));
		chrome.sleep(1);
		
	}
	
	
	@Test(description="API Payment")
	public void s09() {
		chrome.switchToNewWindow();
		pay.trxReserveId = chrome.getCurrentUrl().split("transactionReserveId=")[1];
		System.out.println(pay.trxReserveId);
		
		JSONObject headers = new JSONObject(); 
		headers.put("X-LP-MID", pay.mid); 
		headers.put("X-LP-ClientInfo", "ANDROID_BETA 11.8.0 Android OS 10"); 
//		Map<String, String> Headers = new HashMap<String, String>();
//		Headers.put("X-LP-MID", pay.mid); 
//		Headers.put("X-LP-ClientInfo", "ANDROID_BETA 11.8.0 Android OS 10"); 
		
		
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("transactionReserveId", pay.trxReserveId); 
					
		// Request Payment
		System.out.println("Request Payment");
		Response r = given().
		headers(headers).
		header("Content-type", "application/json").
		body(requestParams.toString()).
//		log().all().
		post(pay.baseURI + "/v3/payment/request/get").
		then().
		log().all().
		extract().response();
		
		int amount = r.jsonPath().get("info.amountInfo.productAmount.amount");
	
		System.out.println(amount);
//		// Payment
//		System.out.println("Payment");
//		Response res = given().
//		when().
//		get(pay.baseURI + "/v1/line-users/" + pay.mid + "/tokens/issue").
//		then().
//		log().all()
//		.extract().response();
//		String requestToken = res.jsonPath().getString("info.requestToken");
//		
//		// RSA Key 발급 및 Password encryption
//		System.out.println("RSA Key/Password encryption");
//		JSONObject payload = new JSONObject(); 
//		payload.put("data", pay.password); 
//		
//		Response keys = given().
//		header("Content-type", "application/json").
//		body(payload.toString()).
////		log().all().
//		post(pay.baseURI + "/v1/rsa/encrypt").
//		then().
//		log().all().
//		extract().response();
//		
//		String password = keys.jsonPath().get("info.encryptedText");
//		String keyName = keys.jsonPath().get("info.keyName");
//		
//		//본 API 호출
//		System.out.println("Payment API");
//		JSONObject PaymentHeader = new JSONObject(); 
//		PaymentHeader.put("X-LP-MID", pay.mid); 
//		PaymentHeader.put("X-LP-ClientInfo", "ANDROID_BETA 11.8.0 Android OS 10"); 
//		PaymentHeader.put("x-lp-region", "TW");
//		
//		JSONArray payments = new JSONArray();
//		JSONArray additionalAgreements = new JSONArray();
//		
//		JSONObject obj1 = new JSONObject();
//		obj1.put("amount", Integer.toString(amount));
//		obj1.put("currency", "TWD");
//		obj1.put("method", "CREDIT_CARD");
//		obj1.put("lpAccountNo", pay.lpAccountNo);
//		payments.put(obj1);
//		
//		JSONObject obj2 = new JSONObject();
//		obj2.put("type", "LINE_PAY_OA");
//		obj2.put("checkYn", "Y");
//		additionalAgreements.put(obj2);
//		
//		
//		JSONObject PaymentRequestBody = new JSONObject();
//		PaymentRequestBody.put("transactionReserveId", pay.trxReserveId);
//		PaymentRequestBody.put("requestToken", requestToken);
//		PaymentRequestBody.put("keyName", keyName);
//		PaymentRequestBody.put("password", password);
//		
//		PaymentRequestBody.put("payments", payments);
//		PaymentRequestBody.put("additionalAgreements", additionalAgreements);
//		
//		
//		given().
//		headers(PaymentHeader).
//		header("Content-type", "application/json").
//		body(PaymentRequestBody.toString()).
//		log().all().
//		post(pay.baseURI + "/v3/payment/request/authorize").
//		then().
//		log().all();	
//		
//		
//		// Calculate Amount
//		given().
//		headers(Headers).
//		header("Content-type", "application/json").
//		body(requestParams.toString()).
//		post(pay.baseURI + "/v3/payment/request/calculate").
//		then().
//		log().all();
//		
//		// Get authorization
//		given().
//		headers(Headers).
//		header("Content-type", "application/json").
//		body(requestParams.toString()).
//		post(pay.baseURI + "/v3/payment/authorization/get").
//		then().
//		log().all();
//		
//		// Confirm authorization
//		given().
//		headers(Headers).
//		header("Content-type", "application/json").
//		body(requestParams.toString()).
//		post(pay.baseURI + "/v3/payment/authorization/confirm").
//		then().
//		log().all();
//		
//		chrome.sleep(10);
	}
	
}
//
//		chrome.switchToNewWindow();
//		chrome.sleep(2);
//		
//		String title = chrome.getTitle();
//		
////		// 카드번호 직접 입력	
//		chrome.click(By.xpath("//*[@id=\"input\"]"));
//		chrome.sleep(2);
//		chrome.type(By.xpath("//*[@id=\"input\"]"), "24111111111111111"); // 첫글자 입력 씹힘 왜Y?
//		chrome.sleep(2);
//		
//		chrome.click(By.xpath("//*[@id=\"input2\"]"));
//		chrome.sleep(1);
//		chrome.type(By.xpath("//*[@id=\"input2\"]"), "0825");
//		chrome.sleep(1);
//		
//		chrome.click(By.xpath("//*[@id=\"input3\"]"));
//		chrome.sleep(1);
//		chrome.type(By.xpath("//*[@id=\"input3\"]"), "123");
//		chrome.sleep(1);
//		
//		chrome.click(By.xpath("//*[@id=\"payment_view_form\"]/div/main/div/div/article[3]/ul/li[1]/label"));
//		chrome.sleep(1);
//		
//		chrome.click(By.xpath("//*[@id=\"payment_view_form\"]/div/main/div/div/div/button"));
//		chrome.sleep(2);
//		
//		chrome.click(By.xpath("/html/body/div[2]/div/div[2]/button"));
//		chrome.sleep(3);
//		
//		
//		
//		chrome.switchToNewWindow();
//		
//		chrome.click(By.xpath("/html/body/div/div/div/div/div/a"));
////		chrome.sleep(3);
//		
//		chrome.sleep(2);
//		
//		// switch로 이전윈도우 찾기?
//		Set<String> allWindowHandles = chrome.getWindowHandles(); // https://www.toolsqa.com/selenium-webdriver/window-handle-in-selenium/
//		Iterator<String> iterator = allWindowHandles.iterator();
//		
//		
//		while (iterator.hasNext()) {
//			String ChildWindow = iterator.next();
//			if(chrome.isElementPresent(By.className("btn _pay"))) {
//				chrome.click(By.className("btn _pay"));
//				chrome.sleep(1);
//				break;
//			}
//			chrome.switchToWindow(ChildWindow);
//		}
		
//		chrome.switchToWindow(title);
//		chrome.sleep(1);
//		chrome.click(By.className("btn _pay"));
//		chrome.sleep(1);
		
		
//		Map pass = new HashMap();
//		pass.put("status_id", new Integer(1));
//        pass.put("comment", "Standard Point Coupon 신청 완료");
//        
//        
//		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + testRunNum + "/4910838", pass);
//		chrome.sleep(2);
//		
		
		// 결제 API 
//		String url = chrome.getCurrentUrl();
//		// https://web-pay.line-beta.me/web/payment/waitPreLogin?transactionReserveId=dGRDbE9xaUZMdFhZMUxsOWtFcjR6ZjdjYnV5US9kcmtJNjd3Q3Q3U3B2bTd2MFNkR0hhL0lNSmhaWDk0bGtCUQ
//		
//		String transactionReserveid = url.split("=")[1];
//		MktpUtil.setTransactionReserveId(transactionReserveid);
//	
//		
//		Results results = Runner.path("classpath:features").parallel(1);  // TOBE
