package notificationpackage.impl;

import enums.language.LanguageType;
import enums.notification.NotificationPackageType;
import lombok.Getter;
import notificationpackage.strategy.ExceedStrategy;
import notificationsubscription.NotificationSubscription;
import notificationsender.EmailSender;
import notificationsender.NotificationSender;
import notificationsender.NotificationSenderSettings;

public class EmailPackage extends NotificationPackage {

	@Getter
	ExceedStrategy exceedStrategy;

	/**
	 * @param
	 * exceedStrategy I hold exceedStrategy as an instance encapsulate it's behaviour in EmailPackage context.
	 *
	 **/
	public EmailPackage(ExceedStrategy exceedStrategy, NotificationPackageType notificationPackageType, LanguageType languageType) {
		super(notificationPackageType, languageType);
		this.exceedStrategy = exceedStrategy;
	}

	@Override
	public NotificationSender initializeSender() {
		return new EmailSender(NotificationSenderSettings.builder().languageType(getLanguageType()).MAX_QUOTA(getMAX_QUOTA()).quota(getMAX_QUOTA()).build());
	}

	public void executeExceedStrategy(NotificationSubscription notificationSubscription, NotificationSender notificationSender) {
		exceedStrategy.exceedOperation(notificationSubscription, notificationSender);
	}
}
