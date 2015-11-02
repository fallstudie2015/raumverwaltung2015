/* Programmiert von: David Fankhänel
 * Programmiert für: BenutzerLöschen-Button auf der Hauptoberfläche
 * Beschreibung: Bereits angelegte Benutzer können gelöscht werden
 */

package de.dhbw.gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;

public class BenutzerLoeschen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_Vorname;
	private JTextField textField_nachname;
	private JTextField textField_email;
	private JLabel lblBenutzer_Vorname;
	private JLabel lblBenutzer_Nachname;
	private JLabel lblBenutzer_email;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();

	/*
	 * Fenster aufbauen
	 */
	public BenutzerLoeschen() {
		setModal(true); // Fenster wird aufgebaut
		setIconImage(Toolkit.getDefaultToolkit().getImage(BenutzerLoeschen.class
				.getResource("/ressources/menu_benutzer_loeschen_transp.png")));
		setResizable(false);
		setTitle("Benutzer löschen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblBenutzerLoeschen = new JLabel("Benutzer löschen");
		lblBenutzerLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBenutzerLoeschen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBenutzerLoeschen, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnLoeschen = new JButton("Löschen");
		btnLoeschen.addActionListener(mal);
		btnLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnLoeschen);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInvisible();
			}
		});
		splitPane.setRightComponent(btnAbbrechen);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(5, 0, 0, 0));

		lblBenutzer_Vorname = new JLabel("Vorname:");
		lblBenutzer_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_Vorname);

		lblBenutzer_Nachname = new JLabel("Nachname:");
		lblBenutzer_Nachname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_Nachname);

		lblBenutzer_email = new JLabel("E-Mail - Adresse:");
		lblBenutzer_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_email);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(5, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		textField_Vorname = new JTextField();
		textField_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(textField_Vorname, BorderLayout.CENTER);
		textField_Vorname.setColumns(10);
		textField_Vorname.addActionListener(mal);
		textField_Vorname.addKeyListener(esc);

		Component rigidArea = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea, BorderLayout.NORTH);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea_1, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		textField_nachname = new JTextField();
		textField_nachname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(textField_nachname, BorderLayout.CENTER);
		textField_nachname.setColumns(10);
		textField_nachname.addActionListener(mal);
		textField_nachname.addKeyListener(esc);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_2, BorderLayout.NORTH);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_3, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		textField_email = new JTextField();
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_7.add(textField_email, BorderLayout.CENTER);
		textField_email.setColumns(10);
		textField_email.addActionListener(mal);
		textField_email.addKeyListener(esc);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_4, BorderLayout.NORTH);

		Component rigidArea_5 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_5, BorderLayout.SOUTH);
	}

	private boolean PflichtfelderPruefen() // Prüft, ob Pflichtfelder gefüllt
	// sind
	{
		boolean gefuellt = true;

		if (textField_Vorname.getText().isEmpty()) {
			lblBenutzer_Vorname.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblBenutzer_Vorname.setForeground(Color.black);
		}

		if (textField_nachname.getText().isEmpty()) {
			lblBenutzer_Nachname.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblBenutzer_Nachname.setForeground(Color.black);
		}

		if (textField_email.getText().isEmpty()) {
			lblBenutzer_email.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblBenutzer_email.setForeground(Color.black);
		}

		return gefuellt;
	}

	private void setInvisible() { // Fenster unsichtbar machen
		this.setVisible(false);
	}

	public static void Erfolg(String nachricht) { // MessageBox für Rückgabewert
		JOptionPane.showMessageDialog(null, nachricht, "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public class MeinActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			boolean pflicht = PflichtfelderPruefen();

			if (pflicht) {
				boolean feedback = SQL_Schnittstelle.deleteBenutzer(
						textField_email.getText(), textField_Vorname.getText(),
						textField_nachname.getText());
				if (feedback == true) {// Rückgabewert der Methode Ausstattung
										// anlegen
					setInvisible();// Beim Klicken auf Abbrechen wird Fenster
									// unsichtbar
					Erfolg("Benutzer wurde gelöscht!");
				} else {
					Erfolg("Benutzer konnte nicht gelöscht werden!");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						" Bitte fuellen Sie die Pflichtfelder aus", "Achtung!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public class KeyListenerESC implements KeyListener {

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				setInvisible();
			}
		}

		public void keyPressed(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {

		}

	}
}
