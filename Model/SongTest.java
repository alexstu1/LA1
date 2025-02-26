package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SongTest {
    Song test = new Song("The Color Violet", "Tory Lanez", "Alone At Prom");

    @Test
    void testGets() {
        assertEquals(test.getTitle(), "The Color Violet");
        assertEquals(test.getArtist(), "Tory Lanez");
        assertEquals(test.getAlbum(), "Alone At Prom");
    }

    @Test
    void testRating() {
        assertFalse(test.isRated());

        test.setRating(3);
        assertTrue(test.isRated());
        assertEquals(test.getRating(), 3);

        test.setRating(-3);
        assertTrue(test.isRated());
        assertEquals(test.getRating(), 0);

        test.setRating(10);
        assertTrue(test.isRated());
        assertTrue(test.isFavorite());
        assertEquals(test.getRating(), 5);
    }
    
    @Test
    void testFavorite() {
        assertFalse(test.isFavorite());

        test.favorite();
        assertTrue(test.isFavorite());

        test.unfavorite();
        assertFalse(test.isFavorite());
    }

    @Test
    void testString() {
        assertEquals(test.toString(), "Tory Lanez - The Color Violet | Appears on: Alone At Prom");
    }
}
