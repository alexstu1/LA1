package Model;

import java.util.ArrayList;

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

    public int countSong(String title) {
        /* Returns the number of songs on the playlist with a title matching
         * the provided title string
         */
        ArrayList<Song> matches = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) matches.add(song);
        }

        return matches.size();
    }

    public boolean hasSong(Song song) {
        if (songs.contains(song)) return true;

        return false;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
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
            output.append(String.format("   %d. %s\n", i + 1, songs.get(i).toString()));
        }

        return output.toString();
    }
}