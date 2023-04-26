package main;

public class UniqueID {
	
	public static int id = 1000000;
	
	public int getID() {
		return id;
	}
	
	public void incID() {
		UniqueID.id++;
	}
}
