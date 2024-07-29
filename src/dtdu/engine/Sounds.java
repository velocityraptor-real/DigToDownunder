package dtdu.engine;

public class Sounds {
	public static volatile float GAME_VOLUME = 1F;
	public static volatile float MUSIC_VOLUME = 1F;
	public static final MusicPlayer mainTheme = new MusicPlayer("music/main_theme.wav");
	public static void updateVolume() {
		mainTheme.updateVolume();
	}
}