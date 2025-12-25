package dtdu.engine;

import java.util.*;

import dtdu.util.Mathe;

public class Control {
	public static final ArrayList<Control> CONTROLS = new ArrayList<>();
	public static final ArrayList<String> control_registry = new ArrayList<>();
	public static final List<Integer> pressed_buttons = Collections.synchronizedList(new ArrayList<>());
	public static void register(String name, Control c) {
		CONTROLS.add(Mathe.binaryInsert(control_registry, name), c);
	}
	public static Control get(String name) {
		return CONTROLS.get(Mathe.binarySearch(control_registry, name));
	}
	public static void onPress(int buttonCode) {
		pressed_buttons.add(buttonCode);
		for(Control c : CONTROLS) if(!c.mode && c.isPressed()) c.action.run();
	} 
	public static void onRelease(int buttonCode) {
		for(Control c : CONTROLS) if(c.mode && c.isPressed()) c.action.run();
		int i = pressed_buttons.indexOf(buttonCode);
		if(i != -1) pressed_buttons.remove(i);
	}
	public final Runnable action;
	public int[][] buttons;
	public boolean sensitive, mode;
	public Control(boolean sensitive, boolean mode, Runnable action) {
		this.sensitive = sensitive;
		this.mode = mode;
		this.action = action;
	}
	public boolean isPressed() {
		for(int[] b : buttons) if(isPressed(b)) return true;
		return false;
	}
	protected boolean isPressed(int[] buttons) {
		synchronized(pressed_buttons) {
			if(sensitive) {
				if(buttons.length != pressed_buttons.size()) return false;
			} else if(buttons.length > pressed_buttons.size()) return false;
			for(int b : buttons) if(!pressed_buttons.contains(b)) return false;
		} return true;
	}
}