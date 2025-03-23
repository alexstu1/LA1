package Model;

import java.util.*;

public class LibraryModel {
    private MusicStore store;
    private ArrayList<Song> songs;
    private ArrayList<Album> albums;
    private ArrayList<Playlist> playlists;

    public LibraryModel() {
        this.store = new MusicStore();
        this.songs = new ArrayList<Song>();
        this.albums = new ArrayList<Album>();
        this.playlists = new ArrayList<Playlist>();
        
               //Below adds the automaintained playlists
        	//THEY ARE ASSUMED TO BE THE FIRST FEW PLAYLISTS IN THE PLAYLISTS ARRAYLISTS
        this.playlists.add(new Playlist("Recently Played"));
        this.playlists.add(new Playlist("Most Played"));
        this.playlists.add(new Playlist("Highly Rated"));
    }

    private String buildSongOutput(ArrayList<Song> matches) {
        StringBuilder output = new StringBuilder("These are the songs that match your search:\n");
        for (Song song : matches) {
            output.append(song.toString());
        }

        return output.toString().trim();
    }

    private String buildAlbumOutput(ArrayList<Album> matches) {
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
        ArrayList<Album> matches = albumByTitleHelper(title);

        if (matches.isEmpty()) return "It doesn't look like that album is in your library.";
        return buildAlbumOutput(matches);
    }

    private ArrayList<Album> albumByTitleHelper(String title) {
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

    public String addSong(String name) {
        ArrayList<Song> matches = store.getSongForLibrary(name);
        if (matches.isEmpty()) return "Sorry! It looks like that song isn't in our library.";

        if (matches.size() > 1) return "There are multiple songs with that title in our library. Please specify the artist to ensure we add the correct one!";
        
        Song adding = matches.get(0);
        if (songs.contains(adding)) return "That song is already in your library.";

        songs.add(adding);
        return "Song added successfully!";
    }

    public String buildAddSongDupeString(String title) {
        // This method will build a string for the user to help them pick what artist
        // to specify when they try to add a song title that matches multiple songs
        ArrayList<Song> matches = store.getSongForLibrary(title);
        StringBuilder output = new StringBuilder("These are your options: ");
        for (Song song : matches) {
            output.append("[" + song.getArtist() + "] ");
        }
        return output.toString().trim();
    }

    public String addSong(String name, String artist) {
        ArrayList<Song> matches = store.getSongForLibrary(name);
        for (Song song : matches) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                if (songs.contains(song)) return "That song is already in your library.";
                songs.add(song);
                return "Song added successfully!";
            }
        }

