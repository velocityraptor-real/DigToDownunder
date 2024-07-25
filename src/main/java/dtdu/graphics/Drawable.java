package main.java.dtdu.graphics;

import java.awt.*;
import java.awt.image.*;

/**
 * Drawable is the standard interface for graphics objects.
 * Drawables should have a BufferedImage image field which contains the current look of the entire object.
 * When the Drawable changes it's appearance, it should either change or entirely replace the image with the {@code draw(boolean render)} method.
 * @author Velocityraptor
 */
public interface Drawable {
	/**
	 * Standard method to create the image of the object.
	 * @param render whether the screen should show the changes. Usually true.
	 * Only set false if you are bulk drawing multiple Drawables and intend to only call {@code Screens.render()} once at the end for efficiency reasons.
	 */
	default void draw(boolean render) {
		if(render) Screens.render();
	}
	/**
	 * Final method that renders the Drawable's Image. Should usually not be overridden.
	 * @param g the graphics for the object to paint in, translated to the object's position
	 */
	default void paint(Graphics g) {
		if(isVisible()) {
			if(getImage() != null) {
				if(!self().prepareImage(getImage(), self())) while((self().checkImage(getImage(), self()) & (ImageObserver.ALLBITS|ImageObserver.ABORT)) == 0) Thread.onSpinWait();
				setWaitingForImage(!g.drawImage(getImage(), 0, 0, self()));
				while(isWaitingForImage()) Thread.onSpinWait();
			} for(Component c : getComponents()) {
				Graphics gr = g.create();
				gr.translate(c.getX(), c.getY());
				c.paint(gr);
				gr.dispose();
			}
		}
	}
	boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height);
	//requirements
	Component self();
	BufferedImage getImage();
	Component[] getComponents();
	boolean isVisible();
	void setWaitingForImage(boolean flag);
	boolean isWaitingForImage();
}