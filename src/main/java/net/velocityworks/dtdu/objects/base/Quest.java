package net.velocityworks.dtdu.objects.base;

import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.Map;

public class Quest extends Generic {
	public boolean quest = false;
	public Quest() {
		super();
	}
	public Quest(final String name, final char icon) {
		super(name, icon);
	}
	@Override
	protected void attributes() {
		this.icon = 'q';
		this.name = "ObjectWithQuest";
	}
	@Override
	public boolean interaction(final int x, final int y) {
		if(quest) {
			Logger.say(Map.get(x, y).name, "You already completed this Quest");
		} else {
			completeQuest();
		}
		return false;
	}
	protected void completeQuest() {
		quest = true;
		questReward();
	}
	protected void questReward() {
		rewardMessage();
	}
	protected void rewardMessage() {
		Logger.say(name, "Completed quest");
	}
}