package dtdu.graphics.screens;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JTextField;

import dtdu.Main;
import dtdu.graphics.*;
import dtdu.graphics.gui.*;
import dtdu.world.*;

public class MainMenuScreen extends GameScreen {
	private static final long serialVersionUID = -4593272722863309251L;
	private GButton loadGame, exit, newSave, back, confirm, selectLanguage;
	private JTextField name;
	private GButton[] saves, delete, languages;
	private File[] savesList;
	public MainMenuScreen() {
		toggleLoadGame(true);
		toggleExit(true);
		toggleSelectLanguage(true);
	}
	public void keyEnterPressed() {
		if(loadGame != null) loadGame.press();
		else if(confirm != null) confirm.press();
		else if(saves != null && saves.length > 0) saves[0].press();
		else if(newSave != null) newSave.press();
	}
	public void keyEscapePressed() {
		if(exit != null) exit.press();
		else if(back != null) back.press();
	}
	public void toggleExit(boolean flag) {
		if(flag) add(exit = new GButton(Language.get("Exit"), () -> Main.shutdown()));
		else if(exit != null) {
			remove(exit);
			exit = null;
		} updateDimensions(getWidth(), getHeight());
	}
	public void toggleSelectLanguage(boolean flag) {
		if(flag) add(selectLanguage = new GButton(Language.get("SelectLanguage"), () -> {
			toggleExit(false);
			toggleLoadGame(false);
			toggleSelectLanguage(false);
			toggleBack(true);
			toggleLanguages(true);
			updateDimensions(getWidth(), getHeight());
			Screens.render();
		})); else if(selectLanguage != null) {
			remove(selectLanguage);
			selectLanguage = null;
		}
	}
	public void toggleLanguages(boolean flag) {
		if(flag) {
			languages = new GButton[] {
				(GButton) add(new LanguageButton(Language.get("Croatian"), 'h')),
				(GButton) add(new LanguageButton(Language.get("English"), '#')),
				(GButton) add(new LanguageButton(Language.get("German"), 'd')),
				(GButton) add(new LanguageButton(Language.get("Russian"), 'r')),
				(GButton) add(new LanguageButton(Language.get("Spanish"), 'e'))
		};} else if(languages != null) {
			for(GButton b : languages) remove(b);
			languages = null;
		}
	}
	public void toggleLoadGame(boolean flag) {
		if(flag) add(loadGame = new GButton(Language.get("LoadGame"), () -> {
			toggleSaves(true);
			toggleExit(false);
			toggleSelectLanguage(false);
			toggleLoadGame(false);
			Screens.render();
		})); else if(loadGame != null) {
			remove(loadGame);
			loadGame = null;
		}
	}
	public void toggleBack(boolean flag) {
		if(flag) add(back = new GButton(Language.get("GoBack"), () -> {
			toggleSaves(false);
			toggleName(false);
			toggleConfirm(false);
			toggleBack(false);
			toggleLanguages(false);
			toggleLoadGame(true);
			toggleSelectLanguage(true);
			toggleExit(true);
			Screens.render();
		})); else if(back != null) {
			remove(back);
			back = null;
		}
	}
	public void toggleConfirm(boolean flag) {
		if(flag) add(confirm = new GButton(Language.get("StartGame"), () -> {
			String text = name.getText();
			if(!(text.isEmpty() || text.isBlank() || text.contains("/") || text.contains("\\") || text.contains(".") || text.contains("|") || text.contains(";") || text.contains(":")) && notOccupied(savesList, text))
				Save.newSave(new File("./saves/" + text));
		})); else if(confirm != null) {
			remove(confirm);
			confirm = null;
		}
	}
	private void toggleNewSave(boolean flag) {
		if(flag) add(newSave = new GButton(Language.get("NewGame"), () -> {
			toggleName(true);
			toggleConfirm(true);
			toggleSaves(false);
			updateDimensions(MainMenuScreen.this.getWidth(), MainMenuScreen.this.getHeight());
			Screens.render();
		})); else if(newSave != null) {
			remove(newSave);
			newSave = null;
		}
	}
	public void toggleName(boolean flag) {
		if(flag) add(name = new JTextField(Language.get("MyGame")) {
			private static final long serialVersionUID = 2561891906833502732L;
			int width = 0, height = 0;
			{
				addKeyListener(new KeyListener(){
					@Override public void keyTyped(KeyEvent e) {}
					@Override public void keyReleased(KeyEvent e) {}
					@Override public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) confirm.press();
					}
				});
			}
			@Override
			protected void paintComponent(Graphics g) {
				if(getWidth() != width || getHeight() != height) {
					width = getWidth();
					height = getHeight();
					updateFont(width, height);
				} super.paintComponent(g);
			}
			protected void updateFont(int width, int height) {
				height = height * 3 / 4;
				width = width * 7 / 50;
				setFont(new Font(Font.MONOSPACED, Font.BOLD, width < height ? width : height));
			}
		}); else if(name != null) {
			remove(name);
			name = null;
		}
	}
	private void toggleSaves(boolean flag) {
		if(flag) {
			savesList = new File("./saves").listFiles((file) -> file.isDirectory());
			toggleBack(true);
			if(savesList.length < 5) toggleNewSave(true);
			if(savesList.length > 0) {
				saves = new GButton[savesList.length];
				delete = new GButton[savesList.length];
				for(int i = 0; i < savesList.length; i++) {
					File f = savesList[i];
					add(saves[i] = new GButton(f.getName(), () -> {
						Save.loadSave(f);
						toggleSaves(false);
						toggleBack(false);
					}));
					add(delete[i] = new GButton(Language.get("Delete"), () -> {
						Main.delete(f);
						toggleBack(false);
						toggleSaves(false);
						toggleSaves(true);
					}));
				}
			} updateDimensions(getWidth(), getHeight());
			Screens.render();
		} else {
			toggleNewSave(false);
			if(saves != null) for(int i = 0; i < saves.length; i++) {
				remove(saves[i]);
				remove(delete[i]);
			} delete = null;
			saves = null;
		}
	}
	private boolean notOccupied(File[] list, String name) {
		for(File f : list) if(name.equals(f.getName())) return false;
		return true;
	}
	@Override
	public void draw(boolean render) {
		if(isVisible() && getWidth() > 0 && getHeight() > 0) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = image.createGraphics();
			graphics.setPaintMode();
			graphics.setColor(Textures.blue_background);
			graphics.fillRect(0, 0, getWidth(), getHeight());
			graphics.dispose();
		} super.draw(render);
	}
	public void updateDimensions() {
		updateDimensions(getWidth(), getHeight());
	}
	@Override
	protected void updateDimensions(int width, int height) {
		int x = width / 3, y = height / 3, buttonwidth = width / 3, buttonheight = height / 10, spacing = buttonheight * 4 / 3;
		if(loadGame != null) {
			loadGame.setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
		} if(selectLanguage != null) {
			selectLanguage.setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
		} if(exit != null) exit.setBounds(x, y, buttonwidth, buttonheight);
		if(saves == null && languages == null) {
			if(back != null) {
				back.setBounds(x, y, buttonwidth, buttonheight);
				y += spacing;
			} if(newSave != null) {
				newSave.setBounds(x, y, buttonwidth, buttonheight);
				y += spacing;
			} if(name != null) {
				name.setBounds(x, y, buttonwidth, buttonheight);
				y += spacing;
			} if(confirm != null) confirm.setBounds(x, y, buttonwidth, buttonheight);
		} else {
			if(saves != null) y = height / (saves.length + (saves.length < 5 ? 3 : 2));
			else if(languages != null)  y = height / (languages.length + (languages.length < 5 ? 3 : 2));
			if(back != null) back.setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
			if(newSave != null) {
				newSave.setBounds(x, y, buttonwidth, buttonheight);
				y += spacing;
			} if(saves != null) for(int i = 0; i < saves.length; i++) {
				saves[i].setBounds(x, y, buttonwidth, buttonheight);
				delete[i].setBounds(x + (int) (buttonwidth * 1.05F), y, (buttonwidth << 2) / 5, buttonheight);
				y += spacing;
			} if(languages != null) for(int i = 0; i < languages.length; i++) {
				languages[i].setBounds(x, y, buttonwidth, buttonheight);
				y += spacing;
			}
		} super.updateDimensions(width, height);
	}
}