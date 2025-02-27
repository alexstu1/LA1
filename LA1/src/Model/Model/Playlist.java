package Model;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
    	/*This object represents a play list created by the user in the view.
    	 * 
    	 */
        this.name = name;
        this.songs = new ArrayList<Song>();
    }

    public String getName() {
        return name;
    }

    public String getSongs() {
    	/* Returns, if empty: An empty string.
    	 * Returns: A string of each name of the song, separated by commas.
    	 */
        if (songs.size() == 0) return "";

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < songs.size() - 1; i++) {
            output.append(songs.get(i).getTitle()).append(",");
        }

        output.append(songs.get(songs.size() - 1).getTitle());
        return output.toString();
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

    @Override
    public String toString() {
    	/* Returns, if play list empty: A printable string reporting the empty status.
    	 * Returns: A String of all songs with their associated tracks,
    	 *  with each song on its own line.
    	 */
        StringBuilder output = new StringBuilder();
        output.append("Playlist: ").append(name).append("\n");
        if (songs.size() == 0) {
            output.append("You dont currently have any tracks on this playlist. Add some to get started!\n");
            return output.toString();
        }

        output.append("Track List:\n");
        for (int i = 0; i < songs.size(); i++) {
            output.append(String.format("   %d. %s\n", i + 1, songs.get(i).toString()));
        }

        return output.toString();
    }
}
