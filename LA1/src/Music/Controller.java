package Music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	public static void main(String[] args) {
		ArrayList<String> albums = loadAlbums();
		System.out.println("test");
		for (String s : albums) {
			ArrayList<String> album = loadFromFile("./albums/"+s);
			//Construct store from album here
		}
		saveFavorite("test2");
		/*TODO: Call constructor method and create library from created albumList

		 in each arrayList the following values are stored in the following order:
		 0:Album Name
		 1:Artist
		 2:Genre
		 3:ReleaseYear
		 4+:Song titles in original order 
		 */
	}
	static ArrayList<String> loadAlbums() {
		ArrayList<String> albums = new ArrayList<String>();
		File file = new File("./albums/albums.txt");
		Scanner reader;
		try {
			reader = new Scanner(file);
			while (reader.hasNext()) {
				albums.add(reader.nextLine().replace(',', '_')+".txt");
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading albums.txt");
		}
		return albums;
	}
	
	static ArrayList<String> loadFromFile(String fileName) {
		/* in the arrayList the following values are stored in the following order:
		 0:Album Name
		 1:Artist
		 2:Genre
		 3:ReleaseYear
		 4+:Song titles in original order 
		 */
		File file = new File(fileName);
		ArrayList<String> album = new ArrayList<String>();
		try {
			Scanner reader = new Scanner(file);
			album.addAll(csvParser(reader.nextLine()));
			while (reader.hasNext()) {
				album.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading file:");
			System.out.println(fileName);
		}
		return album;
	
	}

	static ArrayList<String> csvParser(String toParse) {
		ArrayList<String> album = new ArrayList<String>();
		String[] split = toParse.split(",");
		for (String s : split) {
			album.add(s);
		}
		return album;
	}
	
	
	static ArrayList<String> getFavorites(){
		File file = new File("./albums/favorites.txt");
		ArrayList<String> favorites = new ArrayList<String>();
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				favorites.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading file:");
			System.out.println("./albums/favorites.txt");
		}
		return favorites;
	}
	
	static void saveFavorite(String songName) {
		ArrayList<String> toSave =  getFavorites();
		try {
			FileWriter fileWriter = new FileWriter("./albums/favorites.txt");
			for (String s : toSave) {
				fileWriter.append(s+"\n");
			}
			System.out.println("test3");
			fileWriter.append(songName+"\n");
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Something went wrong while writing file:");
			System.out.println("./albums/favorites.txt");
		}
		
	}
	
}

