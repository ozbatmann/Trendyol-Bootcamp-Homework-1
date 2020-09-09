package notificationpackage.impl;

import notificationpackage.strategy.ExceedStrategy;
import notificationsubscription.NotificationSubscription;
import notificationsender.NotificationSender;
import notificationsender.NotificationSenderSettings;

public class FlexiblePackageExceedStrategy implements ExceedStrategy {
	/**
	 * @param
	 * notificationSubscription notification subscripton information should be changed (Bill) when quota exceed operation performed.
	 * @param
	 * notificationSender notification sender settings (Quota) should be changed when quota exceed operation performed.
	 * **/
	@Override
	public void exceedOperation(NotificationSubscription notificationSubscription, NotificationSender notificationSender) {
		notificationSubscription.addAmountToBill();
		NotificationSenderSettings notificationSenderSettings = notificationSender.getNotificationSenderSettings();
		notificationSenderSettings.setQuota(1);
		notificationSender.setNotificationSenderSettings(notificationSenderSettings);
	}
}
