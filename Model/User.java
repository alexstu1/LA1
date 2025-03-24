package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private String username;
	private String encryptedPassword;
	public User(String username, String password) {
		this.username = username;
		//change to actually salt+encrypt
		this.encryptedPassword = password;

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
	
	public boolean isValidLogin() {
		if (username==""||
			username.contains(",")) {
			return false;
		}
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

	public void saveCredentials() {

		String toSave = username+","+encryptedPassword;
		File file = new File("./users.txt");
		try {
			Scanner reader = new Scanner(file);
			ArrayList<String> unedited = new ArrayList<String>();
			while (reader.hasNextLine()) {
				unedited.add(reader.nextLine());
			}
			FileWriter fileWriter = new FileWriter("./users.txt");
			while (unedited.size()>0) {
				fileWriter.append(unedited.remove(0));
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
