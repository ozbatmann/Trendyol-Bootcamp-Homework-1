package validator;

import enums.messages.MessageType;
import exception.BlackListException;
import model.bill.Bill;
import model.company.Company;
import notificationsubscription.NotificationSubscription;
import util.DateUtil;

import java.util.List;

public class Validator implements Validate {

	@Override
	public boolean validateBilling(Company company) {
		for(NotificationSubscription notificationSubscription : company.getNotificationSubscriptionList()){
			for(Bill bill : notificationSubscription.getBillList()){
				if(DateUtil.lowerThan(bill.getLastPaymentDate(), DateUtil.nowAsDate())) return false;
			}
		}
		return true;
	}

	@Override
	public void validateInBlackList(String companyId, List<String> blackList) throws BlackListException {
		for(String id : blackList){
			if(id.equals(companyId)) throw new BlackListException(MessageType.BLACK_LISTED_COMPANY.getTextMessageKey());
		}
	}

}
