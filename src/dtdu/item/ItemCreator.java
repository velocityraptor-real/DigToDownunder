package dtdu.item;

import dtdu.engine.Registry;
import dtdu.item.base.Item;

public interface ItemCreator extends Registry {
	Item create(byte amount);
}