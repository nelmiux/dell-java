
public class Condo extends Rentable {
	private double weeklyRate;
	
	public Condo(double weeklyRate) {
		this.weeklyRate =  weeklyRate;
		this.dailyRate = this.weeklyRate / 7;
	}
}
