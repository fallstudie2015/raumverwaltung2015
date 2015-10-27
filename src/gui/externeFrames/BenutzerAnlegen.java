package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(BenutzerAnlegen.class.getResource("/ressources/menu_benutzer_anlegen_transp.png")));
		setTitle("Benutzer anlegen");
		setResizable(false);
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblRaumAnlegen = new JLabel("Benutzer anlegen");
		lblRaumAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaumAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRaumAnlegen, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnAnlegen = new JButton("Anlegen");
		btnAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean feedback = SQL_Schnittstelle.insertBenutzer(textField_Name.getText(), textField_Vorname.getText(),
						textField_email.getText(), GetPasswort(), RadioZurueck(), textField_bereich.getText());
				if (feedback == true)
				{
					setVisible();
					Erfolg("Benutzer wurde erstellt");
				}
				else
				{
					Erfolg("Benutzer konnte nicht erstellt werden");
				}
			}
		});
		btnAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnAnlegen);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible();
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

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblName);

		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblVorname);

		JLabel lblEmailAdresse = new JLabel("E-Mail - Adresse");
		lblEmailAdresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblEmailAdresse);

		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblPasswort);

		JLabel lblRolle = new JLabel("Rolle:");
		lblRolle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblRolle);

		JLabel lblBereich = new JLabel("Bereich:");
		lblBereich.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBereich);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(7, 0, 0, 0));

		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_Name);
		textField_Name.setColumns(10);

		textField_Vorname = new JTextField();
		textField_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_Vorname);
		textField_Vorname.setColumns(10);

		textField_email = new JTextField();
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_email);
		textField_email.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(passwordField);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		rdbtnVerwalter = new JRadioButton("Verwalter");
		rdbtnVerwalter.setSelected(false);
		panel_5.add(rdbtnVerwalter);
		rdbtnVerwalter.setFont(new Font("Tahoma", Font.PLAIN, 11));

		rdbtnBenutzer = new JRadioButton("Benutzer");
		rdbtnBenutzer.setSelected(true);
		panel_5.add(rdbtnBenutzer);
		rdbtnBenutzer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBenutzer);
		group.add(rdbtnVerwalter);

		textField_bereich = new JTextField();
		textField_bereich.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(textField_bereich);
		textField_bereich.setColumns(10);

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

	private void setVisible() {
		this.setVisible(false);
	}
	
	public static void Erfolg(String nachricht) {
		JOptionPane.showMessageDialog(null, nachricht, "Information", JOptionPane.INFORMATION_MESSAGE);

	}
}
