package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class PlaylistTest {
    // Coverage Achieved: 96%
    private Playlist playlist;
    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("Test");
        song1 = new Song("Song 1", "Eric", "LA1");
        song2 = new Song("Song 2", "Alex", "LA1");
    }

    @Test
    void testConstructor() {
        assertEquals("Test", playlist.getName());
        assertFalse(playlist.hasSong(song1));
    }

    @Test
    void testAddSong() {
        playlist.addSong(song1);
        assertTrue(playlist.hasSong(song1));
    }

    @Test
    void testHasSong() {
        assertFalse(playlist.hasSong(song1));
        playlist.addSong(song1);
        assertTrue(playlist.hasSong(song1));
    }

    @Test
    void testGetMatches() {
        playlist.addSong(song1);
        playlist.addSong(new Song("Song 1", "Not Eric", "Not LA1"));
        
        ArrayList<Song> matches = playlist.getMatches("Song 1");
        assertEquals(2, matches.size());
        assertEquals("Song 1", matches.get(0).getTitle());
    }

    @Test
    void testRemoveSongByTitle() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.removeSong("Song 1");
        assertFalse(playlist.hasSong(song1));
        assertTrue(playlist.hasSong(song2));
    }

    @Test
    void testRemoveSongByTitleAndArtist() {
        Song song1Dupe = new Song("Song 1", "Not Eric", "LA1");
        playlist.addSong(song1);
        playlist.addSong(song1Dupe);
        playlist.addSong(song2);
        
        assertTrue(playlist.removeSong("Song 1", "Eric"));
        assertFalse(playlist.hasSong(song1));
        assertTrue(playlist.hasSong(song1Dupe));
        
        assertFalse(playlist.removeSong("Nonexistent Song", "Unknown Artist"));
    }

    @Test
    void testToString() {
        String expected = "Playlist: Test\n" +
                          "You dont currently have any tracks on this playlist. Add some to get started!";
        assertTrue(playlist.toString().equals(expected));
        
        playlist.addSong(song1);
        String output = playlist.toString();
        expected = "Playlist: Test\n" +
                          "Track List:\n" +
                          "   1. Eric - Song 1 | Appears on: LA1\n";
                        
        assertTrue(output.equals(expected));
    }
}
