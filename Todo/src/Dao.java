import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;

public class Dao {
	/* Member Variables */
	private DateTimeFormatter dateFormatter;
	
	/* Constructor */
	public Dao() {
		SQLiteJDBCDriverConnection.connect();
		dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	}
	
	/*
	 * Add a new entry to the table
	 */
	public void add(String description, LocalDate dueDate) {
		DateTimeFormatter dbDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String due = dueDate.format(dbDateFormatter);
		SQLiteJDBCDriverConnection.insert(description, due);
	}
	
	/*
	 * Update one entry in the table
	 */
	public void update(int id) {
		SQLiteJDBCDriverConnection.update(id);
	}
	
	/*
	 * Delete one entry in the table
	 */
	public void delete(int id) {
		SQLiteJDBCDriverConnection.delete(id);
	}
	
	/*
	 * List table entries with options all (null), only completed or only incomplete, due date, overdue
	 */
	public List<TodoItem> list(Boolean isCompleted, LocalDate dueDate, boolean isOverdue) {
		List<TodoItem> todoItems = SQLiteJDBCDriverConnection.list(isCompleted, dateFormatter);
		
		if (dueDate == null && !isOverdue)
			return todoItems;
		
		if (dueDate != null) {
			List<TodoItem> filteredTodoItems = new ArrayList();
			String due = dueDate.format(dateFormatter);
			for (TodoItem todoItem: todoItems) {
				if (todoItem.getDue().equals(due))
					filteredTodoItems.add(todoItem);
			}
			return filteredTodoItems;
		}
		
		List<TodoItem> filteredTodoItems = new ArrayList();
		
		for (TodoItem todoItem: todoItems) {
			if (LocalDate.now().isAfter(LocalDate.parse(todoItem.getDue(), dateFormatter)))
				filteredTodoItems.add(todoItem);
		}
		return filteredTodoItems;
	}
	
	/*
	 * List table entries sorted by due date, soonest first
	 */
	public List<TodoItem> sort() {
		return SQLiteJDBCDriverConnection.sort(dateFormatter);
	}
}
