package notificationsender;

import enums.messages.MessageType;
import exception.SubscriptionNotFoundException;
import lombok.Getter;
import model.company.Company;
import model.notification.Notification;
import model.user.User;
import notificationpackage.impl.SmsPackage;
import notificationsubscription.NotificationSubscription;
import util.MessageUtil;

import java.util.Optional;

@Getter
public class SmsSender extends NotificationSender {

	public SmsSender(NotificationSenderSettings notificationSenderSettings) {
		super(notificationSenderSettings);
	}

	/**
	 * @param
	 * notification object given which is sent.
	 * @exception
	 * SubscriptionNotFoundException if quota exceed and suitable subscription is not found.
	 *
	 * **/
	@Override
	public void send(Notification notification) throws SubscriptionNotFoundException {
		for(User user : notification.getReceivers()){
			if(super.validateQuota()){
				MessageUtil.printMessage(super.getMessageConverter().getConvertedMessage(MessageType.SUCCESS_SMS.getTextMessageKey()) + " " + user.getFullName());
				super.decreaseQuota();
			} else {
				quotaExceedOperation(notification.getSender());
			}
		}
	}
	/**
	 * @param
	 * company object is given to reach subscription information.
	 * @exception
	 * SubscriptionNotFoundException if quota exceed and suitable subscription is not found.
	 *
	 * **/
	private void quotaExceedOperation(Company company) throws SubscriptionNotFoundException {
		MessageUtil.printMessage(getMessageConverter().getConvertedMessage(MessageType.SMS_QUOTA_EXCEED.getTextMessageKey()));
		Optional<NotificationSubscription> notificationSubscriptionOptional = company.getSmsNotificationSubscription();

		if(!notificationSubscriptionOptional.isPresent()) throw new SubscriptionNotFoundException(MessageType.SMS_SUBSCRIPTON_NOT_FOUND.getTextMessageKey());
		NotificationSubscription notificationSubscription = notificationSubscriptionOptional.get();
		((SmsPackage) notificationSubscription.getNotificationPackage()).executeExceedStrategy(notificationSubscription, this);

	}

}
