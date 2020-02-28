package eg.edu.guc.santorini.gui;

import javax.swing.Icon;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Tile extends JLabel {
	
	private int layer;
	private boolean destroyed;
	
	public Tile() { }
	
	public Tile(Icon ico)
	{
		super(ico);
		layer = 0; 
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getLayer() {
		return layer;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	

}
