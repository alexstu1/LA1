package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
	private String username;
	private String encryptedPassword;
	private int libraryModelIndex;
	public User(String username, String password, int index) {
		this.username = username;
		//change to actually salt+encrypt
		this.encryptedPassword = password;
		this.libraryModelIndex=index;
	}
	public static boolean isUserNameAvailable(String username) {
		File file = new File("./users.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				String[] saved = reader.nextLine().split(",", 2);
				if(username==saved[0]) {
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
	
	public boolean isValidLogin(String password) {
		File file = new File("./users.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				String[] saved = reader.nextLine().split(",", 2);
				if(username==saved[0]&&encryptedPassword==saved[1]) {
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
	public int getIndex() {
		return this.libraryModelIndex;
	}
	public void saveCredentials() {
		String toSave = username+","+encryptedPassword;
		File file = new File("./users.txt");
		try {
			Scanner reader = new Scanner(file);
			String unedited = reader.next();
			FileWriter fileWriter = new FileWriter("./users.txt");
			fileWriter.append(unedited);
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
