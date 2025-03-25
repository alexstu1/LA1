package Model;

import java.util.*;

public class LibraryModel {
    private ArrayList<Song> songs;
    private ArrayList<Album> albums;
    private ArrayList<Playlist> playlists;

    public LibraryModel() {
        this.songs = new ArrayList<Song>();
        this.albums = new ArrayList<Album>();
        this.playlists = new ArrayList<Playlist>();
        
        //Below adds the automaintained playlists
        //THEY ARE ASSUMED TO BE THE FIRST FEW PLAYLISTS IN THE PLAYLISTS ARRAYLISTS
        this.playlists.add(new Playlist("Recently Played"));
        this.playlists.add(new Playlist("Most Played"));
        this.playlists.add(new Playlist("Top Rated"));
        this.playlists.add(new Playlist("Favorites"));
    }

    private String buildSongOutput(ArrayList<Song> matches) {
        /* This method is used to create a printable search string from a list of found songs.
         * Argument:
         *     matches: An arraylist of Songs that are to be printed out.
         * Returns: A string meant to be printed to the user of the list of songs.
         */
        StringBuilder output = new StringBuilder("These are the songs that match your search:\n");
        for (Song song : matches) {
            output.append(song.toString());
        }

        return output.toString().trim();
    }

    private String buildAlbumOutput(ArrayList<Album> matches) {
         /* This method is used to create a printable search string from a list of found albums.
         * Argument:
         *     matches: An arraylist of Albums that are to be printed out.
         * Returns: A string meant to be printed to the user of the list of albums.
         */
        StringBuilder output = new StringBuilder("These are the albums that match your search:\n");
        for (Album album : matches) {
            output.append(album.toString());
        }

        return output.toString().trim();
    }

    public String getSongByTitle(String title) {
        /* This method searches the user's library for songs with a title that
         * matches the specified title string.
         * Argument: String of a song title.
         * Returns: A printable string containing the information of any songs that match the title.
         */
        ArrayList<Song> matches = songByTitleHelper(title);

        if (matches.isEmpty()) return "It doesn't look like that song is in your library.";
        return buildSongOutput(matches);
    }
    
