import java.util.Random;

/*
 * Attempts to remove random number of Items from "Basket"
 */
public class Consumer {
	private Random r;
	Consumer(){
		super();
		this.r =new Random();
	}
	
	/*
	 * Removes an item from a basket
	 */
	public void take(Basket basket) {
		//generates a random int between 0 and the basket size
		int numItems = r.nextInt(basket.size());
		
		for(int i = 0 ; i < numItems;i++) {
			basket.remove();	
		}	
	}

}
