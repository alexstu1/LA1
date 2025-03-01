package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryModelTest {
	private LibraryModel libraryModel;

	@BeforeEach
	void setUp() {
		libraryModel = new LibraryModel();
		
	}
	
	@Test
	void testLibraryModel() {
		assertEquals(libraryModel,libraryModel);
	}

	@Test
	void testGetSongByTitle() {
		assertEquals("It doesn't look like that song is in your library.",libraryModel.getSongByTitle("NeverGonnaBeARealSong"));
		libraryModel.addSong("Fight for Your Mind");
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Fight for Your Mind | Appears on: Fight for Your Mind",libraryModel.getSongByTitle("Fight for Your Mind"));
	}

	@Test
	void testGetSongByArtist() {
		assertEquals("It doesn't look like that song is in your library.",libraryModel.getSongByArtist("NeverGonnaBeARealArtist"));
		libraryModel.addSong("Fight for Your Mind");
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Fight for Your Mind | Appears on: Fight for Your Mind",libraryModel.getSongByArtist("Ben Harper"));
	}

	@Test
	void testGetAlbumByTitle() {
		assertEquals("It doesn't look like that album is in your library.",libraryModel.getAlbumByTitle("NotARealAlbum"));
		libraryModel.addAlbum("Fight for Your Mind");
		
		assertEquals("These are the albums that match your search:\n"
				+ "Album: Fight for Your Mind (1995)\n"
				+ "Artist: Ben Harper\n"
				+ "Genre: Alternative\n"
				+ "Track List:\n"
				+ "   1. Oppression\n"
				+ "   2. Ground on Down\n"
				+ "   3. Another Lonely Day\n"
				+ "   4. Gold to Me\n"
				+ "   5. Burn One Down\n"
				+ "   6. Excuse Me Mr.\n"
				+ "   7. People Lead\n"
				+ "   8. Fight for Your Mind\n"
				+ "   9. Give a Man a Home\n"
				+ "   10. By My Side\n"
				+ "   11. Power of the Gospel\n"
				+ "   12. God Fearing Man\n"
				+ "   13. One Road to Freedom",libraryModel.getAlbumByTitle("Fight for Your Mind"));				
	}

	@Test
	void testGetAlbumByArtist() {
		assertEquals("It doesn't like you have any albums by that artist in your library.",libraryModel.getAlbumByArtist("NotARealArtist"));
		libraryModel.addAlbum("Fight for Your Mind");
		assertEquals("These are the albums that match your search:\n"
				+ "Album: Fight for Your Mind (1995)\n"
				+ "Artist: Ben Harper\n"
				+ "Genre: Alternative\n"
				+ "Track List:\n"
				+ "   1. Oppression\n"
				+ "   2. Ground on Down\n"
				+ "   3. Another Lonely Day\n"
				+ "   4. Gold to Me\n"
				+ "   5. Burn One Down\n"
				+ "   6. Excuse Me Mr.\n"
				+ "   7. People Lead\n"
				+ "   8. Fight for Your Mind\n"
				+ "   9. Give a Man a Home\n"
				+ "   10. By My Side\n"
				+ "   11. Power of the Gospel\n"
				+ "   12. God Fearing Man\n"
				+ "   13. One Road to Freedom",libraryModel.getAlbumByArtist("Ben Harper"));
	}

	@Test
	void testGetPlaylist() {
		assertEquals("You don't have any playlists in your library yet. Make one to get started!",libraryModel.getPlaylist("Empty"));
		libraryModel.makePlaylist("test");
		assertEquals("It doesn't look like there's a playlist in your library with that name.",libraryModel.getPlaylist("NotFound"));
		assertEquals("This is the playlist that matches your search:\n"
				+ "Playlist: test\n"
				+ "You dont currently have any tracks on this playlist. Add some to get started!",libraryModel.getPlaylist("test"));
	}

	@Test
	void testAddSongString() {
		assertEquals("Sorry! It looks like that song isn't in our library.",libraryModel.addSong("NotPresent"));
		assertEquals("Song added successfully!",libraryModel.addSong("Fight for Your Mind"));
		assertEquals("That song is already in your library.",libraryModel.addSong("Fight for Your Mind"));
		assertEquals("There are multiple songs with that title in our library. Please specify the artist to ensure we add the correct one!",libraryModel.addSong("Lullaby"));
	}

	@Test
	void testBuildAddSongDupeString() {
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildAddSongDupeString("Lullaby"));
	}

	@Test
	void testAddSongStringString() {
		assertEquals("Song added successfully!",libraryModel.addSong("Lullaby", "OneRepublic"));
		assertEquals("That song is already in your library.",libraryModel.addSong("Lullaby", "OneRepublic"));
		assertEquals("Sorry! It looks like that song isn't in our library.",libraryModel.addSong(null));
	}

	@Test
	void testAddAlbumString() {
		assertEquals("Sorry! It looks like that album isn't in our library.",libraryModel.addAlbum("NotARealAlbum"));
		assertEquals("Album added successfully!",libraryModel.addAlbum("Fight for Your Mind"));
		assertEquals("That album is already in your library.",libraryModel.addAlbum("Fight for Your Mind"));
	}

	@Test
	void testGetSongs() {
		assertEquals("Your library has no songs currently. Add some to get started!", libraryModel.getSongs());
		libraryModel.addSong("Fight for Your Mind");
		assertEquals("These are all of the songs in your library:\n"
				+ "Ben Harper - Fight for Your Mind | Appears on: Fight for Your Mind", libraryModel.getSongs());
	}

	@Test
	void testGetArtists() {
		assertEquals("Your library is empty currently. Add some songs or albums to get started!",libraryModel.getArtists());
		libraryModel.addSong("Fight for Your Mind");
		assertEquals("These are all of the artists in your library:\n"
				+ "Ben Harper",libraryModel.getArtists());
	}

	@Test
	void testGetAlbums() {
		assertEquals("Your library has no albums currently. Add some to get started!",libraryModel.getAlbums());
		libraryModel.addAlbum("Fight for Your Mind");
		assertEquals("These are all of the albums in your library:\n"
				+ "Fight for Your Mind - Ben Harper",libraryModel.getAlbums());
	}

	@Test
	void testGetPlaylists() {
		assertEquals("There are no playlists in your library. Make one to get started!",libraryModel.getPlaylists());
		libraryModel.makePlaylist("test");
		assertEquals("These are all of your playlists:\n"
				+ "test",libraryModel.getPlaylists());
	}

	@Test
	void testGetFavorites() {
		assertEquals("You haven't favorited any songs yet.",libraryModel.getFavorites());
		libraryModel.addSong("Fight for Your Mind");
		libraryModel.favoriteSong("Fight for Your Mind");
		assertEquals("These are all of your favorite songs:\n"
				+ "Ben Harper - Fight for Your Mind | Appears on: Fight for Your Mind",libraryModel.getFavorites());
	}

	@Test
	void testMakePlaylist() {
		assertEquals("Playlist created successfully!",libraryModel.makePlaylist("test"));
		assertEquals("You already have a playlist with that name.",libraryModel.makePlaylist("test"));
	}

	@Test
	void testAddToPlaylistStringString() {
		assertEquals("It doesn't look like you have a playlist with that name.",libraryModel.addToPlaylist("test","songName"));
		libraryModel.makePlaylist("test");
		assertEquals("To add a song to a playlist, that song must be in your library first.",libraryModel.addToPlaylist("test","songName"));
		
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals("There are multiple songs with that title in your library, please specify the artist to ensure the correct one is added.",libraryModel.addToPlaylist("test", "Lullaby"));
		
		libraryModel.addSong("Fight for Your Mind");
		assertEquals("Song added successfully!",libraryModel.addToPlaylist("test", "Fight for Your Mind"));
		assertEquals("It looks like that song is already on your playlist.",libraryModel.addToPlaylist("test", "Fight for Your Mind"));
	}

	@Test
	void testBuildAddToPlaylistDupeString() {
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildAddToPlaylistDupeString("Lullaby"));
	}

	@Test
	void testAddToPlaylistStringStringString() {
		libraryModel.makePlaylist("test");
		assertEquals("To add a song to a playlist, that song must be in your library first.",libraryModel.addToPlaylist("test", "Lullaby", "OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals("Song added successfully!",libraryModel.addToPlaylist("test", "Lullaby", "OneRepublic"));
		
	}

	@Test
	void testRemoveFromPlaylistStringString() {
		assertEquals("It doesn't look like you have a playlist with that name.",libraryModel.removeFromPlaylist("test", "Lullaby"));
		libraryModel.makePlaylist("test");
		assertEquals("It doesn't look like that playlist has that song on it.",libraryModel.removeFromPlaylist("test", "Lullaby"));
		
		libraryModel.addSong("Fight for Your Mind");
		libraryModel.addToPlaylist("test", "Fight for Your Mind");
		assertEquals("Song removed successfully!",libraryModel.removeFromPlaylist("test", "Fight for Your Mind"));
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		libraryModel.addToPlaylist("test", "Lullaby","Leonard Cohen");
		libraryModel.addToPlaylist("test", "Lullaby","OneRepublic");
		assertEquals("There are multiple songs on that playlist with that name. Please specify the artist to ensure the correct one is removed.",libraryModel.removeFromPlaylist("test", "Lullaby"));
	}

	@Test
	void testBuildRemoveFromPlaylistDupeString() {
		libraryModel.makePlaylist("test");
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		libraryModel.addToPlaylist("test", "Lullaby","Leonard Cohen");
		libraryModel.addToPlaylist("test", "Lullaby","OneRepublic");
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildRemoveFromPlaylistDupeString("test", "Lullaby"));
	}

	@Test
	void testRemoveFromPlaylistStringStringString() {
		libraryModel.makePlaylist("test");
		assertEquals("It doesn't look like that playlist has that song on it.",libraryModel.removeFromPlaylist("test", "Lullaby", "OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		libraryModel.addToPlaylist("test", "Lullaby","Leonard Cohen");
		libraryModel.addToPlaylist("test", "Lullaby","OneRepublic");
		assertEquals("Song removed successfully!",libraryModel.removeFromPlaylist("test", "Lullaby", "OneRepublic"));
	}

	@Test
	void testFavoriteSongString() {
		assertEquals("To favorite a song, it must be in your library.",libraryModel.favoriteSong("Fight for Your Mind"));
		libraryModel.addSong("Fight for Your Mind");
		assertEquals("Song favorited!",libraryModel.favoriteSong("Fight for Your Mind"));
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals( "There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is favorited.",libraryModel.favoriteSong("Lullaby"));
	}

	@Test
	void testBuildFavoriteRateSongDupeString() {
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildFavoriteRateSongDupeString("Lullaby"));
	}

	@Test
	void testFavoriteSongStringString() {
		assertEquals("To favorite a song, it must be in your library.",libraryModel.favoriteSong("Lullaby","OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals("Song favorited!",libraryModel.favoriteSong("Lullaby","OneRepublic"));
	}

	@Test
	void testRateSongStringInt() {
		assertEquals("To rate a song, it must be in your library.",libraryModel.rateSong("Fight for Your Mind", 4));
		libraryModel.addSong("Fight for Your Mind");
		assertEquals("Song rated!",libraryModel.rateSong("Fight for Your Mind", 4));
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals("There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is rated.", libraryModel.rateSong("Lullaby", 4));
	}

	@Test
	void testRateSongStringIntString() {
		assertEquals("To rate a song, it must be in your library.",libraryModel.rateSong("Lullaby", 4, "OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen");
		libraryModel.addSong("Lullaby","OneRepublic");
		assertEquals("Song rated!",libraryModel.rateSong("Lullaby", 4, "OneRepublic"));
	}

	@Test
	void testGetSongByTitleStore() {
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Fight for Your Mind | Appears on: Fight for Your Mind",libraryModel.getSongByTitleStore("Fight for Your Mind"));
	}
	@Test
	void testGetSongByArtistStore() {
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Oppression | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Ground on Down | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Another Lonely Day | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Gold to Me | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Burn One Down | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Excuse Me Mr. | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - People Lead | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Fight for Your Mind | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Give a Man a Home | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - By My Side | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Power of the Gospel | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - God Fearing Man | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - One Road to Freedom | Appears on: Fight for Your Mind",libraryModel.getSongByArtistStore("Ben Harper"));
	}

	@Test
	void testGetAlbumByTitleStore() {
		assertEquals("These are the albums that match your search:\n"
				+ "Album: Fight for Your Mind (1995)\n"
				+ "Artist: Ben Harper\n"
				+ "Genre: Alternative\n"
				+ "Track List:\n"
				+ "   1. Oppression\n"
				+ "   2. Ground on Down\n"
				+ "   3. Another Lonely Day\n"
				+ "   4. Gold to Me\n"
				+ "   5. Burn One Down\n"
				+ "   6. Excuse Me Mr.\n"
				+ "   7. People Lead\n"
				+ "   8. Fight for Your Mind\n"
				+ "   9. Give a Man a Home\n"
				+ "   10. By My Side\n"
				+ "   11. Power of the Gospel\n"
				+ "   12. God Fearing Man\n"
				+ "   13. One Road to Freedom",libraryModel.getAlbumByTitleStore("Fight for Your Mind"));
	}

	@Test
	void testGetAlbumByArtistStore() {
		assertEquals("These are the albums that match your search:\n"
				+ "Album: Fight for Your Mind (1995)\n"
				+ "Artist: Ben Harper\n"
				+ "Genre: Alternative\n"
				+ "Track List:\n"
				+ "   1. Oppression\n"
				+ "   2. Ground on Down\n"
				+ "   3. Another Lonely Day\n"
				+ "   4. Gold to Me\n"
				+ "   5. Burn One Down\n"
				+ "   6. Excuse Me Mr.\n"
				+ "   7. People Lead\n"
				+ "   8. Fight for Your Mind\n"
				+ "   9. Give a Man a Home\n"
				+ "   10. By My Side\n"
				+ "   11. Power of the Gospel\n"
				+ "   12. God Fearing Man\n"
				+ "   13. One Road to Freedom",libraryModel.getAlbumByArtistStore("Ben Harper"));
	}
	@Test
	void testAddAlbumStringString() {
		assertEquals("Sorry! It looks like that album isn't in our library.",libraryModel.addAlbum("notARealAlbum","NotARealArtist"));
		assertEquals("Album added successfully!",libraryModel.addAlbum("Fight for Your Mind", "Ben Harper"));
	}

}
