package Model;

import java.util.Scanner;
/*This class stores and runs all necessary code to load the music store,
 *  then allow the user to interact with it through the command line with
 *  on screen instructions.
 */

public class View {
	private static MusicStore store = new MusicStore();
	public static void main(String[] args) {
		/* This method prompts and reads user input, then calls the necessary functions.
		 * It does not check or use any command line arguments when running the file.
		 */
		
		Scanner input = new Scanner(System.in);
		boolean continuing = true;
		while(continuing) {
			greetings();
			switch (input.nextLine().trim().toLowerCase()) {
				// Entering either help or nothing will bring up instructions.
				case "help":
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
				case "library":
					modifyLibrary(input);
					break;
				case "rate":
					rateSongs(input);
					break;
				case "end":
					input.close();
					continuing=false;
					break;
				default:
					System.out.println("Command not understood, enter 'help' for instructions.");
			}
		}
	}

	private static void greetings() {
		/* This method greets the user at the main menu.
		 * No arguments or returns.
		 */
		System.out.println("Welcome your last Music App.");
		System.out.println("Your one stop shop for finding new and old favorites.");
		System.out.println("Enter 'help' for instructions");
	}

	private static void instructions() {
		/* This method provides instructions for how to start a command sequence,
		 * 	further instructions are provided once a command is started. 
		 * No arguments or returns.
		 */
		System.out.println("Enter 'search' and follow the instructions given to find a song, album, or playlist.");
		System.out.println("Enter 'get' and follow the instructions to get all of a certain category.");
		System.out.println("Enter 'playlist' and follow the instructions to create or modify a playlist");
		System.out.println("Enter 'library' to add a song or playlist to the saved user library.");
		System.out.println("Enter 'rate' to rate or faviorite a song.");
		System.out.println("Enter 'exit' at any point to return the main menu.");
		System.out.println("Enter 'end' to terminate the program.");	
	}

