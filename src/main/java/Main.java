import enums.language.LanguageType;
import model.company.Company;
import model.notification.Email;
import model.notification.Notification;
import model.notification.Sms;
import model.user.User;
import notificationpackage.impl.NotificationPackage;
import util.DateUtil;
import util.UUIDGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// Company created.
		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();

		// Notification Hub created.
		NotificationHub notificationHub = new NotificationHub();

		// Fixed sms package notification created.
		NotificationPackage smsPackage = notificationHub.getFixedSmsNotificationPackage(LanguageType.TR);
		smsPackage.setMAX_QUOTA(1);
		// Company subscribed to the notification package.
		notificationHub.subscribe(smsPackage, company);

		// Notification sender is initialized.
		notificationHub.initializeSmsSender(smsPackage);

		User user = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman").phoneNumber("5051403166").build();
		User user2 = User.builder().email("aliozbatman2@gmail.com").fullName("Ali Imran Ozbatman2").phoneNumber("5051403167").build();
		List<User> smsReceivers = Arrays.asList(user, user2);

		Notification sms = Sms.builder().content("Trenyol tarafindan atilan sms").receivers(smsReceivers).sender(company).build();

		notificationHub.sendSms(sms);

		// Flexible email package notification created.
		NotificationPackage emailPackage = notificationHub.getFlexibleMailNotificationPackage(LanguageType.EN);
		emailPackage.setMAX_QUOTA(1);

		// Company subscribed to the notification package.
		notificationHub.subscribe(emailPackage, company);

		// Notification sender is initialized.
		notificationHub.initializeEmailSender(emailPackage);

		User user3 = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman3").phoneNumber("5051403166").build();
		User user4 = User.builder().email("aliozbatman2@gmail.com").fullName("Ali Imran Ozbatman4").phoneNumber("5051403167").build();
		List<User> mailReceivers = Arrays.asList(user3, user4);

		Notification mail = Sms.builder().content("Trenyol tarafindan atilan sms").receivers(mailReceivers).sender(company).build();

		notificationHub.sendEmail(mail);

		// Changing last payment date
		company.getNotificationSubscriptionList().get(0).getBillList().get(0).setLastPaymentDate(DateUtil.minusMonth(DateUtil.nowAsDate(),1));
		company.getNotificationSubscriptionList().get(1).getBillList().get(0).setLastPaymentDate(DateUtil.minusMonth(DateUtil.nowAsDate(),1));

		// Retry sms and email send operation
		notificationHub.sendSms(sms);
		notificationHub.sendEmail(mail);
	}
}
