package Model;

import java.util.HashMap;
import java.util.Scanner;
/*This class stores and runs all necessary code to load the music store,
 *  then allow the user to interact with it through the command line with
 *  on screen instructions.
 */

public class View {
	private static HashMap<String,LibraryModel> allLibs = new HashMap<String,LibraryModel>();
	private static LibraryModel lib = new LibraryModel();
	private static MusicStore store = new MusicStore();
	private static String loggedInString = "You are currently not logged in.";
	public static void main(String[] args) {
		/* This method prompts and reads user input, then calls the necessary functions.
		 * It does not check or use any command line arguments when running the file.
		 */
		allLibs.put("", lib);
		Scanner input = new Scanner(System.in);
		boolean continuing = true;
		greetings();
		while(continuing) {
			instructions();
			switch (input.nextLine().trim().toLowerCase()) {
				case "s":
					search(input);		
					break;
				case "g":
					get(input);
					break;
				case "mp":
					modifyPlaylist(input);
					break;
				case "ml":
					modifyLibrary(input);
					break;
				case "p":
					play(input);
					break;
				case "r":
					rateSongs(input);
					break;
				case "l":
					if(allLibs.get("").equals(lib)) {
						login(input);
					} else {
						//user logged in
						logout(input);
					}
					break;
				case "m":
					break;
				case "e":
					input.close();
					continuing=false;
					User.deleteDatabase();
					break;
				default:
					System.out.println("Command not recognized, please enter a valid command from the list within the []. Commands are case insensitive.");
			}
		}
	}

	private static void greetings() {
		/* This method greets the user at the main menu.
		 * No arguments or returns.
		 */
		System.out.println("Welcome to Eric and Alex's Music App!");
		System.out.println("This is your one stop shop for finding new and old favorites!");
	}

