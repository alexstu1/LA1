package Model;

import java.util.*;
import java.io.*;

public class MusicStore {

    private ArrayList<Song> storeSongs;
    private ArrayList<Album> storeAlbums;
    private ArrayList<Song> userSongs;
    private ArrayList<Album> userAlbums;
    private HashSet<String> userArtists;
    private ArrayList<Playlist> userPlaylists;
    private HashSet<Song> userFavorites;


    public MusicStore() {
    	/* This class creates both the store of all available songs and albums, as well as the
    	 * user's chosen songs and play lists.
    	 * The instance variables starting with store refer to the store of all available items.
    	 * The instance variables starting with user refer to the items the user chose to add to their library.
    	 * The albums for the music store must be stored in ./albums/albums.txt
    	 * Each line of albums.txt must be album name, then artist name, separated by a comma.
    	 */
        this.storeSongs = new ArrayList<Song>();
        this.storeAlbums = new ArrayList<Album>();
        this.userArtists = new HashSet<String>();
        this.userSongs = new ArrayList<Song>();
        this.userAlbums = new ArrayList<Album>();
        this.userArtists = new HashSet<String>();
        this.userPlaylists = new ArrayList<Playlist>();
        this.userFavorites = new HashSet<Song>();

        File albumsPath = new File("albums");
        File albumsList = new File(albumsPath, "albums.txt");

        if (albumsList.exists() && albumsList.isFile()) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(albumsList))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    String[] lineSplit = line.split(",");
                    File albumFile = new File(albumsPath, lineSplit[0].trim() + "_" + lineSplit[1].trim() + ".txt");
                    Album currAlbum = new Album(albumFile);
                    for (Song currSong : currAlbum.getTracks()) {
                        storeSongs.add(currSong);
                    }
                    storeAlbums.add(currAlbum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else { 
	    	try {
	    		InputStream is = this.getClass().getResourceAsStream("/albums/albums.txt");
	    		BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
	            String line;
	            while ((line = fileReader.readLine()) != null) {
	                String[] lineSplit = line.split(",");
	                this.getClass().getResourceAsStream(albumsPath+"/"+lineSplit[0].trim()+"_"+lineSplit[1].trim() + ".txt");
	                InputStream albumStream = this.getClass().getResourceAsStream("/"+albumsPath+"/"+lineSplit[0].trim()+"_"+lineSplit[1].trim() + ".txt");
	                BufferedReader albumReader = new BufferedReader(new InputStreamReader(albumStream));
	                Album currAlbum = new Album(albumReader);
	                for (Song currSong : currAlbum.getTracks()) {
	                    storeSongs.add(currSong);
	                }
	                storeAlbums.add(currAlbum);
	            }
	        } catch (IOException e) {
            e.printStackTrace();
	        }
        }
    }

