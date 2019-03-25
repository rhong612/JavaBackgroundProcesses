import javafx.embed.swing.JFXPanel;

public class Main
{
	public static void main(String[] args) {
		final JFXPanel fxPanel = new JFXPanel();
		MusicPlayer player = new MusicPlayer("Song.mp3");
		player.play();
	}
}
