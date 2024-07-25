package main.java.dtdu.graphics;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * ScaledDrawable acts the same as Drawable, but scales the image provided for {@code paint(Graphics g)} to the size of the component.
 * Use if the {@code draw(boolean render)} method does not create an Image of the same size as the intended screen representation.
 * @author Velocityraptor
 */
public interface ScaledDrawable extends Drawable {
	@Override
	default void paint(Graphics g) {
		if(isVisible()) {
			if(getImage() != null) {
				if(!self().prepareImage(getImage(), getWidth(), getHeight(), self())) while((self().checkImage(getImage(), getWidth(), getHeight(), self()) & (ImageObserver.ALLBITS|ImageObserver.ABORT)) == 0) Thread.onSpinWait();
				setWaitingForImage(!g.drawImage(getImage(), 0, 0, getWidth(), getHeight(), self()));
				while(isWaitingForImage()) Thread.onSpinWait();
			} for(Component c : getComponents()) {
				Graphics gr = g.create();
				gr.translate(c.getX(), c.getY());
				c.paint(gr);
				gr.dispose();
			}
		}
	}
	//Requirements
	int getWidth();
	int getHeight();
}