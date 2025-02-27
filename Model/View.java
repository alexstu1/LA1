package Model;

import java.util.Scanner;


public class View {
	private static MusicStore store = new MusicStore();
	public static void main(String[] args) {
		greetings();
		Scanner input = new Scanner(System.in);
		boolean continuing = true;
		while(continuing) {
			switch (input.nextLine().trim().toLowerCase()) {
				case "help":
					break;
				case "":
					instructions();
					break;
				case "search":
					search(input);		
					break;
				case "get":
					get(input);
					break;
				case "playlist":
					modifyPlaylist(input);
					break;
				case "end":
					input.close();
					continuing=false;
					break;
				default:
					unknownCommand();
			}
		}
	}

	private static void greetings() {
		System.out.println("Welcome to the Music App.");
		System.out.println("Enter 'Help' for instructions");
	}

	public static void instructions() {
		System.out.println("Enter 'search' and follow the instructions given to find a song, album, or playlist.");
		System.out.println("");
		System.out.println("Enter 'end' to terminate the program.");	
	}

	public static void unknownCommand() {
		System.out.println("Command not understood, enter 'Help' for instructions.");
	}

	public static void search(Scanner input) {
		System.out.println("Enter 'store' to search the store or 'library' to search your library.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "store":
				storeSearch(input);
				break;
			case "library":
				librarySearch(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				search(input);
		}
	}

	public static void storeSearch(Scanner input){
		System.out.println("Enter 'song' to search for a song, or 'album' to search for an album");
		switch (input.nextLine().trim().toLowerCase()) {
			case "song":
				songFromStore(input);
				break;
			case "album":
				albumFromStore(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				storeSearch(input);
			}
	}

	public static void librarySearch(Scanner input) {
		System.out.println("Enter 'song' to search for a song, 'album' to search for an album, or 'playlist' to search for a playlist.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "song":
				songFromLibrary(input);
				break;
			case "album":
				albumFromLibrary(input);
				break;
			case "playlist":
				playlistSearch(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				librarySearch(input);
			}
	}

	public static void songFromStore(Scanner input) {
		System.out.println("Enter 'title' to search by title, or 'artist' to serach by artist.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "title":
				songFromStoreByTitle(input);
				break;
			case "artist":
				songFromStoreByArtist(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				songFromStore(input);
		}
	}

	public static void albumFromStore(Scanner input) {
		System.out.println("Enter 'title' to search by title, or 'artist' to serach by artist.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "title":
				albumFromStoreByTitle(input);
				break;
			case "artist":
				albumFromStoreByArtist(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				albumFromStore(input);
		}
	}

	public static void songFromLibrary(Scanner input) {
		System.out.println("Enter 'title' to search by title, or 'artist' to serach by artist.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "title":
				songFromLibraryByTitle(input);
				break;
			case "artist":
				songFromLibraryByArtist(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				songFromLibrary(input);
		}	
	}

	public static void albumFromLibrary(Scanner input) {
		System.out.println("Enter 'title' to search by title, or 'artist' to serach by artist.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "title":
				albumFromLibraryByTitle(input);
				break;
			case "artist":
				albumFromLibraryByArtist(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				albumFromLibrary(input);
		}
		
	}

	public static void get(Scanner input) {
		System.out.println("Enter 'songs','artists',albums','playlists', or 'favorites' to get all of them from the library.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "songs":
				getSongs();
				break;
			case "artists":
				getArtists();
				break;
			case "albums":
				getAlbums();
				break;
			case "playlists":
				getPlaylists();
				break;
			case "favorites":
				getFavorites();
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				get(input);
		}
	}

	public static void modifySong(Scanner input, String songName) {
		System.out.println("Enter 'rate' to rate a song, or 'favorite' to favorite the song.\n Alternatively enter 'exit' to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "rate":
				rateSong(input, songName);
				break;
			case "favorite":
				favoriteSong(songName);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				modifySong(input, songName);
		}
	}

	public static void modifyPlaylist(Scanner input) {
		System.out.println("Enter 'create' to create a playlist, 'exit' to cancel, or the playlist's name you want to modify.");
		String entry = input.nextLine().trim().toLowerCase();
		switch (entry) {
			case "create":
				createPlaylist(input);
				break;
			case "exit":
				return;
			default:
				//May need to add check here to verify playlist exists.
				modifySpecificPlaylist(input,entry);
		}
	}

	public static void modifySpecificPlaylist(Scanner input, String entry) {
		System.out.println("Enter 'add' or 'remove' to add or remove a song.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "add":
				addSongToPlaylist(input,entry);
				break;
			case "remove":
				removeSongFromPlaylist(input,entry);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				modifySpecificPlaylist(input,entry);
		}
	}
	
	/*Below here is all search functions to be implemented
	 * 
	 */
	public static void songFromStoreByTitle(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByTitleStore(search));
	}

	public static void songFromStoreByArtist(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByArtistStore(search));
	}

	public static void albumFromStoreByTitle(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByTitleStore(search));
	}

	public static void albumFromStoreByArtist(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByArtistStore(search));
	}

	public static void songFromLibraryByTitle(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByTitleUser(search));
	}

	public static void songFromLibraryByArtist(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByArtistUser(search));
	}

	public static void albumFromLibraryByTitle(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByTitleUser(search));
	}

	public static void albumFromLibraryByArtist(Scanner input) {
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByArtistUser(search));
	}

	public static void playlistSearch(Scanner input) {
		System.out.println("Enter playlist name:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.searchPlaylist(search));
	}

	public static void getSongs() {
		System.out.println(store.getSongTitles());
	}

	public static void getArtists() {
		System.out.println(store.getArtists());
	}

	public static void getAlbums() {
		System.out.println(store.getAlbums());
	}

	public static void getPlaylists() {
		System.out.println(store.getPlaylists());
	}

	public static void getFavorites() {
		System.out.println(store.getFavoriteSongs());
	}

	public static void rateSong(Scanner input, String songName) {
		System.out.println("Enter your rating for the song from 1 to 5");
		int rating = Integer.parseInt(input.next());
		System.out.println(store.rateSong(songName, rating));
	}

	public static void favoriteSong(String songName) {
		System.out.println(store.favoriteSong(songName));
	}

	public static void createPlaylist(Scanner input) {
		System.out.println("Enter playlist name  you want to create");
		String name = input.nextLine();
		System.out.println(store.makePlaylist(name));
	}

	public static void addSongToPlaylist(Scanner input, String playlistName) {
		System.out.println("Enter song to add.");
		String songName = input.nextLine().trim().toLowerCase();
		System.out.println(store.addToPlaylist(playlistName, songName));
	}

	public static void removeSongFromPlaylist(Scanner input, String playlistName) {
		System.out.println("Enter song to remove.");
		String songName = input.nextLine().toLowerCase();
		System.out.println(store.removeFromPlaylist(playlistName, songName));
	}
}