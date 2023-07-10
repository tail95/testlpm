package com.nts.sqa.lpmtest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nts.sqa.lpm.SetUp;
import com.ntscorp.auto_client.verity.Verify;

public class StoreAdd_Minimum_Info extends SetUp {

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
		Verify.verifyTrue(chrome.isElementPresent(By.id("selLang")));
		chrome.click(By.id("selLang"));
		chrome.sleep(3);
		Assert.assertEquals(chrome.getText(By.xpath("//*[@id=\"gnb\"]/li[1]/a")), "HOME");
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
	 * Add Store Test
	 */
	
}
