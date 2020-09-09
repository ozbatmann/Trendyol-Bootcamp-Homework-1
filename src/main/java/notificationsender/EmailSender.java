package notificationsender;

import enums.messages.MessageType;
import exception.SubscriptionNotFoundException;
import lombok.Getter;
import model.notification.Notification;
import model.user.User;
import notificationpackage.impl.EmailPackage;
import notificationsubscription.NotificationSubscription;
import util.MessageUtil;

import java.util.Optional;

@Getter
public class EmailSender extends NotificationSender {

	public EmailSender(NotificationSenderSettings notificationSenderSettings) {
		super(notificationSenderSettings);
	}

	@Override
	public void send(Notification notification) throws SubscriptionNotFoundException {
		for(User user : notification.getReceivers()){
			if(super.validateQuota()){
				MessageUtil.printMessage(super.getMessageConverter().getConvertedMessage(MessageType.SUCCESS_EMAIL.getTextMessageKey()) + " " + user.getFullName());
				super.decreaseQuota();
			} else {
				quotaExceedOperation(notification);
			}
		}

	}

	private void quotaExceedOperation(Notification notification) throws SubscriptionNotFoundException {
		MessageUtil.printMessage(getMessageConverter().getConvertedMessage(MessageType.EMAIL_QUOTA_EXCEED.getTextMessageKey()));
		Optional<NotificationSubscription> notificationSubscriptionOptional = notification.getSender().getEmailNotificationSubscription();

		if(!notificationSubscriptionOptional.isPresent()) throw new SubscriptionNotFoundException(MessageType.EMAIL_SUBSCRIPTON_NOT_FOUND.getTextMessageKey());
		NotificationSubscription notificationSubscription = notificationSubscriptionOptional.get();
		((EmailPackage) notificationSubscription.getNotificationPackage()).executeExceedStrategy(notificationSubscription, this);
	}

}