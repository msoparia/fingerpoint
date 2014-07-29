package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Test;

import psych.indiana.edu.mysql.MySQLAccess;

public class TestMySQLSelect {

	@Test
	public void test() {
		MySQLAccess testMySQLAccess = new MySQLAccess();
		ArrayList<Hashtable<String, String>> resultSet = new ArrayList<Hashtable<String,String>>();
		assertEquals(0,0);
		Exception myException = new Exception();
		try {
			testMySQLAccess.connect();
			resultSet = testMySQLAccess.selectQuery("select * from COMMENTS");
			Iterator<Hashtable<String, String>> resultIterator = resultSet.iterator();
			Hashtable<String, String> resultTuple = new Hashtable<String, String>();
			Set<String> columnSet;
			Iterator<String> columnIterator;
			String column, value;
			
			while (resultIterator.hasNext()){
				resultTuple = resultIterator.next();
				columnSet = resultTuple.keySet();
				columnIterator = columnSet.iterator();
				while (columnIterator.hasNext()){
					column = columnIterator.next();
					System.out.print(resultTuple.get(column)+"\t");
				}
				System.out.println("");
			}
			
			int error = testMySQLAccess.insertData("insert into COMMENTS (mysquser, email, webpage, datum, summery, comments) values ('drake', 'drake@ui.com', 'http://www.drake.me', CURDATE(), 'sum it up!', 'no comments please')");
			if(error==0){
				throw myException;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(e.equals(myException))
				System.out.println("Error Encounteres");
			e.printStackTrace();
		}
		
	}

}
