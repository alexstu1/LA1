package Model;

import java.io.*;
import java.util.*;

public class Album {
    private String title;
    private String artist;
    private String genre;
    private int year;
    private ArrayList<Song> songList;

    public Album(File filename) {
    	/* Argument: A File object of the file name of the file that stores the data for the album.
    	 * 
    	 * This class represents an album.
    	 * It can only represent albums given in a text file in the following format:
    	 * 
    	 * first line: comma separated values of "title,artist,genre,year"
    	 * A song name on each remaining line of the file.
    	 */
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String[] infoLine = fileReader.readLine().split(",");
            this.title = infoLine[0];
            this.artist = infoLine[1];
            this.genre = infoLine[2];
            this.year = Integer.parseInt(infoLine[3]);
            this.songList = new ArrayList<Song>();

            String line;
            while ((line = fileReader.readLine()) != null) {
                Song track = new Song(line, artist, genre, title);
                songList.add(track);  
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Album (BufferedReader br) {
    	try {
	    	String[] infoLine = br.readLine().split(",");
	        this.title = infoLine[0];
	        this.artist = infoLine[1];
	        this.genre = infoLine[2];
	        this.year = Integer.parseInt(infoLine[3]);
	        this.songList = new ArrayList<Song>();
	
	        String line;
	        while ((line = br.readLine()) != null) {
	            Song track = new Song(line, artist, genre, title);
	            songList.add(track);  
        	}
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Album(Album album) {
        // Copy constructor
        this.title = album.getTitle();
        this.artist = album.getArtist();
        this.genre = album.getGenre();
        this.year = album.getYear();
        this.songList = album.getTracks();
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }
    
    public int getYear() {
        return year;
    }

    public ArrayList<Song> getTracks() {
    	//Returns a deep-copy of the songsList
        ArrayList<Song> copy = new ArrayList<Song>();
        for (Song song : songList) {
            copy.add(new Song(song));
        }

        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        if (this == o) return true;

        if (!(o instanceof Album)) return false;

        Album other = (Album) o;
        return this.title.equals(other.getTitle()) && this.artist.equals(other.getArtist());
    }

    @Override
    public String toString() {
    	/*Returns a String in the following format with items in single quotes being the actual value:
    	 * Album: 'album's name' ('year')
    	 * Artist: 'artist's name'
    	 * Genre: 'genre'
    	 * Track List:
    	 * 'Each song in order'
    	 */
        StringBuilder output = new StringBuilder();
        output.append("Album: ").append(title).append(" (").append(year).append(")\n");
        output.append("Artist: ").append(artist).append("\n");
        output.append("Genre: ").append(genre).append("\n");
        output.append("Track List:\n");
        for (int i = 0; i < songList.size(); i++) {
            output.append(String.format("   %d. %s\n", i + 1, songList.get(i).getTitle()));
        }

        return output.toString();
    }
}
