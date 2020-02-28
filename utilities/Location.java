package eg.edu.guc.santorini.utilities;

public class Location {
	private int x;
	private int y;
	private int level;
	
	public Location(int y, int x) {
		this.x = x;
		this.y = y;
		level = 0;
	}
	
	public boolean equals(Location l) {
		if (x == l.getX() && y == l.getY()) {
			return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	

}
