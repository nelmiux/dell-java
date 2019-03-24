import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * Class with static methods to create and access the db
 */
public class SQLiteJDBCDriverConnection {

	/* Methods */

	/*
	 * Table creation and setup
	 */
	public static void connect() {
		Connection connection = null;
		
        try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:db/tododb.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists todoItem");
			statement.executeUpdate("create table todoItem (id integer primary key autoincrement, isCompleted boolean, description string, due text)");
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
    }
	
	/*
	 * Insert a new entry to the table
	 */
	public static void insert(String description, String due) {
		Connection connection = null;
		
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:db/tododb.db");

			String sql = "insert into todoItem values(null, false, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, description);
	        preparedStatement.setString(2, due);
	        preparedStatement.setQueryTimeout(30);
	        preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}
	
	/*
	 * Update one entry in the table
	 */
	public static void update(int id) {
		Connection connection = null;
		
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:db/tododb.db");

			String sql = "update todoItem set isCompleted = true where id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, id);
	        preparedStatement.setQueryTimeout(30);
	        preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}
	
	/*
	 * Delete one entry in the table
	 */
	public static void delete(int id) {
		Connection connection = null;
		
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:db/tododb.db");

			String sql = "delete from todoItem where id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, id);
	        preparedStatement.setQueryTimeout(30);
	        preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}
	
	/*
	 * List table entries with options all, only completed or only incomplete
	 */
	public static List<TodoItem> list(Boolean isCompleted, DateTimeFormatter dateFormatter) {
		Connection connection = null;
		List<TodoItem> todoItems = new ArrayList<TodoItem>();
		
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:db/tododb.db");
			
			String sql = "select * from todoItem";
			
			if (isCompleted != null)
				sql += " where isCompleted = ?";
				
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setQueryTimeout(30);
	        
	        if (sql.contains("?"))
	        	preparedStatement.setBoolean(1, isCompleted);
	        
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				// read the result set
				LocalDate dueDate = LocalDate.parse(rs.getString("due"), DateTimeFormatter.ofPattern( "yyyy-MM-dd" ));
				
				String due = dueDate.format(dateFormatter);
				
				TodoItem todoItem = new TodoItem(rs.getInt("id"), rs.getBoolean("isCompleted"), rs.getString("description"), due);
				
				todoItems.add(todoItem);
			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		
		return todoItems;
	}
	
	/*
	 * List table entries sorted by due date, soonest first
	 */
	public static List<TodoItem> sort(DateTimeFormatter dateFormatter) {
		Connection connection = null;
		List<TodoItem> todoItems = new ArrayList<TodoItem>();
		
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:db/tododb.db");
			
			String sql = "select * from todoItem order by date(due) desc";
				
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setQueryTimeout(30);
	        
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				// read the result set
				LocalDate dueDate = LocalDate.parse(rs.getString("due"), DateTimeFormatter.ofPattern( "yyyy-MM-dd" ));
				
				String due = dueDate.format(dateFormatter);
				
				TodoItem todoItem = new TodoItem(rs.getInt("id"), rs.getBoolean("isCompleted"), rs.getString("description"), due);
				
				todoItems.add(todoItem);
			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		
		return todoItems;
	}
}
