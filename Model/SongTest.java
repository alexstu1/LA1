package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongTest {
    private Song song;
    private Song songCopy;

    @BeforeEach
    void setUp() {
        song = new Song("LA1", "Eric and Alex", "CSC 335");
        songCopy = new Song(song);
    }

    @Test
    void testConstructors() {
        assertEquals("LA1", song.getTitle());
        assertEquals("Eric and Alex", song.getArtist());
        assertEquals("CSC 335", song.getAlbum());
        assertFalse(song.isRated());
        assertFalse(song.isFavorite());
    }
    
    @Test
    void testCopyConstructor() {
        assertEquals(song.getTitle(), songCopy.getTitle());
        assertEquals(song.getArtist(), songCopy.getArtist());
        assertEquals(song.getAlbum(), songCopy.getAlbum());
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
        
        Song differentSong = new Song("LA2", "Aric and Elex", "CSC 336");
        assertFalse(song.equals(differentSong));
        assertFalse(song.equals(null));
        assertFalse(song.equals("Not a song"));
    }
    
    @Test
    void testToString() {
        String expected = "Eric and Alex - LA1 | Appears on: CSC 335\n";
        assertEquals(expected, song.toString());
        
        song.setRating(4);
        expected = "Eric and Alex - LA1 | Appears on: CSC 335 | You Rated: 4/5\n";
        assertEquals(expected, song.toString());
    }
}