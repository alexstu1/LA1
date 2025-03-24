package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
    	/* This object represents a play list created by the user in the view.
    	 * 
    	 */
        this.name = name;
        this.songs = new ArrayList<Song>();
    }

    public String getName() {
        return name;
    }

    public boolean hasSong(Song song) {
        if (songs.contains(song)) return true;

        return false;
    }

    public void addSong(Song song) {
        //adds song at end of playlist
        songs.add(song);
    }

    public void addSong(int index, Song song) {
        //adds song at provided index, start of playlist is index 0.
        songs.add(index, song);
    }

    public void removeSong(int index) {
    	songs.remove(index);
    }

    public int getSize() {
    	return songs.size();
    }
    
    public Song getSong(int index) {
        return new Song(songs.get(index));
    }

    public boolean contains(Song toCheck) {
        //returns true if song in playlist and false if not
    	for (Song song : songs) {
    		if (song.equals(toCheck)) return true;
    	} return false;
    }

    public ArrayList<Song> getMatches(String title) {
        // Returns a deep-copy array containing all songs
        // in the playlist with a name matching the specified name
        ArrayList<Song> matches = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) matches.add(new Song(song));
        }

        return matches;
    }

    public void removeSong(Song song) {
        removeSong(song.getTitle(), song.getArtist());
    }

    public void removeSong(String title) {
        //removes the song of a given title.
        Song removing = null;
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                removing = song;
                break;
            }
        }

        songs.remove(removing);
    }
    

    public boolean removeSong(String title, String artist) {
        /* This method will check for a song by its artist and title
         * in the case that the playlist has multiple songs of the same
         * name, and remove it
         */
        Boolean found = false;
        Song removing = null;
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title) && song.getArtist().equalsIgnoreCase(artist)) {
                found = true;
                removing = song;
                break;
            }
        }

        if (!found) return false;

        songs.remove(removing);
        return true;
    }

    public void shuffle() {
        //randomizes the order of songs in the playlist
    	Collections.shuffle(songs);
    }

    @Override
    public String toString() {
    	/* Returns, if play list empty: A printable string reporting the empty status.
    	 * Returns: A String of all songs with their associated tracks,
    	 *  with each song on its own line.
    	 */
        if (name.equals("Recently Played") || name.equals("Most Played")) return topTenString();
        
        StringBuilder output = new StringBuilder();
        output.append("Playlist: ").append(name).append("\n");
        if (songs.size() == 0) {
            output.append("You dont currently have any tracks on this playlist. Add some to get started!");
            return output.toString();
        }

        output.append("Track List:\n");
        for (int i = 0; i < songs.size(); i++) {
            output.append(String.format("   %d. %s", i + 1, songs.get(i).toString()));
        }

        return output.toString();
    }

    private String topTenString() {
        // This is a special method that toString will call if the playlist
        // being printed is only meant to show the user the top 10 songs on it
        StringBuilder output = new StringBuilder();
        output.append("Playlist: ").append(name).append("\n");
        if (songs.size() == 0) {
            output.append("You dont currently have any tracks on this playlist. Add some to get started!");
            return output.toString();
        }

        int size = (songs.size() < 10) ? songs.size() : 10;
        output.append("Track List:\n");
        for (int i = 0; i < size; i++) {
            output.append(String.format("   %d. %s", i + 1, songs.get(i).toString()));
        }

        return output.toString();
    }
}
