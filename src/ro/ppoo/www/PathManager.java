package ro.ppoo.www;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PathManager {

	private static PathManager instance = null;

	private final static String fileName = "pathManager.txt";
	private static File file;
	private static List<FilePath> paths;

	private PathManager() {

	}

	public static PathManager getInstance() throws IOException {
		if (instance == null) {
			instance = new PathManager();
			paths = new ArrayList<>();
			createFile();
		}
		return instance;
	}

	public static void createFile() throws IOException {
		file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	public static void loadPaths() throws IOException {
		// incarcam ce exista in fisier
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(
				fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line;
		FilePath folder;
		while ((line = bufferedReader.readLine()) != null) {
			folder = new FilePath(line.trim());
			paths.add(folder);
		}
	}

	public static void writePaths() throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		if (paths.size() > 0)
			for (FilePath path : paths) {
				bufferedWriter.write(path.getName()+"\n");
			}
		bufferedWriter.close();
		fileWriter.close();
	}

	public static void addPath(String path) throws IOException {
		FileWriter fileWriter = new FileWriter(file, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		if (!listContains(path)) {
			paths.add(new FilePath(path));
			bufferedWriter.write(path+"\n");
			bufferedWriter.close();
			fileWriter.close();
		}
	}

	public static void showPaths() {
		int i = 1;
		if (paths.size() > 0)
			for (FilePath f : paths) {
				System.out.println(i + ". " + f.getName());
				i++;
			}
	}

	public static boolean listContains(String path) {
		for (FilePath f : paths) {
			if (f.getName().equalsIgnoreCase(path.trim())) {
				return true;
			}
		}
		return false;
	}

	public static List<FilePath> getPaths() {
		return paths;
	}

	public static void removePath(int index) throws IOException {
		paths.remove(index);
		writePaths();
	}
	
	public static void removeAll() throws IOException{
		paths.clear();
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("");
	}

}
