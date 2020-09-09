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
import util.DateUtil;
import util.UUIDGenerator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidationTest {
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	@DisplayName ("Not valid bill information test.")
	public void it_should_not_validate_bill_information() {
		// Given
		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();
		NotificationHub notificationHub = new NotificationHub();

		NotificationPackage notificationPackage = notificationHub.getFixedSmsNotificationPackage(LanguageType.TR);

		notificationHub.initializeSmsSender(notificationPackage);

		notificationHub.subscribe(notificationPackage, company);

		// Subscription lastPaymentDate set as 2 month ago.
		company.getNotificationSubscriptionList().get(0).getBillList().get(0).setLastPaymentDate(DateUtil.minusMonth(DateUtil.nowAsDate(), 1));

		// When
		boolean validationResult = notificationHub.getValidator().validateBilling(company);

		// Then
		Assertions.assertFalse(validationResult);
	}

	@Test
	@DisplayName ("Valid bill information test.")
	public void it_should_validate_bill_information() {
		// Given
		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();
		NotificationHub notificationHub = new NotificationHub();

		NotificationPackage notificationPackage = notificationHub.getFixedSmsNotificationPackage(LanguageType.TR);

		notificationHub.initializeSmsSender(notificationPackage);

		notificationHub.subscribe(notificationPackage, company);

		// When
		boolean validationResult = notificationHub.getValidator().validateBilling(company);

		// Then
		Assertions.assertTrue(validationResult);
	}
	@Test
	@DisplayName ("Is company in black list test.")
	public void it_should_not_validate_black_listed_company() {
		// Given
		Company company = Company.builder().id(UUIDGenerator.getUUID()).name("Trendyol").notificationSubscriptionList(new ArrayList<>()).build();
		NotificationHub notificationHub = new NotificationHub();

		notificationHub.addToBlackList(company);

		NotificationPackage notificationPackage = notificationHub.getFixedSmsNotificationPackage(LanguageType.TR);

		notificationHub.initializeSmsSender(notificationPackage);

		notificationHub.subscribe(notificationPackage, company);

		User user = User.builder().email("aliozbatman@gmail.com").fullName("Ali Imran Ozbatman").phoneNumber("5051403166").build();
		List<User> receivers = Arrays.asList(user);

		Notification notification = Sms.builder().content("Trenyol tarafindan atilan sms").receivers(receivers).sender(company).build();

		// When
		notificationHub.sendSms(notification);

		// Then
		Assertions.assertEquals("Firma kara listededir.\n", outputStreamCaptor.toString());
	}
}
