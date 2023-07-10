package com.nts.sqa.lpmtest;

import org.testng.annotations.Test;

import com.nts.sqa.lpm.SetUp;
import com.ntscorp.auto_client.verity.Verify;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.FileDetector;

public class StoreInformation extends SetUp {

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
	
	@Test(description="Check Same as registered merchant info")
	public void tc02_loadRegisteredInfo() {
		chrome.click(By.id(spe.idSameInfo));
		chrome.sleep(1);
		Assert.assertEquals(chrome.getValue(By.id(spe.idPhoneNumber)), "09-1423-41234");
		Assert.assertEquals(chrome.getValue(By.id(spe.idAddr1)), "add1");
		Assert.assertEquals(chrome.getValue(By.id(spe.idAddrCounty)), "3");
		Assert.assertEquals(chrome.getValue(By.id(spe.idAddrDistrict)), "220"); // Postal Code와 연동 됨
	}
	
	@Test(description="Clear loaded merchant info")
	public void tc03_unloadRegisteredInfo() {
		chrome.click(By.id(spe.idSameInfo));
		chrome.sleep(1);
		Assert.assertEquals(chrome.getValue(By.id(spe.idPhoneNumber)), "");
		Assert.assertEquals(chrome.getValue(By.id(spe.idAddr1)), "");
		Assert.assertEquals(chrome.getValue(By.id(spe.idPostCode)), "");
    	// 아래 2개 assert는 Select County 전체 정보를 가져와서 비교가 어려움 skip 	
		//Assert.assertEquals(chrome.getValue(By.id(spe.idAddrCounty)), "3");
		//Assert.assertEquals(chrome.getValue(By.id(spe.idAddrDistrict)), "3");
	}
	
	@Test(description="Input Store Name & Store Name(EN) & Phone Number")
	public void tc04_inputStoreNameInfo() {
		String expectedSN = "柱家 商店";
		String expectedEN = "HJ Test Brand";
		String expectedPN = "09-6556-15417";
		chrome.type(By.id(spe.idStoreName), expectedSN);
		chrome.type(By.id(spe.idStoreEN), expectedEN);
		chrome.type(By.id(spe.idPhoneNumber), expectedPN);
		chrome.sleep(0.5);
		
		Assert.assertEquals(chrome.getValue(By.id(spe.idStoreName)), expectedSN);
		Assert.assertEquals(chrome.getValue(By.id(spe.idStoreEN)), expectedEN);
		Assert.assertEquals(chrome.getValue(By.id(spe.idPhoneNumber)), expectedPN);
	}
	
	@Test(description="Input Address")
	public void tc05_inputAddress() {
		String exAddr1 = "No32-9 Baoqing Rd";
		String exCity = "1"; // "Taipei"  臺北市
		String exDistrict = "100"; // "Zhongzheng"; // 中正區
		chrome.type(By.id(spe.idAddr1), exAddr1);
		chrome.select(By.id(spe.idAddrCounty)).selectByValue(exCity);
		chrome.select(By.id(spe.idAddrDistrict)).selectByValue(exDistrict);
		chrome.sleep(0.5);
		
		Assert.assertEquals(chrome.getValue(By.id(spe.idAddr1)), exAddr1);
		Assert.assertEquals(chrome.getValue(By.id(spe.idPostCode)), exDistrict);
	}
	
	@Test(description="Set Business time to Open & Close")
	public void tc06_setBusinessTime() {
		chrome.click(By.xpath(spe.xOpenDay[0]));
		chrome.click(By.xpath(spe.xOpenDay[1]));
		chrome.click(By.xpath(spe.xOpenDay[2]));
		chrome.click(By.xpath(spe.xOpenDay[3]));
		chrome.click(By.xpath(spe.xOpenDay[4]));
		chrome.click(By.xpath(spe.xOpenDay[5]));
		chrome.click(By.xpath(spe.xOpenDay[6]));
		chrome.click(By.xpath(spe.xOpenDay[7]));
		chrome.select(By.xpath(spe.xOpenHH)).selectByValue("10");
		chrome.select(By.xpath(spe.xOpenMM)).selectByValue("10");

		chrome.select(By.xpath(spe.xCloseHH)).selectByValue("22");
		chrome.select(By.xpath(spe.xCloseMM)).selectByValue("00");
		
		chrome.sleep(1);
		
		Assert.assertEquals(chrome.getValue(By.xpath(spe.xOpenMM)), "10");
		
	}
	
