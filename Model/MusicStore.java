package Model;

import java.util.*;
import java.io.*;

public class MusicStore {

    private ArrayList<Song> storeSongs;
    private ArrayList<Album> storeAlbums;
    private HashSet<String> storeArtists;
    private ArrayList<Song> userSongs;
    private ArrayList<Album> userAlbums;
    private ArrayList<Playlist> userPlaylists;
    private HashSet<Song> userFavorites;


    public MusicStore() {
        this.storeSongs = new ArrayList<Song>();
        this.storeAlbums = new ArrayList<Album>();
        this.storeArtists = new HashSet<String>();
        this.userSongs = new ArrayList<Song>();
        this.userAlbums = new ArrayList<Album>();
        this.userPlaylists = new ArrayList<Playlist>();
        this.userFavorites = new HashSet<Song>();

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
                        storeSongs.add(currSong);
                        storeArtists.add(currSong.getArtist());
                    }
                    storeAlbums.add(currAlbum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Song checkSong(String title) {
        for (Song song : storeSongs) {
            if (song.getTitle().toLowerCase().equals(title)) {
                return song;
            }
        }
        
        return null;
    }

    private Album checkAlbum(String title) {
        for (Album album : storeAlbums) {
            if (album.getTitle().toLowerCase().equals(title)) {
                return album;
            }
        }

        return null;
    }

    private Playlist checkPlaylist(String name) {
        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().toLowerCase().equals(name)) {
                return playlist;
            }
        }

