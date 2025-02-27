package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

public class AlbumTest {
	File file = new File(".\\albums\\19_Adele.txt");
    Album test = new Album(file);

    @Test
    void testGets() {
        assertEquals(test.getTitle(), "19");
        assertEquals(test.getArtist(), "Adele");
        assertEquals(test.getYear(), 2008);
        assertEquals(test.getGenre(), "Pop");
        assertEquals(test.getTracks(), "[Adele - Daydreamer | Appears on: 19\n"
        								+ ", Adele - Best for Last | Appears on: 19\n"
                      					+ ", Adele - Chasing Pavements | Appears on: 19\n"
                                        + ", Adele - Cold Shoulder | Appears on: 19\n"
                                        + ", Adele - Crazy for You | Appears on: 19\n"
                                        + ", Adele - Melt My Heart to Stone | Appears on: 19\n"
                                        + ", Adele - First Love | Appears on: 19\n"
                                        + ", Adele - Right as Rain | Appears on: 19\n"
                                        + ", Adele - Make You Feel My Love | Appears on: 19\n"
                                        + ", Adele - My Same | Appears on: 19\n"
                                        + ", Adele - Tired | Appears on: 19\n"
                                        + ", Adele - Hometown Glory | Appears on: 19]");
                                        
    }

    @Test
    void testString() {
        assertEquals(test.toString(), "Album: 19 (2008)\nArtist: Adele\nGenre: Pop\nTrack List:\n   1. Daydreamer\n   2. Best for Last\n   3. Chasing Pavements\n" +
        "   4. Cold Shoulder\n   5. Crazy for You\n   6. Melt My Heart to Stone\n   7. First Love\n   8. Right as Rain\n   9. Make You Feel My Love\n   10. My Same\n" +
        "   11. Tired\n   12. Hometown Glory\n");
    }
}
