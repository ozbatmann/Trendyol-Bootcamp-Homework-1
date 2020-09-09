package notificationsender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import notificationsender.message.MessageConverter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public abstract class NotificationSender implements SendNotification {

	private NotificationSenderSettings notificationSenderSettings;

	private MessageConverter messageConverter;

	public NotificationSender(NotificationSenderSettings notificationSenderSettings) {
		this.notificationSenderSettings = notificationSenderSettings;
		this.messageConverter = new MessageConverter(this.notificationSenderSettings.getLanguageType());
	}

	public void decreaseQuota() {
		if(notificationSenderSettings.getQuota() > 0) notificationSenderSettings.setQuota(notificationSenderSettings.getQuota() - 1);
	}

	public boolean validateQuota() {
		return notificationSenderSettings.getQuota() > 0;
	}
}

