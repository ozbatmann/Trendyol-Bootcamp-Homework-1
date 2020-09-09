package notificationpackage.strategy;

import notificationsubscription.NotificationSubscription;
import notificationsender.NotificationSender;

public interface ExceedStrategy {

	void exceedOperation(NotificationSubscription notificationSubscription, NotificationSender notificationSender);
}
