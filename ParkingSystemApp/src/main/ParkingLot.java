package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParkingLot {
	
	public ArrayList<ParkingSpace> parkingLot = new ArrayList<ParkingSpace>();
	
	public void load(String path) throws Exception{
		ArrayList<String> parkingSpaces = new ArrayList<String>();
		FileInputStream str = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(str));
		String strline;
		while((strline = br.readLine()) != null) {
			parkingSpaces.add(strline);
		}
		for(int i = 0; i < parkingSpaces.size(); i+=2) {
			ParkingSpace space = new ParkingSpace();
			space.setSpaceNumber(parkingSpaces.get(i));
			if(parkingSpaces.get(i+1).equals("Vacant")) {
				space.setVacant(true);
			}
			else if(parkingSpaces.get(i+1).equals("Occupied")) {
				space.setVacant(false);
			}
			parkingLot.add(space);
		}
		str.close();
	}
	
	public void update(String path) throws Exception{
		try {		
			BufferedWriter br = new BufferedWriter(new FileWriter(path));
			for(ParkingSpace p: parkingLot){
				br.write(p.getSpaceNumber());
				br.newLine();
				if(p.isVacant()) {
					br.write("Vacant");
				}
				else {
					br.write("Occupied");
				}
				br.newLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
