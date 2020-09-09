package model.notification;

import lombok.Builder;
import lombok.Getter;
import model.company.Company;
import model.user.User;

import java.util.List;

@Getter
public class Email extends Notification {

	private List<User> ccs;

	@Builder
	Email(Company sender, List<User> receivers, String content, List<User> ccs) {
		super(sender, receivers, content);
		this.ccs = ccs;
	}
}
