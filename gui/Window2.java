package eg.edu.guc.santorini.gui;

import java.awt.*;
import javax.swing.*;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.players.Player;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Window2 extends JFrame implements ActionListener, MouseListener {

	private Tile[] labels;
	private Tile tempLabel;
	private JPanel gridPanel, buttonPanel;
	private Player p1;
	private Player p2;
	private Board board;

	public Window2(int rows, int columns) {
		super("Santorini");
		setSize(1000, 700);
		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container content = getContentPane();
		content.setBackground(Color.black);
		content.setLayout(new BorderLayout());

		labels = new Tile[rows * columns];

		createPanels(rows, columns);
		createGrid(rows, columns);
		createButtons();
		setVisible(true);
		board = new Board(p1, p2);
	}

	public void createPanels(int row, int column) {
		// Create Panel for the tiles grid
		gridPanel = new JPanel(new GridLayout(row, column));
		gridPanel.setBackground(Color.white);
		add(gridPanel, BorderLayout.CENTER);

		// Create panel for the buttons vertical bar
		buttonPanel = new JPanel(new GridLayout(10, 1));
		buttonPanel.setBackground(Color.white);
		add(buttonPanel, BorderLayout.EAST);
	}

	public void createGrid(int gridRows, int gridColumns) {
		ImageIcon icon = new ImageIcon("Layer0.jpg");
		for (int i = 0; i < 25; i++) {
			labels[i] = new Tile(icon);
			labels[i].addMouseListener(this);
			gridPanel.add(labels[i]);
		}
	}

	public void createButtons() {
		JLabel label = new JLabel("Build");
		label.setHorizontalAlignment(JLabel.CENTER);
		buttonPanel.add(label);

		ImageIcon icon = new ImageIcon("Hammer.png");
		JButton button1 = new JButton(icon);
		button1.setActionCommand("Build");
		buttonPanel.add(button1);
		button1.setOpaque(false);
		button1.setFocusPainted(false);
		button1.setBorder(BorderFactory.createEmptyBorder());
		button1.setBackground(new Color(0, 0, 0, 0));
		button1.addActionListener(this);

		JLabel gap = new JLabel("");
		buttonPanel.add(gap);

		JLabel label2 = new JLabel("Destroy");
		label2.setHorizontalAlignment(JLabel.CENTER);
		buttonPanel.add(label2);

		ImageIcon icon2 = new ImageIcon("Destroy.png");
		JButton button2 = new JButton(icon2);
		button2.setActionCommand("Destroy");
		buttonPanel.add(button2);
		button2.setOpaque(false);
		button2.setFocusPainted(false);
		button2.setBorder(BorderFactory.createEmptyBorder());
		button2.setBackground(new Color(0, 0, 0, 0));
		button2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Build") && !tempLabel.isDestroyed()) {
			if (tempLabel != null && tempLabel.getIcon() != null) {
				if (tempLabel.getLayer() >= 4) {
					tempLabel.setLayer(-1);
					tempLabel.setDestroyed(true);
				}
				tempLabel.setLayer(tempLabel.getLayer() + 1);
				tempLabel.setIcon(new ImageIcon("Layer" + tempLabel.getLayer()
						+ ".png"));
			}
		} else if (e.getActionCommand().equals("Destroy")) {
			if (tempLabel != null) {
				tempLabel.setIcon(null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		tempLabel = (Tile) e.getSource();
		tempLabel.setBorder(BorderFactory.createLoweredSoftBevelBorder());

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		tempLabel.setBorder(BorderFactory.createEmptyBorder());

	}

	public Tile[] getLabels() {
		return labels;
	}

	public void setLabels(Tile[] labels) {
		this.labels = labels;
	}

	public Tile getTempLabel() {
		return tempLabel;
	}

	public void setTempLabel(Tile tempLabel) {
		this.tempLabel = tempLabel;
	}

	public JPanel getGridPanel() {
		return gridPanel;
	}

	public void setGridPanel(JPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
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

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
