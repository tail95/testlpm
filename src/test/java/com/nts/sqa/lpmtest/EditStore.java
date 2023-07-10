package com.nts.sqa.lpmtest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nts.sqa.lpm.SetUp;

public class EditStore extends SetUp {

	@Test(description="open Monthly Edit page")
	public void tc01_openEditPage() {
		chrome.click(By.xpath("//*[@id=\"content\"]/div/div[3]/div[2]/table/tbody/tr/td[10]/a"));
		chrome.sleep(2);
		Assert.assertEquals(chrome.getText(By.xpath("//*[@id=\"shopForm\"]/p/strong")), "*Store Information");
	}
}
