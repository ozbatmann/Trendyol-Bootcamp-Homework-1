package notificationsubscription;

import billing.Billing;
import lombok.Getter;
import lombok.Setter;
import model.bill.Bill;
import notificationpackage.impl.NotificationPackage;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class NotificationSubscription {

	private String id;
	private NotificationPackage notificationPackage;
	private List<Bill> billList;
	private Date startDate;
	private Date endDate;

	public NotificationSubscription(NotificationPackage notificationPackage) {
		this.notificationPackage = notificationPackage;
		this.startDate = DateUtil.nowAsDate();
		this.endDate = DateUtil.addMonth(DateUtil.nowAsDate(), 1);
		billList = new ArrayList<>();

	}

	public void refreshNotificationSubscription() {
		if(DateUtil.lowerThan(DateUtil.nowAsDate(), endDate)){
			Bill bill = billList.get(billList.size() - 1);
			bill.setAmount(bill.getAmount() + notificationPackage.getExtraAmount());
		} else {
			startDate = DateUtil.nowAsDate();
			endDate = DateUtil.addMonth(DateUtil.nowAsDate(), 1);
			Bill newBill = Billing.generateBill(notificationPackage.getAmount());
			billList.add(newBill);
		}
	}

	public void addAmountToBill() {
		if(DateUtil.lowerThan(DateUtil.nowAsDate(),endDate)){
			Bill bill = billList.get(billList.size() - 1);
			bill.setAmount(bill.getAmount() + notificationPackage.getExtraAmount());
		}
	}

	public void addBill(Bill bill) {
		billList.add(bill);
	}
}
