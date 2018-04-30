/*is On shared Collection 
 * a "Basket"
 */
public class Basket {
	
	private int size;
	private int items;
	
	/*
	 * Makes an empty basket
	 */
	Basket(){
		this.size = 0;
	}
	
	/*
	 * Makes a basket of indicated size
	 */
	Basket(int size){
		this.size = size;
		this.items = 0;
	}
	/*
	 * Returns the size of the basket
	 */
	public int size() {
		 return this.size;
	}
	/*
	 * Returns the number of items in the basket
	 */
	public int numItem() {
		if(items <= 0) {
			return 0;
		} else if (items >= size) {
			return size;
		}else {
			return items;
		}
	}
	/*
	 * Returns true if empty
	 */
	public boolean isEmpty() {
		 if(items == 0) {
			 return true;
		 }
		 else {
			 return false;
		 }
	}
	
	
	/*
	 * Removes an item from a basket
	 */
	public void remove() {
		if(items > 0) {
		 this.items = items - 1;
		}
	}
	/*
	 * adds an item to a basket
	 */
	public void put() {
		 this.items = items + 1;
	}
}
