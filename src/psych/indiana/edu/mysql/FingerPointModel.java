package psych.indiana.edu.mysql;

import java.util.ArrayList;
import java.util.Hashtable;

public class FingerPointModel {
	 private static FingerPointModel instance = null;
	 private ArrayList<Coordinates> coordinateList;
	 
	 
	 private FingerPointModel(){
		 setCoordinateList(new ArrayList<Coordinates>());
	 }
	 
	 public static FingerPointModel getInstance(){
		 if ( instance == null ){
			 instance = new FingerPointModel();
		 }
		 return instance;
	 }

	public ArrayList<Coordinates> getCoordinateList() {
		return coordinateList;
	}

	public void setCoordinateList(ArrayList<Coordinates> coordinateList) {
		this.coordinateList = coordinateList;
	}
	
	public void addCoordinates (Double x, Double y){
		coordinateList.clear();
		coordinateList.add(new Coordinates(x.intValue(), y.intValue()));
	}	
}
