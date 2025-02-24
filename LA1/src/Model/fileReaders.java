package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class fileReaders {

	public static void main(String[] args) {
		//ArrayList<Album> store = makeStore();
		Song toTest = new Song("Carol King - I Feel The Earth Move | Appears on: Tapestry");
		saveRating(toTest,6);
		//System.out.println(store);
		System.out.println(toTest);
		
	}
	
	
	public static void argsParser() {
		
	}
	public static ArrayList<Album> makeStore() {
		ArrayList<Album> store = new ArrayList<Album>();
		ArrayList<String> albumFileNames = loadAlbums();
		for (String fileName : albumFileNames) {
			Album album = new Album(fileName);
			store.add(album);
		}
		return store;
	}

	static ArrayList<String> loadAlbums() {
		ArrayList<String> albums = new ArrayList<String>();
		File file = new File("./albums/albums.txt");
		Scanner reader;
		try {
			reader = new Scanner(file);
			while (reader.hasNext()) {
				albums.add("./albums/"+reader.nextLine().replace(',', '_')+".txt");
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading albums.txt");
		}
		return albums;
	}
	
	/* Old method to be deleted
	static ArrayList<String> loadFromFile(String fileName) {
		// in the arrayList the following values are stored in the following order:
		// 0:Album Name
		// 1:Artist
		// 2:Genre
		// 3:ReleaseYear
		// 4+:Song titles in original order 
		 
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
	
	}*/
	/* Old method to be deleted
	static ArrayList<String> csvParser(String toParse) {
		ArrayList<String> album = new ArrayList<String>();
		String[] split = toParse.split(",");
		for (String s : split) {
			album.add(s);
		}
		return album;
	}*/
	
	
	static ArrayList<Song> getFavorites(){
		File file = new File("./albums/favorites.txt");
		ArrayList<Song> favorites = new ArrayList<Song>();
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				favorites.add(new Song(reader.nextLine()));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading file:");
			System.out.println("./albums/favorites.txt");
		}
		return favorites;
	}
	
	static void favorite(Song toFavorite) {
		ArrayList<Song> toSave =  getFavorites();
		try {
			FileWriter fileWriter = new FileWriter("./albums/favorites.txt");
			for (Song song : toSave) {
				fileWriter.append(song.toString()+"\n");
			}
			fileWriter.append(toFavorite.toString()+"\n");
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Something went wrong while writing file:");
			System.out.println("./albums/favorites.txt");
		}
	}
	static HashMap<Song,Integer> getRatings(){
		File file = new File("./albums/ratings.txt");
		HashMap<Song,Integer> ratings = new HashMap<Song,Integer>();
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				Song toRate = new Song(reader.nextLine());
				Integer rating = Integer.parseInt(reader.nextLine());
				ratings.put(toRate, rating);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading file:");
			System.out.println("./albums/ratings.txt");
		}
		return ratings;
	}
	static int getRating(Song song) {
		File file = new File("./albums/ratings.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				Song toRate = new Song(reader.nextLine());
				if (song.equals(toRate)){
					reader.close();
					return Integer.parseInt(reader.nextLine());
				}

			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading file:");
			System.out.println("./albums/ratings.txt");
		}
		return -1;
	}
	static void saveRating(Song song, int rating) {
		if (rating<1) rating =1;
		if (rating>5) rating=5;
		File file = new File("./albums/ratings.txt");
		Scanner reader;
		ArrayList<String> unedited = new ArrayList<String>();
		try {
			reader = new Scanner(file);
			while (reader.hasNext()) {
				unedited.add(reader.nextLine());
			}
			reader.close();
			
			FileWriter fileWriter = new FileWriter("./albums/ratings.txt");
			while(unedited.size()>0) {
				fileWriter.append(unedited.removeFirst()+"\n");
			}
			fileWriter.append(song.toString()+"\n"+rating+"\n");
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Something went wrong while writing file:");
			System.out.println("./albums/ratingws.txt");
		}
		//can call favorite here if we want iff rating=5
	}
		
		
		
		
	
	static ArrayList<Song> loadPlaylist(String playListName) {
		ArrayList<Song> playList = new ArrayList<Song>();
		try {
			File file = new File("./playlists/"+playListName+".txt"); 
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				Song nextSong = new Song(reader.nextLine());
				playList.add(nextSong);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading file:");
			System.out.println("./playlists/"+playListName+".txt");
		}
		return playList;
	}
	static void addToPlaylist(String playListName, Song songToAdd) {
		ArrayList<Song> thePlaylist = loadPlaylist(playListName);
		try {
			FileWriter fileWriter = new FileWriter("./playlists/"+playListName+".txt"); 
			for (Song song : thePlaylist) {
				fileWriter.append(song.toString());
			}
			fileWriter.append(songToAdd.toString());			
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Something went wrong while writing file:");
			System.out.println("./playlists/"+playListName+".txt");
		}
	}
	static void removeFromPlaylist(String playListName, Song songToRemove) {
		ArrayList<Song> thePlaylist = loadPlaylist(playListName);
		try {
			FileWriter fileWriter = new FileWriter("./playlists/"+playListName+".txt"); 
			for (Song song : thePlaylist) {
				if (song.equals(songToRemove)) {
					//skips the adding of the song into the play list file
					continue;
				}
				fileWriter.append(song.toString());
			}			
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Something went wrong while writing file:");
			System.out.println("./playlists/"+playListName+".txt");
		}
	}

	static ArrayList<String> getPlaylistNames(){
		File file = new File("./albums/playlistNames.txt");
		ArrayList<String> playlists = new ArrayList<String>();
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				playlists.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong while reading file:");
			System.out.println("./albums/playlistNames.txt");
		}
		return playlists;
	}
	static void createPlaylist(String newPlaylist) {
		ArrayList<String> toSave =  getPlaylistNames();
		try {
			FileWriter fileWriter = new FileWriter("./albums/playlistNames.txt");
			for (String s : toSave) {
				if (s.equals(newPlaylist)) {
					fileWriter.close();
					throw new Exception("e");
				}
				fileWriter.append(s+"\n");
			}
			fileWriter.append(newPlaylist+"\n");
			fileWriter.close();
			FileWriter makeFile = new FileWriter ("./playlists/"+newPlaylist+".txt");
			makeFile.close();
		} catch (IOException e) {
			System.out.println("Something went wrong while writing file:");
			System.out.println("./albums/playlistNames.txt");
		} catch (Exception e) {
			System.out.println("A playlist already exists with that name!\nPlease try again with a new name.");
		}
	
	}
}

