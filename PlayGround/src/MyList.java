
public class MyList {
	private String[] store = new String[10];
	private int size = 0;

	private boolean isValidIndex(int i) {
		return i > 0 && i < this.size;
	}
	
	private void grow() {
		String[] backup = store;
		this.store = new String[this.size + 10];
		System.arraycopy(backup, 0, this.store, 0, this.store.length);
	}
		
	public void add(String s) {
		if (this.store.length == this.size) 
			this.grow();
		
		this.store[this.size] = s;
		++this.size;
	}
	
	public int size() {
		return this.size;
	}
	
	public String get(int i) {
		return this.isValidIndex(i) ? store[i] : null;
	}
	
	public void remove(int i) {
		if (this.isValidIndex(i)) {
			for (int j = i; j < this.size - 1; ++j)
				this.store[j] = this.store[j + 1];
			--this.size;
		}
	}
}
