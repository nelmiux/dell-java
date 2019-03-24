
public class Tool extends Rentable {
	private double hourlyRate;
	
	public Tool(double hourlyRate) {
		this.hourlyRate = hourlyRate;
		this.dailyRate = this.hourlyRate * 24;
	}
}
