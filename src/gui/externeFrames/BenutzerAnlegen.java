package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;
import gui.externeFrames.RaumLoeschen.MeinActionListener;

public class BenutzerAnlegen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_Name;
	private JTextField textField_Vorname;
	private JTextField textField_email;
	private JPasswordField passwordField;
	private ButtonGroup group;
	private JTextField textField_bereich;
	private JRadioButton rdbtnBenutzer;
	private JRadioButton rdbtnVerwalter;
	private JLabel lblName;
	private JLabel lblVorname;
	private JLabel lblBereich;
	private JLabel lblEmailAdresse;
	private JLabel lblPasswort;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BenutzerAnlegen frame = new BenutzerAnlegen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BenutzerAnlegen() {
		setModal(true); // Fenster wird aufgebaut
		setIconImage(Toolkit.getDefaultToolkit().getImage(BenutzerAnlegen.class
				.getResource("/ressources/menu_benutzer_anlegen_transp.png")));
		setTitle("Benutzer anlegen");
		setResizable(false);
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));


		JLabel lblBenutzerAnlegen = new JLabel("Benutzer anlegen");
		lblBenutzerAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBenutzerAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBenutzerAnlegen, BorderLayout.NORTH);

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
			public void actionPerformed(ActionEvent arg0) {
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
		panel_3.setLayout(new GridLayout(7, 0, 0, 0));

		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblName);

		lblVorname = new JLabel("Vorname");
		lblVorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblVorname);

		lblEmailAdresse = new JLabel("E-Mail - Adresse");
		lblEmailAdresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblEmailAdresse);

		lblPasswort = new JLabel("Passwort");
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblPasswort);

		JLabel lblRolle = new JLabel("Rolle:");
		lblRolle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblRolle);

		lblBereich = new JLabel("Bereich:");
		lblBereich.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBereich);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(7, 0, 0, 0));

		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_Name);
		textField_Name.setColumns(10);
		textField_Name.addActionListener(mal);
		textField_Name.addKeyListener(esc);

		textField_Vorname = new JTextField();
		textField_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_Vorname);
		textField_Vorname.setColumns(10);
		textField_Vorname.addActionListener(mal);
		textField_Vorname.addKeyListener(esc);

		textField_email = new JTextField();
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_email);
		textField_email.setColumns(10);
		textField_email.addActionListener(mal);
		textField_email.addKeyListener(esc);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(passwordField);
		passwordField.addActionListener(mal);
		passwordField.addKeyListener(esc);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		rdbtnVerwalter = new JRadioButton("Verwalter");
		rdbtnVerwalter.setSelected(false);
		panel_5.add(rdbtnVerwalter);
		rdbtnVerwalter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnVerwalter.addKeyListener(esc);

		rdbtnBenutzer = new JRadioButton("Benutzer");
		rdbtnBenutzer.setSelected(true);
		panel_5.add(rdbtnBenutzer);
		rdbtnBenutzer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnBenutzer.addKeyListener(esc);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBenutzer);
		group.add(rdbtnVerwalter);

		textField_bereich = new JTextField();
		textField_bereich.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_bereich);
		textField_bereich.setColumns(10);
		textField_bereich.addActionListener(mal);
		textField_bereich.addKeyListener(esc);

	}

	private boolean PflichtfelderPruefen() // Prüft, ob Pflichtfelder gefüllt
	// sind
	{
		boolean gefuellt = true;

		if (textField_Name.getText().isEmpty()) {
			lblName.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblName.setForeground(Color.black);
		}

		if (textField_Vorname.getText().isEmpty()) {
			lblVorname.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblVorname.setForeground(Color.black);
		}

		if (textField_email.getText().isEmpty()) {
			lblEmailAdresse.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblEmailAdresse.setForeground(Color.black);
		}

		if (passwordField.getText().isEmpty()) {
			lblPasswort.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblPasswort.setForeground(Color.black);
		}

		if (textField_bereich.getText().isEmpty()) {
			lblBereich.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblBereich.setForeground(Color.black);
		}

		return gefuellt;
	}

	private String RadioZurueck() // gibt den ausgewählten Wert der RadioButtons
									// zurück
	{

		String benutzerRolle = null;

		if (rdbtnBenutzer.isSelected()) { // entweder Benutzer
			benutzerRolle = "b";
		}

		else if (rdbtnVerwalter.isSelected()) // oder Verwalter
		{
			benutzerRolle = "v";
		}
		return benutzerRolle;
	}

	private String GetPasswort() // gibt den Wert des Passwortfeldes zurück
	{
		String pw = "";
		char[] charArray = passwordField.getPassword(); // wandelt char Array
														// von passwordField in
														// String um
		for (int i = 0; i < charArray.length; i++) {
			pw += charArray[i];
		}
		return pw;
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
				boolean feedback = SQL_Schnittstelle.insertBenutzer(
						textField_Name.getText(), textField_Vorname.getText(),
						textField_email.getText(), GetPasswort(),
						RadioZurueck(), textField_bereich.getText());
				if (feedback == true) {// Rückgabewert der Methode
										// Ausstattung
										// anlegen
					setInvisible();
					Erfolg("Benutzer wurde erstellt!");
				} else {
					Erfolg("Benutzer konnte nicht erstellt werden!");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						" Bitte fuellen Sie die Pflichtfelder aus", "Achtung!",
						JOptionPane.ERROR_MESSAGE);
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
