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
        songs.add(song);
    }
    public void addSong(int index,Song song) {
        songs.add(index, song);
    }
    public void removeSong(int index) {
    	songs.remove(index);
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

    public void removeSong(String title) {
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
        /*	This is only for shuffling without permanent changes, likely delete later
     * public String getShuffled() {
        StringBuilder output = new StringBuilder();
        output.append("Playlist: ").append(name).append("\n");
        if (songs.size() == 0) {
            output.append("You dont currently have any tracks on this playlist. Add some to get started!");
            return output.toString();
        }

        output.append("Track List:\n");
        ArrayList<Song> copied = new ArrayList<Song>();
        for (Song song : songs) {
        	copied.add(new Song(song));
        }
        Collections.shuffle(copied);
        for (int i = 0; i < copied.size(); i++) {
            output.append(String.format("   %d. %s", i + 1, copied.get(i).toString()));
        }
        
        return output.toString();
    }*/
    public void shuffle() {
    	Collections.shuffle(songs);
    }

    @Override
    public String toString() {
    	/* Returns, if play list empty: A printable string reporting the empty status.
    	 * Returns: A String of all songs with their associated tracks,
    	 *  with each song on its own line.
    	 */
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
}
