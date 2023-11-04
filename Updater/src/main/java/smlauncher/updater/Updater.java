package smlauncher.updater;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Updater for updating the launcher itself, not the game. Exported in a separate jar file within the launcher jar file.
 *
 * @author TheDerpGamer
 */
public class Updater {

	public static void main(String[] args) {
		try {
			if(args.length == 0) throw new IllegalArgumentException("No URL specified");
		} catch(IllegalArgumentException exception) {
			exception.printStackTrace();
			System.exit(-1);
		}
		String url = args[0];
		String output = args[1];
		System.out.println("Downloading update from " + url);

		try {
			File outputFile = new File(output);
			if(outputFile.exists()) outputFile.delete();
			outputFile.createNewFile();
			//Download the file at the URL and write it to the output file
			IOUtils.copy(new URL(url).openStream(), new FileOutputStream(outputFile));
			System.out.println("Downloaded update to " + outputFile.getAbsolutePath());
			System.out.println("Updating launcher...");
			//Restart the launcher
			runLauncher(outputFile.getAbsolutePath());
		} catch(Exception exception) {
			exception.printStackTrace();
			System.exit(-1);
		}
	}

	private static void runLauncher(String absolutePath) {
		String os = System.getProperty("os.name").toLowerCase();
		if(os.contains("win")) {
			try {
				Runtime.getRuntime().exec("cmd /c start \"\" \"" + absolutePath + "\"");
				System.exit(0);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		} else if(os.contains("mac")) {
			try {
				Runtime.getRuntime().exec("open \"" + absolutePath + "\"");
				System.exit(0);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		} else {
			try {
				Runtime.getRuntime().exec("java -jar \"" + absolutePath + "\"");
				System.exit(0);
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
