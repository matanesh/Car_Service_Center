package Targil3_pack;

import java.util.ArrayList;

public class PrivateCar extends Vehicle{
	int wheels;
	
	public PrivateCar(String type, int year, String license, String owner_name, String owner_phone, ArrayList<String> stations, int wheels) {
		super( type,  year,  license,  owner_name,  owner_phone, stations);
		this.wheels=wheels;
	}
	
	public int getWheels() {
		return this.wheels; 
	}
	public void setWheels(int wheels) {
		this.wheels=wheels;
	}
	public String toString() {
		return super.toString() + " " + "|" + wheels;	
	}

}
