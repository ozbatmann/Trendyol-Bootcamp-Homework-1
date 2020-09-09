package enums.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationPackageType {
	FIXED_SMS(NotificationType.SMS, 20D, 1000, 20D),
	FLEXIBLE_SMS(NotificationType.SMS, 30D, 2000, 0.10),
	FIXED_EMAIL(NotificationType.EMAIL, 10D, 1000, 10D),
	FLEXIBLE_EMAIL(NotificationType.EMAIL, 7.5, 2000, 0.03);

	private NotificationType notificationType;
	private Double amount;
	private int quoata;
	private Double extraAmount;
}