	private static void instructions() {
		/* This method provides instructions for how to start a command sequence,
		 * 	further instructions are provided once a command is started. 
		 * No arguments or returns.
		 */
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(loggedInString);
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("PLAY:             ENTER [P] and follow the instructions to play a song from your library.");
		System.out.println("SEARCH:           Enter [S] and follow the instructions to search for a song, album, or playlist.");
		System.out.println("GET ALL:          Enter [G] and follow the instructions to retrieve all of a certain category (songs, albums, etc.) from your library.");
		System.out.println("MODIFY PLAYLISTS: Enter [MP] and follow the instructions to create or modify a playlist.");
		System.out.println("MODIFY LIBRARY:   Enter [ML] to shuffle or add/remove a song or entire album to your library.");
		System.out.println("RATE/FAVORITE:    Enter [R] to rate or favorite a song.");
		System.out.println("LOGIN:            Enter [L] to login or logout of a library.");
		System.out.println("RETURN TO MENU:   Enter [M] at any point to return the main menu.");
		System.out.println("EXIT APP:         Enter [E] to terminate the program.");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private static void search(Scanner input) {
		/* This method is used to search the music store or user library for songs, albums, or play lists. 
		 * No arguments or returns.
		 */
		System.out.println("Enter [S] to search our store, [L] to search your library, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "s":
				storeSearch(input);
				break;
			case "l":
				librarySearch(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				search(input);
		}
	}

	private static void storeSearch(Scanner input){
		/* This method is used to search the music store for songs, albums, or play lists.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null.
		 */
		System.out.println("Enter [S] to search for a song, [A] to search for an album, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "s":
				songFromStore(input);
				break;
			case "a":
				albumFromStore(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				storeSearch(input);
		}
	}

	private static void librarySearch(Scanner input) {
		/* This method is used to search the user library for songs, albums, or play lists.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null.
		 */
		System.out.println("Enter [S] to search for a song, [A] to search for an album, [P] to search for a playlist, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "s":
				songFromLibrary(input);
				break;
			case "a":
				albumFromLibrary(input);
				break;
			case "p":
				playlistSearch(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				librarySearch(input);
		}
	}

	private static void songFromStore(Scanner input) {
		/* This method is used to search the music store for a song.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [T] to search by title, [A] to search by artist, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "t":
				songFromStoreByTitle(input);
				break;
			case "a":
				songFromStoreByArtist(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				songFromStore(input);
		}
	}

	private static void albumFromStore(Scanner input) {
		/* This method is used to search the music store for an album.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [T] to search by title, [A] to search by artist, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "t":
				albumFromStoreByTitle(input);
				break;
			case "a":
				albumFromStoreByArtist(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				albumFromStore(input);
		}
	}

	private static void songFromLibrary(Scanner input) {
		/* This method is used to search the user library for a song.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [T] to search by title, [A] to search by artist, [G] to search by genre, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "t":
				songFromLibraryByTitle(input);
				break;
			case "a":
				songFromLibraryByArtist(input);
				break;
			case "g":
				songFromLibraryByGenre(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				songFromLibrary(input);
		}	
	}

	private static void albumFromLibrary(Scanner input) {
		/* This method is used to search the user library for an album.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [T] to search by title, [A] to search by artist, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "t":
				albumFromLibraryByTitle(input);
				break;
			case "a":
				albumFromLibraryByArtist(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				albumFromLibrary(input);
		}
		
	}

	private static void get(Scanner input) {
		/* This method is used to get all of the following from the music store: 
		 * 		songs,artists,albums,play lists, or favorites.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [S] to retrieve all songs, [AR] for artists, [AL] for albums, [P] for playlists, [F] for favorites, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "s":
				getSongs(input);
				break;
			case "ar":
				getArtists();
				break;
			case "al":
				getAlbums();
				break;
			case "p":
				getPlaylists();
				break;
			case "f":
				getFavorites();
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				get(input);
		}
	}

	private static void modifyLibrary(Scanner input) {
		/* This method will guide the user through modifying their library.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter [A] to add something to the library, [R] to remove something, [S] to shuffle your songs, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "a":
				addToLibrary(input);
				break;
			case "r":
				removeFromLibrary(input);
				break;
			case "m":
				return;
			case "s":
				lib.shuffle();
				System.out.println("Library shuffled successfully!");
				break;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				modifyLibrary(input);
		}
	}

	private static void removeFromLibrary(Scanner input) {
		/* This method is used when the user selects they want to remove a song from their library.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [S] to remove a song from your library, [A] to remove an album, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "s":
				removeSongLibrary(input);
				break;
			case "a":
				removeAlbumLibrary(input);
				break;
			case "m":
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				removeFromLibrary(input);
		}
	}

	private static void addToLibrary(Scanner input) {
		/* This method is used to add a song or album to the user library.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [S] to add a song to your library, [A] to add an album, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "s":
				addSongToLibrary(input);
				break;
			case "a":
				addAlbumToLibrary(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				addToLibrary(input);
		}
	}
	
	private static void modifyPlaylist(Scanner input) {
		/* This method is used to create or modify an existing play list.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println(lib.getPlaylists());
		System.out.println("NOTE: Recently Played, Most Played, Top Rated, and Favorites are automatically updated with your activity and should not be modified.");
		System.out.println("Enter [C] to create a playlist, the name of the playlist you want to modify, or [M] to return to the main menu.");
		String entry = input.nextLine().trim().toLowerCase();
		switch (entry) {
			case "c":
				createPlaylist(input);
				break;
			case "m":
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
		System.out.println("Enter [A] to add a song to playlist, [R] to remove a song, [S] to shuffle the playlist, or [M] to return to the main menu.");
		switch (input.nextLine().trim().toLowerCase()) {
			case "a":
				addSongToPlaylist(input,playlistName);
				break;
			case "r":
				removeSongFromPlaylist(input,playlistName);
				break;
			case "s":
				lib.shufflePlaylist(playlistName);
				System.out.println("Playlist shuffled successfully!");
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
				modifySpecificPlaylist(input,playlistName);
		}
	}

	public static void rateSongs(Scanner input) {
		/* This method is used to determine if the user wants to rate or favorite a song
		 * 
		 */
		System.out.println("Enter [R] to rate a song, [F] to favorite a song, or [M] to return to the main menu.");
		System.out.println("Note: Giving a song a 5 star rating automatically favorites it!");
		switch (input.nextLine().trim().toLowerCase()) {
			case "r":
				rateSong(input);
				break;
			case "f":
				favoriteSong(input);
				break;
			case "m":
				return;
			default:
				System.out.println("Command not recognized, please enter a valid command from the instructions within the []. Commands are case insensitive.");
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
		rateSongNum(input, songName);
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
		System.out.println("Enter the song's title:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getSongByTitleStore(search, store));
	}

	private static void songFromStoreByArtist(Scanner input) {
		/* This method searches for a song from the music store by a given artist.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null. 
		 */
		System.out.println("Enter the song's artist:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getSongByArtistStore(search, store));
	}

	private static void albumFromStoreByTitle(Scanner input) {
		/* This method searches for an album from the music store by a given album name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter the album's title:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getAlbumByTitleStore(search, store));
	}

	private static void albumFromStoreByArtist(Scanner input) {
		/* This method searches for an album from the music store by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter album's artist:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getAlbumByArtistStore(search, store));
	}

	private static void songFromLibraryByTitle(Scanner input) {
		/* This method searches for a song from the user library by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter the song's title:");
		String search = input.nextLine().toLowerCase();
		String output = lib.getSongByTitle(search);
		System.out.println(output);
		if (output.contains("doesn't look like that song")) return;
		System.out.println("If you would like to see album information for one of the above songs, enter the song's artist or [M] to return to the main menu.");
		String artist = input.nextLine().toLowerCase();
		switch (artist) {
			case "m":
				break;
			default:
				System.out.println(lib.getAlbumInformation(search, artist, store));
		}
	}

	private static void songFromLibraryByArtist(Scanner input) {
		/* This method searches for a song from the user library by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter the song's artist:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getSongByArtist(search));
	}

	private static void songFromLibraryByGenre(Scanner input) {
		System.out.println("Enter the genre you want to search for:");
		String genre = input.nextLine().toLowerCase();
		System.out.println(lib.getSongByGenre(genre));
	}

	private static void albumFromLibraryByTitle(Scanner input) {
		/* This method searches for an album from the user library by a given song name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter the album's title:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getAlbumByTitle(search));
	}

	private static void albumFromLibraryByArtist(Scanner input) {
		/* This method searches for an album from the user library by a given artist name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter the album's artist:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getAlbumByArtist(search));
	}

	private static void playlistSearch(Scanner input) {
		/* This method searches for a play list by a given play list name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println(lib.getPlaylists());
		System.out.println("Enter the playlist's name:");
		String search = input.nextLine().toLowerCase();
		System.out.println(lib.getPlaylist(search));
	}

	private static void getSongs(Scanner input) {
		// This method prints all songs in the music store.
		System.out.println("Would you like to sort your results by [T] title, [A] artist, [R] rating? Enter [N] to not sort. ");
		switch (input.nextLine().trim().toLowerCase()) {
			case "t":
				getSongsSortedByTitle();
				break;
			case "a":
				getSongsSortedByArtist();
				break;
			case "r":
				getSongsSortedByRating();
				break;
			default:
				System.out.println(lib.getSongs());
		}
	}

	private static void getSongsSortedByTitle() {
		// This method prints all songs in the user's library sorted by title.
		System.out.println(lib.getSongsSortedByTitle());
	}

	private static void getSongsSortedByArtist() {
		// This method prints all songs in the user's library sorted by artist.
		System.out.println(lib.getSongsSortedByArtist());
	}

	private static void getSongsSortedByRating() {
		// This method prints all songs in the user's library sorted by rating.
		System.out.println(lib.getSongsSortedByRating());
	}

	private static void getArtists() {
		// This method prints all artist's names who have songs in the music store.
		System.out.println(lib.getArtists());
	}

	private static void getAlbums() {
		// This method prints the names of all albums in the music store.
		System.out.println(lib.getAlbums());
	}

	private static void getPlaylists() {
		// This method prints the names of all play lists.
		System.out.println(lib.getPlaylists());
	}

	private static void getFavorites() {
		// This method prints all songs that the user has favorited or rated '5'.
		System.out.println(lib.getFavorites());
	}
	
	private static void rateSongNum(Scanner input, String songName) {
		/* This method is used in rating a provided song.
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		songName: The String name of the song.
		 * If the user enters a number above 5, the rating will be set to 5.
		 * If the user enters a number below 1, the rating will be set to 1.
		 * If the rating is 5, the song will also be favorited.
		 * Returns null.
		 */
		System.out.println("Rate the song 1-5:");
		int rating = Integer.parseInt(input.nextLine());
		String output = lib.rateSong(songName, rating);
		System.out.println(output);
		if (output.equals("There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is rated.")) {
			rateSongNum(input, songName, rating);
		}
	}

	private static void rateSongNum(Scanner input, String songName, int rating) {
		/* This method is used to rate a song when there are multiple songs in the user's library
		 * with a name matching the specified song name
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		songName: The String name of the song to rate
		 * 		rating: The int rating to give the song
		 * 
		 * Returns null.
		 */
		System.out.println(lib.buildFavoriteRateSongDupeString(songName));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.rateSong(songName, rating, artist));
	}



	private static void favoriteSong(Scanner input) {
		/* This method is used to favorite a song.
		* Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null.
		 */
		System.out.println("Enter the song's name you want to favorite.");
		String songName = input.nextLine().trim().toLowerCase();
		String output = lib.favoriteSong(songName);
		System.out.println(output);
		if (output.equals("There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is favorited.")) {
			favoriteSong(input, songName);
		}
	}

	private static void favoriteSong(Scanner input, String songName) {
		/* This method is used to favorite a song when the user's library contains multiple songs
		 * with a name matching the specified song name
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		songName: The String name of the song to favorite
		 * Returns null.
		 */
		System.out.println(lib.buildFavoriteRateSongDupeString(songName));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.favoriteSong(songName, artist));

	}

	private static void createPlaylist(Scanner input) {
		/* This method is used to create a play list.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selection.
		 * Returns null. 
		 */
		System.out.println("What do you want to name your playlist?");
		String name = input.nextLine();
		System.out.println(lib.makePlaylist(name));
	}

	private static void addSongToPlaylist(Scanner input, String playlistName) {
		/* This method is used to add a song to a provided play list.
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		playlistName: The String name of the play list to add a song to.
		 * 
		 * Returns null. 
		 */
		System.out.println("Enter the song's name you'd like to add to your playlist.");
		String songName = input.nextLine().trim().toLowerCase();
		String output = lib.addToPlaylist(playlistName, songName);
		System.out.println(output);
		if (output.equals("There are multiple songs with that title in your library, please specify the artist to ensure the correct one is added.")) {
			addSongToPlaylist(input, playlistName, songName);
		}
	}

	private static void addSongToPlaylist(Scanner input, String playlistName, String songName) {
		/* This method is used to add a song to a provided playlist when there are multiple songs
		 * with the specified name in the user's library
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		playlistName: The String name of the play list to add a song to.
		 * 		songName: The String name of the song to add to the playlist
		 * 
		 * Returns null. 
		 */
		System.out.println(lib.buildAddToPlaylistDupeString(songName));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.addToPlaylist(playlistName, songName, artist));
	}

	

	private static void removeSongFromPlaylist(Scanner input, String playlistName) {
		/* This method is used to remove a song to a provided play list.
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		playlistName: The String name of the play list to remove a song from.
		 * 
		 * Returns null. 
		 */
		System.out.println("Enter the song's name you'd like to remove.");
		String songName = input.nextLine().toLowerCase();
		String output = lib.removeFromPlaylist(playlistName, songName);
		System.out.println(output);
		if (output.equals("There are multiple songs on that playlist with that name. Please specify the artist to ensure the correct one is removed.")) {
			removeSongFromPlaylist(input, playlistName, songName);
		}
	}

	private static void removeSongFromPlaylist(Scanner input, String playlistName, String songName) {
		/* This method is used to remove a song from a provided playlist when multiple songs
		 * on the playlist share the name of the specified song to remove
		 * Arguments:
		 * 		input: A scanner object that is monitoring the command line where the user enters their selection.
		 * 		playlistName: The String name of the playlist to remove a song from.
		 * 		songName: The String name of the song to remove from the playlist
		 */
		System.out.println(lib.buildRemoveFromPlaylistDupeString(playlistName, songName));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.removeFromPlaylist(playlistName, songName, artist));
	}

	private static void addSongToLibrary(Scanner input) {
		/* This method is used to add a song to the user library
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter the song's name you'd like to add to your library.");
		String songName = input.nextLine().trim().toLowerCase();
		String output = lib.addSong(songName, store);
		System.out.println(output);
		if (output.equals("There are multiple songs with that title in our library. Please specify the artist to ensure we add the correct one!")) {
			addSongToLibrary(input, songName);
		}
	}

	public static void addSongToLibrary(Scanner input, String songName) {
		/* This method is used to add a song to the user library if there are multiple songs with the specified name
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Argument: The string name of the song to add
		 * Returns null. 
		 */
		System.out.println(lib.buildAddSongDupeString(songName, store));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.addSong(songName, artist, store));
	}

	private static void removeSongLibrary(Scanner input) {
		/* This method is used to prompt the user for a song name and remove that song from the library.
   		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter the song's name you'd like to remove from your library.");
		String songName = input.nextLine().trim().toLowerCase();
		String output = lib.removeSongLibrary(songName);
		System.out.println(output);
		if (output.equals("There are multiple songs with that title in our library. Please specify the artist to ensure we remove the correct one!")) {
			removeSongLibrary(input, songName);
		}
	}

	private static void removeSongLibrary(Scanner input, String songName) {
		/* This method is used to prompt the user for an artist name and remove a given song by artist from the library.
   		 * Arguments:
      		 *	input: A scanner object that is monitoring the command line where the user enters their selections.
	 	 *	songName: A String of the song's name to be removed.
		 * Returns null. 
		 */
		System.out.println(lib.buildAddSongDupeString(songName, store));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.removeSongLibrary(songName, artist));
	}

	private static void addAlbumToLibrary(Scanner input) {
		/* This method is used to add an album to the user library
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter the album's name you'd like to add to your library.");
		String albumName = input.nextLine().trim().toLowerCase();
		String output = lib.addAlbum(albumName, store);
		System.out.println(output);
		if (output.equals("There are multiple albums with that title in our library. Please specify the artist to ensure we add the correct one!")) {
			addAlbumToLibrary(input, albumName);
		}
	}

	private static void addAlbumToLibrary(Scanner input, String albumName) {
		/* This method is used to add an album to the user library if there are multiple album with the specified name
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Argument: The string name of the album to add
		 * Returns null. 
		 */
		System.out.println(lib.buildAddAlbumDupeString(albumName, store));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.addAlbum(albumName, artist, store));
	}

