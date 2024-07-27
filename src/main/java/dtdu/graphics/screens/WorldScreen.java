package main.java.dtdu.graphics.screens;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import main.java.dtdu.graphics.*;
import main.java.dtdu.graphics.gui.Dialogue;
import main.java.dtdu.object.base.*;
import main.java.dtdu.world.Save;

public class WorldScreen extends GameScreen {
	private static final long serialVersionUID = -5064589157795472812L;
	public volatile float zoom = 3F;
	public volatile String speechText, hearText;
	Font font;
	@Override
	public void draw(boolean render) {
		if(isVisible() && getWidth() > 0 && getHeight() > 0) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = image.createGraphics();
			BufferedImage i = Save.scene.getBackgroundImage(), im;
			if(i != null) {
				im = new BufferedImage(i.getWidth(), i.getHeight(), i.getType());
				Graphics2D gr = im.createGraphics();
				gr.drawImage(i, 0, 0, null);
				i = im;
				int j = 0;
				GameObject o;
				Individual in;
				GameObject[][] sm = Save.map.staticMap;
				List<Individual> dm = Save.map.dynamicMap;
				for(int y = 0, yl = sm[0].length; y < yl; y++) for(int x = 0, xl = sm.length; x < xl; x++) {
					if(dm != null) synchronized(dm){ while(j < dm.size()) {
						in = dm.get(j);
						if((int) in.x > x || (int) in.y > y) break;
						im = in.getImage();
						if(im != null) gr.drawImage(im, (int) (in.x * 32F - (im.getWidth() >> 1)), (int) (in.y * 32F - (im.getHeight() >> 1)), null);
						j++;
					}} o = sm[x][y];
					if(o != null) {
						im = o.getImage();
						if(im != null) gr.drawImage(im, (x << 5) - (im.getWidth() >> 1), (y << 5) - (im.getHeight() >> 1), null);
					}
				} if(speechText != null && !speechText.isEmpty()) {
					gr.setColor(Textures.speechbox_blue);
					int y = ((i.getHeight() * 3) >> 2);
					gr.fillRect(1, y, i.getWidth() - 2, (i.getHeight() >> 2) - 2);
					gr.setColor(Textures.gold);
					gr.drawRect(0, y, i.getWidth(), i.getHeight() >> 2);
					im = Dialogue.d.getImage();
					if(im != null) gr.drawImage(im, 1, y, (i.getWidth() >> 2) - 2, (i.getHeight() >> 2) - 2, null);
					gr.setFont(font);
					Screens.drawText(speechText, gr, i.getWidth() >> 2, y - (i.getHeight() >> 5), 18);
				} else if(hearText != null && !hearText.isEmpty()) {
					gr.setColor(Textures.speechbox_blue);
					gr.fillRect(1, 1, i.getWidth() - 2, (i.getHeight() >> 2) - 2);
					gr.setColor(Textures.gold);
					gr.drawRect(0, 0, i.getWidth(), i.getHeight() >> 2);
					im = Dialogue.d.getImage();
					if(im != null) gr.drawImage(im, 1, 1, (i.getWidth() >> 2) - 2, (i.getHeight() >> 2) - 2, null);
					gr.setFont(font);
					Screens.drawText(hearText, gr, i.getWidth() >> 2, i.getHeight() >> 4, 18);
				} if(Save.player == null || Save.scene.isFilledRender()) graphics.drawImage(i, 0, 0, getWidth(), getHeight(), this);
				else {
					if(i.getWidth() * zoom < getWidth()) zoom = (float) getWidth() / (float) i.getWidth();
					if(i.getHeight() * zoom < getHeight()) zoom = (float) getHeight() / (float) i.getHeight();
					float tilePixelScale = zoom * 32F;
					int x = (int) -(tilePixelScale * Save.player.x) + getWidth() >> 2, y =  (int) -(tilePixelScale * Save.player.y) + getHeight() >> 2, width = (int) (i.getWidth() * zoom), height = (int) (i.getHeight() * zoom);
					if(x + width < getWidth()) x = getWidth() - width;
					else if(x > 0) x = 0;
					if(y + height < getHeight()) y = getHeight() - height;
					else if(y > 0) y = 0;
					graphics.drawImage(i, x, y, width, height, getBackground(), this);
				} gr.dispose();
			} graphics.dispose();
		} super.draw(render);
	}
	@Override
	protected void updateDimensions(int width, int height) {
		font = Textures.getFont(width, height);
		super.updateDimensions(width, height);
	}
}