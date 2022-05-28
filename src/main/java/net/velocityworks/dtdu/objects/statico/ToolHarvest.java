package net.velocityworks.dtdu.objects.statico;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Inventory.equipSlot;
import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.items.base.Item;

public class ToolHarvest extends Harvest {
	private final Item harvestTool;
	public ToolHarvest(Item harvestTool, String name, char icon, Item container) {
		this(harvestTool, name, icon, container, 1);
	}
	public ToolHarvest(Item harvestTool, String name, char icon, Item container, int amount) {
		super(name, icon, container, amount);
		this.harvestTool = harvestTool;
	}
	@Override
	public boolean harvest() {
		if(equipSlot == harvestTool) return super.harvest();
		out.println(getName() + " requires a " + harvestTool.name + " to be harvested");
		scanner.nextLine();
		return false;
	}
}