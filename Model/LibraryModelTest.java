package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryModelTest {
	//94.7% coverage
	private LibraryModel libraryModel;
	private MusicStore store;
	@BeforeEach
	void setUp() {
		libraryModel = new LibraryModel();
		store = new MusicStore();
	}
	@Test
	void testLibraryModel() {
		assertEquals(libraryModel,libraryModel);
	}

	@Test
	void testGetSongByTitle() {
		assertEquals("It doesn't look like that song is in your library.",libraryModel.getSongByTitle("NeverGonnaBeARealSong"));
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Fight for Your Mind | Genre: Alternative | Appears on: Fight for Your Mind",libraryModel.getSongByTitle("Fight for Your Mind"));
	}

	@Test
	void testGetSongByArtist() {
		assertEquals("It doesn't look like that song is in your library.",libraryModel.getSongByArtist("NeverGonnaBeARealArtist"));
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Fight for Your Mind | Genre: Alternative | Appears on: Fight for Your Mind",libraryModel.getSongByArtist("Ben Harper"));
	}

	@Test
	void testGetAlbumByTitle() {
		assertEquals("It looks like that album isn't in your library.",libraryModel.getAlbumByTitle("NotARealAlbum"));
		libraryModel.addAlbum("Fight for Your Mind", store);
		
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
		libraryModel.addAlbum("Fight for Your Mind", store);
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
		assertEquals("It doesn't look like there's a playlist in your library with that name.", libraryModel.getPlaylist("Empty"));
		libraryModel.makePlaylist("test");
		assertEquals("It doesn't look like there's a playlist in your library with that name.",libraryModel.getPlaylist("NotFound"));
		assertEquals("This is the playlist that matches your search:\n"
				+ "Playlist: test\n"
				+ "You dont currently have any tracks on this playlist. Add some to get started!",libraryModel.getPlaylist("test"));
	}

	@Test
	void testAddSongString() {
		assertEquals("Sorry! It looks like that song isn't in our library.",libraryModel.addSong("NotPresent", store));
		assertEquals("Song added successfully!",libraryModel.addSong("Fight for Your Mind", store));
		assertEquals("That song is already in your library.",libraryModel.addSong("Fight for Your Mind", store));
		assertEquals("There are multiple songs with that title in our library. Please specify the artist to ensure we add the correct one!",libraryModel.addSong("Lullaby", store));
	}

	@Test
	void testBuildAddSongDupeString() {
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildAddSongDupeString("Lullaby", store));
	}

	@Test
	void testAddSongStringString() {
		assertEquals("Song added successfully!",libraryModel.addSong("Lullaby", "OneRepublic", store));
		assertEquals("That song is already in your library.",libraryModel.addSong("Lullaby", "OneRepublic", store));
		assertEquals("Sorry! It looks like that song isn't in our library.",libraryModel.addSong(null, store));
	}

	@Test
	void testAddAlbumString() {
		assertEquals("Sorry! It looks like that album isn't in our library.",libraryModel.addAlbum("NotARealAlbum", store));
		assertEquals("Full album added successfully!",libraryModel.addAlbum("Fight for Your Mind", store));
		assertEquals("That album is already in your library.",libraryModel.addAlbum("Fight for Your Mind", store));
	}

	@Test
	void testGetSongs() {
		assertEquals("Your library has no songs currently. Add some to get started!", libraryModel.getSongs());
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("These are all of the songs in your library:\n"
				+ "Ben Harper - Fight for Your Mind | Genre: Alternative | Appears on: Fight for Your Mind", libraryModel.getSongs());
	}

	@Test
	void testGetArtists() {
		assertEquals("Your library is empty currently. Add some songs or albums to get started!",libraryModel.getArtists());
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("These are all of the artists in your library:\n"
				+ "Ben Harper",libraryModel.getArtists());
	}

	@Test
	void testGetAlbums() {
		assertEquals("Your library has no songs currently. Add some to get started!",libraryModel.getAlbums());
		libraryModel.addAlbum("Fight for Your Mind", store);
		assertEquals("These are all of the albums in your library:\n"
				+ "Fight for Your Mind - Ben Harper",libraryModel.getAlbums());
	}

	@Test
	void testGetPlaylists() {
		assertEquals("These are all of your playlists:\n"
				+ "Recently Played\n"
				+ "Most Played\n"
				+ "Top Rated\n"
				+ "Favorites" ,libraryModel.getPlaylists());
		libraryModel.makePlaylist("test");
		assertEquals("These are all of your playlists:\n"
				+ "Recently Played\n"
				+ "Most Played\n"
				+ "Top Rated\n"
				+ "Favorites\n"
				+ "test",libraryModel.getPlaylists());
	}

	@Test
	void testGetFavorites() {
		assertEquals("You haven't favorited any songs yet.",libraryModel.getFavorites());
		libraryModel.addSong("Fight for Your Mind", store);
		libraryModel.favoriteSong("Fight for Your Mind");
		assertEquals("These are all of your favorite songs:\n"
				+ "Ben Harper - Fight for Your Mind | Genre: Alternative | Appears on: Fight for Your Mind",libraryModel.getFavorites());
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
		
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals("There are multiple songs with that title in your library, please specify the artist to ensure the correct one is added.",libraryModel.addToPlaylist("test", "Lullaby"));
		
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("Song added successfully!",libraryModel.addToPlaylist("test", "Fight for Your Mind"));
		assertEquals("It looks like that song is already on your playlist.",libraryModel.addToPlaylist("test", "Fight for Your Mind"));
	}

	@Test
	void testBuildAddToPlaylistDupeString() {
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildAddToPlaylistDupeString("Lullaby"));
	}

	@Test
	void testAddToPlaylistStringStringString() {
		libraryModel.makePlaylist("test");
		assertEquals("To add a song to a playlist, that song must be in your library first.",libraryModel.addToPlaylist("test", "Lullaby", "OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals("Song added successfully!",libraryModel.addToPlaylist("test", "Lullaby", "OneRepublic"));
		
	}

	@Test
	void testRemoveFromPlaylistStringString() {
		assertEquals("It doesn't look like you have a playlist with that name.",libraryModel.removeFromPlaylist("test", "Lullaby"));
		libraryModel.makePlaylist("test");
		assertEquals("It doesn't look like that playlist has that song on it.",libraryModel.removeFromPlaylist("test", "Lullaby"));
		
		libraryModel.addSong("Fight for Your Mind", store);
		libraryModel.addToPlaylist("test", "Fight for Your Mind");
		assertEquals("Song removed successfully!",libraryModel.removeFromPlaylist("test", "Fight for Your Mind"));
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		libraryModel.addToPlaylist("test", "Lullaby","Leonard Cohen");
		libraryModel.addToPlaylist("test", "Lullaby","OneRepublic");
		assertEquals("There are multiple songs on that playlist with that name. Please specify the artist to ensure the correct one is removed.",libraryModel.removeFromPlaylist("test", "Lullaby"));
	}

	@Test
	void testBuildRemoveFromPlaylistDupeString() {
		libraryModel.makePlaylist("test");
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		libraryModel.addToPlaylist("test", "Lullaby","Leonard Cohen");
		libraryModel.addToPlaylist("test", "Lullaby","OneRepublic");
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildRemoveFromPlaylistDupeString("test", "Lullaby"));
	}

	@Test
	void testRemoveFromPlaylistStringStringString() {
		libraryModel.makePlaylist("test");
		assertEquals("It doesn't look like that playlist has that song on it.",libraryModel.removeFromPlaylist("test", "Lullaby", "OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		libraryModel.addToPlaylist("test", "Lullaby","Leonard Cohen");
		libraryModel.addToPlaylist("test", "Lullaby","OneRepublic");
		assertEquals("Song removed successfully!",libraryModel.removeFromPlaylist("test", "Lullaby", "OneRepublic"));
	}

	@Test
	void testFavoriteSongString() {
		assertEquals("To favorite a song, it must be in your library.",libraryModel.favoriteSong("Fight for Your Mind"));
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("Song favorited!",libraryModel.favoriteSong("Fight for Your Mind"));
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals( "There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is favorited.",libraryModel.favoriteSong("Lullaby"));
	}

	@Test
	void testBuildFavoriteRateSongDupeString() {
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals("These are your options: [Leonard Cohen] [OneRepublic]",libraryModel.buildFavoriteRateSongDupeString("Lullaby"));
	}

	@Test
	void testFavoriteSongStringString() {
		assertEquals("To favorite a song, it must be in your library.",libraryModel.favoriteSong("Lullaby","OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals("Song favorited!",libraryModel.favoriteSong("Lullaby","OneRepublic"));
	}

	@Test
	void testRateSongStringInt() {
		assertEquals("To rate a song, it must be in your library.",libraryModel.rateSong("Fight for Your Mind", 4));
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("Song rated!",libraryModel.rateSong("Fight for Your Mind", 4));
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals("There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is rated.", libraryModel.rateSong("Lullaby", 4));
	}

	@Test
	void testRateSongStringIntString() {
		assertEquals("To rate a song, it must be in your library.",libraryModel.rateSong("Lullaby", 4, "OneRepublic"));
		libraryModel.addSong("Lullaby","Leonard Cohen", store);
		libraryModel.addSong("Lullaby","OneRepublic", store);
		assertEquals("Song rated!",libraryModel.rateSong("Lullaby", 4, "OneRepublic"));
	}

	@Test
	void testGetSongByTitleStore() {
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Fight for Your Mind | Genre: Alternative | Appears on: Fight for Your Mind",libraryModel.getSongByTitleStore("Fight for Your Mind", store));
	}
	@Test
	void testGetSongByArtistStore() {
		assertEquals("These are the songs that match your search:\n"
				+ "Ben Harper - Oppression | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Ground on Down | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Another Lonely Day | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Gold to Me | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Burn One Down | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Excuse Me Mr. | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - People Lead | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Fight for Your Mind | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Give a Man a Home | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - By My Side | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - Power of the Gospel | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - God Fearing Man | Genre: Alternative | Appears on: Fight for Your Mind\n"
				+ "Ben Harper - One Road to Freedom | Genre: Alternative | Appears on: Fight for Your Mind",libraryModel.getSongByArtistStore("Ben Harper", store));
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
				+ "   13. One Road to Freedom",libraryModel.getAlbumByTitleStore("Fight for Your Mind", store));
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
				+ "   13. One Road to Freedom",libraryModel.getAlbumByArtistStore("Ben Harper", store));
	}
	@Test
	void testAddAlbumStringString() {
		assertEquals("Sorry! It looks like that album isn't in our library.",libraryModel.addAlbum("notARealAlbum","NotARealArtist", store));
		assertEquals("Full album added successfully!",libraryModel.addAlbum("Fight for Your Mind", "Ben Harper", store));
	}


	@Test
	void testRateSongStringInt1() {
		libraryModel.addSong("Banjo", store);
		String emptyResult = libraryModel.rateSong("fakeName", 2);
		assertEquals("To rate a song, it must be in your library.",emptyResult);
	
		String ratedResult = libraryModel.rateSong("Banjo", 2);
		assertEquals("Song rated!",ratedResult);
		
		libraryModel.addSong("Lullaby", "OneRepublic",store);
		libraryModel.addSong("Lullaby","Leonard Cohen",store);
		String multipleResult = libraryModel.rateSong("Lullaby", 2);
		String expected = "There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is rated.";
		assertEquals(expected,multipleResult);
	}
	@Test
	void testRateSongStringIntString1() {
		libraryModel.addSong("Lullaby", "OneRepublic",store);
		libraryModel.addSong("Lullaby","Leonard Cohen",store);
		String expected =libraryModel.rateSong("Lullaby", 2,"OneRepublic");
		assertEquals("Song rated!",expected);
		
		String emptyResult = libraryModel.rateSong("fakeName", 2,"fakeArtist");
		assertEquals("To rate a song, it must be in your library.",emptyResult);
	}
	@Test
	void testGetSongsSortedByTitle() {
		libraryModel.addSong("Banjo", store);
		libraryModel.addSong("Anyhow",store);
		
		
		
		
		String actual = libraryModel.getSongsSortedByTitle();
		String expected = "Leonard Cohen - Anyhow | Genre: Singer/Songwriter | Appears on: Old Ideas\n"
				+ "Leonard Cohen - Banjo | Genre: Singer/Songwriter | Appears on: Old Ideas";
		assertEquals(expected,actual);
	}
	@Test
	void testGetSongsSortedByArtist() {
		libraryModel.addSong("Banjo", store);
		libraryModel.addSong("Turning Tables",store);
		
		String actual = libraryModel.getSongsSortedByArtist();
		String expected = "Adele - Turning Tables | Genre: Pop | Appears on: 21\n"
				+ "Leonard Cohen - Banjo | Genre: Singer/Songwriter | Appears on: Old Ideas";
		assertEquals(expected,actual);
	}
	@Test
	void testGetSongsSortedByRating() {
		libraryModel.addSong("Banjo", store);
		libraryModel.addSong("Turning Tables",store);
		libraryModel.rateSong("Banjo", 2);
		libraryModel.rateSong("Turning Tables",3);
		String actual = libraryModel.getSongsSortedByRating();
		String expected = "Adele - Turning Tables | Genre: Pop | Appears on: 21 | You Rated: 3/5\n"
				+ "Leonard Cohen - Banjo | Genre: Singer/Songwriter | Appears on: Old Ideas | You Rated: 2/5\n";
		assertEquals(expected,actual);
	}
	@Test
	void testShuffle() {
		libraryModel.addSong("Banjo", store);
		libraryModel.addSong("Turning Tables",store);
		String shuffledString = "These are all of the songs in your library:\n"
				+ "Adele - Turning Tables | Genre: Pop | Appears on: 21\n"
				+ "Leonard Cohen - Banjo | Genre: Singer/Songwriter | Appears on: Old Ideas";
		boolean shuffled = false;
		for (int i=0;i<25;i++) {		
			libraryModel.shuffle();
			if (libraryModel.getSongs().equals(shuffledString)) {
				shuffled=true;
				break;
			}
		}
		assertTrue(shuffled);
	}
	@Test
	void testGetSongByGenre() {
		String emptyLibrary = libraryModel.getSongByGenre("FAKE");
		assertEquals("Your library is empty. Add something to get started!",emptyLibrary);
		libraryModel.addSong("Banjo", store);
		libraryModel.addSong("Turning Tables",store);
		String actual = libraryModel.getSongByGenre("Pop");
		String expected = "These are the songs that match your search:\n"
				+ "Adele - Turning Tables | Genre: Pop | Appears on: 21";
		assertEquals(expected,actual);
	}
	@Test
	void testPlayString() {
		String noMatch = libraryModel.play("fake");
		assertEquals("It doesn't look like that song is in your library.",noMatch);
		
		libraryModel.addSong("Banjo", store);
		String played = libraryModel.play("Banjo");
		String actual = "Now playing: Leonard Cohen - Banjo | Genre: Singer/Songwriter | Appears on: Old Ideas | Plays: 1";
		assertEquals(actual,played);
		
		libraryModel.addSong("Lullaby", "OneRepublic",store);
		libraryModel.addSong("Lullaby","Leonard Cohen",store);
		String duplicate = libraryModel.play("Lullaby");
		actual = "There are multiple songs in your library with that name. Please specify the artist to ensure the correct one is played.";
		assertEquals(actual,duplicate);
	}
	@Test
	void testPlayStringString() {
		String noMatch = libraryModel.play("fake","faker");
		assertEquals("It doesn't look like that song is in your library.",noMatch);
		
		libraryModel.addSong("Lullaby", "OneRepublic",store);
		libraryModel.addSong("Lullaby","Leonard Cohen",store);
		String actual = libraryModel.play("Lullaby", "OneRepublic");
		String expected = "Now playing: OneRepublic - Lullaby | Genre: Rock | Appears on: Waking Up | Plays: 1";
		assertEquals(expected,actual);
	}
	@Test
	void testRemoveSongLibraryString() {
		String noMatch = libraryModel.removeSongLibrary("Fake");
		assertEquals("It doesn't look like that song is in your library.",noMatch);
		
		libraryModel.addSong("Lullaby", "OneRepublic",store);
		libraryModel.addSong("Lullaby","Leonard Cohen",store);
		String duplicate = libraryModel.removeSongLibrary("Lullaby");
		assertEquals("There are multiple songs in your library with that name. Please specify the artist to ensure we remove the correct one.",duplicate);
		
		libraryModel.addSong("Banjo", store);
		String actual = libraryModel.removeSongLibrary("Banjo");
		assertEquals("Song removed successfully!",actual);
	}
	@Test
	void testRemoveSongLibraryStringString() {
		String noMatch = libraryModel.removeSongLibrary("Fake","faker");
		assertEquals("It doesn't look like that song is in your library.",noMatch);
	
		libraryModel.addSong("Lullaby", "OneRepublic",store);
		libraryModel.addSong("Lullaby","Leonard Cohen",store);
		String actual = libraryModel.removeSongLibrary("Lullaby", "OneRepublic");
		assertEquals("Song removed successfully!",actual);
	}

	@Test
	void testGetAlbumFromInformation() {
		libraryModel.addSong("Fight for Your Mind", store);
		assertEquals("Album: Fight for Your Mind (1995) - In Library\n"
				+ "Artist: Ben Harper\n"
				+ "Genre: Alternative\n"
				+ "Track List:\n"
				+ "   1. Oppression - Not in library\n"
				+ "   2. Ground on Down - Not in library\n"
				+ "   3. Another Lonely Day - Not in library\n"
				+ "   4. Gold to Me - Not in library\n"
				+ "   5. Burn One Down - Not in library\n"
				+ "   6. Excuse Me Mr. - Not in library\n"
				+ "   7. People Lead - Not in library\n"
				+ "   8. Fight for Your Mind - In library\n"
				+ "   9. Give a Man a Home - Not in library\n"
				+ "   10. By My Side - Not in library\n"
				+ "   11. Power of the Gospel - Not in library\n"
				+ "   12. God Fearing Man - Not in library\n"
				+ "   13. One Road to Freedom - Not in library\n",libraryModel.getAlbumInformation("Fight For Your Mind", "Ben Harper", store));
	}

	@Test
	void testRemoveAlbumLibrary() {
		assertEquals("It doesn't look like that album is in your library.", libraryModel.removeAlbumLibrary("Fake Album"));
		libraryModel.addAlbum("19", store);
		assertEquals("Album removed successfully!", libraryModel.removeAlbumLibrary("19"));
	}
}
