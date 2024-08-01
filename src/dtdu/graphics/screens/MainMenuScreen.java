package dtdu.graphics.screens;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JTextField;

import dtdu.Main;
import dtdu.engine.Sounds;
import dtdu.graphics.*;
import dtdu.graphics.gui.*;
import dtdu.world.*;

public class MainMenuScreen extends GameScreen {
	private static final long serialVersionUID = -4593272722863309251L;
	private GButton loadGame, exit, newSave, back, confirm;
	private JTextField name;
	private GButton[] saves, delete;
	private File[] savesList;
	public MainMenuScreen() {
		loadMainPanel();
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
	@Override
	public void removeAll() {
		super.removeAll();
		loadGame = exit = newSave = back = confirm = null;
		name = null;
		saves = delete = null;
	}
	public void loadMainPanel() {
		removeAll();
		add(loadGame = new GButton(Language.get("LoadGame"), () -> loadGamePanel()));
		add(new GButton(Language.get("Settings"), () -> loadSettingsPanel()));
		add(exit = new GButton(Language.get("Exit"), () -> Main.shutdown()));
		updateDimensions(getWidth(), getHeight());
	}
	public void loadSettingsPanel() {
		removeAll();
		add(back = new GButton(Language.get("GoBack"), () -> {loadMainPanel(); Screens.render();}));
		add(new GButton(Language.get("SelectLanguage"), () -> loadLanguagePanel()));
		add(new GButton(Language.get("Sound"), () -> loadSoundPanel()));
		updateDimensions(getWidth(), getHeight());
		Screens.render();
	}
	public void loadSoundPanel() {
		removeAll();
		add(back = new GButton(Language.get("GoBack"), () -> {loadMainPanel(); Screens.render();}));
		add(new GSlider(Language.get("Master"), (e) -> {Sounds.GAME_VOLUME = e / 100F; Sounds.updateVolume();}, Sounds.GAME_VOLUME));
		add(new GSlider(Language.get("Music"), (e) -> {Sounds.MUSIC_VOLUME = e / 100F; Sounds.updateVolume();}, Sounds.MUSIC_VOLUME));
		updateDimensions(getWidth(), getHeight());
		Screens.render();
	}
	public void loadGamePanel() {
		removeAll();
		savesList = new File("./saves").listFiles((file) -> file.isDirectory());
		add(back = new GButton(Language.get("GoBack"), () -> {loadMainPanel(); Screens.render();}));
		if(savesList.length < 5) add(newSave = new GButton(Language.get("NewGame"), () -> loadNewGamePanel()));
		if(savesList.length > 0) {
			saves = new GButton[savesList.length];
			delete = new GButton[savesList.length];
			for(int i = 0; i < savesList.length; i++) {
				File f = savesList[i];
				add(saves[i] = new GButton(f.getName(), () -> {
					Save.loadSave(f);
					MainMenuScreen.this.removeAll();
				})); add(delete[i] = new GButton(Language.get("Delete"), () -> {
					Main.delete(f);
					loadGamePanel();
				}));
			}
		} updateDimensions(getWidth(), getHeight());
		Screens.render();
	}
	public void loadNewGamePanel() {
		removeAll();
		add(back = new GButton(Language.get("GoBack"), () -> {loadMainPanel(); Screens.render();}));
		add(name = new JTextField(Language.get("MyGame")) {
			private static final long serialVersionUID = 2561891906833502732L;
			int width = 0, height = 0; {
				addKeyListener(new KeyListener(){
					@Override public void keyTyped(KeyEvent e) {}
					@Override public void keyReleased(KeyEvent e) {}
					@Override public void keyPressed(KeyEvent e) {if(e.getKeyCode() == KeyEvent.VK_ENTER) confirm.press();}
				});
			} @Override protected void paintComponent(Graphics g) {
				if(getWidth() != width || getHeight() != height) {
					width = getWidth();
					height = getHeight();
					updateFont(width, height);
				} super.paintComponent(g);
			} protected void updateFont(int width, int height) {
				height = height * 3 / 4;
				width = width * 7 / 50;
				setFont(new Font(Font.MONOSPACED, Font.BOLD, width < height ? width : height));
			}
		});
		add(confirm = new GButton(Language.get("StartGame"), () -> {
			String text = name.getText();
			if(!(text.isEmpty() || text.isBlank() || text.contains("/") || text.contains("\\") || text.contains(".") || text.contains("|") || text.contains(";") || text.contains(":")) && notOccupied(savesList, text))
				Save.newSave(new File("./saves/" + text));
		})); updateDimensions(getWidth(), getHeight());
		Screens.render();
	}
	public void loadLanguagePanel() {
		removeAll();
		add(back = new GButton(Language.get("GoBack"), () -> {loadMainPanel(); Screens.render();}));
		add(new LanguageButton(Language.get("Croatian"), 'h'));
		add(new LanguageButton(Language.get("English"), '#'));
		add(new LanguageButton(Language.get("German"), 'd'));
		add(new LanguageButton(Language.get("Russian"), 'r'));
		add(new LanguageButton(Language.get("Spanish"), 'e'));
		updateDimensions(getWidth(), getHeight());
		Screens.render();
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
		int x = width / 3, y = height / (1 + getComponentCount()), buttonwidth = width / 3, buttonheight = height / 10, spacing = buttonheight * 4 / 3;
		if(saves == null) for(Component c : getComponents()) {
			c.setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
		} else if(saves.length > 4) {
			y = height / (2 + saves.length);
			getComponent(0).setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
			for(int i = 0; i < saves.length; i++) {
				saves[i].setBounds(x, y, buttonwidth, buttonheight);
				delete[i].setBounds(x + (int) (buttonwidth * 1.05F), y, (buttonwidth << 2) / 5, buttonheight);
				y += spacing;
			}
		} else {
			y = height / (3 + saves.length);
			getComponent(0).setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
			getComponent(1).setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
			for(int i = 0; i < saves.length; i++) {
				saves[i].setBounds(x, y, buttonwidth, buttonheight);
				delete[i].setBounds(x + (int) (buttonwidth * 1.05F), y, (buttonwidth << 2) / 5, buttonheight);
				y += spacing;
			}
		} super.updateDimensions(width, height);
	}
}