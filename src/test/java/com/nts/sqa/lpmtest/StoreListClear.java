package com.nts.sqa.lpmtest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nts.sqa.lpm.SetUp;
import com.ntscorp.auto_client.verity.Verify;

public class StoreListClear extends SetUp {
	
	@Test(description="Seller Center Home에서 로그인 페이지로 이동")
	public void s01() {
		chrome.click(By.xpath("html/body/div[1]/header/div/div/div[2]/a[1]"));
		chrome.sleep(2);
		chrome.click(By.xpath("//*[@id=\"twTabLine\"]/a"));
		chrome.sleep(1);
	}

	@Test(description="login")
	public void s02() {
		Verify.verifyTrue(chrome.isElementPresent(By.id("id")));
		chrome.type(By.id("id"), spe.sId);
		chrome.sleep(1);
		Verify.verifyTrue(chrome.isElementPresent(By.id("password")));
		chrome.type(By.id("password"), spe.sPassword);
		chrome.sleep(1);
		Verify.verifyTrue(chrome.isElementPresent(By.id("loginActionButton")));
		chrome.click(By.id("loginActionButton"));
		chrome.sleep(3);
	}
	
	@Test(description="change Language Chinese to English")
	public void s03() {
		if(chrome.isElementPresent(By.id("urgentPromotionCloseButton"))) {

		} else {
			chrome.click(By.id("urgentPromotionCloseButton"));
		}
		Verify.verifyTrue(chrome.isElementPresent(By.id("selLang")));
		chrome.click(By.id("selLang"));
		chrome.sleep(3);
		Assert.assertEquals(chrome.getText(By.xpath("//*[@id=\"gnb\"]/li[1]/a")), "HOME");

		if(chrome.isElementPresent(By.id("urgentPromotionCloseButton"))) {

		} else {
			chrome.click(By.id("urgentPromotionCloseButton"));
		}
	}
	
	@Test(description="Move to Store Information page")
	public void s04() {
		// Manage Basic Info
		chrome.click(By.xpath("//*[@id=\"container\"]/div[1]/ul/li[2]/a")); 
		
		// Store Information
		chrome.click(By.xpath("//*[@id=\"container\"]/div[1]/ul/li[2]/ul/li[3]/a"));
		chrome.sleep(2);
		Assert.assertEquals(chrome.getText(By.xpath("//*[@id=\"content\"]/div/div[1]/h2")), "Store Information");
	}
	
	/*
	 * Start Test
	 */

    //  //*[@id="content"]/div/div[3]/div[2]/table/tbody/tr[2]/td[1] 있으면, 삭제하기
	// //*[@id="content"]/div/div[3]/div[2]/table/tbody/tr[2]/td[11]/button   Delete 버튼
	@Test(description="Delete 버튼을 눌렀다가 취소하면 리스트가 그대로 있는지 확인")
	public void tc01_cancelDeleteList() {
		String secondRow = "//*[@id=\"content\"]/div/div[3]/div[2]/table/tbody/tr[2]/td[1]";
		String delBtn = "//*[@id=\"content\"]/div/div[3]/div[2]/table/tbody/tr[2]/td[11]/button";
		
		String sListNo = "1";
		String noXpath = "//*[@id=\"content\"]/div/div[3]/div[2]/table/tbody/tr[1]/td[1]";
		int listNo = 1;
		sListNo = chrome.getText(By.xpath(noXpath));
		
		if(listNo < Integer.parseInt(sListNo)) {
			Alert popUp = chrome.getAlert(By.xpath(delBtn));  //chrome.click(By.xpath(delBtn));
			chrome.sleep(3);
			popUp.dismiss();
			chrome.sleep(2);
		}
		Assert.assertEquals(chrome.isElementPresent(By.xpath(secondRow)), true);
	}

	@Test(description="첫 번째 store 정보를 제외한 다른 상점 목록을 모두 삭제하기")
	public void tc02_clearList() { 
		//String secondRow = "//*[@id=\"content\"]/div/div[3]/div[2]/table/tbody/tr[2]/td[1]";
		String delBtn = "//*[@id=\"content\"]/div/div[3]/div[2]/table/tbody/tr[1]/td[11]/button";

		String sListNo = "1";
		String noXpath = "//*[@id=\"content\"]/div/div[3]/div[2]/table/tbody/tr[1]/td[1]";
		int listNo = 1;
		sListNo = chrome.getText(By.xpath(noXpath));
		
		while(listNo < Integer.parseInt(sListNo)) {
			Alert popUp = chrome.getAlert(By.xpath(delBtn));
			chrome.sleep(1);
			popUp.accept();
			chrome.sleep(1);
			sListNo = chrome.getText(By.xpath(noXpath));
		}
		chrome.sleep(1);
		Assert.assertEquals(sListNo, "1");
	}

}
