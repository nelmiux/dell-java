import java.time.LocalDateTime;

public class TimesheetEntry {
	private static int NEXTID = 1;
	
	private String projectName;
	private String task;
	private int id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	TimesheetEntry(String myProject, String myTask) {
		this.setProjectName(myProject);
		this.setTask(myTask);
		this.setStartTime(LocalDateTime.now());
		this.setId(NEXTID++);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	public void updateEndTime() {
		if (this.endTime != null) {
			throw new IllegalArgumentException();
		}
		
		this.endTime = LocalDateTime.now();
	}
}
