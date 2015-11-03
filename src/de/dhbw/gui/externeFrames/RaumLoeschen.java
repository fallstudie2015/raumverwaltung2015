/* Programmiert von: David Fankhänel
 * Programmiert für: RaumLöschen-Button auf Hauptoberfläche
 * Beschreibung: Oberfläche die die Möglichkeit zum Löschen von Räumen ermöglicht
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

import de.dhbw.gui.PanelRaum;
import de.dhbw.gui.Raumplaner_View;
import de.dhbw.java.SQL_Schnittstelle;

public class RaumLoeschen extends JDialog {

	private JPanel contentPane;

	private Raumplaner_View rv;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();
	private PanelRaum pr;

	/*
	 * Fenster wird aufgebaut
	 */
	public RaumLoeschen(Raumplaner_View rv) {
		setModal(true); // Fenster wird aufgebaut
		setIconImage(Toolkit.getDefaultToolkit().getImage(RaumLoeschen.class
				.getResource("/ressources/menu_raum_loeschen_transp.png")));
		setResizable(false);
		this.rv = rv;
		setTitle("Raum löschen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblRaumLoeschen = new JLabel("Raum löschen");
		lblRaumLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaumLoeschen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRaumLoeschen, BorderLayout.NORTH);

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
				setInvisible(); // Beim Klicken auf Abbrechen wird Fenster
								// unsichtbar
			}
		});
		splitPane.setRightComponent(btnAbbrechen);

		pr = new PanelRaum();
		contentPane.add(pr, BorderLayout.CENTER);

	}

	private void setInvisible() // Fenster unsichtbar machen
	{
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
					.setDeleteFlagRaumByID(pr.getSelectedRaumID());
			if (feedback == true) {// Rückgabewert der Methode
									// Ausstattung anlegen
				setInvisible();
				Erfolg("Raum wurde gelöscht!");
				rv.setRaumArray(SQL_Schnittstelle.getRooms()); // aktualisiert
																// die
																// Räume
			} else {
				Erfolg("Raum konnte nicht gelöscht werden!");
			}

		}
	}

	public class KeyListenerESC implements KeyListener { // Key Listener, damit
															// mit ESC das
															// Fenster beendet
															// werden kann

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
