package notificationsender;

import exception.SubscriptionNotFoundException;
import model.notification.Notification;

public interface SendNotification {

	public void send(Notification notification) throws SubscriptionNotFoundException;
}
