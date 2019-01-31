
public class Car {
	private String Color;
	private String LicenseNumber;
	private String Make;
	private String Model;
	
	public Car(String color, String licenseNumber, String make, String model) {
		this.setColor(color);
		this.setLicenseNumber(licenseNumber);
		this.setMake(make);
		this.setModel(model);
	}
	
	private void setColor(String color) {
		Color = color;
	}
	
	private void setLicenseNumber(String licenseNumber) {
		LicenseNumber = licenseNumber;
	}
	
	private void setMake(String make) {
		Make = make;
	}
	
	private void setModel(String model) {
		Model = model;
	}
	
	public String getColor() {
		return Color;
	}

	public String getLicenseNumber() {
		return LicenseNumber;
	}

	public String getMake() {
		return Make;
	}

	public String getModel() {
		return Model;
	}
}
