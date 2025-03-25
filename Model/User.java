package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class User{
	//test coverage - 91.4% only missing the e.printstacktrace in the exceptions.
	private String username;
	private String encryptedPassword;
	
	public User(String username, String password) {
		/* This method creates a User object that can save or check valid logins
		 * Arguments:
		 * 		username: A string of the users desired uesrname.
		 * 		password: A string of the clear text of the users password.
		 * Returns nothing, this is a constructor.
		 */
		this.username = username;
		this.encryptedPassword = encryptPassword(password);
		
	}
	public String getUsername() {
		return this.username;
	}
	public String getEncrypetdPassword() {
		return this.encryptedPassword;
	}
	public static void deleteDatabase() {
		/* This method deletes all user accounts from the user account file
		 * Arguments: None
		 * Returns: null
		 */
		File usersPath = new File("Users");
		File file = new File(usersPath, "users.txt");
		try {
			FileWriter writer = new FileWriter(file);
			writer.append("");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String encryptPassword(String password) {
		/* This method takes a clear text password and returns its encrypted version,
		 *  after salting with the users username.
		 * Argument:
		 * 		password: A string of the clear text of the users password.
		 * Returns: A string of the encrypted password
		 */
		try {
			SecretKeyFactory f;
			byte[] salt = username.getBytes();
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] hash = f.generateSecret(spec).getEncoded();
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static boolean isUserNameAvailable(String username) {
		/* Static method used to determine if  a username is already in use.
		 * Argument:
		 * 		username: A string of the desired username to check.
		 * Returns a boolean:
		 * 		true: If the username is not already taken.
		 * 		false: If the username is in use.
		 */
		File usersPath = new File("Users");
		File file = new File(usersPath, "users.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				String[] saved = reader.nextLine().split(",", 2);
				if(username.equals(saved[0])) {
					reader.close();
					return false;
				} 
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return true;
	}
	
	public boolean isValidLogin() {
		/*This method verifies the current username password hash against the saved username and password hashes. 
		 * Arguments: none
		 * Returns a boolean:
		 * 		true: If the username and password hash match the saved login details.
		 * 		false: If the uesrname and password hash do not match the saved login details.
		 */
		
		File usersPath = new File("Users");
		File file = new File(usersPath, "users.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				String newLine = reader.nextLine();
				String[] saved = newLine.split(",", 2);
				if(username.equals(saved[0])&&encryptedPassword.equals(saved[1])) {
					reader.close();
					return true;
				} 
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}

	public void saveCredentials() {
		/* This method saves the current username and password hash into
		 *  the saved login details to create a new account
		 * Arguments: None
		 * Returns: null
		 */
		String toSave = username+","+encryptedPassword;
		File usersPath = new File("Users");
		File file = new File(usersPath, "users.txt");
		try {
			Scanner reader = new Scanner(file);
			ArrayList<String> unedited = new ArrayList<String>();
			while (reader.hasNextLine()) {
				unedited.add(reader.nextLine());
			}
			FileWriter fileWriter = new FileWriter(file);
			while (unedited.size()>0) {
				//saves the file how it was before adding the new account to end
				fileWriter.append(unedited.remove(0) + "\n");
			}
			fileWriter.append(toSave);
			fileWriter.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
