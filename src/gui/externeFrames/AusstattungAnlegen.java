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
import java.awt.Toolkit;

public class AusstattungAnlegen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_aName;
	private JLabel lblAusstattungsname;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();
	private Raumplaner_View rv;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AusstattungLoeschen frame = new AusstattungLoeschen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AusstattungAnlegen(Raumplaner_View rv) {
		this.rv = rv;
		setModal(true); // Fenster wird aufgebaut
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AusstattungAnlegen.class.getResource(
						"/ressources/menu_ausstattung_anlegen_transp.png")));
		setResizable(false);
		setTitle("Ausstattung anlegen");
		setLocationRelativeTo(null);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblAusstattungAnlegen = new JLabel("Ausstattung anlegen");
		lblAusstattungAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAusstattungAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAusstattungAnlegen, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnAnlegen = new JButton("Anlegen");
		btnAnlegen.addActionListener(mal);
		btnAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnAnlegen);

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

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(8, 0, 0, 0));

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);

		lblAusstattungsname = new JLabel("Ausstattungsname:");
		lblAusstattungsname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattungsname);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(8, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);

		textField_aName = new JTextField();
		panel_4.add(textField_aName);
		textField_aName.setColumns(10);
		textField_aName.addActionListener(mal);
		textField_aName.addKeyListener(esc);
	}

	private boolean PflichtfelderPruefen() // Prüft, ob Pflichtfelder gefüllt
	// sind
	{
		boolean gefuellt = true;

		if (textField_aName.getText().isEmpty()) {
			lblAusstattungsname.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblAusstattungsname.setForeground(Color.black);
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

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			boolean pflicht = PflichtfelderPruefen();

			if (pflicht) {
				boolean feedback = SQL_Schnittstelle
						.insertAusstattungsArtenLager(textField_aName.getText());

				if (feedback == true) { // Rückgabewert der Methode
										// Ausstattung
										// anlegen
					setInvisible();
					Erfolg("Ausstattung wurde erstellt!");
				} else {
					Erfolg("Ausstattung konnte nicht erstellt werden!");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						" Bitte fuellen Sie das Pflichtfeld aus",
						"Achtung!", JOptionPane.ERROR_MESSAGE);
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