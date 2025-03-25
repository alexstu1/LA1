package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class MusicStoreTest {
	//coverage 99.2%
    private MusicStore musicStore;

    @BeforeEach
    void setUp() {
        musicStore = new MusicStore();
    }

    @Test
    void testGetSongByTitle() {
    	
        String result = musicStore.getSongByTitle("Fake Song (Does not exist)");
        assertEquals("Sorry! It doesn't look like that song is in our library.", result);

        result = musicStore.getSongByTitle("Lullaby");
        String expected = "These are the songs that match your search:\n"+
        	"Leonard Cohen - Lullaby | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"OneRepublic - Lullaby | Genre: Rock | Appears on: Waking Up";
        assertEquals(expected,result);
    }

    @Test
    void testGetSongByArtist() {
        String result = musicStore.getSongByArtist("Unknown Artist");
        assertEquals("Sorry! It doesn't look like that song is in our library.", result);
        result = musicStore.getSongByArtist("Leonard Cohen");
        String expected = "These are the songs that match your search:\n"+
        	"Leonard Cohen - Going Home | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Amen | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Show Me the Place | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Darkness | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Anyhow | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Crazy to Love You | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Come Healing | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Banjo | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Lullaby | Genre: Singer/Songwriter | Appears on: Old Ideas\n"+
        	"Leonard Cohen - Different Sides | Genre: Singer/Songwriter | Appears on: Old Ideas";
        assertEquals(expected,result);
    }

    @Test
    void testGetAlbumByTitle() {
        String result = musicStore.getAlbumByTitle("Fake Album (Does not exist)");
        assertEquals("Sorry! It doesn't look like that album is in our library.", result);

        result = musicStore.getAlbumByTitle("Old Ideas");
        assertTrue(result.contains("These are the albums that match your search:"));
        assertTrue(result.contains("Album: Old Ideas (2012)"));
        assertTrue(result.contains("Artist: Leonard Cohen"));
        assertTrue(result.contains("Genre: Singer/Songwriter"));
        assertTrue(result.contains("Track List:"));
        assertTrue(result.contains("1. Going Home"));
        assertTrue(result.contains("2. Amen"));
        assertTrue(result.contains("3. Show Me the Place"));
        assertTrue(result.contains("4. Darkness"));
        assertTrue(result.contains("5. Anyhow"));
        assertTrue(result.contains("6. Crazy to Love You"));
        assertTrue(result.contains("7. Come Healing"));
        assertTrue(result.contains("8. Banjo"));
        assertTrue(result.contains("9. Lullaby"));
        assertTrue(result.contains("10. Different Sides"));
    }

    @Test
    void testGetAlbumByArtist() {
        String result = musicStore.getAlbumByArtist("Eric Mantica");
        assertEquals("Sorry! It doesn't like we have any albums by that artist in our library.", result);

        result = musicStore.getAlbumByArtist("Leonard Cohen");
        assertTrue(result.contains("These are the albums that match your search:"));
        assertTrue(result.contains("Album: Old Ideas (2012)"));
        assertTrue(result.contains("Artist: Leonard Cohen"));
        assertTrue(result.contains("Genre: Singer/Songwriter"));
        assertTrue(result.contains("Track List:"));
        assertTrue(result.contains("1. Going Home"));
        assertTrue(result.contains("2. Amen"));
        assertTrue(result.contains("3. Show Me the Place"));
        assertTrue(result.contains("4. Darkness"));
        assertTrue(result.contains("5. Anyhow"));
        assertTrue(result.contains("6. Crazy to Love You"));
        assertTrue(result.contains("7. Come Healing"));
        assertTrue(result.contains("8. Banjo"));
        assertTrue(result.contains("9. Lullaby"));
        assertTrue(result.contains("10. Different Sides"));
    }

    @Test
    void testGetSongForLibrary() {
        ArrayList<Song> songs = musicStore.getSongForLibrary("Fake Song (Does not exist)");
        assertTrue(songs.isEmpty());

        songs = musicStore.getSongForLibrary("Lullaby");
        Song orLullaby = new Song("Lullaby", "OneRepublic", "Rock", "Waking Up", 2009);
        Song lcLullaby = new Song("Lullaby", "Leonard Cohen","Singer/Songwriter", "Old Ideas", 2012);

        
        assertTrue(songs.contains(orLullaby));
        assertTrue(songs.contains(lcLullaby));
    }

    @Test
    void testGetAlbumForLibrary() {
        ArrayList<Album> albums = musicStore.getAlbumForLibrary("Fake Album (Does not exist)");
        assertTrue(albums.isEmpty());

        albums = musicStore.getAlbumForLibrary("Old Ideas");
        String result = albums.get(0).toString();
        assertTrue(result.contains("Album: Old Ideas (2012)"));
        assertTrue(result.contains("Artist: Leonard Cohen"));
        assertTrue(result.contains("Genre: Singer/Songwriter"));
        assertTrue(result.contains("Track List:"));
        assertTrue(result.contains("1. Going Home"));
        assertTrue(result.contains("2. Amen"));
        assertTrue(result.contains("3. Show Me the Place"));
        assertTrue(result.contains("4. Darkness"));
        assertTrue(result.contains("5. Anyhow"));
        assertTrue(result.contains("6. Crazy to Love You"));
        assertTrue(result.contains("7. Come Healing"));
        assertTrue(result.contains("8. Banjo"));
        assertTrue(result.contains("9. Lullaby"));
        assertTrue(result.contains("10. Different Sides"));
    }
}
