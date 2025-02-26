package Model;

import java.io.*;
import java.util.*;

public class Album {
    private String title;
    private String artist;
    private String genre;
    private int year;
    private ArrayList<Song> songList;

    public Album(String filename) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String[] infoLine = fileReader.readLine().split(",");
            this.title = infoLine[0];
            this.artist = infoLine[1];
            this.genre = infoLine[2];
            this.year = Integer.parseInt(infoLine[3]);
            this.songList = new ArrayList<Song>();

            String line;
            while ((line = fileReader.readLine()) != null) {
                Song track = new Song(line, artist, title);
                songList.add(track);  
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getTracks() {
        if (songList.size() == 0) return "";
        
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < songList.size() - 1; i++) {
            output.append(songList.get(i).getTitle()).append(",");
        }

        output.append(songList.get(songList.size() - 1).getTitle());
        return output.toString();
    }

    @Override
    public String toString() {
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