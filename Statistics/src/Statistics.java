//the min
//the max
//the number of elements
//the average
//the sum
public class Statistics {
	private Double[] dArray;
	private double min;
	private double max;
	private int numberElements = 0;
	private double average = 0;
	private double sum = 0;

	public Statistics(String[] strArray) {
		this.setValues(strArray);
	}

	private void setValues(String[] strArray) {
		this.numberElements = strArray.length;
		this.dArray = new Double[this.numberElements];
		for (int i = 0; i < this.numberElements; ++i) {
			dArray[i] = Double.parseDouble(strArray[i]);
			
			if (i == 0)
				this.min = this.max = dArray[i];
			
			if (dArray[i] < this.min)
				this.min = dArray[i];
			
			if (dArray[i] > this.max)
				this.max = dArray[i];
			
			this.sum += dArray[i];
			
			this.average = this.sum / this.numberElements;
		}
	}
	
	public double getMin() {
		return this.min;
	}
	
	public double getMax() {
		return this.max;
	}
	
	public int getNumberElements() {
		return this.numberElements;
	}
	
	public double getAverage() {
		return this.average;
	}
	
	public double getSum() {
		return this.sum;
	}
}
