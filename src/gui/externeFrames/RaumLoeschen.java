package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;
import gui.PanelRaum;
import gui.Raumplaner_View;
import gui.externeFrames.BenutzerAnlegen.KeyListenerESC;
import gui.externeFrames.RaumAnlegen.MeinActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class RaumLoeschen extends JDialog {

	private JPanel contentPane;
	private JComboBox combo_name;
	private Raumplaner_View rv;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();
	private PanelRaum pr;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// RaumLoeschen frame = new RaumLoeschen();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
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

	public class MeinActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		System.out.println(pr.getSelectedRaumID());
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

	

	public class KeyListenerESC implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				setInvisible();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
