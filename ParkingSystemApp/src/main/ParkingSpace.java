package main;

public class ParkingSpace {
	
	public boolean isVacant;
	public String spaceNumber;
	
	public ParkingSpace(boolean isVacant, String spaceNumber) {
		super();
		this.isVacant = isVacant;
		this.spaceNumber = spaceNumber;
	}
	
	public ParkingSpace() {
		super();
	}

	public boolean isVacant() {
		return isVacant;
	}

	public void setVacant(boolean isVacant) {
		this.isVacant = isVacant;
	}

	public String getSpaceNumber() {
		return spaceNumber;
	}

	public void setSpaceNumber(String spaceNumber) {
		this.spaceNumber = spaceNumber;
	}
	
	@Override
	public String toString() {
		if(isVacant()) {
			return "Parking Space [Number=" + spaceNumber + "] is vacant";
		}
		else {
			return "Parking Space [Number=" + spaceNumber + "] is occupied";
		}
		
	}

}
