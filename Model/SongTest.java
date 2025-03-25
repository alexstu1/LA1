package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongTest {
    // Coverage Achieved: 100%
    private Song song;
    private Song songCopy;

    @BeforeEach
    void setUp() {
        song = new Song("LA1", "Eric and Alex", "CSC 335","study",2025);
        songCopy = new Song(song);
    }

    @Test
    void testConstructors() {
        assertEquals("LA1", song.getTitle());
        assertEquals("Eric and Alex", song.getArtist());
        assertEquals("CSC 335", song.getGenre());
        assertEquals("study", song.getAlbum());
        assertEquals(2025, song.getYear());
        assertFalse(song.isRated());
        assertFalse(song.isFavorite());
    }
    
    @Test
    void testCopyConstructor() {
        assertEquals(song.getTitle(), songCopy.getTitle());
        assertEquals(song.getArtist(), songCopy.getArtist());
        assertEquals(song.getAlbum(), songCopy.getAlbum());
        assertEquals(song.getGenre(), songCopy.getGenre());
        assertEquals(song.getYear(), songCopy.getYear());
        assertFalse(songCopy.isRated());
        assertFalse(songCopy.isFavorite());
    }
    
    @Test
    void testSetRating() {
        song.setRating(3);
        assertTrue(song.isRated());
        assertEquals(3, song.getRating());
        assertFalse(song.isFavorite());
        
        song.setRating(5);
        assertEquals(5, song.getRating());
        assertTrue(song.isFavorite());
        
        song.setRating(6);
        assertEquals(5, song.getRating());
        
        song.setRating(0);
        assertEquals(1, song.getRating());
    }
    
    @Test
    void testFavorite() {
        assertFalse(song.isFavorite());
        song.favorite();
        assertTrue(song.isFavorite());
        song.unfavorite();
        assertFalse(song.isFavorite());
    }
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEquals() {
        assertTrue(song.equals(songCopy));
        assertTrue(song.equals(song));
        Song differentSong = new Song("LA2", "Aric and Elex", "CSC 336","otherStudy",2024);
        assertFalse(song.equals(differentSong));
        assertFalse(song.equals(null));
        assertFalse(song.equals("Not a song"));
    }
    
    @Test
    void testToString() {
        String expected = "Eric and Alex - LA1 | Genre: CSC 335 | Appears on: study\n";
        assertEquals(expected, song.toString());
        
        song.setRating(4);
        expected = "Eric and Alex - LA1 | Genre: CSC 335 | Appears on: study | You Rated: 4/5\n";
        assertEquals(expected, song.toString());
    }
    @Test
    void testPlay() {
    	String expected = "Now playing: Eric and Alex - LA1 | Genre: CSC 335 | Appears on: study | Plays: 1";
    	assertEquals(expected,song.play());
    	
    }
}
