import billing.Billing;
import enums.language.LanguageType;
import enums.messages.MessageType;
import enums.notification.NotificationPackageType;
import exception.UnpaidBillingException;
import lombok.Getter;
import model.bill.Bill;
import model.company.Company;
import model.notification.Notification;
import notificationpackage.impl.EmailPackage;
import notificationpackage.impl.FixedPackageExceedStrategy;
import notificationpackage.impl.FlexiblePackageExceedStrategy;
import notificationpackage.impl.NotificationPackage;
import notificationpackage.impl.SmsPackage;
import notificationsender.NotificationSender;
import notificationsubscription.NotificationSubscription;
import util.MessageUtil;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class NotificationHub {

	@Getter
	private Validator validator;
	@Getter
	private NotificationSender smsSender;
	@Getter
	private NotificationSender emailSender;
	private Billing billing;

	private List<String> blackListedCompanies;

	public NotificationHub() {
		validator = new Validator();
		billing = new Billing();
		blackListedCompanies = new ArrayList<>();
	}

	public NotificationPackage getFixedSmsNotificationPackage(LanguageType languageType) {
		return new SmsPackage(new FixedPackageExceedStrategy(), NotificationPackageType.FIXED_SMS, languageType);
	}

	public NotificationPackage getFixedEmailNotificationPackage(LanguageType languageType) {
		return new EmailPackage(new FixedPackageExceedStrategy(), NotificationPackageType.FIXED_EMAIL, languageType);
	}

	public NotificationPackage getFlexibleSmsNotificationPackage(LanguageType languageType) {
		return new SmsPackage(new FlexiblePackageExceedStrategy(), NotificationPackageType.FLEXIBLE_SMS, languageType);

	}

	public NotificationPackage getFlexibleMailNotificationPackage(LanguageType languageType) {
		return new EmailPackage(new FlexiblePackageExceedStrategy(), NotificationPackageType.FLEXIBLE_EMAIL, languageType);
	}

	public void initializeSmsSender(NotificationPackage notificationPackage) {
		smsSender = notificationPackage.initializeSender();
	}

	public void initializeEmailSender(NotificationPackage notificationPackage) {
		emailSender = notificationPackage.initializeSender();
	}

	public NotificationSubscription subscribe(NotificationPackage notificationPackage, Company company) {

		NotificationSubscription notificationSubscription = new NotificationSubscription(notificationPackage);
		Bill bill = Billing.generateBill(notificationPackage.getAmount());
		notificationSubscription.addBill(bill);
		company.addSubscription(notificationSubscription);

		return notificationSubscription;
	}

	public void sendSms(Notification notification) {
		try{
			validator.validateInBlackList(notification.getSender().getId(), blackListedCompanies);

			if(!validator.validateBilling(notification.getSender())){
				blackListedCompanies.add(notification.getSender().getId());
				throw new UnpaidBillingException(MessageType.UNPAID_BILL.getTextMessageKey());
			}
			smsSender.send(notification);
		} catch (Exception ex){
			MessageUtil.printMessage(smsSender.getMessageConverter().getConvertedMessage(ex.getMessage()));
		}

	}

	public void sendEmail(Notification notification) {
		try{
			validator.validateInBlackList(notification.getSender().getId(), blackListedCompanies);

			if(!validator.validateBilling(notification.getSender())){
				blackListedCompanies.add(notification.getSender().getId());
				throw new UnpaidBillingException(MessageType.UNPAID_BILL.getTextMessageKey());
			}
			emailSender.send(notification);
		} catch (Exception ex){
			MessageUtil.printMessage(emailSender.getMessageConverter().getConvertedMessage(ex.getMessage()));
		}
	}

	public void addToBlackList(Company company) {
		blackListedCompanies.add(company.getId());
	}

}
