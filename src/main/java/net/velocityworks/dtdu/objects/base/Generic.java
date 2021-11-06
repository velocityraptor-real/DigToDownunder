package net.velocityworks.dtdu.objects.base;

import static java.lang.System.out;
import static net.velocityworks.dtdu.world.Registry.scanner;

import net.velocityworks.dtdu.world.Map;

public class Generic {
	public char icon;
	public String name;
	public Generic() {
		attributes();
	}
	public Generic(final String name, final char icon) {
		this.name = name;
		this.icon = icon;
	}
	protected void attributes() {
		this.icon = 'O';
		this.name = "Object";
	}
	public boolean interaction(final int x, final int y) {
		return true;
	}
	public void interactWith(final int x, final int y) {
		Generic o = Map.get(x, y);
		if(o.interaction(x, y)) {
			out.println(o.name);
			scanner.nextLine();
		}
	}
	public void moveTo(final int fromX, final int fromY, final int toX, final int toY) {
		if(Map.isInsideMap(toX, toY)) {
			if(Map.hasSpace(toX, toY)) {
				move(fromX, fromY, toX, toY);
			} else {
				interactWith(toX, toY);
			}
		}
	}
	public void move(final int fromX, final int fromY, final int toX, final int toY) {
		setLocation(Map.getIcon(fromX, fromY), toX, toY);
		Map.remove(fromX, fromY);
	}
	public void setLocation(final char icon, final int x, final int y) {
		if(Map.isInsideMap(x, y)) {
			Map.set(this, icon, x, y);
		}
	}
	public void setLocation(final int x, final int y) {
		setLocation(this.icon, x, y);
	}
}