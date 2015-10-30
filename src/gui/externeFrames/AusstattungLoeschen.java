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
import gui.Raumplaner_View;
import gui.externeFrames.RaumLoeschen.MeinActionListener;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AusstattungLoeschen extends JDialog {

	private JPanel contentPane;
	private JComboBox comboBox_Ausstattung;
	private JLabel lblAusstattung;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();
	private Raumplaner_View rv;
	private ArrayList ausstattung;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// AusstattungLoeschen frame = new AusstattungLoeschen();
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
	public AusstattungLoeschen(Raumplaner_View rv) {
		this.rv = rv;
		setModal(true); // Fenster wird aufgebaut
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
				setInvisible();// Beim Klicken auf Abbrechen wird Fenster
								// unsichtbar
			}
		});
		splitPane.setRightComponent(btnAbbrechen);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(8, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);

		lblAusstattung = new JLabel("Ausstattungsname:");
		lblAusstattung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblAusstattung);

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(8, 0, 0, 0));

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);

		ausstattung = SQL_Schnittstelle.getAusstattungsArtenLager();
		ausstattung.toString();
		Object[] a = new Object[ausstattung.size()];
		a = ausstattung.toArray(a);
		
		comboBox_Ausstattung = new JComboBox(a);
		comboBox_Ausstattung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(comboBox_Ausstattung);
		comboBox_Ausstattung.addActionListener(mal);
		comboBox_Ausstattung.addKeyListener(esc);
	}


	private void setInvisible() { // Fenster unsichtbar machen
		this.setVisible(false);
	}

	public static void Erfolg(String nachricht) {// MessageBox für Rückgabewert
		JOptionPane.showMessageDialog(null, nachricht, "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public class MeinActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			{
				boolean feedback = SQL_Schnittstelle
						.deleteAusstattungArt(comboBox_Ausstattung.getName());

				if (feedback == true) { // Rückgabewert der Methode
										// Ausstattung anlegen
					setInvisible();
					Erfolg("Ausstattung wurde gelöscht!");
					rv.setGrundausstattungArray(
							SQL_Schnittstelle.getAusstattungsArtenLager());
				} else {
					Erfolg("Ausstattung konnte nicht gelöscht werden!");
				}

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
