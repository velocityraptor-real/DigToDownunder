package net.velocityworks.dtdu.objects;

import static net.velocityworks.dtdu.world.Registry.*;
import net.velocityworks.dtdu.util.Logger;
import net.velocityworks.dtdu.world.World;
import net.velocityworks.dtdu.objects.base.Quest;
public class Barkeeper extends Quest {
	public Barkeeper() {
		super();
	}
	@Override
	protected void attributes() {
		this.icon = 'b';
		this.name = "Barkeeper";
	}
//	@Override
//	protected void questReward() {
//	}
	@Override
	public boolean interaction(final int x, final int y) {
		if(!quest) {
			String cName = player.name;
			Logger.say(name, "Hallo Mensch!");
			Logger.say(name, "Du siehst müde aus...");
			Logger.say(name, "gibt in deiner alten Mine wohl nicht mehr viel zu holen...");
			Logger.say(cName, "Ja leider...");
			Logger.say(cName, "Ich hab mir schon mal überlegt wegzuziehen");
			Logger.say(cName, "irgendwohin wo es mehr Gold gibt.");
			Logger.say(name, "Man sagt dass es in Australien Berge voll Gold gibt...");
			Logger.say(name, "Sag blos du willst nach Australien...");
			Logger.say(cName, ".");
			Logger.say(name, "Und wie willst du das anstellen?");
			Logger.say(name, "Du bist so gut wie Pleite.");
			Logger.say(cName, "Naja, ich habe nicht viel...");
			Logger.say(cName, "aber ich habe noch meine Spitzhacke...");
			Logger.say(cName, "also...");
			Logger.say(name, "Haha willst du dich etwa nach Australien graben?");
			Logger.say(cName, "Weißt du was...");
			Logger.say(cName, "genau das werde ich tun!");
			Logger.say(name, "HAhaHAHAHhahA");
			Logger.say(name, "Schick mir eine Karte wenn du angekommen bist HAhaHa");
			Logger.say(name, "..haha..ha...");
			Logger.say(name, "Hier.");
			Logger.say(name, "Trink erst mal dein Vodka.");
			quest = true;
		}
		if(!Logger.decision("Willst du zuerst noch etwas anderes machen, bevor du dein votka trinkst?", false)) {
			World.sceneTransition++;
		}
		return false;
	}
}