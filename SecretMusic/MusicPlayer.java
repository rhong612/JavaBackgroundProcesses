import java.io.File;
import java.net.URI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * A music player that plays mp3 files from the MusicLibrary
 */
public class MusicPlayer
{
	private MediaPlayer player;
	private String fileLocation;
	
	/**
	 * Constructs a MusicPlayer instance associated with a given FXMLController
	 * @param controller
	 * @param timeSlider
	 */
	public MusicPlayer(String fileLocation) {
		player = null;
		this.fileLocation = fileLocation;
	}
	
	/**
	 * Plays the given song
	 * @param song the song to play
	 */
	public void play()
	{
		player = new MediaPlayer(new Media(convertPathToValidURIString(fileLocation)));
		player.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				play();
			}
		});
		player.play();
	}
	
	/**
	 * Converts a given file path to a valid URI string
	 * @param path the path to convert
	 * @return the path with all illegal URI characters replaced
	 */
	private String convertPathToValidURIString(String path) {
		File f = new File(path);
		URI uri = f.toURI();
		return uri.toString();
	}

}
