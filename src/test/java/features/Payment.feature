Feature: MKTP Payment
  Background:  
    * configure headers = { "X-LP-AUTOTEST-NAME": "features/Payment.feature" }

  	* def baseURI = "http://api-pay.line-apps-beta.com:8080"
  	
    * def paymentsBaseURI = "https://api-pay.line-beta.me:443"

    # 계정 정보 
    * def mid = "u00d5f3a707a98b67e13f342c8530601a"
    # 결제 정보
    * def amount = 539
    * def currency = "TWD"
    * def lpAccountNo = "1000110002462537"
    # set channelId & channelSecretKey - pay_test_tw001
    * def channelId = "1651833220"
    * def channelSecretKey = "4825631fee1f0ceaacffd2a3f1578413"


		# MKTP DATA
		* def mktURI = "http://sparrow-api-pay.line-apps-beta.com:14404/internal"
		* def merchantID = "test-QAtailoff03"
		* def applySeq = 26440
		
		# set channelId & channelSecretKey - LINEPAY_MKT1
    * def channelId = "1651851137"
    * def channelSecretKey = "92b9ed57d04cca448beca0652ef0bd34"
  Scenario: MKTP 결제
  	
  #	## -----MKTP----- ##
  #	## Order - Calculation ##
  #	* def path = "/v1/TW/order"
  #	* def req_header =
       #"""
       #{
       #		 X-LP-MK-MERCHANT-ID : #(merchantID)
       #}
       #"""
    #* def req_body =
