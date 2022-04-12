package src;

import java.util.concurrent.Semaphore;

public class Electric extends WorkStation  
{

	// TODO: add variable like tires? 
	private Semaphore sem;
	
	public Electric(String name, int price, int duration, int counter, int id) {
		super( name,  price,  duration,  counter,  id);
		this.sem = new Semaphore(1);
	}
	
	// TODO: Add car input - adjust prints and variables after done
	public void startEngine()
	{
		
		System.out.println("Insert Car Id -- Wating for Engine fix");
		// Lock the Station
		try {
			this.sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Insert Car Id -- started Engine fix");
		
		// Sleep for the duration of the station work - make sure assign this.duration right
		// Note: We multiply the duration with the number of tires!
		try {
			Thread.sleep(this.getDuration());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		// Release Semaphore
		System.out.println("Electric station Done with car: Add id when integrating");
		this.sem.release();
		
	}

}
