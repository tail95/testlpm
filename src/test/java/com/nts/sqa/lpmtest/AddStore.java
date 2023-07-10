package com.nts.sqa.lpmtest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nts.sqa.lpm.SetUp;

public class AddStore extends SetUp {

	public String curUrl = "https://pay.line-rc.me/tw/center/info/shop/list?locale=en_US";
	
	@Test(description="Add Stores 버튼 클릭 > Add Store 페이지로 이동")
	public void tc01_openAddStorePage() {
		chrome.click(By.xpath("//*[@id=\"content\"]/div/div[3]/div[3]/a/span"));
		chrome.sleep(2);
		Assert.assertEquals(chrome.getText(By.xpath("//*[@id=\"shopForm\"]/p/strong")), "*Store Information Registration");
	}
}
