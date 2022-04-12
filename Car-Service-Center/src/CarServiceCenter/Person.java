// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;

import java.util.Objects;
import java.util.ArrayList; // import the ArrayList class

public class Person implements Comparable<Person>, Cloneable {
    
	// Variables declaration
	protected String name, phoneNumber;
	
	// Default Constructor
	protected Person(){
		name = "Ploni";
		phoneNumber = "0000000000";
	}
	// Constructor
	protected Person(String name, String phoneNumber){
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	// toString override
	@Override
	public String toString(){
	    return name + " " + phoneNumber;
	}
	// Return name function
	protected String getName() {
		return this.name;
	}
	// Return phone number function
	protected String getphoneNumber() {
		return this.phoneNumber;
	}
	// Equals override
	@Override
	public boolean equals(Object other) {
	    // Self check
	    if (this == other)
	        return true;
	    // Null check
	    if (other == null)
	        return false;
	    // Type check and cast
	    if (getClass() != other.getClass())
	        return false;
	    Person person = (Person) other;
	    // Field comparison
	    return Objects.equals(name, person.name) && Objects.equals(phoneNumber, person.phoneNumber);
	}
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + name.hashCode();
	    result = prime * result + (int) (phoneNumber.hashCode() ^ (phoneNumber.hashCode() >>> 32));
	    return result;
	}
	// compareTo implement
	@Override
	public int compareTo(Person o) {
		return name.compareTo(o.name);
	}
	@Override
    protected Person clone() throws CloneNotSupportedException { 
        return (Person)super.clone();
    }
}

