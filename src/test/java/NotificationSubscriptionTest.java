import enums.language.LanguageType;
import model.company.Company;
import model.notification.Notification;
import model.notification.Sms;
import model.user.User;
import notificationpackage.impl.NotificationPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.UUIDGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationSubscriptionTest {

	@Test
	public void it_should_double_bill_amount() {
		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();
		NotificationHub notificationHub = new NotificationHub();

		NotificationPackage notificationPackage = notificationHub.getFixedSmsNotificationPackage(LanguageType.TR);

		// Set quota as 1.
		notificationPackage.setMAX_QUOTA(1);
		notificationHub.subscribe(notificationPackage, company);

		notificationHub.initializeSmsSender(notificationPackage);

		notificationsubscription.NotificationSubscription subscriptionBefore = company.getSmsNotificationSubscription().get();

		Double amountBefore = subscriptionBefore.getBillList().get(subscriptionBefore.getBillList().size() - 1).getAmount();

		User user = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman").phoneNumber("5051403166").build();
		User user2 = User.builder().email("aliozbatman2@gmail.com").fullName("Ali Imran Ozbatman2").phoneNumber("5051403167").build();
		List<User> receivers = Arrays.asList(user, user2);

		Notification notification = Sms.builder().content("Trenyol tarafindan atilan sms").receivers(receivers).sender(company).build();

		notificationHub.sendSms(notification);
		notificationsubscription.NotificationSubscription subscriptionAfter = company.getSmsNotificationSubscription().get();
		double amountAfter = subscriptionAfter.getBillList().get(subscriptionBefore.getBillList().size() - 1).getAmount();

		Assertions.assertNotEquals(amountBefore, amountAfter);
		Assertions.assertEquals(amountBefore * 2, amountAfter);

	}

	@Test
	public void it_should_increase_amount_by_one_extra_amount() {
		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();
		NotificationHub notificationHub = new NotificationHub();

		NotificationPackage notificationPackage = notificationHub.getFlexibleSmsNotificationPackage(LanguageType.TR);

		// Set quota as 1.
		notificationPackage.setMAX_QUOTA(1);
		notificationHub.subscribe(notificationPackage, company);

		notificationHub.initializeSmsSender(notificationPackage);

		notificationsubscription.NotificationSubscription subscriptionBefore = company.getSmsNotificationSubscription().get();

		Double amountBefore = subscriptionBefore.getBillList().get(subscriptionBefore.getBillList().size() - 1).getAmount();

		User user = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman").phoneNumber("5051403166").build();
		User user2 = User.builder().email("aliozbatman2@gmail.com").fullName("Ali Imran Ozbatman2").phoneNumber("5051403167").build();
		List<User> receivers = Arrays.asList(user, user2);

		Notification notification = Sms.builder().content("Trenyol tarafindan atilan sms").receivers(receivers).sender(company).build();

		notificationHub.sendSms(notification);
		notificationsubscription.NotificationSubscription subscriptionAfter = company.getSmsNotificationSubscription().get();
		double amountAfter = subscriptionAfter.getBillList().get(subscriptionBefore.getBillList().size() - 1).getAmount();

		Assertions.assertNotEquals(amountBefore, amountAfter);
		Assertions.assertEquals(amountBefore + notificationPackage.getExtraAmount(), amountAfter);

	}
}
