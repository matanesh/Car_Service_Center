// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;

public abstract class Media implements Playable{
	
	private String name;
	private int Length;
	
	// Default Constructor
	protected Media() {
		name = "UNKNOWN";
		Length = 0;
	}
	//Constructor
	protected Media(String name, int Length) {
		this.name = name;
		this.Length = Length;
	}
	@Override
	public String toString() {
	    return name.toString() + " " + Length;
	}
	// Return Length function
	protected int getLength() {
		return this.Length;
	}
	// Return name function
	public String getName() {
		return this.name;
	}
}