    private ArrayList<Song> getSongByTitle(String title, ArrayList<Song> database) {
        /* This method searches the specified data structure for songs with a title that
         * matches the specified title string.
         * Argument: String of a song title.
         * Argument: The instance variable telling the method where to search.
         * Returns: An ArrayList containing any songs that match the title.
         * 
         * This method is private as it is only meant to prevent duplicate code in the actual
         * search methods.
         */
        ArrayList<Song> matches = new ArrayList<Song>();

        for (Song song : database) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                matches.add(song);
            }
        }

        return matches;
    }

    private ArrayList<Song> getSongByArtist(String artist, ArrayList<Song> database) {
        /* This method searches the specified data structure for songs by an artist that
         * matches the specified artist string.
         * Argument: String of an artist.
         * Argument: The instance variable telling the method where to search.
         * Returns: An ArrayList containing any songs by a matching artist.
         * 
         * This method is private as it is only meant to prevent duplicate code in the actual
         * search methods.
         */
        ArrayList<Song> matches = new ArrayList<Song>();

        for (Song song : database) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                matches.add(song);
            }
        }

        return matches;
    }

    private String makeSearchSongString(ArrayList<Song> matches) {
        /* This method creates the output string representing the search results for a song.
         * Argument: The list of songs that match the search criteria.
         * Returns: A string representation of the songs matching the search criteria.
         * 
         * This method is private as it is a helper to prevent duplicate code in the actual
         * search methods.
         */
        StringBuilder output = new StringBuilder("These are the songs that match your search:\n");
        for (Song song : matches) {
            output.append(song.toString());
        }

        return output.toString().trim();
    }

    private ArrayList<Album> getAlbumByTitle(String title, ArrayList<Album> database) {
        /* This method searches the specified data structure for all albums with a title
         * that matches the specified title string.
         * Argument: String of an album title.
         * Argument: The instance variable telling the method where to search.
         * Returns: An ArrayList containing any albums that match the title.
         * 
         * This method is private as it is only meant to prevent duplicate code in the actual search methods
        */
        ArrayList<Album> matches = new ArrayList<Album>();

        for (Album album : database) {
            if (album.getTitle().equalsIgnoreCase(title)) {
                matches.add(album);
            }
        }

        return matches;
    }

    private ArrayList<Album> getAlbumByArtist(String artist, ArrayList<Album> database) {
        /* This method searches the specified data structure for all albums by an artist
         * that matches the specified artist string.
         * Argument: String of an artist.
         * Argument: The instance variable telling the method where to search.
         * Returns: An ArrayList containing any albums by the artist.
         * 
         * This method is private as it is only meant to prevent duplicate code in the actual search methods
        */
        ArrayList<Album> matches = new ArrayList<Album>();

        for (Album album : database) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                matches.add(album);
            }
        }

        return matches;
    }

    private String makeSearchAlbumString(ArrayList<Album> matches) {
        /* This method creates the output string representing the search results for an album.
         * Argument: The list of albums that match the search criteria.
         * Returns: A string representation of the albums matching the search criteria.
         * 
         * This method is private as it is a helper to prevent duplicate code in the actual
         * search methods.
         */
        StringBuilder output = new StringBuilder("These are the albums that match your search:\n");
        for (Album album : matches) {
            output.append(album.toString());
        }

        return output.toString().trim();
    }

    private Playlist getPlaylist(String name) {
    	/* This method converts a string play list name into its associated playlist object.
    	 * Argument: Takes a String argument of a play list name.
    	 * Returns: the play list with the provided name if it exists.
    	 * Returns: null if no play list of that name exists.
    	 * 
    	 * As play lists are modifiable, the play lists must only be accessible
    	 * through this instance's methods.
    	 */
        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().equalsIgnoreCase(name)) {
                return playlist;
            }
        }

        return null;
    }

    public String songSearchByTitleStore(String title) {
    	/* This method searches the music store for a given song.
    	 * Argument: String name of the song to search the music store for.
    	 * 
    	 * Returns: a string representation of all songs that match the given title if any are found.
    	 * Returns: a printable string for if the song is not in the store.
    	 */
        ArrayList<Song> matches = getSongByTitle(title, storeSongs);
        if (matches.isEmpty()) return "Sorry! It looks like we don't have that song in our library.";

        return makeSearchSongString(matches);
    }

    public String songSearchByTitleUser(String title) {
    	/* This method searches the user library for a given song.
    	 * Argument: String name of the song to search the user library for.
    	 * 
    	 * Returns: on successful search,a string representation of the songs that match the given title.
    	 * Returns: a printable string for if the library is empty, or if no song of that name
    	 * exists in the user library.
    	 */
        if (userSongs.isEmpty()) return "Your library has no songs currently. Add some to get started!";

        ArrayList<Song> matches = getSongByTitle(title, userSongs);
        if (matches.isEmpty()) return "It doesn't look like you've added that song to your library yet.";

        return makeSearchSongString(matches);
    }

    public String songSearchByArtistStore(String artist) {
    	/* This method searches the music store for songs by a given artist.
    	 * Argument: The String name of the artist to search the music store for.
    	 * 
    	 * Returns a printable string for if the artist has no songs in the store.
    	 * Returns, on music found, a string representation of all songs from the provided artist.
    	 */
        ArrayList<Song> matches = getSongByArtist(artist, storeSongs);
        if (matches.isEmpty()) return String.format("Sorry! We don't have any songs by %s in our library.", artist);

        return makeSearchSongString(matches);
    }

    public String songSearchByArtistUser(String artist) {
    	/* This method searches the user library for songs by a given artist.
    	 * Argument: The String name of the artist to search the user library for.
    	 * 
    	 * Returns a printable string for if the artist has no songs in the user's library, or if the library is empty.
    	 * Returns, on music found, a string representation of all songs from the provided artist.
    	 */
        if (userSongs.isEmpty()) return "Your library has no songs currently. Add some to get started!";

        ArrayList<Song> matches = getSongByArtist(artist, userSongs);
        if (matches.isEmpty()) return String.format("It doesn't look like you've added any songs by %s to your library yet.", artist);

        return makeSearchSongString(matches);
    }

    public String albumSearchByTitleStore(String title) {
    	/* This method searches the music store for albums by name.
    	 * Argument: The String name of the artist to search the user library for.
    	 * 
    	 * Returns a printable string for if the artist has no songs in the store.
    	 * Returns, on music found, a string representation of all songs from the provided artist.
    	 */
        ArrayList<Album> matches = getAlbumByTitle(title, storeAlbums);
        if (matches.isEmpty()) return "Sorry! It looks like we don't have that album in our library.";

        return makeSearchAlbumString(matches);
    }

    public String albumSearchByTitleUser(String title) {
    	/* This method searches the user library for albums by name.
    	 * Argument: String of the name of the album to search the user library for.
    	 * 
    	 * Returns: A printable string for if the library has no albums, or if no album with the given
    	 * name exists in the library.
    	 * Returns, on successful search: A string representation of all albums that match the given name. 
    	 */
        if (userAlbums.isEmpty()) return "Your library has no albums currently. Add some to get started!";

        ArrayList<Album> matches = getAlbumByTitle(title, userAlbums);
        if (matches.isEmpty()) return "It doesn't look like you've added that album to your library yet.";

        return makeSearchAlbumString(matches);
    }

    public String albumSearchByArtistStore(String artist) {
    	/* This method searches the music store for a album by artist.
    	 * Argument: String name of artist to search the music store for.
    	 * 
    	 * Returns: A printable string for if the store does not have any albums from the given artist.
    	 * Returns, on successful search: A string representation of all albums from the provided artist.
    	 */
        ArrayList<Album> matches = getAlbumByArtist(artist, storeAlbums);
        if (matches.isEmpty()) return String.format("Sorry! It looks like we don't have any albums by %s in our library.", artist);

        return makeSearchAlbumString(matches);
    }

    public String albumSearchByArtistUser(String artist) {
    	/* This method searches the user library for a album by artist.
    	 * Argument: String representation of the artist to search the user library for.
    	 * 
    	 * Returns: A printable string for if the library has no albums, or if no albums by that artist exist.
    	 * Returns, on successful search: A string representation of all albums from the given artist.
    	 * 
    	 */
        if (userAlbums.isEmpty()) return "Your library has no albums currently. Add some to get started!";
        
        ArrayList<Album> matches = getAlbumByArtist(artist, userAlbums);
        if (matches.isEmpty()) return String.format("It doesn't look like you've added any albums by %s to your library yet.", artist);

        return makeSearchAlbumString(matches);
    }

    public String searchPlaylist(String name) {
    	/* This method searches for a play list.
    	 * Argument: String name of the play list to search for.
    	 * 
    	 * Returns: A printable string for if there are no play lists, or if no play list of the given name exists.
    	 * Returns, on successful search: A string representation of the play list with the given name.
    	 */
        if (userPlaylists.isEmpty()) return "You don't have any playlists in your library yet. Make one to get started!";

        Playlist match = getPlaylist(name);
        if (match == null) return "It doesn't look like there's a playlist in your library with that name.";

        return "This is the playlist that matches your search:\n" + match.toString().trim();
    }

    public String addSongToUser(String title) {
    	/* This method adds a song to the user library.
    	 * Argument: String of the song's name to add to the user library.
    	 * 
    	 * Returns: A printable string for if the song does not exist, or if the song is already in the user library.
    	 * Returns, on successful addition: A printable string reporting success.
    	 */
        ArrayList<Song> matches = getSongByTitle(title, storeSongs);
        if (matches.isEmpty()) return "Sorry it looks like we don't have that song in our library.";

        Song adding = matches.get(0);
        if (!userSongs.contains(adding)) {
            userSongs.add(adding);
            userArtists.add(adding.getArtist());
            return "Song added successfully!";
        }

        return "That song is already in your library.";
    }

    public String addAlbumToUser(String title) {
    	/* This method adds an album to the user library.
    	 * Argument: String of the albums name to add to the user library.
    	 * 
    	 * Returns: A printable string for if the album does not exist, or already exists.
    	 * Returns, on successful addition: A printable string reporting success.
    	 */
        ArrayList<Album> matches = getAlbumByTitle(title, storeAlbums);
        if (matches.isEmpty()) return "Sorry it looks like we don't have that album in our library.";

        Album adding = matches.get(0);
        if (!userAlbums.contains(adding)) {
            userAlbums.add(adding);
            for (Song song : adding.getTracks()) {
                if (!userSongs.contains(song)) {
                    userSongs.add(song);
                }
            }

            return "Album added successfully!";
        }

        return "That album is already in your library.";
    }

    public String getSongTitles() {
    	/* This method gets all songs in the user's library.
    	 * 
    	 * Returns: A printable string representing all songs in the user's library.
    	 */
        if (userSongs.isEmpty()) return "Your library has no songs currently. Add some to get started!";

        StringBuilder output = new StringBuilder();
        output.append("These are all of the songs in your library:\n");
        for (Song song : userSongs) {
            output.append(song.toString());
        }
        
        return output.toString().trim();
    }

    public String getArtists() {
    	/* This method gets all artists who have songs in the user's library.
    	 * 
    	 * Returns: A printable string with all artists in the user's library on their own line.
    	 */
        if (userArtists.isEmpty()) return "Your library is empty currently. Add some songs or albums to get started!";

        StringBuilder output = new StringBuilder();
        output.append("These are all of the artists in your library:\n");
        for (String artist : userArtists) {
            output.append(artist + "\n");
        }

        return output.toString().trim();
    }

    public String getAlbums() {
    	/*This method gets all albums in the user's library.
    	 * 
    	 * Returns: A printable string with all album names in the user's library on their own line.
    	 */
        if (userAlbums.isEmpty()) return "Your library has no albums currently. Add some to get started!";

        StringBuilder output = new StringBuilder();
        output.append("These are all of the albums in your library:\n");
        for (Album album : userAlbums) {
            output.append(album.getTitle() + " - " + album.getArtist() + "\n");
        }

        return output.toString().trim();
    }

    public String getPlaylists() {
    	/* This method gets all play lists in the user's library.
    	 * 
    	 * Returns: A printable string with all play list names on their own line.
    	 */
        if (userPlaylists.isEmpty()) return "There are no playlists in your library. Make one to get started!";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your playlists:\n");
        for (Playlist playlist : userPlaylists) {
            output.append(playlist.getName() + "\n");
        }

        return output.toString().trim();
    }

    public String getFavoriteSongs() {
    	/* This method gets all songs that the user has favorited or rated five stars.
    	 * 
    	 * Returns: A printable string of all favorited songs. 
    	 */
        if (userFavorites.isEmpty()) return "You haven't favorited any songs yet.";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your favorite songs:\n");
        for (Song song : userFavorites) {
            output.append(song.toString());
        }

        return output.toString().trim();
    }

    public String makePlaylist(String name) {
    	/* This method creates a play list.
    	 * Arguments: String name of the play list to create.
    	 * 
    	 * Returns: A printable string for if a play list with the given name already exists.
    	 * Returns, on successful creation: A printable string reporting success.
    	 */
        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().equalsIgnoreCase(name)) {
                return "You already have a playlist with that name.";
            }
        }

        Playlist newPlaylist = new Playlist(name);
        userPlaylists.add(newPlaylist);
        return "Playlist created successfully!";
    }

    public String addToPlaylist(String playlist, String songName) {
    	/* This method adds a song to a play list.
    	 * Arguments:
    	 * 		playlist: The string name of the play list to add to.
    	 * 		songName: The string name of the song to add to the play list.
    	 * 
    	 * Returns: A printable string for if either the song or play list do not exist.
    	 * Returns, on successful addition: A printable string reporting success.
    	 */
        Playlist addingTo = getPlaylist(playlist);
        if (addingTo == null) return "It doesn't look like you have a playlist with that name.";

        ArrayList<Song> matches = getSongByTitle(songName, storeSongs);
        if (matches.isEmpty()) return "Sorry! It looks like we don't have that song in our library.";

        Song adding = matches.get(0);
        if (addingTo.hasSong(adding)) return "It looks like that song is already on your playlist.";

        addingTo.addSong(adding);
        addSongToUser(songName);
        return "Song added successfully!";
    }

    public String removeFromPlaylist(String playlist, String songName) {
    	/* This method removes a song from a playlist.
    	 * Arguments:
    	 * 		playlist: The string name of the play list to remove from.
    	 * 		songName: The string name of the song to remove.
    	 * 
    	 * Returns: A printable string for if the play list does not exist, or if the
    	 * song is not on that play list.
    	 * Returns, on successful removal: A printable string reporting success.
    	 */
        Playlist removingFrom = getPlaylist(playlist);
        if (removingFrom == null) return "It doesn't look like you have a playlist with that name.";

        ArrayList<Song> matches = getSongByTitle(songName, storeSongs);
        if (matches.isEmpty()) return "It doesn't look like that playlist has that song on it.";
        
        Song removing = matches.get(0);
        if (!removingFrom.hasSong(removing)) return "It doesn't look like that playlist has that song on it.";

        removingFrom.removeSong(removing);
        return "Song removed successfully!";
    }

    public String favoriteSong(String title) {
    	/* This method adds a song to the favorites list.
    	 * Argument: String name of the song to favorite.
    	 * 
    	 * Returns: A printable string for if the song is not in the store library.
    	 * Returns, on successful favorite: A printable string reporting success.
    	 */
        ArrayList<Song> matches = getSongByTitle(title, storeSongs);
        if (matches.isEmpty()) return "Sorry! It looks like we don't have that song in our library.";

        Song favoriting = matches.get(0);
        favoriting.favorite();
        userFavorites.add(favoriting);
        addSongToUser(title);
        return "Song favorited!";
    }

    public String rateSong(String title, int rating) {
    	/* This method rates a song and adds to favorites if rated 5.
    	 * Arguments:
    	 * 		title: The string name of the song to rate.
    	 * 		rating: The integer rating to rate the song. 
    	 * If rating is below 0 it will be set to 0.
    	 * If rating is above 5 it will be set to 5.
    	 * 
    	 * Returns: A printable string for if the song is not in the music store.
    	 * Returns, on successful rating: A printable string reporting success.
    	 */
        ArrayList<Song> matches = getSongByTitle(title, storeSongs);
        if (matches.isEmpty()) return "Sorry! It looks like we don't have that song in our library.";

        Song song = matches.get(0);
        song.setRating(rating);
        if (song.isFavorite()) {
            userFavorites.add(song);
            addSongToUser(title);
        }

        return "Song rated!";
    }
}