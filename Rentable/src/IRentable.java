
public interface IRentable {
	/**
	 * @return the description of the item
	 */
	String getDescription();
	
	/**
	 * @return the daily rate for renting the item
	 */
	double getDailyRate();
	
	/**
	 * @param days
	 * @return the amount due for renting the item for the a given number of days
	 */
	double  getPrice(double days);
}
