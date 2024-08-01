package dtdu.graphics;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import dtdu.graphics.gui.*;
import dtdu.graphics.screens.*;
import dtdu.world.*;

public class Screens {
	public static volatile boolean shift = false;
	public static final JFrame frame = new JFrame("Dig To Downunder") {
		private static final long serialVersionUID = -5813497523539285446L;
		static boolean w = false, kp_up = false, up = false, s = false, kp_down = false, down = false, a = false, kp_left = false, left = false, d = false, kp_right = false, right = false;
		{
			addMouseListener(new MouseListener() {
				@Override public void mouseReleased(MouseEvent e) {}
				@Override public void mouseEntered(MouseEvent e) {}
				@Override public void mouseExited(MouseEvent e) {}
				@Override public void mouseClicked(MouseEvent e) {}
				@Override public void mousePressed(MouseEvent e) {
					if(inventoryScreen.isVisible()) {
						if(inventoryScreen.selected != inventoryScreen.hover) {
							inventoryScreen.selected = inventoryScreen.hover;
							inventoryScreen.draw(true);
						}
					}
				}
			});
			addMouseMotionListener(new MouseMotionListener() {
				@Override public void mouseDragged(MouseEvent e) {}
				@Override public void mouseMoved(MouseEvent e) {
					if(inventoryScreen.isVisible()) {
						int slot = inventoryScreen.getSlotForPos((e.getX() << 7) / getWidth(), ((e.getY() << 6) + (e.getY() << 5)) / getHeight());
						if(slot != inventoryScreen.hover) {
							inventoryScreen.hover = slot;
							inventoryScreen.draw(true);
						}
					}
			}});
			addMouseWheelListener((e) -> {
				if(!Save.scene.isFilledRender()) {
					float r = worldScreen.zoom - e.getWheelRotation() * .1F;
					r = r < 1F ? 1F : (r > 10F ? 10F : r);
					if(worldScreen.zoom != r) {
						worldScreen.zoom = r;
						worldScreen.draw(true);
					}
				}
			});
			addKeyListener(new KeyListener() {
				@Override public void keyTyped(KeyEvent e) {}
				@Override public void keyPressed(KeyEvent e) {
					int code = e.getKeyCode();
					switch(code) {
					case KeyEvent.VK_SHIFT: shift = true; break;
					case KeyEvent.VK_ESCAPE:
						if(mainMenuScreen.isVisible()) mainMenuScreen.keyEscapePressed();
						else if(inventoryScreen.isVisible()) inventoryScreen.toggle();
						else pauseScreen.toggle();
						break;
					default:
						if(Dialogue.d == null) switch(code) {
							case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE:
								if(mainMenuScreen.isVisible()) mainMenuScreen.keyEnterPressed();
								else if(pauseScreen.isVisible()) pauseScreen.keyEnterPressed();
								break;
							case KeyEvent.VK_E: if(Save.scene != Scene.MAIN_MENU) inventoryScreen.toggle(); break;
							case KeyEvent.VK_W, KeyEvent.VK_KP_UP, KeyEvent.VK_UP:
								if(code == KeyEvent.VK_W) w = true;
								else if(code == KeyEvent.VK_KP_UP) kp_up = true;
								else if(code == KeyEvent.VK_UP) up = true;
								if(Save.player != null) Save.player.upKey = true;
								break;
							case KeyEvent.VK_S, KeyEvent.VK_KP_DOWN, KeyEvent.VK_DOWN:
								if(code == KeyEvent.VK_S) s = true;
								else if(code == KeyEvent.VK_KP_DOWN) kp_down = true;
								else if(code == KeyEvent.VK_DOWN) down = true;
								if(Save.player != null) Save.player.downKey = true;
								break;
							case KeyEvent.VK_A, KeyEvent.VK_KP_LEFT, KeyEvent.VK_LEFT:
								if(code == KeyEvent.VK_A) a = true;
								else if(code == KeyEvent.VK_KP_LEFT) kp_left = true;
								else if(code == KeyEvent.VK_LEFT) left = true;
								if(Save.player != null) Save.player.leftKey = true;
								break;
							case KeyEvent.VK_D, KeyEvent.VK_KP_RIGHT, KeyEvent.VK_RIGHT:
								if(code == KeyEvent.VK_D) d = true;
								else if(code == KeyEvent.VK_KP_RIGHT) kp_right = true;
								else if(code == KeyEvent.VK_RIGHT) right = true;
								if(Save.player != null) Save.player.rightKey = true;
								break;
						} else if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) Dialogue.d.keySpacePressed();
					}
				}
				@Override public void keyReleased(KeyEvent e) {
					int code = e.getKeyCode();
					switch(code) {
					case KeyEvent.VK_SHIFT:
						shift = false;
						break;
					case KeyEvent.VK_W, KeyEvent.VK_KP_UP, KeyEvent.VK_UP:
						if(code == KeyEvent.VK_W) w = false;
						else if(code == KeyEvent.VK_KP_UP) kp_up = false;
						else if(code == KeyEvent.VK_UP) up = false;
						if(!(w || kp_up || up || Save.player == null)) Save.player.upKey = false;
						break;
					case KeyEvent.VK_S, KeyEvent.VK_KP_DOWN, KeyEvent.VK_DOWN:
						if(code == KeyEvent.VK_S) s = false;
						else if(code == KeyEvent.VK_KP_DOWN) kp_down = false;
						else if(code == KeyEvent.VK_DOWN) down = false;
						if(!(s || kp_down || down || Save.player == null)) Save.player.downKey = false;
						break;
					case KeyEvent.VK_A, KeyEvent.VK_KP_LEFT, KeyEvent.VK_LEFT:
						if(code == KeyEvent.VK_A) a = false;
						else if(code == KeyEvent.VK_KP_LEFT) kp_left = false;
						else if(code == KeyEvent.VK_LEFT) left = false;
						if(!(a || kp_left || left || Save.player == null)) Save.player.leftKey = false;
						break;
					case KeyEvent.VK_D, KeyEvent.VK_KP_RIGHT, KeyEvent.VK_RIGHT:
						if(code == KeyEvent.VK_D) d = false;
						else if(code == KeyEvent.VK_KP_RIGHT) kp_right = false;
						else if(code == KeyEvent.VK_RIGHT) right = false;
						if(!(d || kp_right || right || Save.player == null)) Save.player.rightKey = false;
						break;
					}
				}
			});
		}
	};
	public static final LayoutManager lazyManager = new LayoutManager() {
		@Override public void addLayoutComponent(String name, Component comp) {}
		@Override public void removeLayoutComponent(Component comp) {}
		@Override public Dimension preferredLayoutSize(Container parent) {return parent.getSize();}
		@Override public Dimension minimumLayoutSize(Container parent) {return parent.getMinimumSize();}
		@Override public void layoutContainer(Container parent) {
			synchronized (parent.getTreeLock()) {for(int i = parent.getComponentCount() - 1; i > -1; i--) {
				Component m = parent.getComponent(i);
				if(m.isVisible()) m.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			}}
		}
	};
	public static final JPanel screen = new JPanel(lazyManager) {
		private static final long serialVersionUID = 6410843779087900305L;
		@Override public void paint(Graphics g) {paintComponent(g);}
		@Override public void paintComponent(Graphics g) {
			if(isVisible() && getWidth() > 0 && getHeight() > 0) {
				BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D gr = image.createGraphics();
				for(Component c : screen.getComponents()) c.paint(gr);
				gr.dispose();
				g.drawImage(image, 0, 0, this);
			}
		}
	};
	public static final MainMenuScreen mainMenuScreen = new MainMenuScreen();
	public static final WorldScreen worldScreen = new WorldScreen();
	public static final InventoryScreen inventoryScreen = new InventoryScreen();
	public static final Vignette vignette = new Vignette();
	public static final PauseScreen pauseScreen = new PauseScreen();
	public static final FadeScreen fadescreen = new FadeScreen();
	public static void compileScreens() {
		frame.add(screen);
		screen.add(worldScreen);
		screen.add(inventoryScreen);
		screen.add(mainMenuScreen);
		screen.add(vignette);
		screen.add(fadescreen);
		screen.add(pauseScreen);
		pauseScreen.setVisible(false);
		fadescreen.setVisible(false);
	}
	public static synchronized void render() {
		if(screen.isVisible()) screen.paint(screen.getGraphics());
	}
	public static void drawText(String text, Graphics2D graphics, int x, int y, int rowSize) {
		final int i = text.length() / rowSize + (text.length() % rowSize == 0 ? 0 : 1), h = graphics.getFontMetrics().getHeight();
		for(int k = 0, j = 0; k < i; k++) {
			if(k == i - 1) graphics.drawString(text.substring(j), x, y);
			else {
				graphics.drawString(text.substring(j, j + rowSize), x, y);
				y += h;
				j += rowSize;
			}
		}
	}
	public static void drawCenteredText(String text, Graphics graphics, int centerX, int y) {
		graphics.drawString(text, centerX - (graphics.getFontMetrics().stringWidth(text) >> 1), y);
	}
	public static void drawCenteredText(String text, Graphics2D graphics, int centerX, int y, int rowSize) {
		final int i = text.length() / rowSize + (text.length() % rowSize == 0 ? 0 : 1), h = graphics.getFontMetrics().getHeight();
		String s;
		for(int k = 0, j = 0; k < i; k++) {
			if(k == i - 1) {
				s = text.substring(j);
				graphics.drawString(s, centerX - (graphics.getFontMetrics().stringWidth(s) >> 1), y);
			} else {
				s = text.substring(j, j + rowSize);
				graphics.drawString(s, centerX - (graphics.getFontMetrics().stringWidth(s) >> 1), y);
				y += h;
				j += rowSize;
			}
		}
	}
}