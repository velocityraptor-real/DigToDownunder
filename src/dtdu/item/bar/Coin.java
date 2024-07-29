package dtdu.item.bar;

import java.awt.image.BufferedImage;

import dtdu.graphics.Textures;
import dtdu.item.base.*;

public class Coin extends Item {

    public Coin(byte amount) {
        super(amount);
    }

    public BufferedImage getImage() {
        return Textures.coin;
    }

    public String getRegistryName() {
        return "coin";
    }
}
