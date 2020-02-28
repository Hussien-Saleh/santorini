package eg.edu.guc.santorini.gui;

import java.awt.*;
import javax.swing.*;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.players.Player;

import java.awt.event.*;

@SuppressWarnings("serial")
public class FirstWindow extends JFrame implements ActionListener, MouseListener{

	private JButton b1;
	private JTextField f1, f2;
	private JComboBox<String> jc1, jc2;
	private Board board;
	private Window2 w2;	
	
	public FirstWindow()
	{
		super("Santorini");
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container content = getContentPane();
		content.setBackground(Color.black);
		content.setLayout(null);
		setVisible(true);

		JLabel l1 = new JLabel("player 1");
		l1.setForeground(Color.white);
		l1.setBounds(15, 60, 200, 40);
		content.add(l1);

		JLabel l2 = new JLabel("player 2");
		l2.setForeground(Color.white);
		l2.setBounds(15, 120, 200, 40);
		content.add(l2);

		f1 = new JTextField();
		f1.setBounds(120, 60, 200, 40);
		content.add(f1);

		f2 = new JTextField();
		f2.setBounds(120, 120, 200, 40);
		content.add(f2);

		String[] s = new String [2];
		s[0] = "Pyramid";
		s[1] = "Cube";
		jc1 = new JComboBox<>(s);
		jc1.setBounds(350, 60, 100, 40);
		content.add(jc1);

		jc2 = new JComboBox<>(s);
		jc2.setBounds(350, 120, 100, 40);
		content.add(jc2);

		b1 = new JButton("Start");
		b1.setBounds(150, 490, 250, 50);
		b1.addMouseListener(this);
		b1.setVisible(true);
		content.add(b1);		

		validate();
		repaint();
	}
	


	public void mouseClicked(MouseEvent me) {
		w2 = new Window2(5, 5);
		if (me.getSource() == b1) {
			this.setVisible(false);
			String n1 = f1.getText();
			String n2 = f2.getText();
			w2.setP1(new Player(n1, jc1.getSelectedIndex() + 1));
			w2.setP2(new Player(n2, jc2.getSelectedIndex() + 1));
			w2.setVisible(true);
		}
			
		}
	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public JButton getB1() {
		return b1;
	}


	public void setB1(JButton b1) {
		this.b1 = b1;
	}


	public void mouseReleased(MouseEvent arg0) {

	}

	public void actionPerformed(ActionEvent arg0) {

	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board b) {
		this.board = b;
	}

	public JTextField getF1() {
		return f1;
	}


	public void setF1(JTextField f1) {
		this.f1 = f1;
	}


	public JTextField getF2() {
		return f2;
	}


	public void setF2(JTextField f2) {
		this.f2 = f2;
	}


	public JComboBox<String> getJc1() {
		return jc1;
	}


	public void setJc1(JComboBox<String> jc1) {
		this.jc1 = jc1;
	}


	public JComboBox<String> getJc2() {
		return jc2;
	}


	public void setJc2(JComboBox<String> jc2) {
		this.jc2 = jc2;
	}
}