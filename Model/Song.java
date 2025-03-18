package Model;

public class Song {
    private String title;
    private String artist;
    private String albumName;
    private boolean isRated;
    private int rating;
    private boolean isFavorite;
    private int timesPlayed;
    private String genre;
    private long playedWhen;

    public Song(String title, String artist, String genre, String albumName) {
    	/* This object represents a song on an album.
    	 * Constructor Arguments;
    	 * 		title: String name of the song.
    	 * 		artist: String name of the artist.
    	 * 		albumName: String name of the album the song is from.
    	 */
        this.title = title;
        this.artist = artist;
        this.albumName = albumName;
        this.genre = genre;
        this.timesPlayed = 0;
        this.isRated = false;
        this.isFavorite = false;
        this.playedWhen = 0;
    }

    public Song(Song song) {
        /* Copy constructor
         */
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.albumName = song.getAlbum();
        this.genre = song.getGenre();
        this.timesPlayed = song.getTimesPlayed();
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

    public String getGenre() {
        return genre;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public long getWhenPlayed() {
        return playedWhen;
    }

    public String play() {
        timesPlayed++;
        playedWhen = System.currentTimeMillis();
        return "Now playing: " + this.toString();
    }

    public void setRating(int userRating) {
    	/*	This method allows the user to set the rating of a song.
    	 * 	Argument: The integer rating of the song.
    	 * 		If the rating is above 5 it will be set to 5.
    	 * 		If the rating is below 1 it will be set to 1.
    	 * 
    	 * 		If the rating is 5 it will also favorite the song.
    	 */
        isRated = true;
        if (userRating < 1) rating = 1;
        else if (userRating >= 5) {
            rating = 5;
            this.favorite();
        }

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
    public boolean equals(Object o) {
        if (o == null) return false;

        if (this == o) return true;

        if (!(o instanceof Song)) return false;

        Song other = (Song) o;
        return this.title.equals(other.getTitle()) && this.artist.equals(other.getArtist());
    }

    @Override
    public String toString() {
    	/* This method creates a string representation of the song, meant to be printed out to the user.
    	 */
        StringBuilder output = new StringBuilder();
        output.append(String.format("%s - %s | Genre: %s | Appears on: %s", artist, title, genre, albumName));
        if (isRated) {
            output.append(String.format(" | You Rated: %d/5", rating));
        }

        output.append("\n");
        return output.toString();
    }
}