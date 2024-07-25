package main.java.dtdu.graphics.screens;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JTextField;

import main.java.dtdu.Main;
import main.java.dtdu.graphics.Screens;
import main.java.dtdu.graphics.Textures;
import main.java.dtdu.graphics.gui.GButton;
import main.java.dtdu.util.Utils;
import main.java.dtdu.world.*;

public class MainMenuScreen extends GameScreen {
	private static final long serialVersionUID = -4593272722863309251L;
	private GButton loadGame, exit, newSave, back, confirm;
	private JTextField name;
	private GButton[] saves, delete;
	private File[] savesList;
	public MainMenuScreen() {
		toggleLoadGame(true);
		toggleExit(true);
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
		if(flag) add(exit = new GButton("Exit", () -> Main.shutdown()));
		else if(exit != null) {
			remove(exit);
			exit = null;
		} updateDimensions(getWidth(), getHeight());
	}
	public void toggleLoadGame(boolean flag) {
		if(flag) add(loadGame = new GButton("Load Game", () -> {
				toggleSaves(true);
				toggleExit(false);
				toggleLoadGame(false);
				Screens.render();
			}));
		else if(loadGame != null) {
			remove(loadGame);
			loadGame = null;
		}
	}
	public void toggleBack(boolean flag) {
		if(flag) add(back = new GButton("Go Back", () -> {
				toggleSaves(false);
				toggleName(false);
				toggleConfirm(false);
				toggleBack(false);
				toggleLoadGame(true);
				toggleExit(true);
				Screens.render();
			}));
		else if(back != null) {
			remove(back);
			back = null;
		}
	}
	public void toggleConfirm(boolean flag) {
		if(flag) add(confirm = new GButton("Start Game", () -> {
				String text = name.getText();
				if(!(text.isEmpty() || text.isBlank() || text.contains("/") || text.contains("\\") || text.contains(".") || text.contains("|") || text.contains(";") || text.contains(":")) && notOccupied(savesList, text))
					Save.newSave(new File("./saves/" + text));
		}));
		else if(confirm != null) {
			remove(confirm);
			confirm = null;
		}
	}
	private void toggleNewSave(boolean flag) {
		if(flag) add(newSave = new GButton("New Game", () -> {
				toggleName(true);
				toggleConfirm(true);
				toggleSaves(false);
				updateDimensions(MainMenuScreen.this.getWidth(), MainMenuScreen.this.getHeight());
				Screens.render();
			}));
		else if(newSave != null) {
			remove(newSave);
			newSave = null;
		}
	}
	public void toggleName(boolean flag) {
		if(flag) {
			name = new JTextField("My Game") {
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
			};
			add(name);
		} else if(name != null) {
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
					add(delete[i] = new GButton("Delete", () -> {
						Utils.delete(f);
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
	@Override
	protected void updateDimensions(int width, int height) {
		int x = width / 3, y = height / 3, buttonwidth = width / 3, buttonheight = height / 10, spacing = buttonheight * 4 / 3;
		if(loadGame != null) loadGame.setBounds(x, y, buttonwidth, buttonheight);
		if(exit != null) exit.setBounds(x, y + spacing, buttonwidth, buttonheight);
		if(saves == null) {
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
			y = height / (saves.length + (saves.length < 5 ? 3 : 2));
			if(back != null) back.setBounds(x, y, buttonwidth, buttonheight);
			y += spacing;
			if(newSave != null) {
				newSave.setBounds(x, y, buttonwidth, buttonheight);
				y += spacing;
			} for(int i = 0; i < saves.length; i++) {
				saves[i].setBounds(x, y, buttonwidth, buttonheight);
				delete[i].setBounds(x + (int) (buttonwidth * 1.05F), y, (buttonwidth << 2) / 5, buttonheight);
				y += spacing;
			}
		} super.updateDimensions(width, height);
	}
}