	@Test(description="Add Schedule and then check open day")
	public void tc07_addSchedule() {
		chrome.click(By.id(spe.idAddSchedule));

		Assert.assertEquals(chrome.isSelected(By.name(spe.nameOpenDayMon)), false);
		chrome.click(By.name(spe.nameOpenDayMon));
		Assert.assertEquals(chrome.isSelected(By.name(spe.nameOpenDayMon)), false);
		Assert.assertEquals(chrome.getText(By.xpath(spe.xDelShecdule)), "Delete");
	}
	/*
	 * Business Time은 모든 요일에 대한 설정이 완료되어야 등록이 가능함
	 */
	// 구현하시오!!
	
	
	@Test(description="Input Other Information")
	public void tc08_inputOther() {
		chrome.type(By.id(spe.idOtherInfo), "Run Time 10:10 ~ 22:00");
	}
	
	/*
	@Test(description="Loading Logo image")
	public void tc09_uploadLogo() {
		Keys keys = null;
		chrome.click(By.id(spe.idLogoBtn));
		String autoUpload = "c:\\MyDev\\autoit\\uploadAuto.exe";  // "c:\\Work\\서비스\\가맹점\\Automation\\ic.jpg";
		// run window controller for upload made by autoIt 
		Runtime rt = Runtime.getRuntime();
		Process proc;
		try {
			proc = rt.exec(autoUpload);
			proc.waitFor();
		} catch(Exception e) {
			e.printStackTrace();
		}
		chrome.sleep(3);
		Assert.assertEquals(chrome.getValue(By.xpath(spe.xLogo)), "ic.jpg");
		chrome.sleep(3);
	}
	*/

	@Test(description="Loading Logo Image")
	public void tc09_uploadLogo() {
		String logoImage =  "c:\\Work\\서비스\\가맹점\\Automation\\ic.jpg";
		WebElement upload =chrome.findElement(By.id(spe.idLogoBtn));
		upload.sendKeys(logoImage);
		//chrome.type(By.xpath("//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[9]/td/div[2]/span[1]/input"), logoImage);
	}
	
	@Test(description="Input Store Introduction")
	public void tc10_inputIntroduction() {
		chrome.type(By.name(spe.nameIntroduction), "Variety Delicious food & Sweek drink for you!");
	}
	
	@Test(description="How to get the store")
	public void tc11_howToGo() {
		chrome.type(By.name(spe.nameHowToGet), "Ximen Station Gate 5, go streight ahead 5 minute");
	}
	
	@Test(description="Input Tips")
	public void tc12_inputTips() {
		//Verify.verifyTrue(chrome.isElementPresent(By.xpath(spe.xTipDelBtn)));
		//chrome.click(By.xpath(spe.xAddTipBtn)); // <= not working
		//chrome.sleep(2);
		chrome.type(By.name(spe.nameTipEdit[0]), "Welcome1");
		chrome.type(By.name(spe.nameTipEdit[1]), "Welcome2");
		//chrome.type(By.name(spe.nameTipEdit[2]), "Welcome3");
		
		Assert.assertEquals(chrome.getValue(By.name(spe.nameTipEdit[0])),  "Welcome1");
		Assert.assertEquals(chrome.getValue(By.name(spe.nameTipEdit[1])),  "Welcome2");
		//Assert.assertEquals(chrome.getValue(By.name(spe.nameTipEdit[2])),  "Welcome3");
	}
	
	@Test(description="set Status")
	public void tc13_setStatus() {
		Verify.verifyTrue(chrome.isElementPresent(By.id(spe.idStatus)));
		chrome.select(By.id(spe.idStatus)).selectByValue("HIDE"); // or "DISPLAY"
		chrome.sleep(3);
	}
	/*
	@Test(description="등록(submit)하기")
	public void tc14_submitAll() {
		chrome.click(By.id(spe.idSubmitBtn));
		chrome.sleep(3);
		// //*[@id="content"]/div/div[3]/div[2]/table/tbody/tr/td[1]
		// //*[@id="content"]/div/div[3]/div[2]/table/tbody/tr[1]/td[1]
		// 등록된 표에 등록한 정보가 나오는지 확인하기!!
	}
	*/
}
