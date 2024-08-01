package dtdu.graphics.gui;

import java.awt.*;

import javax.swing.JSlider;

import dtdu.graphics.Screens;
import dtdu.util.Applier;

public class GSlider extends JSlider {
	private static final long serialVersionUID = 3716878770835286818L;
	String name;
	Runnable action;
	public GSlider(String name, Applier c, float value) {
		super(0, 100, (int) (value * 100F));
		this.name = name;
		addChangeListener((e) -> c.apply(getValue()));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int height = getHeight() * 3 / 4, width = name.length() > 10 ? (getWidth() << 2) / (name.length() * 3) : getWidth() * 7 / 50;
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, width < height ? width : height));
		Screens.drawCenteredText(name + ": " + getValue() + "%", g, getWidth() >> 1, (getHeight() >> 1));
	}
}