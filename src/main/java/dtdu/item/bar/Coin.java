package main.java.dtdu.item.bar;

import java.awt.image.BufferedImage;

import main.java.dtdu.graphics.Textures;
import main.java.dtdu.item.base.*;

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
