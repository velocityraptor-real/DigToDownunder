package net.velocityworks.dtdu.objects.statico.type;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Inventory.toolSlot;
import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.items.ItemContainer;
import net.velocityworks.dtdu.items.base.*;

public class ToolHarvest extends HarvestType {
	private final Tool harvestTool;
	public ToolHarvest(Tool harvestTool, String name, char icon, Item ... item) {
		super(name, icon, item);
		this.harvestTool = harvestTool;
	}
	public ToolHarvest(Tool harvestTool, String name, char icon, ItemContainer ... container) {
		super(name, icon, container);
		this.harvestTool = harvestTool;
	}
	@Override
	public boolean harvest() {
		if(toolSlot == harvestTool) {
			toolSlot.use();
			return super.harvest();
		}
		out.println(getName() + " requires a " + harvestTool.name + " to be harvested");
		scanner.nextLine();
		return false;
	}
}