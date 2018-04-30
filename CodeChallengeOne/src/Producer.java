/*
 * Puts items into Basket when it is empty
 * (But enforce max size)
 * so cannot over fill the "Basket"
 */
public class Producer {
	
	/*
	 * Check to see the if the Basket is empty
	 * then adds more
	 */
	public void fill(Basket basket) {
		if(basket.isEmpty()) {
			for(int i = 0; i <basket.size();i++) {
				basket.put();
			}
		}
	}
}
