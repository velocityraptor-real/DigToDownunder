package net.velocityworks.dtdu.objects.base;

import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.Map;

public class Quest extends TrackedObject {
	public boolean removable, quest;
	public Quest (final StaticObject o) {
		super(o);
	}
	public Quest(final StaticObject o, final int x, final int y) {
		super(o, x, y);
	}
	public Quest(final StaticObject o, final int x, final int y, boolean quest) {
		super(o, x, y);
		this.quest = quest;
	}
	public Quest(final StaticObject o, final boolean removable, final int x, final int y) {
		super(o, x, y);
		this.removable = removable;
	}
	@Override
	public void interaction(final Generic o) {
		if(quest) idleInteraction();
		else if(questCondition()){
			quest = true;
			questReward();
			if(removable) Map.remove(x, y);
		}
	}
	protected void idleInteraction() {super.interaction(null);}
	protected boolean questCondition() {return true;}
	protected void questReward() {
		Logger.say(getName(), "Completed quest");
		scanner.nextLine();
	}
}