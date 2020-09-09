package notificationpackage.impl;

import notificationpackage.strategy.ExceedStrategy;
import notificationsubscription.NotificationSubscription;
import notificationsender.NotificationSender;
import notificationsender.NotificationSenderSettings;

public class FlexiblePackageExceedStrategy implements ExceedStrategy {

	@Override
	public void exceedOperation(NotificationSubscription notificationSubscription, NotificationSender notificationSender) {
		notificationSubscription.addAmountToBill();
		NotificationSenderSettings notificationSenderSettings = notificationSender.getNotificationSenderSettings();
		notificationSenderSettings.setQuota(1);
		notificationSender.setNotificationSenderSettings(notificationSenderSettings);
	}
}
