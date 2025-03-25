package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class PlaylistTest {
    // Coverage Achieved: 97.3%
    private Playlist playlist;
    private Song song1;
    private Song song2;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("Test");
        song1 = new Song("Song 1", "Eric", "LA1","test",2025);
        song2 = new Song("Song 2", "Alex", "LA1","test 2", 2024);
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
        playlist.addSong(new Song("Song 1", "Not Eric", "Not LA1","not test1", 2000));
        
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
        Song song1Dupe = new Song("Song 1", "Not Eric", "LA1","test 3", 2023);
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
                          "   1. Eric - Song 1 | Genre: LA1 | Appears on: test\n";
        assertTrue(output.equals(expected));
    }
    @Test
    void testAddSongIntSong() {
    	 playlist.addSong(0,song1);
         assertTrue(playlist.hasSong(song1));
    }
    @Test
    void testRemoveSongInt() {
    	playlist.addSong(0,song1);
    	playlist.removeSong(0);
    	 assertFalse(playlist.hasSong(song1));
    }
    @Test
    void testGetSize() {
    	assertTrue(0==playlist.getSize());
    	playlist.addSong(song1);
    	assertTrue(1==playlist.getSize());
    	playlist.addSong(song2);
    	assertTrue(2==playlist.getSize());
    }
    @Test 
    void testGetSong() {
    	 playlist.addSong(0,song1);
    	 assertEquals(song1,playlist.getSong(0));
    }
    @Test
    void testContains() {
    	playlist.addSong(song1);
    	assertTrue(playlist.contains(song1));
    	assertFalse(playlist.contains(song2));
    }
    @Test
    void testRemoveSongSong() {
    	playlist.addSong(0,song1);
    	playlist.removeSong(0);
    	assertFalse(playlist.contains(song1));
    }
    @Test
    void testRemoveSongString() {
    	playlist.addSong(0,song1);
    	playlist.removeSong("Song 1");
    	assertFalse(playlist.contains(song1));
    }
    @Test
    void testShuffle() {
    	playlist.addSong(0,song1);
    	playlist.addSong(1,song2);
    	boolean shuffled=false;
    	playlist.shuffle();
    	for (int i=0;i<10;i++) {
    		playlist.shuffle();
    		if (playlist.getSong(0).equals(song2)) {
    			shuffled=true;
    			break;
    		}
    	}
    	assertTrue(shuffled);
    }
    @Test
    void testTopTenString() {
    	Playlist newPlaylist = new Playlist("Most Played");
    	String expected = "Playlist: Most Played\n"+
    			"You dont currently have any tracks on this playlist. Add some to get started!";
    	assertEquals(expected,newPlaylist.toString());
    	newPlaylist.addSong(song1);
    	expected = 	"Playlist: Most Played\n"+
    				"Track List:\n"+
    				"   1. Eric - Song 1 | Genre: LA1 | Appears on: test\n";
    	assertEquals(expected,newPlaylist.toString());
    	
    	

    }
    
}
