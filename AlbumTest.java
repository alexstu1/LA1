package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AlbumTest {
    Album test = new Album("19_Adele.txt");

    @Test
    void testGets() {
        assertEquals(test.getTitle(), "19");
        assertEquals(test.getArtist(), "Adele");
        assertEquals(test.getYear(), 2008);
        assertEquals(test.getGenre(), "Pop");
        assertEquals(test.getTracks(), "Daydreamer,Best for Last,Chasing Pavements,Cold Shoulder,Crazy for You,Melt My Heart to Stone,First Love,Right as Rain,Make You Feel My Love,My Same,Tired,Hometown Glory");
    }
}
