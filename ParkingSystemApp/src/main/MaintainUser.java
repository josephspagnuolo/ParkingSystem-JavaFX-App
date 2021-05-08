package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MaintainUser {
	public ArrayList<User> users = new ArrayList<User>();
	public ArrayList<User> officers = new ArrayList<User>();
	public static User currentUser;
	
	public void setCurrentUser(User u) {
		MaintainUser.currentUser = u;
	}
	
	public void load(String path) throws Exception{
		ArrayList<String> eachUser = new ArrayList<String>();
		FileInputStream str = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(str));
		String strline;
		
		while((strline = br.readLine()) != null) {
			eachUser.add(strline);
		}
		
		for(int i = 0; i < eachUser.size(); i+=4) {
			User user = new User();
			user.setFirstName(eachUser.get(i));
			user.setLastName(eachUser.get(i+1));
			user.setEmail(eachUser.get(i+2));
			user.setPassword(eachUser.get(i+3));
			users.add(user);
		}
		
		str.close();
	}
	
	public void load2(String path) throws Exception{
		ArrayList<String> eachOfficer = new ArrayList<String>();
		FileInputStream str = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(str));
		String strline;
		
		while((strline = br.readLine()) != null) {
			eachOfficer.add(strline);
		}
		
		for(int i = 0; i < eachOfficer.size(); i+=4) {
			User officer = new User();
			officer.setFirstName(eachOfficer.get(i));
			officer.setLastName(eachOfficer.get(i+1));
			officer.setEmail(eachOfficer.get(i+2));
			officer.setPassword(eachOfficer.get(i+3));
			officers.add(officer);
		}
		
		str.close();
	}
	
	public void update(String path) throws Exception{
		try {		
			BufferedWriter br = new BufferedWriter(new FileWriter(path));
				for(User u: users){
					br.write(u.getFirstName());
					br.newLine();
					br.write(u.getLastName());
					br.newLine();
					br.write(u.getEmail());
					br.newLine();
					br.write(u.getPassword());
					br.newLine();
				}
				br.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void update2(String path) throws Exception{
		try {		
			BufferedWriter br = new BufferedWriter(new FileWriter(path));
				for(User o: officers){
					br.write(o.getFirstName());
					br.newLine();
					br.write(o.getLastName());
					br.newLine();
					br.write(o.getEmail());
					br.newLine();
					br.write(o.getPassword());
					br.newLine();
				}
				br.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}