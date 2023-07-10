package com.nts.sqa.lpmtest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nts.sqa.lpm.SetUp;
import com.ntscorp.auto_client.verity.Verify;

public class AddTipsTest extends SetUp {
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
	 * Add Store Test
	 */
	
	@Test(description="Add Stores 버튼 클릭 > Add Store 페이지로 이동")
	public void tc01_openAddStorePage() {
		chrome.click(By.xpath("//*[@id=\"content\"]/div/div[3]/div[3]/a/span"));
		chrome.sleep(2);
		Assert.assertEquals(chrome.getText(By.xpath("//*[@id=\"shopForm\"]/p/strong")), "*Store Information Registration");
	}

	@Test(description="Input Tips")
	public void tc12_inputTips() {
		//Verify.verifyTrue(chrome.isElementPresent(By.xpath(spe.xTipDelBtn)));
		chrome.click(By.xpath(spe.xAddTipBtn)); // <= not working
		chrome.sleep(2);
		
		chrome.executeJavascript(chrome, "ns.addTip");
		chrome.sleep(3);
//		chrome.type(By.name(spe.nameTipEdit[0]), "Welcome1");
//		chrome.type(By.name(spe.nameTipEdit[1]), "Welcome2");
		//chrome.type(By.name(spe.nameTipEdit[2]), "Welcome3");
		
//		Assert.assertEquals(chrome.getValue(By.name(spe.nameTipEdit[0])),  "Welcome1");
//		Assert.assertEquals(chrome.getValue(By.name(spe.nameTipEdit[1])),  "Welcome2");
		//Assert.assertEquals(chrome.getValue(By.name(spe.nameTipEdit[2])),  "Welcome3");
	}
	
}
