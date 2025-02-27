package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlaylistTest {
    Playlist test = new Playlist("Test Playlist");
    Song testSong = new Song("A", "B", "C");

    @Test
    void testGets() {
        assertEquals(test.getName(), "Test Playlist");
        assertEquals(test.getSongs(), "");
        test.addSong(testSong);

        assertEquals(test.getSongs(), "A");

        test.removeSong(testSong);
        assertEquals(test.getSongs(), "");
    }

    @Test
    void testString() {
        test.addSong(testSong);
        assertEquals(test.toString(), "Playlist: Test Playlist\nTrack List:\n   1. B - A | Appears on: C\n");

        test.removeSong(testSong);
        assertEquals(test.toString(), "Playlist: Test Playlist\nYou dont currently have any tracks on this playlist. Add some to get started!\n");
    }
}
