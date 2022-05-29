package net.velocityworks.dtdu.objects.ai;

import static net.velocityworks.dtdu.util.Direction.*;
import static net.velocityworks.dtdu.world.Registry.player;

public interface TargetingPlayer extends Moving {
	default void targetPlayer() {
		if(player.getY() > getY()) move(DOWN);
		else if(player.getY() < getY()) move(UP);
		if(player.getX() > getX()) move(RIGHT);
		else if(player.getX() < getX()) move(LEFT);
	}
}