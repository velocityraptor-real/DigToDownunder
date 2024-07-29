package dtdu.graphics.screens;

import java.awt.*;
import java.awt.image.BufferedImage;

import dtdu.engine.Ticker;
import dtdu.graphics.*;
import dtdu.graphics.gui.GButton;
import dtdu.world.*;

public class PauseScreen extends GameScreen {
	private static final long serialVersionUID = -2869924301267754407L;
	private GButton mainMenu, contin;
	private Font font;
	public void toggle() {
		if(visible) {
			Ticker.gamePaused = false;
			setVisible(false);
			Screens.render();
		} else {
			Ticker.gamePaused = true;
			setVisible(true);
			Screens.render();
		}
	}
	public void keyEnterPressed() {
		if(mainMenu != null) mainMenu.press();
	}
	private void toggleMainMenu(boolean flag) {
		if(flag) add(mainMenu = new GButton(Language.get("ExittoMenu"), () -> {
			Save.save();
			Save.scene.sceneTranstionTo(Scene.MAIN_MENU);
			Scene.MAIN_MENU.sceneTransitionFrom(Save.scene);
			Ticker.gamePaused = false;
			PauseScreen.this.setVisible(false);
		}));
		else if(mainMenu != null) {
			remove(mainMenu);
			mainMenu = null;
		}
	}
	private void toggleContinue(boolean flag) {
		if(flag) add(contin = new GButton(Language.get("BacktoGame"), () -> {
			PauseScreen.this.setVisible(false);
			Ticker.gamePaused = false;
		}));
		else if(contin != null) {
			remove(contin);
			contin = null;
		}
	}
	@Override
	public void draw(boolean render) {
		int width = getWidth(), height = getHeight();
		if(visible && width > 0 && height > 0) {
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gr = image.createGraphics();
			gr.setColor(Textures.transparent_black);
			gr.fillRect(0, 0, width, height);
			if(mainMenu != null) {
				gr.setColor(Color.WHITE);
				gr.setFont(font);
				Screens.drawCenteredText(Language.get("gamepaused"), gr, getWidth() >> 1, getHeight() / 5);
			}
		} super.draw(render);
	}
	@Override
	public void setVisible(boolean aFlag) {
		if(aFlag) {
			toggleMainMenu(true);
			toggleContinue(true);
			updateDimensions(getWidth(), getHeight());
		} else {
			toggleMainMenu(false);
			toggleContinue(false);
		} super.setVisible(aFlag);
	}
	@Override
	protected void updateDimensions(int width, int height) {
		int x = width / 3, y = height / 3, buttonwidth = width / 3, buttonheight = height / 10, spacing = buttonheight * 4 / 3;
		if(mainMenu != null) mainMenu.setBounds(x, y, buttonwidth, buttonheight);
		if(contin != null) contin.setBounds(x, y + spacing, buttonwidth, buttonheight);
		font = Textures.getFont(width, height);
		super.updateDimensions(width, height);
	}
}