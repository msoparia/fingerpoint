package psych.indiana.edu.mysql;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(int x, int y){
		this.setX(x);
		this.setY(y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
