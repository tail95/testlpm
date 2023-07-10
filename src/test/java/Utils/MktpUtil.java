package Utils;

public class MktpUtil {
	private static String transactionReserveid;
	
	public static void setTransactionReserveId(String TransactionReserveId) {
        transactionReserveid = TransactionReserveId;
    }

    public static String getTransactionReserveId() {
        return transactionReserveid;
    }
}