	private static void search(Scanner input) {
		/* This method is used to search the music store or user library for songs, albums, or play lists. 
		 * No arguments or returns.
		 */
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

	private static void storeSearch(Scanner input){
		/* This method is used to search the music store for songs, albums, or play lists.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null.
		 */
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

	private static void librarySearch(Scanner input) {
		/* This method is used to search the user library for songs, albums, or play lists.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null.
		 */
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

	private static void songFromStore(Scanner input) {
		/* This method is used to search the music store for a song.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
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

	private static void albumFromStore(Scanner input) {
		/* This method is used to search the music store for an album.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
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

	private static void songFromLibrary(Scanner input) {
		/* This method is used to search the user library for a song.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
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

	private static void albumFromLibrary(Scanner input) {
		/* This method is used to search the user library for an album.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
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

	private static void get(Scanner input) {
		/* This method is used to get all of the following from the music store: 
		 * 		songs,artists,albums,play lists, or favorites.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
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
	private static void modifyLibrary(Scanner input) {
		/* This method is used to add a song or album to the user library.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter 'song' or 'album' to add a song or album to your library.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "song":
				addSongToLibrary(input);
				break;
			case "album":
				addAlbumToLibrary(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				modifyLibrary(input);
		}
	}
	

	private static void modifyPlaylist(Scanner input) {
		/* This method is used to create or modify an existing play list.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter 'create' to create a playlist, 'exit' to cancel, or the playlist's name you want to modify.");
		String entry = input.nextLine().trim().toLowerCase();
		switch (entry) {
			case "create":
				createPlaylist(input);
				break;
			case "exit":
				return;
			default:
				modifySpecificPlaylist(input,entry);
		}
	}

	private static void modifySpecificPlaylist(Scanner input, String playlistName) {
		/* This method is used to add or remove a song from the previously entered play list.
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selections.
		 * 		playlistName: The string name of the play list to modify.
		 * 
		 * Returns null.
		 */
		System.out.println("Enter 'add' or 'remove' to add or remove a song.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "add":
				addSongToPlaylist(input,playlistName);
				break;
			case "remove":
				removeSongFromPlaylist(input,playlistName);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				modifySpecificPlaylist(input,playlistName);
		}
	}
	public static void rateSongs(Scanner input) {
		/* This method is used to determine if the user wants to rate or favorite a song
		 * 
		 */
		System.out.println("Enter 'rate' or 'favorite' to rate or favorite a song.\n Rating a song 5 stars automatically favorites it!");
		switch (input.nextLine().trim().toLowerCase()) {
			case "rate":
				rateSong(input);
				break;
			case "favorite":
				favoriteSong(input);
				break;
			case "exit":
				return;
			default:
				System.out.println("Command not understood, enter 'exit' to return to main menu or try again.");
				rateSongs(input);
		}
	}
	public static void rateSong(Scanner input) {
		/* This method is used to get the song's name the user wants to rate.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null.  
		 */
		System.out.println("Enter the song's name that you want to rate.");
		String songName = input.nextLine().trim().toLowerCase();
		rateSongNum(input,songName);
	}
	/*	Above this line is for user commands.
	 * ----------------------------------------------------------------------
	 *  Below this line is to call functions to execute user commands.
	 */
	private static void songFromStoreByTitle(Scanner input) {
		/* This method searches for a song from the music store by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null.  
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByTitleStore(search));
	}

	private static void songFromStoreByArtist(Scanner input) {
		/* This method searches for a song from the music store by a given artist.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null. 
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByArtistStore(search));
	}

	private static void albumFromStoreByTitle(Scanner input) {
		/* This method searches for an album from the music store by a given album name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByTitleStore(search));
	}

	private static void albumFromStoreByArtist(Scanner input) {
		/* This method searches for an album from the music store by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByArtistStore(search));
	}

	private static void songFromLibraryByTitle(Scanner input) {
		/* This method searches for a song from the user library by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByTitleUser(search));
	}

	private static void songFromLibraryByArtist(Scanner input) {
		/* This method searches for a song from the user library by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByArtistUser(search));
	}

	private static void albumFromLibraryByTitle(Scanner input) {
		/* This method searches for an album from the user library by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByTitleUser(search));
	}

	private static void albumFromLibraryByArtist(Scanner input) {
		/* This method searches for an album from the user library by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByArtistUser(search));
	}

	private static void playlistSearch(Scanner input) {
		/* This method searches for a play list by a given play list name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter playlist name:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.searchPlaylist(search));
	}

	private static void getSongs() {
		// This method prints all songs in the music store.
		System.out.println(store.getSongTitles());
	}

	private static void getArtists() {
		// This method prints all artist's names who have songs in the music store.
		System.out.println(store.getArtists());
	}

	private static void getAlbums() {
		// This method prints the names of all albums in the music store.
		System.out.println(store.getAlbums());
	}

	private static void getPlaylists() {
		// This method prints the names of all play lists.
		System.out.println(store.getPlaylists());
	}

	private static void getFavorites() {
		// This method prints all songs that the user has favorited or rated '5'.
		System.out.println(store.getFavoriteSongs());
	}
	
	private static void rateSongNum(Scanner input, String songName) {
		/* This method is used in rating a provided song.
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		songName: The String name of the song.
		 * If the user enters a number above 5, the rating will be set to 5.
		 * If the user enters a number below 0, the rating will be set to 0.
		 * If the rating is 5, the song will also be favorited.
		 * Returns null.
		 */
		System.out.println("Enter your rating for the song from 1 to 5");
		int rating = Integer.parseInt(input.next());
		System.out.println(store.rateSong(songName, rating));
	}

	private static void favoriteSong(Scanner input) {
		/* This method is used to favorite a song.
		* Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null. 
		 * Returns null.
		 */
		System.out.println("Enter the song's name you want to favorite.");
		String songName = input.nextLine().trim().toLowerCase();
		System.out.println(store.favoriteSong(songName));
	}

	private static void createPlaylist(Scanner input) {
		/* This method is used to create a play list.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null. 
		 */
		System.out.println("Enter playlist name  you want to create");
		String name = input.nextLine();
		System.out.println(store.makePlaylist(name));
	}

	private static void addSongToPlaylist(Scanner input, String playlistName) {
		/* This method is used to add a song to a provided play list.
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		playlistName: The String name of the play list to add a song to.
		 * 
		 * Returns null. 
		 */
		System.out.println("Enter song to add.");
		String songName = input.nextLine().trim().toLowerCase();
		System.out.println(store.addToPlaylist(playlistName, songName));
	}

	private static void removeSongFromPlaylist(Scanner input, String playlistName) {
		/* This method is used to remove a song to a provided play list.
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		playlistName: The String name of the play list to remove a song from.
		 * 
		 * Returns null. 
		 */
		System.out.println("Enter song to remove.");
		String songName = input.nextLine().toLowerCase();
		System.out.println(store.removeFromPlaylist(playlistName, songName));
	}
	private static void addSongToLibrary(Scanner input) {
		/* This method is used to add a song to the user library
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter song to add.");
		String songName = input.nextLine().trim().toLowerCase();
		System.out.println(store.addSongToUser(songName));
	}

	public static void addAlbumToLibrary(Scanner input) {
		/* This method is used to add an album to the user library
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter album to add.");
		String albumName = input.nextLine().trim().toLowerCase();
		System.out.println(store.addAlbumToUser(albumName));
	}
}