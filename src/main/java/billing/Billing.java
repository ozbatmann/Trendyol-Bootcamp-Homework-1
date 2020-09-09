package billing;

import model.bill.Bill;
import util.DateUtil;
import util.UUIDGenerator;

public class Billing {

	public static Bill generateBill(double amount) {
		return Bill.builder().amount(amount).id(UUIDGenerator.getUUID()).lastPaymentDate(DateUtil.addMonth(DateUtil.nowAsDate(), 2)).build();
	}
}
