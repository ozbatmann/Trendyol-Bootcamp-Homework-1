package notificationsender.message;

import enums.language.LanguageType;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageConverter {

	private static final String baseName = "i18n.messages";

	private final ResourceBundle bundle;

	public MessageConverter(LanguageType languageType) {
		bundle = ResourceBundle.getBundle(baseName, Locale.forLanguageTag(languageType.getLanguageTag()));
	}

	public String getConvertedMessage(String textMessageKey) {
		return bundle.getString(textMessageKey);
	}
}
