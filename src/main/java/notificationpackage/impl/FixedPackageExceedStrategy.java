package notificationpackage.impl;

import notificationpackage.strategy.ExceedStrategy;
import notificationsubscription.NotificationSubscription;
import notificationsender.NotificationSender;
import notificationsender.NotificationSenderSettings;

public class FixedPackageExceedStrategy implements ExceedStrategy {
	/**
	 * @param
	 * notificationSubscription notification subscripton information should be changed (Bill) when quota exceed operation performed.
	 * @param
	 * notificationSender notification sender settings (Quota) should be changed when quota exceed operation performed.
	 * **/
	@Override
	public void exceedOperation(NotificationSubscription notificationSubscription, NotificationSender notificationSender) {
		notificationSubscription.refreshNotificationSubscription();
		NotificationSenderSettings notificationSenderSettings = notificationSender.getNotificationSenderSettings();
		notificationSenderSettings.setQuota(notificationSenderSettings.getMAX_QUOTA());
		notificationSender.setNotificationSenderSettings(notificationSenderSettings);
	}
}
