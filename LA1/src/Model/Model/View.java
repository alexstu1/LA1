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

	public static void instructions() {
		/* This method provides instructions for how to start a command sequence,
		 * 	further instructions are provided once a command is started. 
		 * No arguments or returns.
		 */
		System.out.println("Enter 'search' and follow the instructions given to find a song, album, or playlist.");
		System.out.println("Enter'get' and follow the instructions to get all of a certain category.");
		System.out.println("Enter 'playlist' and follow the instructions to create or modify a playlist");
		System.out.println("Enter 'exit' at any point to return the main menu.");
		System.out.println("Enter 'end' to terminate the program.");	
	}

	public static void search(Scanner input) {
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

	public static void storeSearch(Scanner input){
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

	public static void librarySearch(Scanner input) {
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

	public static void songFromStore(Scanner input) {
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

	public static void albumFromStore(Scanner input) {
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

	public static void songFromLibrary(Scanner input) {
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

	public static void albumFromLibrary(Scanner input) {
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

	public static void get(Scanner input) {
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
	/*Method never used, Delete before submission if still never used.
	 * 
	 *
	public static void modifySong(Scanner input, String songName) {
		/* This method is used to either favorite or
		 * 
		 *
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
	*/

	public static void modifyPlaylist(Scanner input) {
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

	public static void modifySpecificPlaylist(Scanner input, String playlistName) {
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
	
	/*	Above this line is for user commands.
	 * ----------------------------------------------------------------------
	 *  Below this line is to call functions to execute user commands.
	 */
	public static void songFromStoreByTitle(Scanner input) {
		/* This method searches for a song from the music store by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null.  
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByTitleStore(search));
	}

	public static void songFromStoreByArtist(Scanner input) {
		/* This method searches for a song from the music store by a given artist.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null. 
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByArtistStore(search));
	}

	public static void albumFromStoreByTitle(Scanner input) {
		/* This method searches for an album from the music store by a given album name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByTitleStore(search));
	}

	public static void albumFromStoreByArtist(Scanner input) {
		/* This method searches for an album from the music store by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByArtistStore(search));
	}

	public static void songFromLibraryByTitle(Scanner input) {
		/* This method searches for a song from the user library by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByTitleUser(search));
	}

	public static void songFromLibraryByArtist(Scanner input) {
		/* This method searches for a song from the user library by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.songSearchByArtistUser(search));
	}

	public static void albumFromLibraryByTitle(Scanner input) {
		/* This method searches for an album from the user library by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByTitleUser(search));
	}

	public static void albumFromLibraryByArtist(Scanner input) {
		/* This method searches for an album from the user library by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter your search:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.albumSearchByArtistUser(search));
	}

	public static void playlistSearch(Scanner input) {
		/* This method searches for a play list by a given play list name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter playlist name:");
		String search = input.nextLine().toLowerCase();
		System.out.println(store.searchPlaylist(search));
	}

	public static void getSongs() {
		// This method prints all songs in the music store.
		System.out.println(store.getSongTitles());
	}

	public static void getArtists() {
		// This method prints all artist's names who have songs in the music store.
		System.out.println(store.getArtists());
	}

	public static void getAlbums() {
		// This method prints the names of all albums in the music store.
		System.out.println(store.getAlbums());
	}

	public static void getPlaylists() {
		// This method prints the names of all play lists.
		System.out.println(store.getPlaylists());
	}

	public static void getFavorites() {
		// This method prints all songs that the user has favorited or rated '5'.
		System.out.println(store.getFavoriteSongs());
	}

	public static void rateSong(Scanner input, String songName) {
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

	public static void favoriteSong(String songName) {
		/* This method is used to favorite a provided song.
		 * Argument: The string name of the song.
		 * 
		 * Returns null.
		 */
		System.out.println(store.favoriteSong(songName));
	}

	public static void createPlaylist(Scanner input) {
		/* This method is used to create a play list.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null. 
		 */
		System.out.println("Enter playlist name  you want to create");
		String name = input.nextLine();
		System.out.println(store.makePlaylist(name));
	}

	public static void addSongToPlaylist(Scanner input, String playlistName) {
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

	public static void removeSongFromPlaylist(Scanner input, String playlistName) {
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

}