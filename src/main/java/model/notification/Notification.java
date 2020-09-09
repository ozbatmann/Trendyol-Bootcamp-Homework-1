package model.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.company.Company;
import model.user.User;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class Notification {

	private Company sender;
	private List<User> receivers;
	private String content;
}
