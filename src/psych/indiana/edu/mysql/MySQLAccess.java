package psych.indiana.edu.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@SuppressWarnings("deprecation")
	public void connect() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties p = new Properties();
		    p.put("user","eeglab");
		    p.put("password","eeg2001");
		    connect = DriverManager.getConnection("jdbc:mysql://localhost/fingerpoint",p);
		    
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}
	
	public ArrayList<Hashtable<String, String>> selectQuery(String query) {
		ArrayList<Hashtable<String, String>> resultArray = new ArrayList<Hashtable<String,String>>();
		ArrayList<String> columnNames = new ArrayList<String>();
		Hashtable<String, String> resultTuple = new Hashtable<String, String>();
		String columnName;
		int columnCount = 0;
		
		try{
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			columnCount = resultSet.getMetaData().getColumnCount();
			for  (int i = 1; i<= columnCount; i++){
				columnNames.add(resultSet.getMetaData().getColumnName(i));
			}
			Iterator<String> columnIterator = columnNames.iterator();
			while (resultSet.next()){
				while (columnIterator.hasNext()){
					columnName = columnIterator.next();
					resultTuple.put(columnName,resultSet.getString(columnName));
				}
				
				resultArray.add((Hashtable<String, String>) resultTuple.clone());
				resultTuple.clear();
				columnIterator = columnNames.iterator();
			}
			return resultArray;
		}
		catch (SQLException se)
		{
			se.printStackTrace();
			return new ArrayList<Hashtable<String,String>>();
		}
	}
	
	public int insertData(String query){
		try {
			statement = connect.createStatement();
			statement.executeUpdate(query);

			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	private void writeMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("The columns in the table are: ");
		
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			String user = resultSet.getString("mysquser");
			String website = resultSet.getString("webpage");
			String summery = resultSet.getString("summery");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("Summery: " + summery);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}
	
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
	
}
