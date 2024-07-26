package main.java.dtdu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import main.java.dtdu.engine.Sounds;
import main.java.dtdu.engine.Ticker;
import main.java.dtdu.util.*;
import main.java.dtdu.world.*;

import static main.java.dtdu.graphics.Screens.*;

public class Main {
	public static void printError(Exception e) {
		e.printStackTrace();
		Scanner c = new Scanner(System.in);
		c.nextLine();
		c.close();
	}
	public static void shutdown() {
		Map.unload();
		Save.save();
		try(DataWriter data = DataWriter.create("./data.dtdu")) {
			Rectangle r = frame.getBounds();
			data.write(r.x);
			data.write(r.y);
			data.write(r.width);
			data.write(r.height);
		} catch (IOException e) {printError(e);}
		System.exit(0);
	}
	public static void main(String[] args) {
		File data = new File("./data.dtdu"), saves = new File("./saves");
		if(!saves.exists()) saves.mkdirs();
		if(!data.exists()) {
			try {data.createNewFile();}
			catch (IOException e) {printError(e);}
			frame.setBounds(250, 50, 900, 700);
		} else try(DataReader r = DataReader.create(data)) {
			frame.setBounds(r.readInt(), r.readInt(), r.readInt(), r.readInt());
		} catch (IOException e) {printError(e);}
		frame.addWindowListener(new WindowListener() {
			@Override public void windowClosing(WindowEvent e) {shutdown();}
			@Override public void windowOpened(WindowEvent e) {}
			@Override public void windowClosed(WindowEvent e) {}
			@Override public void windowIconified(WindowEvent e) {}
			@Override public void windowDeiconified(WindowEvent e) {}
			@Override public void windowActivated(WindowEvent e) {}
			@Override public void windowDeactivated(WindowEvent e) {}
		});
		compileScreens();
		Save.scene.loadGraphics();
		frame.setVisible(true);
		render();
		Ticker.ticker.start();
		Sounds.mainTheme.play();
	}
}