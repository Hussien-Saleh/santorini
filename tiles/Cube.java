package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {

	public Cube() {

	}

	public ArrayList<Location> possibleMoves() {

		ArrayList<Location> l = new ArrayList<>();
		int x = getLocation().getX();
		int y = getLocation().getY();
		if (y != 0) {
			l.add(getBoard().getLocations()[y - 1][x]);  
		}
		if (x != 4) {
			l.add(getBoard().getLocations()[y][x + 1]);
		}
		if (y != 4) {
			l.add(getBoard().getLocations()[y + 1][x]);
		}
		if (x != 0) {
			l.add(getBoard().getLocations()[y][x - 1]);
		}
		return l;
	} 

}
