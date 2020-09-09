package enums.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
	UNPAID_BILL("validation.bill.not.paid"),
	SMS_QUOTA_EXCEED("validation.sms.quota.exceed"),
	EMAIL_QUOTA_EXCEED("validation.email.quota.exceed"),
	BLACK_LISTED_COMPANY("validation.black.listed.company"),
	SUCCESS_SMS("success.sms.send"),
	SUCCESS_EMAIL("success.email.send"),
	SMS_SUBSCRIPTON_NOT_FOUND("error.sms.subscription.not.found"),
	EMAIL_SUBSCRIPTON_NOT_FOUND("error.email.subscription.not.found");

	private final String textMessageKey;
}
