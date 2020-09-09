package model.notification;

import lombok.Builder;
import lombok.Getter;
import model.company.Company;
import model.user.User;

import java.util.List;

@Getter

public class Sms extends Notification {

	@Builder
	public Sms(Company sender, List<User> receivers, String content) {
		super(sender, receivers, content);
	}
}
