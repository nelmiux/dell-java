public class TodoItem {
	/* Member variables */
	private int id;
	private String description;
	private boolean isCompleted;
	private String due;
	
	/* Constructor */
	TodoItem(int id, boolean isCompleted, String todoDescription, String due) {
		this.setId(id);
		this.setCompleted(isCompleted);
		this.setDescription(todoDescription);
		this.setDue(due);
	}

	/* Methods */

	/*
	 * Set the Id
	 */
	private void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Get the Id
	 */
	public int getId() {
		return this.id;
	}

	/*
	 * Get if it is completed
	 */
	public boolean getIsCompleted() {
		return isCompleted;
	}

	/*
	 * Set isCompleted
	 */
	private void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/*
	 * Get description
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * Set the description
	 */
	private void setDescription(String description) {
		this.description = description;
	}

	/*
	 * Get the due date
	 */
	public String getDue() {
		return due;
	}

	/*
	 * Set the due date
	 */
	public void setDue(String due) {
		this.due = due;
	}
}
