package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class BenutzerAnlegen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private ButtonGroup group;
	private JTextField textField_3;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(BenutzerAnlegen.class.getResource("/ressources/menu_benutzer_anlegen_transp.png")));
		setTitle("Benutzer anlegen");
		setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			
			JButton btnNewButton = new JButton("Anlegen");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			splitPane.setLeftComponent(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Abbrechen");
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
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
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_4.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_4.add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_4.add(textField_2);
			textField_2.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_4.add(passwordField);
			
			JPanel panel_5 = new JPanel();
			panel_4.add(panel_5);
			panel_5.setLayout(new GridLayout(0, 2, 0, 0));
			
			JRadioButton rdbtnVerwalter = new JRadioButton("Verwalter");
			panel_5.add(rdbtnVerwalter);
			rdbtnVerwalter.setFont(new Font("Tahoma", Font.PLAIN, 11));
			group.add(rdbtnVerwalter);
			
			JRadioButton rdbtnBenutzer = new JRadioButton("Benutzer");
			panel_5.add(rdbtnBenutzer);
			rdbtnBenutzer.setFont(new Font("Tahoma", Font.PLAIN, 11));
			group.add(rdbtnBenutzer);
			ButtonGroup group = new ButtonGroup();
			
			textField_3 = new JTextField();
			textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_4.add(textField_3);
			textField_3.setColumns(10);
					
	}

}
