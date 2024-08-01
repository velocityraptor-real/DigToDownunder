package dtdu.graphics.gui;

import dtdu.graphics.*;

public class LanguageButton extends GButton {
	private static final long serialVersionUID = -8360998284034462342L;
	public LanguageButton(String text, char lang) {
		super(text, () -> {
			Language.loadLanguage(lang);
			Screens.mainMenuScreen.loadLanguagePanel();
		});
	}
}