package main.java.dtdu.util;

import java.io.*;

import javax.sound.sampled.*;
import javax.sound.sampled.FloatControl.Type;

import main.java.dtdu.Main;

public class AudioPlayer {
	File file;
	Clip clip;
	AudioInputStream inputStream;
	
	boolean playing = false;
	long frame = 0L;
	public AudioPlayer(String path) {
		try {
			inputStream = AudioSystem.getAudioInputStream(file = new File("./src/main/resources/assets/sounds/" + path).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {Main.printError(e);}
	}
	public float getVolume() {
		return (float) Math.pow(10D, ((FloatControl) clip.getControl(Type.MASTER_GAIN)).getValue() / 20F);
	}
	public void setVolume(float volume) {
		if(volume < 0f || volume > 1f) throw new IllegalArgumentException("Volume not valid: " + volume);
		((FloatControl) clip.getControl(Type.MASTER_GAIN)).setValue(20F * (float) Math.log10(volume));
	}
	public boolean isPlaying() {
		return playing;
	}
	public void play() {
		if(playing || frame != 0L) restart();
		else {
			clip.start();
			playing = true;
		}
	}
	public void pause() {
		if(playing) {
			frame = clip.getMicrosecondPosition();
			clip.stop();
			playing = false;
		}
	}
	public void resume() {
		if(!playing) {
			clip.close();
			resetStream();
			clip.setMicrosecondPosition(frame);
			play();
		}
	}
	public void restart() {
		if(playing) clip.stop();
		playing = false;
		clip.close();
		resetStream();
		clip.setMicrosecondPosition(frame = 0L);
		play();
	}
	public void stop() {
		frame = 0L;
		if(playing) clip.stop();
		playing = false;
		clip.close();
	}
	public void jump(long frame) {
		if(frame > 0 && frame < clip.getMicrosecondLength()) {
			if(playing) clip.stop();
			playing = false;
			clip.close();
			resetStream();
			clip.setMicrosecondPosition(this.frame = frame);
			play();
		}
	}
	public void resetStream() {
		try {
			inputStream = AudioSystem.getAudioInputStream(file);
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {Main.printError(e);}
	}
}