        return "Sorry! It looks like that song isn't in our library.";
    }

    private void addSong(Song song) {
        if (!songs.contains(song)) songs.add(song);
    }

    public String addAlbum(String name) {
        ArrayList<Album> matches = store.getAlbumForLibrary(name);
        if (matches.isEmpty()) return "Sorry! It looks like that album isn't in our library.";

        if (matches.size() > 1) return "There are multiple albums with that title in our library. Please specify the artist to ensure we add the correct one!";

        Album adding = matches.get(0);
        if (albums.contains(adding)) return "That album is already in your library.";

        albums.add(adding);
        for (Song song : adding.getTracks()) {
            addSong(song);
        }
        
        return "Album added successfully!";
    }

    public String buildAddAlbumDupeString(String title) {
        // This method will build a string for the user to help them pick what artist
        // to specify when they try to add an album title that matches multiple albums
        ArrayList<Album> matches = store.getAlbumForLibrary(title);
        StringBuilder output = new StringBuilder("These are your options: ");
        for (Album album : matches) {
            output.append("[" + album.getArtist() + "] ");
        }
        return output.toString().trim();
    }

    public String addAlbum(String name, String artist) {
        ArrayList<Album> matches = store.getAlbumForLibrary(name);
        for (Album album : matches) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                if (albums.contains(album)) return "That album is already in your library.";
                albums.add(album);
                for (Song song : album.getTracks()) {
                    addSong(song);
                }
                
                return "Album added successfully!";
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
        HashSet<String> artists = new HashSet<String>();
        for (Song song : songs) {
            artists.add(song.getArtist());
        }

        return artists;
    }

    public String getAlbums() {
    	/*This method gets all albums in the user's library.
    	 * 
    	 * Returns: A printable string with all album names in the user's library on their own line.
    	 */
        if (albums.isEmpty()) return "Your library has no albums currently. Add some to get started!";

        StringBuilder output = new StringBuilder();
        output.append("These are all of the albums in your library:\n");
        for (Album album : albums) {
            output.append(album.getTitle() + " - " + album.getArtist() + "\n");
        }

        return output.toString().trim();
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
                return "Song rated!";
            }
        }

        return "To rate a song, it must be in your library.";
    }

    //ALL METHODS BELOW THIS LINE SIMPLY FORWARD THE METHODS FROM MUSICSTORE FOR SEARCHING PURPOSES

    public String getSongByTitleStore(String title) {
        return store.getSongByTitle(title);
    }

    public String getSongByArtistStore(String artist) {
        return store.getSongByArtist(artist);
    }

    public String getAlbumByTitleStore(String title) {
        return store.getAlbumByTitle(title);
    }

    public String getAlbumByArtistStore(String artist) {
        return store.getAlbumByArtist(artist);
    }
    public String getSongsSortedByTitle() {
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
    	Collections.shuffle(songs);
    }
    public String getContainedAlbums() {
    	if (songs.size()==0) return "Your library is empty. Please add songs to get started!\n";
    	String toReturn = "Songs from the following albums are present in the library:\n";
    	ArrayList<String> toAdd = new ArrayList<String>();
    	for (Song song : songs) {
    		if(!toAdd.contains(song.getAlbum())) {
    			toAdd.add(song.getAlbum());
    			toReturn = toReturn+song.getAlbum()+"\n";
    		}
    	}
    	return toReturn;
    }
    public String getSongByGenre(String genreSearch) {
    	if (songs.size()==0) return "Your library is empty. Please add songs to get started!\n";
    	String toReturn = "";
    	for (Song song : songs) {
    		if (song.getGenre().toLowerCase()==genreSearch.toLowerCase()) {
    			toReturn = toReturn + song.toString();
    		}
    	}
    	if (toReturn =="")
    		return "There are no songs that match that genre in your library.\n";
    	return "The following songs are in the genre "+genreSearch+":\n"+toReturn;
    }
        /*This didnt implement recent songs as a playlist implementation changed
     * public String getRecentSongs() {
    	ArrayList<Song> sortedByTime = new ArrayList<Song>();
    	for (Song song : songs) {
    		if (sortedByTime.size()==0) sortedByTime.add(song);
    		if (song.getWhenPlayed()==0) continue;
    		boolean added = false;
    		for (int i=0;i<sortedByTime.size();i++) {
    			if (sortedByTime.get(i).getWhenPlayed()<=song.getWhenPlayed()) {
    				sortedByTime.add(i, new Song(song));
    				added = true;
    				break;
    			}
    		}
    		if(!added) {
    			sortedByTime.add(song);
    		}
    	}
    	if(sortedByTime.size()==0) {
    		return "No songs have been played yet, play some songs to get recents";
    	}
    	int max = 10;
    	if (sortedByTime.size()<10) {
    		max=sortedByTime.size();
    	}
    	String toReturn = "The following songs have been recently played:\n";
    	for (int i = 0;i<max;i++) {
    		toReturn = toReturn + sortedByTime.get(i).toString();
    	}
    	return toReturn;
    }*/
    public void updateRecents(Song played) {
    	Playlist recents = this.playlists.get(0);
    	if (!recents.contains(new Song(played))) {
    		recents.addSong(0,new Song(played));
    	}
    	if (recents.getSize()==11) {
    		recents.removeSong(11);
    	}
    }

    public void updateMostPlayed() {
    	ArrayList<Song> toUpdate = new ArrayList<Song>();
    	toUpdate.add(new Song(songs.get(0)));
    	for (Song song: songs) {
    		if (song.getTimesPlayed()>toUpdate.getLast().getTimesPlayed()) {
    			for (int idx = 0;idx<toUpdate.size();idx++) {
    				if(toUpdate.get(idx).getTimesPlayed()<song.getTimesPlayed()) {
    					toUpdate.add(idx, new Song(song));
    				}
    				if (toUpdate.size()==11) toUpdate.removeLast();
    			}
    		}
    	}
    	Playlist newPlaylist = new Playlist("Most Played");
    	for (Song song: toUpdate) {
    		newPlaylist.addSong(new Song(song));
    	}
    	playlists.remove(1);
    	playlists.add(1, newPlaylist);
    }
    public void updateHighRating() {
    	ArrayList<Song> toUpdate = new ArrayList<Song>();
    	for (Song song: songs) {
    		if (song.isRated()&&(song.getRating()>=4)) {
    			toUpdate.add(new Song(song));
    		}
    	}
    	Playlist newPlaylist = new Playlist("Most Played");
    	for (Song song: toUpdate) {
    		newPlaylist.addSong(new Song(song));
    	}
    	playlists.remove(2);
    	playlists.add(2, newPlaylist);
    }

    public void autoMakeGenrePlaylists() {
    	HashMap<String,ArrayList<Song>> genreCounts = new HashMap<String,ArrayList<Song>>();
    	for (Song song: songs) {
    		String genre = song.getGenre();
    		if (!genreCounts.containsKey(genre)){
    			genreCounts.put(genre, new ArrayList<Song>());
    		}
    			genreCounts.get(genre).add(new Song(song));

    	}
    	for(String genre : genreCounts.keySet()) {
    		if(genreCounts.get(genre).size()>=10) {
    			//MAKE GENRE PLAYLIST HERE
    			Playlist newPlaylist = new Playlist(genre);
    			for(Song song : genreCounts.get(genre)) {
    				newPlaylist.addSong(song);
    			}
    			playlists.add(3, newPlaylist);
    		}
    	}
    }
}
