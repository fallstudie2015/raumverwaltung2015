/* Programmiert von: David Fankhänel
 * Programmiert für: BenutzerLöschen-Button auf der Hauptoberfläche
 * Beschreibung: Bereits angelegte Benutzer können gelöscht werden
 */

package de.dhbw.gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.gui.PanelBenutzer;
import de.dhbw.java.SQL_Schnittstelle;

public class BenutzerLoeschen extends JDialog {

	private JPanel contentPane;
	private PanelBenutzer meinPanelBenutzer;
	private MeinActionListener mal = new MeinActionListener();

	/*
	 * Fenster aufbauen
	 */
	public BenutzerLoeschen() {
		setModal(true); // Ab hier: Fenster wird aufgebaut
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						BenutzerLoeschen.class
								.getResource("/ressources/menu_benutzer_loeschen_transp.png")));
		setResizable(false);
		setTitle("Benutzer löschen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 500, 500);
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
		meinPanelBenutzer = new PanelBenutzer();
		contentPane.add(meinPanelBenutzer, BorderLayout.CENTER);

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
	}

	private void setInvisible() { // Fenster unsichtbar machen
		this.setVisible(false);
	}

	public static void Erfolg(String nachricht) { // MessageBox für Rückgabewert
		JOptionPane.showMessageDialog(null, nachricht, "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public class MeinActionListener implements ActionListener { // ActionListener,
																// für Drücken
																// auf den
																// Löschen
																// Button

		public void actionPerformed(ActionEvent e) {

			boolean feedback = SQL_Schnittstelle
					.deleteBenutzer(meinPanelBenutzer.getSelectedBenutzerID());
			if (feedback == true) {// Rückgabewert der Methode Ausstattung
									// anlegen
				setInvisible();// Nach löschen des Benutzers wird das Fenster
				// unsichtbar
				Erfolg("Benutzer wurde gelöscht!");
			} else {
				Erfolg("Benutzer konnte nicht gelöscht werden!");
			}
		}
	}
}
