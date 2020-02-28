package eg.edu.guc.santorini;

import java.util.ArrayList;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {
	private Location[][] locations;
	private Player p1;
	private Player p2;
	private Player turn;
	private boolean moved;

	public Board(Player p1, Player p2) {
		locations = new Location[SIDE][SIDE];
		this.p1 = p1;
		this.p2 = p2;
		turn = p1;
		moved = false;
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				Location l = new Location(i, j);
				locations[i][j] = l;
			}
		}
		p1.getT1().setLoc(locations[0][0]);
		p1.getT2().setLoc(locations[4][1]);
		p2.getT1().setLoc(locations[0][3]);
		p2.getT2().setLoc(locations[4][4]);
		p1.getT1().setBoard(this);
		p1.getT2().setBoard(this);
		p2.getT1().setBoard(this);
		p2.getT2().setBoard(this);
	}

	public void move(Piece piece, Location newLocation)
			throws InvalidMoveException {
		int x = newLocation.getX();
		int y = newLocation.getY();
		Location l = locations[y][x];
		if(isGameOver()){
			throw new InvalidMoveException("Game Over");
		}
		if (!l.equals(newLocation)) {
			throw new InvalidMoveException("location not found");
		}
		if (piece == p1.getT1() || piece == p1.getT2()) {
			if (turn == p2) {
				throw new InvalidMoveException("not your turn");
			}
		}
		if (piece == p2.getT1() || piece == p2.getT2()) {
			if (turn == p1) {
				throw new InvalidMoveException("not your turn");
			}
		}
		if (canMove(piece, newLocation)) {
			piece.setLoc(l);
			moved = true;
			if (turn == p1) {
				turn = p2;
			} else {
				turn = p1;
			}
		} else {
			throw new InvalidMoveException("Can't move");
		}

	}

	public void place(Piece piece, Location newLocation)
			throws InvalidPlacementException {
		int x = newLocation.getX();
		int y = newLocation.getY();
		Location l = locations[y][x];
		if(isGameOver()){
			throw new InvalidPlacementException("0");
		}
		if (!l.equals(newLocation)) {
			throw new InvalidPlacementException("location not found");
		}
		if (canPlace(piece, newLocation)) {
			if (moved) {
				l.setLevel(l.getLevel() + 1);
				moved = false;
			} else {
				throw new InvalidPlacementException("tile not moved");
			}
		} else {
			throw new InvalidPlacementException("Can't place");
		}
	}

	public boolean isGameOver() {
		if (getWinner() != null) {
			return true;
		}
		if (hasNoMoves(p1) && hasNoMoves(p2)) {
			return true;
		}
		return false;
	} 

	public boolean isWinner(Player player) {
		Player p = new Player();
		if (player == p1) {
			p = p2;
		}
		if (player == p2) {
			p = p1;
		} 
		if (hasNoMoves(p) && turn == p) {
			return true;
 
		}
		if (player.getT1().getLocation().getLevel() == 3
				|| player.getT2().getLocation().getLevel() == 3) {
			return true;
		} 
		return false;
	}

	public boolean hasNoMoves(Player player) {
		ArrayList<Location> l = player.getT1().possibleMoves();
		for (int i = 0; i < l.size(); i++) {
			if (canMove(player.getT1(), l.get(i))) {
				return false;
			}
		}
		l = player.getT2().possibleMoves();
		for (int i = 0; i < l.size(); i++) {
			if (canMove(player.getT2(), l.get(i))) {
				return false;
			}
		}  
		return true;
	}

	public Player getWinner() {
		if (isWinner(p1)) {
			return p1;
		}
		if (isWinner(p2)) {
			return p2;
		}
		return null;
	}

	public boolean canMove(Piece piece, Location location) {
		int x = location.getX();
		int y = location.getY();
		Location l = locations[y][x];
		ArrayList<Location> l1 = piece.possibleMoves();
		if (piece.getLocation().getLevel() < l.getLevel()-1) {
			return false;
		}

		if (p1.getT1().getLocation().equals(l)
				|| p1.getT2().getLocation().equals(l)
				|| p2.getT1().getLocation().equals(l)
				|| p2.getT2().getLocation().equals(l)) {
			return false;
		}
		if (l.getLevel() == 4) {
			return false;
		}
		for (int i = 0; i < l1.size(); i++) {
			if (l1.get(i).equals(location)) {
				return true;
			}
		}
		return false;
	} 
	 

	public boolean canPlace(Piece piece, Location location) {
		int x = location.getX();
		int y = location.getY();
		Location l = locations[y][x];
		ArrayList<Location> l1 = piece.possiblePlacements();
		if (l.getLevel() == 4) { 
			return false;
			
		}
		if (p1.getT1().getLocation()== location
				|| p1.getT2().getLocation() == location
				|| p2.getT1().getLocation() == location
				|| p2.getT2().getLocation() == location) {
			return false;
		}
		for (int i = 0; i < l1.size(); i++) {
			if (l1.get(i).equals(location)) {
				return true;
			}
		}
		return false;
	}

	public Player getTurn() {
		return this.turn;
	}

	public String[][] display() {
		String[][] s = new String[SIDE][SIDE];
		String r = "";
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {
				r = r + locations[i][j].getLevel();
				if (p1.getT1().getLocation().equals(locations[i][j])
						|| p1.getT2().getLocation().equals(locations[i][j])) {
					if (p1.getT1() instanceof Pyramid) {
						r = r + "P" + "1";
					} else {
						r = r + "C" + "1";
					}
				}
				if (p2.getT1().getLocation().equals(locations[i][j])
						|| p2.getT2().getLocation().equals(locations[i][j])) {
					if (p2.getT1() instanceof Pyramid) {
						r = r + "P" + "2";
					} else {
						r = r + "C" + "2";
					}
				}
				s[i][j] = r;
				r = "";
			}
		}

		return s;
	}

	public Location[][] getLocations() {
		return locations;
	}

	public void setLocations(Location[][] locations) {
		this.locations = locations;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public void setTurn(Player turn) {
		this.turn = turn;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

}