	private static void removeAlbumLibrary(Scanner input) {
		/* This method is used to remove an album to the user library of promped name.
		 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter the album's name you'd like to remove from your library.");
		String albumName = input.nextLine().trim().toLowerCase();
		System.out.println(lib.removeAlbumLibrary(albumName));
	}

	private static void play(Scanner input) {
		/* This method prompts the user for a song to play and artist if required and calls required funcitons to play the song.
  	 	 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter the name of the song you'd like to play.");
		String songName = input.nextLine().trim().toLowerCase();
		String output = lib.play(songName);
		System.out.println(output);
		if (output.contains("multiple songs in your library")) {
			play(input, songName);
		}
	}

	private static void play(Scanner input, String songName) {
		/* This method prompts the user for an artist and calls required funcitons to play the song by artist.
  	 	 * Arguments: 
      		 *	input: A scanner object that is monitoring the command line where the user enters their selections.
	 	 *	songName: A String of the song to play
		 * Returns null. 
		 */
		System.out.println(lib.buildAddSongDupeString(songName, store));
		String artist = input.nextLine().trim().toLowerCase();
		System.out.println(lib.play(songName, artist));
	}

	/* Below this line is for user account handling */
	
	private static void logout(Scanner input) {
		/* This method is used to log the user out and loads the default user library for use.
  	 	 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		
		System.out.println("Enter [Y] to logout or [N] to return to the main menu.");
		switch (input.nextLine().toLowerCase()) {
			case "y":
				lib = allLibs.get("");
				System.out.println("Successfully logged out!");
				loggedInString = "You are currently not logged in.";
				break;
			case "n":
			case "m":
				return;
			default: 
				logout(input);
		}
	}

	private static void login(Scanner input) {
		/* This method is used to create a new user account or to log the user in and loads their user library for use.
  	 	 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter [L] to login, [C] to create a new account, or [M] to return to the main menu.");
		switch (input.nextLine().toLowerCase()) {
			case "l":
				loginAs(input);
				break;
			case "c":
				newUser(input);
				break;
			case "m":
				return;
			default:
				login(input);
		}
		
	}

	public static void loginAs(Scanner input) {
		/* This method is used to log the user in and loads their user library for use.
  		 * If the user types in an invalid username or password it will allow them to retry or go back to main menu.
  	 	 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		System.out.println("Enter your username:");
		String username = input.nextLine();
		System.out.println("Enter your password:");
		String password = input.nextLine();
		User login = new User(username, password);
		if(login.isValidLogin()) {
			if (allLibs.containsKey(username)) {
				lib = allLibs.get(username);
			} else {
				lib = new LibraryModel();
				allLibs.put(username, lib);
			}
			System.out.println("Successfully logged in as " + username+ "!");
			loggedInString = "You are currently logged in as " + username;
			return;
		} else {
			System.out.println("Invalid username or password");
			login(input);
		}
	}

	private static void newUser(Scanner input) {
		/* This method is used to create and login to a new user account and save it for the future.
  	 	 * Argument: A scanner object that is monitoring the command line where the user enters their selections.
		 * Returns null. 
		 */
		boolean validUsername = false;
		String username="";
		while (!validUsername) {
			System.out.println("Enter your desired username:");
			username = input.nextLine();
			validUsername = User.isUserNameAvailable(username);
			if (!validUsername) {
				System.out.println("This username is either taken, or contained a comma, please try a different username.");
			}
		}
		System.out.println("Enter your desired password:");
		String password = input.nextLine();
		User newUser = new User(username, password);
		newUser.saveCredentials();
		lib = new LibraryModel();
		allLibs.put(username, lib);
		System.out.println("Successfully created an account and logged in as "+username+"!");
		loggedInString = "You are currently logged in as " + username + ".";
	}
}