        return null;
    }

    public String songSearchByTitleStore(String title) {
        StringBuilder matches = new StringBuilder();
        for (Song song : storeSongs) {
            if (song.getTitle().toLowerCase().equals(title)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return "Sorry! It looks like we don't have that song in our library.\n";
        }

        return matches.toString();
    }

    public String songSearchByTitleUser(String title) {
        if (userSongs.isEmpty()) return "Your library has no songs currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Song song : userSongs) {
            if (song.getTitle().toLowerCase().equals(title)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return "It doesn't look like you've added that song to your library.\n";
        }

        return matches.toString();
    }

    public String songSearchByArtistStore(String artist) {
        StringBuilder matches = new StringBuilder();
        for (Song song : storeSongs) {
            if (song.getArtist().toLowerCase().equals(artist)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("Sorry! It looks like we don't have any songs by %s in our library.\n", artist);
        }

        return matches.toString();
    }

    public String songSearchByArtistUser(String artist) {
        if (userSongs.isEmpty()) return "Your library has no songs currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Song song : userSongs) {
            if (song.getArtist().toLowerCase().equals(artist)) {
                matches.append(song.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("It doesn't look like you've added any songs by %s to your library yet.\n", artist);
        }

        return matches.toString();
    }

    public String albumSearchByTitleStore(String title) {
        StringBuilder matches = new StringBuilder();
        for (Album album : storeAlbums) {
            if (album.getTitle().toLowerCase().equals(title)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return "Sorry! It looks like we don't have that album in our library.\n";
        }

        return matches.toString();
    }

    public String albumSearchByTitleUser(String title) {
        if (userAlbums.isEmpty()) return "Your library has no albums currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Album album : userAlbums) {
            if (album.getTitle().toLowerCase().equals(title)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return "It doesn't look like you've added that album to your library yet.\n";
        }

        return matches.toString();
    }

    public String albumSearchByArtistStore(String artist) {
        StringBuilder matches = new StringBuilder();
        for (Album album : storeAlbums) {
            if (album.getArtist().toLowerCase().equals(artist)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("Sorry! It looks like we don't have any albums by %s in our library.\n", artist);
        }

        return matches.toString();
    }

    public String albumSearchByArtistUser(String artist) {
        if (userAlbums.isEmpty()) return "Your library has no albums currently. Add some to get started!\n";

        StringBuilder matches = new StringBuilder();
        for (Album album : userAlbums) {
            if (album.getArtist().toLowerCase().equals(artist)) {
                matches.append(album.toString());
            }
        }

        if (matches.isEmpty()) {
            return String.format("It doesn't look like you've added any albums by %s to your library yet.\n", artist);
        }

        return matches.toString();
    }

    public String searchPlaylist(String name) {
        if (userPlaylists.isEmpty()) return "You don't have any playlists in your library yet. Make one to get started!\n";

        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().toLowerCase().equals(name)) {
                return playlist.toString();
            }
        }

        return "It doesn't look like there's a playlist in your library with that name.\n";
    }

    public String addSongToUser(String title) {
        Song adding = checkSong(title);

        if (adding == null) {
            return "Sorry! It looks like we don't have that song in our library.\n";
        }

        if (!userSongs.contains(adding)) {
            userSongs.add(adding);
            return "Song added successfully!\n";
        }

        return "That song is already in your library.\n";
    }

    public String addAlbumToUser(String title) {
        Album adding = checkAlbum(title);

        if (adding == null) {
            return "Sorry! It looks like we don't have that album in our library.\n";
        }

        if (!userAlbums.contains(adding)) {
            userAlbums.add(adding);
            for (Song song : adding.getTracks()) {
                if (!userSongs.contains(song)) {
                    userSongs.add(song);
                }
            }

            return "Album added successfully!\n";
        }

        return "That album is already in your library.\n";
    }

    public String getSongTitles() {
        StringBuilder output = new StringBuilder();
        output.append("These are all of the songs in our library:\n");
        for (Song song : storeSongs) {
            output.append(song.toString());
        }
        
        return output.toString();
    }

    public String getArtists() {
        StringBuilder output = new StringBuilder();
        output.append("These are all of the artists in our library:\n");
        for (String artist : storeArtists) {
            output.append(artist + "\n");
        }

        return output.toString();
    }

    public String getAlbums() {
        StringBuilder output = new StringBuilder();
        output.append("These are all of the albums in our library:\n");
        for (Album album : storeAlbums) {
            output.append(album.getTitle() + "\n");
        }

        return output.toString();
    }

    public String getPlaylists() {
        if (userPlaylists.isEmpty()) return "There are no playlists in your library.\n";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your playlists:\n");
        for (Playlist playlist : userPlaylists) {
            output.append(playlist.getName() + "\n");
        }

        return output.toString();
    }

    public String getFavoriteSongs() {
        if (userFavorites.isEmpty()) return "You haven't favorited any songs yet.\n";

        StringBuilder output = new StringBuilder();
        output.append("These are all of your favorite songs:\n");
        for (Song song : userFavorites) {
            output.append(song.toString());
        }

        return output.toString();
    }

    public String makePlaylist(String name) {
        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().toLowerCase().equals(name)) {
                return "You already have a playlist with that name.\n";
            }
        }

        Playlist newPlaylist = new Playlist(name);
        userPlaylists.add(newPlaylist);
        return "Playlist created successfully!\n";
    }

    public String addToPlaylist(String playlist, String songName) {
        Playlist addingTo = checkPlaylist(playlist);
        Song adding = checkSong(songName);

        if (addingTo != null && adding != null) {
            addingTo.addSong(adding);
            return "Song added successfully!\n";

        } else if (addingTo != null) {
            return "Sorry! It looks like we don't have that song in our library.\n";
        }

        return "It doesn't look like you have a playlist with that name.\n";
    }

    public String removeFromPlaylist(String playlist, String songName) {
        Playlist removingFrom = checkPlaylist(playlist);
        Song removing = checkSong(songName);

        if (removingFrom == null) {
            return "It doesn't look like you have a playlist with that name.\n";
        }
        
        if (removing == null) {
            return "It doesn't look like that playlist has that song on it.\n";
        }

        removingFrom.removeSong(removing);
        return "Song removed successfully!\n";
    }

    public String favoriteSong(String title) {
        Song favoriting = checkSong(title);
        if (favoriting != null) {
            favoriting.favorite();
            userFavorites.add(favoriting);
            return "Song favorited!";
        }

        return "Sorry! It looks like we don't have that song in our library.\n";
    }

    public String rateSong(String title, int rating) {
        Song song = checkSong(title);
        if (song != null) {
            song.setRating(rating);
            if (song.isFavorite()) userFavorites.add(song);
            return "Song rated!";
        }

        return "Sorry! It looks like we don't have that song in our library.\n";
    }
}
