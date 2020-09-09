package enums.language;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageType {
	TR("tr"),
	EN("en");

	private final String languageTag;
}
