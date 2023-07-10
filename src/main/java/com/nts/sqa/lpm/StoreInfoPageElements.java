package com.nts.sqa.lpm;


/*
 * Resource List to Test Automation for Web page * 
 * Elements in Store Information Edit Page
 */
//Store Name's id : shopName
//Store Name(EN)'s xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[2]/td/span/span
//Phone Number's id : telNumber
//Address1's id : address1
//Address2's first select-box's id : addrCity
//Address2's second select-box's is : addrState
//Postal code's id : zipCode
//MON in Open Hours's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[1]/span
//TUE in Open Hours's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[2]/span
//Open time's Hour xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[1]/select
//Open time's Minute xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[2]/select
//Close time's Hour xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[3]/select
//Close time's Minute xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[4]/select
//Other Information's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[8]/td/span/span
//Logo Image's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[9]/td/div[2]/span[1]/input
//Button 확인 : TBD
//Store Introduction's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[10]/td/span/span
//How to get's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[11]/td/span/span
//Add Tips button's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[12]/th/button
//Tip EditBox's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[12]/td/ul/li[1]/span/span
//최대 10개
//Tip EditBox Delete button's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[12]/td/ul/li[1]/a/span
//Delete 할 때 li[x] 이므로 x를 index로 활용
//Status's id :  shopValidityStatus   => HIDE or In service
//Image Upload for Interior/Exterior.. 's xpath : //*[@id="shopForm"]/div[3]/table/tbody/tr/td/ul/li[1]/a 
//==> Implement New Window Control
//Image Upload for Information's xpath : //*[@id="shopForm"]/div[4]/table/tbody/tr/td/ul/li[1]/a
//==> 필요성 확인 후 대응
//Preview Button's id :  previewButton
//
public class StoreInfoPageElements {
	public String targetUrl = "https://pay.line-beta.me/portal/tw/main";
	public String sId  = "test-QAtailauto01";
	public String sPassword = "1Q2w3e4r";

	// (Add Stores Only) Registered Info's id : sameMerchantAddress
	public String idSameInfo = "sameMerchantAddress";
	// Store Name's id : shopName
	public String idStoreName = "shopName"; 
	//Store Name(EN)'s xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[2]/td/span/span
	public String idStoreEN = "shopNameEN";
	// Phone Number's id : telNumber
	public String idPhoneNumber = "telNumber"; 
	// Address1's id : address1
	public String idAddr1 = "address1";
	// Address2's first select-box's id : addrCity
	public String idAddrCounty = "addrCity";
	// Address2's second select-box's id : addrState
	public String idAddrDistrict = "addrState";
	// Postal code's id : zipCode
	public String idPostCode = "zipCode";
	// Add Schedule's id : addRunTime
	public String idAddSchedule = "addRunTime";
	// MON in Open Hours's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[1]/span
	public String[] xOpenDay = {
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[1]/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[2]/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[3]/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[4]/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[5]/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[6]/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[7]/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[1]/label[8]/span" /* Holiday */
	};
	// Default OpenDay's name : shopTimeList[0].mon , Added OpenDay's name : shopTimeList[1].mon
	public String nameOpenDayMon = "shopTimeList[1].mon"; 
	// Delete Added OpenDay's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[8]/td/div/div[2]/div/a
	public String xDelShecdule = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[8]/td/div/div[2]/div/a";
	/*
	 * Open / Close 시간 설정은 같은 element에 Index만 다르므로 위와 같이 Array로 표현해도 좋음. 여기선 명시적으로 표현하고자 분리함.
	 */
	// Open time's Hour xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[1]/select
	public String xOpenHH = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[1]/select";
	// Open time's Minute xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[2]/select
	public String xOpenMM = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[2]/select";
	// Close time's Hour xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[3]/select
	public String xCloseHH = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[3]/select";
	// Close time's Minute xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[4]/select
	public String xCloseMM = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[7]/td/div/div[2]/div/span[4]/select";
	// Other Information's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[8]/td/span/span
	public String idOtherInfo = "shopRunTimeEtc";
	// Logo Image's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[9]/td/div[2]/span[1]/input
	public String xLogo = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[9]/td/div[2]/span[1]/input"; // 입력 불가
	public String idLogoBtn = "__image_file";
	// Button 확인 : TBD
	// Store Introduction's name : description
	public String nameIntroduction = "description";
	// How to get's name : wayDescription
	public String nameHowToGet = "wayDescription";
	// Add Tips button's classname :  hours_button //*[@id="shopForm"]/div[2]/table/tbody/tr[12]/th/button
	public String xAddTipBtn = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/th/button";
	public String classAddTipsBtn = "hours_button";
	// Tip EditBox's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[12]/td/ul/li[1]/span/span
	public String[] xTipEdit = {
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[1]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[2]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[3]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[4]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[5]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[6]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[7]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[8]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[9]/span/span",
			"//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[10]/span/span"
	};
	// 최대 10개
	public String[] nameTipEdit = {
			"tips[0].tip",
			"tips[1].tip",
			"tips[2].tip",
			"tips[3].tip",
			"tips[4].tip",
			"tips[5].tip",
			"tips[6].tip",
			"tips[7].tip",
			"tips[8].tip",
			"tips[9].tip"
	};
	
	// Tip EditBox Delete button's xpath : //*[@id="shopForm"]/div[2]/table/tbody/tr[12]/td/ul/li[1]/a/span
	public String xTipDelBtn = "//*[@id=\"shopForm\"]/div[2]/table/tbody/tr[12]/td/ul/li[1]/a/span"; 
	// Delete 할 때 li[x] 이므로 x를 index로 활용
	
	// Status's id :  shopValidityStatus   => HIDE or In service
	public String idStatus = "shopValidityStatus";
	// Image Upload for Interior/Exterior.. 's xpath : //*[@id="shopForm"]/div[3]/table/tbody/tr/td/ul/li[1]/a
	public String xInteriorImageUp = "//*[@id=\"shopForm\"]/div[3]/table/tbody/tr/td/ul/li[1]/a";
	// ==> 새 창이 뜬다. 새 창에 핸들을 가져와야 함
	
	// Image Upload for Information's xpath : //*[@id="shopForm"]/div[4]/table/tbody/tr/td/ul/li[1]/a
	public String xItemInfoUp = "//*[@id=\"shopForm\"]/div[4]/table/tbody/tr/td/ul/li[1]/a";
	// ==> 필요성 확인 후 대응
	// Preview Button's id :  previewButton
	public String idPreviewBtn = "previewButton";
	// modify and submit Button's id : upsert
	public String idSubmitBtn = "upsert";
	// cancel Button's id : cancel
	public String idCancel = "cancel";
}
