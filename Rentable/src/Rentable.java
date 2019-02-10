
public abstract class Rentable implements IRentable {
	protected double dailyRate;
	
	/*
	 * @see IRentable#getDescription()
	 */
	public String getDescription() {
		return this.getClass().getSimpleName() + " Object";
	}
	
	/*
	 * @see IRentable#getDailyRate()
	 */
	public double getDailyRate() {
		return this.dailyRate;
	}
	
	/*
	 * @see IRentable#getPrice(double)
	 */
	public double getPrice(double days) {
		return this.dailyRate * days;
	}
}
