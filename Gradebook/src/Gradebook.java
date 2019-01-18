import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Gradebook {
	private Map<String, String> studentGrades = new HashMap();
	private Map<String, Double> result = new HashMap();

	public Gradebook(Map<String, String> studentGrades) {
		this.studentGrades = studentGrades;
		this.setResult();
	}
	
	// Set the final map with names and average per student
	private void setResult() {
		for (Entry<String, String> entry: this.studentGrades.entrySet()) {
			this.result.put(entry.getKey(), this.getAverage(entry.getValue()));
		}
	}
	
	// Calculate average after passing a list of comma separated numbers string
	private Double getAverage(String strList) {
		Double sum = 0.0;
		String[] splittedGradeList = strList.split(",");
		for (String s: splittedGradeList) {
			sum += Double.parseDouble(s);
		}
		return sum / splittedGradeList.length;
	}
	
	// Get the final map to print with each student name and average
	public Map<String, Double> getResult() {
		return this.result;
	}
}
