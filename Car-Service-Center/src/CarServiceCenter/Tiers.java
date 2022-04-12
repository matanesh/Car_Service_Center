package src;
import java.util.concurrent.*;

class Tiers extends WorkStation 
{
	private int tires_amount;
	private Semaphore sem;
	
	public Tiers(String name, int price, int duration, int counter, int id, int tires) {
		super( name,  price,  duration,  counter,  id);
		this.tires_amount = tires;
		sem = new Semaphore(1);
	}
	
	// TODO: Add car input - adjust prints and variables after done
	public void startTiers()
	{
		System.out.println("Insert Car Id -- Wating for Tiers fix");
		// Lock the Station
		try {
			this.sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Insert Car Id -- started Tiers fix");
		
		// Sleep for the duration of the station work - make sure assign this.duration right
		// Note: We multiply the duration with the number of tires!
		try {
			Thread.sleep(this.getDuration() * this.tires_amount);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		// Release Semaphore
		System.out.println("Tires station Done with car: Add id when integrating");
		this.sem.release();
		
	}

	
	public Semaphore getSem() {
		return sem;
	}

}
