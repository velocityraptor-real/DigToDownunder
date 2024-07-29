package dtdu.engine;

import dtdu.util.AudioPlayer;

public class MusicPlayer extends AudioPlayer {
	public MusicPlayer(String path) {
		super(path);
	}
	@Override
	public void volume(float volume) {
		super.volume(volume * Sounds.MUSIC_VOLUME);
	}
}