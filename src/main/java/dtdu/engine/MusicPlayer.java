package main.java.dtdu.engine;

import main.java.dtdu.util.AudioPlayer;

public class MusicPlayer extends AudioPlayer {
	public MusicPlayer(String path) {
		super(path);
	}
	@Override
	public void volume(float volume) {
		super.volume(volume * Sounds.MUSIC_VOLUME);
	}
}