package Model;

import java.util.*;
import java.io.*;

public class MusicStore {

    private ArrayList<Song> storeSongs;
    private ArrayList<Album> storeAlbums;
    private HashSet<String> storeArtists;
    private ArrayList<Song> userSongs;
    private ArrayList<Album> userAlbums;
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
        this.storeArtists = new HashSet<String>();
        this.userSongs = new ArrayList<Song>();
        this.userAlbums = new ArrayList<Album>();
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
                        storeArtists.add(currSong.getArtist());
                    }
                    storeAlbums.add(currAlbum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Song checkSong(String title) {
    	/* This method converts a string song name into its associated song object.
    	 * Argument: String of a songs name.
    	 * Returns: Song object with the same name if it exists.
    	 * Returns: null if no song with the given name exists
    	 * 
    	 * As songs are modifiable, they must only be 
    	 * accessible through this instance's methods.
    	 */
        for (Song song : storeSongs) {
            if (song.getTitle().toLowerCase().equals(title)) {
                return song;
            }
        }
        
        return null;
    }

    private Album checkAlbum(String title) {
    	/* This method converts a string album name into its associated album object.
    	 * Argument: String of the albums name.
    	 * Returns: Album object with the same name if it exists.
    	 * Returns: null if no album of that name exists.
    	 *
    	 * As the songs within are modifiable, the album must only be accessible
    	 * through this instance's methods.
    	 */
        for (Album album : storeAlbums) {
            if (album.getTitle().toLowerCase().equals(title)) {
                return album;
            }
        }

        return null;
    }

    private Playlist checkPlaylist(String name) {
    	/* This method converts a string play list name into its associated playlist object.
    	 * Argument: Takes a String argument of a play list name.
    	 * Returns: the play list with the provided name if it exists.
    	 * Returns: null if no play list of that name exists.
    	 * 
    	 * As play lists are modifiable, the play lists must only be accessible
    	 * through this instance's methods.
    	 */
        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().toLowerCase().equals(name)) {
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
        StringBuilder matches = new StringBuilder();
        for (Song song : storeSongs) {
            if (song.getTitle().toLowerCase().equals(title)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return "Sorry! It looks like we don't have that song in our library.\n";
        }

        return matches.toString();
    }

    public String songSearchByTitleUser(String title) {
    	/* This method searches the user library for a given song.
    	 * Argument: String name of the song to search the user library for.
    	 * 
    	 * Returns: on successful search,a string representation of the songs that match the given title.
    	 * Returns: a printable string for if the library is empty, or if no song of that name
    	 * exists in the user library.
    	 */
        if (userSongs.isEmpty()) return "Your library has no songs currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Song song : userSongs) {
            if (song.getTitle().toLowerCase().equals(title)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return "It doesn't look like you've added that song to your library.\n";
        }

        return matches.toString();
    }

    public String songSearchByArtistStore(String artist) {
    	/* This method searches the music store for songs by a given artist.
    	 * Argument: The String name of the artist to search the music store for.
    	 * 
    	 * Returns a printable string for if the artist has no songs in the store.
    	 * Returns, on music found, a string representation of all songs from the provided artist.
    	 */
        StringBuilder matches = new StringBuilder();
        for (Song song : storeSongs) {
            if (song.getArtist().toLowerCase().equals(artist)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("Sorry! It looks like we don't have any songs by %s in our library.\n", artist);
        }

        return matches.toString();
    }

    public String songSearchByArtistUser(String artist) {
    	/* This method searches the user library for songs by a given artist.
    	 * Argument: The String name of the artist to search the user library for.
    	 * 
    	 * Returns a printable string for if the artist has no songs in the store, or if the store is empty.
    	 * Returns, on music found, a string representation of all songs from the provided artist.
    	 */
        if (userSongs.isEmpty()) return "Your library has no songs currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Song song : userSongs) {
            if (song.getArtist().toLowerCase().equals(artist)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("It doesn't look like you've added any songs by %s to your library yet.\n", artist);
        }

        return matches.toString();
    }

    public String albumSearchByTitleStore(String title) {
    	/* This method searches the music store for albums by name.
    	 * Argument: The String name of the artist to search the user library for.
    	 * 
    	 * Returns a printable string for if the artist has no songs in the store.
    	 * Returns, on music found, a string representation of all songs from the provided artist.
    	 */
        StringBuilder matches = new StringBuilder();
        for (Album album : storeAlbums) {
            if (album.getTitle().toLowerCase().equals(title)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return "Sorry! It looks like we don't have that album in our library.\n";
        }

        return matches.toString();
    }

    public String albumSearchByTitleUser(String title) {
    	/* This method searches the user library for albums by name.
    	 * Argument: String of the name of the album to search the user library for.
    	 * 
    	 * Returns: A printable string for if the library has no albums, or if no album with the given
    	 * name exists in the library.
    	 * Returns, on successful search: A string representation of all albums that match the given name. 
    	 */
        if (userAlbums.isEmpty()) return "Your library has no albums currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Album album : userAlbums) {
            if (album.getTitle().toLowerCase().equals(title)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return "It doesn't look like you've added that album to your library yet.\n";
        }

        return matches.toString();
    }

    public String albumSearchByArtistStore(String artist) {
    	/* This method searches the music store for a album by artist.
    	 * Argument: String name of artist to search the music store for.
    	 * 
    	 * Returns: A printable string for if the store does not have any albums from the given artist.
    	 * Returns, on successful search: A string representation of all albums from the provided artist.
    	 */
        StringBuilder matches = new StringBuilder();
        for (Album album : storeAlbums) {
            if (album.getArtist().toLowerCase().equals(artist)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("Sorry! It looks like we don't have any albums by %s in our library.\n", artist);
        }

        return matches.toString();
    }

    public String albumSearchByArtistUser(String artist) {
    	/* This method searches the user library for a album by artist.
    	 * Argument: String representation of the artist to search the user library for.
    	 * 
    	 * Returns: A printable string for if the library has no albums, or if no albums by that artist exist.
    	 * Returns, on successful search: A string representation of all albums from the given artist.
    	 * 
    	 */
        if (userAlbums.isEmpty()) return "Your library has no albums currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Album album : userAlbums) {
            if (album.getArtist().toLowerCase().equals(artist)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("It doesn't look like you've added any albums by %s to your library yet.\n", artist);
        }

        return matches.toString();
    }

    public String searchPlaylist(String name) {
    	/* This method searches for a play list.
    	 * Argument: String name of the play list to search for.
    	 * 
    	 * Returns: A printable string for if there are no play lists, or if no play list of the given name exists.
    	 * Returns, on successful search: A string representation of the play list with the given name.
    	 */
        if (userPlaylists.isEmpty()) return "You don't have any playlists in your library yet. Make one to get started!\n";

        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().toLowerCase().equals(name)) {
                return playlist.toString();
            }
        }

        return "It doesn't look like there's a playlist in your library with that name.\n";
    }

    public String addSongToUser(String title) {
    	/* This method adds a song to the user library.
    	 * Argument: String of the song's name to add to the user library.
    	 * 
    	 * Returns: A printable string for if the song does not exist, or if the song is already in the user library.
    	 * Returns, on successful addition: A printable string reporting success.
    	 */
        Song adding = checkSong(title);

        if (adding == null) {
            return "Sorry! It looks like we don't have that song in our library.\n";
        }

        if (!userSongs.contains(adding)) {
            userSongs.add(adding);
            return "Song added successfully!\n";
        }

        return "That song is already in your library.\n";
    }

    public String addAlbumToUser(String title) {
    	/* This method adds an album to the user library.
    	 * Argument: String of the albums name to add to the user library.
    	 * 
    	 * Returns: A printable string for if the album does not exist, or already exists.
    	 * Returns, on successful addition: A printable string reporting success.
    	 */
        Album adding = checkAlbum(title);

        if (adding == null) {
            return "Sorry! It looks like we don't have that album in our library.\n";
        }

        if (!userAlbums.contains(adding)) {
            userAlbums.add(adding);
            for (Song song : adding.getTracks()) {
                if (!userSongs.contains(song)) {
                    userSongs.add(song);
                }
            }

            return "Album added successfully!\n";
        }

        return "That album is already in your library.\n";
    }

    public String getSongTitles() {
    	/* This method gets all songs in the music store.
    	 * 
    	 * Returns: A printable string representing all songs in the store.
    	 */
        StringBuilder output = new StringBuilder();
        output.append("These are all of the songs in our library:\n");
        for (Song song : storeSongs) {
            output.append(song.toString());
        }
        
        return output.toString();
    }

    public String getArtists() {
    	/* This method gets all artists who have songs in the music store.
    	 * 
    	 * Returns: A printable string with all artists in the store on their own line.
    	 */
        StringBuilder output = new StringBuilder();
        output.append("These are all of the artists in our library:\n");
        for (String artist : storeArtists) {
            output.append(artist + "\n");
        }

        return output.toString();
    }

    public String getAlbums() {
    	/*This method gets all albums in the music store.
    	 * 
    	 * Returns: A printable string with all album names in the store on their own line.
    	 */
        StringBuilder output = new StringBuilder();
        output.append("These are all of the albums in our library:\n");
        for (Album album : storeAlbums) {
            output.append(album.getTitle() + "\n");
        }

        return output.toString();
    }

    public String getPlaylists() {
    	/* This method gets all play lists in the music store.
    	 * 
    	 * Returns: A printable string with all play list names on their own line.
    	 */
        if (userPlaylists.isEmpty()) return "There are no playlists in your library.\n";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your playlists:\n");
        for (Playlist playlist : userPlaylists) {
            output.append(playlist.getName() + "\n");
        }

        return output.toString();
    }

    public String getFavoriteSongs() {
    	/* This method gets all songs that the user has favorited or rated five stars.
    	 * 
    	 * Returns: A printable string of all favorited songs. 
    	 */
        if (userFavorites.isEmpty()) return "You haven't favorited any songs yet.\n";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your favorite songs:\n");
        for (Song song : userFavorites) {
            output.append(song.toString());
        }

        return output.toString();
    }

    public String makePlaylist(String name) {
    	/* This method creates a play list.
    	 * Arguments: String name of the play list to create.
    	 * 
    	 * Returns: A printable string for if a play list with the given name already exists.
    	 * Returns, on successful creation: A printable string reporting success.
    	 */
        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().toLowerCase().equals(name)) {
                return "You already have a playlist with that name.\n";
            }
        }

        Playlist newPlaylist = new Playlist(name);
        userPlaylists.add(newPlaylist);
        return "Playlist created successfully!\n";
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
        Playlist addingTo = checkPlaylist(playlist);
        Song adding = checkSong(songName);

        if (addingTo != null && adding != null) {
            addingTo.addSong(adding);
            return "Song added successfully!\n";

        } else if (addingTo != null) {
            return "Sorry! It looks like we don't have that song in our library.\n";
        }

        return "It doesn't look like you have a playlist with that name.\n";
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
        Playlist removingFrom = checkPlaylist(playlist);
        Song removing = checkSong(songName);

        if (removingFrom == null) {
            return "It doesn't look like you have a playlist with that name.\n";
        }
        
        if (removing == null) {
            return "It doesn't look like that playlist has that song on it.\n";
        }

        removingFrom.removeSong(removing);
        return "Song removed successfully!\n";
    }
    public String favoriteSong(String title) {
    	/* This method adds a song to the favorites list.
    	 * Argument: String name of the song to favorite.
    	 * 
    	 * Returns: A printable string for if the song is not in the user library.
    	 * Returns, on successful favorite: A printable string reporting success.
    	 */
        Song favoriting = checkSong(title);
        if (favoriting != null) {
            favoriting.favorite();
            userFavorites.add(favoriting);
            return "Song favorited!";
        }

        return "Sorry! It looks like we don't have that song in our library.\n";
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
        Song song = checkSong(title);
        if (song != null) {
            song.setRating(rating);
            if (song.isFavorite()) userFavorites.add(song);
            return "Song rated!";
        }

        return "Sorry! It looks like we don't have that song in our library.\n";
    }
}
