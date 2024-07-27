package main.java.dtdu.graphics.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import main.java.dtdu.graphics.*;

public class GButton extends JComponent implements Drawable {
	private static final long serialVersionUID = 8783063990827796523L;
	public BufferedImage image;
	public String text;
	private Font font;
	private int width, height;
	private boolean hover = false, active = false, waitingForUpdate = false;
	public GButton(String text, Runnable action) {
		this.text = text;
		addAction(action);
	}
	public GButton(String text) {
		this.text = text;
	}
	{
		addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {}
			@Override public void mousePressed(MouseEvent e) {if(isVisible()) {
				active = true;
				draw(true);
			}}
			@Override public void mouseReleased(MouseEvent e) {if(isVisible()) {
				active = false;
				draw(true);
			}}
			@Override public void mouseEntered(MouseEvent e) {if(isVisible()) {
				hover = true;
				draw(true);
			}}
			@Override public void mouseExited(MouseEvent e) {if(isVisible()) {
				hover = false;
				draw(true);
			}}
		});
	}
	public void addAction(Runnable action) {
		addMouseListener(new MouseListener() {
			@Override public void mousePressed(MouseEvent e) {if(isVisible()) action.run();}
			@Override public void mouseClicked(MouseEvent e) {}
			@Override public void mouseReleased(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
		});
	}
	public void press() {
		processMouseEvent(new MouseEvent(this, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 1, 1, 1, false, MouseEvent.BUTTON1));
	}
	@Override
	public Component self() {
		return this;
	}
	@Override
	public BufferedImage getImage() {
		return image;
	}
	@Override
	public void draw(boolean render) {
		if(isVisible() && getWidth() > 0 && getHeight() > 0) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = image.createGraphics();
			graphics.drawImage(active ? Textures.button_active : hover ? Textures.button_hover : Textures.button_idle, 0, 0, width, height, this);
			graphics.setFont(font);
			graphics.setColor(getForeground());
			Screens.drawCenteredText(text, graphics, getWidth() >> 1, (getHeight() * 11) >> 4);
			graphics.dispose();
			Drawable.super.draw(render);
		}
	}
	protected void updateFont(int width, int height) {
		height = height * 3 / 4;
		width = text.length() > 10 ? (width << 2) / (text.length() * 3) : width * 7 / 50;
		font = new Font(Font.MONOSPACED, Font.BOLD, width < height ? width : height);
	}
	@Override
	public void paint(Graphics g) {
		if(getWidth() != width || getHeight() != height) {
			width = getWidth();
			height = getHeight();
			updateFont(width, height);
			draw(false);
		} Drawable.super.paint(g);
	}
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		return waitingForUpdate = super.imageUpdate(img, infoflags, x, y, w, h);
	}
	@Override
	public void setWaitingForImage(boolean flag) {
		waitingForUpdate = flag;
	}
	@Override
	public boolean isWaitingForImage() {
		return waitingForUpdate;
	}
}