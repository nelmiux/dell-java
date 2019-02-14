import java.util.ArrayList;
import java.util.List;

public class Timesheet {
	private List<TimesheetEntry> database;

	Timesheet() {
		this.database = new ArrayList();
	}
	
	public void add(String project, String task) {
		TimesheetEntry timesheetEntry = new TimesheetEntry(project, task);
		
		database.add(timesheetEntry);
	}
	
	public List<TimesheetEntry> list(boolean activeOnly, String name) {
		List<TimesheetEntry> filteredList = new ArrayList();
		
		if (name != null) {
			for (TimesheetEntry timesheetEntry: database) {
				if (timesheetEntry.getProjectName().equals(name))
					filteredList.add(timesheetEntry);
			}
		} else {
			filteredList = database;
		}
		
		List<TimesheetEntry> resultedList = new ArrayList();;
		
		if (activeOnly) {
			for (TimesheetEntry timesheetEntry: filteredList) {
				if (timesheetEntry.getEndTime() == null) {
					resultedList.add(timesheetEntry);
				}
			}
			return resultedList;
		}
		
		return filteredList;
	}
	
	public TimesheetEntry get(int id) {
		for (TimesheetEntry timesheetEntry: database) {
			if (timesheetEntry.getId() == id) {
				return timesheetEntry;
			}
		}
		return null;
	}
	
	public void delete(TimesheetEntry entry) {
		database.remove(entry);
	}
	
	public void stop(TimesheetEntry entry) {
		entry.updateEndTime();
	}
}
