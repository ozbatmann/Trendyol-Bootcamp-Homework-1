package model.company;

import enums.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import notificationsubscription.NotificationSubscription;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Company {

	private String id;
	private String name;
	private List<NotificationSubscription> notificationSubscriptionList;

	public void addSubscription(NotificationSubscription notificationSubscription) {
		notificationSubscriptionList.add(notificationSubscription);
	}

	public Optional<NotificationSubscription> getSmsNotificationSubscription() {
		for(NotificationSubscription notificationSubscription : notificationSubscriptionList){
			if(notificationSubscription.getNotificationPackage().getNotificationType().equals(NotificationType.SMS)) return Optional.of(notificationSubscription);
		}
		return Optional.empty();
	}

	public Optional<NotificationSubscription> getEmailNotificationSubscription() {
		for(NotificationSubscription notificationSubscription : notificationSubscriptionList){
			if(notificationSubscription.getNotificationPackage().getNotificationType().equals(NotificationType.EMAIL)) return Optional.of(notificationSubscription);
		}
		return Optional.empty();
	}
}
