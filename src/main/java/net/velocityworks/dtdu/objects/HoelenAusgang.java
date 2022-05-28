package net.velocityworks.dtdu.objects;

import static net.velocityworks.dtdu.Main.*;
import static net.velocityworks.dtdu.world.Registry.player;
import static net.velocityworks.dtdu.world.Scene.*;

import net.velocityworks.dtdu.objects.base.Generic;
import net.velocityworks.dtdu.objects.base.TrackedObject;
import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.util.Logger;

public final class HoelenAusgang extends TrackedObject {
	public HoelenAusgang() {
		super(new StaticObject("Höhlenausgang", 'x'));
	}
	@Override
	public void interaction(Generic o) {
		Logger.say(player.getName(), "Hmm in welche Richtung muss ich mich jetzt nochmal graben?");
		SCENE_TRANSITION = switch(PREVIOUS_SCENE) {
		case GARTEN -> MEXICO;
		default -> PREVIOUS_SCENE;
		};
	}
}