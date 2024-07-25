package main.java.dtdu.item;

import main.java.dtdu.engine.Registry;
import main.java.dtdu.item.base.Item;

public interface ItemCreator extends Registry {
	Item create(byte amount);
}