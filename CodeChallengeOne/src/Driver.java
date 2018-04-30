
public class Driver {

	public static void main(String[] args) {
		// Producer consumer problem
		
		
		Basket fruitBasket = new Basket(100);
		Producer producer1 = new Producer();
		Consumer consumer1 = new Consumer();
		
		for(int i = 0; i < 100; i++) {
			consumer1.take(fruitBasket);
			producer1.fill(fruitBasket);
		}
		
		
		
		
	}

}
