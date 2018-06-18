package ro.ppoo.www;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilePath {

	private String pathName;
	private static int nr = 1;
	private List<String> fileList;
	public FilePath(String name) {
		super();
		this.pathName = name;
		fileList = new ArrayList<String>();
	}

	// list all files from a path
	public void listAllFiles(String pathName) throws IOException {
		File root = new File(pathName);
		File[] list = root.listFiles();
		if (list == null)
			return;
		for (File f : list) {
			if (f.isDirectory()) {
				listAllFiles(f.getAbsolutePath());
				System.out.println("   "+nr + ". Dir:" + f.getCanonicalPath());
				nr++;
			} else {
				if (validateExtension(getExtension(f.getName()))) {
					fileList.add(f.getCanonicalPath());
					System.out.println(nr + ". File:" + f.getCanonicalPath());
					nr++;
				}
			}
		}
	}

	public String getName() {
		return pathName;
	}

	private static String getExtension(String file) {
		String extension = "";
		int i = file.lastIndexOf('.');
		if (i > 0) {
			extension = file.substring(i + 1);
		}
		return extension;
	}

	private static boolean validateExtension(String extension) {
		if (extension != null
				&& (extension.equals("png") || extension.equals("mp3")
						|| extension.equals("wav") || extension.equals("jpg"))) {
			return true;
		}
		return false;
	}

	public void open() throws IOException {
		Desktop.getDesktop().open(new File(pathName));
	}

	public List<String> getFileList() {
		return fileList;
	}


	
}
