package ro.ppoo.www;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	static PathManager pathManager;

	public static void main(String[] args) throws IOException {
		pathManager = PathManager.getInstance();
		pathManager.loadPaths();
		start();
	}

	private static void managePath(int index) throws IOException {
		if (pathManager.getPaths().size() > 0) {
			FilePath p = pathManager.getPaths().get(index);
			System.out.println("1. Show containig files");
			System.out.println("2. Remove directory from favorites");
			System.out.println("3. Open directory");
			System.out.println("4. Go back");
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				p.listAllFiles(p.getName());
				manageFiles(p);
				break;
			case 2:
				pathManager.removePath(index);
				managePath(index);
				break;
			case 3:
				p.open();
				managePath(index);
				break;
			case 4:
				start();
				break;
			default:
				break;
			}
		}
		start();
	}

	private static void manageFiles(FilePath path) throws IOException {
		System.out.println("Choose file: ");
		Scanner scanner = new Scanner(System.in);
		int index = scanner.nextInt();
		FileManager fm = new FileManager(new File(path.getFileList().get(
				index - 1)));
		System.out.println("1. Open");
		System.out.println("2. Rename");
		System.out.println("3. Delete from disk");
		System.out.println("4. Details");
		scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		switch (option) {
		case 1:
			fm.open();
			break;
		case 2:
			scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			fm.rename(name);
			break;
		case 3:
			fm.deleteFromDisk();
			break;
		case 4:
			fm.description();
			break;
		default:
			break;
		}
	}

	public static void start() throws IOException {
		System.out.println("Choose action:");
		System.out.println("1. Show current paths");
		System.out.println("2. Add new path");
		System.out.println("3. Remove path");

		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		switch (option) {
		case 1:
			pathManager.showPaths();
			if (pathManager.getPaths().size() > 0) {
				System.out.println("Choose path number for further details: ");
			} else {
				System.out.println("Currently there are no added paths.");
				start();
			}
			scanner = new Scanner(System.in);
			int index = scanner.nextInt();
			managePath(index - 1);
			break;
		case 2:
			System.out.println("Write path: ");
			scanner = new Scanner(System.in);
			String path = scanner.nextLine();
			pathManager.addPath(path);
			System.out.println("Path added");
			start();
			scanner = new Scanner(System.in);
			break;
		case 3:
			System.out.println("0. Remove all files");
			pathManager.showPaths();
			System.out.println("Pick one number");
			scanner = new Scanner(System.in);
			int nr = scanner.nextInt();
			if(nr==0){
				pathManager.removeAll();
			}
			else {
				pathManager.removePath(nr-1);
			}
			break;
		default:
			System.out.println("Choose a valid option!");
			start();
			break;
		}

	}

}
