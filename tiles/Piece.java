package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {
	private Location loc;
	private Board board;

	public Piece() {

	}

	public abstract ArrayList<Location> possibleMoves();

	public ArrayList<Location> possiblePlacements() {
		ArrayList<Location> l = new ArrayList<>();
		int x = loc.getX();
		int y = loc.getY();
		if (y != 0 && x != 0) {
			l.add(board.getLocations()[y - 1][x - 1]);
		}
		if (y != 0) {
			l.add(board.getLocations()[y - 1][x]);
		}
		if (y != 0 && x != 4) {
			l.add(board.getLocations()[y - 1][x + 1]);
		}
		if (x != 4)  {
			l.add(board.getLocations()[y][x + 1]);
 }
		if (y != 4 && x != 4) {
			l.add(board.getLocations()[y + 1][x + 1]);
		}
		if (y != 4) {
			l.add(board.getLocations()[y + 1][x]);
		}
		if (y != 4 && x != 0) {
			l.add(board.getLocations()[y + 1][x - 1]);
		}
		if (x != 0) {
			l.add(board.getLocations()[y][x - 1]);
		}
		return l;
	}

	public Location getLocation() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
