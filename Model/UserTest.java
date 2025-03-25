package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	private User user;
	@BeforeEach
	void setUp() throws Exception {
	user = new User("username","password");
	}

	@AfterEach
	void tearDown() throws Exception {
		User.deleteDatabase();
	}
	

	@Test
	void testUser() {
		User newUser = new User("abcde","12345");
		assertTrue(newUser instanceof User);
		assertTrue(newUser.getUsername().equals("abcde"));
		assertTrue(newUser.getEncrypetdPassword().equals("kg7tu/NZ+dd4amPJVmtD5Q=="));
	}
	@Test
	void testGetUsername() {
		assertTrue(user.getUsername().equals("username"));
	}
	@Test
	void testGetEncryptedPassword() {
		assertTrue(user.getEncrypetdPassword().equals("EPnJnXBnC67tcb8zm6y1zA=="));
	}
	@Test
	void testDeleteDatabase() {
		user.saveCredentials();
		assertTrue(user.isValidLogin());
		User.deleteDatabase();
		assertFalse(user.isValidLogin());
	}

	@Test
	void testEncryptPassword() {
		User newUser = new User("qwerty","qwerty");
		assertTrue(newUser.encryptPassword("SecurePassword").equals("I8ZZnrzFCWdI6XBQerAkjw=="));
	}

	@Test
	void testIsUserNameAvailable() {
		user.saveCredentials();
		assertFalse(User.isUserNameAvailable("username"));
		assertTrue(User.isUserNameAvailable("new Username"));
	}

	@Test
	void testIsValidLogin() {
		User secondUser = new User("secondUsername","password");
		assertFalse(user.isValidLogin());
		user.saveCredentials();
		secondUser.saveCredentials();
		assertTrue(user.isValidLogin());
	}

	@Test
	void testSaveCredentials() {
		assertFalse(user.isValidLogin());
		user.saveCredentials();
		assertTrue(user.isValidLogin());
	}

}
