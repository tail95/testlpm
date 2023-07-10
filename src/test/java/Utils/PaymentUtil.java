package Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;

import com.github.cliftonlabs.json_simple.Jsoner;
import com.google.common.base.Strings;

import net.minidev.json.JSONObject;

public final class PaymentUtil {
    private static String nonce;
    private static LinkedHashMap dataStore;
    // checkout payment
    private static String shippingTokenKey = null;
    private static String shippingAddr = null;
    // normal payment
    private static String transactionId = null;
    private static String paymentAccToken = null;
    private static String transactionReserveId = null;
    private static String regKey = null;
    private static String oneTimeKey = null;
    private static String sessionToken = null;
    private static JSONObject req_body = new JSONObject();
    // 사용자 정보
    private static String mid = "u00d5f3a707a98b67e13f342c8530601a";
    private static String password = "147369";
    private static String lpaccountno = "1000210002463328"; // visa 주카드
    private static String point = null;
    // 가맹점 정보
    private static String channelId = "null";
    private static String channelSecretKey = "null";
    // charge and pay용
    private static String[] transactionIds = { "0", "0", "0" };

    private static String orderId = null;

    private static String ofPattern = "yyyyMMdd";
    private static SimpleDateFormat formatter = new SimpleDateFormat(ofPattern);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ofPattern);
    private static DateTimeFormatter nowDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private PaymentUtil() {
    }

    public static String encrypt(final String keys, final String data) {
        return toBase64String(HmacUtils.getHmacSha256(keys.getBytes()).doFinal(data.getBytes()));
    }

    public static String toBase64String(byte[] bytes) {
        byte[] byteArray = Base64.encodeBase64(bytes);
        return new String(byteArray);
    }

    public static String getSignature(String channelSecret, String requestURI, String req_body, String nonce) {
        //   JSONObject req_body_json = new JSONObject(req_body);
        String signature = encrypt(channelSecret, channelSecret + requestURI + req_body + nonce);
        return signature;
    }/*
    public static String getSignature(String channelSecret, String requestURI, String nonce){
        //   JSONObject req_body_json = new JSONObject(req_body);
        System.out.println("req_body : " + req_body);
        String signature = encrypt(channelSecret, channelSecret + requestURI + req_body + nonce);
        return signature;
    }*/

    public static String getNonce() {
        nonce = UUID.randomUUID().toString();
        return nonce;
    }

    public static String generateOrderId() {
        orderId = UUID.randomUUID().toString();
        return orderId;
    }

    public static String getOrderId() {
        return "ORD_" + orderId;
    }

    // JSON으로 바디 만들어서 전달
    /*
    public static JSONObject setBody(Integer amount, String currency, String orderId, String packages, String products, String redirectUrls) {
        req_body.appendField("amount", amount);
        req_body.appendField("currency", currency);
        req_body.appendField("orderId", orderId);
        req_body.appendField("packages", packages);
        //  req_body.appendField("packages.products", toJson(products));
        req_body.appendField("redirectUrls", redirectUrls);
        return req_body;
        //   System.out.println("body : " + req_body);
    }*/

    public static void setVar(String name, String var) {
        if (name.equals("transactionId")) {
            transactionId = var;
        } else if (name.equals("paymentAccToken")) {
            paymentAccToken = var;
        } else if (name.equals("transactionReserveId")) {
            // transactionReserveId has to be extracted from paymentUrl - web
            var = getTransactionReserveId(var);
            transactionReserveId = var;
        } else if (name.equals("regKey")) {
            regKey = var;
        } else if (name.equals("shippingTokenKey")) {
            shippingTokenKey = var;
        } else if (name.equals("shippingAddr")) {
            // editable은 주소로 넣을때는 들어가지 않으므로 제외
            int tmp = var.indexOf("editable");
            String tmp1 = var.substring(0, tmp - 1);
            String tmp2 = var.substring(tmp + 15);
            shippingAddr = tmp1 + tmp2;
            System.out.println("shippingAddr: " + var);
        } else if (name.equals("point")) {
            point = var;
        } else if (name.equals("oneTimeKey")) {
            oneTimeKey = var;
        } else if (name.equals("sessionToken")) {
            sessionToken = var;
        }
    }

    public static String getVar(String name) {
        String returnVar = null;

        if (name.equals("transactionId")) {
            returnVar = transactionId;
        } else if (name.equals("paymentAccToken")) {
            returnVar = paymentAccToken;
        } else if (name.equals("transactionReserveId")) {
            returnVar = transactionReserveId;
        } else if (name.equals("regKey")) {
            returnVar = regKey;
        } else if (name.equals("shippingTokenKey")) {
            returnVar = shippingTokenKey;
        } else if (name.equals("shippingAddr")) {
            returnVar = shippingAddr;
        } else if (name.equals("point")) {
            returnVar = point;
        } else if (name.equals("oneTimeKey")) {
            returnVar = oneTimeKey;
        } else if (name.equals("sessionToken")) {
            returnVar = sessionToken;
        }

        return returnVar;
    }

    public static String getTransactionReserveId(String str) {
        if (str.contains("https")) {
            int splitIdx = str.indexOf("transactionReserveId");
            String substr = str.substring(splitIdx).replace("transactionReserveId=", "");
         //   System.out.println("substr : " + substr);
            str = substr;
        }
        return str;
    }

    public static String getTransactionReserveIdBITMAX(String str) {
        if (str.contains("https")) {
            int splitIdx = str.indexOf("transactionReserveId");
            String substr = str.substring(splitIdx).replace("transactionReserveId=", "");
            substr = substr.replace("&locale=JP_LP", "");
            str = substr;
        }
        return str;
    }

    public static void saveTransactionIds(String str, int num) {
        transactionIds[num] = str;
    }

    public static String getTransactionIds(int num) {
        return transactionIds[num];
    }

    public static void writeJSON(String fileContent) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("data.json"));

        Jsoner.serialize(fileContent, writer);
        writer.close();
    }

    public static String replace(String s) {
        s.replaceAll("^\"|\"$", "");
        return s;
    }
