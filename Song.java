package Model;

public class Song {
    private String title;
    private String artist;
    private String albumName;
    private boolean isRated;
    private int rating;
    private boolean isFavorite;

    public Song(String title, String artist, String albumName) {
        this.title = title;
        this.artist = artist;
        this.albumName = albumName;
        this.isRated = false;
        this.isFavorite = false;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return albumName;
    }

    public boolean isRated() {
        return isRated;
    }
    
    public int getRating() {
        return rating;
    }

    public void setRating(int userRating) {
        isRated = true;
        if (userRating < 0) rating = 0;
        else if (userRating > 5) rating = 5;
        else rating = userRating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void favorite() {
        isFavorite = true;
    }

    public void unfavorite() {
        isFavorite = false;
    }

    @Override
    public String toString() {
        return String.format("%s - %s | Appears on: %s", artist, title, albumName);
    }
}