    private ArrayList<Song> songByTitleHelper(String title) {
        /* This method searches the library for a songs name and returns all matches.
         * Argument:
         *     title: A string of the name of song to look for
         * Return: An arraylist of Songs that have the provided song name.
         */
        ArrayList<Song> matches = new ArrayList<Song>();

        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) matches.add(song);
        }

        return matches;
    }

    public String getSongByArtist(String artist) {
        /* This method searches the user's library for songs by an artist that
         * matches the specified artist string.
         * Argument: String of an artist.
         * Returns: A printable string containing the information of any songs by the artist.
         */
        ArrayList<Song> matches = songByArtistHelper(artist);

        if (matches.isEmpty()) return "It doesn't look like that song is in your library.";
        return buildSongOutput(matches);
    }
    
    private ArrayList<Song> songByArtistHelper(String artist) {
        /* This method searches the library for songs by a given artist and returns all matches.
         * Argument:
         *     artist: A string of the artist's name to look for
         * Return: An arraylist of Songs that have the provided artist name.
         */
        ArrayList<Song> matches = new ArrayList<Song>();

        for (Song song : songs) {
            if (song.getArtist().equalsIgnoreCase(artist)) matches.add(song);
        }

        return matches;
    }

    public String getAlbumByTitle(String title) {
        /* This method searches the user's library for albums with a title that
         * matches the specified title string.
         * Argument: String of an album title.
         * Returns: A printable string containing the information of any albums that match the title.
         */
        ArrayList<Album> albumMatches = albumByTitleHelper(title);
        if (!albumMatches.isEmpty()) return buildAlbumOutput(albumMatches);

        return "It looks like that album isn't in your library.";
    }

    private ArrayList<Album> albumByTitleHelper(String title) {
        /* This method searches the library for an album's name and returns all matches.
         * Argument:
         *     title: A string of the name of album to look for.
         * Return: An arraylist of Albums that have the provided album name.
         */
        ArrayList<Album> matches = new ArrayList<Album>();

        for (Album album : albums) {
            if (album.getTitle().equalsIgnoreCase(title)) matches.add(album);
        }

        return matches;
    }

    public String getAlbumByArtist(String artist) {
        /* This method searches the user's library for albums by an artist that
         * matches the specified artist string.
         * Argument: String of an artist.
         * Returns: A printable string containing the information of any albums by the artist.
         */
        ArrayList<Album> matches = albumByArtistHelper(artist);

        if (matches.isEmpty()) return "It doesn't like you have any albums by that artist in your library.";
        return buildAlbumOutput(matches);
    }

    private ArrayList<Album> albumByArtistHelper(String artist) {
        /* This method searches the library for an artist's name and returns all matches.
         * Argument:
         *     artist: A string of the name of the artist to look for.
         * Return: An arraylist of Albums that have the provided artist name.
         */
        ArrayList<Album> matches = new ArrayList<Album>();

        for (Album album : albums) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                matches.add(album);
            }
        }

        return matches;
    }
    
    public String getPlaylist(String name) {
    	/* This method searches for a play list in the user's library.
    	 * Argument: String name of the play list to search for.
    	 * 
    	 * Returns: A printable string for if there are no play lists, or if no play list of the given name exists.
    	 * Returns, on successful search: A string representation of the play list with the given name.
    	 */
        if (playlists.isEmpty()) return "You don't have any playlists in your library yet. Make one to get started!";

        Playlist match = getPlaylistHelper(name);
        if (match == null) return "It doesn't look like there's a playlist in your library with that name.";

        return "This is the playlist that matches your search:\n" + match.toString().trim();
    }

    private Playlist getPlaylistHelper(String name) {
    	/* This method converts a string play list name into its associated playlist object.
    	 * Argument: Takes a String argument of a play list name.
    	 * Returns: the play list with the provided name if it exists.
    	 * Returns: null if no play list of that name exists.
    	 * 
    	 * As play lists are modifiable, the play lists must only be accessible
    	 * through this instance's methods.
    	 */
        for (Playlist playlist : playlists) {
            if (playlist.getName().equalsIgnoreCase(name)) {
                return playlist;
            }
        }

        return null;
    }

    public String addSong(String name, MusicStore store) {
        /* This method adds a song to the user's library.
         * Arguments:
         *     name: String of the name of the song to add.
         *     store: A MusicStore object to look for the song in.
         * Return: A String informing the user of successful addition, multiple songs by that name, 
         *        if the song is already in the library, or if the song is not in the music store.
         */
        ArrayList<Song> matches = store.getSongForLibrary(name);
        if (matches.isEmpty()) return "Sorry! It looks like that song isn't in our library.";

        if (matches.size() > 1) return "There are multiple songs with that title in our library. Please specify the artist to ensure we add the correct one!";
        
        Song adding = matches.get(0);
        if (songs.contains(adding)) return "That song is already in your library.";

        songs.add(adding);
        makeAlbumFromSong(adding);
        autoMakeGenrePlaylists();
        return "Song added successfully!";
    }

    private void makeAlbumFromSong(Song song) {
        /* This method either makes a partial album from a song, or adds a song to a partial album
         * Userd to add partial albums to the user's library from only adding a song from that music store.
         *Argument:
         *    song: A song object to be added to the users library.
         *Returns: null
         */
        Album adding = new Album(song);
        int index = albums.indexOf(adding);
        if (index == -1) {
            albums.add(adding);
            adding.addSong(song);
            return;
        }

        albums.get(index).addSong(song);
    }

    public String buildAddSongDupeString(String title, MusicStore store) {
        // This method will build a string for the user to help them pick what artist
        // to specify when they try to add a song title that matches multiple songs
        ArrayList<Song> matches = store.getSongForLibrary(title);
        StringBuilder output = new StringBuilder("These are your options: ");
        for (Song song : matches) {
            output.append("[" + song.getArtist() + "] ");
        }
        return output.toString().trim();
    }

    public String addSong(String name, String artist, MusicStore store) {
        /* This method adds a song by a specific artist to the user's library.
         * Arguments:
         *     name: String of the name of the song to add.
         *     artist: String of the artis's name of the song to add.
         *     store: A MusicStore object to look for the song in.
         * Return: A String informing the user of successful addition,
         *        if the song is already in the library, or if the song is not in the music store.
         */
        ArrayList<Song> matches = store.getSongForLibrary(name);
        for (Song song : matches) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                if (songs.contains(song)) return "That song is already in your library.";
                songs.add(song);
                makeAlbumFromSong(song);
                autoMakeGenrePlaylists();
                return "Song added successfully!";
            }
        }

        return "Sorry! It looks like that song isn't in our library.";
    }

    private void addSong(Song song) {
        /* This method adds a song to the users library, but not the album the song is from.
         * Argument:
         *    song: A Song object to add to the library.
         * Returns: null
         */
        if (!songs.contains(song)) {
            songs.add(song);
            autoMakeGenrePlaylists();
        }
    }

    public String addAlbum(String name, MusicStore store) {
        /* This method adds an album to the user's library.
         * Arguments:
         *     name: String of the name of the album to add.
         *     store: A MusicStore object to look for the album in.
         * Return: A String informing the user of successful addition, multiple albums of the provided name,
         *        if the album is already in the library, or if the album is not in the music store.
         */
        ArrayList<Album> matches = store.getAlbumForLibrary(name);
        if (matches.isEmpty()) return "Sorry! It looks like that album isn't in our library.";

        if (matches.size() > 1) return "There are multiple albums with that title in our library. Please specify the artist to ensure we add the correct one!";

        Album adding = matches.get(0);
        int index = albums.indexOf(adding);
        if (index != -1 && albums.get(index).getTracks().size() == adding.getTracks().size()) return "That album is already in your library.";

        albums.remove(adding);
        albums.add(adding);
        for (Song song : adding.getTracks()) {
            addSong(song);
        }
        
        return "Full album added successfully!";
    }

    public String buildAddAlbumDupeString(String title, MusicStore store) {
        // This method will build a string for the user to help them pick what artist
        // to specify when they try to add an album title that matches multiple albums
        ArrayList<Album> matches = store.getAlbumForLibrary(title);
        StringBuilder output = new StringBuilder("These are your options: ");
        for (Album album : matches) {
            output.append("[" + album.getArtist() + "] ");
        }
        return output.toString().trim();
    }

    public String addAlbum(String name, String artist, MusicStore store) {
        
        /* This method adds an album by an artist to the user's library.
         * Arguments:
         *     name: String of the name of the album to add.
         *     artist: String of the artis's name of the album.
         *     store: A MusicStore object to look for the album in.
         * Return: A String informing the user of successful addition, multiple albums of the provided name,
         *        if the album is already in the library, or if the album is not in the music store.
         */
        ArrayList<Album> matches = store.getAlbumForLibrary(name);
        for (Album album : matches) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                int index = albums.indexOf(album);
                if (index != -1 && albums.get(index).getTracks().size() == album.getTracks().size()) return "That album is already in your library.";
                albums.remove(album);
                albums.add(album);
                for (Song song : album.getTracks()) {
                    addSong(song);
                }
                
                return "Full album added successfully!";
            }
        }

        return "Sorry! It looks like that album isn't in our library.";
    }

    public String getSongs() {
    	/* This method gets all songs in the user's library.
    	 * 
    	 * Returns: A printable string representing all songs in the user's library.
    	 */
        if (songs.isEmpty()) return "Your library has no songs currently. Add some to get started!";

        StringBuilder output = new StringBuilder();
        output.append("These are all of the songs in your library:\n");
        for (Song song : songs) {
            output.append(song.toString());
        }
        
        return output.toString().trim();
    }

    public String getArtists() {
    	/* This method gets all artists who have songs in the user's library.
    	 * 
    	 * Returns: A printable string with all artists in the user's library on their own line.
    	 */
        if (songs.isEmpty()) return "Your library is empty currently. Add some songs or albums to get started!";

        HashSet<String> artists = getArtistsHelper();

        StringBuilder output = new StringBuilder();
        output.append("These are all of the artists in your library:\n");
        for (String artist : artists) {
            output.append(artist + "\n");
        }

        return output.toString().trim();
    }

    private HashSet<String> getArtistsHelper() {
        /* This method gets all artist names of the songs in the user library
         * Argument: none
         * Returns: A HashSet of Strings of the artist's names who have songs in the user's library.
         */
        HashSet<String> artists = new HashSet<String>();
        for (Song song : songs) {
            artists.add(song.getArtist());
        }

        return artists;
    }

    public String getPlaylists() {
    	/* This method gets all play lists in the user's library.
    	 * 
    	 * Returns: A printable string with all play list names on their own line.
    	 */
        if (playlists.isEmpty()) return "There are no playlists in your library. Make one to get started!";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your playlists:\n");
        for (Playlist playlist : playlists) {
            output.append(playlist.getName() + "\n");
        }

        return output.toString().trim();
    }

    public String getFavorites() {
    	/* This method gets all songs that the user has favorited or rated five stars.
    	 * 
    	 * Returns: A printable string of all favorited songs. 
    	 */
        ArrayList<Song> favorites = getFavoritesHelper();
        if (favorites.isEmpty()) return "You haven't favorited any songs yet.";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your favorite songs:\n");
        for (Song song : favorites) {
            output.append(song.toString());
        }

        return output.toString().trim();
    }

    private ArrayList<Song> getFavoritesHelper() {
        /* This method gets all songs in the user library that have been favorited or rated five stars.
         * Argument: none
         * Returns: An ArrayList of Songs in the user's library that have been favorited or rated five stars.
         */
        ArrayList<Song> favorites = new ArrayList<Song>();

        for (Song song : songs) {
            if (song.isFavorite()) favorites.add(song);
        }

        return favorites;
    }

    public String makePlaylist(String name) {
    	/* This method creates a play list.
    	 * Arguments: String name of the play list to create.
    	 * 
    	 * Returns: A printable string for if a play list with the given name already exists.
    	 * Returns, on successful creation: A printable string reporting success.
    	 */
        for (Playlist playlist : playlists) {
            if (playlist.getName().equalsIgnoreCase(name)) {
                return "You already have a playlist with that name.";
            }
        }

        Playlist newPlaylist = new Playlist(name);
        playlists.add(newPlaylist);
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
        Playlist addingTo = getPlaylistHelper(playlist);
        if (addingTo == null) return "It doesn't look like you have a playlist with that name.";

        ArrayList<Song> matches = songByTitleHelper(songName);
        if (matches.isEmpty()) return "To add a song to a playlist, that song must be in your library first.";

        if (matches.size() > 1) return "There are multiple songs with that title in your library, please specify the artist to ensure the correct one is added.";

        Song adding = matches.get(0);
        if (addingTo.hasSong(adding)) return "It looks like that song is already on your playlist.";

        addingTo.addSong(adding);
        return "Song added successfully!";
    }

    public String buildAddToPlaylistDupeString(String title) {
        // This method will build a string for the user to help them pick what artist
        // to specify when they try to add a song name that matches multiple songs
        ArrayList<Song> matches = songByTitleHelper(title);
        StringBuilder output = new StringBuilder("These are your options: ");
        for (Song song : matches) {
            output.append("[" + song.getArtist() + "] ");
        }
        return output.toString().trim();
    }

    public String addToPlaylist(String playlist, String songName, String artist) {
        /* This method will handle adding a song to a playlist if there are multiple songs
         * of the same name.
         * Arguments:
         *      playlist: The string name of the playlist to add to
         *      songName: The string name of the song to add to the playlist
         *      artist: The string name of the song's artist
         */
        Playlist addingTo = getPlaylistHelper(playlist);
        ArrayList<Song> matches = songByTitleHelper(songName);

        for (Song song : matches) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                if (addingTo.hasSong(song)) return "It looks like that song is already on your playlist.";
                addingTo.addSong(song);
                return "Song added successfully!";
            }
        }

        return "To add a song to a playlist, that song must be in your library first.";
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
        Playlist removingFrom = getPlaylistHelper(playlist);
        if (removingFrom == null) return "It doesn't look like you have a playlist with that name.";

        ArrayList<Song> matches = removingFrom.getMatches(songName);
        if (matches.isEmpty()) return "It doesn't look like that playlist has that song on it.";
        
        if (matches.size() == 1) {
            removingFrom.removeSong(songName);
            return "Song removed successfully!";
        }

        return "There are multiple songs on that playlist with that name. Please specify the artist to ensure the correct one is removed.";
    }

    public String buildRemoveFromPlaylistDupeString(String playlist, String songName) {
        // This method will build a string for the user to help them pick
        // what artist to specify when they try to remove a song name from a playlist
        // that has multiple songs with that name
        Playlist removingFrom = getPlaylistHelper(playlist);
        ArrayList<Song> matches = removingFrom.getMatches(songName);
        StringBuilder output = new StringBuilder("These are your options: ");
        for (Song song : matches) {
            output.append("[" + song.getArtist() + "] ");
        }
        return output.toString().trim();
    }

    public String removeFromPlaylist(String playlist, String songName, String artist) {
        /* This method will handle removing a song from a playlist
         * in the case that the playlist has multiple songs with the specified name.
         * Arguments:
         * 		playlist: The string name of the play list to remove from.
    	 * 		songName: The string name of the song to remove.
         *      artist: The string name of the song's artist.
         * 
         * Returns: A printable string for if the
    	 * song is not on that play list.
    	 * Returns, on successful removal: A printable string reporting success.
    	 */
        Playlist removingFrom = getPlaylistHelper(playlist);
        boolean removed = removingFrom.removeSong(songName, artist);
        if (!removed) return "It doesn't look like that playlist has that song on it.";

        return "Song removed successfully!";
    }

    public String favoriteSong(String title) {
    	/* This method adds a song to the favorites list.
    	 * Argument: String name of the song to favorite.
    	 * 
    	 * Returns: A printable string for if the song is not in the store library.
    	 * Returns, on successful favorite: A printable string reporting success.
    	 */
        ArrayList<Song> matches = songByTitleHelper(title);
        if (matches.isEmpty()) return "To favorite a song, it must be in your library.";

        if (matches.size() == 1) {
            matches.get(0).favorite();
            updateFavorites(matches.get(0));
            return "Song favorited!";
        }

        return "There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is favorited.";

    }

    public String buildFavoriteRateSongDupeString(String title) {
        // This method will build a string for the user to help them pick
        // what artist to specify when they trying to favorite a song in their library
        // that has multiple songs with that name
        ArrayList<Song> matches = songByTitleHelper(title);
        StringBuilder output = new StringBuilder("These are your options: ");
        for (Song song : matches) {
            output.append("[" + song.getArtist() + "] ");
        }
        return output.toString().trim();
    }

    public String favoriteSong(String title, String artist) {
        /* This method will handle favoriting a song in the case that multiple songs in the user's
         * library share the specified name.
         * Arguments:
         *      title: The string name of the song to favorite
         *      artist: The string name of the song's artist
         * 
         * Returns: A printable string for if the song is not in the user's library.
         * Returns, on successful favorite: A printable string reporting success.
         */
        ArrayList<Song> matches = songByTitleHelper(title);
        for (Song song : matches) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                song.favorite();
                updateFavorites(song);
                return "Song favorited!";
            }
        }

        return "To favorite a song, it must be in your library.";
    }

    public String rateSong(String title, int rating) {
    	/* This method rates a song and adds to favorites if rated 5.
    	 * Arguments:
    	 * 		title: The string name of the song to rate.
    	 * 		rating: The integer rating to rate the song. 
    	 * If rating is below 0 it will be set to 0.
    	 * If rating is above 5 it will be set to 5.
    	 * 
    	 * Returns: A printable string for if the song is not in the user's library.
    	 * Returns, on successful rating: A printable string reporting success.
    	 */
        ArrayList<Song> matches = songByTitleHelper(title);
        if (matches.isEmpty()) return "To rate a song, it must be in your library.";

        if (matches.size() > 1) return "There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is rated.";

        matches.get(0).setRating(rating);
        if (rating >= 4) updateHighRating(matches.get(0));
        if (rating == 5) updateFavorites(matches.get(0));
        return "Song rated!";
    }

    public String rateSong(String title, int rating, String artist) {
        /* This method handles rating a song if multiple songs in the user's library share
         * the specified name.
         * Arguments:
         *      title: The string name of the song to rate
         *      rating: The integer rating to give the sogn
         *      artist: The string name of the song's artist
         * 
         * Returns: A printable string for if the song is not in the user's library.
         * Returns, on successful rating: A printable string reporting success.
         */
        ArrayList<Song> matches = songByTitleHelper(title);
        for (Song song : matches) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                song.setRating(rating);
                if (rating >= 4) updateHighRating(song);
                if (rating == 5) updateFavorites(song);
                return "Song rated!";
            }
        }

        return "To rate a song, it must be in your library.";
    }

    public String getSongsSortedByTitle() {
        /* This method sorts all songs in the user's library and returns
         * a printable string representation of all songs sorted alphabetically by title.
         * Argument: none
         * Returns: A String representation of all songs in the user library sorted alphabetically by title.
         */
    	String toReturn = "";
    	ArrayList<Song> sorting = new ArrayList<Song>();
    	for (Song toSort : songs) {
    		if (sorting.size()==0)	sorting.add(toSort);
    		boolean added = false;
    		for (int i=0;i<sorting.size();i++) {
    			int compared = sorting.get(i).getTitle().compareTo(toSort.getTitle());
    			if (compared>=0) {
    				sorting.add(i, toSort);
    				added =true;
    				break;
    			}
    		}
    		if (!added) sorting.add(toSort);
    	}
    	for (Song toBuild : sorting) {
    		toReturn=toReturn + toBuild.toString();
    	}
    	return toReturn;
    }

    public String getSongsSortedByArtist() {
        /* This method sorts all songs in the user's library and returns
         * a printable string representation of all songs sorted alphabetically by artist name.
         * Argument: none
         * Returns: A String representation of all songs in the user library sorted alphabetically by artist name.
         */
    	String toReturn = "";
    	ArrayList<Song> sorting = new ArrayList<Song>();
    	for (Song toSort : songs) {
    		if (sorting.size()==0)	sorting.add(toSort);
    		boolean added = false;
    		for (int i=0;i<sorting.size();i++) {
    			int compared = sorting.get(i).getArtist().compareTo(toSort.getArtist());
    			if (compared>=0) {
    				sorting.add(i, toSort);
    				added =true;
    				break;
    			}
    		}
    		if (!added) sorting.add(toSort);
    	}
    	for (Song toBuild : sorting) {
    		toReturn=toReturn + toBuild.toString();
    	}
    	return toReturn;
    }

    public String getSongsSortedByRating() {
        /* This method sorts all songs in the user's library and returns
         * a printable string representation of all songs sorted by song rating.
         * Argument: none
         * Returns: A String representation of all songs in the user library sorted by song rating.
         */
    	String toReturn = "";
    	ArrayList<Song> sorting = new ArrayList<Song>();
    	for (Song toSort : songs) {
    		if (sorting.size()==0)	sorting.add(toSort);
    		boolean added = false;
    		for (int i=0;i<sorting.size();i++) {
    			Boolean compared = sorting.get(i).getRating()<=toSort.getRating();
    			if (compared) {
    				sorting.add(i, toSort);
    				added =true;
    				break;
    			}
    		}
    		if (!added) sorting.add(toSort);
    	}
    	System.out.println(sorting.size());
    	for (Song toBuild : sorting) {
    		toReturn=toReturn + toBuild.toString();
    	}
    	return toReturn;
    }

    public void shuffle() {
        //Randomizes the order of all songs saved in the user library
    	Collections.shuffle(songs);
    }

    public String getSongByGenre(String genre) {
        /* This method returns a string representation of all songs in the user library that have the given genre.
         * Arguments:
         *    genre: A String of the genre to search for.
         * Returns: A printable String informing the user if the library is empty, could not find any, or all songs 
         *     of the given genre.
         */
        if (songs.isEmpty()) return "Your library is empty. Add something to get started!";

        ArrayList<Song> matches = getSongByGenreHelper(genre);
        return buildSongOutput(matches);
    }

    private ArrayList<Song> getSongByGenreHelper(String genre) {
        /* This helper method builds an arraylist of songs of all songs in the user library
         *     that matches the given genre
         * Argument:
         *    genre: A String of the genre to search for.
         * Returns: An ArrayList of Songs of the songs that have the given genre from the user library.
         */
        ArrayList<Song> matches = new ArrayList<Song>();

        for (Song song : songs) {
            if (song.getGenre().equalsIgnoreCase(genre)) matches.add(song);
        }

        return matches;
    }

    private void updateRecents(Song played) {
        /* This method updates a playlist that tracks the 10 most recently played songs, without duplication.
         *     The provided song is the song the user listened to.
         * Argument:
         *    played: A Song object that the user listened to.
         * Returns: null
         */
    	Playlist recents = this.playlists.get(0);
    	if (recents.contains(played)) {
    		recents.removeSong(played);
    	}

        recents.addSong(0, played);
    }

    private void updateMostPlayed(Song song) {
        /* This method takes a song and updates it in the playlist that tracks the 10 most played song, if it belongs.
         * Argument:
         *    song: A Song that is to be updated in the most played playlist
         * Returns: null
         */
        Playlist mostPlayed = playlists.get(1);
        mostPlayed.removeSong(song);
        if (mostPlayed.getSize() == 0) {
            mostPlayed.addSong(song);
            return;
        }

        int i = mostPlayed.getSize() - 1;
        while (i >= 0 && song.getTimesPlayed() >= mostPlayed.getSong(i).getTimesPlayed()) {
            i--;
        }

        mostPlayed.addSong(i + 1, song);
    }

    private void updateHighRating(Song song) {
        //This method adds the provided song to the High Rating playlist if not already added.
        if (!playlists.get(2).hasSong(song)) playlists.get(2).addSong(song);
    }

    private void updateFavorites(Song song) {
        //This method adds the provided song to the favorites playlit if not already added.
        if (!playlists.get(3).hasSong(song)) playlists.get(3).addSong(song);
    }

    private void autoMakeGenrePlaylists() {
        /* This method looks through the user's library and makes a playlist for any genre that has atleast 
         * 10 songs in the user library with that genre and stores the playlists in the user library.
         * Argument: none
         * Returns: null
         */
    	HashMap<String,ArrayList<Song>> genreCounts = new HashMap<String,ArrayList<Song>>();
    	for (Song song : songs) {
    		String genre = song.getGenre();
    		genreCounts.putIfAbsent(genre, new ArrayList<Song>());
            genreCounts.get(genre).add(song);
    	}

    	for(String genre : genreCounts.keySet()) {
    		if(genreCounts.get(genre).size()>=10) {
    			//MAKE GENRE PLAYLIST HERE
    			Playlist newPlaylist = new Playlist(genre);
    			for(Song song : genreCounts.get(genre)) {
    				newPlaylist.addSong(song);
    			}

    			boolean modified=false;
    			for (int index=0;index<playlists.size();index++) {
    				if(playlists.get(index).getName().equals(genre)) {
    					playlists.remove(index);
    					playlists.add(index,newPlaylist);
    					modified=true;
    					break;
    				}
    			}

    			if (!modified) playlists.add(4, newPlaylist);
    		}
    	}
    }

    public String play(String song) {
        /* This method is used for the user to say they played a song and perform the required auto playlist features.
         * Argument:
         *    song: A String of the song's name.
         * Returns: A printable String informing the user of the song not in the user library, multiple songs by that name, or successful play.
         */
        ArrayList<Song> matches = songByTitleHelper(song);

        if (matches.isEmpty()) return "It doesn't look like that song is in your library.";
        if (matches.size() > 1) return "There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is played.";
        updateRecents(matches.get(0));
        updateMostPlayed(matches.get(0));
        return matches.get(0).play();
    }

    public String play(String songName, String artist) {
        /* This method is used for the user to say they played a song by a given artist and perform the required auto playlist features.
         * Argument:
         *    song: A String of the song's name.
         *    artist: A String of the song's artist.
         * Returns: A printable String informing the user of the song not in the user library, or successful play.
         */
        ArrayList<Song> matches = songByTitleHelper(songName);

        for (Song song : matches) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                updateRecents(song);
                updateMostPlayed(song);
                return song.play();
            }
        }

        return "It doesn't look like that song is in your library.";
    }

    public String removeSongLibrary(String songName) {
        /* This method delets a song form the users library and any of its playlists.
         * Argument:
         *     songName: A String of the song to deletes name.
         * Returns: A printable string informing the user of; no song by name, multiple songs with that name, or successful deletion.
         */
        ArrayList<Song> matches = songByTitleHelper(songName);

        if (matches.isEmpty()) return "It doesn't look like that song is in your library.";
        if (matches.size() > 1) return "There are multiple songs in your library with that name. Please specify the artist to ensure we remove the correct one.";
        deepDelete(matches.get(0));
        return "Song removed successfully!";
    }

    public String removeSongLibrary(String songName, String artist) {
         /* This method delets a song by a given artist form the users library and any of its playlists.
         * Argument:
         *     songName: A String of the song to deletes name.
         *     artist: A String of the song's artist.
         * Returns: A printable string informing the user of; no song by name and artist, or successful deletion.
         */
        ArrayList<Song> matches = songByTitleHelper(songName);

        for (int i = matches.size() - 1; i >= 0; i--) {
            Song song = matches.get(i);
            if (song.getTitle().equalsIgnoreCase(songName) && song.getArtist().equalsIgnoreCase(artist)) {
                deepDelete(song);
                return "Song removed successfully!";
            }
        }

        return "It doesn't look like that song is in your library.";
    }

    public String removeAlbumLibrary(String albumName) {
         /* This method delets an album and all of its songs form the users library and any of its playlists.
         * Argument:
         *     albumName: A String of the album to deletes name.
         * Returns: A printable string informing the user of; no album by name, multiple albums with that name, or successful deletion.
         */
        ArrayList<Album> matches = albumByTitleHelper(albumName);
        if (matches.isEmpty()) return "It doesn't look like that album is in your library.";
        if (matches.size() > 1) return "There are multiple albums in your library with that title. Please specify the artist to ensure the right one is removed.";

        Album removing = matches.get(0);
        albums.remove(removing);
        for (Song song : removing.getTracks()) {
            deepDelete(song);
        }

        return "Album removed successfully!";
    }

    private void deepDelete(Song song) {
        /* This method deletes a song from the users library,
         *   and if it was the last song from an album it removes the album as well.
         * Argument:
         *    song: A Song object that is to be deleted.
         * Returns: null
         */
    	songs.remove(song);
    	for (Playlist playlist : playlists) {
    		playlist.removeSong(song);
    	}

        Album fromSong = new Album(song);
        int index = albums.indexOf(fromSong);
        if (index != -1) albums.get(index).removeSong(song);
    }

    public void shufflePlaylist(String playlistName) {
        /* This method ranomizes the order of the songs in the given playlist.
         * Argument:
         *    playlistName: A String of the playlists name to shuffle
         * Returns: null
         */
        Playlist playlist = getPlaylistHelper(playlistName);
        if (playlist != null) playlist.shuffle();
    }

    private String buildAlbumInformationString(Album album) {
        /* This method builds and returns information of the given album,
         *   and if all songs from that album are in the library or not.
         * Argument:
         *    album: An Album object to get the information from
         * Returns: A printable String of the album's information and presence of songs in the users library.
         */
        StringBuilder output = new StringBuilder();
        output.append("Album: ").append(album.getTitle()).append(" (").append(album.getYear()).append(") - In Library\n");
        output.append("Artist: ").append(album.getArtist()).append("\n");
        output.append("Genre: ").append(album.getGenre()).append("\n");
        output.append("Track List:\n");
        ArrayList<Song> tracks = album.getTracks();
        for (int i = 0; i < tracks.size(); i++) {
            output.append(String.format("   %d. %s", i + 1, tracks.get(i).getTitle()));
            if (songs.contains(tracks.get(i))) output.append(" - In library\n");
            else output.append(" - Not in library\n");
        }

        return output.toString();
    }

    private Album getAlbumFromSong(String songName, String artist, MusicStore store) {
        /* This method gets the full album that the provided songName by artist from the provided musicStore.
         * Arguments:
         *    songName: A String of the song's title.
         *    artist: A String of the song's artist.
         *    store: A MusicStore object to search for the album.
         * Returns: An Album object containing a song of provided title and artist, or null if no such album exists.
         */
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(songName) && song.getArtist().equalsIgnoreCase(artist)) {
                return store.getAlbumForLibrary(song.getAlbum()).get(0);
            }
        }

        return null;
    }

    public String getAlbumInformation(String songName, String artist, MusicStore store) {
        /* This method takes a songName and artist name, finds the album its from,
         *     and returns a printable string representing that album that informs the user
         *     what songs from that album are in the library.
         * Arguments:
         *    songName: A String of the song's title.
         *    artist: A String of the song's artist.
         *    store: A MusicStore object to search for the album.
         * Returns: A printable String of the album's information and presence of songs in the users library.
         */
        Album album = getAlbumFromSong(songName, artist, store);
        return buildAlbumInformationString(album);
    }

    public String getAlbums() {
        /* This method gets all albums in the user's library.
    	 * 
    	 * Returns: A printable string with all album names in the user's library on their own line.
    	 */
        if (songs.isEmpty()) return "Your library has no songs currently. Add some to get started!";
        HashSet<String> albumSet = new HashSet<String>();
        for (Album album : albums) {
            albumSet.add(album.getTitle() + " - " + album.getArtist() + "\n");
        }
        for (Song song : songs) {
            albumSet.add(song.getAlbum() + " - " + song.getArtist() + "\n");
        }

        StringBuilder output = new StringBuilder();
        output.append("These are all of the albums in your library:\n");
        for (String album : albumSet) {
            output.append(album);
        }

        return output.toString().trim();
    }

    //ALL METHODS BELOW THIS LINE SIMPLY FORWARD THE METHODS FROM MUSICSTORE FOR SEARCHING PURPOSES

    public String getSongByTitleStore(String title, MusicStore store) {
        return store.getSongByTitle(title);
    }

    public String getSongByArtistStore(String artist, MusicStore store) {
        return store.getSongByArtist(artist);
    }

    public String getAlbumByTitleStore(String title, MusicStore store) {
        return store.getAlbumByTitle(title);
    }

    public String getAlbumByArtistStore(String artist, MusicStore store) {
        return store.getAlbumByArtist(artist);
    }
}
