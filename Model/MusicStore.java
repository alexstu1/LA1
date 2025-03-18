package Model;

import java.util.*;
import java.io.*;

public class MusicStore {

    private ArrayList<Song> songs;
    private ArrayList<Album> albums;


    public MusicStore() {
    	/* This class creates both the store of all available songs and albums
    	 * The albums for the music store must be stored in ./albums/albums.txt
    	 * Each line of albums.txt must be album name, then artist name, separated by a comma.
    	 */
        this.songs = new ArrayList<Song>();
        this.albums = new ArrayList<Album>();

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
                        songs.add(currSong);
                    }
                    albums.add(currAlbum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } else { 
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
                        songs.add(currSong);
                    }
                    albums.add(currAlbum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        /* This method searches the store for songs with a title that
         * matches the specified title string.
         * Argument: String of a song title.
         * Returns: A printable string containing the information of any songs that match the title.
         */
        ArrayList<Song> matches = songByTitleHelper(title);

        if (matches.isEmpty()) return "Sorry! It doesn't look like that song is in our library.";
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
        /* This method searches the store for songs by an artist that
         * matches the specified artist string.
         * Argument: String of an artist.
         * Returns: A printable string containing the information of any songs by the artist.
         */
        ArrayList<Song> matches = songByArtistHelper(artist);

        if (matches.isEmpty()) return "Sorry! It doesn't look like that song is in our library.";
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
        /* This method searches the store for albums with a title that
         * matches the specified title string.
         * Argument: String of an album title.
         * Returns: A printable string containing the information of any albums that match the title.
         */
        ArrayList<Album> matches = albumByTitleHelper(title);

        if (matches.isEmpty()) return "Sorry! It doesn't look like that album is in our library.";
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
        /* This method searches the store for albums by an artist that
         * matches the specified artist string.
         * Argument: String of an artist.
         * Returns: A printable string containing the information of any albums by the artist.
         */
        ArrayList<Album> matches = albumByArtistHelper(artist);

        if (matches.isEmpty()) return "Sorry! It doesn't like we have any albums by that artist in our library.";
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

    public ArrayList<Song> getSongForLibrary(String title) {
        // Creates a deep-copy of the array containing any songs
        // in the store that match the given title
        ArrayList<Song> matches = songByTitleHelper(title);
        ArrayList<Song> copy = new ArrayList<Song>();

        for (Song song : matches) {
            copy.add(new Song(song));
        }

        return copy;
    }

    public ArrayList<Album> getAlbumForLibrary(String title) {
        // Creates a deep-copy of the array containing any albums
        // in the store that match the given title
        ArrayList<Album> matches = albumByTitleHelper(title);
        ArrayList<Album> copy = new ArrayList<Album>();

        for (Album album : matches) {
            copy.add(new Album(album));
        }

        return copy;
    }	
}
