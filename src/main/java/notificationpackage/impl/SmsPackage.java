package notificationpackage.impl;

import enums.language.LanguageType;
import enums.notification.NotificationPackageType;
import notificationpackage.strategy.ExceedStrategy;
import notificationsubscription.NotificationSubscription;
import notificationsender.NotificationSender;
import notificationsender.NotificationSenderSettings;
import notificationsender.SmsSender;

public class SmsPackage extends NotificationPackage {

	ExceedStrategy exceedStrategy;

	public SmsPackage(ExceedStrategy exceedStrategy, NotificationPackageType notificationPackageType, LanguageType languageType) {
		super(notificationPackageType, languageType);
		this.exceedStrategy = exceedStrategy;
	}

	@Override
	public NotificationSender initializeSender() {
		return new SmsSender(NotificationSenderSettings.builder().languageType(getLanguageType()).MAX_QUOTA(getMAX_QUOTA()).quota(getMAX_QUOTA()).build());
	}

	public void executeExceedStrategy(NotificationSubscription notificationSubscription,NotificationSender notificationSender) {
		exceedStrategy.exceedOperation(notificationSubscription,notificationSender);
	}
}
