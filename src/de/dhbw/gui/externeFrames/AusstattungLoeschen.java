/* Programmiert von: David Fankhänel
 * Programmiert für: AusstattungLöschen-Button auf der Hauptoberfläche
 * Beschreibung: Dient zur Löschung von bereits angelegten Ausstattungen
 */

package de.dhbw.gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.gui.PanelAusstattung;
import de.dhbw.gui.Raumplaner_View;
import de.dhbw.java.SQL_Schnittstelle;

public class AusstattungLoeschen extends JDialog {

	private JPanel contentPane;
	// private JComboBox comboBox_Ausstattung;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();
	private Raumplaner_View rv;
	// private ArrayList ausstattung;
	private PanelAusstattung pr;

	/*
	 * Fenster aufbauen
	 */
	public AusstattungLoeschen(Raumplaner_View rv) {
		this.rv = rv;
		setModal(true); // Ab hier: Fenster wird aufgebaut
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AusstattungLoeschen.class.getResource(
						"/ressources/menu_ausstattung_loeschen_transp.png")));
		setResizable(false);
		setTitle("Ausstattung löschen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblAusstattungLoeschen = new JLabel("Ausstattung löschen");
		lblAusstattungLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAusstattungLoeschen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAusstattungLoeschen, BorderLayout.NORTH);

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
				dispose();// Beim Klicken auf Abbrechen wird Fenster
							// unsichtbar
			}
		});
		splitPane.setRightComponent(btnAbbrechen);

		pr = new PanelAusstattung();
		contentPane.add(pr, BorderLayout.CENTER);
	}

	public static void Erfolg(String nachricht) {// MessageBox für Rückgabewert
		JOptionPane.showMessageDialog(null, nachricht, "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public class MeinActionListener implements ActionListener { // ActionListener,
																// für Drücken
																// auf den
																// Löschen
																// Button

		public void actionPerformed(ActionEvent e) {

			{
				boolean feedback = SQL_Schnittstelle.deleteAusstattungArtByID(
						pr.getSelectedAusstattungsID());

				if (feedback == true) { // Rückgabewert der Methode
										// Ausstattung anlegen

					Erfolg("Ausstattung wurde gelöscht!");
					rv.setGrundausstattungArray(
							SQL_Schnittstelle.getAusstattungsArtenLager());
					dispose();
				} else {
					Erfolg("Ausstattung konnte nicht gelöscht werden!");
				}

			}
		}
	}

	public class KeyListenerESC implements KeyListener {// Key Listener, damit
														// mit ESC das Fenster
														// beendet werden kann

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
}
