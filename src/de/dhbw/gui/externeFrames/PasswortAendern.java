/* Programmiert von: David Fankhänel
 * Programmiert für: PasswortÄndern-Button auf der Hauptoberfläche
 * Beschreibung: Oberfläche um Passwort zu ändern
 */

package de.dhbw.gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;

public class PasswortAendern extends JDialog {

	private JPanel contentPane;
	private JPasswordField passwordFieldAlt;
	private JPasswordField passwordFieldNeu1;
	private JPasswordField passwordFieldNeu2;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();

	/*
	 * Fenster erstellen
	 */
	public PasswortAendern() {
		setModal(true); // Ab hier: Fenster wird aufgebaut
		setType(Type.UTILITY);
		setTitle("Passwort ändern");
		setResizable(false);
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblPasswortAendern = new JLabel("Passwort ändern");
		lblPasswortAendern.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPasswortAendern.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPasswortAendern, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnNewButton = new JButton("Ändern");
		btnNewButton.addActionListener(mal);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnNewButton);

		JButton btnNewButton_1 = new JButton("Abbrechen");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();// Beim Klicken auf Abbrechen wird Fenster
							// unsichtbar
			}
		});
		splitPane.setRightComponent(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(5, 0, 0, 0));

		JLabel lblAltesPasswort = new JLabel("Altes Passwort:");
		lblAltesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAltesPasswort);

		JLabel lblNeuesPasswort = new JLabel("Neues Passwort:");
		lblNeuesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblNeuesPasswort);

		JLabel lblPasswortBesttigen = new JLabel("Passwort bestätigen:");
		lblPasswortBesttigen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblPasswortBesttigen);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(5, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		Component rigidArea = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea, BorderLayout.NORTH);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea_1, BorderLayout.SOUTH);

		passwordFieldAlt = new JPasswordField();
		panel_5.add(passwordFieldAlt, BorderLayout.CENTER);
		passwordFieldAlt.addActionListener(mal);
		passwordFieldAlt.addKeyListener(esc);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		Component rigidArea_2 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_2, BorderLayout.NORTH);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_3, BorderLayout.SOUTH);

		passwordFieldNeu1 = new JPasswordField();
		panel_6.add(passwordFieldNeu1, BorderLayout.CENTER);
		passwordFieldNeu1.addActionListener(mal);
		passwordFieldNeu1.addKeyListener(esc);

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		Component rigidArea_4 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_4, BorderLayout.NORTH);

		Component rigidArea_5 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_5, BorderLayout.SOUTH);

		passwordFieldNeu2 = new JPasswordField();
		panel_7.add(passwordFieldNeu2, BorderLayout.CENTER);
		passwordFieldNeu2.addActionListener(mal);
		passwordFieldNeu2.addKeyListener(esc);
	}

	private String GetPasswortAlt() // gibt den Wert des Passwortfeldes zurück
	{
		String pw = "";
		char[] charArray = passwordFieldAlt.getPassword(); // wandelt char Array
															// von passwordField
															// in String um
		for (int i = 0; i < charArray.length; i++) {
			pw += charArray[i];
		}
		return pw;
	}

	private String GetPasswortNeu1() // gibt den Wert des Passwortfeldes zurück
	{
		String pw = "";
		char[] charArray = passwordFieldNeu1.getPassword(); // wandelt char
															// Array von
															// passwordField in
															// String um
		for (int i = 0; i < charArray.length; i++) {
			pw += charArray[i];
		}
		return pw;
	}

	private String GetPasswortNeu2() // gibt den Wert des Passwortfeldes zurück
	{
		String pw = "";
		char[] charArray = passwordFieldNeu2.getPassword(); // wandelt char
															// Array von
															// passwordField in
															// String um
		for (int i = 0; i < charArray.length; i++) {
			pw += charArray[i];
		}
		return pw;
	}

	public static void PwGeaendert(String nachricht) { // MessageBox für
														// Rückgabewert
		JOptionPane.showMessageDialog(null, nachricht, "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public class KeyListenerESC implements KeyListener { // Key Listener, damit
															// mit ESC das
															// Fenster beendet
															// werden kann

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				dispose();
			}
		}

		public void keyPressed(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {

		}

	}

	public class MeinActionListener implements ActionListener { // ActionListener,
																// für Drücken
																// auf den
																// Ändern Button

		public void actionPerformed(ActionEvent e) {

			String feedback = SQL_Schnittstelle.passwortAendern(
					GetPasswortAlt(), GetPasswortNeu1(), GetPasswortNeu2());
			PwGeaendert(feedback);// Rückgabewert der Methode Ausstattung
									// anlegen
			if (feedback == "Passwort wurde erfolgreich geändert!") {
				dispose();
			}
		}

	}
}
