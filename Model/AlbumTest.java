package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;

class AlbumTest {
    // Coverage Achieved: 94%
    private Album album;
    private Album albumCopy;
    private File testFile;
    
    @BeforeEach
    void setUp() throws IOException {
        testFile = File.createTempFile("testAlbum", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("LA1,Eric and Alex,Computer Science,2025\n");
            writer.write("A\n");
            writer.write("B\n");
            writer.write("C\n");
        }
        album = new Album(testFile);

    }

    @Test
    void testConstructorFromFile() {
        assertEquals("LA1", album.getTitle());
        assertEquals("Eric and Alex", album.getArtist());
        assertEquals("Computer Science", album.getGenre());
        assertEquals(2025, album.getYear());
        assertEquals(3, album.getTracks().size());
    }

    @Test
    void testConstructorFromBufferedReader() throws FileNotFoundException {
        BufferedReader fileReader = new BufferedReader(new FileReader(testFile));
        Album brAlbum = new Album(fileReader);
        assertEquals("LA1", brAlbum.getTitle());
        assertEquals("Eric and Alex", brAlbum.getArtist());
        assertEquals("Computer Science", brAlbum.getGenre());
        assertEquals(2025, brAlbum.getYear());
        assertEquals(3, brAlbum.getTracks().size());
    }
    
    @Test
    void testCopyConstructor() {
        albumCopy = new Album(album);
        assertEquals(album.getTitle(), albumCopy.getTitle());
        assertEquals(album.getArtist(), albumCopy.getArtist());
        assertEquals(album.getGenre(), albumCopy.getGenre());
        assertEquals(album.getYear(), albumCopy.getYear());
        assertEquals(album.getTracks(), albumCopy.getTracks());
    }
    
    @Test
    void testGetTracksDeepCopy() {
        ArrayList<Song> copiedTracks = album.getTracks();
        copiedTracks.get(0).setRating(5);
        assertNotEquals(copiedTracks.get(0).getRating(), album.getTracks().get(0).getRating());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEquals() {
        Album albumCopy = new Album(album);
        assertTrue(album.equals(albumCopy));
        
        Album differentAlbum = new Album(testFile) {
            // Album has no setter methods, so override getTitle
            // for this object to ensure it returns a different name
            // without needing to make a new file
            @Override
            public String getTitle() { return "Different Album"; }
        };
        assertFalse(album.equals(differentAlbum));
        assertFalse(album.equals(null));
        assertFalse(album.equals("Not an album"));
    }
    
    @Test
    void testToString() {
        String expected = "Album: LA1 (2025)\n" +
                          "Artist: Eric and Alex\n" +
                          "Genre: Computer Science\n" +
                          "Track List:\n" +
                          "   1. A\n" +
                          "   2. B\n" +
                          "   3. C\n";
        assertEquals(expected, album.toString());
    }
}