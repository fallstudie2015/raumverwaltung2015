package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import de.dhbw.java.SQL_Schnittstelle;

public class TestPanelBuchung {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQL_Schnittstelle.createConnection();
		JFrame test = new JFrame();
		test.getContentPane().setLayout(new BorderLayout());
		test.getContentPane().add(new PanelBuchung(), BorderLayout.CENTER);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(500, 500);
		test.setVisible(true);

	}

}