/*
    public static JsonObject readJSON() throws IOException, JsonException {
        Reader reader = Files.newBufferedReader(Paths.get("data.json"));
        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
        reader.close();
        return parser;
    }
    public static void writeJSON (String fileContent) {
     //   Path file = Paths.get("../Payment/Util/data.json");
       Path file = Paths.get("/Payment/Util/data.json");
    //    URL path = Foo.class.getResource("data.json");
    //    File file = new File(path.getFile());
        try (Writer fileWriter = new FileWriter(String.valueOf(file))) {
            fileWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    // 사용자 정보
    // TW/TH의 경우 setMid를 통해 별도 mid를 설정하여 그대로 getMid를 사용할 수 있음
    public static void setMid(String midWH) {
        mid = midWH;
    }

    public static String getMid() {
        return mid;
    }

    public static String getPassword() {
        return password;
    }

    public static String getLP() {
        return lpaccountno;
    }

    // 가맹점 정보
    public static void setChannelId(String channelName) {
        channelId = channelName;
    }

    public static void setChannelSecretKey(String channelSecretName) {
        channelSecretKey = channelSecretName;
    }

    public static String getChannelId() {
        return channelId;
    }

    public static String getChannelSecretKey() {
        return channelSecretKey;
    }

    // 포인트 적립 배치 돌리기용
    public static String getDate() {

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        return formatter.format(date);
    }

    public static boolean checkTxid(String type, String txid) {
        boolean result = false;
        if (type.equals("all")) {
            result = txid.endsWith("11");
        } else if (type.equals("partial")) {
            result = txid.endsWith("12");
        }
        return result;
    }

    public static void pause(Integer sec) throws InterruptedException {
        java.lang.Thread.sleep(sec);
    }

    public static Integer getPoint(Integer amount, String type, Integer figure) {
        Integer point = 0;
        if (type.equals("Rate")) {
            double tmp = amount * figure * 0.01;
            point = (int) tmp;
        } else if (type.equals("Quantity")) {
            point = figure;
        } else {
            // todo : 예외처리
        }
        return point;
    }

    public static Integer getBR(Integer amount, Integer figure) {
        // balance 적립은 소수점 1자리에서 반올림
        Integer point = 0;
        double tmp = amount * figure * 0.01;
        point = Math.toIntExact(Math.round(tmp));

        return point;
    }

    public static Integer getAfterBalance(Integer amount, Integer partialRefundAmount, Integer beforeBalance) {
        return beforeBalance - (amount - partialRefundAmount);
    }

    public static String getBalance (String amountString) {
        amountString = amountString.replace(",","");
        amountString = amountString.replace(" 円", "");
        amountString = amountString.replace("LN", "");
        BigDecimal bigDecimal = new BigDecimal(amountString);
        amountString = bigDecimal.stripTrailingZeros().toPlainString();
        return amountString;
    }
    
    public static String calculate (String var1, String sign, String var2) {
        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        BigDecimal calResult = null;
        String result = null;
        if(sign.equals("-")) {
            calResult = bigDecimal1.subtract(bigDecimal2);
        } else if (sign.equals("+")) {
            calResult = bigDecimal1.add(bigDecimal2);
        } else if (sign.equals("/")) {
            // LINK 정책상
            calResult = bigDecimal1.divide(bigDecimal2, 0, RoundingMode.UP);
        }
        result = calResult.stripTrailingZeros().toPlainString();
        return result;
    }

    public static BigDecimal makeBigDecimal (String var) {
        BigDecimal bigDecimal = new BigDecimal(var);
        return bigDecimal;
    }

    public static String getCtbcPaymentApplyForm(String Element) {
        Date now = new Date();
        Random rand = new Random();
        String returnVar = null;

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat TODAY_FORMAT = new SimpleDateFormat("yyyyMMdd");
        String NUMBER_FORMAT = "%d";

        String dateString = DATE_FORMAT.format(now);
        String todayString = TODAY_FORMAT.format(now);
        String randString = String.format(NUMBER_FORMAT, rand.nextInt(9999999));
        String SeqFrame = todayString + randString;
        String merchantId = "0" + String.valueOf(rand.nextInt(1000000000));
        String storeId = String.valueOf((rand.nextInt(9000000) + 1000000));
        String posNo = "1" + String.format("%07d", rand.nextInt(10000000));

        if (Element == "SeqFrame") returnVar = SeqFrame;
        else if (Element == "merchantId") returnVar = merchantId;
        else if (Element == "corpTxTime") returnVar = dateString;
        else if (Element == "storeId") returnVar = storeId;
        else if (Element == "posNo") returnVar = posNo;
        return returnVar;
    }

    public static String getArgNoByCardId(String cardId) {
        return cardId.split("_")[1];
    }

    public static String getTodayString() {
        LocalDate today = LocalDate.now();
        return today.format(dateTimeFormatter);
    }

    public static String getNowDateString() {
        LocalDateTime today = LocalDateTime.now();
        return today.format(nowDateTimeFormatter);
    }

    public static String getRandTradeSpecInfo() {
        Random rand = new Random();
        Strings.padStart(String.valueOf(rand.nextInt(1000000)), 6, '0');

        StringBuffer randTradeSpecInfo =  new StringBuffer();
        randTradeSpecInfo.append("120");
        randTradeSpecInfo.append(getTodayString());
        randTradeSpecInfo.append(Strings.padStart(String.valueOf(rand.nextInt(1000000)), 6, '0'));
        randTradeSpecInfo.append(Strings.padStart(String.valueOf(rand.nextInt(1000)), 3, '0'));
        return randTradeSpecInfo.toString();
    }

    public static String getRandAuthNo() {
        Random rand = new Random();
        return Strings.padStart(String.valueOf(rand.nextInt(1000000)), 6, '0');
    }

    public static String createSalesId() {
        return "1" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "00000";
    }

    public static String createBrandId() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "0000";
    }

    public static String createBalanceAdjustmentInfoId() {
        return new SimpleDateFormat("yyMMdd").format(new Date()) + "0000000";
    }

    public static String createTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }
}