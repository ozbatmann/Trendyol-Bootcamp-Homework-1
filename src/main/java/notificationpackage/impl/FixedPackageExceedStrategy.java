package notificationpackage.impl;

import notificationpackage.strategy.ExceedStrategy;
import notificationsubscription.NotificationSubscription;
import notificationsender.NotificationSender;
import notificationsender.NotificationSenderSettings;

public class FixedPackageExceedStrategy implements ExceedStrategy {

	@Override
	public void exceedOperation(NotificationSubscription notificationSubscription, NotificationSender notificationSender) {
		notificationSubscription.refreshNotificationSubscription();
		NotificationSenderSettings notificationSenderSettings = notificationSender.getNotificationSenderSettings();
		notificationSenderSettings.setQuota(notificationSenderSettings.getMAX_QUOTA());
		notificationSender.setNotificationSenderSettings(notificationSenderSettings);
	}
}
