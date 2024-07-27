package main.java.dtdu.graphics.gui;

import main.java.dtdu.graphics.*;

public class LanguageButton extends GButton {
	private static final long serialVersionUID = -8360998284034462342L;
	public LanguageButton(String text, char lang) {
		super(text, () -> {
			Language.loadLanguage(lang);
			Screens.mainMenuScreen.toggleBack(false);
			Screens.mainMenuScreen.toggleLanguages(false);
			Screens.mainMenuScreen.toggleBack(true);
			Screens.mainMenuScreen.toggleLanguages(true);
			Screens.mainMenuScreen.updateDimensions();
			Screens.render();
		});
	}
}