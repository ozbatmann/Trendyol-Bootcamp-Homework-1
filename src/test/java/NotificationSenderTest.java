import enums.language.LanguageType;
import model.company.Company;
import model.notification.Notification;
import model.notification.Sms;
import model.user.User;
import notificationpackage.impl.NotificationPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.UUIDGenerator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NotificationSenderTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	@DisplayName ("Sms success message")
	public void it_should_send_sms() {
		// Given
		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();
		NotificationHub notificationHub = new NotificationHub();

		NotificationPackage notificationPackage = notificationHub.getFixedSmsNotificationPackage(LanguageType.TR);

		notificationHub.subscribe(notificationPackage, company);

		notificationHub.initializeSmsSender(notificationPackage);

		User user = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman").phoneNumber("5051403166").build();
		List<User> receivers = Arrays.asList(user);

		Notification notification = Sms.builder().content("Trenyol tarafindan atilan sms").receivers(receivers).sender(company).build();

		notificationHub.sendSms(notification);

		Assertions.assertEquals("Sms basariyla gonderildi. "+user.getFullName()+"\n", outputStreamCaptor.toString());
	}

	@Test
	@DisplayName ("Quota exceed exception messsage")
	public void it_should_not_send_sms_quota_exceed() {

		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();
		NotificationHub notificationHub = new NotificationHub();

		NotificationPackage notificationPackage = notificationHub.getFixedSmsNotificationPackage(LanguageType.TR);

		// Set quota as 0.
		notificationPackage.setMAX_QUOTA(0);
		notificationHub.subscribe(notificationPackage, company);

		notificationHub.initializeSmsSender(notificationPackage);

		User user = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman").phoneNumber("5051403166").build();
		//User user2 = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman2").phoneNumber("5051403166").build();
		List<User> receivers = Arrays.asList(user);

		Notification notification = Sms.builder().content("Trenyol tarafindan atilan sms").receivers(receivers).sender(company).build();

		notificationHub.sendSms(notification);
		Assertions.assertEquals("Mevcut sms kotasi asildi.\n", outputStreamCaptor.toString());
	}
}
