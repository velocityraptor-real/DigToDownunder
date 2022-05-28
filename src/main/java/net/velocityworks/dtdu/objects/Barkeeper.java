package net.velocityworks.dtdu.objects;

import static net.velocityworks.dtdu.world.Registry.*;
import static net.velocityworks.dtdu.Main.*;

import net.velocityworks.dtdu.objects.base.*;
import net.velocityworks.dtdu.objects.statico.StaticObject;
import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.*;

public class Barkeeper extends SilentQuest {
	public Barkeeper() {
		super(new StaticObject("Barkeeper", 'b'));
	}
	@Override
	protected void idleInteraction() {
		if(!Logger.decision("Willst du zuerst noch etwas anderes machen, bevor du dein votka trinkst?", false)) SCENE_TRANSITION = Scene.SCHLAFZIMMER;
	}
	@Override
	public boolean questCondition() {
		String cName = player.getName();
		Logger.say(getName(), "Hallo Mensch!");
		Logger.say(getName(), "Du siehst m�de aus...");
		Logger.say(getName(), "gibt in deiner alten Mine wohl nicht mehr viel zu holen...");
		Logger.say(cName, "Ja leider...");
		Logger.say(cName, "Ich hab mir schon mal �berlegt wegzuziehen");
		Logger.say(cName, "irgendwohin wo es mehr Gold gibt.");
		Logger.say(getName(), "Man sagt dass es in Australien Berge voll Gold gibt...");
		Logger.say(getName(), "Sag blos du willst nach Australien...");
		Logger.say(cName, ".");
		Logger.say(getName(), "Und wie willst du das anstellen?");
		Logger.say(getName(), "Du bist so gut wie Pleite.");
		Logger.say(cName, "Naja, ich habe nicht viel...");
		Logger.say(cName, "aber ich habe noch meine Spitzhacke...");
		Logger.say(cName, "also...");
		Logger.say(getName(), "Haha willst du dich etwa nach Australien graben?");
		Logger.say(cName, "Weißt du was...");
		Logger.say(cName, "genau das werde ich tun!");
		Logger.say(getName(), "HAhaHAHAHhahA");
		Logger.say(getName(), "Schick mir eine Karte wenn du angekommen bist HAhaHa");
		Logger.say(getName(), "..haha..ha...");
		Logger.say(getName(), "Hier.");
		Logger.say(getName(), "Trink erst mal dein Vodka.");
		return Inventory.pickUp(stamperl);
	}
	@Override
	protected void questReward() {idleInteraction();}
}