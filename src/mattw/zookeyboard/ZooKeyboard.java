package mattw.zookeyboard;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZooKeyboard extends Application {

	private Config config = new Config("zookeyboard.json");
	private Random rand = new Random();
	private List<String> soundFiles = new ArrayList<>();
	private GlobalKeyboardHook hook = new GlobalKeyboardHook(true);
	private List<MediaPlayer> players = new ArrayList<>();

	{
		config.load();
		for(String source : config.getAudioSources()) {
			loadAvailableSounds(new File(source));
		}
		hook.addKeyListener(new GlobalKeyAdapter() {
			public void keyPressed(GlobalKeyEvent e) {
				if(e.getVirtualKeyCode() == KeyEvent.VK_F8) {
					Platform.exit();
					System.exit(0);
				}
				playSound();
			}
			public void keyReleased(GlobalKeyEvent e) {}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {}

	public void loadAvailableSounds(File path) {
		if(path.exists()) {
			for(File f : path.listFiles()) {
				if(f.isDirectory()) {
					loadAvailableSounds(f);
				} else if(f.isFile()) {
					String filePath = f.toURI().toASCIIString();
					if(filePath.endsWith(".wav") || filePath.endsWith(".mp3")) {
						soundFiles.add(filePath);
					}
				}
			}
		} else {
			System.err.println("Path does not exist");
		}
	}

	public void playSound() {
		String soundFile = soundFiles.get(rand.nextInt(soundFiles.size()));
		Media media = new Media(soundFile);
		System.out.println("Playing: "+media.getDuration()+" "+media.getSource());
		MediaPlayer player = new MediaPlayer(media);
		players.add(player);
		player.play();
		player.setOnEndOfMedia(() -> {
			System.out.println("End of media: "+media.getSource());
			players.remove(player);
		});
		try { Thread.sleep(100); } catch (Exception ignored) {}
	}
}

