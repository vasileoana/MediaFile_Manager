package ro.ppoo.www;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileManager {

	private File file;

	public FileManager(File file) {
		super();
		this.file = file;
	}

	public void description() {
		System.out.println("File: " +file.getName() + " found at path: " + file.getAbsolutePath() + "\n");
		System.out.println("Last modified: " + file.lastModified() + "\n"
				+ "Size: " + file.length() + "Bytes\n" + "Is File: " + file.isFile()
				+ "\n" + "Is Directory: " + file.isDirectory() + "\n"
				+ "Is hidden: "+ file.isHidden());
	}

	public void rename(String name) {
		file.renameTo(new File(name));
		System.out.println("File was renamed!");
	}

	public void deleteFromDisk() {
		file.delete();
	}

	public void open() throws IOException {
		Desktop.getDesktop().open(file);
	}
}
