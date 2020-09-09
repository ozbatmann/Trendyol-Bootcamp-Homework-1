package notificationsender;

import enums.language.LanguageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NotificationSenderSettings {

	private int quota;
	private LanguageType languageType;
	private final int MAX_QUOTA;
}