#	    """
#	    {
#	      "applySeq": #(applySeq)
#	    }
#	    """
#	  Given url mktURI + path
#		And headers req_header
#		And request req_body
#		When method POST
    #Then status 200
    #And match response.resultCode == "0000"
  #	
  #	* def tempOrderSeq = response.data.tempOrderSeq
  #	
  	## Order - Payment Request ##
  	* def tempOrderSeq = "37240"
		* def path = "/v1/TW/order/" + tempOrderSeq + "/payment/request" 
  	* def req_header =
       """
       {
       		 X-LP-MK-MERCHANT-ID : #(merchantID)
       }
       """
    * def req_body =
	    """
	    {
	      "paymentType": "LINE_PAY"
	    }
	    """
    * def orderId = 'ORD_' + Java.type('Utils.PaymentUtil').generateOrderId()
    
	  Given url mktURI + path
		And headers req_header
		And request req_body
		When method POST
    Then status 200
    And match response.resultCode == "0000"
  	
  	* def transactionId = response.data.transactionId
    And Java.type('Utils.PaymentUtil').setVar("transactionReserveId", response.data.paymentUrl.web)
  	
    ## Request payment ##
  	* def path = "/v3/payment/request/get"
  	* def transactionReserveId = Java.type('Utils.MktpUtil').getTransactionReserveId() 
  	* def transactionReserveId = "RTNvdlBKalFlK0hvT0VFMjhPT2FxY2tydUpRUmpza2dZNzgxRWFZaFNnMk9pU3MrQ2RsMjdiZXMwRis3VHE5TQ"
  	* def req_header =
       """
       {
       		 X-LP-MID : #(mid),
           X-LP-ClientInfo : "ANDROID_BETA	12.18.0	Android OS	12"
       }
       """
    * def req_body =
	    """
	    {
	      "transactionReserveId": #(transactionReserveId)
	    }
	    """
    
    
    Given url baseURI + path
		And headers req_header
		And request req_body
		When method POST
    Then status 200
    And match response.returnCode == "0000"
    #And match response.info.cancelUrl != null

	## Payment ##
	# token 발급
    * def path = "/v1/line-users/" + mid + "/tokens/issue"
    Given url baseURI + path
    When method GET
    Then status 200
    And match response.returnCode == "0000"
    And match response.info.requestToken != null
    * def requestToken = response.info.requestToken

    # RSA KEY 발급 및 password encrypt
    * def password = Java.type('Utils.PaymentUtil').getPassword()
    * def payload =
    """
    {
      data: #(password)
    }
    """
    * def path = "/v1/rsa/encrypt"
    Given url baseURI + path
    And request payload
    When method post
    Then status 200
    And match response.returnCode == "0000"
    And match response.info.encryptedText != null
    And match response.info.keyName != null
    * def password =  response.info.encryptedText
    * def keyName = response.info.keyName
    
   # 본 API 호출
    * def path = "/v3/payment/request/authorize"
    * def req_header =
       """
       {
         X-LP-MID: #(mid),
         X-LP-ClientInfo : "ANDROID_BETA	11.10.2	Android OS	10",
         x-lp-region : "TW"
       }
       """
    * def req_body =
    """
    {
      "transactionReserveId" : #(transactionReserveId),
      "keyName" : #(keyName),
      "password" : #(password),
      "requestToken" : #(requestToken),
      "payments":[{
          "amount":"539",
          "currency":"TWD",
          "method":"CREDIT_CARD",
          "lpAccountNo": #(lpAccountNo)
       }],
      "additionalAgreements": [{
        "type":"LINE_PAY_OA",
        "checkYn" : "Y"
      }]
    }
    """
    Given url baseURI + path
    And headers req_header
    And request req_body
    When method post
    Then status 200
    And match response.returnCode == "0000"

    ## Calculate Amount ##
    * def path = "/v3/payment/request/calculate"
    * def req_header =
       """
       {
         X-LP-MID: #(mid),
         X-LP-ClientInfo : "ANDROID_BETA	12.18.0	Android OS	12"
       }
       """
    * def req_body =
    """
    {
      "transactionReserveId" : #(transactionReserveId)
    }
    """
    Given url baseURI + path
    And headers req_header
    And request req_body
    When method post
    Then status 200
    And match response.returnCode == "0000"
    And match response.info.productAmount.amount == amount
    Then Java.type('Utils.PaymentUtil').pause(5000)
    
    ## Get authorization ##
    * def path = "/v3/payment/authorization/get"
    * def req_header =
       """
       {
         X-LP-MID: #(mid),
         X-LP-ClientInfo : "ANDROID_BETA	12.18.0	Android OS	12"
       }
       """
    * def req_body =
    """
    {
      "transactionReserveId" : #(transactionReserveId)
    }
    """
    Given url baseURI + path
    And headers req_header
    And request req_body
    When method post
    Then status 200
    And match response.returnCode == "0000"
    
    ## Confirm authorization ##
    * def path = "/v3/payment/authorization/confirm"
    * def req_header =
      """
       {
       		 X-LP-MID : #(mid),
           X-LP-ClientInfo : "ANDROID_BETA	12.18.0	Android OS	12"
       }
       """
    * def req_body =
    """
    {
      "transactionReserveId" : #(transactionReserveId)
    }
    """
    Given url baseURI + path
    And headers req_header
    And request req_body
    When method post
    Then status 200
    And match response.returnCode == "0000"
    
    
    
    # Confirm payment ##
    * def path = "/v3/payments/" + transactionId + "/confirm"
    * def nonce = Java.type('Utils.PaymentUtil').getNonce()
    * text req_body =
      """
     {
      "amount": 539,
      "currency": "TWD"
     }
     """
    * def signature = Java.type('Utils.PaymentUtil').getSignature(channelSecretKey, path, req_body, nonce)
    * def req_header =
       """
      {
        Content-Type : 'application/json',
        X-LINE-ChannelId : #(channelId),
        X-LINE-Authorization-Nonce : #(nonce),
        X-LINE-Authorization : #(signature)
      }
      """
    Given url paymentsBaseURI + path
    And headers req_header
    And request req_body
    When method post
    Then status 200
    And match response.returnCode == "0000"
    
    
    #
    ## MKTP Confirm Payment ##
    #* def tempOrderSeq = "37233"
    #* def path = "/v1/TW/order/" + tempOrderSeq + "/payment/confirm"
    #* def req_header =
       #"""
      #{
        #X-LP-MK-MERCHANT-ID : #(merchantID)
      #}
      #"""
    #* text req_body =
      #"""
     #{
      #"amount": 539,
      #"currency": "TWD"
     #}
     #"""
    #* def nonce = Java.type('Utils.PaymentUtil').getNonce()
    #* def signature = Java.type('Utils.PaymentUtil').getSignature(channelSecretKey, path, req_body, nonce)
    #Given url mktURI + path
    #And headers req_header
    #And request req_body
    #When method post
    #Then status 200
    #And match response.resultCode == "0000"
      #
      
