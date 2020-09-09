package notificationpackage.impl;

import enums.language.LanguageType;
import enums.notification.NotificationPackageType;
import enums.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;
import notificationpackage.strategy.SenderStrategy;
import util.UUIDGenerator;

@Getter
@Setter
public abstract class NotificationPackage implements SenderStrategy {

	private Integer MAX_QUOTA;
	private String id;
	private Double amount;
	private Integer quota;
	private Double extraAmount;
	private LanguageType languageType;
	private NotificationType notificationType;

	NotificationPackage(NotificationPackageType notificationPackageType, LanguageType languageType) {
		id = UUIDGenerator.getUUID();
		amount = notificationPackageType.getAmount();
		quota = notificationPackageType.getQuoata();
		extraAmount = notificationPackageType.getExtraAmount();
		notificationType = notificationPackageType.getNotificationType();
		MAX_QUOTA = getQuota();
		this.languageType = languageType;
	}
}
