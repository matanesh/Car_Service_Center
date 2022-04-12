// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;

public class VideoClip extends Media{
	
	// Default Constructor
	protected VideoClip() {
		super();
	}
	//Constructor
	protected VideoClip(String name, int Length) {
		super(name, Length);
	}
	public String play() {
		return "VideoClip:" + this.getName() + " " + "is now playing for" + " " + this.getLength() + " "+"time\n";	
	}

}
