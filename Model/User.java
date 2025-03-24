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

public class User {
	private String username;
	private String encryptedPassword;
	public User(String username, String password) {
		this.username = username;
		//change to actually salt+encrypt
		this.encryptedPassword = encryptPassword(password);
		

	
	
	}
	public String encryptPassword(String password) {
		
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
		if (username.equals("")||
			username.contains(",")) {
			return false;
		}
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
