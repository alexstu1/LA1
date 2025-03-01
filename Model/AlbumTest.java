package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AlbumTest {
    Album test = new Album("C:\\Users\\emant\\OneDrive\\Desktop\\CSC 335\\LA 1\\albums\\19_Adele.txt");

    @Test
    void testGets() {
        assertEquals(test.getTitle(), "19");
        assertEquals(test.getArtist(), "Adele");
        assertEquals(test.getYear(), 2008);
        assertEquals(test.getGenre(), "Pop");
        assertEquals(test.getTracks(), "Daydreamer,Best for Last,Chasing Pavements,Cold Shoulder,Crazy for You,Melt My Heart to Stone,First Love,Right as Rain,Make You Feel My Love,My Same,Tired,Hometown Glory");
    }

    @Test
    void testString() {
        assertEquals(test.toString(), "Album: 19 (2008)\nArtist: Adele\nGenre: Pop\nTrack List:\n   1. Daydreamer\n   2. Best for Last\n   3. Chasing Pavements\n" +
        "   4. Cold Shoulder\n   5. Crazy for You\n   6. Melt My Heart to Stone\n   7. First Love\n   8. Right as Rain\n   9. Make You Feel My Love\n   10. My Same\n" +
        "   11. Tired\n   12. Hometown Glory\n");
    }
}
